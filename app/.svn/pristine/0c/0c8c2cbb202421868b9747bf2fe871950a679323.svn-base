/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.jaspb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 行政处罚案件结案审批表的Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
@Controller
public class JaspbController extends AbsJbpmController {
	
	@Autowired
    private JaspbManager    jaspbManager;
	
	/**
     * 行政处罚案件结案审批表
     * @param applyId 对应的违法案件调查任务Id
     * @param model
     */
    @RequestMapping(value = "/zzjaspb.htm")
    public String sxgzs(String applyId,String wflx, ModelMap model,JaspbForm jaspbForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            jaspbForm=jaspbManager.getSxgzsFormData(applyId);
            model.addAttribute("jaspbForm", jaspbForm);
            model.addAttribute("wflx", wflx);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/jaspb/jaspb";
    }
    
    /**
     * 制作行政处罚案件结案审批表的附件
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveJaspb.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,JaspbForm jaspbForm) {
    	try {
    		//保存勘察笔录及问题项
    		jaspbManager.saveSxgzs(jaspbForm,applyId,wflx);
    		//生成询问笔录doc文件
    		jaspbManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}
