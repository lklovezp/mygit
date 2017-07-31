/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.sys.mobile.MobileManager;

/**
 * 手机登录后，获取用户具有的菜单
 * 
 * @author wumi
 * @version $Id: ZcdController.java, v 0.1 Apr 24, 2013 4:32:29 PM wumi Exp $
 */
@Controller
public class ZcdController {

    @Autowired
    private MobileManager mobileManager;

    /**
     * 获取用户具有的手机端的操作
     * 
     * @param model {@link ModelMap}
     */
    @RequestMapping(value = "/zcd.mo", produces = "application/json")
    @ResponseBody
    public List<Map<String, String>> zcd(ModelMap model) {
        return mobileManager.queryMo();
    }

}
