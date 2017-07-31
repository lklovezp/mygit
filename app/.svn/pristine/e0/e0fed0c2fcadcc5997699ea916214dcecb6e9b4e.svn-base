package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;
/**
 * 抽查月份枚举
 * @author likun
 * @created 2017-07-22
 */
public enum MonthEnum {
	January_month("01","一月"),
	February_month("02","二月"),
	March_month("03","三月"),
	April_month("04","四月"),
	May_month("05","五月"),
	June_month("06","六月"),
	July_month("07","七月"),
	August_month("08","八月"),
	September_month("09","九月"),
	October_month("10","十月"),
	November_month("11","十一月"),
	December_month("12","十二月"),;
	private MonthEnum(String code, String name) {
	     this.code = code;
	     this.name = name;
	}
	/**
	 * 
	 * 函数介绍：获得列表
	 */
	public static List<Combobox> getMonthEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (MonthEnum monthEnum : values()) {
			list.add(new Combobox(monthEnum.code, monthEnum.name));
		}
		return list;
	}
	/**
	 * 
	 * 函数介绍：根据code得到name
	 */
	public static String getNameByCode(String code) {
		for (MonthEnum monthEnum : values()) {
			if(StringUtils.isNotBlank(code) && code.equals(monthEnum.code)){
				return monthEnum.name;
			}
		}
		return null;
	}
	
	private String code;
    private String name;
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
