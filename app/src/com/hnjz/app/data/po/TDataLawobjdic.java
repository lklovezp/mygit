package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWOBJDIC 实体类
 * 作者：张少卫
 * 生成日期：Tue Mar 17 16:10:45 CST 2015
 * 功能描述：执法对象结构字典
 */ 

@SuppressWarnings("serial")
public class TDataLawobjdic extends BaseObject {
	/**  */
	private String lawobjtypeid;
	/** 列名（英文） */
	private String colengname;
	/** 列名（中文） */
	private String colchiname;
	/** 必填 Y/N */
	private String mandatory;
	/** 名称，用来被其他功能引用时指定的名称 */
	private String enumname;
	/** 输入方式，参见相关枚举 */
	private String inputtype;
	/** 数据来源，参见相关枚举 */
	private String datasource;
	/** 是否一行显示两项内容Y/N */
	private String istwoitem;
	/** 备注 */
	private String desc;

	public void setLawobjtypeid(String lawobjtypeid){
		this.lawobjtypeid = lawobjtypeid;
	}
	public String getLawobjtypeid(){
		return lawobjtypeid;
	}
	public void setColengname(String colengname){
		this.colengname = colengname;
	}
	public String getColengname(){
		return colengname;
	}
	public void setColchiname(String colchiname){
		this.colchiname = colchiname;
	}
	public String getColchiname(){
		return colchiname;
	}
	public void setMandatory(String mandatory){
		this.mandatory = mandatory;
	}
	public String getMandatory(){
		return mandatory;
	}
	public void setEnumname(String enumname){
		this.enumname = enumname;
	}
	public String getEnumname(){
		return enumname;
	}
	public void setInputtype(String inputtype){
		this.inputtype = inputtype;
	}
	public String getInputtype(){
		return inputtype;
	}
	public void setDatasource(String datasource){
		this.datasource = datasource;
	}
	public String getDatasource(){
		return datasource;
	}
	public void setIstwoitem(String istwoitem){
		this.istwoitem = istwoitem;
	}
	public String getIstwoitem(){
		return istwoitem;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}

