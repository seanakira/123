package com.cts.localtour.entity;
// Generated 2016-5-19 14:40:25 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserPrevilegeTable generated by hbm2java
 */
@Entity
@Table(name = "userPrevilegeTable", catalog = "localtour")
public class UserPrevilegeTable implements java.io.Serializable {

	private Integer id;
	private Integer userId;
	private Integer previlegeId;
	private String note;
	private Boolean enable;

	public UserPrevilegeTable() {
	}

	public UserPrevilegeTable(Integer userId, Integer previlegeId, String note, Boolean enable) {
		this.userId = userId;
		this.previlegeId = previlegeId;
		this.note = note;
		this.enable = enable;
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

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "previlegeId")
	public Integer getPrevilegeId() {
		return this.previlegeId;
	}

	public void setPrevilegeId(Integer previlegeId) {
		this.previlegeId = previlegeId;
	}

	@Column(name = "note", length = 20)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
