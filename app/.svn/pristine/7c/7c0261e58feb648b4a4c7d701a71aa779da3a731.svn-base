package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字段输入方式枚举
 *
 */
public enum InputTypeEnum {
	wbk("1","文本框"),
	wby("2","文本域"),
	rq("3","日期"),
	xllbdx("4","下拉列表单选"),
	xllbddx("5","下拉列表多选"),
	xls("6","下拉树"),
	hidden("7","隐藏域"),
	number("8","数值"),
	choose("9","弹窗选择"),
	zdwbk("10","只读文本框"),
	jwsrk("11","经纬度输入框"),
	scztkj("12","生产状态控件");
	;
	private InputTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	/**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static List<Combobox> getInputTypeList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (InputTypeEnum inputTypeEnum : values()) {
			list.add(new Combobox(inputTypeEnum.code, inputTypeEnum.name));
		}
		return list;
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
}
