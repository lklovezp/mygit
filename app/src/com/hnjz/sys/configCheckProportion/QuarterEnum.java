package com.hnjz.sys.configCheckProportion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * 抽查季度枚举
 * @author shiqiuhan
 * @created 2015-12-16,下午01:50:07
 */
public enum QuarterEnum {

	first_quarter("1","第一季度"),
	second_quarter("2","第二季度"),
	third_quarter("3","第三季度"),
	fourth_quarter("4","第四季度"),;
    
    private QuarterEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
	 * 
	 * 函数介绍：获得列表
	 */
	public static List<Combobox> getQuarterEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (QuarterEnum quarterEnum : values()) {
			list.add(new Combobox(quarterEnum.code, quarterEnum.name));
		}
		return list;
	}
	/**
	 * 
	 * 函数介绍：根据code得到name
	 */
	public static String getNameByCode(String code) {
		for (QuarterEnum quarterEnum : values()) {
			if(StringUtils.isNotBlank(code) && code.equals(quarterEnum.code)){
				return quarterEnum.name;
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
