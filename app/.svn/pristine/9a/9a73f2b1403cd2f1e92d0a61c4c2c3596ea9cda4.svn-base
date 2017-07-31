package com.hnjz.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jsonUtils {

	
	 public static List<Map<String, String>> jsonStringToList(String rsContent) throws Exception
	    {
	        JSONArray arry = JSONArray.fromObject(rsContent);

	        System.out.println("json×Ö·û´®ÄÚÈÝÈçÏÂ");
	        System.out.println(arry);
	        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
	        for (int i = 0; i < arry.size(); i++)
	        {
	            JSONObject jsonObject = arry.getJSONObject(i);
	            Map<String, String> map = new HashMap<String, String>();
	            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
	            {
	                String key = (String) iter.next();
	                String value = jsonObject.get(key).toString();
	                map.put(key, value);
	            }
	            rsList.add(map);
	        }
	        return rsList;
	    }

}
