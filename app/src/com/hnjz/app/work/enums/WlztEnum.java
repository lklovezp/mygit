package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum WlztEnum {
   
	//����״̬
	WLZT_QT("1","����"),
	QLZT_YT("2","Һ��"),
	WLZT_GT("3","����"),
	;
	/***
     * 
      * �������ܣ�
            �õ��Ƿ�ö������  ������
     * ���������
    
     * ����ֵ��
     */
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (WlztEnum ele : values()) {
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
        for (WlztEnum ele : values()) {
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
	private WlztEnum(String code,String name){
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