/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.area.AreaType;
import com.hnjz.sys.po.TSysArea;

/**
 * ���Ź�����Controller
 * 
 * @author wumi
 * @version $Id: OrgController.java, v 0.1 Jan 15, 2013 5:04:04 PM wumi Exp $
 */
@Controller
public class OrgController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(OrgController.class);

	@Autowired
	private OrgManager orgManager;
	
	@Autowired
	private AreaManager areaManager;

	/**
	 * ���˵����ܵĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �˵����ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/orgList.htm")
	public String orgList(ModelMap model, String title) {
		this.orgQuery(model, null, null);
		model.addAttribute("title", title);
		return "sys/org/orgList";
	}

	/**
	 * ��ѯ���нڵ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/orgQuery.json", produces = "application/json")
	public void orgQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = orgManager.queryOrg(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ɾ��һ��������Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            ����Id
	 * @return ɾ�����
	 */
	@RequestMapping(value = "/removeOrg.json", produces = "application/json")
	public void removeOrg(ModelMap model, String id) {
		try {
			orgManager.removeOrg(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.error("ɾ��������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ϵͳ����Ա���Ը����û�����������Ϊ���ڲ��ŵ�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/saveUserArea.json", produces = "application/json")
	public void saveUserArea(ModelMap model) {
		try {
			orgManager.saveUserArea();
			JsonResultUtil.suncess(model, "�����ɹ���");
		} catch (AppException e) {
			log.error("ɾ��������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ����һ��������Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param orgForm
	 *            ���Ź��ܵ�form����
	 * @return ���Ź��ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/saveOrg.json", produces = "application/json")
	public void saveOrg(ModelMap model, OrgForm orgForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(orgForm));
			}
			orgManager.saveOrg(orgForm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("���沿����Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * �༭һ��������Ϣ
	 * 
	 * @param id
	 *            ����Id
	 * @return ���Ź��ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/editOrg.htm")
	public String editOrg(ModelMap model, OrgForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "�½�����");
			// �½�����ʱ ������ǹ���Ա����������
			if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
				model.addAttribute("admin", "1");
			} else {
				TSysArea a = areaManager.getAreaByUser(CtxUtil.getCurUser().getId());
				model.addAttribute("area", a.getId());
				model.addAttribute("areaName", a.getName());
				model.addAttribute("admin", "0");
			}
			return "sys/org/editOrg";
		}
		if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
			model.addAttribute("admin", "1");
		} else {
			model.addAttribute("admin", "0");
		}
		orgManager.orgInfo(frm);
		model.addAttribute("title", "�༭����");
		return "sys/org/editOrg";
	}

	/**
	 * ���ŵĹ���ѡ�����
	 * 
	 * @param id
	 *            ����Id
	 * @return ���Ź��ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/orgPubQuery.htm")
	public String orgPubQuery(ModelMap model, String id, String areaid, String multi) {
		try {
			JSONArray re = orgManager.querySelectOrg(id,areaid);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("��ѯ���Ŵ���", e);
		}
		return "common/orgPubQuery";
	}
	/**
	 * admin��¼���ŵĹ���ѡ�����
	 * 
	 * @param id
	 *            ����Id
	 * @return ���Ź��ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/orgAdminQuery.htm")
	public String orgQuery(ModelMap model, String id, String areaid, String multi) {
		try {
			JSONArray re = orgManager.queryAdminOrg(id,areaid);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("��ѯ���Ŵ���", e);
		}
		return "common/orgPubQuery";
	}

	/**
	 * ��������
	 */
	@RequestMapping(value = "/orgType.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> orgType(String orgid) {
		List<Combobox> cs = new ArrayList<Combobox>();
		try {
			TSysArea a = new TSysArea();
			if (StringUtil.isNotBlank(orgid)){
				a = areaManager.getAreaByOrgid(orgid);
				String aType = a.getType();
				cs = OrgType.getTypes();
				for (Combobox c : cs) {
					if (c.getId().equals("0")){
						if (aType.equals(AreaType.BT.getCode())){
							c.setName("ִ���ܶ�");
						} else if (aType.equals(AreaType.S.getCode())) {
							c.setName("ִ��֧��");
						} else if (aType.equals(AreaType.TC.getCode())) {
							c.setName("ִ�����");
						}
						break;
					}
				}
				return cs;
			} else {
				if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
					cs = new ArrayList<Combobox>();
					Combobox c1 = new Combobox("0", "ִ���ܶ�");
					Combobox c2 = new Combobox("0", "ִ��֧��");
					Combobox c3 = new Combobox("0", "ִ�����");
					cs.add(c1);
					cs.add(c2);
					cs.add(c3);
					
				} else {
					a = areaManager.getAreaByUser(CtxUtil.getCurUser().getId());
					String aType = a.getType();
					cs = OrgType.getTypes();
					for (Combobox c : cs) {
						if (c.getId().equals("0")){
							if (aType.equals(AreaType.BT.getCode())){
								c.setName("ִ���ܶ�");
							} else if (aType.equals(AreaType.S.getCode())) {
								c.setName("ִ��֧��");
							} else if (aType.equals(AreaType.TC.getCode())) {
								c.setName("ִ�����");
							}
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return cs;
	}
}