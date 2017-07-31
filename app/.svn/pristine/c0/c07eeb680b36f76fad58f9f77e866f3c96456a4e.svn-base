/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 是否的枚举
 * 
 * @author wumi
 * @version $Id: BaseEnum.java, v 0.1 Jan 12, 2012 8:36:55 AM Administrator Exp $
 */
public enum YnEnum {

    /**是*/
    Y("Y", "是"),
    /**否*/
    N("N", "否"), ;

    /***
     * 
      * 函数介绍：
            得到是否枚举类型  下拉框
     * 输入参数：
    
     * 返回值：
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (YnEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getNote()));
        }
        return re;
    }

    /**
     * 根据枚举的code获取描述
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (YnEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getNote();
            }
        }
        return null;
    }

    /** code */
    private String code;

    /** 说明 */
    private String note;

    private YnEnum(String code, String note) {
        this.code = code;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
