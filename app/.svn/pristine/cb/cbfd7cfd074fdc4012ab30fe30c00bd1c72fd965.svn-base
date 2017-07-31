/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.server;

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
import com.hnjz.sys.role.RoleController;
import com.hnjz.sys.role.RoleForm;

/**
 * 服务器的Controller
 * 
 * @author wumi
 * @version $Id: ServerController.java, v 0.1 Mar 26, 2013 4:37:52 PM wumi Exp $
 */
@Controller
public class ServerController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private ServerManager serverManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/serverList.htm")
	public String serverList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/server/serverList";
	}

	/**
	 * 查询服务器管理列表
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
	@RequestMapping(value = "/serverQuery.json", produces = "application/json")
	public void serverQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = serverManager.queryServer(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到添加服务器管理页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link RoleForm}
	 * @return 添加服务器管理页面
	 */
	@RequestMapping(value = "/serverAdd.htm")
	public String serverAdd(ModelMap model, ServerForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建服务器");
			return "sys/server/serverAdd";
		}
		serverManager.serverInfo(frm);
		model.addAttribute("title", "修改服务器");
		return "sys/server/serverAdd";
	}

	/**
	 * 保存服务器管理
	 * 
	 * @param id
	 *            服务器管理信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/removeServer.json", produces = "application/json")
	public void removeServer(String id, ModelMap model) {
		try {
			serverManager.removeServer(id);
			JsonResultUtil.suncess(model, "操作成功！");
		} catch (AppException e) {
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * 保存服务器
	 * 
	 * @param frm
	 *            {@link RoleForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/saveServer.json", produces = "application/json")
	public void saveServer(ServerForm frm, ModelMap model) {
		try {
			serverManager.saveServer(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 服务器管理下拉列表的公共查询
	 */
	@RequestMapping(value = "/queryServer.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryServer() {
		try {
			return serverManager.queryServer();
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}

}
