package com.hnjz.sys.mobile;

/**
 * 
 * Description: 手机功能管理 数据传递对象 MobileForm.java Create on Apr 22, 2013 12:00:26 PM
 * 
 * @author xuguanghui
 * @version 1.0 Copyright (c) 2013 Company,Inc. All Rights Reserved
 */
public class MobileForm {

	private String id;
	private String name;// 功能名称
	private String pid;// 父功能id
	private String pname;// 父功能name
	private String style;
	private String activity;//
	private String icon;// 图片名称
	private String describe;// 功能描述
	private int orderby;// 显示顺序
	private String isActive;// 是否有效
	private String version;// 是否有效
	
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getOrderby() {
		return orderby;
	}
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPname() {
		return pname;
	}
}
