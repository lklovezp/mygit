/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;

/**
 * 用户登录后主页的帮助类
 * 
 * @author wumi
 * @version $Id: IndexManager.java, v 0.1 Feb 6, 2013 9:23:40 AM wumi Exp $
 */
@Service("indexManager")
public class IndexManager {
    /**日志*/
    private static final Log log = LogFactory.getLog(IndexManager.class);

    @Autowired
    private Dao              dao;
    public static int mode;
    public static String sysVer;
    public static String webURL;
   
    
    
    /**
     * 用户登录后获取用户所具有的菜单
     * 
     * @return 
     */
    public JSONObject i() {
    	JSONObject obj=new JSONObject();
    	//Map<String, JSONObject<String,JSONArray>> map=new HashMap<String, JSONObject<String,JSONArray>>();
        try {
            String role = CtxUtil.getCurUser().getId();
            JSONArray arr1=new JSONArray();
            JSONArray arr2=null;
            //封装lev1的数据
            JSONObject obj1 = new JSONObject();
            JSONObject obj11 = new JSONObject();
            
            obj1.put("name", "首页");
            obj1.put("id", "01");
            obj1.put("icon", "li01");
            obj1.put("href", "home.htm");
            arr1.put(obj1);
            obj11.put("lev1", arr1);
        } catch (JSONException e) {
            log.error("用户登录查询菜单错误：", e);
        }
        return obj;
    }

}
