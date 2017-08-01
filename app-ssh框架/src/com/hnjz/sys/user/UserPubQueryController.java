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
 * 用户公共选择
 * 
 * @author wumi
 * @version $Id: UserPubQueryController.java, v 0.1 Jan 28, 2013 8:48:36 AM wumi
 *          Exp $
 */
@Controller
public class UserPubQueryController {
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(UserPubQueryController.class);

	@Autowired
	private UserPubQueryManager userPubQueryManager;

	/**
	 * 用户公共选择
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            用户已经选过的数据的id
	 * @param oper
	 *            操作（s-单选，m-多选）
	 * @param all
	 *            true显示所有用户(办公室可以选择所有人员)；为空或其他不是true的字符串 显示自己部门及下级部门的用户
	 * @param notShowZj
	 *            true不显示自己；为空或其他不是true的字符串 把自己显示出来
	 * @return 用户公共选择页面
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
			log.error("查询错误：", e);
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
			//1是按角色只选择信访专员，2是日常专员，其他如需再加，在页面添加group加上不同标识即可
			model.addAttribute("menu", re.toString());
			model.addAttribute("ids", id);
			model.addAttribute("methodname", methodname);
			model.addAttribute("multi", multi);
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return "common/taskUserPubQuery";
	}
}
