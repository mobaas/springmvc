/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.service;

import com.mobaas.smfx.PageList;
import com.mobaas.smfx.Pager;
import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.domain.Administrator;

/**
 * 管理员服务接口
 * @author billy (billy_zh@126.com)
 *
 */
public interface AdminService {

	/**
	 */
	Administrator selectAdminById(int adminId);
	
	/**
	 */
	Administrator selectAdminByName(String loginname);

	/**
	 */
	Administrator selectAdminByNameAndPassword(String loginName, String password);

	/**
	 */
	void insertAdmin(Administrator user);

	/**
	 * @return 
	 */
	int updateAdmin(Administrator user);

	/**
	 * @return 
	 */
	int deleteAdmin(int adminId);

	/**
	 */
	PageList<Administrator> selectAdminList(String keyword, Pager pager);

	/**
	 */
	void insertAdminLog(AdminLog log);


	/**
	 */
	PageList<AdminLog> selectAdminLogList(String category, Pager pager);
}
