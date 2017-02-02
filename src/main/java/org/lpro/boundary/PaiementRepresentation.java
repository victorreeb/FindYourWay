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
import org.lpro.entity.Paiement;

/**
 *
 * @author remaki
 */
@Path("/paiements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class PaiementRepresentation {
    
     @EJB
    PaiementResource paiementResource;

    @GET
    @Path("/{paiementId}")
    public Response getOnePaiement(@PathParam("paiementId") String paiementId) {
        Paiement p = this.paiementResource.findById(paiementId);
        if (p != null) {
            return Response.ok(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{paiementId}")
    public void deletePaiement(@PathParam("paiementId") String paiementId) {
        paiementResource.removePaiement(paiementId);
    }

    @POST
    public Response addPaiement(Paiement paiement, @QueryParam("commandeId") String commandeId, @Context UriInfo uriInfo) {
        Paiement p = this.paiementResource
                .ajoutePaiement(commandeId,new Paiement(paiement.getMail(),paiement.getName(),
                paiement.getDateExperation(),paiement.getNumber_card(),paiement.getCvv2(),paiement.getType(),paiement.getMontant()));
        URI uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commandeId)
                .path(PaiementRepresentation.class)
                .path(p.getId())
                .build();
        return Response.created(uri).entity(p).build();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
