package com.cts.localtour.entity;
// Generated 2016-10-10 10:01:17 by Hibernate Tools 3.4.0.CR1

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

	private int id;
	private String previlegeName;
	private Boolean enable;
	private int previlegeId;

	public PrevilegeTable() {
	}

	public PrevilegeTable(int id, int previlegeId) {
		this.id = id;
		this.previlegeId = previlegeId;
	}

	public PrevilegeTable(int id, String previlegeName, Boolean enable, int previlegeId) {
		this.id = id;
		this.previlegeName = previlegeName;
		this.enable = enable;
		this.previlegeId = previlegeId;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "previlegeName", length = 16)
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

	@Column(name = "previlegeId", nullable = false)
	public int getPrevilegeId() {
		return this.previlegeId;
	}

	public void setPrevilegeId(int previlegeId) {
		this.previlegeId = previlegeId;
	}

}
