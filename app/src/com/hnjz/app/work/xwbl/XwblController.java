/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.xwbl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 询问笔录的Controller
 * 作者：xb
 * 生成日期：Mar 12, 2015
 * 功能描述：

 *
 */
@Controller
public class XwblController extends AbsJbpmController {
	
	@Autowired
    private XwblManager    xwblManager;
	
	/**
     * 询问笔录
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/xwbl.htm")
    public String xwbl(String applyId, String wflx, ModelMap model, XwblForm xwblForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            xwblForm=xwblManager.getXwblFormData(applyId,wflx);
            
            model.addAttribute("wflx", wflx);
            //追加问题列表
            ArrayList<HashMap<String, String>> morekcxwblwtlist = xwblManager.getMoreKcxwblWtList(applyId, "1");
            
            if (morekcxwblwtlist.size() == 0){
            	morekcxwblwtlist = xwblManager.getBlwt(applyId, wflx);
            }
            model.addAttribute("kcxwblwtlist", morekcxwblwtlist);
            
            String title = "法定代表人（负责人）：";
            if (work.getZfdxType().equals("06")){
            	title = "经营者";
            }
            model.addAttribute("title", title);

            model.addAttribute("xwblForm", xwblForm);
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
        return "app/work/xwbl/xwbl";
    }
    
    /**
     * 保存询问笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveXwbl.json", produces = "application/json")
    public void saveXwbl(String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel, ModelMap model,XwblForm xwblForm) {
    	try {
    		//保存询问笔录及问题项
    		xwblManager.saveXwbl(xwblForm, applyId, wflx, ids, content, danr, wttype, isdel);
    		//生成询问笔录doc文件
    		xwblManager.saveShengchengXwbl(applyId, wflx);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 修改问题
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/changeWT.htm")
    public String changeWT(String applyId,String wtid,String wtcontent, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            
            model.addAttribute("wtid", wtid);
            model.addAttribute("wtcontent", wtcontent);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/xwbl/changeWT";
    }
    
    /**
     * 保存修改问题
     * @param model
     */
    @RequestMapping(value = "/saveWT.json", produces = "application/json")
    public void saveWT(String applyId,String wtid,String wtcontent, ModelMap model) {
        try {
        	String id=xwblManager.saveWT(applyId,wtid,wtcontent);
        	
        	model.put("id", id);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    

}
