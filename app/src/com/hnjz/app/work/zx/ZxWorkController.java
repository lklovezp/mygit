/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.zx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 专项任务执行的Controller
 * 作者：xb
 * 生成日期：Mar 12, 2015
 * 功能描述：

 *
 */
@Controller
public class ZxWorkController extends AbsJbpmController {
	
	@Autowired
    private ZxWorkManager    zxWorkManager;
	
	@Autowired
    private RwglManager    rwglManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
    
    /**
     * 专项任务的执行界面-step2：办理
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxworkzxBL.htm")
    public String zxworkzxBL(String applyId, String taskId, ModelMap model,BlZxxdForm blZxxdForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            
            blZxxdForm=zxWorkManager.getBlZxxdFormData(applyId);
            
            model.addAttribute("blZxxdForm", blZxxdForm);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/zx/zxworkzxBL";
    }
    
    /**
     * 保存办理
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxWorkzxBL.json")
    public void saveZxWorkzxBL(String applyId,ModelMap model,BlZxxdForm blZxxdForm){
    	try {
    		zxWorkManager.saveZxWorkzxBL(applyId,blZxxdForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * 函数介绍：专项子任务table（办理）
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/zxZfdxTable.json", produces = "application/json")
    public void zxZfdxTable(String applyId,ModelMap model) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, String>> listMap = zxWorkManager.zxZfdxTableData(applyId);
            map.put("total", listMap.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 专项任务分工页面
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxrwfg.htm")
    public String zxrwfg(String applyId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            
            List<Map<String, String>> listMap = zxWorkManager.zxZfdxTableData_wfp(applyId);
            model.addAttribute("listMap", listMap);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/zx/zxrwfg";
    }
    
    /**
     * 分派任务
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxrwfg.json")
    public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx,ModelMap model){
    	try {
    		zxWorkManager.saveZxrwfg(applyId,zfdxid,zbry,yqwcsx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存分派任务错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 专项子任务的执行界面：办理
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxzrw_zxPage.htm")
    public String zxzrw_zxPage(String applyId, String taskId, ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm,String sy) {
        try {
            Work work = workManager.get(applyId);
            
            if(null == work.getZxStartTime()){
                work.setZxStartTime(new Date());
                //work.setState(WorkState.BLZ.getCode());//开始办理状态改为“办理中”
                this.manager.save(work);
                //保存“办理”流转记录
                Date date = new Date();
                zxWorkManager.saveLog(CtxUtil.getCurUser(), date, WorkLogType.ZX, WorkState.BLZ, work, date);//办理
            }
            
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            model.addAttribute("sy", sy);
            //检查人列表
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
            blZxxdZrwMainForm=zxWorkManager.getBlZxxdZrwMainFormData(applyId);
            
            model.addAttribute("blZxxdZrwMainForm", blZxxdZrwMainForm);
            //检查人列表
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/zx/zxzrw_zxPage";
    }
    
    /**
     * 保存办理
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPage.json")
    public void saveZxzrw_zxPage(String applyId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 办理完毕
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPageBlwb.json")
    public void saveZxzrw_zxPageBlwb(String applyId,String taskId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
    		zxWorkManager.saveZxzrw_zxPageBlwb(applyId,taskId);
    		
    		//专项子任务办理完毕附件打个zip包，分别保存两份到子任务、父任务附件中去。
    		zxWorkManager.saveZxzrw_zip(applyId);
    		
    		//若附件列表中有处理意见书则自动派发后督察任务到主办人员的待办列表中
            rwglManager.saveHdcTask(applyId);
    		
			JsonResultUtil.suncess(model, "任务办理完毕！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 专项行动任务的报告界面-step3：报告
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxworkzxBG.htm")
    public String zxworkzxBG(String applyId, String taskId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/zx/zxworkzxBG";
    }
    
    /**
     * 验证"办理"【true、通过；false、不通过】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/checkZxBlBL.json", produces = "application/json")
    public void checkZxBlBL(String applyId, ModelMap model) {
        try {
            ResultBean rb = zxWorkManager.checkZxBlBL(applyId);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 验证子任务"办理"【true、通过；false、不通过】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/checkZxZrwBlBL.json", produces = "application/json")
    public void checkZxZrwBlBL(String applyId, ModelMap model) {
        try {
            ResultBean rb = zxWorkManager.checkZxZrwBlBL(applyId);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }

}
