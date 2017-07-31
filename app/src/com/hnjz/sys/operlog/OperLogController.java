/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.operlog;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;

/**
 * ������־������Controller
 * 
 * @author wumi
 * @version $Id: LogController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class OperLogController {

	/** ��־ */
	private static final Log log = LogFactory
			.getLog(OperLogController.class);

	@Autowired
	private OperLogManager operLogManager;

	/**
	 * ���ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �ĳ�ʼ����
	 */
	@RequestMapping(value = "/operLogList.htm")
	public String logList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/operlog/operlogList";
	}

	/**
	 * ��ѯ�б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryOperLogList.json", produces = "application/json")
	public void queryLogList(ModelMap model, String czsjFrom, String czsjTo, String czrName, String operateType, String note, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = operLogManager.queryOperLogList(czsjFrom, czsjTo, czrName, operateType, note, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * 
	 * �������ܣ��������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/operateTypeList.json")
	@ResponseBody
	public List<Combobox> operateTypeList(ModelMap model) {
		return WorkLogType.getTypes();
	}
}