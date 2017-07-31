package com.hnjz.app.work.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;


/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum WzlbEnum {
   
	//�豸���������
	SBHWZLB_1("1","���˷���װ������"),
	SBHWZLB_2("2","������ʩ"),
	SBHWZLB_3("3","��©���ռ�����/�豸"),
	SBHWZLB_4("4","Ӧ������豸"),
	SBHWZLB_5("5","Ӧ����Ԯ����"),
	SBHWZLB_6("6","����"),
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
        for (WzlbEnum ele : values()) {
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
        for (WzlbEnum ele : values()) {
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
	private WzlbEnum(String code,String name){
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