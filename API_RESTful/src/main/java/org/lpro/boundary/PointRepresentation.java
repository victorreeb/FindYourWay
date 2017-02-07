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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Point;

/**
 *
 * @author remaki
 */
@Path("/privee/points")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PointRepresentation {
    
    @EJB // @Inject pareil, pour l'injection de dépendance
    PointResource ptResource;
    
    
     @Context
    private UriInfo uriInfo;
    
     
     
    @GET
    public Response getPoints(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Point> liste = this.ptResource.findAdminAll();
        
        //pour chaque message
        for(Point p : liste) {
            
           
            p.getLinks().clear();
            p.addLink(this.getUriForSelfPoint(uriInfo, p),"self");
            
            
        
        };
        GenericEntity<List<Point>> list = new GenericEntity<List<Point>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{pointId}")
    public Response getPoint(@PathParam("pointId") String pointId, @Context UriInfo uriInfo) {
        Point point = this.ptResource.findById(pointId);
        if (point != null) {
            
            point.getLinks().clear();
            point.addLink(this.getUriForSelfPoint(uriInfo, point),"self");
            
            return Response.ok(point).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Path("/{pointId}")
    public void deletePoint(@PathParam("pointId") String pointId) {
        ptResource.removePoint(pointId);
    }

    
     
    
     @POST
    public Response addPoint(Point point, @Context UriInfo uriInfo) {
        Point newPoint = this.ptResource.ajoutePoint(point);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPoint.getId()).build();
        return Response.created(uri)
                .entity(newPoint)
                .build();
    }
    
    
    
    
     //pour un message particulier
    private String getUriForSelfPoint(UriInfo uriInfo, Point point) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PointRepresentation.class)
                .path(point.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de messages
    private String getUriForPoint(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PointRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
