/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;
import control.KeyManagement;
import control.PasswordManagement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.net.URI;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Categorie;
import org.lpro.entity.Commande;
import org.lpro.entity.Ingredients;
import org.lpro.entity.Sandwich;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.annotation.Priority;
import javax.inject.Inject;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.PUT;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Provider;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.lpro.entity.Accreditation;
import org.lpro.entity.Paiement;
import provider.AuthentificationFiltre;
import provider.Secured;

/**
 *
 * @author remaki
 */

@Stateless //EJB: transactionnel pris en charge, le fait qu'il y ait plusieurs requetes, que les ressources soient vérouillées
@Path("/commandes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommandeRepresentation {
    
    //attribut pour stocker le token
    
    String token;
    @Inject
    private KeyManagement keyManagement;
     @Inject
    private AuthentificationFiltre filtre;
    @Context
    private UriInfo uriInfo;
    
     @EJB // @Inject pareil, pour l'injection de dépendance
    CommandeResource comResource;
    @EJB
    SandwichResource SandwichResource;
    
    @EJB
    PaiementResource PaiementResource;
    
     @GET
    public Response getCommandes(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Commande> liste = this.comResource.findAll();
        
        //pour chaque message
        for(Commande com : liste) {
            
            List<Sandwich> lsan = this.SandwichResource.findAll(com.getId());
             List<Paiement> lpa = this.PaiementResource.findAll(com.getId());
            com.getLinks().clear();
            com.addLink(this.getUriForSelfCommande(uriInfo, com),"self");
            com.addLink(this.getUriForSandwich(uriInfo, com),"sandwichs");
            com.addLink(this.getUriForPaiement(uriInfo, com),"paiements");
            for(Sandwich san : lsan ) {
                san.calculatePrix();
                san.getLinks().clear();
                san.addLink(this.getUriForSelfSandwich(uriInfo,san,com),"self");
            }
            for(Paiement pa : lpa ) {
                
                pa.getLinks().clear();
                pa.addLink(this.getUriForSelfPaiement(uriInfo,pa,com),"self");
            }
            com.setPaiements(lpa);
            com.setSandwichs(lsan);
        com.calculateMontant();
        };
        GenericEntity<List<Commande>> list = new GenericEntity<List<Commande>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    
     @GET
     @Path("privee/commandes")
    public Response getSortedCommandes(@Context UriInfo uriInfo) {
        
        //liste des messages
        List<Commande> liste = this.comResource.findAll();
        
        //pour chaque message
        for(Commande com : liste) {
            
            List<Sandwich> lsan = this.SandwichResource.findAll(com.getId());
            com.getLinks().clear();
            com.addLink(this.getUriForSelfCommande(uriInfo, com),"self");
            com.addLink(this.getUriForSandwich(uriInfo, com),"sandwichs");
            for(Sandwich san : lsan ) {
                san.calculatePrix();
                san.getLinks().clear();
                san.addLink(this.getUriForSelfSandwich(uriInfo,san,com),"self");
            }
        com.setSandwichs(lsan);
        com.calculateMontant();
        };
        GenericEntity<List<Commande>> list = new GenericEntity<List<Commande>>(liste) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
   @GET
    @Path("/{commandeId}")
    public Response getCommande(@PathParam("commandeId") String commandeId, @Context UriInfo uriInfo) {
        Commande commande = this.comResource.findById(commandeId);
        if (commande != null) {
            
            commande.getLinks().clear();
            commande.addLink(this.getUriForSelfCommande(uriInfo, commande),"self");
            List<Sandwich> lsan = this.SandwichResource.findAll(commandeId);
            
            for(Sandwich san : lsan) {
                san.calculatePrix();
                san.getLinks().clear();
                
                san.addLink(this.getUriForSelfSandwich(uriInfo, san, commande), "self");
                
            }
             commande.calculateMontant();
            commande.setSandwichs(lsan);
            return Response.ok(commande).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
   
    
    
     @GET
    @Path("/{commandeId}/etat")
    public Response getEtatCommande(@PathParam("commandeId") String commandeId, @Context UriInfo uriInfo) {
        Commande commande = this.comResource.findById(commandeId);
        if (commande != null) {
            
            commande.getLinks().clear();
            commande.addLink(this.getUriForSelfCommande(uriInfo, commande),"self");
            List<Sandwich> lsan = this.SandwichResource.findAll(commandeId);
            
            for(Sandwich san : lsan) {
                
                san.calculatePrix();
                
                san.getLinks().clear();
               
                san.addLink(this.getUriForSelfSandwich(uriInfo, san, commande), "self");
                
            }
             commande.calculateMontant();
             commande.setSandwichs(lsan);
            
           
            return Response.ok(commande).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    
    
   @GET
    @Path("{commandeId}/sandwichs")
    public Response getAllSandwichs(@PathParam("commandeId") String commandeId, @Context UriInfo uriInfo) {
        List<Sandwich> lsan = this.SandwichResource.findAll(commandeId);
        Commande commande = this.comResource.findById(commandeId);
        for(Sandwich san : lsan) {
                san.calculatePrix();
                san.getLinks().clear();
                
                san.addLink(this.getUriForSandwich(uriInfo, commande),commandeId);
                
            }
        commande.calculateMontant();
        GenericEntity<List<Sandwich>> list = new GenericEntity<List<Sandwich>>(lsan) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
  
    
     @GET
    @Path("{commandeId}/sandwichs/{sandwichId}")
    public Response getOneSandwich(@PathParam("sandwichId") String commandeId,
            @Context UriInfo uriInfo,
            @PathParam("sandwichId") String sandwichId) {
        Sandwich san = this.SandwichResource.findById(sandwichId);
        return Response.ok(san, MediaType.APPLICATION_JSON).build();
    }
     
    @PUT
    @Path("{commandeId}/sandwichs/{sandwichId}")
    public Sandwich updateOneSandwich(@PathParam("commandeId") String commandeId,
            @Context UriInfo uriInfo,
            @PathParam("sandwichId") String sandwichId,Sandwich sandwich) {
        
        Commande commande1 = this.comResource.findById(commandeId);
        
       Sandwich san= commande1.getSandwich(sandwichId);
       if( (!commande1.getEtat().equals("paid"))&& (!commande1.getEtat().equals("delivered"))&& (!commande1.getEtat().equals("pending"))&& (!commande1.getEtat().equals("ready"))){
           
           
          san= this.comResource.updateCommande(commandeId, commande1, sandwichId, sandwich);       
       
       }    
       
       
        return  san;
    }
    
    
     @POST
    public Response addCommande(Commande commande, @Context UriInfo uriInfo) {
              
         commande.setPaiements(new ArrayList<Paiement>());
        Commande newCommande = this.comResource.save(commande);
        newCommande.calculateMontant();
        this.token = issueToken(newCommande.getId());
        System.out.println("token =="+this.token);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCommande.getId())                
                .build();
        return Response.created(uri)
                .entity(newCommande).
                header(AUTHORIZATION,"Bearer "+this.token)
                .build();
    }
 
    
    @PUT
    @Path("/{commandeId}")
    public Response updateCommande (@PathParam("commandeId") String commandeId,String newDate, @Context UriInfo uriInfo) {
        
        Commande commande = this.comResource.findById(commandeId);
        if (commande != null) {
            
            commande.getLinks().clear();
            commande.addLink(this.getUriForSelfCommande(uriInfo, commande),"self");
            List<Sandwich> lsan = this.SandwichResource.findAll(commandeId);
            
            for(Sandwich san : lsan) {
                san.calculatePrix();
                san.getLinks().clear();
                
                san.addLink(this.getUriForSelfSandwich(uriInfo, san, commande), "self");
                
            }
             commande.calculateMontant();
             commande.setDate(newDate);
             commande.setSandwichs(lsan);
            return Response.ok(commande).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        
    }
    
     @POST
      @Path("/{commandeId}/payCommande")
    public Response payCommande(@PathParam("commandeId") String commandeId, @Context UriInfo uriInfo,Paiement paiement) {
        Commande commande = this.comResource.findById(commandeId);
                   
           
             double montant =  commande.calculateMontant();
               if(montant == paiement.getMontant() && commande != null) {
                   
                  //Paiement newPaiement = this.PaiementResource.save(paiement); 
                   Paiement p = this.PaiementResource
                .ajoutePaiement(commandeId, new Paiement(paiement.getMail(),paiement.getName(),
                paiement.getDateExperation(),paiement.getNumber_card(),paiement.getCvv2(),paiement.getType(),paiement.getMontant()));
        URI uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commandeId)
                .path(PaiementRepresentation.class)
                .path(p.getId())
                .build();
                commande.setEtat("paid");
                 return Response.created(uri).entity(p).build();
                   
               }     
            
        
          else {
                   
                   
             return Response.status(Response.Status.NOT_FOUND).build();
        }
            
        
    }
    
    
    
     @GET
    @Path("/{commandeId}/content")
    public Response getContentCommande(@PathParam("commandeId") String commandeId, @Context UriInfo uriInfo) {
        Commande commande = this.comResource.findById(commandeId);
        if (commande != null) {
            
            commande.getLinks().clear();
            commande.addLink(this.getUriForSelfCommande(uriInfo, commande),"self");
            List<Sandwich> lsan = this.SandwichResource.findAll(commandeId);
            
            for(Sandwich san : lsan) {
                
                san.calculatePrix();
                
                san.getLinks().clear();
               
                san.addLink(this.getUriForSelfSandwich(uriInfo, san, commande), "self");
                
            }
              
             commande.calculateMontant();
             commande.setSandwichs(lsan);
            
           
            return Response.ok(commande).build();
        } else {
            
            //pas besoin de lien 
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
 
    
    
    
    public Response authentifieCommande(Accreditation accrediation) {
        try {
            String commandeId = accrediation.commande.getId();
            //String motDePasse = accrediation.getPassword();
            // On authentifie l'utilisateur en utilisant les crédentails fournis
            authentifie(commandeId);
            // On fournit un token
            String token = issueToken(commandeId);
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authentifie(String commandeId) throws Exception {
        // System.out.println("hash : "+PasswordManagement.digestPassword(motDePasse));
        // On authentifie l'utilisateur en utilisant la BD, LDAP,...
        // On lance une exception si les crédentials sont invalides
       // String motDePasseBD ="$2a$10$dmenVT8Ng0/n3.TN4hKHpOeyg9oMsp8rtlkZ11CB1P3XqKnt79dsm";
        if (commandeId.equals("remaki")) { 
        } else {
            throw new NotAuthorizedException("Problème d'authentification");
        }
    }
    
    @Secured
    @POST
    @Path("/{commandeId}/sandwichs")
    public Response addSandwich(@PathParam("commandeId") String commandeId,
            Sandwich sandwich,
            @Context UriInfo uriInfo,ContainerRequestContext requestContext)throws Exception {
        Commande commande = this.comResource.findById(commandeId);
        String token = this.token;
        System.out.println("token récupéré   :"+token);
       // String authHeader
               // = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
       //  if (authHeader == null || !authHeader.startsWith("Bearer ")) {
           // throw new NotAuthorizedException("Probleme header autorisation");
      //  }
        // On extrait le token, et on vérifie qu'il est valide
        //String token2 = authHeader.substring("Bearer".length()).trim();
        if( (!commande.getEtat().equals("paid"))&& (!commande.getEtat().equals("delivered"))&& (!commande.getEtat().equals("pending"))&& (!commande.getEtat().equals("ready"))){
           
             Sandwich san = this.SandwichResource.ajouteSandwich(commandeId, sandwich);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/")
                .path(san.getId())
                .build();
        return Response.created(uri).entity(san).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
       
        
    }
    
   @Secured 
   @DELETE
    @Path("/{commandeId}")
    public void deleteCommande(@PathParam("commandeId") String id) {
         Commande commande = this.comResource.findById(id);
        if(!commande.getEtat().equals("paid")) {
            this.comResource.delete(id);
        }
        
    }
  
    
    
    
    
    
      //pour une categorie particuliere
    private String getUriForSelfCommande(UriInfo uriInfo, Commande commande) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commande.getId())
                .build()
                .toString();
        return uri;
                
    }
    
    // pour la collection de categories
    private String getUriForCommande(UriInfo uriInfo) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
    }
    
    
    private String getUriForSelfPaiement(UriInfo uriInfo, Paiement pa, Commande commande) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commande.getId())
                .path(PaiementRepresentation.class)
                .path(pa.getId())
                .build()
                .toString();
        
        return uri;
        
    }
    // pour un ingredient d'une categorie 
    private String getUriForSelfSandwich(UriInfo uriInfo, Sandwich san, Commande commande) {
        
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commande.getId())
                .path(SandwichRepresentation.class)
                .path(san.getId())
                .build()
                .toString();
        
        return uri;
        
    }
    
    
    //pour la collection d'ingredients d'une categorie
    
    private String getUriForSandwich(UriInfo uriInfo, Commande commande) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commande.getId())
                .path(SandwichRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
        
        
    }
    
     private String getUriForPaiement(UriInfo uriInfo, Commande commande) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(CommandeRepresentation.class)
                .path(commande.getId())
                .path(PaiementRepresentation.class)
                .build()
                .toString();
        
        return uri;
        
        
        
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
