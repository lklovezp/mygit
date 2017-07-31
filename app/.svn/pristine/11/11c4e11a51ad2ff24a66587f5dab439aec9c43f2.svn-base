/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 任务状态,主要为查询和绩效用，工作流的流转不需要
 * 
 * @author wumi
 * @version $Id: WorkState.java, v 0.1 Feb 19, 2013 2:17:44 PM wumi Exp $
 */
public enum WorkState {

    /**已派发*/
    YPF("01", "已派发","转派"),
    /**已转派*/
    YZP("02", "已转派","接受"),
    /**已下派*/
    YXP("03", "已下派","接受"),
    /**已接受*/
    JS("004", "已接受","办理"),
    /**办理中*/
    BLZ("05", "办理中","办理"),
    /**已办理*/
    YBL("06", "已办理","审核"),
    /**已审核*/
    YSH("07", "已审核","归档"),
    /**已退回*/
    YTH("08", "已退回","办理"),
    /**已归档*/
    YGD("09", "已归档",""), ;
    
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (WorkState ele : values()) {
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
        for (WorkState ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getText();
            }
        }
        return code;
    }
    
    /**
     * 根据枚举的code获取描述
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
     * 根据枚举的code获取描述
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
     * 根据枚举的code获取描述
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

    /** 编码 */
    private String code;
    /** 文字 */
    private String text;
    
    /** 下一步操作类型 */
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
