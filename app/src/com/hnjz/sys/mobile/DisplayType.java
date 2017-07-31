package com.hnjz.sys.mobile;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * Description:�ֻ�����չʾ����
 * DisplayType.java Create on Apr 22, 2013 9:53:54 AM 
 * @author xuguanghui
 * @version 1.0
 * Copyright (c) 2013 Company,Inc. All Rights Reserved
 */
public enum DisplayType {
    JIUGONGGE("1","�Ź���"),
    SHORTCUTS("2","��ݼ�"),
    LIST("3","�б�"),
    OTHER("4","����");

    public static List<Combobox> getTypes(){
        List<Combobox> re = new ArrayList<Combobox>();
        for (DisplayType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getNote()));
        }
        return re;
    }

    /**
     * ����ö�ٵ�code��ȡ����
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (DisplayType ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getNote();
            }
        }
        return null;
    }

    /** code */
    private String code;

    /** ˵�� */
    private String note;

    private DisplayType(String code, String note) {
        this.code = code;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
	
}