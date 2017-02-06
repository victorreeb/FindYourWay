package org.lpro.entity;

import java.io.Serializable;

import javax.persistence.Id;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;
	//1 or 0, 1 for admin
	private int type;
	private String password;
	
	public User(){}
	
	public User(String name, String email, String password){
		this.type = 0;
		if(name != null){
			this.name = name;	
		}
		if(email != null){
			this.email = email;			
		}
		if(password != null){
			this.password = password;
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
		
	
	
	
}
