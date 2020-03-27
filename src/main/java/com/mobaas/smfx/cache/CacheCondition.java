/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.cache;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.mobaas.smfx.util.PropertiesUtil;

/**
 * Cache 条件注解类
 * @author billy (billy_zh@126.com)
 *
 * 根据属性配置中cache.provider给定的值来生成Cache相关的Bean
 * 
 */
public abstract class CacheCondition implements Condition {

	private static final String CACHE_PROVIDER = "cache.provider";
	private static final String EHCACHE_CACHE = "ehcache";
	private static final String REDIS_CACHE = "redis";
	
	protected abstract String getName();
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		Properties props = PropertiesUtil.load(context.getEnvironment());
		
		if ( props.containsKey(CACHE_PROVIDER) ) {
			return getName().equals( props.getProperty(CACHE_PROVIDER) );
		}
		
		return EHCACHE_CACHE.equals(getName());  // default
	}
	
	private Properties getProperties(Environment env) {
		Properties properties = new Properties();
		try {
			properties.load( new ClassPathResource("application.properties").getInputStream() );
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String profile = env.getProperty("spring.profiles.active"); // 优先从环境变量加载外部配置
		if (profile != null && profile.length() > 0) {
			try {
				Properties props2 = new Properties();
				props2.load( new FileSystemResource("/Users/billyzh/mobaas/smfx/config/application-" + profile + ".properties").getInputStream() );
				for (Object key : props2.keySet()) {
					properties.put(key, props2.get(key));
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			profile = properties.getProperty("spring.profiles.active"); // 加载ClassPath内的配置
			if (profile != null && profile.length() > 0) {
				try {
					Properties props2 = new Properties();
					props2.load( new ClassPathResource("application-" + profile + ".properties").getInputStream() );
					for (Object key : props2.keySet()) {
						properties.put(key, props2.get(key));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return properties;
	}
	
	/**
	 * Redis 条件注解
	 * @author billy
	 *
	 */
	public final static class Redis extends CacheCondition {
		
		@Override
		public String getName() {
			return REDIS_CACHE;
		}
	}
	
	/**
	 * EhCache 条件注解
	 * @author billy
	 *
	 */
	public final static class EhCache extends CacheCondition {
		
		@Override
		public String getName() {
			return EHCACHE_CACHE;
		}
	};
	
	
}
