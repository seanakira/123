package com.cts.localtour.entity;
// Generated 2017-3-27 13:53:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserRoleTable generated by hbm2java
 */
@Entity
@Table(name = "userRoleTable", catalog = "localtour")
public class UserRoleTable implements java.io.Serializable {

	private Integer id;
	private int userId;
	private int roleId;

	public UserRoleTable() {
	}

	public UserRoleTable(int userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
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

	@Column(name = "userID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "roleID", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
