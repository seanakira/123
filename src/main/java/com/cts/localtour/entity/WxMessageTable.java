package com.cts.localtour.entity;
// Generated 2017-5-22 15:24:40 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxMessageTable generated by hbm2java
 */
@Entity
@Table(name = "wxMessageTable", catalog = "localtour")
public class WxMessageTable implements java.io.Serializable {

	private Integer id;
	private String userRealName;
	private String toUser;
	private String url;
	private String text;
	private String success;

	public WxMessageTable() {
	}

	public WxMessageTable(String userRealName, String toUser, String url, String text, String success) {
		this.userRealName = userRealName;
		this.toUser = toUser;
		this.url = url;
		this.text = text;
		this.success = success;
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

	@Column(name = "userRealName", nullable = false, length = 10)
	public String getUserRealName() {
		return this.userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	@Column(name = "toUser", nullable = false, length = 50)
	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	@Column(name = "url", nullable = false, length = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "text", nullable = false, length = 65535)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "success", nullable = false, length = 20)
	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
