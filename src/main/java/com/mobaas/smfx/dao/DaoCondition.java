/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao;

import java.util.Properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.mobaas.smfx.util.PropertiesUtil;

/**
 * Dao 条件注解类
 * @author billy (billy_zh@126.com)
 *
 * 根据属性配置中dao.provider给定的值来生成Dao相关的Bean
 * 
 */
public abstract class DaoCondition implements Condition {

	private static final String DAO_PROVIDER = "dao.provider";
	private static final String MYBATIS_DAO = "mybatis";
	private static final String HIBERNATE_DAO = "hibernate";
	
	protected abstract String getName();
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		Properties props = PropertiesUtil.load(context.getEnvironment());
		
		if ( props.containsKey(DAO_PROVIDER) ) {
			return getName().equals( props.getProperty(DAO_PROVIDER) );
		}
		
		return MYBATIS_DAO.equals(getName());  // default
	}
	
	/**
	 * MyBatis 条件注解
	 * @author billy
	 *
	 */
	public final static class MyBatis extends DaoCondition {
		
		@Override
		public String getName() {
			return MYBATIS_DAO;
		}
	}
	
	/**
	 * Hibernate 条件注解
	 * @author billy
	 *
	 */
	public final static class Hibernate extends DaoCondition {
		
		@Override
		public String getName() {
			return HIBERNATE_DAO;
		}
	};
	
	
}
