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
 * �������������᰸��������Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
@Controller
public class JaspbController extends AbsJbpmController {
	
	@Autowired
    private JaspbManager    jaspbManager;
	
	/**
     * �������������᰸������
     * @param applyId ��Ӧ��Υ��������������Id
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
     * �����������������᰸�������ĸ���
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveJaspb.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,JaspbForm jaspbForm) {
    	try {
    		//���濱���¼��������
    		jaspbManager.saveSxgzs(jaspbForm,applyId,wflx);
    		//����ѯ�ʱ�¼doc�ļ�
    		jaspbManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}