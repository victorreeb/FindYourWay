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
import javax.persistence.CascadeType;



import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;


import javax.persistence.ManyToOne;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author remaki
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p where p.id =:id"),
    @NamedQuery(name="Point.findAdminAll",query="SELECT p from Point p")
})
public class Point implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
   
    private double lat;
    private double lng;
    private String appellation;
    @ManyToOne // va se référer à u message  qui correspond à mapBy
    @JsonBackReference //cassser le cycle   
    private Partie partie;
    
       
    
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
     public Point() {
    }

    public Point(double lat, double lng,String appellation) {
        this.lat = lat;
        this.lng = lng;
        this.appellation = appellation;
    }

    public Point(double lat, double lng,String appellation,Partie partie) {
          this.lat = lat;
          this.lng = lng;
          this.appellation = appellation;
          this.partie = partie;
          
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getAppellation() {
        return appellation;
    }

	
	 
	


    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

   


}
