/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle10gDialect;

/**
 * oracle数据类型的转换
 * 
 * @author wumi
 * @version $Id: OracleDialectEx.java, v 0.1 Jun 21, 2012 3:19:30 PM wumi Exp $
 */
public class OracleDialectEx extends Oracle10gDialect {

    public OracleDialectEx() {
        super();
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
        registerHibernateType(Types.CHAR, Hibernate.STRING.getName());
    }
}
