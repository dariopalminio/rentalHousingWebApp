/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Dario Palminio
 * 
 */
@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="username", nullable = false, length = 45)
	private String username;
	
	@Column(name="password", nullable = false, length = 45)
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;
	
	@OneToOne
    @JoinColumn(name="person_id")
	private Person person;

    @OneToMany(mappedBy="user", cascade =  CascadeType.REMOVE)
	private List<Role> roleList = new ArrayList<Role>(); //Authorities
    
    public User(){
    	enabled = true;
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
    
	/*
	public String getRolesStr(){
		String roleStr = "";
		org.hibernate.Hibernate.initialize(this.getRoleList());
		for (Role r : this.getRoleList()){
			roleStr += r.getRoleType();
		}
		return roleStr;
	}*/
}
