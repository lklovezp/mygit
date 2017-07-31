/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 任务人员枚举
 * 作者：xb
 * 生成日期：Apr 8, 2015
 * 功能描述：

 *
 */
public enum TaskUserEnum {
	//任务过程
    DJR("00", "登记人"),
    SCR("01", "生成人"),
    PFR("02", "派发人"),
    ZPR("03", "转派人"),
    //rzq新加用于联动任务统计
    XPR("37", "下派人"),
    
    JSR("04", "接受人"),
    ZBR("05", "主办人"),
    XBR("06", "协办人"),
    SH("07", "审核"),
    TH("08", "退回"),
    GD("09", "归档"),
    
    //办理过程-现场监察
    RCJC_JCR("10", "现场监察.检查人"),
    RCJC_JLR("11", "现场监察.记录人"),
    //办理过程-年度核查
    NDHC_JCR("12", "年度核查.检查人"),
    NDHC_JLR("13", "年度核查.记录人"),
    //办理过程-后督察
    HDC_JCR("14", "后督察.检查人"),
    HDC_JLR("15", "后督察.记录人"),
    //办理过程-信访投诉
    XFTS_JCR("16", "信访投诉.检查人"),
    XFTS_JLR("17", "信访投诉.记录人"),
    //办理过程-排污许可证检查
    PWXKZJC_JCR("18", "排污许可证检查.检查人"),
    PWXKZJC_JLR("19", "排污许可证检查.记录人"),
    //办理过程-专项子任务
    ZXXD_JCR("20", "专项子任务.检查人"),
    ZXXD_JLR("21", "专项子任务.记录人"),
    //办理过程-违法案件调查
    WFAJDC_JLR("22", "违法案件调查.记录人"),
    WFAJDC_KCBLJCKCR("61", "违法案件调查.勘查笔录检查（勘察）人"),
    WFAJDC_KCBLJLR("62", "违法案件调查.勘查笔录记录人"),
    //办理过程-限期治理
    XQZL_JCR("23", "限期治理.检查人"),
    XQZL_JLR("24", "限期治理.记录人"),
    //办理过程-跟踪检查
    GZJC_JCR("25", "跟踪检查.检查人"),
    GZJC_JLR("26", "跟踪检查.记录人"),
    //办理过程-自动监控
    ZDJK_JCR("27", "自动监控.检查人"),
    ZDJK_JLR("28", "自动监控.记录人"),
    //办理过程-危险废物
    WXFW_JCR("29", "危险废物.检查人"),
    WXFW_JLR("30", "危险废物.记录人"),
    //办理过程-危险化学品
    WXHXP_JCR("31", "危险化学品.检查人"),
    WXHXP_JLR("32", "危险化学品.记录人"),
    //办理过程-辐射安全
    FSAQ_JCR("33", "辐射安全.检查人"),
    FSAQ_JLR("34", "辐射安全.记录人"),
    //办理过程-污染事故现场调查
    WRSGXCDC_JCR("35", "污染事故现场调查.检查人"),
    WRSGXCDC_JLR("36", "污染事故现场调查.记录人");

    /**
     * 获取所有的任务人员类型，供下拉框使用
     * 
     * @return 任务人员类型
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (TaskUserEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.getText()));
        }
        return re;
    }

    /**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static TaskUserEnum getByCode(String code) {
        TaskUserEnum ryLx = null;
        for (TaskUserEnum ele : values()) {
            if (ele.getCode().equals(code)) {
            	ryLx = ele;
                break;
            }
        }
        return ryLx;
    }
    
    public static String getCodeByEnum(String enumName){
		String code = "";
		for (TaskUserEnum ele : values()) {
			if (ele.name().equals(enumName)) {
				code = ele.getCode();
				break;
			}
		}
		if ("".equals(code)){
			code = enumName;
		}
		return code;
	}

    /** 编码 */
    private String code;
    /** 文字 */
    private String text;

    private TaskUserEnum(String code, String text) {
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
