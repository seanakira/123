package com.cts.localtour.entity;
// Generated 2016-5-19 14:40:25 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PrevilegeTable generated by hbm2java
 */
@Entity
@Table(name = "previlegeTable", catalog = "localtour")
public class PrevilegeTable implements java.io.Serializable {

	private int previlegeId;
	private String previlegeName;
	private Boolean enable;

	public PrevilegeTable() {
	}

	public PrevilegeTable(int previlegeId) {
		this.previlegeId = previlegeId;
	}

	public PrevilegeTable(int previlegeId, String previlegeName, Boolean enable) {
		this.previlegeId = previlegeId;
		this.previlegeName = previlegeName;
		this.enable = enable;
	}

	@Id

	@Column(name = "previlegeId", unique = true, nullable = false)
	public int getPrevilegeId() {
		return this.previlegeId;
	}

	public void setPrevilegeId(int previlegeId) {
		this.previlegeId = previlegeId;
	}

	@Column(name = "previlegeName", length = 45)
	public String getPrevilegeName() {
		return this.previlegeName;
	}

	public void setPrevilegeName(String previlegeName) {
		this.previlegeName = previlegeName;
	}

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}