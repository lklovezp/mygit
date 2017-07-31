/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * 随机抽查规则
 * @author shiqiuhan
 *
 */
public enum ConfigCheckEnum {

	/**
	 * 随机抽查规则
	 */
	SJCXBMLD("1","随机任务派发到企业所属监管部门领导"),
	SJCXZBR("2","随机抽选主办人"),
	SJCXXBR("3","随机抽选主办人协办人"),
	;
	
	/** 编码 */
	private String code;
	/** 文字 */
	private String text;

	private ConfigCheckEnum(String code, String text) {
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
		for (ConfigCheckEnum ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.getText();
			}
		}
		return null;
	}

	/**
	 * @param code 
	 */
	public static List<ConfigCheckEnum> getListEnum() {
		List<ConfigCheckEnum> configs = new ArrayList<ConfigCheckEnum>();
		for (ConfigCheckEnum ele : values()) {
			configs.add(ele);
		}
		return configs;
	}
}
