package com.daro.rental.housing.controller;

import java.text.DateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name = "statusController")
@ViewScoped
@Controller
public class StatusController {

	final static Logger logger = Logger.getLogger(StatusController.class);

	Dictionary dictionary = new DictionaryProvider();

	public String getStatusMessage() {
		String message = "";
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, FacesContext.getCurrentInstance()
						.getViewRoot().getLocale());

		String formattedDate = dateFormat.format(date);

		message += dictionary.getValue("user") + ": " + getCurrentLogedUser();
		message += ", " + dictionary.getValue("date") + ": " + formattedDate;
		return message;

	}

	private String getCurrentLogedUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentUserLogedInfo;
		if (auth != null){
			currentUserLogedInfo = auth.getName(); // get logged in username
			String authoritiesStr = "["; // roles
			for (GrantedAuthority ga : auth.getAuthorities()) {
				authoritiesStr += dictionary.getValue(ga.toString()) + " ";
			}
			authoritiesStr += "]";
			currentUserLogedInfo += " " + authoritiesStr;
		}else{
			//is loggedout
			currentUserLogedInfo = dictionary.getValue("ROLE_ANONYMOUS");
		}
		return currentUserLogedInfo;
	}
}
