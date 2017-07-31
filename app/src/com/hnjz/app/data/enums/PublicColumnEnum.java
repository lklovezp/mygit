package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�����󹫹��ֶ�ö��ֵ
 *
 */
public enum PublicColumnEnum {
	mc("01","����"),
	ssxzq("02","����������"),
	hylx("03","��ҵ����"),
	fddbr("04","����������"),
	fddbrlxdh("05","������������ϵ�绰"),
	hbfzr("06","����������"),
	hbfzrlxdh("07","������������ϵ�绰"),
	jd("08","����"),
	wd("09","γ��"),
	cjr("10","������"),
	dz("11","��ַ"),
	;
	private PublicColumnEnum(String code, String name) {
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
		for (PublicColumnEnum inputTypeEnum : values()) {
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