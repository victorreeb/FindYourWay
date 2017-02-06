/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
=======
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
>>>>>>> ajout d'autres resources
>>>>>>> ajout d'autres resources

import javax.persistence.Id;
<<<<<<< HEAD

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
=======
<<<<<<< HEAD
<<<<<<< HEAD

public class Destination implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String id_partie;
	private int latitude;
	private int longitude;
	private String description;
	private String lieu;
	
	public Destination(){}
	
	public Destination(int lat, int longi, String desc, String lieu){
		this.latitude = lat;
		this.longitude = longi;
		this.description = desc;
		this.lieu = lieu;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id_partie
	 */
	public String getId_partie() {
		return id_partie;
	}

	/**
	 * @param id_partie the id_partie to set
	 */
	public void setId_partie(String id_partie) {
		this.id_partie = id_partie;
	}

	/**
	 * @return the latitude
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}

	/**
	 * @param lieu the lieu to set
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
=======
=======
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
>>>>>>> ajout de destination/Admin
import javax.persistence.OneToMany;
>>>>>>> ajout de destination/Admin
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
<<<<<<< HEAD
<<<<<<< HEAD

@Entity  //pour dire qu'on ça va persister dans une table dans une base de données
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name="Destination.findAdminAll",query="SELECT d from Destination d"),
    
})

=======
=======

>>>>>>> ajout d'autres resources
@Entity  //pour dire qu'on ça va persister dans une table dans une base de données
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
<<<<<<< HEAD
>>>>>>> ajout création partie: méthode POST
=======
@NamedQueries({
    @NamedQuery(name="Destination.findAdminAll",query="SELECT d from Destination d"),
    
})
<<<<<<< HEAD
>>>>>>> ajout de destination/Admin
=======

>>>>>>> ajout d'autres resources
public class Destination implements Serializable {

  private static final long serialVersionUID = 1L;

<<<<<<< HEAD
<<<<<<< HEAD

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")

=======
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
>>>>>>> ajout création partie: méthode POST
=======

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")

>>>>>>> ajout d'autres resources
    private String id;
    private String lat;
    private String lng;
    private String description;
    private String lieu;

    private ArrayList<String> indices = new ArrayList<>();
    //@OneToMany(mappedBy = "destination",fetch=FetchType.EAGER)  // une instance de la classe message correspond plusieurs instance de commentaires 
    //@JsonManagedReference //le point d'entrée  (pour eviter le cycle)
     //collection
    
    
   //@OneToOne(fetch=FetchType.LAZY, mappedBy="destination")

    
<<<<<<< HEAD
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")  // une instance de la classe message correspond plusieurs instance de commentaires 
    //@JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    
<<<<<<< HEAD
=======
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")  // une instance de la classe message correspond plusieurs instance de commentaires 
    @JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    private ArrayList<String> indices; //collection
>>>>>>> ajout d'autres resources
    
    
   @OneToOne(fetch=FetchType.LAZY, mappedBy="destination")
   private Partie partie;

<<<<<<< HEAD
=======
    @OneToMany( cascade = CascadeType.ALL,mappedBy = "destination",fetch=FetchType.EAGER)  // une instance de la classe message correspond plusieurs instance de commentaires 
    @JsonManagedReference //le point d'entrée  (pour eviter le cycle)
    private List<Indice> indices; //collection
    
    
   //@OneToOne(fetch=FetchType.LAZY, mappedBy="destination")
  // private Partie partie;
>>>>>>> ajout création partie: méthode POST
=======
>>>>>>> ajout d'autres resources
    @XmlElement(name="_links")
    @Transient 
    private List<Link>  links = new ArrayList<>();
    
    
    public Destination(){
        
    }
    
    
<<<<<<< HEAD
<<<<<<< HEAD

    public Destination(String lat,String lng, String description,String lieu,ArrayList<String> indices){

=======
    public Destination(String lat,String lng, String description,String lieu,List<Indice> indices){
>>>>>>> ajout création partie: méthode POST
=======

    public Destination(String lat,String lng, String description,String lieu,ArrayList<String> indices){

>>>>>>> ajout d'autres resources
        
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.lieu = lieu;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> ajout d'autres resources

        this.indices = indices;

        

<<<<<<< HEAD
=======
        this.indices = indices;
>>>>>>> ajout création partie: méthode POST
=======
>>>>>>> ajout d'autres resources
        
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

    
<<<<<<< HEAD
    
    
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> ajout d'autres resources
=======
>>>>>>> ajout d'autres resources


    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
<<<<<<< HEAD
=======
>>>>>>> ajout création partie: méthode POST
    



=======
    


>>>>>>> ajout d'autres resources
>>>>>>> ajout d'autres resources
}
