package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�������ֶ�����������Դö��
 *
 */
public enum DataSourceEnum {
	yxzd("4","1","��Ч�ֵ�","ztList.json"),
	kzlxzd("4","2","���������ֵ�","kzlxList.json"),
	ssxzqs("6","3","������������","regionTree.json"),
	hyxl("4","4","��ҵ����","industryList.json"),
	spjgzd("4","5","���������ֵ�","dicList.json?type=10"),
	jsjdjsczt("4","6","������ȼ�����״̬","dicList.json?type=8"),
	jsxz("5","7","��������","dicList.json?type=9"),
	ssjgbms("6","8","������ܲ�����","orgTree.json"),
	sfzd("4","9","�Ƿ��ֵ�","sfList.json"),		//��ѡ���ǡ���
	qyzt("4","10","��ҵ״̬","qyztList.json"),		//��ҵ��Ӫ״̬
	jzgdzt("4","11","��������״̬","dicList.json?type=27"),//��������״̬
	xqyzzl("5","12","������ֳ����","dicList.json?type=28"),//������ֳ����
	zclx("4","13","ע������","dicList.json?type=30"),//��ҵ��ȾԴע������
	gklx("5","14","��������","dicList.json?type=32"),//��ҵ��ȾԴ��������
	lsgx("4","15","������ϵ","dicList.json?type=31"),//��ҵ��ȾԴ������ϵ
	yqgx("4","16","�������ϵ","dicList.json?type=33"),//��ҵ��ȾԴ�������ϵ
	;
	private DataSourceEnum(String inputType,String code, String name, String url) {
		this.inputType = inputType;
		this.code = code;
		this.name = name;
		this.url = url;
	}
	
	private String inputType;

	private String code;

	private String name;
	
	private String url;

	/**
	 * 
	 * �������ܣ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static List<Combobox> getDataSourceList(String inputType) {
		List<Combobox> list = new ArrayList<Combobox>();
		for (DataSourceEnum inputTypeEnum : values()) {
			if(StringUtils.isNotBlank(inputType) && inputType.equals(inputTypeEnum.inputType)){
				list.add(new Combobox(inputTypeEnum.code, inputTypeEnum.name));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * �������ܣ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static DataSourceEnum getDataSourceByCode(String code) {
		for (DataSourceEnum dataSourceEnum : values()) {
			if(StringUtils.isNotBlank(code) && code.equals(dataSourceEnum.code)){
				return dataSourceEnum;
			}
		}
		return null;
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

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}