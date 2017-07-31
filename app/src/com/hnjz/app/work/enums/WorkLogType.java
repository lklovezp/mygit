/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * �������ͣ���ҪΪ��ѯ�ͼ�Ч�ã�����������ת����Ҫ
 * 
 * @author wumi
 * @version $Id: WorkLogType.java, v 0.1 Feb 19, 2013 5:07:41 PM wumi Exp $
 */
public enum WorkLogType {
    /**�ɷ�*/
    PF("001", "�ɷ�"),
    /**ת��*/
    ZF("002", "ת��"),
    /**����*/
    JS("004", "����"),
    /**ִ��*/
    ZX("008", "����"),
//    /**��ͬ*/
//    PT("009", "��ͬ"),
//    /**��¼*/
//    JL("010", "��¼"),
//    /**�ϱ�*/
    SB("012", "�ϱ�"),
    XP("015", "����"),
    /**���*/
    SH("016", "���"),
    /**�˻�*/
    TH("017", "�˻�"),
    /**�鵵*/
    GD("020", "�鵵"), ;

    /**
     * �õ�ʱ������
     * 
     * @return 
     */
    public static List<Combobox> getTimeTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        re.add(new Combobox(PF.getCode(), PF.getText()));
        re.add(new Combobox(ZF.getCode(), ZF.getText()));
        re.add(new Combobox(JS.getCode(), JS.getText()));
        re.add(new Combobox(ZX.getCode(), ZX.getText()));
        re.add(new Combobox(SH.getCode(), SH.getText()));
//        re.add(new Combobox(SH.getCode().concat(",").concat(TH.getCode()), SH.getText()));
        re.add(new Combobox(GD.getCode(), GD.getText()));
        re.add(new Combobox(TH.getCode(), TH.getText()));
        return re;
    }

    /**
     * �õ���������
     * 
     * @return
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (WorkLogType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (WorkLogType ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getText();
            }
        }
        return null;
    }

    /** ���� */

    private String code;
    /** ���� */
    private String text;

    private WorkLogType(String code, String text) {
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