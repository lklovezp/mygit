/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.util.DateUtil;

/**
 * ����ֵת��Ϊ���ַ����� {@link HashMap}
 * 
 * @author wumi
 * @version $Id: NullControllHashMap.java, v 0.1 2013-8-28 ����9:11:31 wumi Exp $
 */
public class NullControllHashMap extends HashMap<String, String> {

    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    public String put(String key, String value) {
        if (StringUtils.isBlank(value)) {
            value = "";
        }
        return super.put(key, value);
    }

    /**
     * ��Map�з���{@link Date}���͵�ֵ<br>
     * Null --> ""<br>
     * Object --> String.valueOf(value) ��ʽ���ַ���<br>
     * 
     * @param key map�е�key
     * @param value map�е�value
     * @return value
     */
    public String put(String key, Object value) {
        if (null == value) {
            value = "";
        }
        return super.put(key, String.valueOf(value));
    }

    /**
     * ��Map�з���{@link Date}���͵�ֵ<br>
     * Null --> ""<br>
     * Date --> yyyy-MM-dd ��ʽ���ַ���<br>
     * 
     * @param key map�е�key
     * @param value map�е�value
     * @return value
     */
    public String put(String key, Date value) {
        if (null == value) {
            return super.put(key, "");
        }
        return super.put(key, DateUtil.getDate(value));
    }

}