/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.DniType;
import com.daro.rental.housing.model.entity.HouseType;
import com.daro.rental.housing.model.entity.RoleType;

/**
 * @author Dario Palminio
 * 
 */
public class SelectItemBuilder {

	/**
	 * Return a list of SelectItem that represents a trslated HouseType list 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> buildHouseTypeList() {
	    List<SelectItem> types = new ArrayList<SelectItem>();
	    DictionaryProvider dictionary = new DictionaryProvider();
	    for (HouseType item : HouseType.values())
        {
	    	String key = item.name();
	    	String traslatedKey = dictionary.getValue(key);
	    	types.add(new SelectItem(key,traslatedKey));
        }
	    return types;
	}
	
	public List<SelectItem> buildDnyTypesList() {
	    List<SelectItem> types = new ArrayList<SelectItem>();
	    
	    for (DniType item : DniType.values())
        {
	    	types.add(new SelectItem(item.name(),item.name()));
        }
	    return types;
	}
	
	public List<SelectItem> buildRoleTypeList() {
		List<SelectItem> types = new ArrayList<SelectItem>();
		DictionaryProvider dictionary = new DictionaryProvider();
		for (RoleType item : RoleType.values())
	        {
			    String key = item.name();
		    	String traslatedKey = dictionary.getValue(key);
		    	types.add(new SelectItem(key,traslatedKey));
	        }
		return types;
	}
}
