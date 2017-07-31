package com.hnjz.app.work.manager.nodes;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ��������������������ö��
 * ���ߣ�zqf
 * 
 */
public enum HzWorkFlowEnum {
    	
	//����������ö��,��Ϊ�ŷ�Ͷ��������ճ��칫��������һ��������ֻ������һ������
	WorkFlow_1("1","generalTask1"),
	WorkFlow_2("2","xftsTask"),
	WorkFlow_3("3","sjxftsTask"),
	WorkFlow_4("4","test"),
	WorkFlow_5("5","zfjczlc"),
	
	;
	/***
     * 
     * �������ܣ� �õ��Ƿ�ö������  ������
     * ���������
     * ����ֵ��
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (HzWorkFlowEnum ele : values()) {
            re.add(new Combobox(ele.getCode(), ele.name));
        }
        return re;
    }

    /**
     * ����ö�ٵ�code��ȡ����
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
	/** ���� */
	private String code;
	/** ���� */
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