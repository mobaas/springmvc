/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.dao.hibernate;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobaas.smfx.dao.AdminDao;
import com.mobaas.smfx.dao.DaoCondition;
import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.domain.Administrator;

/**
 * 管理员数据访问实现类
 * @author billy (billy_zh@126.com)
 *
 */
@Repository
@Conditional(DaoCondition.Hibernate.class)
public class HibernateAdminDao extends HibernateDaoSupport implements AdminDao {

	@Override
	public Administrator selectAdminById(int adminId) throws DataAccessException {
		return getSessionFactory().openSession().get(Administrator.class, adminId);
	}

	@Override
	public Administrator selectAdminByName(String loginName) {
		
		Query<Administrator> query = getSessionFactory().openSession().createQuery("from Administrator where loginName=?", Administrator.class);
		query.setParameter(0, loginName);
		
		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly=true)
	public Administrator selectAdminByNameAndPassword(String loginName, String password) {

		Query<Administrator> query = getSessionFactory().getCurrentSession().createQuery("from Administrator where loginName=? and password=?", Administrator.class);
		query.setParameter(0, loginName);
		query.setParameter(1, password);
		
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void insertAdmin(Administrator admin) {
		getSessionFactory().getCurrentSession().save(admin);
	}

	@Override
	@Transactional
	public int updateAdmin(Administrator admin) {
		getSessionFactory().getCurrentSession().update(admin);
		return 0;
	}

	@Override
	@Transactional
	public int deleteAdmin(int adminId) {
		Query<Void> query = getSessionFactory().getCurrentSession().createQuery("delete from Administrator where adminId=?", Void.class);
		query.setParameter(0, adminId);
		return query.executeUpdate();
	}

	@Override
	public int selectAdminCount(String keyword) {
		Query<Long> query = getSessionFactory().openSession().createQuery("select count(*) from Administrator", Long.class);
		return query.uniqueResult().intValue();
	}
	
	@Override
	public List<Administrator> selectAdminList(String keyword, int offset, int limit) {
		Query<Administrator> query = getSessionFactory().openSession().createQuery("from Administrator", Administrator.class);
		
		return query.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	@Override
	@Transactional
	public void insertAdminLog(AdminLog log) {
		getSessionFactory().getCurrentSession().save(log);
	}
	
	@Override
	public int selectAdminLogCount(String category) {
		Query<Long> query = getSessionFactory().openSession().createQuery("select count(*) from AdminLog", Long.class);
		return query.uniqueResult().intValue();
	}
	
	@Override
	public List<AdminLog> selectAdminLogList(String keyword, int offset, int limit) {
		Query<AdminLog> query = getSessionFactory().openSession().createQuery("from AdminLog order by logTime desc", AdminLog.class);
		
		return query.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

}
