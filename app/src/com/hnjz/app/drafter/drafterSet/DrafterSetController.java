/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSet;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.drafter.po.TDataDrafterSet;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.user.UserPubQueryManager;

/**
 * 稿件审核人设置的Controller
 * @author shiqiuhan
 * @created 2015-12-24,上午08:57:41
 */
@Controller
public class DrafterSetController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(DrafterSetController.class);

	@Autowired
	private DrafterSetManager drafterSetManager;
	
	@Autowired
	private OrgManager orgManager;
	
	@Autowired
	private DicManager dicManager;
	
	@Autowired
	private UserPubQueryManager userPubQueryManager;
	
	/**
	 * 跳转到稿件审核人设置界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/drafterSetList.htm")
	public String drafterSet(ModelMap model, String title, String name) throws Exception {
		model.addAttribute("title", title);
		
		JSONArray orgs = orgManager.queryOrg(name, "Y");//部门树
		model.addAttribute("orgList", orgs.toString());
		
		List <Combobox> jbs= dicManager.queryDicDataByCode(DicTypeEnum.GJSHJB.getCode());//审核级别
		model.addAttribute("jbList", jbs);
		
		String [] ids = null;
		JSONArray shrs = userPubQueryManager.queryUser(ids, "true", "false", "true");//审核人
		model.addAttribute("shrList", shrs.toString());
		return "app/drafter/drafterSet";
	}

	/**
	 * 保存稿件审核人设置信息
	 * 
	 * @param frm
	 *            {@link drafterSetForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/drafterSetSave.json", produces = "application/json")
	public void drafterSetSave(String orgId,String jb,String shr, ModelMap model) {
		try {
			drafterSetManager.saveDrafterSet(orgId,jb,shr);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 根据部门和审核级别查询稿件审核人设置信息
	 * 
	 * @param frm
	 *            {@link drafterSetForm}
	 * @param model
	 *            {@link ModelMap}
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryShr.json", produces = "application/json")
	public void queryShr(String orgId,String jb,ModelMap model) throws Exception {
		try {
			String [] ids = null;
			List <TDataDrafterSet> sets = drafterSetManager.queryShr(orgId,jb);
			if(sets!=null && sets.size()>0){
				ids = new String[sets.size()+1];
				for(int i=0;i<sets.size();i++){
					ids[i] = sets.get(i).getAudit();
					
				}
			}
			JSONArray shrs = userPubQueryManager.queryUser(ids, "true", "false", "true");//审核人
			model.addAttribute("shrList", shrs.toString());
			JsonResultUtil.suncess(model, "刷新成功！");
		    
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
}
