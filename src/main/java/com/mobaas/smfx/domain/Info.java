/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.domain;

import java.util.Date;

/**
 * 资讯
 * @author billy (billy_zh@126.com)
 *
 */
public class Info {

	private int id;
	private String title;
	private int category;
	private String content;
	private Date pubTime;
	private int topNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public int getTopNo() {
		return topNo;
	}
	public void setTopNo(int topNo) {
		this.topNo = topNo;
	}
	
	
}
