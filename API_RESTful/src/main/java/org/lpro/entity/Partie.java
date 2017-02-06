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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author remaki
 */
@Entity //elle va persister dans la bdd
@XmlRootElement
// tous les messages qui appartiennent à la classe message
@NamedQueries({
    @NamedQuery(name = "Partie.findAll", query = "SELECT p FROM Partie p")
})
public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //clé
    private String id;
    
    private String nom;
    private String description;
    
    
    
    @ManyToOne // va se référer à u message  qui correspond à mapBy
    @JsonBackReference //cassser le cycle   
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partie")  // une instance de la classe message correspond plusieurs instance de commentaires 
    @JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    private List<Point> points; //collection
    
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="id")
   private Destination destination;
    
     @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    public List<Link> getLinks() {
        
        return links;
    }
    
    
    public void addLink(String uri, String rel) {
        
        this.links.add(new Link(rel, uri));
    }
    
    
     public Partie() { //tjrs un constructeur vide pour JPA
        this.points = new ArrayList<>();
    }

    public Partie(String id, String nom, String description,Destination destination) {
        this.id = id; 
        this.nom = nom;
        this.description = description;
        this.points = new ArrayList<>();
        this.destination = destination;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    
    
    
    
    
}
