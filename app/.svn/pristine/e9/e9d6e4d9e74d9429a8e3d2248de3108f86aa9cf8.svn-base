/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.tasktype;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.ComboboxTree;

/**
 * 任务类型管理的Controller
 * 
 * @author wumi
 * @version $Id: TaskTypeController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class TaskTypeController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(TaskTypeController.class);

	@Autowired
	private TaskTypeManager taskTypeManager;

	/**
	 * 到任务类型的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 任务类型的初始界面
	 */
	@RequestMapping(value = "/taskTypeList.htm")
	public String taskTypeList(ModelMap model, String title) {
		
		JSONArray arr = new JSONArray();
		try {
			arr = taskTypeManager.queryTaskType();
			model.addAttribute("menu", arr.toString());
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sys/tasktype/taskTypeList";
	}
	
	/**
	 * 点击树加载某一任务类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 任务类型的编辑界面
	 */
	@RequestMapping(value = "/editTaskType.htm")
	public String editTaskType(ModelMap model, TaskTypeForm frm) {
		
		if (StringUtils.isBlank(frm.getId())){
			model.addAttribute("title", "新建任务类型");
			return "sys/tasktype/editTaskType";
		}
		model.addAttribute("title", "编辑任务类型");
		taskTypeManager.taskTypeInfo(frm);
		return "sys/tasktype/editTaskType";
	}
	
	/**
	 * 添加子类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 任务类型的编辑界面
	 */
	@RequestMapping(value = "/addSubTaskType.htm")
	public String addSubTaskType(ModelMap model, TaskTypeForm frm) {
		model.addAttribute("title", "添加子类型");
		return "sys/tasktype/addSubTaskType";
	}
	
	/**
	 * 查询任务类型下拉树 
	 * 
	 * @return ComboboxTree value : id; text:name
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTaskTypeIdName.json")
	@ResponseBody
	public List<ComboboxTree> queryTaskTypeTree(ModelMap model, String id) {
		List<ComboboxTree> cs = taskTypeManager.queryTaskTypeIdTree(id);
		return cs;
	}
	
	/**
	 * 查询任务类型下拉树 
	 * 
	 * @return ComboboxTree value : id; text:name
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTaskTypeCodeName.json")
	@ResponseBody
	public List<ComboboxTree> queryTaskTypeCodeName(ModelMap model, String id) {
		List<ComboboxTree> cs = taskTypeManager.queryTaskTypeCodeTree(id);
		return cs;
	}
	
	/**
	 * 保存任务类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存任务类型
	 */
	@RequestMapping(value = "/saveTaskType.json")
	public void saveTaskType(ModelMap model, TaskTypeForm frm) {
		taskTypeManager.saveTaskType(frm);
	}
	
	/**
	 * 删除任务类型
	 * 
	 * @param model {@link ModelMap}
	 * @param id 任务类型id
	 * @return 删除任务类型
	 */
	@RequestMapping(value = "/removeTaskType.json")
	public void removeTaskType(ModelMap model, String id) {
		try {
			taskTypeManager.removeTaskType(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除任务类型信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
}
