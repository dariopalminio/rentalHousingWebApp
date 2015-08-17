package com.daro.rental.housing.controller.util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.ConverterException;

import org.apache.log4j.Logger;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import com.daro.rental.housing.controller.HouseAddController;
import com.daro.rental.housing.model.faces.TraslatedRoleEntry;

/**
 * Used to "p:pickList" primeface TAG.
 * Work with RolesPickList class, TraslatedRoleEntry class, UserEditController class and userEdit.xhtml.
 * Al DualListModel le pasamos directamente los objetos de nuestro modelo, que tendremos que convertir. 
 * El componente PickList de primeface acepta en su atributo "converter" el identificador del conversor de los 
 * objetos que le pasamos. Estos objetos normalmente tendrán un conjunto de atributos entre los 
 * que estará un identificador que será el valor real en el HTML y el que se enviará al servidor
 * para que con éste sepamos a que objeto concreto hace referencia. En nuestro ejemplo tenemos en 
 * el TraslatedRoleEntry, el identificador (role) y el nombre(label). 
 * 
 * @author dario.palminio
 *
 */
@FacesConverter("traslatedRoleEntryPickListConverter")
public class TraslatedRoleEntryPickListConverter implements Converter{

	final static Logger logger = Logger.getLogger(TraslatedRoleEntryPickListConverter.class);
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		return getObjectFromUIPickListComponent(component,value);
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
       
        if(object == null){
            string="";
        }else{
            try{
                string = String.valueOf(((TraslatedRoleEntry)object).getRole());
            }catch(ClassCastException cce){
            	logger.error(cce.getMessage());
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private TraslatedRoleEntry getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<TraslatedRoleEntry> dualList;
        try{
            dualList = (DualListModel<TraslatedRoleEntry>) ((PickList)component).getValue();
            TraslatedRoleEntry traslatedRoleEntry = getObjectFromList(dualList.getSource(),value);
            if(traslatedRoleEntry==null){
                traslatedRoleEntry = getObjectFromList(dualList.getTarget(),value);
            }             
            return traslatedRoleEntry;
        }catch(ClassCastException cce){
        	logger.error(cce.getMessage());
            throw new ConverterException();
        }catch(NumberFormatException nfe){
        	logger.error(nfe.getMessage());
            throw new ConverterException();
        }
    }
    
    private TraslatedRoleEntry getObjectFromList(final List<?> list, final String identifier) {
        for(final Object object:list){
            final TraslatedRoleEntry traslatedRoleEntry = (TraslatedRoleEntry) object;
            if(traslatedRoleEntry.getRole().equals(identifier)){
                return traslatedRoleEntry;
            }
        }
        return null;
    }
   
}
