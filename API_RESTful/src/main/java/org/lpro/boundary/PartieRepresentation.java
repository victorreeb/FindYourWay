/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import control.KeyManagement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.net.URI;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Partie;
import provider.AuthentificationFiltre;

/**
 *
 * @author remaki
 */
@Path("/parties")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PartieRepresentation {
    
    String token;
    @Inject
    private KeyManagement keyManagement;
     @Inject
    private AuthentificationFiltre filtre;
    @EJB // @Inject pareil, pour l'injection de dépendance
    PointResource ptResource;
    @EJB
    DestinationResource destResource;
    
    @EJB
    PartieResource partResource;
    
     @Context
    private UriInfo uriInfo;
    // création d'une partie : refaire pour le client POST (X,Y) 
    /* 
    @POST
    public Response addPartie(Partie partie, @Context UriInfo uriInfo) {
        Partie newPartie = this.partResource.save(partie);
        this.token = issueToken(newPartie.getId());
        System.out.println("token =="+this.token);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPartie.getId().toString())                
                .build();
        return Response.created(uri)
                .entity(newPartie).
                header(AUTHORIZATION,"Bearer "+this.token)
                .build();
    }
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private String issueToken(String commandeId) {
        Key key = keyManagement.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(commandeId)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(5L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(">>>> token/key : " + jwtToken + " - " + key);
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    } 
    
    
    
}
