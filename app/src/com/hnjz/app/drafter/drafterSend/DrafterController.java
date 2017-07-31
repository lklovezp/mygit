/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.drafter.enums.AuditStateEnum;
import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;

/**
 * ������͵�Controller
 * @author shiqiuhan
 * @created 2015-12-24,����08:57:41
 */
@Controller
public class DrafterController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(DrafterController.class);

	@Autowired
	private DrafterManager drafterManager;

	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/drafterList.htm")
	public String drafterList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/drafter/drafterList";
	}

	/**
	 * ��ѯ����б�
	 * 
	 * @param frm
	 * 			  ��ѯ����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/drafterQuery.json", produces = "application/json")
	public void drafterQuery(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryDrafter(frm,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ת����ѯ�������б�ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/auditDrafterList.htm")
	public String auditDrafterList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/drafter/auditDrafterList";
	}
	

	/**
	 * ��ѯ�������б�
	 * 
	 * @param frm
	 * 			  ��ѯ����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditDrafterQuery.json", produces = "application/json")
	public void auditDrafterQuery(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryAuditDrafter(frm,page,pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ת��ͳ�ƽ��ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/tjdrafter.htm")
	public String drafterStatistics(ModelMap model, String title, DrafterForm frm) {
		model.addAttribute("title", title);
		Date date=new Date();
		if(null==frm.getSubmitDate1()||frm.getSubmitDate1().equals("")){
			frm.setSubmitDate1(DateUtil.getDateTime("yyyy-MM",date)+"-01 ");
		}
		if(null==frm.getSubmitDate2()||frm.getSubmitDate2().equals("")){
			frm.setSubmitDate2(DateUtil.getDateTime("yyyy-MM-dd",date));
		}
		return "app/drafter/drafterStatistics";
	}
	
	/**
	 * ��ѯ���ͳ���б�
	 * 
	 * @param frm
	 * 			  ��ѯ����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/drafterStatistics.json", produces = "application/json")
	public void drafterStatistics(ModelMap model, DrafterForm frm, String page, 
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = drafterManager.queryDrafterStatistics(frm,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ת���������ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link drafterForm}
	 * @return ���͸��ҳ��
	 */
	@RequestMapping(value = "/drafterSend.htm")
	public String drafterAdd(ModelMap model, DrafterForm frm) {
		model.addAttribute("title", "���͸��");
		if(StringUtils.isBlank(frm.getId())){	//����
			frm.setDrafterId(CtxUtil.getUserId());//Ĭ�������Ϊ��ǰ��¼��
			frm.setDrafterName(CtxUtil.getCurUser().getName());
		}else{//�༭
			TBizDrafter drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class, frm.getId());
			frm.setId(drafter.getId());
			frm.setDrafterId(drafter.getDrafterId());
			frm.setDrafterName(drafter.getDrafterName());
			frm.setDescribe(drafter.getDescribe());
			frm.setName(drafter.getName());
		}
		return "app/drafter/drafterSend";
	}
	
	/**
	 * ���͸��
	 * 
	 * @param frm
	 *            {@link drafterForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/drafterSave.json", produces = "application/json")
	public void drafterSave(DrafterForm frm,ModelMap model) {
		try {
			TBizDrafter drafter = drafterManager.saveDrafter(frm);
			model.put("id", drafter.getId());
			if(frm.getState()==0){
				JsonResultUtil.suncess(model, "����ѱ��棡");
			}else{
				JsonResultUtil.suncess(model, "���ͳɹ���");
			}
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ɾ�����
	 * 
	 * @param id
	 *            �����Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delDrafter.json", produces = "application/json")
	public void delDrafter(String id, ModelMap model) {
		try {
			this.drafterManager.removeDrafter(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (AppException e) {
			log.debug("ɾ����������������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
     * �������鿴ҳ��
     * @param id   ���id
     * @param model
     */
    @RequestMapping(value = "/drafterInfo.htm")
    public String drafterInfo(String id,ModelMap model) {
        try {
        	TBizDrafter drafter = null;
        	//���
        	String state = null;
        	if(StringUtils.isNotBlank(id)){
        		drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class,id);
        		//���״̬����Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
        		if(drafter.getState()!=null){
        			state=AuditStateEnum.getNameByCode(String.valueOf(drafter.getState()));
        		}
        	}
        	model.addAttribute("state", state);
        	model.addAttribute("drafter", drafter);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/drafter/drafterInfo";
    }
    
    /**
     * ����������ҳ��
     * @param id   ���id
     * @param model
     */
    @RequestMapping(value = "/auditDrafter.htm")
    public String auditDrafter(String id,ModelMap model) {
        try {
        	TBizDrafter drafter = null;
        	//���
        	String state = null;
        	if(StringUtils.isNotBlank(id)){
        		drafter = (TBizDrafter) drafterManager.get(TBizDrafter.class,id);
        		//���״̬����Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
        		if(drafter.getState()!=null){
        			state=AuditStateEnum.getNameByCode(String.valueOf(drafter.getState()));
        		}
        	}
        	model.addAttribute("state", state);
        	model.addAttribute("drafter", drafter);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/drafter/drafterAudit";
    }
    
    /**
	 * �������
	 * 
	 * @param id
	 *            �����Ϣ��ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/auditDrafter.json", produces = "application/json")
	public void audit(String id,String result,ModelMap model) {
		try {
			this.drafterManager.auditDrafter(id,result);
			JsonResultUtil.suncess(model, "������ɣ�");
		} catch (AppException e) {
			log.debug("ɾ����������������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 
	 * �������ܣ�����״̬�����б�
	 * 
	 */
	@RequestMapping(value = "/stateList.json")
	@ResponseBody
	public List<Combobox> stateList(ModelMap model) {
		return drafterManager.queryStateList();
	}
}