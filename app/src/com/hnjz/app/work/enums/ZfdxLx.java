/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 执法对象枚举
 * 
 * @author wumi
 * @version $Id: ZfdxLx.java, v 0.1 Jan 28, 2013 11:17:30 AM wumi Exp $
 */
public enum ZfdxLx {
    GYWRY("01", "工业污染源"),
    JSXM("02", "建设项目"),
    YY("03", "医院"),
    GL("04", "锅炉"),
    JZGD("05", "建筑工地"),
    SC("06", "三产"),
    XQYZ("07", "畜禽养殖"),
    FWY("08", "服务业"),
    YSY("09", "饮食业"),
    ZZY("10", "三产制造业"),
    YLY("11", "娱乐业"),
    ;

    /**
     * 获取所有的执法对象类型，供下拉框使用
     * 
     * @return 执法对象类型
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (ZfdxLx ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static ZfdxLx getByCode(String code) {
        ZfdxLx zfdxLx = null;
        for (ZfdxLx ele : values()) {
            if (ele.getCode().equals(code)) {
                zfdxLx = ele;
                break;
            }
        }
        return zfdxLx;
    }

    /** 编码 */
    private String code;
    /** 文字 */
    private String text;

    private ZfdxLx(String code, String text) {
        this.code = code;
        this.text = text;
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
}
