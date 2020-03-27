/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mobaas.smfx.util.CryptoUtil;

/**
 * PasswordEncoder 实现类
 * @author billy (billy_zh@126.com)
 *
 */
@Component
public class PasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return CryptoUtil.MD5Hex(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.toString().equals(CryptoUtil.MD5Hex(rawPassword.toString()));
	}

}
