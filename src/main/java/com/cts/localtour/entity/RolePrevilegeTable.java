package com.cts.localtour.entity;
// Generated 2016-7-25 10:27:26 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RolePrevilegeTable generated by hbm2java
 */
@Entity
@Table(name = "rolePrevilegeTable", catalog = "localtour")
public class RolePrevilegeTable implements java.io.Serializable {

	private Integer id;
	private int roleId;
	private int previlegeId;

	public RolePrevilegeTable() {
	}

	public RolePrevilegeTable(int roleId, int previlegeId) {
		this.roleId = roleId;
		this.previlegeId = previlegeId;
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

	@Column(name = "roleID", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "previlegeID", nullable = false)
	public int getPrevilegeId() {
		return this.previlegeId;
	}

	public void setPrevilegeId(int previlegeId) {
		this.previlegeId = previlegeId;
	}

}
