/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.sxgzs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 事先告知书的Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
@Controller
public class SxgzsController extends AbsJbpmController {
	
	@Autowired
    private SxgzsManager    sxgzsManager;
	
	/**
     * 事先告知书
     * @param applyId 对应的违法案件调查任务Id
     * @param model
     */
    @RequestMapping(value = "/zzsxgzs.htm")
    public String sxgzs(String applyId, ModelMap model,SxgzsForm sxgzsForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            sxgzsForm=sxgzsManager.getSxgzsFormData(applyId);
            model.addAttribute("sxgzsForm", sxgzsForm);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/sxgzs/sxgzs";
    }
    
    /**
     * 制作事先告知书附件
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveSxgzs.json", produces = "application/json")
    public void saveSxgzs(String applyId, ModelMap model,SxgzsForm sxgzsForm) {
    	try {
    		//保存勘察笔录及问题项
    		sxgzsManager.saveSxgzs(sxgzsForm,applyId);
    		//生成询问笔录doc文件
    		sxgzsManager.saveShengchengSxgzs(applyId);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}
