/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mobaas.smfx.PageList;
import com.mobaas.smfx.Pager;
import com.mobaas.smfx.domain.Info;
import com.mobaas.smfx.service.InfoService;
import com.mobaas.smfx.web.aop.OpCategory;
import com.mobaas.smfx.web.aop.OperateLog;

/**
 * 资讯控制器类
 * @author billy (billy_zh@126.com)
 *
 */
@Controller
@RequestMapping("/info")
public class InfoController {

	@Autowired
	private InfoService infoService;

	@Autowired
	private HttpServletRequest request;
	
	private String getCsrfToken() {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return csrfToken.getToken();
	}
	
	@GetMapping("list")
	public ModelAndView list(
			@RequestParam(value="keyword", required=false, defaultValue="")String keyword,
			@RequestParam(value="page", required=false, defaultValue="1")Integer page,
			@RequestParam(value="pagesize", required=false, defaultValue="20")Integer pageSize) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		Pager pager = new Pager(page);
		pager.setPageSize(pageSize);
		
		PageList<Info> plist = infoService.selectInfoList(keyword, pager);
		pager.setRowCount(plist.getTotal());
		mv.addObject("infolist", plist.getList());
		mv.addObject("keyword", keyword);
		mv.addObject("pager", pager);
		
		return mv;
	}

	@GetMapping("add")
	public ModelAndView add() throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.addObject("token", getCsrfToken());
		mv.setViewName("/info/add");
		
		return mv;
	}

	@RequestMapping("add_submit")
	@OperateLog(Category=OpCategory.INFO, Name="添加资讯")
	public String add_submit(@RequestParam("title")String title,
			@RequestParam("content")String content) throws Exception {
		
		Info info = new Info();
		info.setTitle(title);
		info.setContent(content);
		info.setPubTime(new Date());

		infoService.insertInfo(info);
		
		return "redirect:/info/list.do";
	}

	@GetMapping("edit")
	public ModelAndView edit(@RequestParam("id")Integer id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		Info info = infoService.selectInfoById(id);
		mv.addObject("info", info);
		mv.addObject("token", getCsrfToken());
		mv.setViewName("/info/edit");
		
		return mv;
	}

	@RequestMapping("edit_submit")
	@OperateLog(Category=OpCategory.INFO, Name="编辑资讯")
	public String edit_submit(@RequestParam("id")Integer id,
			@RequestParam("title")String title,
			@RequestParam("content")String content) throws Exception {

		Info info = infoService.selectInfoById(id);
		info.setTitle( title );
		info.setContent( content );
		
		infoService.updateInfo(info);

		return "redirect:/info/list.do";
	}
}
