/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.permission;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.Constants;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.role.RoleManager;

/**
 * 权限管理的Controller
 * 
 * @author wumi
 * @version $Id: QuxanxianController.java, v 0.1 Jan 18, 2013 2:29:39 PM wumi
 *          Exp $
 */
@SuppressWarnings("unchecked")
@Controller
public class PermissionController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(PermissionController.class);

	@Autowired
	private PermissionManager permissionManager;
	@Autowired
	private RoleManager roleManager;
	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/permissionList.htm")
	public String permissionList(ModelMap model, String role, String title,String name) {
		try {
			if (!StringUtil.isNotBlank(role)){
				if (CtxUtil.getCurUser().getId().equals(Constants.ROOT_ROLE)){
					role = Constants.ROOT_ROLE;
				} else {
					List<Combobox> combo = roleManager.queryRole();
					if(null != combo && combo.size()>0)
						role = combo.get(0).getId();
				}
			}
			
			JSONArray re = permissionManager.queryQuxian(role);
			model.addAttribute("re", re.toString());
			model.addAttribute("role", role);
			model.addAttribute("name", name);
			model.addAttribute("title", title);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "sys/permission/permissionList";
	}

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/saveQuxanxian.json")
	public void saveQuxanxian(ModelMap model, String role,
			HttpServletRequest req) {
		try {
			Enumeration<String> checkboxValue = req.getParameterNames();
			permissionManager.savePermission(role, checkboxValue);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 刷新权限数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/refQuxanxian.json")
	public void refQuxanxian(ModelMap model) {
		try {
			permissionManager.getSecurityData().loadAllResource();
			JsonResultUtil.suncess(model, "刷新成功！");
		} catch (Exception e) {
			log.error("刷新错误：", e);
			JsonResultUtil.fail(model, "刷新失败！");
		}
	}

}
