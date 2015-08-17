/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.faces;

/**
 * 
 * @author dario.palminio
 *
 */
public class TraslatedRoleEntry {

	private String role; //key value
	private String label; //Translated value
	
	public TraslatedRoleEntry(String role, String label){
		this.role = role;
		this.label = label;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
	  return label;
	}
	
} 
