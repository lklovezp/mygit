/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 工单审核的动作
 * 
 * @author wumi
 * @version $Id: WorkSh.java, v 0.1 Mar 28, 2013 9:30:11 AM wumi Exp $
 */
public enum WorkSh {

    /*上级审核**/
    SJSH(0, "提交上级审核"),
    /*上报上级单位**/
    SB(3, "上报上级单位"),
    /*归档**/
    GD(1, "归档"),
    /*退回**/
    TH(2, "退回"),
    /*退回地市**/
    THDS(3, "退回"),;
    /** 编码 */
    private int    code;
    /** 文字 */
    private String text;

    private WorkSh(int code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 生成MAP
     * @param e
     * @return
     */
    public static Map<String, Object> toMap(WorkSh e) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", e.getCode());
        map.put("text", e.getText());
        return map;
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
