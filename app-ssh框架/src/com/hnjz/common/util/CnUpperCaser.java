package com.hnjz.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 将10亿以内的阿拉伯数字转成汉字大写形式
 * 
 * @author xizhenyin
 * 
 */
public class CnUpperCaser {
	// 整数部分
	private String integerPart;

	// 小数部分
	private String floatPart;

	// 将数字转化为汉字的数组,因为各个实例都要使用所以设为静态
	private static final char[] cnNumbers = { '零', '壹', '贰', '叁', '肆', '伍',
			'陆', '柒', '捌', '玖' };
	
	private static final char[] cnDates = {'O','一','二','三','四','五','六','七','八','九'};

	// 供分级转化的数组,因为各个实例都要使用所以设为静态
	private static final char[] series = { '元', '拾', '百', '仟', '万', '拾', '百',
			'仟', '亿', '拾', '百', '仟' };

	private static final char[] series2 = { '角', '分' };

	/**
	 * 构造函数,通过它将阿拉伯数字形式的字符串传入
	 * 
	 * @param original
	 */
	public CnUpperCaser(String original) {
		// 成员变量初始化
		integerPart = "";
		floatPart = "";

		if (original.contains(".")) {
			// 如果包含小数点
			int dotIndex = original.indexOf(".");
			integerPart = original.substring(0, dotIndex);
			floatPart = original.substring(dotIndex + 1);
		} else {
			// 不包含小数点
			integerPart = original;
		}
	}

