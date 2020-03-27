/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author billy (billy_zh@126.com)
 *
 */
public final class CryptoUtil {

	private CryptoUtil() {
	}

	/**
	 * @return
	 */
	public static String MD5(String str) {
		if (str == null)
			return "";
		
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return new String(Base64.encodeBase64(md5.digest(str.getBytes("utf-8"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 */
	public static String MD5Hex(String str) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return ByteToHex(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String ByteToHex(byte[] bytes) {
		int i;
		StringBuffer sb = new StringBuffer();
		for (i = 0; i < bytes.length; i++) {
			String s = Integer.toHexString(bytes[i]);
			if (s.length() < 2) {
				s = "0" + s;
			}
			if (s.length() > 2) {
				s = s.substring(s.length() - 2);
			}
			sb.append(s);
		}
		return sb.toString();
	}

	

	
}
