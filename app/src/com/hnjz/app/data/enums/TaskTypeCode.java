/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * 任务类型的枚举
 * 
 * @author wumi
 * @version $Id: WorkTypeCode.java, v 0.1 May 3, 2013 2:02:43 PM wumi Exp $
 */
public enum TaskTypeCode {

	/** 10 现场监察 */
    RCJC("10", "现场监察"),
	/** 11 年度核查 */
    NDHC("11", "年度核查"),
	/** 12 后督察 */
    HDC("12", "后督察"),
	/** 13 信访投诉 */
    XFTS("13", "信访投诉"),
	/** 14 排污许可证检查 */
    PWXKZJC("14", "排污许可证检查"),
	/** 15 专项行动 */
    ZXXD("15", "专项行动"),
	/** 16 违法案件调查 */
    WFAJ("16", "违法案件调查"),
	/** 17 限期治理 */
    XQZL("17", "限期治理"),
	/** 18 跟踪检查 */
    GZJC("18", "跟踪检查"),
	/** 19 自动监控 */
    ZDJK("19", "自动监控"),
	/** 20 危险废物 */
    WXFW("20", "危险废物"),
	/** 21 危险化学品 */
    WXHXP("21", "危险化学品"),
	/** 22 辐射安全 */
    FSAQ("22", "辐射安全"),
	/** 23 污染事故现场调查 */
    WRSGXCDC("23", "污染事故现场调查"),
    /** 24 日常办公 */
    RCBG("24", "日常办公"),
	;

	public static List<Combobox> getTypes() {
		List<Combobox> re = new ArrayList<Combobox>();
		for (TaskTypeCode ele : values()) {
			re.add(new Combobox(String.valueOf(ele.getCode()), ele.getText()));
		}
		return re;
	}

	/**
	 * 根据枚举的code获取描述
	 * 
	 * @param code
	 */
	public static String getNote(String code) {
		for (TaskTypeCode ele : values()) {
			if (StringUtils.equals(code, ele.getCode())) {
				return ele.getText();
			}
		}
		return null;
	}

	/**
	 * 根据枚举的code获取描述
	 * 
	 * @param code
	 */
	public static String getEnumByCode(String code) {
		for (TaskTypeCode ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.name();
			}
		}
		return "";
	}
	
	private TaskTypeCode(String code, String text) {
		this.code = code;
		this.text = text;
	}

	/** 编码 */
	private String code;
	/** 文字 */
	private String text;

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

}
