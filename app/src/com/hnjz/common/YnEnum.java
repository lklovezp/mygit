/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * �Ƿ��ö��
 * 
 * @author wumi
 * @version $Id: BaseEnum.java, v 0.1 Jan 12, 2012 8:36:55 AM Administrator Exp $
 */
public enum YnEnum {

    /**��*/
    Y("Y", "��"),
    /**��*/
    N("N", "��"), ;

    /***
     * 
      * �������ܣ�
            �õ��Ƿ�ö������  ������
     * ���������
    
     * ����ֵ��
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (YnEnum ele : values()) {
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
        for (YnEnum ele : values()) {
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