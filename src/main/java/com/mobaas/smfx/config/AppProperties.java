/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mobaas.smfx.util.PropertiesUtil;

/**
 * 应用属性
 * @author billy (billy_zh@126.com)
 *
 */
@Component
public class AppProperties implements InitializingBean {

	@Autowired
	private Environment env;
	
	private Properties properties;
	
	private List<String> keyList;

	public Collection<String> keys() {
		if (keyList != null)
			return keyList;
		
		keyList = new ArrayList<>();
		
		for (Object key : properties.keySet()) {
			keyList.add((String)key);
		}
		
		return keyList;
	}
	
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}
	
	public String getString(String name) {
		return properties.getProperty(name);
	}
	
	public int getInteger(String name) {
		String value = properties.getProperty(name) ;
		if (value == null)
			return 0;
		
		return Integer.parseInt( value );
	}
	
	public boolean getBoolean(String name) {
		String value = properties.getProperty(name);
		if (value == null)
			return false;
		
		value = value.toLowerCase();
		
		return "true".equals(value) || "yes".equals(value);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		properties = PropertiesUtil.load(env);
	}
}
