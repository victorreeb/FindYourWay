/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Commande implements Serializable{
        private static final long serialVersionUID = 1L;

    @Id
     //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
   
    private int nbre;
    //Date date = new Date();

    //DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
    
    private String date;
    
    private String etat; //la commande peut être: créée (created), payée (paid), en cours (pending), prête (ready), livrée (delivered)
    
    private double montant;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")  // une instance de la classe message correspond plusieurs instance de commentaires 
    @JsonManagedReference //le point d'entrée  (pour eviter le cycle)   
    private List<Paiement> paiements; 
    
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
    
    public Sandwich getSandwich(String sandwichId){
        List <Sandwich> lsan = this.getSandwichs();
        Sandwich res=null;
            for(Sandwich in : lsan) {

               if(in.getId().equals(sandwichId)) {

                  res = in;
                }

             

           }
            
        return res;
    }
    
    public void setSandwich(String sandwichId, Sandwich sandwich){
        List <Sandwich> lsan = this.getSandwichs();
       
            for(Sandwich in : lsan) {

                    if(in.getId().equals(sandwichId)) {

                       in.setTaille(sandwich.getTaille());
                       in.setType(sandwich.getType());
                    }
                    this.setSandwichs(lsan);                
            
           }          
            
        
    }
    
    public double calculateMontant() {
        this.montant = 0;
        List<Sandwich> lsan= this.getSandwichs();
        this.nbre = lsan.size();
        for(Sandwich in : lsan) {
                
          this.montant+= in.calculatePrix();
                
            
                
            }
        
        
        return this.montant;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
  
    
    
    
    
    
    
}
