/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.quarterChecktimeSet;

/**
 * ���ȳ��ʱ�����õ�Form
 * @author shiqiuhan
 * @created 2016-3-17,����03:02:59
 */
public class QuarterChecktimeSetForm {
	/** ���� */
	private String id;
	/** ��� */
	private String year;
	/** ���� */
	private String quarter;
	/** ���� */
	private String area;
	/** ���ʱ�� */
	private String time;
	/** �Ƿ���ִ�� */
	private String executed;
	/** �Ƿ���Ч */
	private String isActive;
	/** ���� */
	private Integer orderby;

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

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getExecuted() {
		return executed;
	}

	public void setExecuted(String executed) {
		this.executed = executed;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}