/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

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
import com.hnjz.sys.po.TSysRole;

/**
 * ��ɫ������Controller
 * 
 * @author wumi
 * @version $Id: RoleController.java, v 0.1 Dec 28, 2011 9:23:24 AM
 *          Administrator Exp $
 */
@Controller
public class RoleController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private RoleManager roleManager;

	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/roleList.htm")
	public String roleList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/role/roleList";
	}

	/**
	 * ��ѯ��ɫ�б�
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
	@RequestMapping(value = "/roleQuery.json", produces = "application/json")
	public void roleQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = roleManager.queryRole(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ת�����ӽ�ɫҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link RoleForm}
	 * @return ���ӽ�ɫҳ��
	 */
	@RequestMapping(value = "/roleAdd.htm")
	public String roleAdd(ModelMap model, RoleForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½���ɫ");
			return "sys/role/roleAdd";
		}
		TSysRole po = (TSysRole) roleManager.get(TSysRole.class, frm.getId());
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setNote(po.getDescribe());
		frm.setIsActive(po.getIsActive());
		frm.setIsSys(po.getIssys());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "�޸Ľ�ɫ");
		return "sys/role/roleAdd";
	}

	/**
	 * ɾ����ɫ
	 * 
	 * @param id
	 *            ��ɫ��Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delRole.json", produces = "application/json")
	public void delRole(String id, ModelMap model) {
		try {
			this.roleManager.removeRole(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.debug("ɾ���û���Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * �����ɫ
	 * 
	 * @param frm
	 *            {@link RoleForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/roleSave.json", produces = "application/json")
	public void roleSave(RoleForm frm, ModelMap model) {
		try {
			roleManager.saveRole(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ��ɫ�����б��Ĺ�����ѯ
	 */
	@RequestMapping(value = "/queryAllRole.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAllRole() {
		try {
			return roleManager.queryRole();
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
}