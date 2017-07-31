/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ������˵Ķ���
 * 
 * @author wumi
 * @version $Id: WorkSh.java, v 0.1 Mar 28, 2013 9:30:11 AM wumi Exp $
 */
public enum WorkSh {

    /*�ϼ����**/
    SJSH(0, "�ύ�ϼ����"),
    /*�ϱ��ϼ���λ**/
    SB(3, "�ϱ��ϼ���λ"),
    /*�鵵**/
    GD(1, "�鵵"),
    /*�˻�**/
    TH(2, "�˻�"),
    /*�˻ص���**/
    THDS(3, "�˻�"),;
    /** ���� */
    private int    code;
    /** ���� */
    private String text;

    private WorkSh(int code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * ����MAP
     * @param e
     * @return
     */
    public static Map<String, Object> toMap(WorkSh e) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", e.getCode());
        map.put("text", e.getText());
        return map;
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