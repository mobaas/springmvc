/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao;

import java.util.List;

import com.mobaas.smfx.domain.Info;

/**
 * 资讯数据访问接口
 * @author billy (billy_zh@126.com)
 *
 */
public interface InfoDao {

	List<Info> selectInfoList(String keyword, int offset, int limit);

	Info selectInfoById(int id);

	void insertInfo(Info info);

	int deleteInfo(int id);

	int updateInfo(Info info);

	int selectInfoCount(String keyword);
	
}
