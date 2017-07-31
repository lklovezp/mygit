package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;
/**
 * ����·�ö��
 * @author likun
 * @created 2017-07-22
 */
public enum MonthEnum {
	January_month("01","һ��"),
	February_month("02","����"),
	March_month("03","����"),
	April_month("04","����"),
	May_month("05","����"),
	June_month("06","����"),
	July_month("07","����"),
	August_month("08","����"),
	September_month("09","����"),
	October_month("10","ʮ��"),
	November_month("11","ʮһ��"),
	December_month("12","ʮ����"),;
	private MonthEnum(String code, String name) {
	     this.code = code;
	     this.name = name;
	}
	/**
	 * 
	 * �������ܣ�����б�
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
	 * �������ܣ�����code�õ�name
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