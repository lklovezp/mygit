package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;
import com.hnjz.common.domain.Combobox;

/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum HjzlEnum {
    //��ҵ��������������������������
	QYDQHJZL_YJ("1","һ��"),
	QYDQHJZL_EJ("2","����"),
	QYDQHJZL_SJ("3","����"),
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
        for (HjzlEnum ele : values()) {
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
        for (HjzlEnum ele : values()) {
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
	private HjzlEnum(String code,String name){
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