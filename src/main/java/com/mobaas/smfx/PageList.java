/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx;

import java.util.List;

/**
 * 分页List
 * @author billy  (billy_zh@126.com)
 *
 * @param <T>
 */
public class PageList<T> {

	private int total;
	private List<T> list;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
