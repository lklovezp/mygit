/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 收发文类型
 * @author 时秋寒
 *
 */
public enum SfwlxEnum {
	fw("0","发文"),
	sw("1","收文"),
	;
	
	
    private SfwlxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static List<Combobox> getSfwlxEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (SfwlxEnum sfwlxEnum : values()) {
			list.add(new Combobox(sfwlxEnum.code, sfwlxEnum.name));
		}
		return list;
	}
    
	/**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static SfwlxEnum getByCode(String code) {
    	SfwlxEnum sfwlx = null;
        for (SfwlxEnum ele : values()) {
            if (ele.getCode().equals(code)) {
            	sfwlx = ele;
                break;
            }
        }
        return sfwlx;
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
