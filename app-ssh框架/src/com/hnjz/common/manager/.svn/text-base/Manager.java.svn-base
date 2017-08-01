/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.manager;

import java.util.List;
import java.util.Map;

import com.hnjz.common.dao.Dao;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;

/**
 * 
 * 
 * @author wumi
 * @version $Id: Manager.java, v 0.1 Jan 8, 2013 10:26:45 AM wumi Exp $
 */
public interface Manager {

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
     * Map data = new HashMap();
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
     * 分页查询出结果集，示例参见：{@link Dao#find(String, Map)}
     * 
     * @param hsql hsql语句
     * @param data hsql查询条件的参数
     * @param curPage 当前要要查询的页数
     * @return 结果列表
     */
    FyResult find(final String hsql, final QueryCondition data, int curPage);

}
