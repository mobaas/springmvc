/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx;

/**
 * 分页
 * @author billy  (billy_zh@126.com)
 *
 */
public class Pager {

	private int pageNo;
	private int pageSize = 20;
	private int rowCount;
	private int pageCount;
	private String queryString;
	
	public Pager() {
		this.pageNo = 1;
	}
	
	public Pager(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public Pager(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.pageCount = (rowCount - 1) / pageSize + 1;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public int getOffset() {
	
		if (pageNo == 0)
			return 0;
		
		return (pageNo - 1) * pageSize;
	}
	
	public String getCacheKey() {
		return String.format("_%d_%d", pageNo, pageSize);
	}
}
