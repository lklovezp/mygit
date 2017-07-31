/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * �û��ļ������磺�ܶӣ����ӣ���Ա������Խ�ͣ�����ԽС
 * 
 * @author wumi
 * @version $Id: UserPosition.java, v 0.1 Mar 27, 2013 3:46:08 PM wumi Exp $
 */
public enum UserPosition {
    /**��Ա*/
    KY(0, "��Ա"),
    /**�Ƴ�*/
    KZ(4, "��/�Ƴ�"),
    /**����*/
    FD(12, "����"),
    /**�칫��*/
    BGS(13, "�칫��"),
    /**�ܶ�*/
    ZD(16, "�ܶ�"), ;

    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (UserPosition ele : values()) {
            re.add(new Combobox(String.valueOf(ele.getCode()), ele.getText()));
        }
        return re;
    }
    
    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (UserPosition ele : values()) {
            if (ele.getCode() == Integer.parseInt(code)) {
                return ele.getText();
            }
        }
        return null;
    }

    /** ���� */
    private int    code;
    /** ���� */
    private String text;

    private UserPosition(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}