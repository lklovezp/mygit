/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configchecktemplate;

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

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 设置监察模板管理的Controller
 * 
 * @author wumi
 * @config $Id: ConfigController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class ConfigController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(ConfigController.class);

	@Autowired
	private ConfigManager configManager;

	/**
	 * 到设置监察模板的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 设置监察模板的初始界面
	 */
	@RequestMapping(value = "/configCheckTemplate.htm")
	public String configList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/configCheckTemplate/configList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryConfigList.json", produces = "application/json")
	public void queryConfigList(ModelMap model, String tasktypeid, String lawobjtype, String isexecchecklist,
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = configManager.queryConfig(tasktypeid, lawobjtype, isexecchecklist, page,
					pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 编辑一个设置监察模板信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/editConfig.htm")
	public String edtiVersion(ModelMap model, ConfigForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建设置监察模板");
			return "sys/configCheckTemplate/newConfig";
		}
		configManager.configInfo(frm);
		model.addAttribute("title", "编辑设置监察模板");
		return "sys/configCheckTemplate/newConfig";
	}

	/**
	 * 保存一个设置监察模板信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm
	 *            设置监察模板的表单
	 * @return 设置监察模板的初始界面
	 */
	@RequestMapping(value = "/saveConfig.htm")
	public void saveConfig(ModelMap model, HttpServletResponse response, ConfigForm frm) {
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
			configManager.saveConfig(frm, frm.getFile());
			JsonResultUtil.suncess(model, "保存成功！");
			model.remove("configForm");
			writer.print("{\"state\":true,\"msg\":\"保存成功\"}");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
	}

	/**
	 * 删除一个设置监察模板
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            设置监察模板id
	 */
	@RequestMapping(value = "/removeConfig.json", produces = "application/json")
	public void delVersion(ModelMap model, String id) {
		try {
			configManager.removeConfig(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除设置监察模板信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
	/**
	 * 文件下载（下载过程中多线程下载时会重复调用方法，在生成文件的方法中进行下载会导致生成多个文件）
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downloadCheckTemplate")
	public void downloadCheckTemplate(String id, HttpServletResponse res) {
		configManager.downloadCheckTemplate(id, res);
	}
}
