/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 手机端用户登录
 * 
 * @author wumi
 * @version $Id: LoginController.java, v 0.1 Apr 22, 2013 9:09:50 AM wumi Exp $
 */
@Controller
public class LoginController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private LoginManager loginManager;

	/**
	 * 手机端用户登录
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            用户名
	 * @param pwd
	 *            密码
	 */
	@RequestMapping(value = "/login.mo", produces = "application/json")
	public void login(ModelMap model, HttpServletRequest request, String name,
			String pwd) {
		if (log.isDebugEnabled()) {
			log.debug("name:" + name + "---" + "pwd:*******");
		}
		try{
			loginManager.saveLogin(model, request, name, pwd);
		} catch (Exception e){
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 手机端用户登录
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            用户名
	 */
	@RequestMapping(value = "/test.mo", produces = "application/json")
	public void test(ModelMap model, String name) {
		TSysUser u = CtxUtil.getCurUser();
		if (log.isDebugEnabled()) {
			log.debug("name:" + u.getName());
		}
	}

}
