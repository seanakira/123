package com.cts.localtour.entity;
// Generated 2016-12-22 12:25:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReimbursementTable generated by hbm2java
 */
@Entity
@Table(name = "reimbursementTable", catalog = "localtour")
public class ReimbursementTable implements java.io.Serializable {

	private Integer id;
	private int tourId;
	private float headAmount;

	public ReimbursementTable() {
	}

	public ReimbursementTable(int tourId, float headAmount) {
		this.tourId = tourId;
		this.headAmount = headAmount;
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

	@Column(name = "headAmount", nullable = false, precision = 12, scale = 0)
	public float getHeadAmount() {
		return this.headAmount;
	}

	public void setHeadAmount(float headAmount) {
		this.headAmount = headAmount;
	}

}
