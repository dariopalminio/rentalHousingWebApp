package com.daro.rental.housing.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.rental.housing.controller.generic.AbstractPersonController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.faces.SelectItemBuilder;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="personEditController")
@ViewScoped
@Controller
public class PersonEditController extends AbstractPersonController {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(PersonEditController.class);
	
	public final static String PERSON_EDIT_PAGE = "personEdit.xhtml";
	
	private String personId; //Request parameter (viewParam).
	
	public PersonEditController(){
	}
	
	/**
	 * Load the data after being redirected the page and have been sent a 'personId' parameter (viewParam).
	 */
	public void loadData() {
		if (personId != null){
			try {
				person = personService.getById(Long.valueOf(personId));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String msgError = "The personId parameter is null. There is a problem with parameter passing.";
			logger.error(msgError);
			this.setErrorMessage("Error: ", msgError);
		}
	}

	/**
	 * Edit Person (save data)
	 */
	public void save() {
		try{
			personService.update(person);
			Dictionary dictionary = new DictionaryProvider();
			String message = person.getFullName() + dictionary.getValue("was_edited");
			this.setInfoMessage(message, message);			
		}catch(Exception e){
			logger.error(e.getMessage());
			this.setErrorMessage("Error: ",e.getMessage());
		}		
	}
	
	/**
	 * Data Provider to list box view (p:selectOneMenu)
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getDniTypeList() {		
		SelectItemBuilder selectItemBuilder = new SelectItemBuilder();
	    List<SelectItem> types = selectItemBuilder.buildDnyTypesList();
	    return types;
	}
	
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
}
