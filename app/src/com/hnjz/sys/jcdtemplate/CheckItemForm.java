/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

/**
 * �����Form
 * 
 * @author wumi
 * @version $Id: CheckItemForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class CheckItemForm {

	private String id;
	/** ���ģ������ */
	private String templatename;
	/** ���ģ��id */
	private String templateid;
	/** ��������� */
	private String contents;
	/** ������� */
	private String code;
	/** ��������ݵ�λ���������� */
	private String contentsunit;
	/**
	 * �������� 0 ��� 1 ��ѡ 2 ��ѡ
	 */
	private String inputtype;
	/** ������һ����ʾY/N */
	private String isanswernewline;
	/** ȡִ���������Ϣ */
	private String getlawobjvalue;
	/** ���� */
	private String describe;
	/** �������Ƿ���� */
	private String isrequired;
	/** ���� */
	private Integer orderby;
	/** �Ƿ���� */
	private String isActive;
	/** ������ */
	private String data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getContentsunit() {
		return contentsunit;
	}
	public void setContentsunit(String contentsunit) {
		this.contentsunit = contentsunit;
	}
	public String getInputtype() {
		return inputtype;
	}
	public void setInputtype(String inputtype) {
		this.inputtype = inputtype;
	}
	public String getIsanswernewline() {
		return isanswernewline;
	}
	public void setIsanswernewline(String isanswernewline) {
		this.isanswernewline = isanswernewline;
	}
	public String getGetlawobjvalue() {
		return getlawobjvalue;
	}
	public void setGetlawobjvalue(String getlawobjvalue) {
		this.getlawobjvalue = getlawobjvalue;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getIsrequired() {
		return isrequired;
	}
	public void setIsrequired(String isrequired) {
		this.isrequired = isrequired;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsActive() {
		return isActive;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}