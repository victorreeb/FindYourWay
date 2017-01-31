/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author remaki
 */

@Entity //elle va persister dans la bdd
@XmlRootElement
// toutes les catégories qui appartiennent à la classe categorie
@NamedQueries({
    @NamedQuery(name = "Sandwich.findAll", query = "SELECT s FROM Sandwich s")
})
public class Sandwich implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
     private String id;    
    private String taille;
    private String type;
    private double prix;
    @ManyToOne // va se référer à une catégorie qui correspond à mapBy
    @JsonBackReference //cassser le cycle   
    private Commande commande;

     @OneToMany(cascade = CascadeType.ALL, mappedBy = "sandwich",fetch=FetchType.EAGER)  // une instance de la classe message correspond plusieurs instance de commentaires 
    //@JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    private List<Ingredients> ingredients; //collection
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    
    public List<Link> getLinks() {
        
        return links;
    }
    
    
    public void addLink(String uri, String rel) {
        
        this.links.add(new Link(rel, uri));
    }
    public Sandwich() { //tjrs un constructeur vide pour JPA
        this.ingredients = new ArrayList<>();
    }

    public Sandwich(String taille, String type) {
         
        this.taille = taille;
        this.type = type;
        this.ingredients = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    
    
    public double calculatePrix() {
        this.prix = 0;
        switch(this.taille) {
            
              case "petite faim":    this.prix= 4;break;
              case "moyenne faim":   this.prix= 5;break;
              case "grosse faim":    this.prix= 7;break;
              case "ogre":           this.prix= 10;break;
        }
        
        return this.prix;
    }
    
}
