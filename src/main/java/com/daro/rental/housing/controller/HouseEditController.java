/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
 * Author: Dario Palminio
 */
package com.daro.rental.housing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.rental.housing.controller.generic.AbstractHouseController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.model.faces.SelectItemBuilder;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name = "houseEditController")
@ViewScoped
@Controller
public class HouseEditController extends AbstractHouseController {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String HOUSE_EDIT_PAGE = "houseEdit.xhtml";
	
	final static Logger logger = Logger.getLogger(HouseAddController.class);
	
	private String houseId;
	
	private String selectedProprietaryName;
	
	public void loadData() {
		if (houseId != null){
			try {
				house = houseService.getById(Long.valueOf(houseId));
				selectedProprietaryName = house.getProprietaryString();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public String getSelectedProprietaryName() {
		return selectedProprietaryName;
	}

	public void setSelectedProprietaryName(String selectedProprietaryName) {
		this.selectedProprietaryName = selectedProprietaryName;
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
		if (proprietarySelected != null){
			house.setProprietary(proprietarySelected);
			selectedProprietaryName = proprietarySelected.getFullName();
		}
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	/**
	 * Save. Persist changes.
	 */
	public void save() {
		if (house != null){
			try {
				houseService.update(house);
				Dictionary dictionary = new DictionaryProvider();
				String message = house.getAddress() + dictionary.getValue("was_edited");
				this.setInfoMessage(message, message);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.setErrorMessage("Error...", "House is null! Select a house.");
		}
	
	}
	
	public List<SelectItem> getHouseTypesList() {
		SelectItemBuilder selectItemBuilder = new SelectItemBuilder();
	    List<SelectItem> types = selectItemBuilder.buildHouseTypeList();
	    return types;
	}
	
}