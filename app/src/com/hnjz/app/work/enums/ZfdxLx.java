/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ִ������ö��
 * 
 * @author wumi
 * @version $Id: ZfdxLx.java, v 0.1 Jan 28, 2013 11:17:30 AM wumi Exp $
 */
public enum ZfdxLx {
    GYWRY("01", "��ҵ��ȾԴ"),
    JSXM("02", "������Ŀ"),
    YY("03", "ҽԺ"),
    GL("04", "��¯"),
    JZGD("05", "��������"),
    SC("06", "����"),
    XQYZ("07", "������ֳ"),
    FWY("08", "����ҵ"),
    YSY("09", "��ʳҵ"),
    ZZY("10", "��������ҵ"),
    YLY("11", "����ҵ"),
    ;

    /**
     * ��ȡ���е�ִ���������ͣ���������ʹ��
     * 
     * @return ִ����������
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (ZfdxLx ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * ����code�õ�����
     * @param code
     * @return
     */
    public static ZfdxLx getByCode(String code) {
        ZfdxLx zfdxLx = null;
        for (ZfdxLx ele : values()) {
            if (ele.getCode().equals(code)) {
                zfdxLx = ele;
                break;
            }
        }
        return zfdxLx;
    }

    /** ���� */
    private String code;
    /** ���� */
    private String text;

    private ZfdxLx(String code, String text) {
        this.code = code;
        this.text = text;
    }

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