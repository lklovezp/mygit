/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

/**
 * sql�����Ĺ�����
 * 
 * @author wumi
 * @version $Id: SqlUtil.java, v 0.1 Dec 20, 2011 7:19:55 AM Administrator Exp $
 */
public class SqlUtil {

    /**
     * ��һ����ѯ�б���sqlתΪ����Ŀ��sql
     * select * from user where 1=1 --> select count(1) from user where 1=1
     * 
     * @param sql ��ѯ�б���sql
     * @return ����Ŀ��sql
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