package com.hnjz.app.work.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.enums.WorkSh;
import com.hnjz.app.work.manager.WorkReportManager;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.app.work.worktype.WorkTypeManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 上报操作
 * @author zn
 * @version $Id: WorkReportController.java, v 0.1 2013-4-3 上午01:38:21 zn Exp $
 */
@Controller
public class WorkReportController extends AbsJbpmController {

    @Autowired
    private WorkTypeManager   workTypeManager;
    @Autowired
    private WorkReportManager workReportManager;
    
    @Autowired
    private RwglManager    rwglManager;

    @Value("#{settings['EXECUTE_PAGE_TYPE']}")
    private String          executePageType; //执行界面类型 0老界面1新界面
    /**
     * 进入归档页面
     * @param applyId
     * @param taskId
     * @param model
     *//*
    @RequestMapping(value = "/report_gdPage.htm")
    public void report_gdPage(String applyId, String taskId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            WorkType type = workTypeManager.getWorkType(work.getWorkType().getId());
            StringBuilder url = new StringBuilder();
            if(StringUtils.equals(executePageType, "0")){
                url.append(type.getUrl());
            }else{
                url.append(type.getUrl2());
            }
            url.append("?applyId=").append(applyId);
            url.append("&taskId=").append(taskId);
            url.append("&oper=v");
            model.addAttribute("url", url);
        } catch (Exception e) {
            log.error("", e);
        }
    }*/

    /**
     * 归档
     * @param applyId
     * @param taskId
     * @param model
     */
    @RequestMapping(value = "/report_gd.json", produces = "application/json")
    public void report_gd(String applyId, String taskId, ModelMap model) {
        try {
            workReportManager.saveGd(applyId, taskId);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 退回
     * @param applyId
     * @param taskId
     * @param model
     */
    @RequestMapping(value = "/report_th.json", produces = "application/json")
    public void report_th(String applyId, String taskId, String opinion, ModelMap model) {
        try {
            workReportManager.saveTh(applyId, taskId,opinion);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

    /**
     * 上报任务的审核页面
     * 函数介绍：
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/report_shPage.htm")
    public String report_shPage(String applyId, String taskId, ModelMap model,String lx,String sy) {
        try {
        	Work work = workManager.get(applyId);
            List<WorkSh> acs = workManager.getShActions(applyId);
            model.addAttribute("acs", acs);
            model.addAttribute("work", work);
            model.addAttribute("applyId", applyId);
            model.addAttribute("taskId", taskId);
            model.addAttribute("lx", lx);
            model.addAttribute("sy", sy);
            model.addAttribute("curUser", CtxUtil.getCurUser());
            model.addAttribute("curDate", new Date());
            //显示信息
            Map<String, Object> shInfo=rwglManager.getShInfo(applyId);
            model.addAttribute("shInfo", shInfo);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/report/report_shPage";
    }
    
    /**
     * 上报任务的审核操作
     * @param applyId
     * @param taskId
     * @param passed
     * @param model
     */
    @RequestMapping(value = "/report_sh.json", produces = "application/json")
    public void report_sh(String applyId, String taskId, Boolean passed, String opinion, ModelMap model) {
        try {
        	workReportManager.saveReportSh(applyId, taskId, passed, opinion);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
}
