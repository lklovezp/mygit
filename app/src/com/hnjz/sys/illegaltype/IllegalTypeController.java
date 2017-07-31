/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;

/**
 * Υ�����͹�����Controller
 * 
 * @author wumi
 * @version $Id: IllegalTypeController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class IllegalTypeController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(IllegalTypeController.class);

	@Autowired
	private IllegalTypeManager illegalTypeManager;

	/**
	 * ��Υ�����͵ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return Υ�����͵ĳ�ʼ����
	 */
	@RequestMapping(value = "/illegalTypeList.htm")
	public String illegalTypeList(ModelMap model, String title) {
		
		JSONArray arr = new JSONArray();
		try {
			arr = illegalTypeManager.queryIllegalType();
			model.addAttribute("menu", arr.toString());
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/blgl/blglList";
	}
	
	/**
	 * ���������ĳһΥ������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return Υ�����͵ı༭����
	 */
	@RequestMapping(value = "/editIllegalType.htm")
	public String editIllegalType(ModelMap model, IllegalTypeForm frm) {
		
		if (StringUtils.isBlank(frm.getId())){
			model.addAttribute("title", "�½�Υ������");
			return "sys/illegaltype/editIllegalType";
		}
		illegalTypeManager.IllegalTypeInfo(frm);
		return "sys/illegaltype/editIllegalType";
	}
	
	/**
	 * ���������ĳһΥ������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return Υ�����͵ı༭����
	 */
	@RequestMapping(value = "/queryIllegalType.json")
	public void queryIllegalType(ModelMap model, IllegalTypeForm frm) {
		JSONArray ar = new JSONArray();
		ar = illegalTypeManager.IllegalTypeInfo(frm);
		model.addAttribute("data", ar.toString());
	}
	
	/**
	 * ����Υ������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ����Υ������
	 */
	@RequestMapping(value = "/saveIllegalType.json")
	public void saveIllegalType(ModelMap model, IllegalTypeForm frm) {
		try {
			illegalTypeManager.saveIllegalType(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ��Υ������
	 * 
	 * @param model {@link ModelMap}
	 * @param id Υ������id
	 * @return ɾ��Υ������
	 */
	@RequestMapping(value = "/removeIllegalType.json")
	public void removeIllegalType(ModelMap model, String id) {
		try {
			illegalTypeManager.removeIllegalType(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.error("ɾ��Υ��������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}