package org.lpro.boundary;

import control.PasswordManagement;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.lpro.entity.Utilisateur;


@Stateless
public class UtilisateurRessource {
    
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * Trouver un utilisateur par son ID
     * @param id
     * @return l'utilisateur
     */
    public Utilisateur findById(String id) {
        return this.em.find(Utilisateur.class, id);
    }
    
    /**
     * Enregistre un utilisateur
     * @param user l'utilisateur à enregistrer
     * @return l'utilisateur 
     */
    public Utilisateur save(Utilisateur user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(PasswordManagement.digestPassword(user.getPassword()));
        return this.em.merge(user);
    }

    /**
     * Retrouver un utilisateur par son adresse email
     * @param mail email de l'utilisateur
     * @return l'utilisateur ou génére une exception
     */
     public Utilisateur findByEmail(String mail) {
        Query query;
        query = em.createQuery("SELECT c FROM Utilisateur c WHERE c.email LIKE :monmail");
        query.setParameter("monmail", mail); 
        Utilisateur user = (Utilisateur) query.getSingleResult();
        return user;
    }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

