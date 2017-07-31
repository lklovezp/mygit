/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.sdhz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 送达回证的Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
@Controller
public class SdhzController extends AbsJbpmController {
	
	@Autowired
    private SdhzManager    sdhzManager;
	
	/**
     * 送达回证
     * @param applyId 对应的违法案件调查任务Id
     * @param model
     */
    @RequestMapping(value = "/zzsdhz.htm")
    public String sxgzs(String applyId,String wflx, ModelMap model,SdhzForm sdhzForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            sdhzForm=sdhzManager.getSxgzsFormData(applyId);
            model.addAttribute("sdhzForm", sdhzForm);
            model.addAttribute("wflx", wflx);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/sdhz/sdhz";
    }
    
    /**
     * 制作送达回证附件
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveSdhz.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,SdhzForm sdhzForm) {
    	try {
    		//保存勘察笔录及问题项
    		sdhzManager.saveSxgzs(sdhzForm,applyId,wflx);
    		//生成询问笔录doc文件
    		sdhzManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}
