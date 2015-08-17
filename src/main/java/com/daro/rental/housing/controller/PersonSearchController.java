package com.daro.rental.housing.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Controller;

import com.daro.rental.housing.controller.generic.AbstractPersonController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.Person;

import javax.annotation.PostConstruct;

@ManagedBean(name = "personSearchController")
@ViewScoped
@Controller
public class PersonSearchController extends AbstractPersonController {

	private static final long serialVersionUID = 1L;

	public final static String PERSON_SEARCH_DIALOG_PAGE = "personSearch"; //personSearch.XHTML
	
	private String dniToSearch;

	@PostConstruct
	public void init() {
		person = new Person();
		dniToSearch = "";
	}

	public String getDniToSearch() {
		return dniToSearch;
	}

	public void setDniToSearch(String dniToSearch) {
		this.dniToSearch = dniToSearch;
	}

	public void search(){
		//personsList = personService.search(parameterMap);		
		person = personService.getByDni(dniToSearch);
		if (person == null){
			if (dniToSearch.equals("")){
				this.setInfoMessage("enter any text.", person.getFullName() + " " + "enter any text.");
			}
			this.setInfoMessage("not founded", person.getFullName() + " " + "not founded");
		}
	}
	
	public void selectPerson() {
		if (person.getId() != null){
			RequestContext.getCurrentInstance().closeDialog(person);
		}else{
			//Person not selected
			Dictionary dictionary = new DictionaryProvider();
			String message = dictionary.getValue("msg_error_person_not_selected");
			this.setErrorMessage("Selection...", message);
		}
	}

}
