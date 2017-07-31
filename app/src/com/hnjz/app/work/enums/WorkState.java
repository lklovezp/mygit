/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ����״̬,��ҪΪ��ѯ�ͼ�Ч�ã�����������ת����Ҫ
 * 
 * @author wumi
 * @version $Id: WorkState.java, v 0.1 Feb 19, 2013 2:17:44 PM wumi Exp $
 */
public enum WorkState {

    /**���ɷ�*/
    YPF("01", "���ɷ�","ת��"),
    /**��ת��*/
    YZP("02", "��ת��","����"),
    /**������*/
    YXP("03", "������","����"),
    /**�ѽ���*/
    JS("004", "�ѽ���","����"),
    /**������*/
    BLZ("05", "������","����"),
    /**�Ѱ���*/
    YBL("06", "�Ѱ���","���"),
    /**�����*/
    YSH("07", "�����","�鵵"),
    /**���˻�*/
    YTH("08", "���˻�","����"),
    /**�ѹ鵵*/
    YGD("09", "�ѹ鵵",""), ;
    
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (WorkState ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }
    
    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (WorkState ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getText();
            }
        }
        return code;
    }
    
    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getCode(String name) {
        for (WorkState ele : values()) {
            if (ele.getText().equals(name)) {
                return ele.getCode();
            }
        }
        return null;
    }
    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNameByCode(String code) {
    	for (WorkState ele : values()) {
    		if (ele.getCode().equals(code)) {
    			return ele.getText();
    		}
    	}
    	return null;
    }
    
    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static WorkState getWorkStateByCode(String code) {
    	for (WorkState ele : values()) {
    		if (ele.getCode().equals(code)) {
    			return ele;
    		}
    	}
    	return null;
    }

    /** ���� */
    private String code;
    /** ���� */
    private String text;
    
    /** ��һ���������� */
    private String nextType;

    private WorkState(String code, String text, String nextType) {
        this.code = code;
        this.text = text;
        this.nextType = nextType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public String getNextType() {
		return nextType;
	}

	public void setNextType(String nextType) {
		this.nextType = nextType;
	}

}