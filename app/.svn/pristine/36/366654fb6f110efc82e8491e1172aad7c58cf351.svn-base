/**
 * 
 */
package com.hnjz.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/***
 * 
 * 是否枚举类
 * @author zhaomh
 * @version $Id: IsEnum.java, v 0.1 2013-8-1 上午11:07:07 zhaomh Exp $
 */
public enum IsEnum {
   SHI("1", "是"),
   FOU("2","否"),;
   /** 编码 */
   private String code;
   /** 文字 */
   private String text;
   /***
    * 
     * 函数介绍：
           得到是否枚举类型  下拉框
    * 输入参数：
   
    * 返回值：
    */
   public static List<Combobox> getTypes() {
       List<Combobox> re = new ArrayList<Combobox>();
       for (IsEnum ele : values()) {
           re.add(new Combobox(ele.getCode(), ele.getText()));
       }
       return re;
   }
   
   /**
    * 根据code得到对象
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
    * 根据code得到对象
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
