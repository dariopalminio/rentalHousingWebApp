/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.controller.generic;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.daro.rental.housing.model.entity.House;
import com.daro.rental.housing.service.HouseService;

/**
 * @author Dario Palminio
 * 
 */
public class AbstractHouseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected HouseService houseService;
	
	public House house;

	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
	
	public void setInfoMessage(String msgSummary, String msgDetail) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		facesMessage.setSummary(msgSummary);
		facesMessage.setDetail(msgDetail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void setErrorMessage(String msgSummary, String msgDetail) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setSummary(msgSummary);
		facesMessage.setDetail(msgDetail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public HouseService getHouseService() {
		return houseService;
	}

	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
	}
    
}
