package com.cts.localtour.entity;
// Generated 2016-8-12 11:12:23 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleTable generated by hbm2java
 */
@Entity
@Table(name = "roleTable", catalog = "localtour")
public class RoleTable implements java.io.Serializable {

	private Integer id;
	private String roleName;
	private String roleComment;
	private Boolean enable;
	private int roleId;
	private String roleCreator;

	public RoleTable() {
	}

	public RoleTable(String roleName, int roleId) {
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public RoleTable(String roleName, String roleComment, Boolean enable, int roleId, String roleCreator) {
		this.roleName = roleName;
		this.roleComment = roleComment;
		this.enable = enable;
		this.roleId = roleId;
		this.roleCreator = roleCreator;
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

	@Column(name = "roleName", nullable = false, length = 16)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleComment", length = 65535)
	public String getRoleComment() {
		return this.roleComment;
	}

	public void setRoleComment(String roleComment) {
		this.roleComment = roleComment;
	}

	@Column(name = "enable")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "roleId", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleCreator", length = 45)
	public String getRoleCreator() {
		return this.roleCreator;
	}

	public void setRoleCreator(String roleCreator) {
		this.roleCreator = roleCreator;
	}

}
