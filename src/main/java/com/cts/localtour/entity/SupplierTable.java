package com.cts.localtour.entity;
// Generated 2016-12-6 17:34:40 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SupplierTable generated by hbm2java
 */
@Entity
@Table(name = "supplierTable", catalog = "localtour")
public class SupplierTable implements java.io.Serializable {

	private Integer id;
	private String supplierName;
	private int regionId;
	private String supplierContact;
	private String phone;
	private Integer accountPeriod;
	private Date accountDate;
	private boolean enable;

	public SupplierTable() {
	}

	public SupplierTable(String supplierName, int regionId, String phone, boolean enable) {
		this.supplierName = supplierName;
		this.regionId = regionId;
		this.phone = phone;
		this.enable = enable;
	}

	public SupplierTable(String supplierName, int regionId, String supplierContact, String phone, Integer accountPeriod,
			Date accountDate, boolean enable) {
		this.supplierName = supplierName;
		this.regionId = regionId;
		this.supplierContact = supplierContact;
		this.phone = phone;
		this.accountPeriod = accountPeriod;
		this.accountDate = accountDate;
		this.enable = enable;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "supplierName", nullable = false, length = 20)
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "regionID", nullable = false)
	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Column(name = "supplierContact", length = 16)
	public String getSupplierContact() {
		return this.supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	@Column(name = "phone", nullable = false, length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "accountPeriod")
	public Integer getAccountPeriod() {
		return this.accountPeriod;
	}

	public void setAccountPeriod(Integer accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "accountDate", length = 10)
	public Date getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
