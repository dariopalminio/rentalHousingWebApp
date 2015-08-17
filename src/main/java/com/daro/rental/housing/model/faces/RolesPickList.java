package com.daro.rental.housing.model.faces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import com.daro.rental.housing.service.UserServiceImpl;

/**
 * 
 * @author dario.palminio
 *
 */
public class RolesPickList {

	private final static Logger logger = Logger.getLogger(RolesPickList.class);
	
	private DualListModel<TraslatedRoleEntry> roles;

	public RolesPickList(){
		List<TraslatedRoleEntry> source = new ArrayList<TraslatedRoleEntry>();
        List<TraslatedRoleEntry> target = new ArrayList<TraslatedRoleEntry>();    
        roles = new DualListModel<TraslatedRoleEntry>(source, target);
	}
	
	public DualListModel<TraslatedRoleEntry> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<TraslatedRoleEntry> roles) {
		this.roles = roles;
	}
	
	public List<TraslatedRoleEntry> getSource() {
		return roles.getSource();
	}

	public void setSource(List<TraslatedRoleEntry> source) {
		this.roles.setSource(source);
	}

	public List<TraslatedRoleEntry> getTarget() {
		return roles.getTarget();
	}

	public void setTarget(List<TraslatedRoleEntry> target) {
		this.roles.setTarget(target);
	}

	/**
	 * Initialize
	 * @param allList All options
	 * @param targetList Selected options
	 */
	public void initialize(List<String> allList, List<String> targetList){
		List<TraslatedRoleEntry> source = new ArrayList<TraslatedRoleEntry>();
        List<TraslatedRoleEntry> target = new ArrayList<TraslatedRoleEntry>();
        
        for (String s : targetList){
        	target.add(new TraslatedRoleEntry(s,s));
        }
        
        for (String s : allList){
        	if (!targetList.contains(s)){
        		source.add(new TraslatedRoleEntry(s,s));
        	}
        }
        
        roles = new DualListModel<TraslatedRoleEntry>(source, target);
	}
	
	public List<String> getTargetStringList() {
		List<String> stringList = new ArrayList<String>();
		logger.debug("****************** getTargetStringList:"+roles.getTarget());
		for (TraslatedRoleEntry item : roles.getTarget()){
			stringList.add(item.getRole());
		}
		return stringList;
	}
	
}
