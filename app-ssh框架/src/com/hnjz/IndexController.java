/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.loginlog.LoginLogManager;

/**
 * 主页控制器
 * 
 * @author wumi
 * @version $Id: IndexController.java, v 0.1 Dec 27, 2011 10:22:04 AM Administrator Exp $
 */
@Controller
public class IndexController {
    /**日志*/
    private static final Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    private IndexManager     indexManager;

    @Autowired
    private LoginLogManager    loginLogManager;
    
    @Value("#{settings['WHETHER_THE_JOB_RUN']}")
    
    /**
     * 展示主页，主要是查询出左侧的菜单<br>
     * 主要解决ie10点击兼容性视图时，会重新登录
     * 
     * @return
     */
    @RequestMapping(value = "/ire.htm")
    public String ire() {
        return "ire";
    }

    /**
     * 展示主页，主要是查询出左侧的菜单
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/i.htm")
    public String i(ModelMap model) {
    	JSONObject map= indexManager.i();
        model.addAttribute("map", map);
        model.addAttribute("name", CtxUtil.getCurUser().getName());
        if (log.isDebugEnabled()) {
            log.debug("当前登录用户为：" + CtxUtil.getCurUser().getName());
        }
        if("a0000000000000000000000000000000".equals(CtxUtil.getCurUser().getId()) || "Y".equals(CtxUtil.getCurUser().getIssys())){
        	return "i";
        }else{
        	return "i_user";
        }
    }

    /**
     * 展示主页，主要是查询出左侧的菜单
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/home.htm")
    public String toHome(ModelMap model) {
    	try{
			//本月登录次数
        	model.addAttribute("theMonthLogTimes", "0");
        	//所有登录次数
        	model.addAttribute("allLogTimes", "0");
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return "home";
    }

}
