package com.hnjz.sys.mobile;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * Description:手机功能展示类型
 * DisplayType.java Create on Apr 22, 2013 9:53:54 AM 
 * @author xuguanghui
 * @version 1.0
 * Copyright (c) 2013 Company,Inc. All Rights Reserved
 */
public enum DisplayType {
    JIUGONGGE("1","九宫格"),
    SHORTCUTS("2","快捷键"),
    LIST("3","列表"),
    OTHER("4","其他");

    public static List<Combobox> getTypes(){
        List<Combobox> re = new ArrayList<Combobox>();
        for (DisplayType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getNote()));
        }
        return re;
    }

    /**
     * 根据枚举的code获取描述
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

    /** 说明 */
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
