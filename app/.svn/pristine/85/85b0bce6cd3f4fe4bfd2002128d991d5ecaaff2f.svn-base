package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字段输入数据来源枚举
 *
 */
public enum DataSourceEnum {
	yxzd("4","1","有效字典","ztList.json"),
	kzlxzd("4","2","控制类型字典","kzlxList.json"),
	ssxzqs("6","3","所属行政区树","regionTree.json"),
	hyxl("4","4","行业下拉","industryList.json"),
	spjgzd("4","5","审批机关字典","dicList.json?type=10"),
	jsjdjsczt("4","6","建设进度及生产状态","dicList.json?type=8"),
	jsxz("5","7","建设性质","dicList.json?type=9"),
	ssjgbms("6","8","所属监管部门树","orgTree.json"),
	sfzd("4","9","是否字典","sfList.json"),		//单选：是、否
	qyzt("4","10","企业状态","qyztList.json"),		//企业运营状态
	jzgdzt("4","11","建筑工地状态","dicList.json?type=27"),//建筑工地状态
	xqyzzl("5","12","畜禽养殖种类","dicList.json?type=28"),//畜禽养殖种类
	zclx("4","13","注册类型","dicList.json?type=30"),//工业污染源注册类型
	gklx("5","14","国控类型","dicList.json?type=32"),//工业污染源国控类型
	lsgx("4","15","隶属关系","dicList.json?type=31"),//工业污染源隶属关系
	yqgx("4","16","和央企关系","dicList.json?type=33"),//工业污染源和央企关系
	;
	private DataSourceEnum(String inputType,String code, String name, String url) {
		this.inputType = inputType;
		this.code = code;
		this.name = name;
		this.url = url;
	}
	
	private String inputType;

	private String code;

	private String name;
	
	private String url;

	/**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static List<Combobox> getDataSourceList(String inputType) {
		List<Combobox> list = new ArrayList<Combobox>();
		for (DataSourceEnum inputTypeEnum : values()) {
			if(StringUtils.isNotBlank(inputType) && inputType.equals(inputTypeEnum.inputType)){
				list.add(new Combobox(inputTypeEnum.code, inputTypeEnum.name));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static DataSourceEnum getDataSourceByCode(String code) {
		for (DataSourceEnum dataSourceEnum : values()) {
			if(StringUtils.isNotBlank(code) && code.equals(dataSourceEnum.code)){
				return dataSourceEnum;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
