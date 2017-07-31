/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

/**
 * 年度抽查对象的表单对象
 * @author shiqiuhan
 * @created 2016-3-10,下午04:08:15
 */
public class YearLawobjForm {
	/** 主键 */
	private String id;
	/** 年份 */
	private String year;
	/** 抽查对象 */
	private String lawobj;
	/** 区域 */
	private String area;
	/** 执法对象类型 */
	private String lawobjtype;
	/** 执法对象名称 */
	private String lawobjname;
	/** 抽选的企业类型 
	 *  0 重点企业 1 一般企业 2特殊企业*/
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
