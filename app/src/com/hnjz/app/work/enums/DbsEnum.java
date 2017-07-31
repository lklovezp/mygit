package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum DbsEnum {
    	
	//�徻��ˮ����ˮ�幦�����(�ر�ˮ)
	DBS_1("1","�� �� "),
	DBS_2("2","�� ��"),
	DBS_3("3","�� ��"),
	DBS_4("4","�� ��"),
	DBS_5("5","�� ��"),
	
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
        for (DbsEnum ele : values()) {
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
        for (DbsEnum ele : values()) {
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
	private DbsEnum(String code,String name){
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