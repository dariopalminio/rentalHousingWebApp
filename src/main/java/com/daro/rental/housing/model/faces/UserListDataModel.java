package com.daro.rental.housing.model.faces;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.SelectableDataModel;

import com.daro.rental.housing.model.entity.pojo.UserRoleDetail;

import javax.faces.model.ListDataModel;


/**
 * @author Dario Palminio
 * 
 */
public class UserListDataModel extends ListDataModel<UserRoleDetail> implements
SelectableDataModel<UserRoleDetail>, Serializable {

/**
 * 
 */
private static final long serialVersionUID = 1L;

public UserListDataModel(List<UserRoleDetail> user) {
	super(user);
}

@Override
public UserRoleDetail getRowData(String rowKey) {
	List<UserRoleDetail> users = (List<UserRoleDetail>) getWrappedData();

	for (UserRoleDetail u : users) {
		if (u.getUsername() == rowKey)
			return u;
	}

	return null;
}

@Override
public Object getRowKey(UserRoleDetail user) {
	return user.getUsername();
}


}
