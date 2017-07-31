/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 抽选类型枚举（年度抽查对象）
 * @author shiqiuhan
 * @created 2016-3-11,下午04:48:38
 */
public enum CxlxEnum {
	zdqy("0","重点企业"),
	ybqy("1","一般企业"),
	tsqy("2","特殊企业"),
	;
	
	
    private CxlxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static List<Combobox> getCxlxEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (CxlxEnum cxlxEnum : values()) {
			list.add(new Combobox(cxlxEnum.code, cxlxEnum.name));
		}
		return list;
	}
    
	/**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static CxlxEnum getByCode(String code) {
    	CxlxEnum cxLx = null;
        for (CxlxEnum ele : values()) {
            if (ele.getCode().equals(code)) {
            	cxLx = ele;
                break;
            }
        }
        return cxLx;
    }
    
    /** 编码*/
    private String code;
    /** 名称 */
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
