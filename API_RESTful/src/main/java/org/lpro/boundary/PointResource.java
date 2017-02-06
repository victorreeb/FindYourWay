/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;

import javax.persistence.CacheStoreMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.lpro.entity.Partie;
import org.lpro.entity.Point;

/**
 *
 * @author remaki
 */
@Stateless
public class PointResource {
  
    @PersistenceContext
    EntityManager em;
    
    
    
     public Point findById(String id) {
        return this.em.find(Point.class, id);
    }

    public List<Point> findAll(String partieId) {
        Query query = em.createQuery("SELECT p FROM Point p where p.partie.id= :id ");
        query.setParameter("id", partieId);
        List<Point> liste = query.getResultList();
        return liste;
    }

    
    public List<Point> findAdminAll(){
        
        Query q = this.em.createNamedQuery("Point.findAdminAll", Point.class);
        // pour éviter les pbs de cache
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Point ajoutePoint(Point point) {
        Point p = new Point(point.getLat(),point.getLng(),point.getAppellation());
        p.setId(UUID.randomUUID().toString());        
        return this.em.merge(p);
    }
    public void removePoint(String pointId) {
        try {
            Point ref = this.em.getReference(Point.class, pointId);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }

    
        
        
    }

    
     public Point save (Point point) {
        
      return this.em.merge(point);
    } 

    }

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
