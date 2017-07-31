/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

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
import com.hnjz.sys.po.TDataCheckProportion;

/**
 * �������趨��Controller
 * @author shiqiuhan
 * @created 2015-12-16,����01:38:29
 */
@Controller
public class CheckProportionController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(CheckProportionController.class);

	@Autowired
	private CheckProportionManager checkProportionManager;

	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/checkProportionList.htm")
	public String checkProportionList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/randomCheck/configCheckProportion/checkProportionList";
	}

	/**
	 * ��ѯ���ȳ������б�
	 * 
	 * @param frm
	 *            �������������԰���ݡ����ȡ�����������
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkProportionQuery.json", produces = "application/json")
	public void checkProportionQuery(ModelMap model, String page,CheckProportionForm frm, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = checkProportionManager.queryCheckProportion(frm, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ת������/�޸ļ��ȳ�����ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link CheckProportionForm}
	 * @return ���Ӽ��ȳ�����ҳ��
	 */
	@RequestMapping(value = "/checkProportionAdd.htm")
	public String checkProportionAdd(ModelMap model, CheckProportionForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½����ȳ�����");
			return "sys/randomCheck/configCheckProportion/checkProportionAdd";
		}
		TDataCheckProportion po = (TDataCheckProportion) checkProportionManager.get(TDataCheckProportion.class, frm.getId());
		frm.setId(po.getId());
		frm.setYear(po.getYear());
		frm.setQuarter(po.getQuarter());
		frm.setProportion(String.valueOf(po.getProportion()));
		frm.setIsActive(po.getIsActive());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "�޸ļ��ȳ�����");
		return "sys/randomCheck/configCheckProportion/checkProportionAdd";
	}
	/**
	 * 
	 * �������ܣ����������б�
	 * 
	 */
	@RequestMapping(value = "/quarterList.json")
	@ResponseBody
	public List<Combobox> quarterList(ModelMap model) {
		return checkProportionManager.queryQuarterList();
	}

	/**
	 * ɾ�����ȳ�����
	 * 
	 * @param id
	 *            ���ȳ�������Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delCheckProportion.json", produces = "application/json")
	public void delCheckProportion(String id, ModelMap model) {
		try {
			this.checkProportionManager.removeCheckProportion(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.debug("ɾ�����ȳ�������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ���漾�ȳ�������Ϣ
	 * 
	 * @param frm
	 *            {@link CheckProportionForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/checkProportionSave.json", produces = "application/json")
	public void checkProportionSave(CheckProportionForm frm, ModelMap model) {
		try {
			if(StringUtils.isBlank(frm.getId()) && StringUtils.isNotBlank(frm.getYear()) && StringUtils.isNotBlank(frm.getQuarter())){
				int proportion = checkProportionManager.queryProportion(frm.getYear(), frm.getQuarter());
	    		if(proportion!=0){
	    			JsonResultUtil.fail(model, "�ü��ȳ��������趨�������ظ�����");
	    			return;
	    		}
			}
			checkProportionManager.saveCheckProportion(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}