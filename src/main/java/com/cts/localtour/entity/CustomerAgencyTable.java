package com.cts.localtour.entity;
// Generated 2016-8-16 11:30:27 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerAgencyTable generated by hbm2java
 */
@Entity
@Table(name = "customerAgencyTable", catalog = "localtour")
public class CustomerAgencyTable implements java.io.Serializable {

	private Integer id;
	private String customerAgencyName;
	private int regionId;
	private String phone;
	private boolean enable;

	public CustomerAgencyTable() {
	}

	public CustomerAgencyTable(String customerAgencyName, int regionId, String phone, boolean enable) {
		this.customerAgencyName = customerAgencyName;
		this.regionId = regionId;
		this.phone = phone;
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

	@Column(name = "customerAgencyName", nullable = false, length = 20)
	public String getCustomerAgencyName() {
		return this.customerAgencyName;
	}

	public void setCustomerAgencyName(String customerAgencyName) {
		this.customerAgencyName = customerAgencyName;
	}

	@Column(name = "regionID", nullable = false)
	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Column(name = "phone", nullable = false, length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
