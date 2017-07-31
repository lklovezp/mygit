/**
 * 
 */
package com.hnjz.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/***
 * 
 * �Ƿ�ö����
 * @author zhaomh
 * @version $Id: IsEnum.java, v 0.1 2013-8-1 ����11:07:07 zhaomh Exp $
 */
public enum IsEnum {
   SHI("1", "��"),
   FOU("2","��"),;
   /** ���� */
   private String code;
   /** ���� */
   private String text;
   /***
    * 
     * �������ܣ�
           �õ��Ƿ�ö������  ������
    * ���������
   
    * ����ֵ��
    */
   public static List<Combobox> getTypes() {
       List<Combobox> re = new ArrayList<Combobox>();
       for (IsEnum ele : values()) {
           re.add(new Combobox(ele.getCode(), ele.getText()));
       }
       return re;
   }
   
   /**
    * ����code�õ�����
    * @param code
    * @return
    */
   public static String getTextByCode(String code) {
       for (IsEnum ele : values()) {
           if (ele.getCode().equals(code)) {
               return ele.getText();
           }
       }
       return "";
   }

   /**
    * ����code�õ�����
    * @param code
    * @return
    */
   public static IsEnum getByCode(String code) {
	   IsEnum isEnum = null;
       for (IsEnum ele : values()) {
           if (ele.getCode().equals(code)) {
        	   isEnum = ele;
               break;
           }
       }
       return isEnum;
   }
	private IsEnum(String code, String text) {
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