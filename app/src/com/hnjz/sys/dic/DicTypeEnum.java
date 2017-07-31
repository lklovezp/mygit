/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.util.ArrayList;
import java.util.List;

/**
 * �ֵ�����
 * 
 * @author wumi
 * @version $Id: DicTypeEnum.java, v 0.1 Mar 8, 2013 3:05:25 PM wumi Exp $
 */
public enum DicTypeEnum {

	/**
	 * �ֵ�����
	 */
	RWLY("1","������Դ"),
	RWMJ("2","�����ܼ�"),
	JJCD("3","�����̶�"),
	FJLX("4","��������"),
	//LAWTYPE("5","ִ����������"),
	KZLX("6","��������"),
	ZYCL("7","���ɲ������ڵ�"),
	JSJDJSCZT("8","������ȼ�����״̬"),
	JSXZ("9","��������"),
	SPJG("10","��������"),
	XFTSLY("11","�ŷ�Ͷ����Դ"),
	JCDFH("12","��λ����"),
	FDDBR("13","����������"),
	ZW("14","ְ��"),
	ZXZZMB("15","��������ģ��"),
	ZXFS("16","��������ִ�з�ʽ"),
	CXCS("18","��������ҵ��ѡ����"),
	GJSHJB("19","�����˼���"),
	XFLY("20","�ŷ���Դ"),
	SWR("21","ˮ��Ⱦ"),
	DQWR("22","������Ⱦ"),
	ZSWR("23","������Ⱦ"),
	GFWR("24","�̷���Ⱦ"),
	FSWR("25","������Ⱦ"),
	HFXS("26","�ط���ʽ"),
	JZGDZT("27","��������״̬"),
	XQYZZL("28","������ֳ����"),
	USERGROUP("29","���̽����˷���"),
	ZCLX("30","��ҵ��ȾԴע������"),
	LSGX("31","��ҵ��ȾԴ������ϵ"),
	GKLX("32","��ҵ��ȾԴ��������"),
	YQGX("33","��ҵ��ȾԴ�����ϵ"),
	STHJWR("34","��̬������Ⱦ"),
	;
	
	/** ���� */
	private String code;
	/** ���� */
	private String text;

	private DicTypeEnum(String code, String text) {
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

	/**
	 * ����ö�ٵ�code��ȡ����
	 * 
	 * @param code 
	 */
	public static String getText(String code) {
		for (DicTypeEnum ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.getText();
			}
		}
		return null;
	}

	/**
	 * @param code 
	 */
	public static List<DicTypeEnum> getListEnum() {
		List<DicTypeEnum> dics = new ArrayList<DicTypeEnum>();
		for (DicTypeEnum ele : values()) {
			dics.add(ele);
		}
		return dics;
	}
}