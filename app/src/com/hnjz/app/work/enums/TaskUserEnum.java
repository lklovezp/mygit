/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ������Աö��
 * ���ߣ�xb
 * �������ڣ�Apr 8, 2015
 * ����������

 *
 */
public enum TaskUserEnum {
	//�������
    DJR("00", "�Ǽ���"),
    SCR("01", "������"),
    PFR("02", "�ɷ���"),
    ZPR("03", "ת����"),
    //rzq�¼�������������ͳ��
    XPR("37", "������"),
    
    JSR("04", "������"),
    ZBR("05", "������"),
    XBR("06", "Э����"),
    SH("07", "���"),
    TH("08", "�˻�"),
    GD("09", "�鵵"),
    
    //��������-�ֳ����
    RCJC_JCR("10", "�ֳ����.�����"),
    RCJC_JLR("11", "�ֳ����.��¼��"),
    //��������-��Ⱥ˲�
    NDHC_JCR("12", "��Ⱥ˲�.�����"),
    NDHC_JLR("13", "��Ⱥ˲�.��¼��"),
    //��������-�󶽲�
    HDC_JCR("14", "�󶽲�.�����"),
    HDC_JLR("15", "�󶽲�.��¼��"),
    //��������-�ŷ�Ͷ��
    XFTS_JCR("16", "�ŷ�Ͷ��.�����"),
    XFTS_JLR("17", "�ŷ�Ͷ��.��¼��"),
    //��������-��������֤���
    PWXKZJC_JCR("18", "��������֤���.�����"),
    PWXKZJC_JLR("19", "��������֤���.��¼��"),
    //��������-ר��������
    ZXXD_JCR("20", "ר��������.�����"),
    ZXXD_JLR("21", "ר��������.��¼��"),
    //��������-Υ����������
    WFAJDC_JLR("22", "Υ����������.��¼��"),
    WFAJDC_KCBLJCKCR("61", "Υ����������.�����¼��飨���죩��"),
    WFAJDC_KCBLJLR("62", "Υ����������.�����¼��¼��"),
    //��������-��������
    XQZL_JCR("23", "��������.�����"),
    XQZL_JLR("24", "��������.��¼��"),
    //��������-���ټ��
    GZJC_JCR("25", "���ټ��.�����"),
    GZJC_JLR("26", "���ټ��.��¼��"),
    //��������-�Զ����
    ZDJK_JCR("27", "�Զ����.�����"),
    ZDJK_JLR("28", "�Զ����.��¼��"),
    //��������-Σ�շ���
    WXFW_JCR("29", "Σ�շ���.�����"),
    WXFW_JLR("30", "Σ�շ���.��¼��"),
    //��������-Σ�ջ�ѧƷ
    WXHXP_JCR("31", "Σ�ջ�ѧƷ.�����"),
    WXHXP_JLR("32", "Σ�ջ�ѧƷ.��¼��"),
    //��������-���䰲ȫ
    FSAQ_JCR("33", "���䰲ȫ.�����"),
    FSAQ_JLR("34", "���䰲ȫ.��¼��"),
    //��������-��Ⱦ�¹��ֳ�����
    WRSGXCDC_JCR("35", "��Ⱦ�¹��ֳ�����.�����"),
    WRSGXCDC_JLR("36", "��Ⱦ�¹��ֳ�����.��¼��");

    /**
     * ��ȡ���е�������Ա���ͣ���������ʹ��
     * 
     * @return ������Ա����
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (TaskUserEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * ����code�õ�����
     * @param code
     * @return
     */
    public static TaskUserEnum getByCode(String code) {
        TaskUserEnum ryLx = null;
        for (TaskUserEnum ele : values()) {
            if (ele.getCode().equals(code)) {
            	ryLx = ele;
                break;
            }
        }
        return ryLx;
    }
    
    public static String getCodeByEnum(String enumName){
		String code = "";
		for (TaskUserEnum ele : values()) {
			if (ele.name().equals(enumName)) {
				code = ele.getCode();
				break;
			}
		}
		if ("".equals(code)){
			code = enumName;
		}
		return code;
	}

    /** ���� */
    private String code;
    /** ���� */
    private String text;

    private TaskUserEnum(String code, String text) {
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