/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.hnjz.common.dao.DaoImpl;
import com.hnjz.quartz.po.Job;

/**
 * job�ĵ�ʵ��
 * 
 * @author wumi
 * @version $Id: JobDao.java, v 0.1 Apr 11, 2013 10:51:57 AM wumi Exp $
 */
@Service("jobDao")
public class JobDao extends DaoImpl {

    @Resource
    private SessionFactory sessionFactory;

    /**
     * ��ѯ������ҵ
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Job> getAllJob() {
        return (List<Job>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            @Override
            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                String hsql = "from Job as job order by created ";
                Query q = s.createQuery(hsql);
                q.setLockMode("job", LockMode.UPGRADE);
                return q.list();
            }
        });

    }

    public Session getMySession() {
        return super.getSession();
    }

    @PostConstruct
    public void injectSessionFactory() {
        super.setSessionFactory(sessionFactory);
    }

}