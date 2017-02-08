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
import org.lpro.entity.Point;

/**
 *
 * @author remaki
 */
@Stateless
public class DestinationResource {
    
     @PersistenceContext
      EntityManager em;
    
    
    
     public Destination findById(String id) {
        return this.em.find(Destination.class, id);
    }

    public List<Destination> findAll(String partieId) {
        Query query = em.createQuery("SELECT d FROM Destination d where d.partie.id= :id ");
        query.setParameter("id", partieId);
        List<Destination> liste = query.getResultList();
        return liste;
    }
    
    
     public List<Destination> findAdminAll(){
        
        Query q = this.em.createNamedQuery("Destination.findAdminAll", Destination.class);
        // pour éviter les pbs de cache
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
     
    public Destination ajouteDestination( Destination destination) {
       
        Destination des = new Destination(destination.getLat(),destination.getLng(),destination.getLieu(),destination.getDescription(),destination.getIndices());
        des.setId(UUID.randomUUID().toString());      
        
        this.em.merge(des);
        return des;
    }

    public void removeDestination(String destinationId) {
        try {
            Destination ref = this.em.getReference(Destination.class, destinationId);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }            
        
        
    }

     public Destination save (Destination destination) {
        
      return this.em.merge(destination);
    } 
    
    
    
    
    
    
    
    
    
}
