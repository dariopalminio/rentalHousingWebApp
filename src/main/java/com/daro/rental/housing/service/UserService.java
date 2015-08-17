package com.daro.rental.housing.service;

import java.util.List;
import com.daro.persistence.generic.service.GenericService;
import com.daro.rental.housing.model.entity.User;
import com.daro.rental.housing.model.entity.pojo.UserRoleDetail;


/**
 * @author Dario Palminio
 * 
 */
public interface UserService extends GenericService<User>{

	public User loadUserByUsername(String usernameValue);
	public List<UserRoleDetail> getUserRoleList();
	void saveUserRoles(User user, List<String> selectedList);
}
