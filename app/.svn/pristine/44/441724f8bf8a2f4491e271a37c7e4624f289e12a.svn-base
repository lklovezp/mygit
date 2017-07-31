/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common.util;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 日志的工具类
 * 
 * @author wumi
 * @version $Id: LogUtil.java, v 0.1 Jan 12, 2012 8:32:57 AM Administrator Exp $
 */
public class LogUtil {

    /**
     * 将一个对象toString成一个多行的
     * 
     * @param o 要toString的对象
     * @return 字符串
     */
    public static String m(Object o) {
        if (null == o) {
            return null;
        }
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }

}
