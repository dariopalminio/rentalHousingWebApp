/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dario Palminio
 * 
 */
public enum RoleType{
	ROLE_ADMIN,
	ROLE_AGENT,
	ROLE_PROPRIETARY,
	ROLE_RENTERS,
	ROLE_ANONYMOUS;	
	
	
	public static final List<String> getNamesList(){
		List<String> list = new ArrayList<String>();
		for (RoleType r : RoleType.values()) {
			list.add(r.name());
		}
		return list;
	}
	
}
