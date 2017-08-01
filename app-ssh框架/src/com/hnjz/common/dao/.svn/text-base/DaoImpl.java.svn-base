/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;

/**
 * 对数据库访问的封装
 * 
 * @author wumi
 * @version $Id: DaoImpl.java, v 0.1 Dec 19, 2011 9:46:07 AM Administrator Exp $
 */
@SuppressWarnings("unchecked")
public class DaoImpl extends HibernateDaoSupport implements Dao {
	
	 /**日志*/
    private static final Log log = LogFactory.getLog(DaoImpl.class);

    /**
     * @see com.hnjz.common.dao.Dao#find(java.lang.String, java.lang.Object[])
     */
	public <T> List<T> find(String hsql, Object... canshu) {
        return this.getHibernateTemplate().find(hsql, canshu);
    }

    /**
     * @see com.hnjz.common.dao.Dao#get(java.lang.Class, java.lang.String)
     */
    public Object get(Class<?> clazz, String id) {
        return this.getHibernateTemplate().get(clazz, id);
    }

    /**
     * @see com.hnjz.common.dao.Dao#remove(java.lang.Class, java.lang.String)
     */
    public void remove(Class<?> clazz, String id) {
        Object object = this.get(clazz, id);
        if (null != object) {
            this.remove(object);
        }
    }

    /**
     * @see com.hnjz.common.dao.Dao#remove(java.lang.Object)
     */
    public void remove(Object object) {
        this.getHibernateTemplate().delete(object);
    }

    /**
     * @see com.hnjz.common.dao.Dao#remove(java.util.List)
     */
    public void remove(List<? extends Object> objs) {
        this.getHibernateTemplate().deleteAll(objs);
    }

    /**
     * @see com.hnjz.common.dao.Dao#save(java.lang.Object)
     */
    public Object save(Object object) {
        this.getHibernateTemplate().saveOrUpdate(object);
        return object;
    }

