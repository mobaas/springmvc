/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

/**
 * EhCache 配置类
 * @author billy  (billy_zh@126.com)
 *
 */
@Configuration
@Conditional(CacheCondition.EhCache.class)
public class EhCacheConfig {

	@Bean
    public EhCacheCacheManager cacheCacheManager(net.sf.ehcache.CacheManager cacheManager){
        return new EhCacheCacheManager(cacheManager);
    }

	@Bean
	public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation( new ClassPathResource("ehcache.xml") );
        return ehCacheManagerFactoryBean;
    }
	
}
