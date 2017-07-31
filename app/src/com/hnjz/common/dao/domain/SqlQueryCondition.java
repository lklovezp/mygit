/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.dao.domain;

/**
 * sql��ѯ�����ķ�װ
 * 
 * @author wumi
 * @version $Id: SqlQueryCondition.java, v 0.1 2013-10-12 ����11:02:14 wumi Exp $
 */
public class SqlQueryCondition extends QueryCondition {

    /**��ѯ������*/
    private String name;

    public SqlQueryCondition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}