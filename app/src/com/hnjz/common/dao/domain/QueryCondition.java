/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.util.DateUtil;

/**
 * 查询条件
 * 
 * @author wumi
 * @version $Id: QueryPCondition.java, v 0.1 Dec 27, 2011 9:22:38 AM Administrator Exp $
 */
public class QueryCondition {
    /**查询语句中的参数*/
    private Map<String, Object> canshu = new HashMap<String, Object>();

    private String              pageSize;

    /**
     * 放入查询条件,精确查询
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void put(String key, Object[] value) {
        canshu.put(key, value);
    }

    /**
     * 放入查询条件,精确查询
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void put(String key, Object value) {
        if (value instanceof String) {
            value=StringUtils.trim(String.valueOf(value));
        }
        canshu.put(key, value);
    }

    /**
     * 放入查询条件,模糊查询
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void putLike(String key, String value) {
        value=StringUtils.trim(value);
        canshu.put(key, "%".concat(value).concat("%"));
    }

    /**
     * 放入查询条件,模糊查询（左匹配）
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void putLikeL(String key, String value) {
        value=StringUtils.trim(value);
    	canshu.put(key, value.concat("%"));
        
    }

    /**
     * 放入查询条件,将结束日期+1
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void putEndDate(String key, String value) {
        value = DateUtil.getNextDate(value, 1);
        canshu.put(key, value);
    }

    /**
     * 放入查询条件,模糊查询（右匹配）
     * 
     * @param key 查询条件key
     * @param value 查询条件value
     */
    public void putLikeR(String key, String value) {
        value=StringUtils.trim(value);
    	canshu.put(key, "%".concat(value));
    }

    public Map<String, Object> getCanshu() {
        return canshu;
    }

    public void setCanshu(Map<String, Object> canshu) {
        this.canshu = canshu;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

}
