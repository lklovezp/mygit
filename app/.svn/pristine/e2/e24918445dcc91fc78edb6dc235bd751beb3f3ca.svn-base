/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 操作类型，主要为查询和绩效用，工作流的流转不需要
 * 
 * @author wumi
 * @version $Id: WorkLogType.java, v 0.1 Feb 19, 2013 5:07:41 PM wumi Exp $
 */
public enum WorkLogType {
    /**派发*/
    PF("001", "派发"),
    /**转发*/
    ZF("002", "转派"),
    /**接受*/
    JS("004", "接受"),
    /**执行*/
    ZX("008", "办理"),
//    /**陪同*/
//    PT("009", "陪同"),
//    /**记录*/
//    JL("010", "记录"),
//    /**上报*/
    SB("012", "上报"),
    XP("015", "下派"),
    /**审核*/
    SH("016", "审核"),
    /**退回*/
    TH("017", "退回"),
    /**归档*/
    GD("020", "归档"), ;

    /**
     * 得到时间类型
     * 
     * @return 
     */
    public static List<Combobox> getTimeTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        re.add(new Combobox(PF.getCode(), PF.getText()));
        re.add(new Combobox(ZF.getCode(), ZF.getText()));
        re.add(new Combobox(JS.getCode(), JS.getText()));
        re.add(new Combobox(ZX.getCode(), ZX.getText()));
        re.add(new Combobox(SH.getCode(), SH.getText()));
//        re.add(new Combobox(SH.getCode().concat(",").concat(TH.getCode()), SH.getText()));
        re.add(new Combobox(GD.getCode(), GD.getText()));
        re.add(new Combobox(TH.getCode(), TH.getText()));
        return re;
    }

    /**
     * 得到操作类型
     * 
     * @return
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (WorkLogType ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * 根据枚举的code获取描述
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (WorkLogType ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getText();
            }
        }
        return null;
    }

    /** 编码 */

    private String code;
    /** 文字 */
    private String text;

    private WorkLogType(String code, String text) {
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
