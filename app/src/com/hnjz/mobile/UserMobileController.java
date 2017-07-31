/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;

/**
 * �û�������Controller
 * 
 * @author zhangqingfeng
 * @version $Id: UserController.java, v 0.1 2016-03-14
 */
@Controller
public class UserMobileController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(UserMobileController.class);

	@Autowired
	private UserManager userManager;
	
	/**
	 * �ֻ����û���ȡ������Ϣ
	 * @param userId ��¼�û���id
	 */
	@RequestMapping(value = "/getOrgbyUser.mo", produces = "application/json")
	public void getOrgbyUser(ModelMap model,String userId) {
		try{
			TSysOrg org = userManager.getOrgbyUser(userId);
			model.addAttribute("orgId", org.getId());//��ʱ�ȴ�����IId������Ҫ�ӵĶ��ˣ�����ȫ��װ��������
		} catch (Exception e){
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

}