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
 * �ʹ��֤��Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
@Controller
public class SdhzController extends AbsJbpmController {
	
	@Autowired
    private SdhzManager    sdhzManager;
	
	/**
     * �ʹ��֤
     * @param applyId ��Ӧ��Υ��������������Id
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
     * �����ʹ��֤����
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveSdhz.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,SdhzForm sdhzForm) {
    	try {
    		//���濱���¼��������
    		sdhzManager.saveSxgzs(sdhzForm,applyId,wflx);
    		//����ѯ�ʱ�¼doc�ļ�
    		sdhzManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}