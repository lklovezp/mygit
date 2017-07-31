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
 * ר������ִ�е�Controller
 * ���ߣ�xb
 * �������ڣ�Mar 12, 2015
 * ����������

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
     * ר�������ִ�н���-step2������
     * 
     * @param applyId ����Id
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
     * �������
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxWorkzxBL.json")
    public void saveZxWorkzxBL(String applyId,ModelMap model,BlZxxdForm blZxxdForm){
    	try {
    		zxWorkManager.saveZxWorkzxBL(applyId,blZxxdForm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���������Ϣ����",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * �������ܣ�ר��������table��������
    
     * ���������
    
     * ����ֵ��
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
     * ר������ֹ�ҳ��
     * 
     * @param applyId ����Id
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
     * ��������
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxrwfg.json")
    public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx,ModelMap model){
    	try {
    		zxWorkManager.saveZxrwfg(applyId,zfdxid,zbry,yqwcsx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������������",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * ר���������ִ�н��棺����
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/zxzrw_zxPage.htm")
    public String zxzrw_zxPage(String applyId, String taskId, ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm,String sy) {
        try {
            Work work = workManager.get(applyId);
            
            if(null == work.getZxStartTime()){
                work.setZxStartTime(new Date());
                //work.setState(WorkState.BLZ.getCode());//��ʼ����״̬��Ϊ�������С�
                this.manager.save(work);
                //���桰��������ת��¼
                Date date = new Date();
                zxWorkManager.saveLog(CtxUtil.getCurUser(), date, WorkLogType.ZX, WorkState.BLZ, work, date);//����
            }
            
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            model.addAttribute("sy", sy);
            //������б�
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
            blZxxdZrwMainForm=zxWorkManager.getBlZxxdZrwMainFormData(applyId);
            
            model.addAttribute("blZxxdZrwMainForm", blZxxdZrwMainForm);
            //������б�
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/zx/zxzrw_zxPage";
    }
    
    /**
     * �������
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPage.json")
    public void saveZxzrw_zxPage(String applyId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���������Ϣ����",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * �������
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPageBlwb.json")
    public void saveZxzrw_zxPageBlwb(String applyId,String taskId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
    		zxWorkManager.saveZxzrw_zxPageBlwb(applyId,taskId);
    		
    		//ר�������������ϸ������zip�����ֱ𱣴����ݵ������񡢸����񸽼���ȥ��
    		zxWorkManager.saveZxzrw_zip(applyId);
    		
    		//�������б����д�����������Զ��ɷ��󶽲�����������Ա�Ĵ����б���
            rwglManager.saveHdcTask(applyId);
    		
			JsonResultUtil.suncess(model, "���������ϣ�");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���������Ϣ����",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * ר���ж�����ı������-step3������
     * 
     * @param applyId ����Id
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
     * ��֤"����"��true��ͨ����false����ͨ����
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
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * ��֤������"����"��true��ͨ����false����ͨ����
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
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }

}