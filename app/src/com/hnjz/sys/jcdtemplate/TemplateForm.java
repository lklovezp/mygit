/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

/**
 * 监察模板的Form
 * 
 * @author wumi
 * @version $Id: TemplateForm.java, v 0.1 2013-3-25 下午03:33:13 wumi Exp $
 */
public class TemplateForm {
	/** 检查单模板id */
	private String id;
	/** 检查单模板名称 */
	private String name;
	/** 所属父模板 */
	private String pid;
	/** 行业标识（只有模板的根节点需要关联到行业上） */
	private String industry;
	/** 任务类型（只有根节点需要关联到任务类型） */
	private String tasktype;
	/** 是否必选Y/N */
	private String isrequired;
	/** 版本 */
	private Integer release;
	/** 是否当前版本（Y/N） */
	private String iscurver;
	/** 子节点个数 */
	private Integer childnum;
	/** 备注 */
	private String describe;
	/** 排序 */
	private Integer orderby;
	/**
	 * 操作类型 
	 * 1：新建监察模板
	 * 2：编辑监察模板
	 * 3：编辑版本
	 * 4：编辑子模板
	 * 5：跳转到检查项列表
	 */
	private String operate;
	/** 页面标题 */
	private String title;
	/** 是否为复制模板或版本 */
	private String isCopy;
	/** 生成检查记录文档时模板名称是否显示 */
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
