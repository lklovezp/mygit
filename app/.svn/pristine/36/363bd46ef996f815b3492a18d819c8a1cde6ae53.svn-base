/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型
 * 
 * @author wumi
 * @version $Id: DicTypeEnum.java, v 0.1 Mar 8, 2013 3:05:25 PM wumi Exp $
 */
public enum DicTypeEnum {

	/**
	 * 字典类型
	 */
	RWLY("1","任务来源"),
	RWMJ("2","任务密级"),
	JJCD("3","紧急程度"),
	FJLX("4","附件类型"),
	//LAWTYPE("5","执法对象类型"),
	KZLX("6","控制类型"),
	ZYCL("7","自由裁量根节点"),
	JSJDJSCZT("8","建设进度及生产状态"),
	JSXZ("9","建设性质"),
	SPJG("10","审批机关"),
	XFTSLY("11","信访投诉来源"),
	JCDFH("12","单位符号"),
	FDDBR("13","法定代表人"),
	ZW("14","职务"),
	ZXZZMB("15","在线制作模板"),
	ZXFS("16","任务类型执行方式"),
	CXCS("18","特殊监管企业抽选次数"),
	GJSHJB("19","稿件审核级别"),
	XFLY("20","信访来源"),
	SWR("21","水污染"),
	DQWR("22","大气污染"),
	ZSWR("23","噪声污染"),
	GFWR("24","固废污染"),
	FSWR("25","辐射污染"),
	HFXS("26","回访形式"),
	JZGDZT("27","建筑工地状态"),
	XQYZZL("28","畜禽养殖种类"),
	USERGROUP("29","会商接收人分组"),
	ZCLX("30","工业污染源注册类型"),
	LSGX("31","工业污染源隶属关系"),
	GKLX("32","工业污染源国控类型"),
	YQGX("33","工业污染源央企关系"),
	STHJWR("34","生态环境污染"),
	;
	
	/** 编码 */
	private String code;
	/** 文字 */
	private String text;

	private DicTypeEnum(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 根据枚举的code获取描述
	 * 
	 * @param code 
	 */
	public static String getText(String code) {
		for (DicTypeEnum ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.getText();
			}
		}
		return null;
	}

	/**
	 * @param code 
	 */
	public static List<DicTypeEnum> getListEnum() {
		List<DicTypeEnum> dics = new ArrayList<DicTypeEnum>();
		for (DicTypeEnum ele : values()) {
			dics.add(ele);
		}
		return dics;
	}
}
