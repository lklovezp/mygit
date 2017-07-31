/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.quarterChecktimeSet;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.xxgl.tslawobj.TslawobjManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;

/**
 * ���ȳ��ʱ�����õ�Controller
 * @author shiqiuhan
 * @created 2016-3-17,����03:02:13
 */
@Controller
public class QuarterChecktimeSetController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(QuarterChecktimeSetController.class);
	
	@Autowired
	private TslawobjManager tslawobjManager;
	
	@Autowired
	private QuarterChecktimeSetManager quarterChecktimeSetManager;

	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/quarterChecktimeSetList.htm")
	public String quarterChecktimeSetList(ModelMap model, String title) {
		
		model.addAttribute("title", title);
		model.addAttribute("areaid", CtxUtil.getAreaId());
		return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetList";
	}

	/**
	 * ��ѯ���ȳ��ʱ���б�
	 * 
	 * @param frm
	 *            �������������԰���ݡ���������
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/quarterChecktimeSetQuery.json", produces = "application/json")
	public void quarterChecktimeSetQuery(ModelMap model, String page,QuarterChecktimeSetForm frm, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = quarterChecktimeSetManager.queryQuarterChecktimeSet(frm, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ת������/�޸ļ��ȳ��ʱ��ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link QuarterChecktimeSetForm}
	 * @return ���Ӽ��ȳ��ʱ��ҳ��
	 */
	@RequestMapping(value = "/quarterChecktimeSetAdd.htm")
	public String quarterChecktimeSetAdd(ModelMap model, QuarterChecktimeSetForm frm) {
		
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½����ȳ��ʱ��");
			model.addAttribute("areaid", CtxUtil.getAreaId());
			return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetAdd";
		}
		TDataQuarterChecktimeSet po = (TDataQuarterChecktimeSet) quarterChecktimeSetManager.get(TDataQuarterChecktimeSet.class, frm.getId());
		frm.setId(po.getId());
		frm.setYear(po.getYear());
		frm.setQuarter(po.getQuarter());
		frm.setTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",po.getTime()));
		frm.setIsActive(po.getIsActive());
		frm.setOrderby(po.getOrderby());
		model.addAttribute("title", "�޸ļ��ȳ��ʱ������");
		model.addAttribute("areaid", po.getArea().getId());
		return "sys/randomCheck/quarterChecktimeSet/quarterChecktimeSetAdd";
	}

	/**
	 * ɾ�����ȳ��ʱ��
	 * 
	 * @param id
	 *            ���ȳ��ʱ����Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delQuarterChecktimeSet.json", produces = "application/json")
	public void delQuarterChecktimeSet(String id, ModelMap model) {
		try {
			this.quarterChecktimeSetManager.removeQuarterChecktimeSet(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.debug("ɾ�����ȳ��ʱ����Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ���漾�ȳ��ʱ����Ϣ
	 * 
	 * @param frm
	 *            {@link QuarterChecktimeSetForm}
	 * @param model
	 *            {@link ModelMap}
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/quarterChecktimeSetSave.json", produces = "application/json")
	public void quarterChecktimeSetSave(QuarterChecktimeSetForm frm, ModelMap model) throws ParseException {
		try {
			if(StringUtils.isBlank(frm.getId())){
				if(StringUtils.isNotBlank(frm.getYear()) && StringUtils.isNotBlank(frm.getQuarter())){
					String time = quarterChecktimeSetManager.queryTime(frm.getYear(), frm.getQuarter(),frm.getArea());
					
					//String err=tslawobjManager.saveHistoryConfigCheck("", "", "");
					String err="";
		    		if(StringUtils.isBlank(err)){
		    			JsonResultUtil.fail(model, "����ȷ�ϱ�������������ã�");
		    			return;
		    		}
					if(StringUtils.isNotBlank(time)){
		    			JsonResultUtil.fail(model, "�ü��ȳ��ʱ�����趨�������ظ�����");
		    			return;
		    		}
				}
			}
			quarterChecktimeSetManager.saveQuarterChecktimeSet(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}