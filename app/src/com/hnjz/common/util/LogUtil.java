/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common.util;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ��־�Ĺ�����
 * 
 * @author wumi
 * @version $Id: LogUtil.java, v 0.1 Jan 12, 2012 8:32:57 AM Administrator Exp $
 */
public class LogUtil {

    /**
     * ��һ������toString��һ�����е�
     * 
     * @param o ҪtoString�Ķ���
     * @return �ַ���
     */
    public static String m(Object o) {
        if (null == o) {
            return null;
        }
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }

}