/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gestion de alquileres inmobiliarios
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Dario Palminio
 * 
 */
@Entity
@Table(name = "person")
public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="person_id", nullable = false)
	private Long id;
	
	@Column(name="first_name", nullable = false, length = 40)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length = 40)
	private String lastName; 
	
	@Column(name="dni_type", nullable = false, length = 3)
	private String dniType; 
	
	@Column(name="dni_code", nullable = false, length = 40)
	private String dniCode;

	@Column(name="address", nullable = false, length = 250)
	private String address; //Calle-Nro-Torre-Piso-Dto
	
	@Column(name="city", nullable = false, length = 40)
	private String city; 
	
	@Column(name="email", nullable = true, length = 45)
	private String email;
	
	@Column(name="mobile", nullable = true, length = 15)
	private String mobile;
	
	@Column(name="phone", nullable = true, length = 15)
	private String phone;
	
	@OneToOne(mappedBy = "person", cascade =  CascadeType.REMOVE)
	private User user;
	
    //@OneToMany(mappedBy="proprietary", fetch=FetchType.EAGER)
    @OneToMany(mappedBy="proprietary")
	private List<House> housesList = new ArrayList<House>();
	
    @OneToMany(mappedBy="houseHolder")
	private List<Contract> householderContractList = new ArrayList<Contract>();
    
 
    @OneToMany(mappedBy="renter")
	private List<Contract> renterContractList = new ArrayList<Contract>();
    
    @OneToMany(mappedBy="guarantor")
	private List<Contract> guarantorContractList = new ArrayList<Contract>();
    

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<House> getHousesList() {
		return housesList;
	}

	public void setHousesList(List<House> housesList) {
		this.housesList = housesList;
	}

	public List<Contract> getHouseholderContractList() {
		return householderContractList;
	}

	public void setHouseholderContractList(List<Contract> householderContractList) {
		this.householderContractList = householderContractList;
	}

	public List<Contract> getRenterContractList() {
		return renterContractList;
	}

	public void setRenterContractList(List<Contract> renterContractList) {
		this.renterContractList = renterContractList;
	}

	public List<Contract> getGuarantorContractList() {
		return guarantorContractList;
	}

	public void setGuarantorContractList(List<Contract> guarantorContractList) {
		this.guarantorContractList = guarantorContractList;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDniType() {
		return dniType;
	}

	public void setDniType(String dniType) {
		this.dniType = dniType;
	}

	public String getDniCode() {
		return dniCode;
	}

	public void setDniCode(String dniCode) {
		this.dniCode = dniCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}
	
	@Override
	public String toString() {
	  return "Person: firstName" + this.firstName;
	}
    
	
}
