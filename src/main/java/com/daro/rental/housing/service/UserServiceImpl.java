package com.daro.rental.housing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.persistence.generic.service.GenericServiceImpl;
import com.daro.rental.housing.controller.UserEditController;
import com.daro.rental.housing.model.entity.Role;
import com.daro.rental.housing.model.entity.User;
import com.daro.rental.housing.model.entity.pojo.UserRoleDetail;
import com.daro.rental.housing.model.faces.TraslatedRoleEntry;

/**
 * 
 * @author Dario Palminio
 *
 */
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService {

	private final static Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	@Autowired
	private RoleService roleService;

	@Override
	@Transactional
	public User loadUserByUsername(String usernameValue) {
		User userFounded = null;
		try {
			userFounded = this.getByField("username", usernameValue);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userFounded.getRoleList();
		/*
		 * Recorre el listado de roles para traerlos a memoria. De ese modo se
		 * salva de la exception: failed to lazily initialize a collection of
		 * role: com.daro.rental.housing.model.entity.User.roleList, could not
		 * initialize proxy - no Session Caused by:
		 * org.hibernate.LazyInitializationException: failed to lazily
		 * initialize a collection of role:
		 * com.daro.rental.housing.model.entity.User.roleList, could not
		 * initialize proxy - no Session
		 */
		for (Role r : userFounded.getRoleList()) {
			r.getId();
		}
		return userFounded;
	}

	/**
	 * Gets a list of combination of users and their roles.
	 */
	@Transactional
	public List<UserRoleDetail> getUserRoleList() {
		List<User> users = null;
		List<UserRoleDetail> list = new ArrayList<UserRoleDetail>();

		try {
			users = this.list();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}

		for (User u : users) {
			UserRoleDetail item = new UserRoleDetail(u.getUsername(),
					u.getEnabled());
			for (Role r : u.getRoleList()) {
				TraslatedRoleEntry entry = new TraslatedRoleEntry(
						r.getRoleType(), r.getRoleType());
				item.getRoles().add(entry);
			}
			item.setPersonName(u.getPerson().getFullName());
			list.add(item);
		}
		return list;
	}

	@Transactional
	public void saveUserRoles(User user, List<String> selectedList) {
		try {
			logger.debug("----------------------------------- saveUserRoles"
					+ selectedList);
			List<String> listInDataBase = new ArrayList<String>();
			for (Role r : user.getRoleList()) {
				if (!selectedList.contains(r.getRoleType())) {
					roleService.removeById(r.getId());
					selectedList.remove(r.getRoleType());
				}
				listInDataBase.add(r.getRoleType());
			}
			logger.debug("################# saveUserRoles , listInDataBase:"
					+ listInDataBase);
			for (String s : selectedList) {
				if (!listInDataBase.contains(s)) {
					logger.debug("################# saveUserRoles , to add:"
							+ s);
					Role roleNew = new Role();
					roleNew.setRoleType(s);
					roleNew.setUser(user);
					roleService.add(roleNew);
				}
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
