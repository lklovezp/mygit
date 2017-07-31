/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.dao.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author wumi
 * @version $Id: Sql.java, v 0.1 2013-10-12 上午11:13:31 wumi Exp $
 */
public class Sql {

    /**查询条数的sql*/
    private String              countSql;
    /**查询列表的sql*/
    private String              listSql;
    /**sql*/
    private String              endSql;
    /**可以做非空判断的查询条件*/
    private Map<String, String> notBlanks;

    public String getCountSql() {
        return countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public String getListSql() {
        return listSql;
    }

    public void setListSql(String listSql) {
        this.listSql = listSql;
    }

    public String getEndSql() {
        return endSql;
    }

    public void setEndSql(String endSql) {
        this.endSql = endSql;
    }

    public Map<String, String> getNotBlanks() {
        return notBlanks;
    }

    public void setNotBlanks(Map<String, String> notBlanks) {
        this.notBlanks = notBlanks;
    }

    public void addBlank(String name, String value) {
        if (null == notBlanks) {
            notBlanks = new HashMap<String, String>();
        }
        notBlanks.put(name, value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
