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
    
    
     @POST
    public Response addPoint(Point point, @Context UriInfo uriInfo) {
        Point newPoint = this.ptResource.ajoutePoint(point);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPoint.getId()).build();
        return Response.created(uri)
                .entity(newPoint)
                .build();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
