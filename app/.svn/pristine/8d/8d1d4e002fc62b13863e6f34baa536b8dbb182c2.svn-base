/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.cfjds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 行政处罚决定书的Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
@Controller
public class CfjdsController extends AbsJbpmController {
	
	@Autowired
    private CfjdsManager    cfjdsManager;
	
	/**
     * 行政处罚决定书
     * @param applyId 对应的违法案件调查任务Id
     * @param model
     */
    @RequestMapping(value = "/zzcfjds.htm")
    public String sxgzs(String applyId,String wflx, ModelMap model,CfjdsForm cfjdsForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            cfjdsForm=cfjdsManager.getCfjdsFormData(applyId);
            model.addAttribute("cfjdsForm", cfjdsForm);
            model.addAttribute("wflx", wflx);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/cfjds/cfjds";
    }
    
    /**
     * 制作事先告知书附件
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveCfjds.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,CfjdsForm cfjdsForm) {
    	try {
    		//保存勘察笔录及问题项
    		cfjdsManager.saveSxgzs(cfjdsForm,applyId,wflx);
    		//生成询问笔录doc文件
    		cfjdsManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}
