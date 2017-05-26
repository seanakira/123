package com.cts.localtour.entity;
// Generated 2017-5-25 17:25:22 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SupplierContentTable generated by hbm2java
 */
@Entity
@Table(name = "supplierContentTable", catalog = "localtour")
public class SupplierContentTable implements java.io.Serializable {

	private Integer id;
	private String contentName;
	private int supplierScopeId;
	private boolean enable;

	public SupplierContentTable() {
	}

	public SupplierContentTable(String contentName, int supplierScopeId, boolean enable) {
		this.contentName = contentName;
		this.supplierScopeId = supplierScopeId;
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

	@Column(name = "contentName", nullable = false, length = 16)
	public String getContentName() {
		return this.contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	@Column(name = "supplierScopeID", nullable = false)
	public int getSupplierScopeId() {
		return this.supplierScopeId;
	}

	public void setSupplierScopeId(int supplierScopeId) {
		this.supplierScopeId = supplierScopeId;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
