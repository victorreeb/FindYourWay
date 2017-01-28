/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author remaki
 */

@Entity  //pour dire qu'on ça va persister dans une table dans une base de données
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Commande.findAll",query="SELECT i from Commande i") //ma classe accepte une requete de ce type,on peut créer d'autres
public class Commande {
        private static final long serialVersionUID = 1L;

    @Id
     //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
   
    private int nbre;
    
    private String date;
    
    private String etat; //la commande peut être: créée, payée, en cours, prête, livrée
    
    
    
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")  // une instance de la classe categorie correspond plusieurs instance de ingredients 
    @JsonManagedReference  //le point d'entrée  (pour eviter le cycle)
    private List<Sandwich> sandwichs; //collection
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    public List<Link> getLinks() {
        
        return links;
    }
    
    
    public void addLink(String uri, String rel) {
        
        this.links.add(new Link(rel, uri));
    }

    //constructeur vide pour JPA 
    public Commande() {
        
        //JPA
    }
    
    

    public Commande(int nbre, String date) {
        this.nbre = nbre;
        this.date = date;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    //Getters et setters 

    public int getNbre() {
        return nbre;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<Sandwich> getSandwichs() {
        return sandwichs;
    }

    public void setSandwichs(List<Sandwich> sandwichs) {
        this.sandwichs = sandwichs;
    }
  
    
}
