/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

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

import com.hnjz.common.util.StringUtil;

/**
 * �û�����ѡ��
 * 
 * @author wumi
 * @version $Id: UserPubQueryController.java, v 0.1 Jan 28, 2013 8:48:36 AM wumi
 *          Exp $
 */
@Controller
public class UserPubQueryController {
	/** ��־ */
	private static final Log log = LogFactory
			.getLog(UserPubQueryController.class);

	@Autowired
	private UserPubQueryManager userPubQueryManager;

	/**
	 * �û�����ѡ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            �û��Ѿ�ѡ�������ݵ�id
	 * @param oper
	 *            ������s-��ѡ��m-��ѡ��
	 * @param all
	 *            true��ʾ�����û�(�칫�ҿ���ѡ��������Ա)��Ϊ�ջ���������true���ַ��� ��ʾ�Լ����ż��¼����ŵ��û�
	 * @param notShowZj
	 *            true����ʾ�Լ���Ϊ�ջ���������true���ַ��� ���Լ���ʾ����
	 * @return �û�����ѡ��ҳ��
	 */
	@RequestMapping(value = "/userPubQuery.htm")
	public String userPubQuery(ModelMap model, String id, String oper,
			String all, String notShowZj, String notShowSys) {
		try {
			String[] ids = null;
			if(StringUtils.isNotBlank(id)){
				ids = id.split(",");
			}
			JSONArray re = userPubQueryManager.queryUser(ids, all, notShowZj, notShowSys);
			model.addAttribute("menu", re.toString());
			model.addAttribute("ids", id);
			model.addAttribute("oper", oper);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return "common/treePubQuery";
	}

	@RequestMapping(value = "/taskUserPubQuery.htm")
	public String taskUserPubQuery(ModelMap model, String all,String areaid,String displayAll, String showBj, String id, String notShowZj, String methodname, String multi,String showExist,String group, String condition) {
		try {
			List<String> ids = new ArrayList<String>();
			if (StringUtil.isNotBlank(id)){
				for (int i = 0; i < id.split(",").length; i++) {
					ids.add(id.split(",")[i]);
				}
			}
			JSONArray re = new JSONArray();			
			//1�ǰ���ɫֻѡ���ŷ�רԱ��2���ճ�רԱ�����������ټӣ���ҳ������group���ϲ�ͬ��ʶ����
			if("1".equals(group)){
				re = userPubQueryManager.taskUserPubQueryByRole(all,areaid,displayAll, showBj, ids, notShowZj,showExist,group);
			}else if("2".equals(group)){
				re = userPubQueryManager.taskUserPubQueryByRole(all,areaid,displayAll, showBj, ids, notShowZj,showExist,group);
			}else if("3".equals(group)){
				re = userPubQueryManager.taskUserPubQueryByRoleWithout(all,areaid,displayAll, showBj, ids, notShowZj,showExist,group);
			}else if("4".equals(group)){
				re = userPubQueryManager.taskUserPubQueryByRoleWithout(all,areaid,displayAll, showBj, ids, notShowZj,showExist,group);
			}else if("5".equals(group)){
				re = userPubQueryManager.ZdtaskUserPubQueryByRoleWithout(all,areaid,displayAll, showBj, ids, notShowZj,showExist,group);
			}else{
				re = userPubQueryManager.taskUserPubQuery(all,areaid,displayAll, showBj, ids, notShowZj,showExist,condition);
			}
			model.addAttribute("menu", re.toString());
			model.addAttribute("ids", id);
			model.addAttribute("methodname", methodname);
			model.addAttribute("multi", multi);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return "common/taskUserPubQuery";
	}
}