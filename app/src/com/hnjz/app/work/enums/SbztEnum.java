package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum SbztEnum {
  
	//�豸״̬
	SBZT_1("1","�ܱ�ʽ"),
	SBZT_2("2","���ܱ�ʽ"),
	SBZT_3("3","����ʽ"),
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
        for (SbztEnum ele : values()) {
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
        for (SbztEnum ele : values()) {
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
	private SbztEnum(String code,String name){
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