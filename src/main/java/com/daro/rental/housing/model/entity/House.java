/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gesti�n de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Dario Palminio
 * 
 */
@Entity
@Table(name = "house")
public class House implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="house_id", nullable = false)
	private Long id;
	
	@Column(name="house_type", nullable = false, length = 3)
	private String houseType;
	
	@Column(name="address", nullable = false, length = 250)
	private String address; //Calle-Nro-Torre-Piso-Dto
	
	@Column(name="city", nullable = false, length = 40)
	private String city; 
	
    @ManyToOne( cascade = {CascadeType.DETACH}, targetEntity=Person.class )
    @JoinColumn(name="proprietary_id")
	private Person proprietary;
	
    @OneToMany(mappedBy="house")
	private List<Contract> contracts = new ArrayList<Contract>();
    
	public Person getProprietary() {
		return proprietary;
	}

	public void setProprietary(Person proprietary) {
		this.proprietary = proprietary;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public String getProprietaryString() {
		String str = null;
		if (proprietary != null){
			str = proprietary.getLastName() + " " + proprietary.getFirstName();
		}
		return str;
	}
	
}
