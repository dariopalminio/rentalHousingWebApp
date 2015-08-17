package com.daro.rental.housing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.daro.rental.housing.controller.generic.AbstractPersonController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.model.entity.Role;
import com.daro.rental.housing.model.entity.RoleType;
import com.daro.rental.housing.model.entity.User;
import com.daro.rental.housing.model.faces.SelectItemBuilder;
import com.daro.rental.housing.service.ServiceException;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="personAddController")
@ViewScoped
@Controller
public class PersonAddController extends AbstractPersonController {

	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(PersonAddController.class);
	
	private String role;
	
	public PersonAddController(){
		clear();
	}	
	
	public void clear(){
		person = new Person();		
		role = RoleType.ROLE_ANONYMOUS.name();
	}
	
	/**
	 * Add new person with user and role
	 */
	public void addPerson() {
		Dictionary dictionary = new DictionaryProvider();
		try {
			person = personService.addPersonAndUser(person, this.role);
			String message = person.getFullName() + dictionary.getValue("was_added");
			message += dictionary.getValue("username") + person.getUser().getUsername();
			message += dictionary.getValue("password") + person.getUser().getPassword();
			this.setInfoMessage(message, message);
		} catch (ServiceException se) {
			this.setErrorMessage("Error: ", dictionary.getValue(se.getMessage()));
			logger.error(se.getMessage());
		} catch (Exception e){
			this.setErrorMessage("Error: ",e.getMessage());
			logger.error(e.getMessage());
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
	
	/**
	 * Data Provider to list box view (p:selectOneMenu)
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getRoleTypeList(){		
		SelectItemBuilder selectItemBuilder = new SelectItemBuilder();
	    List<SelectItem> types = selectItemBuilder.buildRoleTypeList();
	    return types;			
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
