/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

/**
 * 部门管理表单
 * 
 * @author wumi
 * @version $Id: OrgForm.java, v 0.1 Jan 15, 2013 5:05:03 PM wumi Exp $
 */
public class OrgForm {

	private String id;

	private String name; // 部门名称

	private String note; // 描述

	private String bmlx; // 部门类型

	private String org; // 上级部门ID

	private String orgName; // 上级部门名称

	private String orderby;

	private String gzdw; // 工作单位

	private String leader; // 主管领导

	private String leaderName; // 主管领导

	private String area; // 所属区域

	private String areaName; // 所属区域

	private String dominarea; // 管辖区域
	private String dominareaName; // 管辖区域
	
	private String biztype; // 业务类型 0执法 1其他
	
	private String isActive; // 是否有效

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
