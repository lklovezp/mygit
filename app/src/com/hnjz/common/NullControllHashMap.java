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
 * 将空值转化为空字符串的 {@link HashMap}
 * 
 * @author wumi
 * @version $Id: NullControllHashMap.java, v 0.1 2013-8-28 上午9:11:31 wumi Exp $
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
     * 往Map中放入{@link Date}类型的值<br>
     * Null --> ""<br>
     * Object --> String.valueOf(value) 格式的字符串<br>
     * 
     * @param key map中的key
     * @param value map中的value
     * @return value
     */
    public String put(String key, Object value) {
        if (null == value) {
            value = "";
        }
        return super.put(key, String.valueOf(value));
    }

    /**
     * 往Map中放入{@link Date}类型的值<br>
     * Null --> ""<br>
     * Date --> yyyy-MM-dd 格式的字符串<br>
     * 
     * @param key map中的key
     * @param value map中的value
     * @return value
     */
    public String put(String key, Date value) {
        if (null == value) {
            return super.put(key, "");
        }
        return super.put(key, DateUtil.getDate(value));
    }

}
