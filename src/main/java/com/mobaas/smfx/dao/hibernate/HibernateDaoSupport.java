/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hibernate Dao类
 * @author billy (billy_zh@126.com)
 *
 */
public class HibernateDaoSupport {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
