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
 * �Զ��ɷ�������Controller
 * 
 * @author wumi
 * @version $Id: TimerTaskController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class TimerTaskController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(TimerTaskController.class);

	@Autowired
	private TimerTaskManager timerTaskManager;

	/**
	 * ���������͵ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return Υ�����͵ĳ�ʼ����
	 */
	@RequestMapping(value = "/timerTaskList.htm")
	public String timerTaskList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/timertask/timerTaskList";
	}

	/**
	 * ��ѯ�б�����
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
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * �༭һ���û���Ϣ
	 * 
	 * @param id
	 *            �û�Id
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/editTimerTask.htm")
	public String editTimerTask(ModelMap model, TimerTaskForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½��Զ��ɷ�����");
			return "sys/timertask/newTimerTask";
		}
		timerTaskManager.timerTaskInfo(frm);
		model.addAttribute("title", "�༭�Զ��ɷ�����");
		return "sys/timertask/newTimerTask";
	}
	
	/**
	 * ����һ���Զ��ɷ���Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            �û�������form����
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/saveTimerTask.json", produces = "application/json")
	public void saveTimerTask(ModelMap model, TimerTaskForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			timerTaskManager.saveTimerTask(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ��һ���Զ��ɷ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �Զ��ɷ�id
	 */
	@RequestMapping(value = "/removeTimerTask.json", produces = "application/json")
	public void delUser(ModelMap model, String id) {
		try {
			timerTaskManager.removeTimerTask(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���Զ��ɷ���Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
	
	/**
	 * 
	 * �������ܣ���������������
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/taskTypeExceptZX.json")
	@ResponseBody
	public List<ComboboxTree> taskTypeExceptZX(ModelMap model, String oper) {
		return timerTaskManager.taskTypeExceptZX();
	}
	
	/**
	 * 
	 * �������ܣ���������������
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getLawObjTypeByTaskType.json")
	@ResponseBody
	public List<Combobox> getLawObjTypeByTaskType(ModelMap model, String tasktype) {
		return timerTaskManager.getLawObjTypeByTaskType(tasktype);
	}
}