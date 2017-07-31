/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * @author wumi
 * @version $Id: AreaType.java, v 0.1 2013-3-25 下午04:05:47 wumi Exp $
 */
public enum AreaType {
    /**省级*/
    BT("0", "兵团"),
    /**市级*/
    S("1", "师"),
    /**区县*/
    TC("2", "团场"), ;

    private AreaType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (AreaType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /** 编码 */
    private String code;
    /** 文字 */
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
