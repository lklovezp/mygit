/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 检查项枚举
 * @author renzhengquan
 * @version 
 */
public enum JcxEnum {
	jsxm_xmsfajhsg("01","项目是否按计划施工"),
	jsxm_xmsjtcs("02","项目实际投产数"),
	jsxm_fzsstcs("03","防治设施投产数"),
	
	xkz_fzsstcs("04","污染物是否超标排放"),
	;
	
	
    private JcxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
	 * 
	 * 函数介绍：获得列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static List<Combobox> getJcxEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (JcxEnum jcxEnum : values()) {
			list.add(new Combobox(jcxEnum.code, jcxEnum.name));
		}
		return list;
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
