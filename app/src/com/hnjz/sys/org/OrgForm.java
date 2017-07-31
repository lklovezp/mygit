/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

/**
 * ���Ź�������
 * 
 * @author wumi
 * @version $Id: OrgForm.java, v 0.1 Jan 15, 2013 5:05:03 PM wumi Exp $
 */
public class OrgForm {

	private String id;

	private String name; // ��������

	private String note; // ����

	private String bmlx; // ��������

	private String org; // �ϼ�����ID

	private String orgName; // �ϼ���������

	private String orderby;

	private String gzdw; // ������λ

	private String leader; // �����쵼

	private String leaderName; // �����쵼

	private String area; // ��������

	private String areaName; // ��������

	private String dominarea; // ��Ͻ����
	private String dominareaName; // ��Ͻ����
	
	private String biztype; // ҵ������ 0ִ�� 1����
	
	private String isActive; // �Ƿ���Ч

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

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

	public String getBmlx() {
		return bmlx;
	}

	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public void setDominarea(String dominarea) {
		this.dominarea = dominarea;
	}

	public String getDominarea() {
		return dominarea;
	}

	public void setDominareaName(String dominareaName) {
		this.dominareaName = dominareaName;
	}

	public String getDominareaName() {
		return dominareaName;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsActive() {
		return isActive;
	}
}