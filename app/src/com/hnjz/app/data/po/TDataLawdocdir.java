package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWDOCDIR 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:03 CST 2015
 * 功能描述：执法文件目录
 */ 

@SuppressWarnings("serial")
public class TDataLawdocdir extends BaseObject {
	/** 名称 */
	private String name;
	/** 父Id */
	private String pid;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
}

