/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 部门管理的Controller
 * @author wumi
 * @version $Id: OrgController.java, v 0.1 Jan 15, 2013 5:04:04 PM wumi Exp $
 */
@Controller
public class OrgController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(OrgController.class);

	@Autowired
	private OrgManager orgManager;

	/**
	 * 到菜单功能的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/orgList.htm")
	public String orgList(ModelMap model, String title) {
		this.orgQuery(model, null, null);
		model.addAttribute("title", title);
		return "sys/org/orgList";
	}

	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/orgQuery.json", produces = "application/json")
	public void orgQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = orgManager.queryOrg(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 删除一个部门信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            部门Id
	 * @return 删除结果
	 */
	@RequestMapping(value = "/removeOrg.json", produces = "application/json")
	public void removeOrg(ModelMap model, String id) {
		try {
			orgManager.removeOrg(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.error("删除部门信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存一个部门信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param orgForm
	 *            部门功能的form表单
	 * @return 部门功能的初始界面
	 */
	@RequestMapping(value = "/saveOrg.json", produces = "application/json")
	public void saveOrg(ModelMap model, OrgForm orgForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(orgForm));
			}
			orgManager.saveOrg(orgForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存部门信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 编辑一个部门信息
	 * 
	 * @param id
	 *            部门Id
	 * @return 部门功能的初始界面
	 */
	@RequestMapping(value = "/editOrg.htm")
	public String editOrg(ModelMap model, OrgForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建部门");
			// 新建部门时 如果不是管理员则锁定区域
			if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
				model.addAttribute("admin", "1");
			} else {
				model.addAttribute("area", "");
				model.addAttribute("areaName", "");
				model.addAttribute("admin", "0");
			}
			return "sys/org/editOrg";
		}
		if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
			model.addAttribute("admin", "1");
		} else {
			model.addAttribute("admin", "0");
		}
		orgManager.orgInfo(frm);
		model.addAttribute("title", "编辑部门");
		return "sys/org/editOrg";
	}

	/**
	 * 部门的公共选择界面
	 * 
	 * @param id
	 *            部门Id
	 * @return 部门功能的初始界面
	 */
	@RequestMapping(value = "/orgPubQuery.htm")
	public String orgPubQuery(ModelMap model, String id, String multi) {
		try {
			JSONArray re = orgManager.querySelectOrg(id);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("查询部门错误：", e);
		}
		return "common/orgPubQuery";
	}
	/**
	 * admin登录部门的公共选择界面
	 * 
	 * @param id
	 *            部门Id
	 * @return 部门功能的初始界面
	 */
	@RequestMapping(value = "/orgAdminQuery.htm")
	public String orgQuery(ModelMap model, String id, String areaid, String multi) {
		try {
			JSONArray re = orgManager.queryAdminOrg(id,areaid);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("查询部门错误：", e);
		}
		return "common/orgPubQuery";
	}

}
