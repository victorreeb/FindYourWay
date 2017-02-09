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

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author remaki
 */


@Entity  //pour dire qu'on ça va persister dans une table dans une base de données
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name="Destination.findAdminAll",query="SELECT d from Destination d"),

})



public class Destination implements Serializable {

  private static final long serialVersionUID = 1L;


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")


    private String id;
    private String lat;
    private String lng;
    private String description;
    private String lieu;

    private ArrayList<String> indices = new ArrayList<>();   

    @XmlElement(name="_links")
    @Transient
    private List<Link>  links = new ArrayList<>();


    public Destination(){

    }



    public Destination(String lat,String lng, String description,String lieu,ArrayList<String> indices){


        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.lieu = lieu;
        this.indices = indices;





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


    public ArrayList<String> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<String> indices) {
        this.indices = indices;
    }



}
