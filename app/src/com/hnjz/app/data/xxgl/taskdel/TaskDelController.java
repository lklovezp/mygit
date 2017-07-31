/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.taskdel;

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
import com.hnjz.sys.po.TSysUser;

/**
 * ����ɾ����Controller
 * 
 * @author wumi
 * @version $Id: TaskDelController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class TaskDelController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(TaskDelController.class);

	@Autowired
	private TaskDelManager taskDelManager;

	/**
	 * ������ɾ���ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ����ɾ���ĳ�ʼ����
	 */
	@RequestMapping(value = "/taskDelList.htm")
	public String taskDelList(ModelMap model, String title,String areaid) {
		model.addAttribute("title", title);
		//Ĭ�ϵ�ǰ����
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
				areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		return "app/data/xxgl/taskdel/taskDelList";
	}

	/**
	 * ��ѯ�б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryTaskDelList.json", produces = "application/json")
	public void queryTaskDelList(ModelMap model, String areaid, String name, String hander, String endTimeFrom, String endTimeTo, String page,
			String pageSize,String rwzt,String zbrId) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = taskDelManager.queryTaskList(areaid, name, hander,endTimeFrom, endTimeTo, page, pageSize,rwzt,zbrId);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ɾ��һ������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �����ɷ�id
	 */
	@RequestMapping(value = "/removeTask.json", produces = "application/json")
	public void delUser(ModelMap model, String id) {
		try {
			taskDelManager.removeTask(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���Զ��ɷ���Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
}