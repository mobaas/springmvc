/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao.hibernate;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobaas.smfx.dao.DaoCondition;
import com.mobaas.smfx.dao.InfoDao;
import com.mobaas.smfx.domain.Info;

/**
 * 资讯数据访问实现类
 * @author billy (billy_zh@126.com)
 *
 */
@Repository
@Conditional(DaoCondition.Hibernate.class)
public class HibernateInfoDao extends HibernateDaoSupport implements InfoDao {

	@Override
	public List<Info> selectInfoList(String keyword, int offset, int limit) {
		Query<Info> query = getSessionFactory().openSession().createQuery("from Info order by addTime desc", Info.class);
		
		return query.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	@Override
	public int selectInfoCount(String keyword) {
		Query<Long> query = getSessionFactory().openSession().createQuery("select count(*) from Info", Long.class);
		return query.uniqueResult().intValue();
	}
	
	@Override
	public Info selectInfoById(int id) {
		return getSessionFactory().openSession().get(Info.class, id);
	}

	@Override
	@Transactional
	public void insertInfo(Info info) {
		getSessionFactory().getCurrentSession().save(info);
	}

	@Override
	@Transactional
	public int deleteInfo(int id) {
		Query<Void> query = getSessionFactory().getCurrentSession().createQuery("delete from Info where id=?", Void.class);
		query.setParameter(0, id);
		return query.executeUpdate();
	}

	@Override
	@Transactional
	public int updateInfo(Info info) {
		getSessionFactory().getCurrentSession().update(info);
		return 1;
	}

}
