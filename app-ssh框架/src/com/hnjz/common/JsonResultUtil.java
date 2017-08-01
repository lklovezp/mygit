/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.lob.SerializableClob;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;

/**
 * 框架中返回json结果的工具类
 * 
 * @author wumi
 * @version $Id: JsonResultUtil.java, v 0.1 Jan 11, 2013 10:06:20 AM wumi Exp $
 */
@SuppressWarnings("unchecked")
public class JsonResultUtil {

    private static final Log log = LogFactory.getLog(JsonResultUtil.class);

    /**
     * 返回操作成功的json
     * 
     * @param model {@link }
     * @param msg 提示信息
     */
    public static void suncess(ModelMap model, String msg) {
        model.addAttribute("state", Boolean.TRUE);
        model.addAttribute("msg", msg);
    }

    /**
     * 返回操作失败的json
     * 
     * @param model {@link }
     * @param msg 提示信息
     */
    public static void fail(ModelMap model, String msg) {
        model.addAttribute("state", Boolean.FALSE);
        model.addAttribute("msg", msg);
    }

    /**
     * 返回操作成功的json
     * 
     * @param model {@link }
     * @param re 分页结果
     */
    public static void fyWeb(ModelMap model, FyWebResult re) {
        model.addAttribute("total", re.getTotal());
        model.addAttribute("pageSize", re.getPerPageNum());
        model.addAttribute("pageNumber", re.getPageNumber());
        model.addAttribute("rows", re.getRows());
    }

    /**
     * 返回操作成功的json
     * 
     * @param model {@link }
     * @param re 分页结果
     */
    public static void listWeb(ModelMap model, List<?> rows) {
        model.addAttribute("total", rows.size());
        model.addAttribute("rows", rows);
    }

    /**
     * 返回数据
     * 
     * @param model
     * @param str
     */
    public static void show(ModelMap model, Map<String, Object> map) {
        model.addAllAttributes(map);
    }
    /**
     * 将对象转化为String，如果为空，返回""
     * 
     * @param re 要转化为String的对象
     * @return 将对象转化为String，如果为空，返回""
     */
     public static String get(Object re){
    	 return String.valueOf(null == re ? "" : re);
     }

    /**
     * 将JSONArray字符串转换成List<Map<String, String>>
     * 
     * @param str  JSONArray字符串
     * @return  转换过的List<Map<String, String>>
     */
    public static List<Map<String, String>> jsonToList(String str) {
        List<Map<String, String>> re = new ArrayList<Map<String, String>>();
        try {
            Map<String, String> map = null;
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                map = new HashMap<String, String>();
                JSONObject o = array.getJSONObject(i);
                Iterator<String> keys = o.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    map.put(key, StringUtils.trim(o.getString(key)));
                }
                if (log.isDebugEnabled()) {
                    log.debug("map:" + map);
                }
                re.add(map);
            }
        } catch (JSONException e) {
            log.error("", e);
        }
        return re;
    }
    /**
     * 将JSONArray字符串转换成List<Combobox>
     * 
     * @param str  JSONArray字符串
     * @return  转换过的List<Combobox>
     */
    public static List<Combobox> jsonToList_Comb(String str) {
        List<Combobox> re = new ArrayList<Combobox>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                re.add(new Combobox(array.getJSONObject(i).getString("id"), array
                    .getJSONObject(i).getString("name")));
            }
        } catch (JSONException e) {
            log.error("", e);
        }
        return re;
    }
    
    /**
     * 
     * 函数介绍：List<Map<String,Object>> 转为JSONArray
    
     * 输入参数：
    
     * 返回值：
     */
    public static JSONArray listMapToJSONArray(List<Map<String,Object>> listMap){
    	JSONArray jsonArray = new JSONArray();
    	try {
			if(listMap!=null && listMap.size()>0){
				for(Map<String,Object> map : listMap){
					JSONObject jsonObject = new JSONObject();
					jsonArray.put(jsonObject);
					for(Map.Entry<String, Object> en : map.entrySet()){
						if(en.getValue()!=null && en.getValue() instanceof SerializableClob){
							jsonObject.put(en.getKey(), getClob((SerializableClob) en.getValue()));
						}else{
							String value = en.getValue()==null?"":String.valueOf(en.getValue());
							if(en.getKey().equals("CREATED_") || en.getKey().equals("UPDATED_")){
								if(StringUtil.isNotBlank(value) && value.contains(".0")){
									Date date = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss.S",value);
									value = DateUtil.getDateTime(date);
								}
							}
							jsonObject.put(en.getKey(), value);
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return jsonArray;
    }
    /**
     * 
     * 函数介绍：获取clob类型的数据
    
     * 输入参数：
    
     * 返回值：
     */
	public static String getClob(SerializableClob c) {
		Reader reader = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = c.getCharacterStream();
			br = new BufferedReader(reader);
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
    
}
