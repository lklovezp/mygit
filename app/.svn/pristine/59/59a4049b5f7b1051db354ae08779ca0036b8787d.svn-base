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
 * 公共DAO类<br/>
 * 其他DAO继承此类，提供<T(实体类型), S(主键类型)>
 * 
 * @author zn
 * @version $Id: MyDao.java, v 0.1 2013-1-15 上午09:11:19 Administrator Exp $
 */
@SuppressWarnings("unchecked")
public abstract class MyDao<T, S extends Serializable> extends DaoImpl {

    @Resource
    private SessionFactory sessionFactory;

    /** 实体类型 */
    protected Class<T>     entityClass;

    public MyDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 根据条件查询列表<br/>
     * pageNo和pageSize都不等于-1时进行分页查询
     * 
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页显示记录数
     * @param cList
     *            !=null时添加条件<br/>
     *            Criterion条件集合
     * @param orderMap
     *            !=null时添加排序 排序集合<br/>
     *            {'字段名',是否正序(true-按正序,false-按倒序)}
     * @return
     */

    public List<T> findByCondition(final Integer pageNo, final Integer pageSize,
                                   final List<Criterion> cList, final Map<String, Boolean> orderMap) {
        Session session = super.getSession();
        Criteria criteria = session.createCriteria(entityClass);
        if (cList != null) {// 添加查询条件
            for (int i = 0; i < cList.size(); i++) {
                criteria.add(cList.get(i));
            }
        }
        if (orderMap != null) {// 添加排序
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
        if (pageNo != -1 && pageSize != -1) {// 添加分页
            Integer start = (pageNo - 1) * pageSize;
            criteria.setFirstResult(start);
            criteria.setMaxResults(pageSize);
        }
        return criteria.list();
    }

    /**
     * 查询总数
     * @param cList
     * @return
     */
    public Integer findCount(final List<Criterion> cList) {
        Session session = super.getSession();
        Criteria criteria = session.createCriteria(entityClass);
        if (cList != null) {// 添加查询条件
            for (int i = 0; i < cList.size(); i++) {
                criteria.add(cList.get(i));
            }
        }
        return (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    /**
     * 查询实体对象
     * 
     * @param id
     *            实体对象
     * @return
     */
    public T get(S id) {
        return (T) super.getSession().get(entityClass, id);
    }

    /**
     * 执行HQL语句
     * @param hql
     * @return  影响的记录数
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
     * 执行SQL语句(带参数)
     * @param sql   语句
     * @param canshu    参数
     * @return  影响的记录数
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
     * SQL查询
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
