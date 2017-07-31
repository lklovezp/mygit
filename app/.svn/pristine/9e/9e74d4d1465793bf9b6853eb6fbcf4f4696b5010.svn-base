/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.kcbl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.xwbl.XwblManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 勘察笔录的Controller
 * 作者：xb
 * 生成日期：Mar 12, 2015
 * 功能描述：

 *
 */
@Controller
public class KcblController extends AbsJbpmController {
	
	@Autowired
    private KcblManager    kcblManager;
	
	@Autowired
    private XwblManager    xwblManager;
	/**
     * 勘察笔录
     * 
     * @param applyId 任务Id
     * @param model
     */
    /*@RequestMapping(value = "/kcbl.htm")
    public String kcbl(String applyId,String wflx, ModelMap model,KcblForm kcblForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            kcblForm=kcblManager.getKcblFormData(applyId);
            model.addAttribute("kcblForm", kcblForm);
            
            model.addAttribute("wflx", wflx);
            
            List kcxwblwtlist = kcblManager.getAllKcxwblWtList(applyId,"0");
            if(kcxwblwtlist.size() == 0){
            	//所有系统配置的问题列表
            	kcxwblwtlist = kcblManager.getKcxwblWtList(applyId,TaskTypeCode.WFAJ.getCode(), wflx, "0");
            }
            
            //所有新增问题
            List morekcxwblwtlist = kcblManager.getMoreKcxwblWtList(applyId, "0");
            
            model.addAttribute("kcxwblwtlist", kcxwblwtlist);
            model.addAttribute("morekcxwblwtlist", morekcxwblwtlist);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/kcbl/kcbl";
    }*/
	
	/**
     * 勘察笔录
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/kcbl.htm")
    public String kcbl(String applyId, String wflx, ModelMap model, KcblForm kcblForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            kcblForm=kcblManager.getKcblFormData(applyId);
            
            model.addAttribute("wflx", wflx);
            //追加问题列表
            ArrayList<HashMap<String, String>> morekcxwblwtlist = xwblManager.getMoreKcxwblWtList(applyId, "0");
            
            if (morekcxwblwtlist.size() == 0){
            	morekcxwblwtlist = kcblManager.getBlwt(applyId, wflx);
            }
            model.addAttribute("kcxwblwtlist", morekcxwblwtlist);
            
            model.addAttribute("kcblForm", kcblForm);
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
        return "app/work/kcbl/kcbl";
    }
    
    /**
     * 保存勘察笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveKcbl.json", produces = "application/json")
    public void saveXwbl(String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel, ModelMap model,KcblForm kcblForm) {
    	try {
    		//保存询问笔录及问题项
    		kcblManager.saveKcbl(kcblForm, applyId, wflx, ids, content, danr, wttype, isdel);
    		//生成询问笔录doc文件
    		kcblManager.saveShengchengKcbl(applyId, wflx);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 删除勘察笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/delRecord.json", produces = "application/json")
    public void delKcbl(String applyId, String wflx,ModelMap model) {
    	try {
    		
    		String wflxs = kcblManager.getWflx(applyId);
    		
    		if(StringUtils.isNotBlank(wflxs) && StringUtils.isNotBlank(wflx)){
    			//比较该任务的违法案件类型及前端页面变化后的违法案件类型，若不相同，删除勘察询问笔录问题记录
    			if(!wflxs.contains(wflx)){
    				//删除勘察询问笔录问题记录
        			kcblManager.delKcbl(applyId);
    			}
    		}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * 获取执法证号
     * @param model
     */
    @RequestMapping(value = "/getLwnumber.json", produces = "application/json")
    public void getLwnumber(String userIds,ModelMap model) {
    	try {
            String lwnumbers = kcblManager.getLwnumber(userIds);
            model.put("lwnumbers", lwnumbers);
		} catch (Exception e) {
			log.error("查询执法证号错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    

}
