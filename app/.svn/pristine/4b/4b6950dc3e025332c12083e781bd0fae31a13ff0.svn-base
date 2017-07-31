/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hnjz.common.dao.Dao;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;

/**
 * 
 * @author wumi
 * @version $Id: ManagerImpl.java, v 0.1 Jan 8, 2013 10:28:27 AM wumi Exp $
 */
@Service("manager")
public class ManagerImpl implements Manager {

	@Autowired
	protected Dao dao;
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/**
	 * @see com.hnjz.common.manager.Manager#find(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public <T> List<T> find(String hsql) {
		return dao.find(hsql);
	}
	/**
	 * @see com.hnjz.common.manager.Manager#find(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public <T> List<T> find(String hsql, Object... canshu) {
		return dao.find(hsql, canshu);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#find(java.lang.String,
	 *      com.hnjz.common.dao.domain.QueryCondition)
	 */
	@Override
	public <T> List<T> find(String hsql, QueryCondition data) {
		return dao.find(hsql, data);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#find(java.lang.String,
	 *      com.hnjz.common.dao.domain.QueryCondition, int)
	 */
	@Override
	public FyResult find(String hsql, QueryCondition data, int curPage) {
		return dao.find(hsql, data, curPage);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#get(java.lang.Class,
	 *      java.lang.String)
	 */
	@Override
	public Object get(Class<?> clazz, String id) {
		return dao.get(clazz, id);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#remove(java.lang.Class,
	 *      java.lang.String)
	 */
	@Override
	public void remove(Class<?> clazz, String id) {
		dao.remove(clazz, id);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object object) {
		dao.remove(object);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#remove(java.util.List)
	 */
	@Override
	public void remove(List<? extends Object> objs) {
		dao.remove(objs);
	}

	/**
	 * @see com.hnjz.common.manager.Manager#save(java.lang.Object)
	 */
	@Override
	public Object save(Object object) {
		return dao.save(object);
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
