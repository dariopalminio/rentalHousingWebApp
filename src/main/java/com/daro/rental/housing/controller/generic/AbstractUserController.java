package com.daro.rental.housing.controller.generic;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.daro.rental.housing.model.entity.User;
import com.daro.rental.housing.service.UserService;

/**
 * @author Dario Palminio
 * 
 */
public abstract class AbstractUserController implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Autowired
	@ManagedProperty("#{userService}")
	protected UserService userService;
	
	public User user;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
}