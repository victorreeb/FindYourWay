package org.lpro.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ZAKARIA
 */

@Entity  //pour dire qu'on ça va persister dans une table dans une base de données
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Utilisateur implements Serializable {
    
    
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
     
    // Mot de passe
    private String password;

    // FullName
    private String fullName;
    
    // Email
    @Column(unique = true)
    private String email;
    
    // 0 pour les utilisateurs et 1 pour les admins
    private int user_type = 0 ;

   
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
    public Utilisateur() {
    }
    
    public Utilisateur(String email , String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        //this.password = password;
        
    }

    public int getUserType()
    {
        return user_type;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    //Getters et setters 
    public String getfullName()
    {
        return fullName;
    }
    
    public void setfullName(String fullName)
    {
        this.fullName = fullName;
    }
   
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }

}

