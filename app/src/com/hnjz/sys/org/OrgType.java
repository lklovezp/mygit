/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;


/**
 * ��������
 * 
 * @author wumi
 * @version $Id: OrgType.java, v 0.1 Feb 17, 2013 5:21:28 PM wumi Exp $
 */
public enum OrgType {

    /**ִ���ܶ�*/
    ZD("0", "ִ���ܶ�"),
    /**����*/
    CS("1", "����"),
    /**�칫��*/
    BGS("2", "�칫��"),
    /**����*/
    KS("3", "����"),
    /**��������*/
    QT("4", "��������"),;
    
    public static List<Combobox> getTypes(){
        List<Combobox> re = new ArrayList<Combobox>();
        for (OrgType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getNote()));
        }
        return re;
    }

    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (OrgType ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getNote();
            }
        }
        return null;
    }

    /** code */
    private String code;

    /** ˵�� */
    private String note;

    private OrgType(String code, String note) {
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