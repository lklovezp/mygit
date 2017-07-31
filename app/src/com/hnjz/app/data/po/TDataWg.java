package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_WG 实体类
 * 作者：XUYAXING
 * 生成日期：Fri Feb 27 15:15:02 CST 2015
 * 功能描述：网格化管理
 */ 

@SuppressWarnings("serial")
public class TDataWg extends BaseObject{
  
	private String id;
	private String wgmc;
	private String ms;
	private String isActive;
	private String areaid;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWgmc() {
		return wgmc;
	}
	public void setWgmc(String wgmc) {
		this.wgmc = wgmc;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
	
}
