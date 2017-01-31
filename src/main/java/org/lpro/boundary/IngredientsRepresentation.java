/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.net.URI;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Ingredients;

/**
 *
 * @author remaki
 */

@Path("/ingredients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class IngredientsRepresentation {
    
     @EJB
    IngredientsResource ingredientResource;

    @GET
    @Path("/{ingredientId}")
    public Response getAllIngredients(@PathParam("ingredientId") String ingredientId) {
        Ingredients in = this.ingredientResource.findById(ingredientId);
        if (in != null) {
            return Response.ok(in).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{ingredientId}")
    public void deleteIngredient(@PathParam("ingredientId") String ingredientId) {
        ingredientResource.delete(ingredientId);
    }

    @POST
    public Response addIngredient(Ingredients ingredient, @QueryParam("categorieId") String categorieId, @Context UriInfo uriInfo) {
        Ingredients in = this.ingredientResource
                .ajouteIngredient(categorieId, new Ingredients(ingredient.getNom(), ingredient.getCategorie()));
        URI uri = uriInfo.getBaseUriBuilder()
                .path(CategorieRepresentation.class)
                .path(categorieId)
                .path(IngredientsRepresentation.class)
                .path(in.getId())
                .build();
        return Response.created(uri).entity(in).build();
    }
    
    
    
    
    
    
    @GET
    @Path("/{ingredientId}/categorie")
    public Response getIngredientCategorie(@PathParam("ingredientId") String ingredientId) {
        Ingredients in = this.ingredientResource.findById(ingredientId);
        if (in != null) {
            return (Response.ok(in.getCategorie(),MediaType.APPLICATION_JSON).build());
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
