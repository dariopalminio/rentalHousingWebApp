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
@Table(name = "pay")
public class Pay implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pay_id")
	private Long id;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Contract.class )
	@JoinColumn(name="contract_id")
	private Contract contract;
	
	@Column(name="months_date")
	private Date monthsDate;
	
	@Column(name="expires_date")
	private Date expiresDate;
	
	@Column(name="paid")
	Boolean paid;
	
	@Column(name="expired")
	Boolean expired;

	@OneToMany(mappedBy = "pay")
	private List<PayItem> payItemsList = new ArrayList<PayItem>();
	
	public List<PayItem> getPayItemsList() {
		return payItemsList;
	}

	public void setPayItemsList(List<PayItem> payItemsList) {
		this.payItemsList = payItemsList;
	}

	public Date getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Date getMonthsDate() {
		return monthsDate;
	}

	public void setMonthsDate(Date monthsDate) {
		this.monthsDate = monthsDate;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	      
	   

	   
	   
}
