/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.xzcf;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.role.RoleController;
import com.hnjz.wf.AbsJbpmController;

/**
 * 行政处罚Controller
 * 作者：zhangqingfeng
 * 生成日期：Mar 16, 2015
 * 功能描述：
 *
 */
@Controller
public class XzcfController extends AbsJbpmController{

    /**日志*/
    private static final Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private XzcfManager    xczfManager;
    
   /**
     * 
     * 函数介绍：跳转到：行政处罚列表页面。
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/xzcfList.htm")
    public String xzcfList(ModelMap model,String title) {
    	model.put("title", title);
        return "app/work/xzcf/xzcfList";
    }
    
    /**
     * 
     * 函数介绍：查询：行政处罚列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getXzcfList.json", produces = "application/json")
    public void getXzcfList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String lawobjId, String page, String pageSize) {
        try {
        	//添加离线判断标识，进行分类查询（存的时候加入0,1的存值）
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = xczfManager.getXzcfList(rwmc,rwly,pfrId, rwzt, tasktype, lawobjId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 进入行政处罚页面
     * @param applyId   任务Id
     * @param model
     */
    @RequestMapping(value = "/xzcfbl.htm")
    public String xzcfbl(String applyId,ModelMap model) {
        try {
        	Work work = null;
        	//派发
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//接受人
        		if(StringUtils.isNotBlank(work.getJsr())){
        			TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getJsr());
        			work.setJsrNames(jsrObj.getName());
        		}else if(StringUtils.isNotBlank(work.getJsrIds())){
        			String [] jsrIds = work.getJsrIds().split(",");
        			String jsrNames = "";
        			for(String jsr : jsrIds){
        				TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, jsr);
        				jsrNames = jsrNames+jsrObj.getName() + ",";
        			}
        			jsrNames = jsrNames.substring(0,jsrNames.length()-1);
        			work.setJsrNames(jsrNames);
        		}else{
        			work.setJsrNames("");
        		}
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/xzcf/xzcfBL";
    }
    
    /**
     * 
     * 函数介绍：跳转到行政处罚详情页面
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/xxcx_xzcfInfo.htm")
    public String xxcx_xzcfInfo(ModelMap model,String lawobjid){
    	model.put("lawobjid", lawobjid);
    	return "app/work/xzcf/ZfdxxzcfList";
    }
    
    /**
     * 进入行政处罚页面
     * @param applyId   任务Id
     * @param model
     */
    @RequestMapping(value = "/xzcfblInfo.htm")
    public String xzcfblInfo(String applyId,String lawobjId, ModelMap model) {
        try {
        	Work work = null;
        	//派发
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//接受人
        		if(StringUtils.isNotBlank(work.getJsr())){
        			TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getJsr());
        			work.setJsrNames(jsrObj.getName());
        		}else if(StringUtils.isNotBlank(work.getJsrIds())){
        			String [] jsrIds = work.getJsrIds().split(",");
        			String jsrNames = "";
        			for(String jsr : jsrIds){
        				TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, jsr);
        				jsrNames = jsrNames+jsrObj.getName() + ",";
        			}
        			jsrNames = jsrNames.substring(0,jsrNames.length()-1);
        			work.setJsrNames(jsrNames);
        		}else{
        			work.setJsrNames("");
        		}
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/xzcf/xzcfBLInfo";
    }

}
