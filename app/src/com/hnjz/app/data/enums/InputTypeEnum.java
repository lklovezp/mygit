package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�������ֶ����뷽ʽö��
 *
 */
public enum InputTypeEnum {
	wbk("1","�ı���"),
	wby("2","�ı���"),
	rq("3","����"),
	xllbdx("4","�����б���ѡ"),
	xllbddx("5","�����б���ѡ"),
	xls("6","������"),
	hidden("7","������"),
	number("8","��ֵ"),
	choose("9","����ѡ��"),
	zdwbk("10","ֻ���ı���"),
	jwsrk("11","��γ�������"),
	scztkj("12","����״̬�ؼ�");
	;
	private InputTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	/**
	 * 
	 * �������ܣ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static List<Combobox> getInputTypeList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (InputTypeEnum inputTypeEnum : values()) {
			list.add(new Combobox(inputTypeEnum.code, inputTypeEnum.name));
		}
		return list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}