/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.timertask;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.util.LogUtil;

/**
 * 自动派发管理的Controller
 * 
 * @author wumi
 * @version $Id: TimerTaskController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class TimerTaskController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(TimerTaskController.class);

	@Autowired
	private TimerTaskManager timerTaskManager;

	/**
	 * 到任务类型的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的初始界面
	 */
	@RequestMapping(value = "/timerTaskList.htm")
	public String timerTaskList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/timertask/timerTaskList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryTimerTaskList.json", produces = "application/json")
	public void queryTimerTaskList(ModelMap model, String name, String accepter, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = timerTaskManager.queryTimerTask(name, accepter, isActive, page, pageSize);
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
	@RequestMapping(value = "/editTimerTask.htm")
	public String editTimerTask(ModelMap model, TimerTaskForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建自动派发任务");
			return "sys/timertask/newTimerTask";
		}
		timerTaskManager.timerTaskInfo(frm);
		model.addAttribute("title", "编辑自动派发任务");
		return "sys/timertask/newTimerTask";
	}
	
	/**
	 * 保存一个自动派发信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            用户管理的form表单
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/saveTimerTask.json", produces = "application/json")
	public void saveTimerTask(ModelMap model, TimerTaskForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			timerTaskManager.saveTimerTask(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除一个自动派发
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            自动派发id
	 */
	@RequestMapping(value = "/removeTimerTask.json", produces = "application/json")
	public void delUser(ModelMap model, String id) {
		try {
			timerTaskManager.removeTimerTask(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除自动派发信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
	/**
	 * 
	 * 函数介绍：任务类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTypeExceptZX.json")
	@ResponseBody
	public List<ComboboxTree> taskTypeExceptZX(ModelMap model, String oper) {
		return timerTaskManager.taskTypeExceptZX();
	}
	
	/**
	 * 
	 * 函数介绍：任务类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/getLawObjTypeByTaskType.json")
	@ResponseBody
	public List<Combobox> getLawObjTypeByTaskType(ModelMap model, String tasktype) {
		return timerTaskManager.getLawObjTypeByTaskType(tasktype);
	}
}
