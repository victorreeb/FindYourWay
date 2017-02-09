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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Destination;
import org.lpro.entity.Partie;
import org.lpro.entity.Point;
import provider.AuthentificationFiltre;
import provider.Secured;

/**
 *
 * @author remaki
 */
@Path("/parties")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PartieRepresentation {
    Partie currentPartie;
    String token;
    int etape=0;
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

   
    @POST
    public Response addPartie(Partie partie, @Context UriInfo uriInfo) {
        currentPartie=null;
        List<Point> points = this.ptResource.findAdminAll();
        List<Destination> destinations = this.destResource.findAdminAll();
        Collections.shuffle(points);
       // Collections.shuffle(destinations);
        Partie newPartie  = new Partie();
        Destination destination = new Destination();
        destination = destinations.get(0);
        //newPartie = partie;
        newPartie.setNom(partie.getNom());
         newPartie.setDescription(partie.getDescription());
        System.out.println("points  ="+points.toString());
        System.out.println("points  ="+destination.getLat());
        newPartie.setPoints(points);
        newPartie.setDestination(destinations);
        this.destResource.ajouteDestination(newPartie.getId(), destination);
         this.currentPartie = this.partResource.save(newPartie);
         
        this.token = issueToken(newPartie.getId());
        System.out.println("token =="+this.token);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPartie.getId().toString())                
                .build();
        return Response.created(uri).
             
                header(AUTHORIZATION,"Bearer "+this.token)
                .build();
    }
    
     @GET
    public Response getPartie(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Partie> liste = this.partResource.findAll();
        
        //pour chaque message
        for(Partie p : liste) {
            
            List<Point> lp = this.ptResource.findAll(p.getId());
            List<Destination> ld = this.destResource.findAll(p.getId());
            p.getLinks().clear();
            p.addLink(this.getUriForSelfPartie(uriInfo, p),"self");
            p.addLink(this.getUriForPoints(uriInfo, p),"points");
            for(Point pt : lp ) {
                
                p.getLinks().clear();
                p.addLink(this.getUriForSelfPoint(uriInfo, pt, p),"self");
            }
            p.setPoints(lp);
            for(Destination d : ld ) {
                
                d.getLinks().clear();
                d.addLink(this.getUriForSelfDestination(uriInfo, d, p),"self");
            }
            p.setDestination(ld);
        };
        GenericEntity<List<Partie>> list = new GenericEntity<List<Partie>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    @Secured
    @GET 
    @Path("/point")
    public Response getPoint( @Context UriInfo uriInfo) {
    
       String  appellation = this.currentPartie.getPoints().get(this.etape).getAppellation();
       System.out.println("apppelation 1 ="+appellation);
        JsonObject jsonResult = Json.createObjectBuilder().add("appellation",  appellation ).build();
            
        return Response.ok().entity(jsonResult).build();
     
    }
   
    
    
     @POST
     @Path("/points")   
     public  Response sendPoint(Point point, @Context UriInfo uriInfo) {
       
       String indice=null;
       if(this.verifierPoint(point.getLat(), point.getLng())) {
         indice=  this.currentPartie.getDestination().get(0).getIndices().get(this.etape);
         this.etape++;
       }
       System.out.println("indice="+indice);
       JsonObject jsonResult = Json.createObjectBuilder().add("indice",  indice).build();
            
        return Response.ok().entity(jsonResult).build();
   }
    
     
      @POST
     @Path("/destination")   
     public  Response sendDestination(Point point, @Context UriInfo uriInfo) {
       int score = 0;
       
       if(this.verifieDestination(point.getLat(), point.getLng()) && this.etape == 5) {
         score =10;
       }
       System.out.println("indice="+score);
       JsonObject jsonResult = Json.createObjectBuilder().add("score",  score).build();
            
        return Response.ok().entity(jsonResult).build();
   } 
     
   /* 
    @GET
    @Path("/{partieId}")
    public Response getPartie(@PathParam("partieId") String partieId, @Context UriInfo uriInfo) {
        Partie partie = this.partResource.findById(partieId);
         Destination dest = this.destResource.findOneDestination(partie.getId());
        if (partie != null) {
            
            partie.getLinks().clear();
            partie.addLink(this.getUriForSelfPartie(uriInfo, partie),"self");
            List<Point> lp = this.ptResource.findAll(partieId);
            
            for(Point p : lp) {
                
                p.getLinks().clear();
                
                p.addLink(this.getUriForSelfPoint(uriInfo, p, partie), "self");
                
            }
            partie.setPoints(lp);
            partie.setDestination(dest);
            return Response.ok(partie).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }*/

    
    
    //pour un message particulier
    private String getUriForSelfPartie(UriInfo uriInfo, Partie partie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .path(partie.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de messages
    private String getUriForPartie(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    // pour un commentaire d'un message 
    private String getUriForSelfPoint(UriInfo uriInfo, Point pt, Partie partie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .path(partie.getId())
                .path(PointRepresentation.class)
                .path(pt.getId())
                .build()
                .toString();
        
        return uri;
        
    }
    
    private String getUriForSelfDestination(UriInfo uriInfo, Destination dest, Partie partie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .path(partie.getId())
                .path(DestinationRepresentation.class)
                .path(dest.getId())
                .build()
                .toString();
        
        return uri;
        
    }
    //pour la collection de commentaires d'un message 
    
    private String getUriForPoints(UriInfo uriInfo, Partie partie) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .path(partie.getId())
                .path(PointRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
        
        
    }
    
    
    private boolean verifierPoint(double lat, double lng){
        
        boolean res=true;
        
        if(this.currentPartie.getPoints().get(this.etape).getLat()==lat && this.currentPartie.getPoints().get(this.etape).getLng()==lng){
            
            res =true;
        }
        
        
        
        return res;
    }
    
    
    
    private boolean verifieDestination(double lat, double lng){
        
        boolean res=true;
        
        if((this.currentPartie.getDestination().get(0).getLat())==lat && (this.currentPartie.getDestination().get(0).getLng())==lng ){
            
            res = true;
        }
        return res;
    }
    
    
    
    
    
    
    

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
