/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mobaas.smfx.dao.InfoDao;
import com.mobaas.smfx.domain.Info;

/**
 * 资讯Mapper接口
 * @author billy (billy_zh@126.com)
 *
 */
public interface InfoMapper extends InfoDao {
	
	@Override
	List<Info> selectInfoList(
			@Param("keyword")String keyword, 
			@Param("offset")int offset, 
			@Param("limit")int limit);

	@Override
	Info selectInfoById(int id);

	@Override
	void insertInfo(Info info);

	@Override
	int deleteInfo(int id);

	@Override
	int updateInfo(Info info);

	@Override
	int selectInfoCount(
			@Param("keyword")String keyword);
	
}
