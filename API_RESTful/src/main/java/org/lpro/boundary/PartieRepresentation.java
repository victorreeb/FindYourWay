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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Partie;

/**
 *
 * @author remaki
 */
@Path("/parties")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PartieRepresentation {
    
    
    @EJB // @Inject pareil, pour l'injection de dépendance
    PointResource ptResource;
    @EJB
    DestinationResource destResource;
    
    @EJB
    PartieResource partResource;
    
    // création d'une partie
    @POST
    public Response addPartie(Partie partie, @Context UriInfo uriInfo) {
        Partie newPartie = this.partResource.save(partie);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPartie.getId()).build();
        return Response.created(uri)
                .entity(newPartie)
                .build();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
