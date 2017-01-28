/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;
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
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
})
public class Categorie implements Serializable {
    
    
     private static final long serialVersionUID = 1L;

     @Id
     //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String libelle;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie",fetch=FetchType.EAGER )  // une instance de la classe categorie correspond plusieurs instance de ingredients 
    @JsonManagedReference  //le point d'entrée  (pour eviter le cycle)
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
   
     public Categorie() { //tjrs un constructeur vide pour JPA
        this.ingredients = new ArrayList<>();
    }

    public Categorie(String id, String libelle) {
        this.id = id; 
        this.libelle = libelle;
       
        this.ingredients = new ArrayList<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
    
    
    
    
    
    
    
    
    
    
    
}
