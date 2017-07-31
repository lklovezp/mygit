/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 用户的级别，例如：总队，副队，科员，级别越低，数字越小
 * 
 * @author wumi
 * @version $Id: UserPosition.java, v 0.1 Mar 27, 2013 3:46:08 PM wumi Exp $
 */
public enum UserPosition {
    /**科员*/
    KY(0, "科员"),
    /**科长*/
    KZ(4, "处/科长"),
    /**副队*/
    FD(12, "副队"),
    /**办公室*/
    BGS(13, "办公室"),
    /**总队*/
    ZD(16, "总队"), ;

    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (UserPosition ele : values()) {
            re.add(new Combobox(String.valueOf(ele.getCode()), ele.getText()));
        }
        return re;
    }
    
    /**
     * 根据枚举的code获取描述
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (UserPosition ele : values()) {
            if (ele.getCode() == Integer.parseInt(code)) {
                return ele.getText();
            }
        }
        return null;
    }

    /** 编码 */
    private int    code;
    /** 文字 */
    private String text;

    private UserPosition(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
