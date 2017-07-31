/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * �����ö��
 * @author renzhengquan
 * @version 
 */
public enum JcxEnum {
	jsxm_xmsfajhsg("01","��Ŀ�Ƿ񰴼ƻ�ʩ��"),
	jsxm_xmsjtcs("02","��Ŀʵ��Ͷ����"),
	jsxm_fzsstcs("03","������ʩͶ����"),
	
	xkz_fzsstcs("04","��Ⱦ���Ƿ񳬱��ŷ�"),
	;
	
	
    private JcxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
	 * 
	 * �������ܣ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static List<Combobox> getJcxEnumList() {
		List<Combobox> list = new ArrayList<Combobox>();
		for (JcxEnum jcxEnum : values()) {
			list.add(new Combobox(jcxEnum.code, jcxEnum.name));
		}
		return list;
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