/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.drafter.enums.AuditStateEnum;
import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;

/**
 * 稿件报送的Controller
 * @author shiqiuhan
 * @created 2015-12-24,上午08:57:41
 */
@Controller
public class DrafterController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(DrafterController.class);

	@Autowired
	private DrafterManager drafterManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/drafterList.htm")
	public String drafterList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/drafter/drafterList";
	}

	/**
	 * 查询稿件列表
	 * 
	 * @param frm
	 * 			  查询条件
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/drafterQuery.json", produces = "application/json")
	public void drafterQuery(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryDrafter(frm,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 跳转到查询稿件审核列表页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/auditDrafterList.htm")
	public String auditDrafterList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/drafter/auditDrafterList";
	}
	

	/**
	 * 查询稿件审核列表
	 * 
	 * @param frm
	 * 			  查询条件
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditDrafterQuery.json", produces = "application/json")
	public void auditDrafterQuery(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryAuditDrafter(frm,page,pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 跳转到统计结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/tjdrafter.htm")
	public String drafterStatistics(ModelMap model, String title, DrafterForm frm) {
		model.addAttribute("title", title);
		Date date=new Date();
		if(null==frm.getSubmitDate1()||frm.getSubmitDate1().equals("")){
			frm.setSubmitDate1(DateUtil.getDateTime("yyyy-MM",date)+"-01 ");
		}
		if(null==frm.getSubmitDate2()||frm.getSubmitDate2().equals("")){
			frm.setSubmitDate2(DateUtil.getDateTime("yyyy-MM-dd",date));
		}
		return "app/drafter/drafterStatistics";
	}
	
	/**
	 * 查询稿件统计列表
	 * 
	 * @param frm
	 * 			  查询条件
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @throws Exception
	 */
	@RequestMapping(value = "/drafterStatistics.json", produces = "application/json")
	public void drafterStatistics(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryDrafterStatistics(frm,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到稿件发送页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link drafterForm}
	 * @return 发送稿件页面
	 */
	@RequestMapping(value = "/drafterSend.htm")
	public String drafterAdd(ModelMap model, DrafterForm frm) {
		model.addAttribute("title", "发送稿件");
		if(StringUtils.isBlank(frm.getId())){	//新增
			frm.setDrafterId(CtxUtil.getUserId());//默认拟稿人为当前登录人
			frm.setDrafterName(CtxUtil.getCurUser().getName());
		}else{//编辑
			TBizDrafter drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class, frm.getId());
			frm.setId(drafter.getId());
			frm.setDrafterId(drafter.getDrafterId());
			frm.setDrafterName(drafter.getDrafterName());
			frm.setDescribe(drafter.getDescribe());
			frm.setName(drafter.getName());
		}
		return "app/drafter/drafterSend";
	}
	
	/**
	 * 发送稿件
	 * 
	 * @param frm
	 *            {@link drafterForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/drafterSave.json", produces = "application/json")
	public void drafterSave(DrafterForm frm,ModelMap model) {
		try {
			TBizDrafter drafter = drafterManager.saveDrafter(frm);
			model.put("id", drafter.getId());
			if(frm.getState()==0){
				JsonResultUtil.suncess(model, "稿件已保存！");
			}else{
				JsonResultUtil.suncess(model, "发送成功！");
			}
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 删除稿件
	 * 
	 * @param id
	 *            稿件信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delDrafter.json", produces = "application/json")
	public void delDrafter(String id, ModelMap model) {
		try {
			this.drafterManager.removeDrafter(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除稿件审核人设置信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
     * 进入稿件查看页面
     * @param id   稿件id
     * @param model
     */
    @RequestMapping(value = "/drafterInfo.htm")
    public String drafterInfo(String id,ModelMap model) {
        try {
        	TBizDrafter drafter = null;
        	//稿件
        	String state = null;
        	if(StringUtils.isNotBlank(id)){
        		drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class,id);
        		//稿件状态（因为是查看页面，直接用原字段了）
        		if(drafter.getState()!=null){
        			state=AuditStateEnum.getNameByCode(String.valueOf(drafter.getState()));
        		}
        	}
        	model.addAttribute("state", state);
        	model.addAttribute("drafter", drafter);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/drafter/drafterInfo";
    }
    
    /**
     * 进入稿件审批页面
     * @param id   稿件id
     * @param model
     */
    @RequestMapping(value = "/auditDrafter.htm")
    public String auditDrafter(String id,ModelMap model) {
        try {
        	TBizDrafter drafter = null;
        	//稿件
        	String state = null;
        	if(StringUtils.isNotBlank(id)){
        		drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class,id);
        		//稿件状态（因为是查看页面，直接用原字段了）
        		if(drafter.getState()!=null){
        			state=AuditStateEnum.getNameByCode(String.valueOf(drafter.getState()));
        		}
        	}
        	model.addAttribute("state", state);
        	model.addAttribute("drafter", drafter);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/drafter/drafterAudit";
    }
    
    /**
	 * 审批稿件
	 * 
	 * @param id
	 *            稿件信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/auditDrafter.json", produces = "application/json")
	public void audit(String id,String result,ModelMap model) {
		try {
			this.drafterManager.auditDrafter(id,result);
			JsonResultUtil.suncess(model, "审批完成！");
		} catch (AppException e) {
			log.debug("删除稿件审核人设置信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 
	 * 函数介绍：审批状态下拉列表
	 * 
	 */
	@RequestMapping(value = "/stateList.json")
	@ResponseBody
	public List<Combobox> stateList(ModelMap model) {
		return drafterManager.queryStateList();
	}
}
