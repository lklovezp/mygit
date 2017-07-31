/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.server;

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
import com.hnjz.sys.role.RoleController;
import com.hnjz.sys.role.RoleForm;

/**
 * ��������Controller
 * 
 * @author wumi
 * @version $Id: ServerController.java, v 0.1 Mar 26, 2013 4:37:52 PM wumi Exp $
 */
@Controller
public class ServerController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private ServerManager serverManager;

	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/serverList.htm")
	public String serverList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/server/serverList";
	}

	/**
	 * ��ѯ�����������б�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            �������������԰����ƣ���ע����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 */
	@RequestMapping(value = "/serverQuery.json", produces = "application/json")
	public void serverQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = serverManager.queryServer(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ת�����ӷ���������ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link RoleForm}
	 * @return ���ӷ���������ҳ��
	 */
	@RequestMapping(value = "/serverAdd.htm")
	public String serverAdd(ModelMap model, ServerForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½�������");
			return "sys/server/serverAdd";
		}
		serverManager.serverInfo(frm);
		model.addAttribute("title", "�޸ķ�����");
		return "sys/server/serverAdd";
	}

	/**
	 * �������������
	 * 
	 * @param id
	 *            ������������Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/removeServer.json", produces = "application/json")
	public void removeServer(String id, ModelMap model) {
		try {
			serverManager.removeServer(id);
			JsonResultUtil.suncess(model, "�����ɹ���");
		} catch (AppException e) {
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * ���������
	 * 
	 * @param frm
	 *            {@link RoleForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/saveServer.json", produces = "application/json")
	public void saveServer(ServerForm frm, ModelMap model) {
		try {
			serverManager.saveServer(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ���������������б��Ĺ�����ѯ
	 */
	@RequestMapping(value = "/queryServer.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryServer() {
		try {
			return serverManager.queryServer();
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}

}