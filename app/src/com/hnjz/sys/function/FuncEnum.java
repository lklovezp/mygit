/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.function;

/**
 * ���ܲ˵�
 * 
 * @author wumi
 * @version $Id: FuncEnum.java, v 0.1 Feb 17, 2013 5:21:28 PM wumi Exp $
 */
public enum FuncEnum {

	/** ��̨���ܹ��� */
	WEBFUNC("a0000000000000000000000000000001", "��̨���ܹ���"),
	/** ��̨Ȩ�޹��� */
	WEBPER("a0000000000000000000000000000002", "��̨Ȩ�޹���"),
	/** �ն˹��ܹ��� */
	MOFUNC("40288ace4b80bdc0014b80eab67f008b", "�ն˹��ܹ���"),
	/** �ն�Ȩ�޹��� */
	MOPER("40288ace4b80bdc0014b80ecd72a00af", "�ն�Ȩ�޹���"),
	;
	
	/**
	 * ����ö�ٵ�code��ȡ����
	 * 
	 * @param code
	 */
	public static String getNote(String code) {
		for (FuncEnum ele : values()) {
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

	private FuncEnum(String code, String note) {
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