/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 用户管理的Controller
 * 
 * @author zhangqingfeng
 * @version $Id: UserController.java, v 0.1 Jan 17, 2017 9:27:01 AM wumi Exp $
 */
@Controller
public class UserController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserManager userManager;

	/**
	 * 到用户管理的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/userList.htm")
	public String userList(ModelMap model, String title,String id,String areaId,String starttime,String endtime) {
		model.addAttribute("title", title);
		return "sys/user/userList";
	}

	/**
	 * 用户管理的查询
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 */
	@RequestMapping(value = "/userQuery.json", produces = "application/json")
	public void userQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = userManager.queryUser(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 删除一个用户
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            用户id
	 */
	@RequestMapping(value = "/delUser.json", produces = "application/json")
	public void delUser(ModelMap model, String id) {
		try {
			userManager.removeUser(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除用户信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}

	/**
	 * 保存一个用户信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            用户管理的form表单
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/saveUser.json", produces = "application/json")
	public void saveUser(ModelMap model, UserForm frm, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			frm.setPassword("888888");
			userManager.saveUser(frm, frm.getFile());
			model.remove("userForm");
			writer.print("{\"state\":true,\"msg\":\"保存成功\"}");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
	}

	/**
	 * 编辑一个用户信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/editUser.htm")
	public String editUser(ModelMap model, String id) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + id);
		}
		if (StringUtils.isNotBlank(id)) {
			UserForm userForm = userManager.editUser(id);
			model.addAttribute("userForm", userForm);
			model.addAttribute("title", "编辑用户");
		} else {
			model.addAttribute("title", "新建用户");
		}
		return "sys/user/editUser";
	}
	
	/**
	 * 修改密码
	 * 
	 * @param id
	 *            用户Id
	 */
	@RequestMapping(value = "/editPas.htm")
	public String editPas(ModelMap model, UserForm frm, String title) {
		model.addAttribute("id", CtxUtil.getCurUser().getId());
		model.addAttribute("title", title);
		return "sys/user/editPas";
	}

	/**
	 * 保存密码
	 * 
	 * @param id
	 *            用户Id
	 */
	@RequestMapping(value = "/savePas.json", produces = "application/json")
	public void savePas(ModelMap model, UserForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			String msg = userManager.savePas(frm);
			JsonResultUtil.suncess(model, msg);
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 *            用户Id
	 */
	@RequestMapping(value = "/resetPas.json", produces = "application/json")
	public void resetPas(ModelMap model, String id) {
		try {
			userManager.resetPas(id);
			JsonResultUtil.suncess(model, "重置密码成功");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

}
