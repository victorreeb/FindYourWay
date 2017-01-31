/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;
import org.lpro.entity.Categorie;

import org.lpro.entity.Commande;
import org.lpro.entity.Ingredients;
import org.lpro.entity.Sandwich;

/**
 *
 * @author remaki
 */
@Path("commandes")
@Stateless
public class CommandeResource {
    
    
     @PersistenceContext
    EntityManager em;

    public Commande findById(String id) {
        return this.em.find(Commande.class, id);
    }

    public List<Commande> findAll() {
        Query q = this.em.createNamedQuery("Commande.findAll", Commande.class);
        // pour éviter les pbs de cache
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
    
    
    public Commande save(Commande com) {
        com.setId(UUID.randomUUID().toString());
        com.setSandwichs(new ArrayList<>());
        return this.em.merge(com);
    }
    
    public Commande updateCommande (String id, Commande commande, String sandwichId,Sandwich sandwich) {
        Commande ref;
        try {
             ref = this.em.getReference(Commande.class, id);
        }
        catch(EntityNotFoundException e) {
            
            
        }
        ref = this.em.getReference(Commande.class, id);
        ref.setSandwich(sandwichId, sandwich);
        
        return this.em.merge(ref);
        
    }

    public void delete(String id) {
        try {
            Commande ref = this.em.getReference(Commande.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
