/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

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

/**
 * 年度抽查对象管理的Controller
 * @author shiqiuhan
 * @created 2016-3-10,下午04:15:44
 */
@Controller
public class YearLawobjController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(YearLawobjController.class);

	@Autowired
	private YearLawobjManager yearLawobjManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/yearLawobjList.htm")
	public String yearLawobjList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/data/xxgl/yearlawobj/yearLawobjList";
	}

	/**
	 * 查询年度抽查对象列表
	 * @param model
	 * @param lawobjname
	 * @param lawobjtype
	 * @param type
	 * @param page
	 * @param pageSize
	 */
	@RequestMapping(value = "/yearLawobjQuery.json", produces = "application/json")
	public void yearLawobjQuery(ModelMap model, String year, String lawobjname, String lawobjtype, String type, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = yearLawobjManager.queryYearLawobj(year,lawobjname, lawobjtype, type, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 跳转到添加年度抽查对象页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/yearLawobjAdd.htm")
	public String yearLawobjAdd(ModelMap model, String year) {
			model.addAttribute("title", "添加年度抽查对象");
			model.addAttribute("year", year);
			return "app/data/xxgl/yearlawobj/yearLawobjAdd";
	}
	
	/**
	 * 判断是否已完成抽选
	 * 
	 * @param year
	 *            年份
	 * @throws Exception
	 */
	@RequestMapping(value = "/isChecked.json", produces = "application/json")
	public void startCheck(ModelMap model, String year) {
		try {
			if(!yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "该年度尚未完成抽查，请先完成年度抽选工作");
				return ;
			}
			JsonResultUtil.suncess(model, "已完成年度抽选");
		} catch (Exception e) {
			log.error("出错：", e);
		}
	}
	

	/**
	 * 删除年度抽查对象
	 * @param id
	 * @param model
	 */
	@RequestMapping(value = "/delYearLawobj.json", produces = "application/json")
	public void delYearLawobj(String id, ModelMap model) {
		try {
			this.yearLawobjManager.removeYearLawobj(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除年度抽查对象信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * 批量保存年度抽查对象
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/yearLawobjSave.json", produces = "application/json")
	public void yearLawobjSave(ModelMap model,String ids,String names,String year) throws Exception {
		try {
			yearLawobjManager.saveYearLawobj(ids,names,year);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 
	 * 函数介绍：抽选类型下拉列表
	 * 
	 */
	@RequestMapping(value = "/cxlxList.json")
	@ResponseBody
	public List<Combobox> cxlxList(ModelMap model) {
		return yearLawobjManager.queryCxlxList();
	}
}
