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
 * ѯ�ʱ�¼��Controller
 * ���ߣ�xb
 * �������ڣ�Mar 12, 2015
 * ����������

 *
 */
@Controller
public class XwblController extends AbsJbpmController {
	
	@Autowired
    private XwblManager    xwblManager;
	
	/**
     * ѯ�ʱ�¼
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/xwbl.htm")
    public String xwbl(String applyId, String wflx, ModelMap model, XwblForm xwblForm) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("work", work);
            xwblForm=xwblManager.getXwblFormData(applyId,wflx);
            
            model.addAttribute("wflx", wflx);
            //׷�������б�
            ArrayList<HashMap<String, String>> morekcxwblwtlist = xwblManager.getMoreKcxwblWtList(applyId, "1");
            
            if (morekcxwblwtlist.size() == 0){
            	morekcxwblwtlist = xwblManager.getBlwt(applyId, wflx);
            }
            model.addAttribute("kcxwblwtlist", morekcxwblwtlist);
            
            String title = "���������ˣ������ˣ���";
            if (work.getZfdxType().equals("06")){
            	title = "��Ӫ��";
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
     * ����ѯ�ʱ�¼
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveXwbl.json", produces = "application/json")
    public void saveXwbl(String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel, ModelMap model,XwblForm xwblForm) {
    	try {
    		//����ѯ�ʱ�¼��������
    		xwblManager.saveXwbl(xwblForm, applyId, wflx, ids, content, danr, wttype, isdel);
    		//����ѯ�ʱ�¼doc�ļ�
    		xwblManager.saveShengchengXwbl(applyId, wflx);
    		
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * �޸�����
     * 
     * @param applyId ����Id
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
     * �����޸�����
     * @param model
     */
    @RequestMapping(value = "/saveWT.json", produces = "application/json")
    public void saveWT(String applyId,String wtid,String wtcontent, ModelMap model) {
        try {
        	String id=xwblManager.saveWT(applyId,wtid,wtcontent);
        	
        	model.put("id", id);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    

}