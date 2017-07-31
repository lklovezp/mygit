/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

/**
 * 违法类型的Form
 * 
 * @author wumi
 * @version $Id: AreaForm.java, v 0.1 2013-3-25 下午03:33:13 wumi Exp $
 */
public class IllegalTypeForm {

	private String id;
	
	private String name;
	
	private String pid;
	
	private Integer orderby;
	
	private String describe;

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

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
