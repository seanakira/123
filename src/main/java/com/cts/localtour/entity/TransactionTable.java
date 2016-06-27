package com.cts.localtour.entity;
// Generated 2016-6-24 18:43:01 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransactionTable generated by hbm2java
 */
@Entity
@Table(name = "transactionTable", catalog = "localtour")
public class TransactionTable implements java.io.Serializable {

	private Integer id;
	private String tourNo;
	private int userId;

	public TransactionTable() {
	}

	public TransactionTable(String tourNo, int userId) {
		this.tourNo = tourNo;
		this.userId = userId;
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

	@Column(name = "tourNO", nullable = false, length = 16)
	public String getTourNo() {
		return this.tourNo;
	}

	public void setTourNo(String tourNo) {
		this.tourNo = tourNo;
	}

	@Column(name = "userID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
