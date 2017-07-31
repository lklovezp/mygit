/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.industry;

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
 * 行业管理的Controller
 * 
 * @author wumi
 * @version $Id: IndustryController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class IndustryController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(IndustryController.class);

	@Autowired
	private IndustryManager industryManager;

	/**
	 * 到任务类型的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的初始界面
	 */
	@RequestMapping(value = "/industryList.htm")
	public String industryList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/industry/industryList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryIndustryList.json", produces = "application/json")
	public void queryIndustryList(ModelMap model, String name, String lawobjtype, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = industryManager.queryIndustry(name, lawobjtype, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 编辑一个用户信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/edtiIndustry.htm")
	public String edtiIndustry(ModelMap model, IndustryForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建行业");
			return "sys/industry/newIndustry";
		}
		industryManager.industryInfo(frm);
		model.addAttribute("title", "编辑行业");
		return "sys/industry/newIndustry";
	}
	
	/**
	 * 保存一个行业信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            用户管理的form表单
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/saveIndustry.json", produces = "application/json")
	public void saveIndustry(ModelMap model, IndustryForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			industryManager.saveIndustry(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除一个行业
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            行业id
	 */
	@RequestMapping(value = "/removeIndustry.json", produces = "application/json")
	public void removeIndustry(ModelMap model, String id) {
		try {
			industryManager.removeIndustry(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除行业信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
}
