package com.daro.rental.housing.controller.generic;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.service.PersonService;

/**
 * @author Dario Palminio
 * 
 */
public abstract class AbstractPersonController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@ManagedProperty("#{personService}")
	protected PersonService personService;

	public Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
