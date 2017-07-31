package com.hnjz.app.drafter.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * 稿件审核状态枚举
 * @author shiqiuhan
 * @created 2015-12-23,下午04:13:40
 */
public enum AuditStateEnum {

	WTJ("0","未提交"),
	YTJ("1","已提交"),
	CSTG("2","初审通过"),
	CSWTG("3","初审未通过"),
	YPZ("4","已批准"),
	WPZ("5","未批准"),
	YBC("6","已报出"),
	WBC("7","未报出"),
	;
    
    private AuditStateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
	 * 
	 * 函数介绍：获得列表
	 */
	public static List<Combobox> getStateEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (AuditStateEnum stateEnum : values()) {
			list.add(new Combobox(stateEnum.code, stateEnum.name));
		}
		return list;
	}
	/**
	 * 
	 * 函数介绍：根据code得到name
	 */
	public static String getNameByCode(String code) {
		for (AuditStateEnum stateEnum : values()) {
			if(StringUtils.isNotBlank(code) && code.equals(stateEnum.code)){
				return stateEnum.name;
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
