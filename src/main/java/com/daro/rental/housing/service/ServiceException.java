/**
 * Author: Dario Palminio
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 */
package com.daro.rental.housing.service;

/**
 * ServiceException
 * 
 * @author dario.palminio
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public final static String MSG_ERROR_PERSON_DUPLICATED = "MSG_ERROR_PERSON_DUPLICATED";
	
	public ServiceException(String message){
		super(message);
	}
}
