/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSet;

/**
 * �����������õı�������
 * @author shiqiuhan
 * @created 2015-12-24,����02:02:25
 */
public class DrafterSetForm {
	/** ���� */
	private String id;
	/** �����*/
	private String audits;
	/** ����*/
	private String area;
	/** ����*/
	private String orgid;
	/** ��˼���*/
	private String type;
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

	public String getAudits() {
		return audits;
	}

	public void setAudits(String audits) {
		this.audits = audits;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
}