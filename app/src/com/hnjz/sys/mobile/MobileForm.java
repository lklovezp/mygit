package com.hnjz.sys.mobile;

/**
 * 
 * Description: �ֻ����ܹ��� ���ݴ��ݶ��� MobileForm.java Create on Apr 22, 2013 12:00:26 PM
 * 
 * @author xuguanghui
 * @version 1.0 Copyright (c) 2013 Company,Inc. All Rights Reserved
 */
public class MobileForm {

	private String id;
	private String name;// ��������
	private String pid;// ������id
	private String pname;// ������name
	private String style;
	private String activity;//
	private String icon;// ͼƬ����
	private String describe;// ��������
	private int orderby;// ��ʾ˳��
	private String isActive;// �Ƿ���Ч
	private String version;// �Ƿ���Ч
	
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