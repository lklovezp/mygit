/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.AppException;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;

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
            String hsql = "select function.id,function.name,function.function.id, function.linkAddr from TSysRoleFunc where role.id in(select role.id from TSysUserRole where user.id = ?) ";
            if (mode == 1){
            	hsql += " and function.id not in ('a0000000000000000000000000000001', '40288ace4b80bdc0014b80eab67f008b') ";
            }
            hsql += " order by function.orderby ";
            List<Object[]> re = dao.find(hsql, role);
            //角色的权限有重复的时候过滤掉重复的菜单项
            for(int i=0; i < re.size();i++){
            	for(int j=re.size()-1;j > i;j--){
					if(String.valueOf(re.get(i)[0]).equals(String.valueOf(re.get(j)[0]))){
	                    re.remove(j);
	                 }
            	}
            }
            JSONArray arr1=new JSONArray();
            JSONArray arr2=null;
            JSONArray arr3=null;
            //封装lev1的数据
            JSONObject obj1 = new JSONObject();
            JSONObject obj11 = new JSONObject();
            
            obj1.put("name", "首页");
            obj1.put("id", "01");
            obj1.put("icon", "li01");
            obj1.put("href", "home.htm");
            arr1.put(obj1);
            obj11.put("lev1", arr1);
            int a = 1;
            for (Object[] ele : re) {
            	obj1 = new JSONObject();
            	if ("".equals(String.valueOf(ele[2])) || "null".equals(String.valueOf(ele[2]))) {
            		a++;
            		obj1.put("name", String.valueOf(ele[1]));
            		obj1.put("id", String.valueOf(ele[0]));
            		obj1.put("icon", "li0" + String.valueOf(a));
            		arr1.put(obj1);
            		obj11.put("lev1", arr1);
            		obj.put("0", obj11);
            		//封装二级菜单
            		arr2=new JSONArray();
            		//封装lev2的数据
                    JSONObject obj2 = null;
            		JSONObject obj22 = new JSONObject();
            		for(Object[] ele1 : re){
            			obj2 = new JSONObject();
                		if(String.valueOf(ele[0]).equals(String.valueOf(ele1[2]))){
                		    obj2.put("name",String.valueOf(ele1[1]) );
                			obj2.put("id",String.valueOf(ele1[0]));
                			String href=String.valueOf(ele1[3] == null ? "" : ele1[3])+"?title="+String.valueOf(ele1[1])+"&fid="+String.valueOf(ele1[0])+"";
                			obj2.put("href",href);
                			arr2.put(obj2);
                			obj22.put("lev2", arr2);
                			obj.put(String.valueOf(ele[0]), obj22);
                			//封装三级菜单
                    		arr3=new JSONArray();
                    		//封装lev3的数据
                            JSONObject obj3 = null;
                    		JSONObject obj33 = new JSONObject();
                    		for(Object[] ele2 : re){
                    			obj3 = new JSONObject();
                        		if(String.valueOf(ele1[0]).equals(String.valueOf(ele2[2]))){
                        		    obj3.put("name",String.valueOf(ele2[1]) );
                        			obj3.put("id",String.valueOf(ele2[0]));
                        			String href1=String.valueOf(ele2[3] == null ? "" : ele2[3])+"?title="+String.valueOf(ele2[1])+"&fid="+String.valueOf(ele2[0])+"";
                        			obj3.put("href",href1);
                        			arr3.put(obj3);
                        			obj33.put("lev3", arr3);
                        			obj.put(String.valueOf(ele1[0]), obj33);
                        		}
                        	}
                		}
                	}
                }
            }
        } catch (JSONException e) {
            log.error("用户登录查询菜单错误：", e);
        }
        return obj;
    }

    /**
     * 下载帮助文档
     * @param res
     * @param typeEnum 帮助文档类型枚举值
     * @throws Exception
     */
	public void downHelpDoc(HttpServletResponse res, String typeEnum) throws Exception{
		List<TDataFile> filePo = this.dao.find("from TDataFile where type = ?", typeEnum);
		if (!(filePo.size() > 0)){
			throw new AppException("无帮助文档可下载。");
		}
		String path = File.separator + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(0).getOsname();
		FileUpDownUtil.downloadFile(res, path, filePo.get(0).getName());
	}

	public void isExistsHelpDoc(String typeEnum) throws Exception{
		List<TDataFile> filePo = this.dao.find("from TDataFile where type = ?", typeEnum);
		if (!(filePo.size() > 0)){
			throw new AppException("无帮助文档可下载。");
		}
	}
}
