package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DISCRECASECLASS 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:04 CST 2015
 * 功能描述：情形分类
 */ 

public class TDataDiscrecaseclass extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 115329370758801838L;
	/** 法律依据标识 */
	private TDataDiscremerit tDataDiscremerit;
	/** 名称 */
	private String name;
	/** 内容 */
	private String content;
	
	public TDataDiscremerit gettDataDiscremerit() {
		return tDataDiscremerit;
	}
	public void settDataDiscremerit(TDataDiscremerit tDataDiscremerit) {
		this.tDataDiscremerit = tDataDiscremerit;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
}

