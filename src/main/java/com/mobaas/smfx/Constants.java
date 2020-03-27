/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx;

/**
 * 常数
 * @author billy (billy_zh@126.com)
 * 
 */
public class Constants {

	public static final String ADMIN_NOT_EXISTS = "用户不存在！";
	
	/**
	 * Root组件包
	 */
	public static final String COMPONENT_PACKAGE = "com.mobaas.smfx";
	
	/**
	 * WebMvc组件包
	 */
	public static final String CONTROLLER_PACKAGE = "com.mobaas.smfx.web";
	
	/**
	 * MyBatis Mapper类包
	 */
	public static final String MYBATIS_MAPPER_PACKAGE = "com.mobaas.smfx.dao.mybatis";
	
	/**
	 * MyBatis Mapper Xml文件位置
	 */
	public static final String MYBATIS_MAPPER_LOCATION = "classpath:mapper/mybatis/*.xml";

	/**
	 * Hibernate Xml文件位置
	 */
	public static final String HIBERNATE_MAPPER_LOCATION = "classpath:mapper/hibernate/*.xml";
	
	/**
	 * JSP文件位置
	 */
	public static final String VIEW_PREFIX = "/WEB-INF/jsp/";
	public static final String VIEW_SUFFIX = ".jsp";
	
	/**
	 * 操作日志切点
	 */
	public static final String LOG_POINTCUT = "execution (@com.mobaas.smfx.web.aop.OperateLog * com.mobaas.smfx.web.controller.*.*(..))";
}
