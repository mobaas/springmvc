/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.domain.Administrator;

/**
 * 管理员数据访问接口
 * @author billy (billy_zh@126.com)
 *
 */
public interface AdminDao {

	Administrator selectAdminById(int adminId) throws DataAccessException;

	Administrator selectAdminByName(String loginname) throws DataAccessException;

	Administrator selectAdminByNameAndPassword(String loginName, String password)
			throws DataAccessException;

	void insertAdmin(Administrator admin) throws DataAccessException;

	int updateAdmin(Administrator admin) throws DataAccessException;

	int deleteAdmin(int adminId) throws DataAccessException;

	int selectAdminCount(String keyword);
	
	List<Administrator> selectAdminList(String keyword, int offset, int limit);

	void insertAdminLog(AdminLog log);

	int selectAdminLogCount(String category);
	
	List<AdminLog> selectAdminLogList(String category, int offset, int limit);

}
