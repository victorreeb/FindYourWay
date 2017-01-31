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
import org.lpro.entity.Commande;
import org.lpro.entity.Ingredients;
import org.lpro.entity.Sandwich;

/**
 *
 * @author remaki
 */
@Path("/sandwichs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class SandwichRepresentation {
    
    @EJB
    SandwichResource sandResource;
    @EJB
    CommandeResource commandeResource;
    @GET
    @Path("/{sandwichId}")
    public Response getAllSandwichs(@PathParam("sandwichId") String sandwichId) {
        Sandwich san = this.sandResource.findById(sandwichId);
        if (san != null) {
            return Response.ok(san).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{sandwichId}")
    public void deleteSandwich(@PathParam("sandwichId") String sandwichId) {
        sandResource.removeSandwich(sandwichId);
    }
    
   

    
    
    
    
    
    
    
    
}
