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
 * @version $Id: AreaType.java, v 0.1 2013-3-25 ����04:05:47 wumi Exp $
 */
public enum AreaType {
    /**ʡ��*/
    BT("0", "����"),
    /**�м�*/
    S("1", "ʦ"),
    /**����*/
    TC("2", "�ų�"), ;

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

    /** ���� */
    private String code;
    /** ���� */
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