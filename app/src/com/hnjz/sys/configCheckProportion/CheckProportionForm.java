/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

/**
 * ���ȱ����ı�������
 * @author shiqiuhan
 * @created 2015-12-16,����01:40:59
 */
public class CheckProportionForm {
	/** ���� */
	private String id;
	/** ��� */
	private String year;
	/** ���� */
	private String quarter;
	/** ������ */
	private String proportion;
	/** �Ƿ���Ч */
	private String isActive;
	/** ���� */
	private Integer orderby;
	/** ������1*/
	private String proportion1;
	/** ������2*/
	private String proportion2;

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

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
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

	public String getProportion1() {
		return proportion1;
	}

	public void setProportion1(String proportion1) {
		this.proportion1 = proportion1;
	}

	public String getProportion2() {
		return proportion2;
	}

	public void setProportion2(String proportion2) {
		this.proportion2 = proportion2;
	}

}