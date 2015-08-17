package com.daro.rental.housing.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Controller;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.rental.housing.controller.generic.AbstractHouseController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.House;
import com.daro.rental.housing.model.faces.HouseListDataModel;

import javax.annotation.PostConstruct;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="housesListController")
@ViewScoped
@Controller
public class HousesListController extends AbstractHouseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(HousesListController.class);
	
	public HouseListDataModel houseListDataModel;

	public List<House> houseList;

	private Dictionary dictionary = new DictionaryProvider();
	
	public HousesListController() {				
		house = new House();
		houseList = new ArrayList<House>();		
	}

	@PostConstruct
	public void init() {
		houseList = getAllHouses();
		houseListDataModel = new HouseListDataModel(houseList);
		//updateTable();
	}
	
	//Refreshing Table, update all data to table
	public void updateTable() {
		houseList = new ArrayList();
		houseListDataModel.setWrappedData(null);
		houseList = getAllHouses();
		houseListDataModel.setWrappedData(houseList);
	}
	
	public List<House> getHouseList() {
		return houseList;
	}

	public void setHouseList(List<House> houseList) {
		this.houseList = houseList;
	}

	public House getSelectedHouse() {
		//return selected House;
		return house;
	}

	public void setSelectedHouse(House selectedHouse) {
		//set selected House;	
		house = selectedHouse;	
	}

	public HouseListDataModel getHouseListDataModel() {
		return houseListDataModel;
	}

	public void setHouseDataModel(HouseListDataModel houseListDataModel) {
		this.houseListDataModel = houseListDataModel;
	}


	public void onRowSelect(SelectEvent selectEvent) {
		this.setSelectedHouse((House) selectEvent.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
		this.setSelectedHouse(null);
	}

	public List<House> getAllHouses() {
		try {
			return houseService.list();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void updateHouse(ActionEvent actionEvent) {
		try {
			houseService.update(this.getSelectedHouse());
			updateTable();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteHouse(ActionEvent actionEvent) {
		if (this.thereIsSelectedHouse()){
			try{
				houseService.removeById(this.getSelectedHouse().getId());
				updateTable();
				this.setInfoMessage("Deleted", this.getSelectedHouse().getAddress() + " " + dictionary.getValue("was_deleted"));
			}catch (Exception e){
				this.setErrorMessage("Error...", e.getMessage());
			}	
		}else{
			this.setErrorMessage("Selection...", dictionary.getValue("there_is_not_row_selected"));
			throw(new java.lang.NullPointerException());
		}
	}

	public void redirectToHouseEdit(ActionEvent actionEvent){
		try {
			if (thereIsSelectedHouse()){
				String houseIdValue = this.getSelectedHouse().getId().toString();
				String pageWithParam = HouseEditController.HOUSE_EDIT_PAGE + "?houseId=" + houseIdValue;
				FacesContext.getCurrentInstance().getExternalContext().redirect(pageWithParam);
			}else{
				setErrorMessage("Selected", dictionary.getValue("not_house_select"));
			}	        
	        
		} catch (IOException ioe) {
			logger.error(ioe.getStackTrace());
		}		
	}
	
	public boolean thereIsSelectedHouse(){
		return ((this.getSelectedHouse() != null) && (this.getSelectedHouse().getId() != null));
	}

	
}
