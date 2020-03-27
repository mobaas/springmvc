/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.mobaas.smfx.Constants;
import com.mobaas.smfx.dao.DaoCondition;

/**
 * MyBatis 配置类
 * @author billy (billy_zh@126.com)
 *
 */
@Configuration
@EnableTransactionManagement
@Conditional(DaoCondition.MyBatis.class)
@MapperScan(Constants.MYBATIS_MAPPER_PACKAGE)
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Bean
    @Scope("prototype")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
	@Bean
	public SqlSessionFactory sqlSessionFactory( DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setMapperLocations( new PathMatchingResourcePatternResolver()
                .getResources( Constants.MYBATIS_MAPPER_LOCATION ) );
		factoryBean.setDataSource(dataSource);
		factoryBean.afterPropertiesSet();
		
		return factoryBean.getObject();
	}
	
	private DataSourceTransactionManager txManager;

	@Bean
	public DataSourceTransactionManager transactionManager( DataSource dataSource) {
		txManager = new DataSourceTransactionManager(dataSource);
		return txManager;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager;
	}
}
