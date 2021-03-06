package com.cts.localtour.entity;
// Generated 2017-3-27 13:53:58 by Hibernate Tools 3.4.0.CR1

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
	private Boolean enable;
	private String note;
	private Integer previlegeId;
	private Integer userId;

	public UserPrevilegeTable() {
	}

	public UserPrevilegeTable(Boolean enable, String note, Integer previlegeId, Integer userId) {
		this.enable = enable;
		this.note = note;
		this.previlegeId = previlegeId;
		this.userId = userId;
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

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "note", length = 20)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "previlegeId")
	public Integer getPrevilegeId() {
		return this.previlegeId;
	}

	public void setPrevilegeId(Integer previlegeId) {
		this.previlegeId = previlegeId;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
