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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p where p.id = id"),
    @NamedQuery(name="Point.findAdminAll",query="SELECT p from Point p")
})
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
   
    private String lat;
    private String lng;
    private String appellation;
     @ManyToMany // va se référer à u message  qui correspond à mapBy
    @JsonBackReference //cassser le cycle   
    private List<Partie> parties;
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
     public Point() {
    }

    public Point(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Point(String lat, String lng,String appellation) {
          this.lat = lat;
          this.lng = lng;
          this.appellation = appellation;

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

    public String getAppellation() {
        return appellation;
    }

	
	 
	

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    
    
    

    
    
    
    



}
