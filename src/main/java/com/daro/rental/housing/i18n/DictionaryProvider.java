/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 * @author Dario Palminio
 * 
 */
public class DictionaryProvider implements Dictionary{
	 
    private ResourceBundle bundle; //resource-bundle in face-config.xml configured
    private final static String BUNDLE_NAME = "dictionary";
    
    public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

    /**
     * Retrieve resource bundle from Context
     * @return
     */
	public ResourceBundle getBundle() {
        if (bundle == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            bundle = context.getApplication().getResourceBundle(context, BUNDLE_NAME);
        }
        return bundle;
    }
 
	/**
	 * Get entry value from dictionary bundle for specified key.
	 * 
	 * @param key String to translate
	 * @return String translated value
	 */
    public String getValue(String key) { 
        String result = null;
        try {
            result = getBundle().getString(key);
        } catch (MissingResourceException e) {
        	//Not found
            result = "???" + key + "???";
        }
        return result;
    }
 
}

