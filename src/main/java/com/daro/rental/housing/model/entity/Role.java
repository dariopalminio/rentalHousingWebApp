/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Dario Palminio
 * 
 */
@Entity
@Table(name = "roles")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", nullable = false)
	private Long id;
	
	//@Column(name="username", nullable = false, length = 45)
	//private String username;
	
	@Column(name="role", nullable = false, length = 45)
	private String roleType; //Authority

    @ManyToOne( cascade = {CascadeType.DETACH}, targetEntity=User.class )
    @JoinColumn(name="username")
	private User user;

    public Role(String role){
    	this.roleType = role;
    }
    
    public Role(){
    	roleType = RoleType.ROLE_ANONYMOUS.name();
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
       
	
}
