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

import org.apache.log4j.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.stereotype.Controller;

import com.daro.rental.housing.controller.generic.AbstractUserController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.Role;
import com.daro.rental.housing.model.entity.RoleType;
import com.daro.rental.housing.model.faces.RolesPickList;
import com.daro.rental.housing.model.faces.TraslatedRoleEntry;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="usersEditController")
@ViewScoped
@Controller
public class UserEditController extends AbstractUserController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(UserEditController.class);
	
	public final static String USER_EDIT_PAGE = "userEdit.xhtml";
	
	private String usernameToEdit; //Request parameter (viewParam).
	
	private RolesPickList rolesPickList = new RolesPickList();

	
	/**
	 * Load the data after being redirected the page and have been sent a 'usernameToEdit' parameter (viewParam).
	 */
	public void loadData() {
		if (usernameToEdit != null){
			user = userService.loadUserByUsername(usernameToEdit);
			
			List<String> targetRoles = retrieveTarget(user.getRoleList());
			List<String> allRoles = RoleType.getNamesList();
			rolesPickList = new RolesPickList();
			logger.debug("Load data -+-+-+-+-+-+  targetRoles"+targetRoles);
			rolesPickList.initialize(allRoles, targetRoles);
			translate();
			
		}else{
			String msgError = "The usernameToEdit parameter is null. There is a problem with parameter passing.";
			logger.error(msgError);
			this.setErrorMessage(msgError,msgError);
		}
	}

	private List<String> retrieveTarget(List<Role> rolesTarget){
		List<String> target = new ArrayList<String>();
		for (Role r : rolesTarget){
			target.add(r.getRoleType());
		}
		return target;
	}	
	
	private void translate(){
		Dictionary dictionary = new DictionaryProvider();
		for (TraslatedRoleEntry item : rolesPickList.getRoles().getSource()){
			String label = dictionary.getValue(item.getRole());
			item.setLabel(label);
		}
		for (TraslatedRoleEntry item : rolesPickList.getRoles().getTarget()){
			String label = dictionary.getValue(item.getRole());
			item.setLabel(label);
		}
	}
	
	/**
	 * Edit User (save data)
	 */
	public void save() {
		try{
			List<String> selectedList = rolesPickList.getTargetStringList();
			userService.saveUserRoles(user, selectedList);
			Dictionary dictionary = new DictionaryProvider();
			String message = user.getUsername() + dictionary.getValue("was_edited");
			this.setInfoMessage(message, message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getUsernameToEdit() {
		return usernameToEdit;
	}

	public void setUsernameToEdit(String usernameToEdit) {
		this.usernameToEdit = usernameToEdit;
	}

	public DualListModel<TraslatedRoleEntry> getRolesDualListModel() {
		return rolesPickList.getRoles();
	}

	public void setRolesDualListModel(
			DualListModel<TraslatedRoleEntry> rolesDualListModel) {
		rolesPickList.setRoles(rolesDualListModel);
	}
	
}
