package com.cts.localtour.entity;
// Generated 2016-11-8 16:41:17 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusinessTypeTable generated by hbm2java
 */
@Entity
@Table(name = "businessTypeTable", catalog = "localtour")
public class BusinessTypeTable implements java.io.Serializable {

	private Integer id;
	private String businessTypeName;
	private boolean enable;

	public BusinessTypeTable() {
	}

	public BusinessTypeTable(String businessTypeName, boolean enable) {
		this.businessTypeName = businessTypeName;
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

	@Column(name = "businessTypeName", nullable = false, length = 8)
	public String getBusinessTypeName() {
		return this.businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
