/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * �������͵�ö��
 * 
 * @author wumi
 * @version $Id: WorkTypeCode.java, v 0.1 May 3, 2013 2:02:43 PM wumi Exp $
 */
public enum TaskTypeCode {

	/** 10 �ֳ���� */
    RCJC("10", "�ֳ����"),
	/** 11 ��Ⱥ˲� */
    NDHC("11", "��Ⱥ˲�"),
	/** 12 �󶽲� */
    HDC("12", "�󶽲�"),
	/** 13 �ŷ�Ͷ�� */
    XFTS("13", "�ŷ�Ͷ��"),
	/** 14 ��������֤��� */
    PWXKZJC("14", "��������֤���"),
	/** 15 ר���ж� */
    ZXXD("15", "ר���ж�"),
	/** 16 Υ���������� */
    WFAJ("16", "Υ����������"),
	/** 17 �������� */
    XQZL("17", "��������"),
	/** 18 ���ټ�� */
    GZJC("18", "���ټ��"),
	/** 19 �Զ���� */
    ZDJK("19", "�Զ����"),
	/** 20 Σ�շ��� */
    WXFW("20", "Σ�շ���"),
	/** 21 Σ�ջ�ѧƷ */
    WXHXP("21", "Σ�ջ�ѧƷ"),
	/** 22 ���䰲ȫ */
    FSAQ("22", "���䰲ȫ"),
	/** 23 ��Ⱦ�¹��ֳ����� */
    WRSGXCDC("23", "��Ⱦ�¹��ֳ�����"),
    /** 24 �ճ��칫 */
    RCBG("24", "�ճ��칫"),
	;

	public static List<Combobox> getTypes() {
		List<Combobox> re = new ArrayList<Combobox>();
		for (TaskTypeCode ele : values()) {
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
		for (TaskTypeCode ele : values()) {
			if (StringUtils.equals(code, ele.getCode())) {
				return ele.getText();
			}
		}
		return null;
	}

	/**
	 * ����ö�ٵ�code��ȡ����
	 * 
	 * @param code
	 */
	public static String getEnumByCode(String code) {
		for (TaskTypeCode ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.name();
			}
		}
		return "";
	}
	
	private TaskTypeCode(String code, String text) {
		this.code = code;
		this.text = text;
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