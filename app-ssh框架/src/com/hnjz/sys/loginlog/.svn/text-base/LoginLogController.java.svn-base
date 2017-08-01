/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.loginlog;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 登录日志管理的Controller
 * 
 * @author wumi
 * @version $Id: LogController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class LoginLogController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(LoginLogController.class);

	@Autowired
	private LoginLogManager loginLogManager;

	/**
	 * 到的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 的初始界面
	 */
	@RequestMapping(value = "/loginLogList.htm")
	public String logList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/loginlog/loginlogList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryLoginLogList.json", produces = "application/json")
	public void queryLogList(ModelMap model, String name, String username, String loginip, String logintype, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = loginLogManager.queryLoginLogList(name, username, loginip, logintype, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 * 查询登录次数统计数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryLogTimesList.json", produces = "application/json")
	public void queryLogTimesList(ModelMap model, String areaId, String name, String starttime, String endtime, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = loginLogManager.queryLogTimesList(areaId, name, starttime, endtime, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 * 绩效考核：登录次数统计
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 的初始界面
	 */
	@RequestMapping(value = "/logTimesList.htm")
	public String logTimesList(ModelMap model, String title) {
		TSysUser u = CtxUtil.getCurUser();
		//默认当前区域
		String areaId = u.getOrgId();
		model.addAttribute("areaId", areaId);
		//默认起始时间为上个月第一天至最后一天。
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar cal2= Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.DATE, -1);
		
		String starttime = DateUtil.getDate(cal.getTime());
		String endtime = DateUtil.getDate(cal2.getTime());
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);
		
		model.addAttribute("title", title);
		return "sys/loginlog/logTimesList";
	}

	/**
	 * 绩效考核：登录次数详情
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 的初始界面
	 */
	@RequestMapping(value = "/logTimesListDetails.htm")
	public String logTimesListDetails(ModelMap model,String title,String id, String areaId, String starttime, String endtime){
		model.addAttribute("title", title);
		model.addAttribute("id", id);
		model.addAttribute("areaId", areaId);
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);
		return "sys/loginlog/logTimesListDetails";
	}
	
	
	/**
	 * 查询登录次数统计数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryLogTimesListDetails.json", produces = "application/json")
	public void queryLogTimesListDetails(ModelMap model, String id,String starttime, String endtime, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = loginLogManager.queryLogTimesListDetails(id,starttime, endtime, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 首页：查询本月或所有登录次数统计数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 的初始界面
	 */
	@RequestMapping(value = "/logTimesDetails.htm")
	public String logTimesDetails(ModelMap model,String title,String bythemonth){
		model.addAttribute("title", title);
		model.addAttribute("bythemonth", bythemonth);
		return "sys/loginlog/logTimesDetails";
	}
	
	/**
	 * 首页：查询本月或所有登录次数统计数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryLogTimesDetails.json", produces = "application/json")
	public void queryLogTimesDetails(ModelMap model, String bythemonth, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = loginLogManager.queryLogTimesDetails(bythemonth, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
}
