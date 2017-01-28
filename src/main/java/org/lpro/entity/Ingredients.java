/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author remaki
 */
@Entity
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
     @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String nom;
    @ManyToOne // va se référer à une catégorie qui correspond à mapBy
    @JsonBackReference //cassser le cycle   
    private Categorie categorie;
    @ManyToOne // va se référer à une catégorie qui correspond à mapBy
    //@JsonBackReference //cassser le cycle   
    private Sandwich sandwich;
    
     @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    public List<Link> getLinks() {
        
        return links;
    }
    
    
    public void addLink(String uri, String rel) {
        
        this.links.add(new Link(rel, uri));
    }

    public Ingredients() {
    }

    public Ingredients(String nom) {
        this.nom = nom;
        
    }

    public Ingredients(String nom, Categorie categorie) {
        this.nom = nom;
        this.categorie = categorie;
      
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    
   
    
}
