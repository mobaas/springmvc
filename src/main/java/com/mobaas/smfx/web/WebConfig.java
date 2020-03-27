/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mobaas.smfx.Constants;

/**
 * Web配置类
 * @author billy (billy_zh@126.com)
 *
 * 需要继承自 WebMvcConfigureAdapter
 * 
 */
@Configuration
@EnableWebMvc // 启用 SpringMVC
@EnableAspectJAutoProxy(proxyTargetClass=true) // 启用 AOP
@ComponentScan(Constants.CONTROLLER_PACKAGE)
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * 配置jsp 视图解析器
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix( Constants.VIEW_PREFIX ); //设置预加载路径/目录
		resolver.setSuffix( Constants.VIEW_SUFFIX ); //设置允许后缀
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	/**
	 * 配置静态资源的处理
	 * 对静态资源的请求转发到容器缺省的servlet,而不使用 DispatcherServlet
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
