/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mobaas.smfx.config.AppConfig;
import com.mobaas.smfx.web.WebConfig;

/**
 * InfoController 测试类
 * @author billy (billy_zh@126.com)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration(name="parent", classes= {AppConfig.class}),
	@ContextConfiguration(name="child", classes= {WebConfig.class})
})
@WebAppConfiguration
public class InfoControllerTest {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "abc123";

    @Autowired
    private Filter springSecurityFilterChain;

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp() throws Exception {

//		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//				.addFilters(springSecurityFilterChain).build();
		
		List<? extends GrantedAuthority> authorites = Arrays.asList(
				new SimpleGrantedAuthority("ROLE_SYSTEM_VIEW"),
                new SimpleGrantedAuthority("ROLE_INFO_VIEW"));
		
        //如果启用了csrf,别忘了带上with(csrf())
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(get("/").with(csrf()).with(user(USERNAME).password(PASSWORD).authorities(authorites)))
                .addFilters(springSecurityFilterChain)
                .build();
    }

	@Test
	public void testList() throws Exception {
		
		MvcResult ret = mockMvc.perform(get("/info/list"))
			.andExpect(status().isOk())
			.andReturn();
		
	}
}
