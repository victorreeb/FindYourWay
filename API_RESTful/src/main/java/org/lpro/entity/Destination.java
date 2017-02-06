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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author remaki
 */
@Entity
public class Destination implements Serializable {

  private static final long serialVersionUID = 1L;

    @Id //clé
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String lat;
    private String lng;
    private String description;
    private String lieu;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")  // une instance de la classe message correspond plusieurs instance de commentaires 
    @JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    private List<Indice> indices; //collection
    
    
   @OneToOne(fetch=FetchType.LAZY, mappedBy="destination")
   private Partie partie;
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    
    public Destination(){
        
    }
    
    
    public Destination(String lat,String lng, String description,String lieu){
        
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.lieu = lieu;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    public List<Link> getLinks() {
        
        return links;
    }
    
    
    public void addLink(String uri, String rel) {
        
        this.links.add(new Link(rel, uri));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<Indice> getIndices() {
        return indices;
    }

    public void setIndices(List<Indice> indices) {
        this.indices = indices;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    
}
