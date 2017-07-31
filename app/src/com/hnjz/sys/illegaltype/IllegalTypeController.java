/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

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

/**
 * 违法类型管理的Controller
 * 
 * @author wumi
 * @version $Id: IllegalTypeController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class IllegalTypeController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(IllegalTypeController.class);

	@Autowired
	private IllegalTypeManager illegalTypeManager;

	/**
	 * 到违法类型的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的初始界面
	 */
	@RequestMapping(value = "/illegalTypeList.htm")
	public String illegalTypeList(ModelMap model, String title) {
		
		JSONArray arr = new JSONArray();
		try {
			arr = illegalTypeManager.queryIllegalType();
			model.addAttribute("menu", arr.toString());
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/blgl/blglList";
	}
	
	/**
	 * 点击树加载某一违法类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的编辑界面
	 */
	@RequestMapping(value = "/editIllegalType.htm")
	public String editIllegalType(ModelMap model, IllegalTypeForm frm) {
		
		if (StringUtils.isBlank(frm.getId())){
			model.addAttribute("title", "新建违法类型");
			return "sys/illegaltype/editIllegalType";
		}
		illegalTypeManager.IllegalTypeInfo(frm);
		return "sys/illegaltype/editIllegalType";
	}
	
	/**
	 * 点击树加载某一违法类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的编辑界面
	 */
	@RequestMapping(value = "/queryIllegalType.json")
	public void queryIllegalType(ModelMap model, IllegalTypeForm frm) {
		JSONArray ar = new JSONArray();
		ar = illegalTypeManager.IllegalTypeInfo(frm);
		model.addAttribute("data", ar.toString());
	}
	
	/**
	 * 保存违法类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存违法类型
	 */
	@RequestMapping(value = "/saveIllegalType.json")
	public void saveIllegalType(ModelMap model, IllegalTypeForm frm) {
		try {
			illegalTypeManager.saveIllegalType(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除违法类型
	 * 
	 * @param model {@link ModelMap}
	 * @param id 违法类型id
	 * @return 删除违法类型
	 */
	@RequestMapping(value = "/removeIllegalType.json")
	public void removeIllegalType(ModelMap model, String id) {
		try {
			illegalTypeManager.removeIllegalType(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.error("删除违法类型信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}
