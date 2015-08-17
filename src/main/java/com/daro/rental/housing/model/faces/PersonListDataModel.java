package com.daro.rental.housing.model.faces;

import java.io.Serializable;
import java.util.List;
import org.primefaces.model.SelectableDataModel;
import javax.faces.model.ListDataModel;

import com.daro.rental.housing.model.entity.Person;


/**
 * @author Dario Palminio
 * 
 */
public class PersonListDataModel extends ListDataModel<Person> implements
	SelectableDataModel<Person>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonListDataModel(List<Person> person) {
		super(person);
	}
	
	@Override
	public Person getRowData(String rowKey) {
		// TODO Auto-generated method stub
		List<Person> persons = (List<Person>) getWrappedData();

		for (Person p : persons) {
			if (p.getId() == Long.parseLong(rowKey))
				return p;
		}

		return null;
	}

	@Override
	public Object getRowKey(Person person) {
		return person.getId();
	}

	

}
