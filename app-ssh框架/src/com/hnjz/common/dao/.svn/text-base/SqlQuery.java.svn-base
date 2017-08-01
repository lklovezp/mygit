/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.util.List;
import java.util.Map;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.SqlQueryCondition;

/**
 * 对于sql查询的一些封装
 * 
 * @author wumi
 * @version $Id: SqlQuery.java, v 0.1 Dec 20, 2011 6:42:32 AM Administrator Exp $
 */
public interface SqlQuery {

    /**
     * 使用sql查询，用于参数个数不固定的查询，例如： String sql = "SELECT ID,NAME from USER where
     * NAME = :name"; QueryPCondition data = new QueryPCondition();
     * data.put("name", "张三");
     * 
     * if(null != sex){ sql.applend(" and SEX = :sex"); data.put("sex", sex); }
     * dao.queryForList(sql,data); 
     * List中为Map，以本查询为例，
     * List中为Map的结构：
     *  key value 
     *  ID   1234566 
     *  NAME 张三
     * 
     * @param data
     *            sql需要的参数
     * @return 结果列表
     */
    List<Map<String, Object>> query(final SqlQueryCondition data);

    /**
     * 分页查询出结果集，示例参见：{@link Dao#find(String, Map)}
     * 
     * @param data
     *            sql查询条件的参数
     * @param curPage
     *            当前要要查询的页数
     * @return 结果列表
     */
    FyResult queryPageList(final SqlQueryCondition data, int curPage);

}
