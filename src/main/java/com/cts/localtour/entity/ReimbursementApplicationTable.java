package com.cts.localtour.entity;
// Generated 2017-3-27 13:53:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReimbursementApplicationTable generated by hbm2java
 */
@Entity
@Table(name = "reimbursementApplicationTable", catalog = "localtour")
public class ReimbursementApplicationTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private int deptId;
	private int status;

	public ReimbursementApplicationTable() {
	}

	public ReimbursementApplicationTable(int tourId, int deptId, int status) {
		this.tourId = tourId;
		this.deptId = deptId;
		this.status = status;
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

	@Column(name = "tourId", nullable = false)
	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Column(name = "deptId", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
