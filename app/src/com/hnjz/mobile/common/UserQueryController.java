/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.sys.user.UserPubQueryManager;

/**
 * 
 * @author wumi
 * @version $Id: UserQuery.java, v 0.1 Apr 22, 2013 4:56:49 PM wumi Exp $
 */
@Controller
public class UserQueryController {

    /**日志*/
    private static final Log    log = LogFactory.getLog(UserQueryController.class);

    @Autowired
    private UserPubQueryManager userPubQueryManager;

    /**
     * 手机端人员选择
     * 
     * @param model
     */
    @RequestMapping(value = "/queryRy.mo", produces = "application/json")
    public void queryRy(ModelMap model, String all, String notShowZj, String notShowSys) {
        try {
            JSONArray re = userPubQueryManager.queryUser(null, all, notShowZj, notShowSys);
            if (log.isDebugEnabled()) {
                log.debug("re:" + re.toString());
            }
            model.addAttribute("re", re.toString());
        } catch (Exception e) {
            log.error("", e);
        }

    }

}
