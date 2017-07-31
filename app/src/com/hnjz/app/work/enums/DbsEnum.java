package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 企业危化枚举
 * 作者：李坤
 */
public enum DbsEnum {
    	
	//清净下水受纳水体功能类别(地表水)
	DBS_1("1","Ⅰ 类 "),
	DBS_2("2","Ⅱ 类"),
	DBS_3("3","Ⅲ 类"),
	DBS_4("4","Ⅳ 类"),
	DBS_5("5","Ⅴ 类"),
	
	;
	/***
     * 
      * 函数介绍：
            得到是否枚举类型  下拉框
     * 输入参数：
    
     * 返回值：
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (DbsEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.name));
        }
        return re;
    }

    /**
     * 根据枚举的code获取描述
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (DbsEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getName();
            }
        }
        return null;
    }	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	private DbsEnum(String code,String name){
		this.code=code;
		this.name=name;
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
