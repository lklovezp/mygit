/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;

/**
 * ���������Controller
 * 
 * @author wumi
 * @version $Id: AreaController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class AreaController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(AreaController.class);

	@Autowired
	private AreaManager areaManager;

	/**
	 * ������ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ����ĳ�ʼ����
	 */
	@RequestMapping(value = "/areaList.htm")
	public String areaList(ModelMap model, String title) {
		this.areaQuery(model, null, null);
		model.addAttribute("title", title);
		return "sys/area/areaList";
	}

	/**
	 * ��ѯ���нڵ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/areaQuery.json", produces = "application/json")
	public void areaQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = areaManager.queryArea(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ɾ��һ���˵���Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            �����form����
	 * @param result
	 *            {@link BindingResult}
	 * @return ����ĳ�ʼ����
	 */
	@RequestMapping(value = "/removeArea.json", produces = "application/json")
	public void removeArea(ModelMap model, String id) {
		try {
			areaManager.removeArea(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���˵���Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ����һ���˵���Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            �����form����
	 * @param result
	 *            {@link BindingResult}
	 * @return ����ĳ�ʼ����
	 */
	@RequestMapping(value = "/saveArea.json", produces = "application/json")
	public void saveArea(ModelMap model, AreaForm areaForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(areaForm));
			}
			areaManager.saveArea(areaForm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("����˵���Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * �༭һ���˵���Ϣ
	 * 
	 * @param id
	 *            �˵�Id
	 * @return ����ĳ�ʼ����
	 */
	@RequestMapping(value = "/editArea.htm")
	public String editArea(ModelMap model, AreaForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½�����");
			return "sys/area/editArea";
		}
		areaManager.areaInfo(frm);
		model.addAttribute("title", "�޸�����");
		return "sys/area/editArea";
	}

	/**
	 * �˵��Ĺ���ѡ�����
	 * 
	 * @param id
	 *            �˵�Id
	 * @return ����ĳ�ʼ����
	 */
	@RequestMapping(value = "/areaPubQuery.htm")
	public String areaPubQuery(ModelMap model, String id, String multi) {
		try {
			JSONArray re = areaManager.querySelectArea(id);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
		} catch (Exception e) {
			log.error("��ѯ���Ŵ���", e);
		}
		return "common/areaPubQuery";
	}

	/**
	 * ��������
	 */
	@RequestMapping(value = "/areaType.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaType() {
		try {
			return AreaType.getTypes();
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
}