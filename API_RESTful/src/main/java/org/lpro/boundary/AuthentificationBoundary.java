package org.lpro.boundary;

import control.KeyManagement;
import control.PasswordManagement;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import org.lpro.entity.Accreditation;
import org.mindrot.jbcrypt.BCrypt;

@Path("/authentification")
public class AuthentificationBoundary {

    @Inject
    private KeyManagement keyManagement;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response authentifieCommande(Accreditation accrediation) {
        try {
            //String commandeId = accrediation.getCommandeId();
            //String motDePasse = accrediation.getPassword();
            // On authentifie l'utilisateur en utilisant les crédentails fournis
            //authentifie(commandeId);
            // On fournit un token
            String token = "";
            return Response.ok().build();

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
