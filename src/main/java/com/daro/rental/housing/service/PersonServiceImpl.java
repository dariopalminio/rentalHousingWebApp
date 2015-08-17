/**
 * Daro Rental Housing Argentina (Management System for building rentals)
 * Sistema para gestión de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.daro.persistence.generic.error.PersistenceException;
import com.daro.persistence.generic.service.GenericServiceImpl;
import com.daro.rental.housing.model.entity.Person;
import com.daro.rental.housing.model.entity.Role;
import com.daro.rental.housing.model.entity.User;
import com.daro.rental.housing.util.PasswordGenerator;

/**
 * @author Dario Palminio
 * 
 */
public class PersonServiceImpl extends GenericServiceImpl<Person> implements
		PersonService {

	final static Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * Get Person by DNI code
	 */
	@Transactional
	public Person getByDni(String dni) {
		Person personFounded = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("dniCode", dni);
		List<Person> lst = null;
		try {
			lst = this.search(parameterMap);
			if (!lst.isEmpty()) {
				personFounded = lst.get(0);
			}
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personFounded;
	}

	/**
	 * Persists Person-User-Role
	 */
	@Transactional
	public Person addPersonAndUser(Person p, String strRole)
			throws ServiceException {

		if (isNotExistsPerson(p.getDniCode())) {
			try {
				Role role = new Role(strRole);
				User user = new User();
				user.setUsername(p.getDniCode());
				user.setPassword(PasswordGenerator.getNewPass());
				role.setUser(user);
				p.setUser(user);

				this.add(p);

				user.setPerson(p);

				userService.add(user);
				roleService.add(role);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ServiceException(
					ServiceException.MSG_ERROR_PERSON_DUPLICATED);
		}
		return p;
	}

	public boolean isNotExistsPerson(String dni) {
		Person personFounded = getByDni(dni);
		return (personFounded == null);
	}

}
