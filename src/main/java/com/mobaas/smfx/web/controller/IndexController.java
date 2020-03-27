/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author billy (billy_zh@126.com)
 *
 */
@Controller
public class IndexController {

	@Autowired
	private HttpServletRequest request;
	
	private String getCsrfToken() {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return csrfToken.getToken();
	}
	
	@RequestMapping("index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("login")
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		
	    mv.addObject("token", getCsrfToken());
	    mv.setViewName("login");
		return mv;
	}

}
