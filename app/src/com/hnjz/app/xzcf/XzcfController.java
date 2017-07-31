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
 * ��������Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 16, 2015
 * ����������
 *
 */
@Controller
public class XzcfController extends AbsJbpmController{

    /**��־*/
    private static final Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private XzcfManager    xczfManager;
    
   /**
     * 
     * �������ܣ���ת�������������б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/xzcfList.htm")
    public String xzcfList(ModelMap model,String title) {
    	model.put("title", title);
        return "app/work/xzcf/xzcfList";
    }
    
    /**
     * 
     * �������ܣ���ѯ�����������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getXzcfList.json", produces = "application/json")
    public void getXzcfList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String lawobjId, String page, String pageSize) {
        try {
        	//���������жϱ�ʶ�����з����ѯ�����ʱ�����0,1�Ĵ�ֵ��
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = xczfManager.getXzcfList(rwmc,rwly,pfrId, rwzt, tasktype, lawobjId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * ������������ҳ��
     * @param applyId   ����Id
     * @param model
     */
    @RequestMapping(value = "/xzcfbl.htm")
    public String xzcfbl(String applyId,ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//������
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
     * �������ܣ���ת��������������ҳ��
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/xxcx_xzcfInfo.htm")
    public String xxcx_xzcfInfo(ModelMap model,String lawobjid){
    	model.put("lawobjid", lawobjid);
    	return "app/work/xzcf/ZfdxxzcfList";
    }
    
    /**
     * ������������ҳ��
     * @param applyId   ����Id
     * @param model
     */
    @RequestMapping(value = "/xzcfblInfo.htm")
    public String xzcfblInfo(String applyId,String lawobjId, ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//������
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