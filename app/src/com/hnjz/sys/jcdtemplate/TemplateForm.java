/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

/**
 * ���ģ���Form
 * 
 * @author wumi
 * @version $Id: TemplateForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class TemplateForm {
	/** ��鵥ģ��id */
	private String id;
	/** ��鵥ģ������ */
	private String name;
	/** ������ģ�� */
	private String pid;
	/** ��ҵ��ʶ��ֻ��ģ��ĸ��ڵ���Ҫ��������ҵ�ϣ� */
	private String industry;
	/** �������ͣ�ֻ�и��ڵ���Ҫ�������������ͣ� */
	private String tasktype;
	/** �Ƿ��ѡY/N */
	private String isrequired;
	/** �汾 */
	private Integer release;
	/** �Ƿ�ǰ�汾��Y/N�� */
	private String iscurver;
	/** �ӽڵ���� */
	private Integer childnum;
	/** ��ע */
	private String describe;
	/** ���� */
	private Integer orderby;
	/**
	 * �������� 
	 * 1���½����ģ��
	 * 2���༭���ģ��
	 * 3���༭�汾
	 * 4���༭��ģ��
	 * 5����ת��������б�
	 */
	private String operate;
	/** ҳ����� */
	private String title;
	/** �Ƿ�Ϊ����ģ���汾 */
	private String isCopy;
	/** ���ɼ���¼�ĵ�ʱģ�������Ƿ���ʾ */
	private String namevisiable;

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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public String getIsrequired() {
		return isrequired;
	}
	public void setIsrequired(String isrequired) {
		this.isrequired = isrequired;
	}
	public Integer getRelease() {
		return release;
	}
	public void setRelease(Integer release) {
		this.release = release;
	}
	public String getIscurver() {
		return iscurver;
	}
	public void setIscurver(String iscurver) {
		this.iscurver = iscurver;
	}
	public Integer getChildnum() {
		return childnum;
	}
	public void setChildnum(Integer childnum) {
		this.childnum = childnum;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsCopy() {
		return isCopy;
	}
	public void setIsCopy(String isCopy) {
		this.isCopy = isCopy;
	}
	public String getNamevisiable() {
		return namevisiable;
	}
	public void setNamevisiable(String namevisiable) {
		this.namevisiable = namevisiable;
	}
}