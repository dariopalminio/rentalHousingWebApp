/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestión de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.service;


import com.daro.persistence.generic.service.GenericService;
import com.daro.rental.housing.model.entity.Person;

/**
 * @author Dario Palminio
 * 
 */
public interface PersonService extends GenericService<Person>{

	public Person getByDni(String dni);
	public Person addPersonAndUser(Person p, String strRole) throws ServiceException;
	public boolean isNotExistsPerson(String dni);
	
}

