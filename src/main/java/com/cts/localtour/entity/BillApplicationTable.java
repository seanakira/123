package com.cts.localtour.entity;
// Generated 2016-11-29 15:16:41 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BillApplicationTable generated by hbm2java
 */
@Entity
@Table(name = "billApplicationTable", catalog = "localtour")
public class BillApplicationTable implements java.io.Serializable {

	private Integer id;
	private int supplierId;
	private int deptId;

	public BillApplicationTable() {
	}

	public BillApplicationTable(int supplierId, int deptId) {
		this.supplierId = supplierId;
		this.deptId = deptId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "supplierId", nullable = false)
	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "deptId", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

}