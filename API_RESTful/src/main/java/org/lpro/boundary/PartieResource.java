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
import org.lpro.entity.Destination;
import org.lpro.entity.Partie;

/**
 *
 * @author remaki
 */
@Stateless // pour l'injection de dépendance, gestion transactionnelle
public class PartieResource {
    
    @PersistenceContext
    EntityManager em;
    
    
    
    public Partie findById(String id) {
        return this.em.find(Partie.class, id);
    }

    public List<Partie> findAll() {
        Query q = this.em.createNamedQuery("Partie.findAll", Partie.class);
        // pour éviter les pbs de cache
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Partie save(Partie partie) {
        partie.setId(UUID.randomUUID().toString());
        partie.setPoints(new ArrayList<>());
        partie.setDestination(new Destination());
        return this.em.merge(partie);
    }

    public void delete(String id) {
        try {
            Partie ref = this.em.getReference(Partie.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
