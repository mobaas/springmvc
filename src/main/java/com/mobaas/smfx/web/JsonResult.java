/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web;

/**
 * Json结果类
 * @author billy  (billy_zh@126.com)
 *
 * 与@ResponseBody配合使用。
 * 
 */
public class JsonResult {

	private int errCode;
	private String errMsg;
	
	public int getErrCode() {
		return errCode;
	}
	
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public static JsonResult ok() {
		JsonResult jr = new JsonResult();
		return jr;
	}
	
	public static JsonResult error(int code, String msg) {
		JsonResult jr = new JsonResult();
		jr.setErrCode(code);
		jr.setErrMsg(msg);
		return jr;
	}
}
