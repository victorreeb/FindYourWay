package org.lpro.entity;

import java.io.Serializable;

import javax.persistence.Id;

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
	
}
