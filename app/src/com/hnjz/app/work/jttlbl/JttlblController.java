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
 * �����������۱�¼��Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
@Controller
public class JttlblController extends AbsJbpmController {
	
	@Autowired
    private JttlblManager    jttlblManager;
	
	/**
     * �����������۱�¼
     * @param applyId ��Ӧ��Υ��������������Id
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
     * ���������������۱�¼����
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveAjtlbl.json", produces = "application/json")
    public void saveAjtlbl(String applyId, String wflx, ModelMap model,JttlblForm jttlblForm) {
    	try {
    		//���濱���¼��������
    		jttlblManager.saveJttlbl(jttlblForm,applyId,wflx);
    		//����ѯ�ʱ�¼doc�ļ�
    		jttlblManager.saveShengchengXwbl(applyId, wflx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }    

}