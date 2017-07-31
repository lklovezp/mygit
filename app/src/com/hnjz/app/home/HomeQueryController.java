/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.home;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 
 * 首页面的Controller
 * @author sunkuo
 * @version $Id: HomeQueryController.java, v 0.1 2013-6-24 上午08:00:14 Administrator Exp $
 */
@Controller
public class HomeQueryController {

    /**日志*/
    private static final Log log = LogFactory.getLog(HomeQueryController.class);

    @Autowired
    private HomeManager      homeManager;

    /**
     * 统计饼图
     * @param name 
     * @return list
     */
    @RequestMapping(value = "/homeQuery.json", produces = "application/json")
    @ResponseBody
    public List<Object[]> homeQuery(String name) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("name:"+name);
            }
            //List<Object[]> list = homeManager.getWork(name);
            return null;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 到详细界面
     * 
     * @param model {@link ModelMap}
     * @return 详细界面
     */
    @RequestMapping(value = "/homeInfo.htm")
    public String homeInfo(ModelMap model, String hName, String hValue) {
        model.put("hName", hName);
        model.put("hValue", hValue);
        return "homeInfo";
    }

    /**
     * 详细页面
     * 
     * @param model {@link ModelMap}
     * @param name 通过hName,hValue查询
     * @param page 第几页
     * @param pageSize 每页显示多少条
     */
    @RequestMapping(value = "/homeInfoQuery.json", produces = "application/json")
    public void homeInfoQuery(ModelMap model, String hName, String hValue, String page,
                              String pageSize) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("表单信息:" + LogUtil.m(hName) + LogUtil.m(hValue));
            }
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = homeManager.homeInfo(hName, hValue, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
}
