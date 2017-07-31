/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.sys.po.TSysRole;

/**
 * 角色管理的Controller
 * 
 * @author wumi
 * @version $Id: RoleController.java, v 0.1 Dec 28, 2011 9:23:24 AM
 *          Administrator Exp $
 */
@Controller
public class RoleController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private RoleManager roleManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/roleList.htm")
	public String roleList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/role/roleList";
	}

	/**
	 * 查询角色列表
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
	@RequestMapping(value = "/roleQuery.json", produces = "application/json")
	public void roleQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = roleManager.queryRole(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到添加角色页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link RoleForm}
	 * @return 添加角色页面
	 */
	@RequestMapping(value = "/roleAdd.htm")
	public String roleAdd(ModelMap model, RoleForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建角色");
			return "sys/role/roleAdd";
		}
		TSysRole po = (TSysRole) roleManager.get(TSysRole.class, frm.getId());
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setNote(po.getDescribe());
		frm.setIsActive(po.getIsActive());
		frm.setIsSys(po.getIssys());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "修改角色");
		return "sys/role/roleAdd";
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 *            角色信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delRole.json", produces = "application/json")
	public void delRole(String id, ModelMap model) {
		try {
			this.roleManager.removeRole(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除用户信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * 保存角色
	 * 
	 * @param frm
	 *            {@link RoleForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/roleSave.json", produces = "application/json")
	public void roleSave(RoleForm frm, ModelMap model) {
		try {
			roleManager.saveRole(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 角色下拉列表的公共查询
	 */
	@RequestMapping(value = "/queryAllRole.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAllRole() {
		try {
			return roleManager.queryRole();
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
}
