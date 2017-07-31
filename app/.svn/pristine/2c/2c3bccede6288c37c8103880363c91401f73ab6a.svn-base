package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象公共字段枚举值
 *
 */
public enum PublicColumnEnum {
	mc("01","名称"),
	ssxzq("02","所属行政区"),
	hylx("03","行业类型"),
	fddbr("04","法定代表人"),
	fddbrlxdh("05","法定代表人联系电话"),
	hbfzr("06","环保负责人"),
	hbfzrlxdh("07","环保负责人联系电话"),
	jd("08","经度"),
	wd("09","纬度"),
	cjr("10","创建人"),
	dz("11","地址"),
	;
	private PublicColumnEnum(String code, String name) {
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
		for (PublicColumnEnum inputTypeEnum : values()) {
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
