/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;

/**
 * 区域管理的Controller
 * 
 * @author wumi
 * @version $Id: AreaController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class AreaController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(AreaController.class);

	@Autowired
	private AreaManager areaManager;

	/**
	 * 到区域的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/areaList.htm")
	public String areaList(ModelMap model, String title) {
		this.areaQuery(model, null, null);
		model.addAttribute("title", title);
		return "sys/area/areaList";
	}

	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/areaQuery.json", produces = "application/json")
	public void areaQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = areaManager.queryArea(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 删除一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            区域的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/removeArea.json", produces = "application/json")
	public void removeArea(ModelMap model, String id) {
		try {
			areaManager.removeArea(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            区域的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/saveArea.json", produces = "application/json")
	public void saveArea(ModelMap model, AreaForm areaForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(areaForm));
			}
			areaManager.saveArea(areaForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 编辑一个菜单信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/editArea.htm")
	public String editArea(ModelMap model, AreaForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建区域");
			return "sys/area/editArea";
		}
		areaManager.areaInfo(frm);
		model.addAttribute("title", "修改区域");
		return "sys/area/editArea";
	}

	/**
	 * 菜单的公共选择界面
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/areaPubQuery.htm")
	public String areaPubQuery(ModelMap model, String id, String multi) {
		try {
			JSONArray re = areaManager.querySelectArea(id);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
		} catch (Exception e) {
			log.error("查询部门错误：", e);
		}
		return "common/areaPubQuery";
	}

	/**
	 * 区域类型
	 */
	@RequestMapping(value = "/areaType.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaType() {
		try {
			return AreaType.getTypes();
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
}
