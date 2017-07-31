package com.hnjz.app.work.manager.nodes;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 慧正工作流创建的流程枚举
 * 作者：zqf
 * 
 */
public enum HzWorkFlowEnum {
    	
	//创建的流程枚举,因为信访投诉任务和日常办公任务流程一样，所以只设置了一个流程
	WorkFlow_1("1","generalTask1"),
	WorkFlow_2("2","xftsTask"),
	WorkFlow_3("3","sjxftsTask"),
	WorkFlow_4("4","test"),
	WorkFlow_5("5","zfjczlc"),
	
	;
	/***
     * 
     * 函数介绍： 得到是否枚举类型  下拉框
     * 输入参数：
     * 返回值：
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (HzWorkFlowEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.name));
        }
        return re;
    }

    /**
     * 根据枚举的code获取描述
     * 
     * @param code 
     */
    public static String getNote(String code) {
        for (HzWorkFlowEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getName();
            }
        }
        return null;
    }	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	private HzWorkFlowEnum(String code,String name){
		this.code=code;
		this.name=name;
	}
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
