/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.service;

import com.mobaas.smfx.PageList;
import com.mobaas.smfx.Pager;
import com.mobaas.smfx.domain.Info;

/**
 * 资讯服务接口
 * @author billy (billy_zh@126.com)
 *
 */
public interface InfoService {

	PageList<Info> selectInfoList(String keyword, Pager pager);

	Info selectInfoById(int id);

	void insertInfo(Info info);

	int deleteInfo(int id);

	int updateInfo(Info info);
}
