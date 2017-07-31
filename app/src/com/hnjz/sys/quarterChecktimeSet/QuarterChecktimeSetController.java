/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.quarterChecktimeSet;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.xxgl.tslawobj.TslawobjManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;

/**
 * 季度抽查时间设置的Controller
 * @author shiqiuhan
 * @created 2016-3-17,下午03:02:13
 */
@Controller
public class QuarterChecktimeSetController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(QuarterChecktimeSetController.class);
	
	@Autowired
	private TslawobjManager tslawobjManager;
	
	@Autowired
	private QuarterChecktimeSetManager quarterChecktimeSetManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/quarterChecktimeSetList.htm")
	public String quarterChecktimeSetList(ModelMap model, String title) {
		
		model.addAttribute("title", title);
		model.addAttribute("areaid", CtxUtil.getAreaId());
		return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetList";
	}

	/**
	 * 查询季度抽查时间列表
	 * 
	 * @param frm
	 *            搜索条件，可以按年份、季度搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/quarterChecktimeSetQuery.json", produces = "application/json")
	public void quarterChecktimeSetQuery(ModelMap model, String page,QuarterChecktimeSetForm frm, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = quarterChecktimeSetManager.queryQuarterChecktimeSet(frm, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到添加/修改季度抽查时间页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link QuarterChecktimeSetForm}
	 * @return 添加季度抽查时间页面
	 */
	@RequestMapping(value = "/quarterChecktimeSetAdd.htm")
	public String quarterChecktimeSetAdd(ModelMap model, QuarterChecktimeSetForm frm) {
		
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建季度抽查时间");
			model.addAttribute("areaid", CtxUtil.getAreaId());
			return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetAdd";
		}
		TDataQuarterChecktimeSet po = (TDataQuarterChecktimeSet) quarterChecktimeSetManager.get(TDataQuarterChecktimeSet.class, frm.getId());
		frm.setId(po.getId());
		frm.setYear(po.getYear());
		frm.setQuarter(po.getQuarter());
		frm.setTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",po.getTime()));
		frm.setIsActive(po.getIsActive());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "修改季度抽查时间设置");
		model.addAttribute("areaid", po.getArea().getId());
		return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetAdd";
	}

	/**
	 * 删除季度抽查时间
	 * 
	 * @param id
	 *            季度抽查时间信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delQuarterChecktimeSet.json", produces = "application/json")
	public void delQuarterChecktimeSet(String id, ModelMap model) {
		try {
			this.quarterChecktimeSetManager.removeQuarterChecktimeSet(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除季度抽查时间信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存季度抽查时间信息
	 * 
	 * @param frm
	 *            {@link QuarterChecktimeSetForm}
	 * @param model
	 *            {@link ModelMap}
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/quarterChecktimeSetSave.json", produces = "application/json")
	public void quarterChecktimeSetSave(QuarterChecktimeSetForm frm, ModelMap model) throws ParseException {
		try {
			if(StringUtils.isBlank(frm.getId())){
				if(StringUtils.isNotBlank(frm.getYear()) && StringUtils.isNotBlank(frm.getQuarter())){
					String time = quarterChecktimeSetManager.queryTime(frm.getYear(), frm.getQuarter(),frm.getArea());
					
					//String err=tslawobjManager.saveHistoryConfigCheck("", "", "");
					String err="";
		    		if(StringUtils.isBlank(err)){
		    			JsonResultUtil.fail(model, "请先确认保存过随机抽查设置！");
		    			return;
		    		}
					if(StringUtils.isNotBlank(time)){
		    			JsonResultUtil.fail(model, "该季度抽查时间已设定，请勿重复设置");
		    			return;
		    		}
				}
			}
			quarterChecktimeSetManager.saveQuarterChecktimeSet(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}
