package com.hnjz.wf.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.hnjz.common.dao.DaoImpl;
import com.hnjz.wf.util.ReflectionUtils;

/**
 * ����DAO��<br/>
 * ����DAO�̳д��࣬�ṩ<T(ʵ������), S(��������)>
 * 
 * @author zn
 * @version $Id: MyDao.java, v 0.1 2013-1-15 ����09:11:19 Administrator Exp $
 */
@SuppressWarnings("unchecked")
public abstract class MyDao<T, S extends Serializable> extends DaoImpl {

    @Resource
    private SessionFactory sessionFactory;

    /** ʵ������ */
    protected Class<T>     entityClass;

    public MyDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * ����������ѯ�б�<br/>
     * pageNo��pageSize��������-1ʱ���з�ҳ��ѯ
     * 
     * @param pageNo
     *            ҳ��
     * @param pageSize
     *            ÿҳ��ʾ��¼��
     * @param cList
     *            !=nullʱ��������<br/>
     *            Criterion��������
     * @param orderMap
     *            !=nullʱ�������� ���򼯺�<br/>
     *            {'�ֶ���',�Ƿ�����(true-������,false-������)}
     * @return
     */

    public List<T> findByCondition(final Integer pageNo, final Integer pageSize,
                                   final List<Criterion> cList, final Map<String, Boolean> orderMap) {
        Session session = super.getSession();
        Criteria criteria = session.createCriteria(entityClass);
        if (cList != null) {// ���Ӳ�ѯ����
            for (int i = 0; i < cList.size(); i++) {
                criteria.add(cList.get(i));
            }
        }
        if (orderMap != null) {// ��������
            Iterator<String> keyIt = orderMap.keySet().iterator();
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                if (orderMap.get(key)) {
                    criteria.addOrder(Order.asc(key));
                } else {
                    criteria.addOrder(Order.desc(key));
                }
            }
        }
        if (pageNo != -1 && pageSize != -1) {// ���ӷ�ҳ
            Integer start = (pageNo - 1) * pageSize;
            criteria.setFirstResult(start);
            criteria.setMaxResults(pageSize);
        }
        return criteria.list();
    }

    /**
     * ��ѯ����
     * @param cList
     * @return
     */
    public Integer findCount(final List<Criterion> cList) {
        Session session = super.getSession();
        Criteria criteria = session.createCriteria(entityClass);
        if (cList != null) {// ���Ӳ�ѯ����
            for (int i = 0; i < cList.size(); i++) {
                criteria.add(cList.get(i));
            }
        }
        return (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    /**
     * ��ѯʵ�����
     * 
     * @param id
     *            ʵ�����
     * @return
     */
    public T get(S id) {
        return (T) super.getSession().get(entityClass, id);
    }

    /**
     * ִ��HQL���
     * @param hql
     * @return  Ӱ��ļ�¼��
     */
    public int execHQL(String hql, Object... canshu) {
        Query query = super.getSession().createQuery(hql);
        if (canshu != null && canshu.length > 0) {
            for (int i = 0; i < canshu.length; i++) {
                query.setParameter(i, canshu[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * ִ��SQL���(������)
     * @param sql   ���
     * @param canshu    ����
     * @return  Ӱ��ļ�¼��
     */
    public int execSQL(String sql, Object... canshu) {
        SQLQuery query = super.getSession().createSQLQuery(sql);
        if (canshu != null && canshu.length > 0) {
            for (int i = 0; i < canshu.length; i++) {
                query.setParameter(i, canshu[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * SQL��ѯ
     * @param sql
     * @param canshu
     * @return
     */
    public List<Object> findBySQL(String sql, Object... canshu) {
        SQLQuery query = super.getSession().createSQLQuery(sql);
        if (canshu != null && canshu.length > 0) {
            for (int i = 0; i < canshu.length; i++) {
                query.setParameter(i, canshu[i]);
            }
        }
        return query.list();
    }

    public Session getMySession() {
        return super.getSession();
    }

    @PostConstruct
    public void injectSessionFactory() {
        super.setSessionFactory(sessionFactory);
    }

}