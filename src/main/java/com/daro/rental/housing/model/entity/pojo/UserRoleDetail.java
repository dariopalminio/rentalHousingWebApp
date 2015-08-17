package com.daro.rental.housing.model.entity.pojo;

import java.util.ArrayList;
import java.util.List;


import com.daro.rental.housing.model.faces.TraslatedRoleEntry;

/**
 * @author Dario Palminio
 * 
 */
public class UserRoleDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private List<TraslatedRoleEntry> roles = new ArrayList<TraslatedRoleEntry>();
	
	private String personName;
	
	private Boolean enabled;

	public UserRoleDetail(){
		this.username = "";
		this.enabled = false;
	}
	
	public UserRoleDetail(String username, Boolean enabled){
		this.username = username;
		this.enabled = enabled;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<TraslatedRoleEntry> getRoles() {
		return roles;
	}

	public void setRoles(List<TraslatedRoleEntry> roles) {
		this.roles = roles;
	}

	/**
	 * Get Translated Label list for all roles.
	 * @return
	 */
	public String getTraslatedRoles() {
		String traslated = "";
		String separator = "";
		for(TraslatedRoleEntry r : roles){
			traslated += separator + r.getLabel();
			separator = ", ";
		}
		return traslated;
	}
	
}
