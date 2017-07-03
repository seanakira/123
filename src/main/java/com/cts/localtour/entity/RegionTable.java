package com.cts.localtour.entity;
// Generated 2017-7-3 13:59:12 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RegionTable generated by hbm2java
 */
@Entity
@Table(name = "regionTable", catalog = "localtour")
public class RegionTable implements java.io.Serializable {

	private Integer id;
	private String regionName;
	private boolean enable;

	public RegionTable() {
	}

	public RegionTable(String regionName, boolean enable) {
		this.regionName = regionName;
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

	@Column(name = "regionName", nullable = false, length = 16)
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
