/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.lpro.entity.Commande;
import org.lpro.entity.Paiement;

/**
 *
 * @author remaki
 */

@Stateless
public class PaiementResource {
    
    @PersistenceContext
    EntityManager em;
    
    
    public Paiement findById(String id) {
        return this.em.find(Paiement.class, id);
    }
    
    
     public List<Paiement> findAll(String commandeId) {
        Query query = em.createQuery("SELECT p FROM Paiement p where p.commande.id= :id ");
        query.setParameter("id", commandeId);
        List<Paiement> liste = query.getResultList();
        return liste;
    }
    
    
     public Paiement ajoutePaiement(String commandeId, Paiement paiement) {
        Paiement p = new Paiement(paiement.getMail(),paiement.getName(),
                paiement.getDateExperation(),paiement.getNumber_card(),paiement.getCvv2(),paiement.getType(),paiement.getMontant());
        p.setId(UUID.randomUUID().toString());
        p.setCommande(this.em.find(Commande.class, commandeId));
        this.em.persist(p);
        return p;
    }

    public void removePaiement(String paiementId) {
        try {
            Paiement ref = this.em.getReference(Paiement.class, paiementId);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    }
    
    
    
    
    
    
    
    
    
    
}
