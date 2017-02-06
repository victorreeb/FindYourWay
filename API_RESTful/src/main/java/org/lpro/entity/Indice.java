package org.lpro.entity;

import java.io.Serializable;
import javax.persistence.Id;

public class Indice implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String id_destination;
	private String text;
	
	public Indice(){}
	
	public Indice(String text){
		this.text = text;
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
	 * @return the id_destination
	 */
	public String getId_destination() {
		return id_destination;
	}

	/**
	 * @param id_destination the id_destination to set
	 */
	public void setId_destination(String id_destination) {
		this.id_destination = id_destination;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}
