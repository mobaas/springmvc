/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.domain;

import java.io.Serializable;
import java.util.Date;

import com.mobaas.smfx.util.DateUtil;

/**
 * 管理日志
 * @author billy (billy_zh@126.com)
 *
 */
public class AdminLog implements Serializable{
	
	private int logId;
	private String opName;
	private String category;
	private String loginName;
	private String logIp;
	private int duration;
	private String result;
	private int adminId;
	private Date logTime;

	public String getLogTimeStr() {
		return DateUtil.toString(logTime);
	}

	public AdminLog() {
		logTime = new Date();
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getLogId() {
		return logId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogIp() {
		return logIp;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
}
