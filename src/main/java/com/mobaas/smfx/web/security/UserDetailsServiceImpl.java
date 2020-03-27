/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mobaas.smfx.Constants;
import com.mobaas.smfx.domain.Administrator;
import com.mobaas.smfx.service.AdminService;

/**
 * UserDetailsService 实现类
 * @author billy (billy_zh@126.com)
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminService adminService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Administrator admin = adminService.selectAdminByName(username);
		if (admin == null) {
			throw new UsernameNotFoundException(Constants.ADMIN_NOT_EXISTS);
		}

		// TODO： 从数据库获取权限列表。
		List<GrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_SYSTEM_VIEW"));
		authList.add(new SimpleGrantedAuthority("ROLE_SYSTEM_EDIT"));
		authList.add(new SimpleGrantedAuthority("ROLE_INFO_VIEW"));
		authList.add(new SimpleGrantedAuthority("ROLE_INFO_EDIT"));
		
		return new User(username, admin.getPassword(), authList);
		
	}

}
