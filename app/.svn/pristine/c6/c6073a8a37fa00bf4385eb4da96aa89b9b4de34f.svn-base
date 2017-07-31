/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;

/**
 * 版本管理的Controller
 * 
 * @author wumi
 * @version $Id: VersionController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class VersionController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(VersionController.class);

	@Autowired
	private VersionManager versionManager;

	/**
	 * 到版本的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 版本的初始界面
	 */
	@RequestMapping(value = "/versionList.htm")
	public String versionList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/version/versionList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryVersionList.json", produces = "application/json")
	public void queryVersionList(ModelMap model, String code, String name, String isActive,
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = versionManager.queryVersion(code, name, isActive, page,
					pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryVersionType.json", produces = "application/json")
	public List<Combobox> queryVersionType(ModelMap model) {
		return FileTypeEnums.getTypeList(FileTypeEnums.APP.getCode());
	}

	/**
	 * 编辑一个版本信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/editVersion.htm")
	public String edtiVersion(ModelMap model, VersionForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建版本");
			return "sys/version/newVersion";
		}
		versionManager.versionInfo(frm);
		model.addAttribute("title", "编辑版本");
		return "sys/version/newVersion";
	}

	/**
	 * 保存一个版本信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm
	 *            版本的表单
	 * @return 版本的初始界面
	 */
	@RequestMapping(value = "/saveVersion.htm")
	public void saveVersion(ModelMap model, HttpServletRequest request, HttpServletResponse response, VersionForm frm) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			versionManager.saveVersion(frm, frm.getFile(), request);
			JsonResultUtil.suncess(model, "保存成功！");
			model.remove("versionForm");
			writer.print("{\"state\":true,\"msg\":\"保存成功\"}");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
			writer.print("{\"state\":false,\"msg\":\"" + e.getMessage() + "\"}");
		}
	}

	/**
	 * 删除一个版本
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            版本id
	 */
	@RequestMapping(value = "/removeVersion.json", produces = "application/json")
	public void delVersion(ModelMap model, String id) {
		try {
			versionManager.removeVersion(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除版本信息错误：", e);
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
	@RequestMapping(value = "/downloadVersion")
	public void downloadVersion(String id, HttpServletResponse res) {
		versionManager.downloadVersion(id, res);
	}
	
	/**
	 * 文件下载（下载过程中多线程下载时会重复调用方法，在生成文件的方法中进行下载会导致生成多个文件）
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downApk")
	public void downApk(ModelMap model, HttpServletResponse res) {
		try {
			versionManager.downApk(res);
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 文件下载（下载过程中多线程下载时会重复调用方法，在生成文件的方法中进行下载会导致生成多个文件）
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downMoblieApk")
	public void downMoblieApk(ModelMap model, HttpServletResponse res) {
		try {
			versionManager.downMoblieApkApk(res);
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 取得版本
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            版本id
	 */
	@RequestMapping(value = "/getVersion.json", produces = "application/json")
	public void getVersion(ModelMap model) {
		HashMap<String, Object> m = versionManager.getVersionMo();
		model.addAttribute("version", m.get("name"));
		model.addAttribute("data", m);
	}
	
	/**
	 * 取得手机端版本
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            版本id
	 */
	@RequestMapping(value = "/getMoblieVersion.json", produces = "application/json")
	public void getMoblieVersion(ModelMap model) {
		HashMap<String, Object> m = versionManager.getMoblieVersionMo();
		model.addAttribute("version", m.get("name"));
		model.addAttribute("data", m);
	}
}
