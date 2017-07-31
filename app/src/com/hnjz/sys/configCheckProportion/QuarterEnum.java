package com.hnjz.sys.configCheckProportion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.domain.Combobox;

/**
 * ��鼾��ö��
 * @author shiqiuhan
 * @created 2015-12-16,����01:50:07
 */
public enum QuarterEnum {

	first_quarter("1","��һ����"),
	second_quarter("2","�ڶ�����"),
	third_quarter("3","��������"),
	fourth_quarter("4","���ļ���"),;
    
    private QuarterEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
	 * 
	 * �������ܣ�����б�
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
	 * �������ܣ�����code�õ�name
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