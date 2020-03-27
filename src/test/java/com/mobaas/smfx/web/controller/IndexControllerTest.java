/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

import javax.servlet.Filter;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mobaas.smfx.config.AppConfig;
import com.mobaas.smfx.web.WebConfig;

/**
 * IndexController 测试类
 * @author billy (billy_zh@126.com)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration(name="parent", classes= {AppConfig.class}),
	@ContextConfiguration(name="child", classes= {WebConfig.class})
})
@WebAppConfiguration
public class IndexControllerTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "abc123";

    @Autowired
    private Filter springSecurityFilterChain;
    
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	@Test
	public void testIndexNotLogin() throws Exception {
		
		MvcResult ret = mockMvc.perform(get("/index"))
			.andExpect(status().is3xxRedirection())
			.andReturn();
		
	}
	
	@Test
	public void testIndexOnUserLogin() throws Exception {
		
		MvcResult ret = mockMvc.perform(get("/index").with(csrf()).with(user(USERNAME).password(PASSWORD)))
			.andExpect(status().isOk())
			.andReturn();
		
	}
	
	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(formLogin("/login_submit.do").user(USERNAME).password(PASSWORD))
			.andExpect(authenticated().withUsername(USERNAME));
        mockMvc.perform(logout("/logout"));

	}
	
}
