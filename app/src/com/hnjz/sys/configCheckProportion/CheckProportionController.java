/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

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
import com.hnjz.sys.po.TDataCheckProportion;

/**
 * 抽查比例设定的Controller
 * @author shiqiuhan
 * @created 2015-12-16,下午01:38:29
 */
@Controller
public class CheckProportionController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(CheckProportionController.class);

	@Autowired
	private CheckProportionManager checkProportionManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/checkProportionList.htm")
	public String checkProportionList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/randomCheck/configCheckProportion/checkProportionList";
	}

	/**
	 * 查询季度抽查比例列表
	 * 
	 * @param frm
	 *            搜索条件，可以按年份、季度、抽查比例搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkProportionQuery.json", produces = "application/json")
	public void checkProportionQuery(ModelMap model, String page,CheckProportionForm frm, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = checkProportionManager.queryCheckProportion(frm, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到添加/修改季度抽查比例页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link CheckProportionForm}
	 * @return 添加季度抽查比例页面
	 */
	@RequestMapping(value = "/checkProportionAdd.htm")
	public String checkProportionAdd(ModelMap model, CheckProportionForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建季度抽查比例");
			return "sys/randomCheck/configCheckProportion/checkProportionAdd";
		}
		TDataCheckProportion po = (TDataCheckProportion) checkProportionManager.get(TDataCheckProportion.class, frm.getId());
		frm.setId(po.getId());
		frm.setYear(po.getYear());
		frm.setQuarter(po.getQuarter());
		frm.setProportion(String.valueOf(po.getProportion()));
		frm.setIsActive(po.getIsActive());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "修改季度抽查比例");
		return "sys/randomCheck/configCheckProportion/checkProportionAdd";
	}
	/**
	 * 
	 * 函数介绍：季度下拉列表
	 * 
	 */
	@RequestMapping(value = "/quarterList.json")
	@ResponseBody
	public List<Combobox> quarterList(ModelMap model) {
		return checkProportionManager.queryQuarterList();
	}

	/**
	 * 删除季度抽查比例
	 * 
	 * @param id
	 *            季度抽查比例信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delCheckProportion.json", produces = "application/json")
	public void delCheckProportion(String id, ModelMap model) {
		try {
			this.checkProportionManager.removeCheckProportion(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除季度抽查比例信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存季度抽查比例信息
	 * 
	 * @param frm
	 *            {@link CheckProportionForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/checkProportionSave.json", produces = "application/json")
	public void checkProportionSave(CheckProportionForm frm, ModelMap model) {
		try {
			if(StringUtils.isBlank(frm.getId()) && StringUtils.isNotBlank(frm.getYear()) && StringUtils.isNotBlank(frm.getQuarter())){
				int proportion = checkProportionManager.queryProportion(frm.getYear(), frm.getQuarter());
	    		if(proportion!=0){
	    			JsonResultUtil.fail(model, "该季度抽查比例已设定，请勿重复设置");
	    			return;
	    		}
			}
			checkProportionManager.saveCheckProportion(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}
