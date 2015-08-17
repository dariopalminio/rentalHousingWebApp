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
import com.daro.rental.housing.controller.generic.AbstractPersonController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.model.faces.PersonListDataModel;

import javax.annotation.PostConstruct;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="personsListController")
@ViewScoped
@Controller
public class PersonsListController extends AbstractPersonController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(PersonsListController.class);
	
	public PersonListDataModel personListDataModel;

	public List<Person> personList;
	
	
	public PersonsListController() {		
		person = new Person(); //represents selected Person
		personList = new ArrayList<Person>();

	}

	@PostConstruct
	public void init() {
		personList = getAllPersons();
		personListDataModel = new PersonListDataModel(personList);
		//updateTable();
	}
	
	//Refreshing Table, update all data to table
	public void updateTable() {
		personList = new ArrayList();
		personListDataModel.setWrappedData(null);
		personList = getAllPersons();
		personListDataModel.setWrappedData(personList);
	}
	
	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public Person getSelectedPerson() {
		//return selected Person
		return person;
	}

	public void setSelectedPerson(Person selectedPerson) {
		//set selected Person;	
		person = selectedPerson;	
	}


	public PersonListDataModel getPersonListDataModel() {
		return personListDataModel;
	}

	public void setPersonDataModel(PersonListDataModel personListDataModel) {
		this.personListDataModel = personListDataModel;
	}


	public void onRowSelect(SelectEvent selectEvent) {
		this.setSelectedPerson((Person) selectEvent.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
		this.setSelectedPerson(null);
	}

	public List<Person> getAllPersons() {
		try {
			return personService.list();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void updatePerson(ActionEvent actionEvent) {
		try {
			personService.update(this.getSelectedPerson());
			updateTable();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletePerson(ActionEvent actionEvent) {
		if (this.thereIsSelectedPerson()){
			try{
				personService.removeById(this.getSelectedPerson().getId());
				updateTable();
				Dictionary dictionary = new DictionaryProvider();
				this.setInfoMessage("Deleted...", this.getSelectedPerson().getFullName() + " " + dictionary.getValue("was_deleted"));
			}catch (Exception e){
				this.setErrorMessage("Error: ", e.getMessage());
			}			
			
		}else{
			this.setErrorMessage("Error: ", "There is not row selected !");
			throw(new java.lang.NullPointerException());
		}
	}

	public void redirectToPersonEdit(ActionEvent actionEvent){
		try {
			if (thereIsSelectedPerson()){
				String personIdValue = this.getSelectedPerson().getId().toString();
				String pageWithParam = PersonEditController.PERSON_EDIT_PAGE + "?personId=" + personIdValue;
				FacesContext.getCurrentInstance().getExternalContext().redirect(pageWithParam);
			}else{
				Dictionary dictionary = new DictionaryProvider();
				setErrorMessage("Error: ", dictionary.getValue("not_person_select"));
			}	        
	        
		} catch (IOException ioe) {
			setErrorMessage("Error: ", ioe.getMessage());
			logger.error(ioe.getStackTrace());
		}		
	}
	
	public boolean thereIsSelectedPerson(){
		return ((this.getSelectedPerson() != null) && (this.getSelectedPerson().getId() != null));
	}
	
}
