/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.sxgzs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * ���ȸ�֪���Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
@Controller
public class SxgzsController extends AbsJbpmController {
	
	@Autowired
    private SxgzsManager    sxgzsManager;
	
	/**
     * ���ȸ�֪��
     * @param applyId ��Ӧ��Υ��������������Id
     * @param model
     */
    @RequestMapping(value = "/zzsxgzs.htm")
    public String sxgzs(String applyId, ModelMap model,SxgzsForm sxgzsForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            sxgzsForm=sxgzsManager.getSxgzsFormData(applyId);
            model.addAttribute("sxgzsForm", sxgzsForm);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/sxgzs/sxgzs";
    }
    
    /**
     * �������ȸ�֪�鸽��
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveSxgzs.json", produces = "application/json")
    public void saveSxgzs(String applyId, ModelMap model,SxgzsForm sxgzsForm) {
    	try {
    		//���濱���¼��������
    		sxgzsManager.saveSxgzs(sxgzsForm,applyId);
    		//����ѯ�ʱ�¼doc�ļ�
    		sxgzsManager.saveShengchengSxgzs(applyId);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}