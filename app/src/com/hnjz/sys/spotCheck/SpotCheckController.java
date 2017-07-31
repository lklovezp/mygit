/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.spotCheck;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.xxgl.yearlawobj.YearLawobjManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.configCheckProportion.CheckProportionManager;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TSysUser;

/**
 * �����ѡ��Controller
 * @author shiqiuhan
 * @created 2015-12-16,����01:38:29
 */
@Controller
public class SpotCheckController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(SpotCheckController.class);
	
	@Autowired
	private SpotCheckManager spotCheckManager;
	
	@Autowired
	private CheckProportionManager checkProportionManager;
	
	@Autowired
	private YearLawobjManager yearLawobjManager;
	
	/**
	 * ��ת����ѯ���ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ��ѯ���ҳ��
	 */
	@RequestMapping(value = "/spotCheckList.htm")
	public String spotCheckList(ModelMap model, String title,String areaid) {
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		model.addAttribute("title", title);
		return "sys/randomCheck/spotCheck/spotCheckList";
	}
	
	/**
	 * ��ʼ��ѡ(����)
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 *            
	 * @throws Exception
	 */
	@RequestMapping(value = "/startCheck.json", produces = "application/json")
	public void startCheck(ModelMap model, String year, String quarter, String areaid) {
		try {
			int proportion = checkProportionManager.queryProportion(year, quarter);//���ȳ�����
			//������ݼ��Ȳ�ѯ�����е���ҵ�б�
			List<TBizCheckedLawobj> tBizCheckedLawobj= spotCheckManager.queryCheckedList(year,quarter);
			if(!yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "�������δ��ɳ�飬���������ȳ�ѡ����");
				return ;
			}
			if(tBizCheckedLawobj!=null && tBizCheckedLawobj.size()>0){
				JsonResultUtil.fail(model, "�ü�������ɳ�ѡ���ɵ����ѯ�鿴��ѡ���");
				return ;
			}else if(proportion==0){
				JsonResultUtil.fail(model, "�ü��ȳ�����δ���ã��������ó�����");
				return ;
			}else if(proportion<25){//������С��25%
				JsonResultUtil.fail(model, "���������ɵ���25%�����������ó�����");
				return ;
			}
			spotCheckManager.startSpotCheck(year, quarter,areaid);
			JsonResultUtil.suncess(model, "��ѡ�ɹ�");
		} catch (Exception e) {
			log.error("��ѡ������", e);
		}
	}
	
	/**
	 * ��ʼ��ѡ(���)
	 * 
	 * @param year
	 *            ���
	 * @throws Exception
	 */
	@RequestMapping(value = "/yearCheck.json", produces = "application/json")
	public void yearCheck(ModelMap model, String year) {
		try {
			//������ݲ�ѯ������Ƿ�����ɳ�ѡ
			if(yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "���������ɳ�ѡ���ɵ����ѯ�鿴��ѡ���");
				return ;
			}
			spotCheckManager.startYearCheck(year);
			JsonResultUtil.suncess(model, "��ѡ�ɹ�");
		} catch (Exception e) {
			log.error("��ѡ������", e);
		}
	}
	
	/**
	 * ɾ����ѡ���(���)
	 * 
	 * @param year
	 *            ���
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteResult.json", produces = "application/json")
	public void deleteResult(ModelMap model, String year) {
		try {
			//������ݲ�ѯ������Ƿ�����ɳ�ѡ
			if(!yearLawobjManager.isChecked(year)){
				JsonResultUtil.fail(model, "����Ȼ�δ������ȳ��");
				return ;
			}
			spotCheckManager.deleteResult(year);
			JsonResultUtil.suncess(model, "ɾ���ɹ�");
		} catch (Exception e) {
			log.error("��ѡ������", e);
		}
	}
	
	/**
	 * ��������
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 *            
	 * @throws Exception
	 */
	@RequestMapping(value = "/createWork.json", produces = "application/json")
	public void createWork(ModelMap model, String year, String quarter) {
		try {
			String createResult = spotCheckManager.createWork(year, quarter);
			JsonResultUtil.suncess(model, createResult);
		} catch (Exception e) {
			log.error("��ѡ������", e);
		}
	}
	
	/**
	 * ��ѯ�����е���ȾԴ�б�
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkedLawobjQuery.json", produces = "application/json")
	public void checkedLawobjQuery(ModelMap model, String page,String year, String month,String areaid,
			String pageSize) {
		try {
			
			if(StringUtils.isBlank(areaid)){
				areaid=CtxUtil.getAreaId();
			}
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = spotCheckManager.queryCheckedLawobj(year, month,areaid,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
}