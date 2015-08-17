package com.daro.rental.housing.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Controller;

import com.daro.rental.housing.controller.generic.AbstractUserController;
import com.daro.rental.housing.i18n.Dictionary;
import com.daro.rental.housing.i18n.DictionaryProvider;
import com.daro.rental.housing.model.entity.pojo.UserRoleDetail;
import com.daro.rental.housing.model.faces.TraslatedRoleEntry;
import com.daro.rental.housing.model.faces.UserListDataModel;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name = "usersListController")
@ViewScoped
@Controller
public class UsersListController extends AbstractUserController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(UsersListController.class);

	public UserListDataModel userListDataModel;

	private UserRoleDetail selectedUser;

	public List<UserRoleDetail> userList;

	
	public UsersListController() {
		selectedUser = new UserRoleDetail();
		userList = new ArrayList<UserRoleDetail>();
	}

	@PostConstruct
	public void init() {
		userList = new ArrayList();
		// userListDataModel.setWrappedData(null);
		// userList = getAllUsers();
		userListDataModel = new UserListDataModel(userList);
		userListDataModel.setWrappedData(userList);
		// updateTable();
	}

	// Refreshing Table, update all data to table
	public void updateTable() {
		userList = new ArrayList();
		userListDataModel.setWrappedData(null);
		userList = getAllUsers();
		userListDataModel.setWrappedData(userList);
	}

	public List<UserRoleDetail> getUserList() {
		return userList;
	}

	public void setUserList(List<UserRoleDetail> userList) {
		this.userList = userList;
	}

	public UserRoleDetail getSelectedUser() {
		// return selected User;
		logger.info("UsersListController.getSelectedUser:" + selectedUser);
		return selectedUser;
	}

	public void setSelectedUser(UserRoleDetail selectedUser) {
		// set selected User;
		this.selectedUser = selectedUser;
		logger.info("UsersListController.setSelectedUser:" + selectedUser);
	}

	public UserListDataModel getUserListDataModel() {
		return userListDataModel;
	}

	public void setUserDataModel(UserListDataModel userListDataModel) {
		this.userListDataModel = userListDataModel;
	}

	public void onRowSelect(SelectEvent selectEvent) {
		logger.info("UsersListController.onRowSelect:" + selectedUser);
		this.setSelectedUser((UserRoleDetail) selectEvent.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
		this.setSelectedUser(null);
		logger.info("UsersListController.onRowUnselect");
	}

	public List<UserRoleDetail> getAllUsers() {
		List<UserRoleDetail> allUsers = userService.getUserRoleList();
		allUsers = translate(allUsers);
		return allUsers;
	}

	/**
	 * Translate roles names from each user item to correct language using
	 * Dictionary. Translate role keys to values depending of language.
	 * 
	 * @param list
	 * @return
	 */
	public List<UserRoleDetail> translate(List<UserRoleDetail> list) {
		Dictionary dictionary = new DictionaryProvider();
		for (UserRoleDetail item : list) {
			for (TraslatedRoleEntry r : item.getRoles()) {
				r.setLabel(dictionary.getValue(r.getRole()));
			}
		}
		return list;
	}

	public void redirectToUserEdit(ActionEvent actionEvent) {
		try {
			if (thereIsSelectedUser()) {
				String usernameToEdit = selectedUser.getUsername();
				String pageWithParam = UserEditController.USER_EDIT_PAGE
						+ "?usernameToEdit=" + usernameToEdit;
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(pageWithParam);
			} else {
				DictionaryProvider dictionary = new DictionaryProvider();
				setErrorMessage("Selection...", dictionary.getValue("not_user_select"));
			}

		} catch (IOException ioe) {
			setErrorMessage("Error...", ioe.getMessage());
			logger.error(ioe.getStackTrace());
		}
	}

	public boolean thereIsSelectedUser() {
		return ((this.getSelectedUser() != null) && (this.getSelectedUser()
				.getUsername() != null));
	}

}
