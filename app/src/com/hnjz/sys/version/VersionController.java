/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;

/**
 * �汾������Controller
 * 
 * @author wumi
 * @version $Id: VersionController.java, v 0.1 2013-3-25 ����03:59:37 wumi Exp $
 */
@Controller
public class VersionController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(VersionController.class);

	@Autowired
	private VersionManager versionManager;

	/**
	 * ���汾�ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �汾�ĳ�ʼ����
	 */
	@RequestMapping(value = "/versionList.htm")
	public String versionList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/version/versionList";
	}

	/**
	 * ��ѯ�б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryVersionList.json", produces = "application/json")
	public void queryVersionList(ModelMap model, String code, String name, String isActive,
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = versionManager.queryVersion(code, name, isActive, page,
					pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ѯ�б�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryVersionType.json", produces = "application/json")
	public List<Combobox> queryVersionType(ModelMap model) {
		return FileTypeEnums.getTypeList(FileTypeEnums.APP.getCode());
	}

	/**
	 * �༭һ���汾��Ϣ
	 * 
	 * @param id
	 *            �û�Id
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/editVersion.htm")
	public String edtiVersion(ModelMap model, VersionForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½��汾");
			return "sys/version/newVersion";
		}
		versionManager.versionInfo(frm);
		model.addAttribute("title", "�༭�汾");
		return "sys/version/newVersion";
	}

	/**
	 * ����һ���汾��Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm
	 *            �汾�ı���
	 * @return �汾�ĳ�ʼ����
	 */
	@RequestMapping(value = "/saveVersion.htm")
	public void saveVersion(ModelMap model, HttpServletRequest request, HttpServletResponse response, VersionForm frm) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			versionManager.saveVersion(frm, frm.getFile(), request);
			JsonResultUtil.suncess(model, "����ɹ���");
			model.remove("versionForm");
			writer.print("{\"state\":true,\"msg\":\"����ɹ�\"}");
		} catch (Exception e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
			writer.print("{\"state\":false,\"msg\":\"" + e.getMessage() + "\"}");
		}
	}

	/**
	 * ɾ��һ���汾
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �汾id
	 */
	@RequestMapping(value = "/removeVersion.json", produces = "application/json")
	public void delVersion(ModelMap model, String id) {
		try {
			versionManager.removeVersion(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���汾��Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}

	/**
	 * �ļ����أ����ع����ж��߳�����ʱ���ظ����÷������������ļ��ķ����н������ػᵼ�����ɶ���ļ���
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downloadVersion")
	public void downloadVersion(String id, HttpServletResponse res) {
		versionManager.downloadVersion(id, res);
	}
	
	/**
	 * �ļ����أ����ع����ж��߳�����ʱ���ظ����÷������������ļ��ķ����н������ػᵼ�����ɶ���ļ���
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downApk")
	public void downApk(ModelMap model, HttpServletResponse res) {
		try {
			versionManager.downApk(res);
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * �ļ����أ����ع����ж��߳�����ʱ���ظ����÷������������ļ��ķ����н������ػᵼ�����ɶ���ļ���
	 * 
	 * @param filePath
	 * @param fileName
	 * @param res
	 */
	@RequestMapping(value = "/downMoblieApk")
	public void downMoblieApk(ModelMap model, HttpServletResponse res) {
		try {
			versionManager.downMoblieApkApk(res);
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ȡ�ð汾
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �汾id
	 */
	@RequestMapping(value = "/getVersion.json", produces = "application/json")
	public void getVersion(ModelMap model) {
		HashMap<String, Object> m = versionManager.getVersionMo();
		model.addAttribute("version", m.get("name"));
		model.addAttribute("data", m);
	}
	
	/**
	 * ȡ���ֻ��˰汾
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �汾id
	 */
	@RequestMapping(value = "/getMoblieVersion.json", produces = "application/json")
	public void getMoblieVersion(ModelMap model) {
		HashMap<String, Object> m = versionManager.getMoblieVersionMo();
		model.addAttribute("version", m.get("name"));
		model.addAttribute("data", m);
	}
}