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
 * �������͹�����Controller
 * 
 * @author wumi
 * @version $Id: TaskTypeController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class TaskTypeController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(TaskTypeController.class);

	@Autowired
	private TaskTypeManager taskTypeManager;

	/**
	 * ���������͵ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �������͵ĳ�ʼ����
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
	 * ���������ĳһ��������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �������͵ı༭����
	 */
	@RequestMapping(value = "/editTaskType.htm")
	public String editTaskType(ModelMap model, TaskTypeForm frm) {
		
		if (StringUtils.isBlank(frm.getId())){
			model.addAttribute("title", "�½���������");
			return "sys/tasktype/editTaskType";
		}
		model.addAttribute("title", "�༭��������");
		taskTypeManager.taskTypeInfo(frm);
		return "sys/tasktype/editTaskType";
	}
	
	/**
	 * ����������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �������͵ı༭����
	 */
	@RequestMapping(value = "/addSubTaskType.htm")
	public String addSubTaskType(ModelMap model, TaskTypeForm frm) {
		model.addAttribute("title", "����������");
		return "sys/tasktype/addSubTaskType";
	}
	
	/**
	 * ��ѯ�������������� 
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
	 * ��ѯ�������������� 
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
	 * ������������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ������������
	 */
	@RequestMapping(value = "/saveTaskType.json")
	public void saveTaskType(ModelMap model, TaskTypeForm frm) {
		taskTypeManager.saveTaskType(frm);
	}
	
	/**
	 * ɾ����������
	 * 
	 * @param model {@link ModelMap}
	 * @param id ��������id
	 * @return ɾ����������
	 */
	@RequestMapping(value = "/removeTaskType.json")
	public void removeTaskType(ModelMap model, String id) {
		try {
			taskTypeManager.removeTaskType(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ������������Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
}