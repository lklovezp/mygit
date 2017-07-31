package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DOTEMPLATE 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:04 CST 2015
 * 功能描述：任务操作模板
 */ 

@SuppressWarnings("serial")
public class TDataDotemplate extends BaseObject {
	/**  */
	private String name;
	/**  */
	private Long auditlevel;
	/** 后台办理跳转地址 */
	private String weburl;
	/** 终端办理跳转地址 */
	private String mobileurl;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAuditlevel(Long auditlevel){
		this.auditlevel = auditlevel;
	}
	public Long getAuditlevel(){
		return auditlevel;
	}
	public void setWeburl(String weburl){
		this.weburl = weburl;
	}
	public String getWeburl(){
		return weburl;
	}
	public void setMobileurl(String mobileurl){
		this.mobileurl = mobileurl;
	}
	public String getMobileurl(){
		return mobileurl;
	}
}

