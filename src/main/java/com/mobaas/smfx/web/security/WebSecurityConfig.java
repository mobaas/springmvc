/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.service.AdminService;
import com.mobaas.smfx.web.aop.OpCategory;

/**
 * Security 配置类
 * @author billy (billy_zh@126.com)
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AdminService adminService;
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.anyRequest()
        		.authenticated()
        		//.antMatchers("/admin/**").access("hasAuthority('ROLE_ADMIN_VIEW')")  // 需要ADMIN权限
        		//.antMatchers("/info/**").access("hasAuthority('ROLE_INFO_VIEW')")  // 需要INFO权限
        		.and()
	        .formLogin()
	        	.usernameParameter("username")
	        	.passwordParameter("password")
	        	.loginPage("/login.do")
	        	.loginProcessingUrl("/login_submit.do")
	        	.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
							Authentication authentication) throws IOException, ServletException {
			
						log("登录成功", authentication.getName(), request);
						
						super.setDefaultTargetUrl("/index.do");
						super.onAuthenticationSuccess(request, response, authentication);
					}
	        	})
	        	.failureHandler(new SimpleUrlAuthenticationFailureHandler() {
	        		
					@Override
	        		public void onAuthenticationFailure(HttpServletRequest request,
	        				HttpServletResponse response, AuthenticationException exception)
	        				throws IOException, ServletException {

						log("登录失败", "", request);
						
						super.setDefaultFailureUrl("/login.do?error");
						super.onAuthenticationFailure(request, response, exception);
	        		}
	        	})
	        	.permitAll()
	        	.and()
	        .rememberMe()
	        	.rememberMeCookieName("mobaas-admin")
	        	.rememberMeParameter("remember")
	        	.alwaysRemember(true)
	        	.and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout.do", "GET"))
	        	.addLogoutHandler(new LogoutHandler() {

					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) {

						log("退出登录", "", request);
					}
	        		
	        	})
	        	.and()
	        .sessionManagement()
	        	.invalidSessionUrl("/login.do");
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    private void log(String opName, String username, HttpServletRequest request) {
    	AdminLog log = new AdminLog();
		log.setCategory( OpCategory.SYSTEM.toString() );
		log.setOpName( opName );
		log.setLogTime(new Date());
		log.setLogIp(request.getRemoteAddr());
		log.setLoginName( username );
		
		adminService.insertAdminLog(log);
    }
    
}
