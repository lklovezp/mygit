/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.Date;

/**
 * ������͵ı�������
 * @author shiqiuhan
 * @created 2015-12-16,����01:40:59
 */
public class DrafterForm {
	/** ���� */
	private String id;
    /** �������*/
	private String name; 
	/** �����id*/
	private String drafterId; 
	/** ���������*/
	private String drafterName; 
	/** ����˲���id*/
	private String drafterOrgid; 
	/** ����˲�������*/
	private String drafterOrgname; 
	/** ���״̬*/
	private Integer state; 
	/** ����ύ����*/
	private Date submitDate; 
	private String submitDate1; 
	private String submitDate2; 
	/** �����id*/
	private String auditid;
	/** ���������*/
	private String auditName;
	/** �������*/
	private Date auditDate; 
	/** ��ע*/
	private String describe; 
	/** �Ƿ���Ч */
	private String isActive;
	private String areaid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditid() {
		return auditid;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDrafterId() {
		return drafterId;
	}

	public void setDrafterId(String drafterId) {
		this.drafterId = drafterId;
	}

	public String getDrafterName() {
		return drafterName;
	}

	public void setDrafterName(String drafterName) {
		this.drafterName = drafterName;
	}

	public String getDrafterOrgid() {
		return drafterOrgid;
	}

	public void setDrafterOrgid(String drafterOrgid) {
		this.drafterOrgid = drafterOrgid;
	}

	public String getDrafterOrgname() {
		return drafterOrgname;
	}

	public void setDrafterOrgname(String drafterOrgname) {
		this.drafterOrgname = drafterOrgname;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitDate1() {
		return submitDate1;
	}

	public void setSubmitDate1(String submitDate1) {
		this.submitDate1 = submitDate1;
	}

	public String getSubmitDate2() {
		return submitDate2;
	}

	public void setSubmitDate2(String submitDate2) {
		this.submitDate2 = submitDate2;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
}