    /**
     * @see com.hnjz.common.dao.Dao#find(java.lang.String,
     *      com.hnjz.common.dao.domain.QueryCondition)
     */
    public <T> List<T> find(final String hsql, final QueryCondition data) {
        return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                Query query = s.createQuery(hsql);
                for (Map.Entry<String, Object> entry : data.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                return query.list();
            }
        });
    }

    /**
     * @see com.hnjz.common.dao.Dao#find(java.lang.String,
     *      com.hnjz.common.dao.domain.QueryCondition, int)
     */
    public FyResult find(final String hsql, final QueryCondition data, final int curPage) {
        String countHsql = SqlUtil.getCountSql(hsql);
        return this.find(countHsql, hsql, data, curPage);
    }

    
    public FyResult find(final String counthsql, final String hsql, final QueryCondition data,
                         final int curPage) {
        return (FyResult) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                FyResult fy = new FyResult();
                Query query = s.createQuery(counthsql);
                for (Map.Entry<String, Object> entry : data.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                long count= Long.parseLong(String.valueOf(query.list().get(0)));
                fy.setNum(count);
                fy.setCurrentPage(curPage);
                if (StringUtils.isNotBlank(data.getPageSize())) {
                    fy.setPerPageNum(Integer.parseInt(data.getPageSize()));
                }
                fy.execute();
                if (fy.getAllPage() < fy.getCurrentPage()) {
                    fy.setCurrentPage(1);
                    fy.execute();
                }

                query = s.createQuery(hsql);
                for (Map.Entry<String, Object> entry : data.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                if (fy.getStartItem() >= 0 && fy.getPerPageNum() >= 0) {
                    query.setFirstResult(fy.getStartItem());
                    query.setMaxResults(fy.getPerPageNum());
                }

                fy.setRe(query.list());
                return fy;
            }

        });
    }

    private void setParameter(Query query, Map.Entry<String, Object> entry) {
        if (entry.getValue() instanceof Object[]) {
            query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
        } else if (entry.getValue() instanceof Collection) {
            query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
        } else {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    
    public <T> List<T> find(String hsql) {
        return this.getHibernateTemplate().find(hsql);
    }

    
    public void removeFindObjs(String hsql, Object... canshu) {
        List<?> re = this.find(hsql, canshu);
        this.remove(re);
    }

    
    public FyResult query(final String sql, final QueryCondition data, final int curPage) {
        //Assert.notNull(data.getPageSize(),"QueryCondition 中的pageSize不能为空");
        return (FyResult) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                FyResult fy = new FyResult();
                String countHsql = SqlUtil.getCountSql(sql);
                Query query = s.createSQLQuery(countHsql);
                for (Map.Entry<String, Object> entry : data.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                List<Object> list = query.list();
                long count = Long.parseLong(String.valueOf(list.size()==0?0:list.get(0)));
                fy.setNum(count);
                fy.setCurrentPage(curPage);
                if (StringUtils.isNotBlank(data.getPageSize())) {
                    fy.setPerPageNum(Integer.parseInt(data.getPageSize()));
                }
                fy.execute();

                query = s.createSQLQuery(sql);
                for (Map.Entry<String, Object> entry : data.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                if (fy.getStartItem() >= 0 && fy.getPerPageNum() >= 0) {
                    query.setFirstResult(fy.getStartItem());
                    query.setMaxResults(fy.getPerPageNum());
                }

                fy.setRe(query.list());
                return fy;
            }
        });
    }

    
    public <T> int batchSave(final T[] array) {
        Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < array.length; i++) {
            session.save(array[i]);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
        tx.commit();
        return array.length;
    }

	
	public <T> List<T> findBySql(String sql, Object... canshu) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if (canshu != null && canshu.length > 0){
			for (int i = 0; i < canshu.length; i++) {
				query.setParameter(i, canshu[i]);
			}
		}
		return query.list();
	}
	
	
	public <T> List<T> findBySql(final String sql, final Map<String,Object> condition) {
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String,Object>>>() {
			public List<Map<String,Object>> doInHibernate(Session session) throws HibernateException,
			SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				for (Map.Entry<String, Object> entry : condition.entrySet()) {
					setParameter(query, entry);
				}
				return query.list();
			}
		});
	}

	
	public FyResult find(final String sql, final int curPage, int pageSize, Map<String, Object> data) {
		final String countSql = "select count(1) from (" + sql + ") total";
		final QueryCondition condition = new QueryCondition();
		condition.setPageSize(String.valueOf(pageSize == 0 ? 20 : pageSize));
		condition.setCanshu(data);
		return (FyResult) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                FyResult fy = new FyResult();
                SQLQuery query = s.createSQLQuery(countSql);
//                Query query = s.createQuery(countSql);
                for (Map.Entry<String, Object> entry : condition.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }

                long count = Long.parseLong(String.valueOf(query.list().get(0)));
                fy.setNum(count);
                fy.setCurrentPage(curPage);
                if (StringUtils.isNotBlank(condition.getPageSize())) {
                    fy.setPerPageNum(Integer.parseInt(condition.getPageSize()));
                }
                fy.execute();
                if(log.isDebugEnabled()){
                    log.debug("page:"+curPage);
                    log.debug("fy:"+ToStringBuilder.reflectionToString(fy, ToStringStyle.MULTI_LINE_STYLE));
                }

                if (fy.getAllPage() < fy.getCurrentPage()) {
//                    fy.setCurrentPage(1);
//                    fy.execute();
                }

                query = s.createSQLQuery(sql);
                for (Map.Entry<String, Object> entry : condition.getCanshu().entrySet()) {
                    setParameter(query, entry);
                }
                if (fy.getStartItem() >= 0 && fy.getPerPageNum() >= 0) {
                    query.setFirstResult(fy.getStartItem());
                    query.setMaxResults(fy.getPerPageNum());
                }

                fy.setRe(query.list());
                return fy;
            }

        });
	}

	
	public boolean exec(String sql) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
		return true;
	}

	
	public List<Map<String, Object>> queryListMapBySql(final String sql,final Map<String, Object> condition) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String,Object>>>() {
			public List<Map<String,Object>> doInHibernate(Session session) throws HibernateException,
			SQLException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if(condition!=null){
					Set<String> keys = condition.keySet();
					for (Iterator<String> it = keys.iterator(); it.hasNext();) {
						String key = it.next();
						query.setParameter(key, condition.get(key));
					}
				}
				return query.list();
			}
		});
	}
	
	public List<Map<String, Object>> queryListMapBySqlForHbdj(final String sql,final String tableName,final Map<String, Object> condition) {
		List<String> list = this.findBySql("select LISTAGG(t.column_name, ',') WITHIN GROUP(ORDER BY ROWNUM) from user_col_comments t where t.table_name = ?", tableName);
		if(list.size()>0){
			final String[] column = list.get(0).split(",");
			return this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String,Object>>>() {
				public List<Map<String,Object>> doInHibernate(Session session) throws HibernateException,
				SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					for(String str : column){
						if("CREATED_".equals(str) || "UPDATED_".equals(str)){
							query.addScalar(str, Hibernate.TIMESTAMP);
						}else{
							query.addScalar(str, Hibernate.STRING);
						}
					}
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if(condition!=null){
						Set<String> keys = condition.keySet();
						for (Iterator<String> it = keys.iterator(); it.hasNext();) {
							String key = it.next();
							query.setParameter(key, condition.get(key));
						}
					}
					return query.list();
				}
			});
		}
		return null;
	}
}
