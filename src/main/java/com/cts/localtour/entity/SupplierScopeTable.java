package com.cts.localtour.entity;
// Generated 2016-11-9 10:58:35 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SupplierScopeTable generated by hbm2java
 */
@Entity
@Table(name = "supplierScopeTable", catalog = "localtour")
public class SupplierScopeTable implements java.io.Serializable {

	private Integer id;
	private String supplierScopeName;
	private boolean enable;

	public SupplierScopeTable() {
	}

	public SupplierScopeTable(String supplierScopeName, boolean enable) {
		this.supplierScopeName = supplierScopeName;
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

	@Column(name = "supplierScopeName", nullable = false, length = 16)
	public String getSupplierScopeName() {
		return this.supplierScopeName;
	}

	public void setSupplierScopeName(String supplierScopeName) {
		this.supplierScopeName = supplierScopeName;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
