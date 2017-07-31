package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_VERSION 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:02 CST 2015
 * 功能描述：版本信息
 */ 

@SuppressWarnings("serial")
public class TDataVersion extends BaseObject {
	/** 版本号，如20150101 */
	private String code;
	/** 版本名称，如V2.0.0 */
	private String name;
	/** 是否强制安装或重新 Y/N */
	private String isforce;
	/** 安装包类型
0 终端
1 单机版
2 单机数据包
3 帮助文档
 */
	private String type;

	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setIsforce(String isforce){
		this.isforce = isforce;
	}
	public String getIsforce(){
		return isforce;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
}

