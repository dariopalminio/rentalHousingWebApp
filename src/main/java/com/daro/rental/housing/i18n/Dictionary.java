package com.daro.rental.housing.i18n;

/**
 * @author Dario Palminio
 * 
 */
public interface Dictionary {

	/**
	 * Get entry value from dictionary bundle for specified key.
	 * 
	 * @param key String to translate
	 * @return String translated value
	 */
	public String getValue(String key);
	
}
