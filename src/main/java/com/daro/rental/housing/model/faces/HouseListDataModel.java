package com.daro.rental.housing.model.faces;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.SelectableDataModel;

import com.daro.rental.housing.model.entity.House;

import javax.faces.model.ListDataModel;

/**
 * @author Dario Palminio
 * 
 */
public class HouseListDataModel extends ListDataModel<House> implements
SelectableDataModel<House>, Serializable {

/**
 * 
 */
private static final long serialVersionUID = 1L;

public HouseListDataModel(List<House> house) {
	super(house);
}

@Override
public House getRowData(String rowKey) {
	// TODO Auto-generated method stub
	List<House> houses = (List<House>) getWrappedData();

	for (House h : houses) {
		if (h.getId() == Long.parseLong(rowKey))
			return h;
	}

	return null;
}

@Override
public Object getRowKey(House house) {
	return house.getId();
}



}
