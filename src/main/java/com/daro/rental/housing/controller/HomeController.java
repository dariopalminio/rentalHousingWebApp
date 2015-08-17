/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="homeController")
@ViewScoped
@Controller
public class HomeController {

	
	
	public String getWelcomeMessage(){
		
       /* Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, lang);
         
        String formattedDate = dateFormat.format(date);*/
		Dictionary dictionary = new DictionaryProvider();
		
		return dictionary.getValue("welcome");
        
	}	
	
}
