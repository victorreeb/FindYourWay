/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Destination;
import org.lpro.entity.Point;

/**
 *
 * @author remaki
 */
@Path("/privee/destinations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class DestinationRepresentation {    
    
    
    @EJB // @Inject pareil, pour l'injection de dépendance
    DestinationResource destResource;    
    
     @Context
    private UriInfo uriInfo;
     
     @GET
    public Response getDestinations(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Destination> liste = this.destResource.findAdminAll();
        
        //pour chaque message
        for(Destination d : liste) {
            
            
            d.getLinks().clear();
            d.addLink(this.getUriForSelfDestination(uriInfo, d),"self");
            
            
        
        };
        GenericEntity<List<Destination>> list = new GenericEntity<List<Destination>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{destinationId}")
    public Response getMessage(@PathParam("destinationId") String destinationId, @Context UriInfo uriInfo) {
        Destination destination = this.destResource.findById(destinationId);
        if (destination != null) {
            
            destination.getLinks().clear();
            destination.addLink(this.getUriForSelfDestination(uriInfo, destination),"self");
           
            return Response.ok(destination).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }    
     
     
     
        
     @POST
    public Response addDestination(Destination destination, @Context UriInfo uriInfo) {
        Destination newDestination = this.destResource.ajouteDestination(destination);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newDestination.getId()).build();
        return Response.created(uri)
                .entity(newDestination)
                .build();
    }
    
    
     //PUT: modification d'un objet de la collection
    
    @PUT
    @Path("{id}")
    public Destination update(@PathParam("id") String id, Destination destination) {
        Destination dest = this.destResource.findById(id);
        dest.setLat(destination.getLat());
        dest.setLng(destination.getLng());
        dest.setDescription(destination.getDescription());
        dest.setLieu(destination.getLieu());
        dest.setIndices(destination.getIndices());
        return this.destResource.save(destination);
    }
    
    
    
    @DELETE
    @Path("/{destinationId}")
    public void deleteMessage(@PathParam("destinationId") String id) {
        this.destResource.removeDestination(id);
    }
    
    
    //pour une destination particulière
    private String getUriForSelfDestination(UriInfo uriInfo, Destination destination) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(DestinationRepresentation.class)
                .path(destination.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de destinations
    private String getUriForDestination(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(DestinationRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
