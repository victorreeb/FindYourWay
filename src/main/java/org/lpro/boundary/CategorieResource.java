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

/**
 *
 * @author remaki
 */
@Path("categories")
@Stateless
public class CategorieResource {
    
    @PersistenceContext
    EntityManager em;

    public Categorie findById(String id) {
        return this.em.find(Categorie.class, id);
    }

    public List<Categorie> findAll() {
        Query q = this.em.createNamedQuery("Categorie.findAll", Categorie.class);
        // pour éviter les pbs de cache
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Categorie save(Categorie cat) {
        cat.setId(UUID.randomUUID().toString());
        cat.setIngredients(new ArrayList<>());
        return this.em.merge(cat);
    }

    public void delete(String id) {
        try {
            Categorie ref = this.em.getReference(Categorie.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    } 
    
    
    
    
    
    
    
}
