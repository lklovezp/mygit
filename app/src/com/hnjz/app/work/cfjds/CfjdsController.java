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
 * ���������������Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
@Controller
public class CfjdsController extends AbsJbpmController {
	
	@Autowired
    private CfjdsManager    cfjdsManager;
	
	/**
     * ��������������
     * @param applyId ��Ӧ��Υ��������������Id
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
     * �������ȸ�֪�鸽��
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveCfjds.json", produces = "application/json")
    public void saveSxgzs(String applyId, String wflx, ModelMap model,CfjdsForm cfjdsForm) {
    	try {
    		//���濱���¼��������
    		cfjdsManager.saveSxgzs(cfjdsForm,applyId,wflx);
    		//����ѯ�ʱ�¼doc�ļ�
    		cfjdsManager.saveShengchengSxgzs(applyId, wflx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}