/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobaas.smfx.PageList;
import com.mobaas.smfx.Pager;
import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.domain.Administrator;
import com.mobaas.smfx.service.AdminService;
import com.mobaas.smfx.util.CryptoUtil;
import com.mobaas.smfx.web.JsonResult;
import com.mobaas.smfx.web.aop.OpCategory;
import com.mobaas.smfx.web.aop.OperateLog;


/**
 * 
 * @author billy (billy_zh@126.com)
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("list")
	public ModelAndView list(@RequestParam(value="keyword", required=false, defaultValue="")String keyword) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		Pager pager = new Pager(1);
		
		PageList<Administrator> plist = adminService.selectAdminList(keyword, pager);
		pager.setRowCount(plist.getTotal());
		mv.addObject("adminlist", plist.getList());
		mv.addObject("pager", pager);
		mv.addObject("keyword", keyword);
		
		mv.setViewName("/admin/list");
		
		return mv;
	}

	@GetMapping("loglist")
	public ModelAndView loglist(@RequestParam(value="category", required=false, defaultValue="")String category,
			@RequestParam(value="page", required=false, defaultValue="1")Integer page,
			@RequestParam(value="pagesize", required=false, defaultValue="20")Integer pageSize) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		Pager pager = new Pager(page);
		pager.setPageSize(pageSize);
		
		PageList<AdminLog> plist = adminService.selectAdminLogList(category, pager);
		pager.setRowCount(plist.getTotal());
		mv.addObject("loglist", plist.getList());
		mv.addObject("pager", pager);
		mv.addObject("category", category);
		
		mv.setViewName("/admin/loglist");
		
		return mv;
	}

	@GetMapping("add")
	public String add() throws Exception {
		
		return "/admin/add";
	}

	@PostMapping("add_submit")
	@ResponseBody
	@OperateLog(Category=OpCategory.SYSTEM, Name="添加管理员")
	public JsonResult add_submit(@RequestParam("loginName")String loginName,
			@RequestParam("password")String password,
			@RequestParam("repPasswd")String repPasswd) throws Exception {
		
		if (loginName == null || loginName.equals("")) {
			return JsonResult.error(-1, "登陆名不能为空");
		}
		if (password == null || password.equals("")) {
			return JsonResult.error(-1, "密码不能为空");
		}
		if (repPasswd == null || repPasswd.equals("")) {
			return JsonResult.error(-1, "重复密码不能为空");
		}
		if (!password.equals(repPasswd)) {
			return JsonResult.error(-1, "密码不一致");
		}
		
		Administrator admin = new Administrator();
		admin.setLoginName(loginName);
		admin.setPassword(CryptoUtil.MD5(password));
		adminService.insertAdmin(admin);
		
		return JsonResult.ok();
	}

	@GetMapping("edit")
	public ModelAndView edit(@RequestParam("id")Integer id) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		Administrator admin = adminService.selectAdminById(id);
		mv.addObject("admin", admin);
		mv.setViewName("/admin/edit");
	
		return mv;
	}

	@RequestMapping("edit_submit")
	@ResponseBody
	@OperateLog(Category=OpCategory.SYSTEM, Name="编辑管理员")
	public JsonResult edit_submit(@RequestParam("id")Integer id,
			@RequestParam("newPasswd")String newPasswd) throws Exception {

		Administrator originAdmin = adminService.selectAdminById(id);
		if (originAdmin != null) {
			
			if (!(newPasswd == null || newPasswd.equals(""))) {
				originAdmin.setPassword(CryptoUtil.MD5(newPasswd));
			}

			int n = adminService.updateAdmin(originAdmin);
			return JsonResult.ok();
			
		} else {
			return JsonResult.error(-1, "找不到记录。");
		}

	}
	
	@GetMapping(value="delete")
	public ModelAndView delete(@RequestParam("id")Integer id) throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("/admin/delete");
		return mv;
	}
	
	@PostMapping(value="delete")
	@ResponseBody
	@OperateLog(Category=OpCategory.SYSTEM, Name="删除管理员")
	public JsonResult delete(@RequestParam("id")Integer id,
			@RequestParam(value="confirm", required=false)String confirm) throws Exception {

		if ("yes".equals(confirm)) {
			
			int n = adminService.deleteAdmin(id);
			
			return JsonResult.ok();
		} else {

			return JsonResult.error(-1, "参数不正确。");
		}
	}
}
