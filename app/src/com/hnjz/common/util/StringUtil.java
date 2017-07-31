package com.hnjz.common.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
    /**
     * ���ַ�������ʹ��ָ���ķ�������
     * @param list
     * @param concat
     * @return
     */
    public static String concat(List<String> list, String concat) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            strBuffer.append(list.get(i)).append(concat);
        }
        return strBuffer.toString();
    }

    /**
     * ת��ΪSQLʹ�õ�in�е��ַ���
     * [a,b,c,d/a,b,c,d,]����['a','b','c','d']
     * @param in
     * @return
     */
    public static String convertSqlIn(String in) {
        if (in.lastIndexOf(",") == in.length() - 1) {
            in = in.substring(0, in.length() - 1);
        }
        String out = in.replaceAll(",", "','");
        return "'".concat(out).concat("'");
    }
    public static String convertSqlInArray(String[] str) { 
    	StringBuffer sb = new StringBuffer(); 
    	for (int i = 0; i < str.length; i++) { 
    	sb.append("'").append(str[i]).append("'").append(","); 
    	} 
    	return sb.toString().substring(0, sb.length() - 1); 
    } 
    
    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param in
     * @return flag
     */
    public static boolean isNotBlank(String in) {
        boolean flag = false;
        if (in != null && !in.equals("") && !in.equals("null") && !in.equals("NULL")){
        	flag = true;
        }
        return flag;
    }
    
    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param in
     * @return flag
     */
    public static boolean isBlank(String in) {
        boolean flag = false;
        if (in == null || in.equals("") || in.equals("null") || in.equals("NULL")){
        	flag = true;
        }
        return flag;
    }
    
    /**
     * 
     * �������ܣ�ת�����ݿ��ֶε�po������
    
     * ���������
    
     * ����ֵ��
     */
    public static String transColumnToAttr(String column){
    	if(StringUtils.isNotBlank(column)){
    		column = column.replace("_", "");
    		column = column.toLowerCase();
    		if("isactive".equals(column)){
    			return "isActive";
    		}
    	}
    	return column;
    }

}