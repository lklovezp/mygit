/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

/**
 * ��ɫ�ı�������
 * 
 * @author wumi
 * @version $Id: RoleFrm.java, v 0.1 Dec 28, 2011 10:12:52 AM Administrator Exp
 *          $
 */
public class RoleForm {
	/** ���� */
	private String id;
	/** ���� */
	private String name;
	/** ˵�� */
	private String note;
	/** �Ƿ���Ч */
	private String isActive;
	/** �Ƿ����Ա��ɫ */
	private String isSys;
	/** ���� */
	private Integer orderby;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getOrderby() {
		return orderby;
	}

}