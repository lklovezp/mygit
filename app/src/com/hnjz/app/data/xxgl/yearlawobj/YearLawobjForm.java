/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

/**
 * ��ȳ�����ı�������
 * @author shiqiuhan
 * @created 2016-3-10,����04:08:15
 */
public class YearLawobjForm {
	/** ���� */
	private String id;
	/** ��� */
	private String year;
	/** ������ */
	private String lawobj;
	/** ���� */
	private String area;
	/** ִ���������� */
	private String lawobjtype;
	/** ִ���������� */
	private String lawobjname;
	/** ��ѡ����ҵ���� 
	 *  0 �ص���ҵ 1 һ����ҵ 2������ҵ*/
	private String  type;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLawobj() {
		return lawobj;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setLawobj(String lawobj) {
		this.lawobj = lawobj;
	}
	public String getLawobjtype() {
		return lawobjtype;
	}
	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjname() {
		return lawobjname;
	}
	public void setLawobjname(String lawobjname) {
		this.lawobjname = lawobjname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}