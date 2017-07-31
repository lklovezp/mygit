/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;

/**
 * �����ݿⳣ�÷��ʵĽӿڶ���<br>
 * 
 * @author wumi
 * @version $Id: Dao.java, v 0.1 Dec 19, 2011 9:12:57 AM Administrator Exp $
 */
public interface Dao {

    /**
     * ����Id��Class��ȡһ������
     * 
     * @param clazz Ҫ��ȡ�����class
     * @param id Ҫ��ȡ���������
     * @return ����
     */
    Object get(Class<?> clazz, String id);

    /**
     * ����һ������
     * 
     * @param object Ҫ����Ķ���
     * @return 
     */
    Object save(Object object);
    
    /**
     * �޸Ļ����Ӷ���
     */
    Object merge(Object object);

    /**
     * ����Id��classɾ��һ������
     * 
     * @param clazz Ҫɾ�������class
     * @param id Ҫɾ�����������
     */
    void remove(Class<?> clazz, String id);

    /**
     * ɾ������Ķ���
     * 
     * @param object Ҫɾ���Ķ���
     */
    void remove(Object object);

    /**
     * ����ɾ������Ķ���
     * 
     * @param objs Ҫɾ���Ķ����б�
     */
    void remove(List<? extends Object> objs);

    /**
     * ɾ������hsql��ѯ�����Ķ��������Ķ��������磺<br>
     * String hsql = "from User";
     * <br>
     * dao.find(hsql);
     * 
     * @param hsql hsql���
     * @return ����б�
     */
    void removeFindObjs(String hsql, Object... canshu);

    /**
     * ʹ��hsql��ѯ�����磺<br>
     * String hsql = "from User";
     * <br>
     * dao.find(hsql);
     * 
     * @param hsql hsql���
     * @return ����б�
     */
    <T> List<T> find(String hsql);

    /**
     * ʹ��hsql��ѯ�����磺<br>
     * String hsql = "from User where name = ?";
     * <br>
     * dao.find(hsql,"����");
     * <br>
     * String hsql = "from User where name = ? and sex = ?";
     * <br>
     * dao.find(hsql,"����","��");
     * <br>
     * hsql Ϊ "from XX ..." ����XX����������List<XX>
     * <br>
     * "select X,Y from Z ..",���� List<Object[]>
     * <br>
     * "select X from Z ..",���� List<Object>
     * 
     * @param hsql hsql���
     * @param canshu hsql��ѯ�����Ĳ���������ö��Ÿ���������Ϊnull
     * @return ����б�
     */
    <T> List<T> find(String hsql, Object... canshu);
    <T> List<T> findbysql(String sql, Object... canshu);
    /**
     * ʹ��hsql��ѯ�����ڲ����������̶��Ĳ�ѯ�����磺<br>
     * String hsql = "from User where name = :name";
     * QueryCondition data = new QueryCondition();
     * data.put("name", "����");
     * 
     * if(null != sex){
     *   sql.applend(" and SEX = :sex");
     *   data.put("sex", sex);
     * }
     * dao.find(sql,data);
     * ���ص�List��ΪObject[]����
     * 
     * @param hsql hsql���
     * @param canshu hsql��ѯ�����Ĳ���
     * @return ����б�
     */
    <T> List<T> find(final String hsql, final QueryCondition data);

    /**
     * ������������
     * 
     * @param <T>
     * @param array
     * @return
     */
    <T> int batchSave(final T[] array);

    /**
     * ��ҳ��ѯ���������ʾ���μ���{@link Dao#find(String, Map)}
     * 
     * @param hsql hsql���
     * @param data hsql��ѯ�����Ĳ���
     * @param curPage ��ǰҪҪ��ѯ��ҳ��
     * @return ����б�
     */
    FyResult find(final String hsql, final QueryCondition data, int curPage);

    /**
     * ��ҳ��ѯ���������ʾ���μ���{@link Dao#find(String, Map)}
     * 
     * @param counthsql ��ѯ��Ŀ�����
     * @param hsql hsql���
     * @param data hsql��ѯ�����Ĳ���
     * @param curPage ��ǰҪҪ��ѯ��ҳ��
     * @return ����б�
     */
    FyResult find(final String counthsql, final String hsql, final QueryCondition data, int curPage);

    /**
     * ��ҳ��ѯ���������ʾ���μ���{@link Dao#find(String, Map)}
     * 
     * @param hsql sql���
     * @param data hsql��ѯ�����Ĳ���
     * @param curPage ��ǰҪҪ��ѯ��ҳ��
     * @return ����б�
     */
    FyResult query(final String sql, final QueryCondition data, int curPage);
    
    /**
     * ʹ��sql��ѯ�����磺<br>
     * String sql = "select * from T_DATA_FILE";
     * <br>
     * dao.findBySql(sql);
     * 
     * @param sql sql���
     * @return ����б�
     */
    public <T> List<T> findBySql(String sql, Object... canshu);
    
    public FyResult find(String sql, final int curPage, int pageSize, Map<String, Object> condition);
    
    public boolean exec(String sql);

    /**
     * 
     * �������ܣ�ʹ��sql��ѯ��������map��
    
     * ���������sql��䡢����
    
     * ����ֵ��
     */
	public <T> List<T> findBySql(String sql, Map<String, Object> condition);

	/**
	 * 
	 * �������ܣ�ͨ��sql��ѯ�б�
	
	 * ���������
	
	 * ����ֵ��List<Map<String,Object>> key:���� value����ֵ
	 */
	public List<Map<String, Object>> queryListMapBySql(String sql, Map<String, Object> condition);

	/**
	 * 
	 * �������ܣ�����ͬ���ӿ�ר��sql��ѯ������map���ݼ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, Object>> queryListMapBySqlForHbdj(String sql, String tableName, Map<String, Object> condition);
	
	/**
	 *���ݱ��洫����
	 */
	public List<Map<String,Object>> doInEntity(final String sql, final Map<String,Object> condition) throws HibernateException,
	SQLException;
	/**
	 * ���ݱ�����װ����
	 * @throws SQLException 
	 */
	

	
}