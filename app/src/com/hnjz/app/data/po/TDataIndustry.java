package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysDic;

/**
 * T_DATA_INDUSTRY 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:03 CST 2015
 * 功能描述：行业
 */ 

@SuppressWarnings("serial")
public class TDataIndustry extends BaseObject {
	/** 名称 */
	private String name;
	/**  */
	private String pid;
    /**行业代码 */
	private String code;
	/** 执法对象类型
01	工业污染源
02	建设项目
03	医院
04	锅炉
05	建筑工地
06	三产
07	畜禽养殖
08  服务业
09  饮食业
10  三产制造业
11  娱乐业 */
	private String lawobjtype;
	/** 行业可转执法对象类型 */
	private String tolawobjtype;

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
	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
	public void setTolawobjtype(String tolawobjtype) {
		this.tolawobjtype = tolawobjtype;
	}
	public String getTolawobjtype() {
		return tolawobjtype;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

