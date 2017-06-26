package com.cts.localtour.entity;
// Generated 2017-6-26 11:03:25 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SupplierBusinessTable generated by hbm2java
 */
@Entity
@Table(name = "supplierBusinessTable", catalog = "localtour")
public class SupplierBusinessTable implements java.io.Serializable {

	private Integer id;
	private int supplierId;
	private int supplierScopeId;

	public SupplierBusinessTable() {
	}

	public SupplierBusinessTable(int supplierId, int supplierScopeId) {
		this.supplierId = supplierId;
		this.supplierScopeId = supplierScopeId;
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

	@Column(name = "supplierID", nullable = false)
	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplierScopeID", nullable = false)
	public int getSupplierScopeId() {
		return this.supplierScopeId;
	}

	public void setSupplierScopeId(int supplierScopeId) {
		this.supplierScopeId = supplierScopeId;
	}

}
