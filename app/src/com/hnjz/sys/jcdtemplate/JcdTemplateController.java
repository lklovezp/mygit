/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * ���ģ�������Controller
 * 
 * @author wumi
 * @version $Id: JcdTemplateController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class JcdTemplateController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(JcdTemplateController.class);

	@Autowired
	private JcdTemplateManager jcdTemplateManager;

	/**
	 * �����ģ��ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ���ģ��ĳ�ʼ����
	 */
	@RequestMapping(value = "/jcdTemplateList.htm")
	public String jcdTemplateList(ModelMap model, String title) {
		
		this.jcdTemplateQuery(model, null);
		model.addAttribute("title", title);
		return "sys/jcdtemplate/jcdTemplateList";
	}
	
	/**
	 * ��ѯ���нڵ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryJcdTemplate.json", produces = "application/json")
	public void jcdTemplateQuery(ModelMap model, String name) {
		try {
			JSONArray re = jcdTemplateManager.queryJcdTemplate(name);
			model.addAttribute("menu", re.toString());
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ������ģ������ת��ҳ��
	 * @param model {@link ModelMap}
	 * @return ҳ��·��
	 */
	@RequestMapping(value = "/treeClickJumpPage.htm")
	public String treeClickJumpPage(ModelMap model, TemplateForm frm) {
		return jcdTemplateManager.treeClickJumpPage(frm);
	}
	
	/**
	 * ��ת���½������ҳ��
	 * @param model {@link ModelMap}
	 * @return �½������ĳ�ʼ����
	 */
	@RequestMapping(value = "/editCheckListItem.htm")
	public String newCheckListItem(ModelMap model, CheckItemForm frm) {
		jcdTemplateManager.queryCheckListList(frm);
		return "sys/jcdtemplate/newCheckListItem";
	}
	
	/**
	 * ��ת���汾ҳ��
	 * @param model {@link ModelMap}
	 * @return �½������ĳ�ʼ����
	 */
	@RequestMapping(value = "/editTemplateVersion.htm")
	public String editTemplateVersion(ModelMap model, TemplateForm frm) {
		
		return jcdTemplateManager.editTemplateVersion(frm);
	}
	
	/**
	 * ������ģ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ������ģ��
	 */
	@RequestMapping(value = "/saveTemplate.json", produces = "application/json")
	public void saveTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveTemplate(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ������ģ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ������ģ��
	 */
	@RequestMapping(value = "/saveSubTemplate.json", produces = "application/json")
	public void saveSubTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveSubTemplate(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ����ģ��汾
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ������ģ��
	 */
	@RequestMapping(value = "/saveTemplateVersion.json", produces = "application/json")
	public void saveTemplateVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveTemplateVersion(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ���渴�Ƶ�ģ��汾
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveCopyVersion.json", produces = "application/json")
	public void saveCopyVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveCopyVersion(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��ת���½������ҳ��
	 * @param model {@link ModelMap}
	 * @return �½������ĳ�ʼ����
	 */
	@RequestMapping(value = "/copyTemplatePage.htm")
	public String copyTemplatePage(ModelMap model, String oldIndustry, String oldTaskType) {
		TemplateForm t = new TemplateForm();
		t.setIndustry(oldIndustry);
		t.setTasktype(oldTaskType);
		TDataChecklisttemplate po = jcdTemplateManager.queryTemplateWithNoId(t);
		model.addAttribute("title", "����ģ��");
		model.addAttribute("oldIndustry", oldIndustry);
		model.addAttribute("oldTaskType", oldTaskType);
		model.addAttribute("name", po.getName());
		return "sys/jcdtemplate/copyTemplate";
	}
	
	/**
	 * ���渴�Ƶ�ģ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveCopyTemplate.json", produces = "application/json")
	public void saveCopyTemplate(ModelMap model, String name, String oldIndustry, String oldTaskType, String industry, String taskType) {
		try {
			jcdTemplateManager.saveCopyTemplate(name, oldIndustry, oldTaskType, industry, taskType);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��������
	 */
	@RequestMapping(value = "/saveCheckListItem.json", produces = "application/json")
	public void saveCheckListItem(ModelMap model, CheckItemForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveCheckListItem(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��ѯ������б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryCheckListItem.json", produces = "application/json")
	public void queryCheckListItem(ModelMap model, String templateid,
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = jcdTemplateManager.queryCheckListItem(templateid, page,
					pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ѯ������б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryCheckItemAns.json", produces = "application/json")
	public void queryCheckItemAns(ModelMap model, String itemId) {
		try {
			FyWebResult re = jcdTemplateManager.queryCheckItemAns(itemId);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ת��������ģ��ҳ��
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/addSubTemplate.htm")
	public String addSubTemplate(ModelMap model, TemplateForm frm) {
		jcdTemplateManager.addSubTemplate(frm);
		return "sys/jcdtemplate/newSubTemplate";
	}
	
	/**
	 * ����ΪĬ��ģ��
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveDefaultVersion.json")
	public void saveDefaultVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveDefaultVersion(frm);
			JsonResultUtil.suncess(model, "���óɹ���");
		} catch (Exception e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, "����ʧ�ܡ�");
		}
	}
	
	/**
	 * ɾ�������
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeCheckListItme.json")
	public void removeCheckListItme(ModelMap model, CheckItemForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeCheckListItme(frm);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܡ�");
		}
	}
	
	/**
	 * ɾ��ģ��
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeTemplate.json")
	public void removeTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeTemplate(frm);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ���汾
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeTempVersion.json")
	public void removeVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeVersion(frm);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ����ģ��
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeSubTemplate.json")
	public void removeSubTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeSubTemplate(frm);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��ѯ���нڵ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/templateTree.json", produces = "application/json")
	public void templateTree(ModelMap model, String templateId) {
		try {
			JSONArray re = jcdTemplateManager.templateTree(templateId);
			model.addAttribute("menu", re.toString());
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
}