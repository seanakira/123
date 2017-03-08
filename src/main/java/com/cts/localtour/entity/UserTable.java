package com.cts.localtour.entity;
// Generated 2017-3-8 13:58:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserTable generated by hbm2java
 */
@Entity
@Table(name = "userTable", catalog = "localtour")
public class UserTable implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String realName;
	private String pwd;
	private int deptId;
	private String position;
	private String phone;
	private String qq;
	private String email;
	private boolean weiXinMessageSwitch;
	private String headImg;
	private String dataDeptIds;
	private boolean enable;

	public UserTable() {
	}

	public UserTable(String userName, String realName, String pwd, int deptId, boolean weiXinMessageSwitch,
			String dataDeptIds, boolean enable) {
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.deptId = deptId;
		this.weiXinMessageSwitch = weiXinMessageSwitch;
		this.dataDeptIds = dataDeptIds;
		this.enable = enable;
	}

	public UserTable(String userName, String realName, String pwd, int deptId, String position, String phone, String qq,
			String email, boolean weiXinMessageSwitch, String headImg, String dataDeptIds, boolean enable) {
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.deptId = deptId;
		this.position = position;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
		this.weiXinMessageSwitch = weiXinMessageSwitch;
		this.headImg = headImg;
		this.dataDeptIds = dataDeptIds;
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

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "realName", nullable = false, length = 10)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "pwd", nullable = false, length = 10)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "deptID", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Column(name = "position", length = 16)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "phone", length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "qq", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "weiXinMessageSwitch", nullable = false)
	public boolean isWeiXinMessageSwitch() {
		return this.weiXinMessageSwitch;
	}

	public void setWeiXinMessageSwitch(boolean weiXinMessageSwitch) {
		this.weiXinMessageSwitch = weiXinMessageSwitch;
	}

	@Column(name = "headImg", length = 50)
	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Column(name = "dataDeptIds", nullable = false, length = 100)
	public String getDataDeptIds() {
		return this.dataDeptIds;
	}

	public void setDataDeptIds(String dataDeptIds) {
		this.dataDeptIds = dataDeptIds;
	}

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
