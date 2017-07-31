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
 * ��ѯ����
 * 
 * @author wumi
 * @version $Id: QueryPCondition.java, v 0.1 Dec 27, 2011 9:22:38 AM Administrator Exp $
 */
public class QueryCondition {
    /**��ѯ����еĲ���*/
    private Map<String, Object> canshu = new HashMap<String, Object>();

    private String              pageSize;

    /**
     * �����ѯ����,��ȷ��ѯ
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
     */
    public void put(String key, Object[] value) {
        canshu.put(key, value);
    }

    /**
     * �����ѯ����,��ȷ��ѯ
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
     */
    public void put(String key, Object value) {
        if (value instanceof String) {
            value=StringUtils.trim(String.valueOf(value));
        }
        canshu.put(key, value);
    }

    /**
     * �����ѯ����,ģ����ѯ
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
     */
    public void putLike(String key, String value) {
        value=StringUtils.trim(value);
        canshu.put(key, "%".concat(value).concat("%"));
    }

    /**
     * �����ѯ����,ģ����ѯ����ƥ�䣩
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
     */
    public void putLikeL(String key, String value) {
        value=StringUtils.trim(value);
    	canshu.put(key, value.concat("%"));
        
    }

    /**
     * �����ѯ����,����������+1
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
     */
    public void putEndDate(String key, String value) {
        value = DateUtil.getNextDate(value, 1);
        canshu.put(key, value);
    }

    /**
     * �����ѯ����,ģ����ѯ����ƥ�䣩
     * 
     * @param key ��ѯ����key
     * @param value ��ѯ����value
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