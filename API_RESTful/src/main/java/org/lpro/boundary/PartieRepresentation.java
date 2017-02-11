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
    String identifiant;
    private double tolerance = 50;
    private int score=0;
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
        partie = this.partResource.save(partie);
        for(Point pt : points ) {
            this.ptResource.ajoutePoint(partie.getId(), pt);
               
            }
        for(Destination d : destinations ) {
            
            this.destResource.ajouteDestination(partie.getId(),d);
               
            }         
        System.out.println("id de la partie >>>>>"+partie.getId());
        
        this.identifiant = partie.getId();
        this.token = issueToken(newPartie.getId());
        System.out.println("token =="+this.token);
        URI uri = uriInfo.getAbsolutePathBuilder().path(partie.getId().toString())                
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
       this.currentPartie = this.partResource.findById(this.identifiant);
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
         score = this.attribuerScore(point.getLat(), point.getLng());
       }
       System.out.println("indice="+score);
       JsonObject jsonResult = Json.createObjectBuilder().add("score",  score).build();
            
        return Response.ok().entity(jsonResult).build();
   } 
            
    
    //pour une partie particulière 
    private String getUriForSelfPartie(UriInfo uriInfo, Partie partie) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .path(partie.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de parties
    private String getUriForPartie(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(PartieRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    // pour un point d'une partie
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
    //pour la collection de points d'une partie 
    
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
        
        boolean res=false;
        
        if(this.currentPartie.getPoints().get(this.etape).getLat()==lat && this.currentPartie.getPoints().get(this.etape).getLng()==lng){
            
            res =true;
        }
        
        
        
        return res;
    }
      
    
    
     private boolean verifieDestination(double lat, double lng){
        
        boolean res=false;               
        if(distance(this.currentPartie.getDestination().get(0).getLat(),this.currentPartie.getDestination().get(0).getLng(),lat,lng,"K")<=this.tolerance) {
            
            res = true;
        }
        return res;
    }
    
    
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
    
    private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
    
    private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

    
    
    
    
    private int attribuerScore(double lat1, double lon1) {
       
        
        double res = distance(lat1,lon1,this.currentPartie.getDestination().get(0).getLat(),this.currentPartie.getDestination().get(0).getLng(),"K");
        
            if(res < this.tolerance) {this.score = 10;}        
                else if (res < (2*this.tolerance ))  {  this.score = 8; }        
                else if (res < (3*this.tolerance ))  {  this.score = 6; }
                else if(res < (5*this.tolerance  ))  {  this.score = 3; }
                else if(res < (10*this.tolerance ))  {  this.score = 1; }
        
        
        
        return this.score;
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

    public Partie getCurrentPartie() {
        return currentPartie;
    }

    public void setCurrentPartie(Partie currentPartie) {
        this.currentPartie = currentPartie;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEtape() {
        return etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }
    
    
    
}