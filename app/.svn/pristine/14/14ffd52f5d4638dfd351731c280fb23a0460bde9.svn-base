/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.spotCheck;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.xxgl.yearlawobj.YearLawobjManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.configCheckProportion.CheckProportionManager;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TSysUser;

/**
 * 随机抽选的Controller
 * @author shiqiuhan
 * @created 2015-12-16,下午01:38:29
 */
@Controller
public class SpotCheckController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(SpotCheckController.class);
	
	@Autowired
	private SpotCheckManager spotCheckManager;
	
	@Autowired
	private CheckProportionManager checkProportionManager;
	
	@Autowired
	private YearLawobjManager yearLawobjManager;
	
	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/spotCheckList.htm")
	public String spotCheckList(ModelMap model, String title,String areaid) {
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		model.addAttribute("title", title);
		return "sys/randomCheck/spotCheck/spotCheckList";
	}
	
	/**
	 * 开始抽选(季度)
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 *            
	 * @throws Exception
	 */
	@RequestMapping(value = "/startCheck.json", produces = "application/json")
	public void startCheck(ModelMap model, String year, String quarter, String areaid) {
		try {
			int proportion = checkProportionManager.queryProportion(year, quarter);//季度抽查比例
			//根据年份季度查询被抽中的企业列表
			List<TBizCheckedLawobj> tBizCheckedLawobj= spotCheckManager.queryCheckedList(year,quarter);
			if(!yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "该年度尚未完成抽查，请先完成年度抽选工作");
				return ;
			}
			if(tBizCheckedLawobj!=null && tBizCheckedLawobj.size()>0){
				JsonResultUtil.fail(model, "该季度已完成抽选，可点击查询查看抽选结果");
				return ;
			}else if(proportion==0){
				JsonResultUtil.fail(model, "该季度抽查比例未设置，请先设置抽查比例");
				return ;
			}else if(proportion<25){//抽查比例小于25%
				JsonResultUtil.fail(model, "抽查比例不可低于25%，请重新设置抽查比例");
				return ;
			}
			spotCheckManager.startSpotCheck(year, quarter,areaid);
			JsonResultUtil.suncess(model, "抽选成功");
		} catch (Exception e) {
			log.error("抽选出错：", e);
		}
	}
	
	/**
	 * 开始抽选(年度)
	 * 
	 * @param year
	 *            年份
	 * @throws Exception
	 */
	@RequestMapping(value = "/yearCheck.json", produces = "application/json")
	public void yearCheck(ModelMap model, String year) {
		try {
			//根据年份查询该年度是否已完成抽选
			if(yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "该年度已完成抽选，可点击查询查看抽选结果");
				return ;
			}
			spotCheckManager.startYearCheck(year);
			JsonResultUtil.suncess(model, "抽选成功");
		} catch (Exception e) {
			log.error("抽选出错：", e);
		}
	}
	
	/**
	 * 删除抽选结果(年度)
	 * 
	 * @param year
	 *            年份
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteResult.json", produces = "application/json")
	public void deleteResult(ModelMap model, String year) {
		try {
			//根据年份查询该年度是否已完成抽选
			if(!yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "该年度还未进行年度抽查");
				return ;
			}
			spotCheckManager.deleteResult(year);
			JsonResultUtil.suncess(model, "删除成功");
		} catch (Exception e) {
			log.error("抽选出错：", e);
		}
	}
	
	/**
	 * 生成任务
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 *            
	 * @throws Exception
	 */
	@RequestMapping(value = "/createWork.json", produces = "application/json")
	public void createWork(ModelMap model, String year, String quarter) {
		try {
			String createResult = spotCheckManager.createWork(year, quarter);
			JsonResultUtil.suncess(model, createResult);
		} catch (Exception e) {
			log.error("抽选出错：", e);
		}
	}
	
	/**
	 * 查询被抽中的污染源列表
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkedLawobjQuery.json", produces = "application/json")
	public void checkedLawobjQuery(ModelMap model, String page,String year, String month,String areaid,
			String pageSize) {
		try {
			
			if(StringUtils.isBlank(areaid)){
				areaid=CtxUtil.getAreaId();
			}
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = spotCheckManager.queryCheckedLawobj(year, month,areaid,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
}
