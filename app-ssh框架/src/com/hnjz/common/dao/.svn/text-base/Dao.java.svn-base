/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.util.List;
import java.util.Map;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;

/**
 * 对数据库常用访问的接口定义<br>
 * 
 * @author wumi
 * @version $Id: Dao.java, v 0.1 Dec 19, 2011 9:12:57 AM Administrator Exp $
 */
public interface Dao {

    /**
     * 根据Id和Class获取一个对象
     * 
     * @param clazz 要获取对象的class
     * @param id 要获取对象的主键
     * @return 对象
     */
    Object get(Class<?> clazz, String id);

    /**
     * 保存一个对象
     * 
     * @param object 要保存的对象
     * @return 
     */
    Object save(Object object);

    /**
     * 根据Id和class删除一个对象
     * 
     * @param clazz 要删除对象的class
     * @param id 要删除对象的主键
     */
    void remove(Class<?> clazz, String id);

    /**
     * 删除传入的对象
     * 
     * @param object 要删除的对象
     */
    void remove(Object object);

    /**
     * 批量删除传入的对象
     * 
     * @param objs 要删除的对象列表
     */
    void remove(List<? extends Object> objs);

    /**
     * 删除根据hsql查询出来的东西出来的东西，例如：<br>
     * String hsql = "from User";
     * <br>
     * dao.find(hsql);
     * 
     * @param hsql hsql语句
     * @return 结果列表
     */
    void removeFindObjs(String hsql, Object... canshu);

    /**
     * 使用hsql查询，例如：<br>
     * String hsql = "from User";
     * <br>
     * dao.find(hsql);
     * 
     * @param hsql hsql语句
     * @return 结果列表
     */
    <T> List<T> find(String hsql);

    /**
     * 使用hsql查询，例如：<br>
     * String hsql = "from User where name = ?";
     * <br>
     * dao.find(hsql,"张三");
     * <br>
     * String hsql = "from User where name = ? and sex = ?";
     * <br>
     * dao.find(hsql,"张三","男");
     * <br>
     * hsql 为 "from XX ..." 返回XX对象的类表，List<XX>
     * <br>
     * "select X,Y from Z ..",返回 List<Object[]>
     * <br>
     * "select X from Z ..",返回 List<Object>
     * 
     * @param hsql hsql语句
     * @param canshu hsql查询条件的参数，多个用逗号隔开，可以为null
     * @return 结果列表
     */
    <T> List<T> find(String hsql, Object... canshu);

    /**
     * 使用hsql查询，用于参数个数不固定的查询，例如：<br>
     * String hsql = "from User where name = :name";
     * QueryCondition data = new QueryCondition();
     * data.put("name", "张三");
     * 
     * if(null != sex){
     *   sql.applend(" and SEX = :sex");
     *   data.put("sex", sex);
     * }
     * dao.find(sql,data);
     * 返回的List中为Object[]数组
     * 
     * @param hsql hsql语句
     * @param canshu hsql查询条件的参数
     * @return 结果列表
     */
    <T> List<T> find(final String hsql, final QueryCondition data);

    /**
     * 批量保存数据
     * 
     * @param <T>
     * @param array
     * @return
     */
    <T> int batchSave(final T[] array);

    /**
     * 分页查询出结果集，示例参见：{@link Dao#find(String, Map)}
     * 
     * @param hsql hsql语句
     * @param data hsql查询条件的参数
     * @param curPage 当前要要查询的页数
     * @return 结果列表
     */
    FyResult find(final String hsql, final QueryCondition data, int curPage);

    /**
     * 分页查询出结果集，示例参见：{@link Dao#find(String, Map)}
     * 
     * @param counthsql 查询数目的语句
     * @param hsql hsql语句
     * @param data hsql查询条件的参数
     * @param curPage 当前要要查询的页数
     * @return 结果列表
     */
    FyResult find(final String counthsql, final String hsql, final QueryCondition data, int curPage);

    /**
     * 分页查询出结果集，示例参见：{@link Dao#find(String, Map)}
     * 
     * @param hsql sql语句
     * @param data hsql查询条件的参数
     * @param curPage 当前要要查询的页数
     * @return 结果列表
     */
    FyResult query(final String sql, final QueryCondition data, int curPage);
    
    /**
     * 使用sql查询，例如：<br>
     * String sql = "select * from T_DATA_FILE";
     * <br>
     * dao.findBySql(sql);
     * 
     * @param sql sql语句
     * @return 结果列表
     */
    public <T> List<T> findBySql(String sql, Object... canshu);
    
    public FyResult find(String sql, final int curPage, int pageSize, Map<String, Object> condition);
    
    public boolean exec(String sql);

    /**
     * 
     * 函数介绍：使用sql查询，参数用map绑定
    
     * 输入参数：sql语句、参数
    
     * 返回值：
     */
	public <T> List<T> findBySql(String sql, Map<String, Object> condition);

	/**
	 * 
	 * 函数介绍：通过sql查询列表
	
	 * 输入参数：
	
	 * 返回值：List<Map<String,Object>> key:列名 value：列值
	 */
	public List<Map<String, Object>> queryListMapBySql(String sql, Map<String, Object> condition);

	/**
	 * 
	 * 函数介绍：单机同步接口专用sql查询（返回map数据集）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, Object>> queryListMapBySqlForHbdj(String sql, String tableName, Map<String, Object> condition);
}
