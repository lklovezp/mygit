/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

/**
 * sql解析的工具类
 * 
 * @author wumi
 * @version $Id: SqlUtil.java, v 0.1 Dec 20, 2011 7:19:55 AM Administrator Exp $
 */
public class SqlUtil {

    /**
     * 将一个查询列表的sql转为查数目的sql
     * select * from user where 1=1 --> select count(1) from user where 1=1
     * 
     * @param sql 查询列表的sql
     * @return 查数目的sql
     */
    public static String getCountSql(String sql) {
        int start = sql.indexOf("from");
        String str = sql.substring(start, sql.length());
        str = "select count(*) ".concat(str);
        return str;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        String str = "select * from user where 1=1";
        //System.out.print(getCountSql(str));
    }

}
