/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.industry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * ��ҵ������Controller
 * 
 * @author wumi
 * @version $Id: IndustryController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class IndustryController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(IndustryController.class);

	@Autowired
	private IndustryManager industryManager;

	/**
	 * ���������͵ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return Υ�����͵ĳ�ʼ����
	 */
	@RequestMapping(value = "/industryList.htm")
	public String industryList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/industry/industryList";
	}

	/**
	 * ��ѯ�б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryIndustryList.json", produces = "application/json")
	public void queryIndustryList(ModelMap model, String name, String lawobjtype, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = industryManager.queryIndustry(name, lawobjtype, isActive, page, pageSize);
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
	@RequestMapping(value = "/edtiIndustry.htm")
	public String edtiIndustry(ModelMap model, IndustryForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½���ҵ");
			return "sys/industry/newIndustry";
		}
		industryManager.industryInfo(frm);
		model.addAttribute("title", "�༭��ҵ");
		return "sys/industry/newIndustry";
	}
	
	/**
	 * ����һ����ҵ��Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            �û�������form����
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/saveIndustry.json", produces = "application/json")
	public void saveIndustry(ModelMap model, IndustryForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			industryManager.saveIndustry(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ��һ����ҵ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            ��ҵid
	 */
	@RequestMapping(value = "/removeIndustry.json", produces = "application/json")
	public void removeIndustry(ModelMap model, String id) {
		try {
			industryManager.removeIndustry(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����ҵ��Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
}