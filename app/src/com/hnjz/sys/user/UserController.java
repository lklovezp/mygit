/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.org.OrgManager;

/**
 * �û�������Controller
 * 
 * @author wumi
 * @version $Id: UserController.java, v 0.1 Jan 17, 2013 9:27:01 AM wumi Exp $
 */
@Controller
public class UserController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private OrgManager orgManager;

	/**
	 * ���û������ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �û������ĳ�ʼ����
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userList.htm")
	public String userList(ModelMap model, String title,String name,String id,String areaId,String starttime,String endtime) throws Exception {
		model.addAttribute("title", title);
		model.addAttribute("areaid", CtxUtil.getAreaId());
		JSONArray orgs = orgManager.queryOrg(name, "Y");//������
		model.addAttribute("orgList", orgs.toString());
		return "sys/user/userList";
	}

	/**
	 * �û������Ĳ�ѯ
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
	@RequestMapping(value = "/userQuery.json", produces = "application/json")
	public void userQuery(ModelMap model, String name, String isActive, String page,
			String pageSize,String orgid,String areaid) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = userManager.queryUser(name, isActive, page, pageSize,orgid,areaid);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ɾ��һ���û�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �û�id
	 */
	@RequestMapping(value = "/delUser.json", produces = "application/json")
	public void delUser(ModelMap model, String ids) {
		try {
			String[] idArray=ids.split(",");
			for(String id : idArray){
				userManager.removeUser(id);
			}
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���û���Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}

	/**
	 * ����һ���û���Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            �û�������form����
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/saveUser.json", produces = "application/json")
	public void saveUser(ModelMap model, UserForm frm, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			userManager.saveUser(frm, frm.getFile());
			model.remove("userForm");
			
			writer.print("{\"state\":true,\"msg\":\"����ɹ�\"}");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
	}

	/**
	 * �༭һ���û���Ϣ
	 * 
	 * @param id
	 *            �û�Id
	 * @return �û������ĳ�ʼ����
	 */
	@RequestMapping(value = "/editUser.htm")
	public String editUser(ModelMap model, UserForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isNotBlank(frm.getId())) {
			this.userManager.editUser(frm);
			model.put("roleId", frm.getRole());
			model.put("groupId", frm.getGroup());
			model.addAttribute("title", "�༭�û�");
		} else {
			model.addAttribute("title", "�½��û�");
		}
		return "sys/user/editUser";
	}
	@RequestMapping(value = "/previewImage.htm")
	public String viewPreviewImage(ModelMap model,String id) {
		model.addAttribute("id", id);
		return "sys/user/previewUserImage";
	}
	@RequestMapping(value = "/viewUserImage.json")
	public void previewImage(String id, HttpServletResponse res) {
		userManager.previewImage(id, res);
	}
	
	/**
	 * �޸�����
	 * 
	 * @param id
	 *            �û�Id
	 */
	@RequestMapping(value = "/editPas.htm")
	public String editPas(ModelMap model, UserForm frm, String title) {
		model.addAttribute("id", CtxUtil.getCurUser().getId());
		model.addAttribute("title", title);
		return "sys/user/editPas";
	}

	/**
	 * ��������
	 * 
	 * @param id
	 *            �û�Id
	 */
	@RequestMapping(value = "/savePas.json", produces = "application/json")
	public void savePas(ModelMap model, UserForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			String msg = userManager.savePas(frm);
			JsonResultUtil.suncess(model, msg);
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	@RequestMapping(value = "userTree.json")
	@ResponseBody
	public List<ComboboxTree> userJson (ModelMap model,  String areaid){
		
		if(!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
			areaid=CtxUtil.getCurUser().getAreaId();
		}
		return userManager.queryUserComboTree(areaid);
	}

	/**
	 * ��������
	 * 
	 * @param id
	 *            �û�Id
	 */
	@RequestMapping(value = "/resetPas.json", produces = "application/json")
	public void resetPas(ModelMap model, String id) {
		try {
			userManager.resetPas(id);
			JsonResultUtil.suncess(model, "��������ɹ�");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * �û�ְλ
	 */
	@RequestMapping(value = "/userPosition.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> userPosition() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("�û�ְλ:" + UserPosition.getTypes());
			}
			return UserPosition.getTypes();
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}

}