/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;

/**
 * 用户管理的Controller
 * 
 * @author zhangqingfeng
 * @version $Id: UserController.java, v 0.1 2016-03-14
 */
@Controller
public class UserMobileController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(UserMobileController.class);

	@Autowired
	private UserManager userManager;
	
	/**
	 * 手机端用户获取部门信息
	 * @param userId 登录用户的id
	 */
	@RequestMapping(value = "/getOrgbyUser.mo", produces = "application/json")
	public void getOrgbyUser(ModelMap model,String userId) {
		try{
			TSysOrg org = userManager.getOrgbyUser(userId);
			model.addAttribute("orgId", org.getId());//暂时先传部门IId，后续要加的多了，可以全封装对象里面
		} catch (Exception e){
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

}
