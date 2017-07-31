package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 企业危化枚举
 * 作者：李坤
 */
public enum SbztEnum {
  
	//设备状态
	SBZT_1("1","密闭式"),
	SBZT_2("2","半密闭式"),
	SBZT_3("3","敞开式"),
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
        for (SbztEnum ele : values()) {
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
        for (SbztEnum ele : values()) {
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
	private SbztEnum(String code,String name){
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
