/**
 * Argentina Rental Housing Backend (Management System for building rentals)
 * Sistema para gesti�n de alquileres inmobiliarios
 * License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
 * Author: Dario Palminio
 */
package com.daro.rental.housing.model.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "contract")
public class Contract implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contract_id", nullable = false)
	private Long id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, targetEntity = House.class)
	@JoinColumn(name = "house_id")
	private House house;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, targetEntity = Person.class)
	@JoinColumn(name = "householder_id")
	private Person houseHolder;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, targetEntity = Person.class)
	@JoinColumn(name = "renters_id")
	private Person renter;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, targetEntity = Person.class)
	@JoinColumn(name = "guarantor_id")
	private Person guarantor;

	@Column(name = "date_start", nullable = false)
	private Date dateStart;

	@Column(name = "date_end", nullable = false)
	private Date dateEnd;

	@Column(name = "days_to_expire", nullable = false)
	private Integer daysToExpire;
	
	@Column(name = "active")
	private Boolean active;

	@Column(name = "must_pay_expenses")
	private Boolean mustPayExpenses; 
	
	@Column(name = "must_pay_services")
	private Boolean mustPayServices;
	
	@Column(name = "deposit_of_security_total")
	private Double depositOfSecurityTotal;
	
	@Column(name = "deposit_of_security_returned")
	private Boolean depositOfSecurityReturned;
	
	@Column(name = "contract_compliance")
	private Boolean contractCompliance;
	
	@OneToMany(mappedBy = "contract")
	private List<Pay> paysList = new ArrayList<Pay>();

	public List<Pay> getPaysList() {
		return paysList;
	}

	public Double getDepositOfSecurityTotal() {
		return depositOfSecurityTotal;
	}

	public void setDepositOfSecurityTotal(Double depositOfSecurityTotal) {
		this.depositOfSecurityTotal = depositOfSecurityTotal;
	}

	public void setPaysList(List<Pay> paysList) {
		this.paysList = paysList;
	}

	public Integer getDaysToExpire() {
		return daysToExpire;
	}

	public void setDaysToExpire(Integer daysToExpire) {
		this.daysToExpire = daysToExpire;
	}

	public Boolean getMustPayExpenses() {
		return mustPayExpenses;
	}

	public void setMustPayExpenses(Boolean mustPayExpenses) {
		this.mustPayExpenses = mustPayExpenses;
	}

	public Boolean getMustPayServices() {
		return mustPayServices;
	}

	public void setMustPayServices(Boolean mustPayServices) {
		this.mustPayServices = mustPayServices;
	}

	public Boolean getDepositOfSecurityReturned() {
		return depositOfSecurityReturned;
	}

	public void setDepositOfSecurityReturned(Boolean depositOfSecurityReturned) {
		this.depositOfSecurityReturned = depositOfSecurityReturned;
	}

	public Boolean getContractCompliance() {
		return contractCompliance;
	}

	public void setContractCompliance(Boolean contractCompliance) {
		this.contractCompliance = contractCompliance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Person getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(Person houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Person getRenter() {
		return renter;
	}

	public void setRenter(Person renter) {
		this.renter = renter;
	}

	public Person getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(Person guarantor) {
		this.guarantor = guarantor;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}



	

}
