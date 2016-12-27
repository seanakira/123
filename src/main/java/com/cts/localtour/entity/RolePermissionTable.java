package com.cts.localtour.entity;
// Generated 2016-12-27 10:39:04 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RolePermissionTable generated by hbm2java
 */
@Entity
@Table(name = "rolePermissionTable", catalog = "localtour")
public class RolePermissionTable implements java.io.Serializable {

	private Integer id;
	private int roleId;
	private int permissionId;

	public RolePermissionTable() {
	}

	public RolePermissionTable(int roleId, int permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
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

	@Column(name = "permissionID", nullable = false)
	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}
