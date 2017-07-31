/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
/**
 * 
 * 
 * @author wumi
 * @version $Id: MySqlDialectEx.java, v 0.1 Dec 28, 2011 5:40:39 AM Administrator Exp $
 */
public class MySqlDialectEx extends MySQLDialect {
    
    public MySqlDialectEx() {
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
        registerHibernateType(Types.CHAR, Hibernate.STRING.getName()); 
    }
}