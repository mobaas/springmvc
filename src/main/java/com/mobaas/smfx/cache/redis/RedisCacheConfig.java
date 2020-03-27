/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.cache.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.mobaas.smfx.cache.CacheCondition;
import com.mobaas.smfx.cache.CacheCondition.Redis;
import com.mobaas.smfx.config.AppProperties;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 配置类
 * @author billy (billy_zh@126.com)
 *
 */
@Configuration
@Conditional(CacheCondition.Redis.class)
public class RedisCacheConfig {

	@Autowired
	private AppProperties appProperties;
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		SimpleCacheManager scm = new SimpleCacheManager();
		
		List<Cache> caches = new ArrayList<>();
		caches.add( new RedisCacheImpl("infoCache", redisTemplate) );
		caches.add( new RedisCacheImpl("adminlogCache", redisTemplate) );
		
		scm.setCaches(caches);
		
		return scm;
	}
	
	@Bean 
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		return template;
		
	}
	
	@Bean
	public RedisConnectionFactory connectionFactory() {
		
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		poolCfg.setMaxIdle( appProperties.getInteger("redis.maxIdle") );
		poolCfg.setMaxTotal( appProperties.getInteger("redis.maxTotal") );
		poolCfg.setMaxWaitMillis( appProperties.getInteger("redis.maxWaitMillis") );
		poolCfg.setTestOnBorrow( appProperties.getBoolean("redis.testOnBorrow") );
		
		JedisConnectionFactory factory = new JedisConnectionFactory(poolCfg);
		factory.setHostName( appProperties.getString("redis.host") );
		factory.setPort( appProperties.getInteger("redis.port") );
		factory.setPassword( appProperties.getString("redis.password") );
		
		// 初始化连接pool
		factory.afterPropertiesSet();
 
		return factory;
	}

}
