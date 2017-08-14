package com.cts.localtour.entity;
// Generated 2017-7-10 15:45:00 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TourTypeTable generated by hbm2java
 */
@Entity
@Table(name = "tourTypeTable", catalog = "localtour")
public class TourTypeTable implements java.io.Serializable {

	private Integer id;
	private String tourTypeName;
	private boolean enable;

	public TourTypeTable() {
	}

	public TourTypeTable(String tourTypeName, boolean enable) {
		this.tourTypeName = tourTypeName;
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

	@Column(name = "tourTypeName", nullable = false, length = 10)
	public String getTourTypeName() {
		return this.tourTypeName;
	}

	public void setTourTypeName(String tourTypeName) {
		this.tourTypeName = tourTypeName;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
