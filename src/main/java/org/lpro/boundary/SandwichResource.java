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
import org.lpro.entity.Categorie;
import org.lpro.entity.Commande;
import org.lpro.entity.Ingredients;
import org.lpro.entity.Sandwich;

/**
 *
 * @author remaki
 */
@Stateless
public class SandwichResource {
    
    
    
    
    @PersistenceContext
    EntityManager em;

     

   

    public Sandwich findById(String id) {
        return this.em.find(Sandwich.class, id);
    }

    public List<Sandwich> findAll(String commandeId) {
        Query query = em.createQuery("SELECT s FROM Sandwich s where s.commande.id= :id ");
        query.setParameter("id", commandeId);
        List<Sandwich> liste = query.getResultList();
        return liste;
    }

    public Sandwich ajouteSandwich(String commandeId, Sandwich sandwich) {
        Sandwich san = new Sandwich(sandwich.getTaille(), sandwich.getType());
        san.setId(UUID.randomUUID().toString());
        san.setCommande(this.em.find(Commande.class, commandeId));
        this.em.persist(san);
        return san;
    }

    public void removeSandwich(String sandwichId) {
        try {
            Sandwich ref = this.em.getReference(Sandwich.class, sandwichId);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    }
    
    
    

    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    

