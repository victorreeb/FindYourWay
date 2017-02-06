package org.lpro.entity;

import java.io.Serializable;

import javax.persistence.Id;

public class Point implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private int latitude;
	private int longitude;
	private String appellation;
	
	public Point(){}
	
	public Point(int lat, int longi, String appellation){
		this.latitude = lat;
		this.longitude = longi;
		this.appellation = appellation;
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
	 * @return the appellation
	 */
	public String getAppellation() {
		return appellation;
	}

	/**
	 * @param appellation the appellation to set
	 */
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	
}
