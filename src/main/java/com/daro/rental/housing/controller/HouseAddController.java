/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.rental.housing.controller.generic.AbstractHouseController;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.House;
import com.daro.rental.housing.model.entity.HouseType;
import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.model.faces.SelectItemBuilder;

import javax.faces.model.SelectItem;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name = "houseAddController")
@ViewScoped
@Controller
public class HouseAddController extends AbstractHouseController {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(HouseAddController.class);
	
	private String selectedProprietaryName;
	
	public String getSelectedProprietaryName() {
		return selectedProprietaryName;
	}

	public void setSelectedProprietaryName(String selectedProprietaryName) {
		this.selectedProprietaryName = selectedProprietaryName;
	}

	public HouseAddController(){
		clear();
	}	
	
	public void clear(){
		house = new House();
		house.setHouseType(HouseType.UNIDENTIFIED.name());
		selectedProprietaryName = ""; //UNIDENTIFIED
	}
	
	public void addHouse() {
		DictionaryProvider dictionary = new DictionaryProvider();
		if((house.getProprietary() != null) && (house.getProprietary() != null)){
			try {
				houseService.add(house);
				String message = house.getAddress() + dictionary.getValue("was_added");
				this.setInfoMessage(message, message);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//Person not selected
			String message = dictionary.getValue("msg_error_proprietary_not_selected");
			this.setErrorMessage(message, message);
		}
	}
	
	//Open Person Selecting Dialog Page as modal Dialog
	public void chooseProprietary() {
		logger.info("houseAddController.chooseProprietary");
		RequestContext.getCurrentInstance().openDialog(PersonSearchController.PERSON_SEARCH_DIALOG_PAGE);
	}

	//Return from Person Selecting Dialog Page as modal Dialog
	public void onProprietaryChosen(SelectEvent event) {
		logger.info("houseAddController.onCarChosen");
		Person proprietarySelected = (Person) event.getObject();
		house.setProprietary(proprietarySelected);
		selectedProprietaryName = proprietarySelected.getFullName();
	}
	
	public List<SelectItem> getHouseTypesList() {
		SelectItemBuilder selectItemBuilder = new SelectItemBuilder();
	    List<SelectItem> types = selectItemBuilder.buildHouseTypeList();
	    return types;
	}
		
	
	
}
