/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mobaas.smfx.PageList;
import com.mobaas.smfx.Pager;
import com.mobaas.smfx.dao.AdminDao;
import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.domain.Administrator;
import com.mobaas.smfx.service.AdminService;

/**
 * 管理员服务实现类
 * @author billy (billy_zh@126.com)
 *
 */
@Service
public class AdminServiceImpl  implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public Administrator selectAdminById(int adminId) {
		return adminDao.selectAdminById(adminId);
	}

	@Override
	public PageList<Administrator> selectAdminList(String keyword, Pager pager) {
		PageList<Administrator> plist = new PageList<>();
		plist.setTotal(adminDao.selectAdminCount(keyword));
		if (plist.getTotal() > 0) {
			plist.setList( adminDao.selectAdminList(keyword, pager.getOffset(), pager.getPageSize()) );
		}
		
		return plist;
	}

	@Override
	public Administrator selectAdminByName(String loginname) {
		return adminDao.selectAdminByName(loginname);
	}

	@Override
	public Administrator selectAdminByNameAndPassword(String loginname, String password)
			throws DataAccessException {
		return adminDao.selectAdminByNameAndPassword(loginname, password);
	}

	@Override
	public void insertAdmin(Administrator admin) {
		this.adminDao.insertAdmin(admin);
	}

	@Override
	public int updateAdmin(Administrator admin) {
		return adminDao.updateAdmin(admin);
	}

	@Override
	public int deleteAdmin(int adminId) {
		return adminDao.deleteAdmin(adminId);
	}

	@CacheEvict(value="adminlogCache", allEntries=true)
	@Override
	public void insertAdminLog(AdminLog log) {		
		adminDao.insertAdminLog(log);
	}

	@Cacheable(value="adminlogCache", key="#keyword.concat(#pager.cacheKey)")
	@Override
	public PageList<AdminLog> selectAdminLogList(String keyword, Pager pager) {
		PageList<AdminLog> plist = new PageList<AdminLog>();
		plist.setTotal( adminDao.selectAdminLogCount(keyword) );
		if (plist.getTotal() > 0) {
			plist.setList( adminDao.selectAdminLogList(keyword, pager.getOffset(), pager.getPageSize()) );
		}
		
		return plist;
	}

}
