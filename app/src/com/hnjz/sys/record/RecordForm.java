/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.record;

/**
 * ��¼�����Form
 * 
 * @author wumi
 * @version $Id: RecordForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class RecordForm {
	/** ������id */
	private String id;
	/** ���������� */
	private String content;
	/** ���� */
	private String describe;
	/** ��ʾ���� */
	private String tsnr;
	/** Υ������ID */
	private String wflx;
	/** ִ���������� */
	private String lawobjtype;
	/** ����ѯ�����ֱ�� 0���� 1ѯ�� */
	private String kcxwbj;
	/** ��������ID */
	private String tasktype;
	/** �Ƿ��ɾ�� */
	private String isdel;
	/** ���� */
	private Integer orderby;
	/** �汾�� */
	private Integer vernum;
	/** �Ƿ�ǰ�汾Y/N */
	private String iscurver;
	/** �Ƿ��Զ�����Y/N */
	private String iszdjz;
	/** �Ƿ��Զ�����Y/N */
	private String isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getTsnr() {
		return tsnr;
	}

	public void setTsnr(String tsnr) {
		this.tsnr = tsnr;
	}

	public String getWflx() {
		return wflx;
	}

	public void setWflx(String wflx) {
		this.wflx = wflx;
	}

	public String getKcxwbj() {
		return kcxwbj;
	}

	public void setKcxwbj(String kcxwbj) {
		this.kcxwbj = kcxwbj;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getVernum() {
		return vernum;
	}

	public void setVernum(Integer vernum) {
		this.vernum = vernum;
	}

	public String getIscurver() {
		return iscurver;
	}

	public void setIscurver(String iscurver) {
		this.iscurver = iscurver;
	}

	public String getIszdjz() {
		return iszdjz;
	}

	public void setIszdjz(String iszdjz) {
		this.iszdjz = iszdjz;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}

	public String getLawobjtype() {
		return lawobjtype;
	}
}