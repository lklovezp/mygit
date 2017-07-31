/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.jttlbl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 案件集体讨论笔录的Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
@Controller
public class JttlblController extends AbsJbpmController {
	
	@Autowired
    private JttlblManager    jttlblManager;
	
	/**
     * 案件集体讨论笔录
     * @param applyId 对应的违法案件调查任务Id
     * @param model
     */
    @RequestMapping(value = "/zzjttlbl.htm")
    public String kcbl(String applyId,String wflx, ModelMap model,JttlblForm jttlblForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            jttlblForm=jttlblManager.getJttlblForm(applyId);
            model.addAttribute("jttlblForm", jttlblForm);
            model.addAttribute("wflx", wflx);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/jttlbl/jttlbl";
    }
    
    /**
     * 制作案件集体讨论笔录附件
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveAjtlbl.json", produces = "application/json")
    public void saveAjtlbl(String applyId, String wflx, ModelMap model,JttlblForm jttlblForm) {
    	try {
    		//保存勘察笔录及问题项
    		jttlblManager.saveJttlbl(jttlblForm,applyId,wflx);
    		//生成询问笔录doc文件
    		jttlblManager.saveShengchengXwbl(applyId, wflx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}
