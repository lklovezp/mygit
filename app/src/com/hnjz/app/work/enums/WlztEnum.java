package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 企业危化枚举
 * 作者：李坤
 */
public enum WlztEnum {
   
	//物理状态
	WLZT_QT("1","气体"),
	QLZT_YT("2","液体"),
	WLZT_GT("3","固体"),
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
        for (WlztEnum ele : values()) {
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
        for (WlztEnum ele : values()) {
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
	private WlztEnum(String code,String name){
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
