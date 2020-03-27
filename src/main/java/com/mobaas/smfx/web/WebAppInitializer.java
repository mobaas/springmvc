/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mobaas.smfx.config.AppConfig;

/**
 * Web应用初始化类
 * @author billy (billy_zh@126.com)
 *
 * 替代web.xml文件。
 * 
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addFilter("springSecurityFilterChain", 
				new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "*.do");

        // 静态资源映射
        servletContext.getServletRegistration("default").addMapping("/lib/*");
        
        servletContext.addListener(HttpSessionEventPublisher.class);
		super.onStartup(servletContext);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };        //指定Web配置类
	}

	@Override
	protected String[] getServletMappings() {//将 DispatcherServlet 映射到 "/" 路径
		return new String[] { "*.do" };
	}

}