	/**
	 * 取得大写形式的字符串
	 * 
	 * @return
	 */
	public String getCnString() {
		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));

			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}

		// 小数部分处理
		if (floatPart.length() > 0) {
			sb.append("点");
			for (int i = 0; i < floatPart.length(); i++) {
				int number = getNumber(floatPart.charAt(i));

				sb.append(cnNumbers[number]);
			}
		}

		// 返回拼接好的字符串
		return sb.toString();
	}

	/**
	 * 金额中文字符串，高位不存在补/
	 * 
	 * @return
	 */
	public String getCnStringAll() {
		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		for (int i = series.length; i > integerPart.length(); i--) {

			sb.append("/");
			sb.append(series[i - 1]);
		}
		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));

			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}

		// 小数部分处理

		// sb.append("点");
		for (int i = 0; i < series2.length; i++) {

			int number = 0;
			try {
				number = getNumber(floatPart.charAt(i));
			} catch (Exception e) {
			}

			sb.append(cnNumbers[number]);
			sb.append(series2[i]);

		}

		// 返回拼接好的字符串
		return sb.toString();
	}

	public String getCnStringAll2() {
		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));

			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}
		String newchar = sb.toString();
		while(newchar.length()<24)
		{
			newchar=" "+newchar;
		}
		String outStr = "";
		String [] temp = new String[newchar.length()/8];
		int i = 0;
		int j = temp.length;
		for(String a:temp)
		{
			a = newchar.substring(i,i+8);
			
			a=getwanStr(a,j);
			i+=8;
			j--;
			outStr +=a;
			
			
		}
		
		
		
		
		
		
		
		
		// 小数部分处理
		if (floatPart.length() > 0) {
			for (int k = 0; k < floatPart.length(); k++) {
				int number = getNumber(floatPart.charAt(k));

				outStr+=cnNumbers[number];
				outStr+=series2[k];
			}
		}
		
		
		
		
		
		
		
		
	    if ( "元".equals(outStr.substring(outStr.length()-1) )|| "角".equals(outStr.substring(outStr.length()-1) )){
	    	outStr = outStr+"整";}
		// 返回拼接好的字符串
		return outStr;
	}
	
	@SuppressWarnings("unchecked")
	private String getwanStr(String inStr,int jici)
	{
		
		String outStr =inStr;
		Map map = new HashMap();
		map.put(1, "元");
		map.put(2, "万");
		map.put(3, "亿");
		
		if(inStr.indexOf((("零仟零百零拾零"+map.get(jici))))!=-1)
		{
			
			return (String)map.get(jici);
		}
		if(inStr.indexOf("        ")!=-1)
		{

			return "";
		}
		boolean is0 =false;
		
		if("零".equals(inStr.substring(0,1)))
		{
			outStr = outStr.replace("零仟", "零");
			is0 = true;
		}
		
		if(inStr.indexOf((("零百零拾零"+map.get(jici))))!=-1)
		{
			return outStr.replace("零百零拾零", "");
		}
		
		
		if("零".equals(inStr.substring(2,3)))
		{
			if(is0)
			{
				outStr = outStr.replace("零百", "");
			}
			else
			{
				outStr = outStr.replace("零百", "零");
			}
			
			is0 = true;
		}
		else
		{
			is0 =false;
		}
		
		if(inStr.indexOf(("零拾零"+map.get(jici)))!=-1)
		{
			return outStr.replace("零拾零", "");
		}
		
		
		
		if("零".equals(inStr.substring(4,5)))
		{
			if(is0)
			{
				outStr = outStr.replace("零拾", "");
			}
			else
			{
				outStr = outStr.replace("零拾", "零");
			}
			is0 = true;
		}
		else
		{
			is0 =false;
		}
		
		if("零".equals(inStr.substring(6,7)))
		{
			outStr = outStr.replace("零"+map.get(jici), "");
		}
		
		 
		 if(!map.get(jici).equals(outStr.substring(outStr.length()-1)))
		 {
			 outStr+=map.get(jici);
		 }
		
		return outStr;
	}
	
	/**
	 * 金额中文字符串，高位不存在补/
	 * 
	 * @return
	 */
	public String getCnStringAll(String cnNumColor,String seriesColor) {
		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		for (int i = series.length; i > integerPart.length(); i--) {

			
			
			sb.append("<font color='").append(cnNumColor).append("'>").append("/").append("</font>");
			sb.append("<font color='").append(seriesColor).append("'>").append(series[i - 1]).append("</font>");
		}
		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));

			sb.append("<font color='").append(cnNumColor).append("'>").append(cnNumbers[number]).append("</font>");
			sb.append("<font color='").append(seriesColor).append("'>").append(series[integerPart.length() - 1 - i]).append("</font>");
		}

		// 小数部分处理

		// sb.append("点");
		for (int i = 0; i < series2.length; i++) {

			int number = 0;
			try {
				number = getNumber(floatPart.charAt(i));
			} catch (Exception e) {
			}

			sb.append("<font color='").append(cnNumColor).append("'>").append(cnNumbers[number]).append("</font>");
			sb.append("<font color='").append(seriesColor).append("'>").append(series2[i]).append("</font>");

		}

		// 返回拼接好的字符串
		return sb.toString();
	}

	/**
	 * 将字符形式的数字转化为整形数字 因为所有实例都要用到所以用静态修饰
	 * 
	 * @param c
	 * @return
	 */
	private static int getNumber(char c) {
		String str = String.valueOf(c);
		return Integer.parseInt(str);
	}
	
	public static String dateNumToUpper(int num){
		char[] str = String.valueOf(num).toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		//for(int i=0;i<str.length;i++){
		//	strBuffer.append(cnDates[Integer.parseInt(str[i]+"")]);
		//}
		if(str!=null && str.length==1){
			strBuffer.append(cnDates[Integer.parseInt(str[0]+"")]);
		}else if(str!=null && str.length==2){
			if(Integer.parseInt(str[0]+"")==1){	//10--19
				if(Integer.parseInt(str[1]+"")==0){//10
					strBuffer.append("十");
				}else{	//11--19
					strBuffer.append("十");
					strBuffer.append(cnDates[Integer.parseInt(str[1]+"")]);
				}
			}else{//20--99
				if(Integer.parseInt(str[1]+"")==0){//整十
					strBuffer.append(cnDates[Integer.parseInt(str[0]+"")]);
					strBuffer.append("十");
				}else{
					strBuffer.append(cnDates[Integer.parseInt(str[0]+"")]);
					strBuffer.append("十");
					strBuffer.append(cnDates[Integer.parseInt(str[1]+"")]);
				}
			}
		}
		return strBuffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new CnUpperCaser("10202004000.1").getCnStringAll2());
//		System.out.println(new CnUpperCaser("123456789").getCnString());
//		System.out.println(new CnUpperCaser(".123456789").getCnString());
//		System.out.println(new CnUpperCaser("0.1234").getCnString());
//		System.out.println(new CnUpperCaser("1").getCnString());
//		System.out.println(new CnUpperCaser("12").getCnString());
//		System.out.println(new CnUpperCaser("123").getCnString());
//		System.out.println(new CnUpperCaser("1234").getCnString());
//		System.out.println(new CnUpperCaser("12345").getCnString());
//		System.out.println(new CnUpperCaser("123456").getCnString());
//		System.out.println(new CnUpperCaser("1234567").getCnString());
//		System.out.println(new CnUpperCaser("12345678").getCnString());
//		System.out.println(new CnUpperCaser("123456789").getCnString());
	}
}
