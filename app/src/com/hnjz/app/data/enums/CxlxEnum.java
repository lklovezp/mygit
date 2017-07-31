/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ��ѡ����ö�٣���ȳ�����
 * @author shiqiuhan
 * @created 2016-3-11,����04:48:38
 */
public enum CxlxEnum {
	zdqy("0","�ص���ҵ"),
	ybqy("1","һ����ҵ"),
	tsqy("2","������ҵ"),
	;
	
	
    private CxlxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
	 * 
	 * �������ܣ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static List<Combobox> getCxlxEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (CxlxEnum cxlxEnum : values()) {
			list.add(new Combobox(cxlxEnum.code, cxlxEnum.name));
		}
		return list;
	}
    
	/**
     * ����code�õ�����
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
    
    /** ����*/
    private String code;
    /** ���� */
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