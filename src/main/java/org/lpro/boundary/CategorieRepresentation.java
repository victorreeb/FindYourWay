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
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Categorie;
import org.lpro.entity.Ingredients;

/**
 *
 * @author remaki
 */
@Stateless //EJB: transactionnel pris en charge, le fait qu'il y ait plusieurs requetes, que les ressources soient vérouillées
@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CategorieRepresentation {
    
     @EJB // @Inject pareil, pour l'injection de dépendance
    CategorieResource catResource;
    @EJB
    IngredientsResource ingredientsResource;
    
     @GET
    public Response getCategories(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Categorie> liste = this.catResource.findAll();
        
        //pour chaque message
        for(Categorie cat : liste) {
            
            List<Ingredients> lin = this.ingredientsResource.findAll(cat.getId());
            cat.getLinks().clear();
            cat.addLink(this.getUriForSelfCategorie(uriInfo, cat),"self");
            cat.addLink(this.getUriForIngredients(uriInfo, cat),"ingredients");
            for(Ingredients in : lin ) {
                
                in.getLinks().clear();
                in.addLink(this.getUriForSelfIngredient(uriInfo, in, cat),"self");
            }
            cat.setIngredients(lin);
        
        };
        GenericEntity<List<Categorie>> list = new GenericEntity<List<Categorie>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    
   @GET
    @Path("/{categorieId}")
    public Response getCategorie(@PathParam("categorieId") String categorieId, @Context UriInfo uriInfo) {
        Categorie categorie = this.catResource.findById(categorieId);
        if (categorie != null) {
            
            categorie.getLinks().clear();
            categorie.addLink(this.getUriForSelfCategorie(uriInfo, categorie),"self");
            List<Ingredients> lin = this.ingredientsResource.findAll(categorieId);
            
            for(Ingredients in : lin) {
                
                in.getLinks().clear();
                
                in.addLink(this.getUriForSelfIngredient(uriInfo, in, categorie), "self");
                
            }
            categorie.setIngredients(lin);
            return Response.ok(categorie).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
   @GET
    @Path("{categorieId}/ingredients")
    public Response getAllIngredients(@PathParam("categorieId") String categorieId, @Context UriInfo uriInfo) {
        List<Ingredients> lin = this.ingredientsResource.findAll(categorieId);
        Categorie categorie = this.catResource.findById(categorieId);
        for(Ingredients in : lin) {
                
                in.getLinks().clear();
                
                in.addLink(this.getUriForIngredients(uriInfo, categorie),categorieId);
                
            }
        
        GenericEntity<List<Ingredients>> list = new GenericEntity<List<Ingredients>>(lin) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
  
    
     @GET
    @Path("{categorieId}/ingredients/{ingredientId}")
    public Response getOneIngredient(@PathParam("categorieId") String categorieId,
            @Context UriInfo uriInfo,
            @PathParam("ingredientId") String ingredientId) {
        Ingredients in = this.ingredientsResource.findById(ingredientId);
        return Response.ok(in, MediaType.APPLICATION_JSON).build();
    }

     @POST
    public Response addCategorie(Categorie categorie, @Context UriInfo uriInfo) {
        Categorie newCategorie = this.catResource.save(categorie);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCategorie.getId()).build();
        return Response.created(uri)
                .entity(newCategorie)
                .build();
    }
 
    
    
    @POST
    @Path("/{categorieId}/ingredients")
    public Response addIngredient(@PathParam("categorieId") String categorieId,
            Ingredients ingredient,
            @Context UriInfo uriInfo) {
        Ingredients in = this.ingredientsResource.ajouteIngredient(categorieId, ingredient);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/")
                .path(in.getId())
                .build();
        return Response.created(uri).entity(in).build();
    }
    
    
   @DELETE
    @Path("/{categorieId}")
    public void deleteCategorie(@PathParam("categorieId") String id) {
        this.catResource.delete(id);
    }
     
    
    
    
    
    
    
      //pour une categorie particuliere
    private String getUriForSelfCategorie(UriInfo uriInfo, Categorie categorie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CategorieRepresentation.class)
                .path(categorie.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de categories
    private String getUriForCategorie(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CategorieRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    // pour un ingredient d'une categorie 
    private String getUriForSelfIngredient(UriInfo uriInfo, Ingredients in, Categorie categorie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CategorieRepresentation.class)
                .path(categorie.getId())
                .path(IngredientsRepresentation.class)
                .path(in.getId())
                .build()
                .toString();
        
        return uri;
        
    }
    
    
    //pour la collection d'ingredients d'une categorie
    
    private String getUriForIngredients(UriInfo uriInfo, Categorie categorie) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CategorieRepresentation.class)
                .path(categorie.getId())
                .path(IngredientsRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
        
        
    }
    
    
    
    
}
