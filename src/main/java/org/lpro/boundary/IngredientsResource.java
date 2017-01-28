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
import org.lpro.entity.Categorie;
import org.lpro.entity.Ingredients;

/**
 *
 * @author remaki
 */
@Stateless
public class IngredientsResource {
    
  @PersistenceContext
    EntityManager em;

    public Ingredients findById(String id) {
        return this.em.find(Ingredients.class, id);
    }

    public List<Ingredients> findAll(String categorieId) {
        Query query = em.createQuery("SELECT c FROM Ingredients c where c.categorie.id= :id ");
        query.setParameter("id", categorieId);
        List<Ingredients> liste = query.getResultList();
        return liste;
    }

    public Ingredients ajouteIngredient(String categorieId, Ingredients ingredient) {
        Ingredients in = new Ingredients(ingredient.getNom());
        in.setId(UUID.randomUUID().toString());
        in.setCategorie(this.em.find(Categorie.class, categorieId));
        this.em.persist(in);
        return in;
    }

    public void removeIngredient(String ingredientId) {
        try {
            Ingredients ref = this.em.getReference(Ingredients.class, ingredientId);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
