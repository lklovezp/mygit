/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.operlog;

import java.util.Date;

import com.hnjz.app.work.po.Work;

/**
 * ������־��Form
 * 
 * @author wumi
 * @version $Id: OperLogForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class OperLogForm {

	private String id;
	/** ���� */
	private Work work;
	/** ���������� */
	private String czrId;
	/** ���������� */
	private String czrName;
	/** ����״̬ */
	private String workSate;
	/** �������� */
	private String operateType;
	/** ����ʱ�� */
	private Date czsj;
	/** ��ע */
	private String note;
	/** ��ʼʱ�� */
	private Date startTime;
	/** ʹ��ʱ�� */
	private Long userTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getCzrId() {
		return czrId;
	}

	public void setCzrId(String czrId) {
		this.czrId = czrId;
	}

	public String getCzrName() {
		return czrName;
	}

	public void setCzrName(String czrName) {
		this.czrName = czrName;
	}

	public String getWorkSate() {
		return workSate;
	}

	public void setWorkSate(String workSate) {
		this.workSate = workSate;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getUserTime() {
		return userTime;
	}

	public void setUserTime(Long userTime) {
		this.userTime = userTime;
	}
}