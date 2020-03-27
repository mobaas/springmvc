/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.domain;

import java.io.Serializable;
import java.util.Date;

import com.mobaas.smfx.util.DateUtil;

/**
 * 管理员
 * @author billy (billy_zh@126.com)
 *
 */
public class Administrator implements Serializable {

	private int adminId;
	private String loginName;
	private String password;
	private String realname;
	private String cellphone;
	private String email;
	private int manager;
	private Date addTime;
	private String permissions;

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getRealname() {
		return realname;
	}
	
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public String getAddTimeStr() {
		return DateUtil.getDateTimeStr(addTime);
	}
	
	public String getAddDateStr() {
		return DateUtil.getDateStr(addTime);
	}
}
