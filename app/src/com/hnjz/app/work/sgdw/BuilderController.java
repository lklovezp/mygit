package com.hnjz.app.work.sgdw;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.TDataSgdw;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

@Controller
public class BuilderController {
    /*��־*/	
	private static final Log log=LogFactory.getLog(BuilderController.class);
	@Autowired
	private BuilderManager builderManager;
	/**
	 * ��ʩ����λ�����ĳ�ʼ����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return ʩ����λ�����ĳ�ʼ����
	 */
	@RequestMapping(value = "/builderList.htm")
	public String builderList(ModelMap model, String title,String id,String areaId,String starttime,String endtime) {
		model.addAttribute("title", title);
		return "app/work/sgdw/builderList";
	}
	@RequestMapping(value="/queryBuilderList.json")
	public void queryBuilderList(ModelMap model, String name, String isActive, String page,String pageSize){
		page = StringUtils.defaultIfBlank(page, "1");
	}
	/**
	 * �༭һ��ʩ����λ��Ϣ
	 * 
	 * @param id
	 *            ʩ����λId
	 * @return ʩ����λ�����ĳ�ʼ����
	 */
	@RequestMapping(value = "/editBuilder.htm")
	public String editBuilder(ModelMap model, BuilderForm builderForm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + builderForm.getId());
		}
		if (StringUtils.isNotBlank(builderForm.getId())) {
			try {
				this.builderManager.editBuilder(builderForm);
			} catch (AppException e) {
				e.printStackTrace();
			}
			model.addAttribute("title", "�༭ʩ����λ");
		} else {
			model.addAttribute("title", "�½�ʩ����λ");
		}
		return "app/work/sgdw/editBuilder";
	}
	
	/**
	 * ����һ��ʩ����λ��Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param builderForm
	 *            ʩ����λ��form����
	 * 
	 */
	@RequestMapping(value = "/saveBuilder.json")
	public void saveBuilder(ModelMap model, BuilderForm builderForm,String isActive) {
		
		try {
			log.info("�õ�isAtive===="+isActive);
			builderManager.saveBuilder(builderForm);
			log.info("�õ�isAtive===="+isActive);
			JsonResultUtil.suncess(model, "���ӳɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
		}
	}
	/**
	 * ʩ����λ�����Ĳ�ѯ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            �������������԰����Ʋ�ѯ
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 */
	@RequestMapping(value = "/builderQuery.json", produces = "application/json")
	public void builderQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = builderManager.queryBuilderList(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	/**
	 * ɾ��һ��ʩ����λ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            ʩ����λid
	 */
	@RequestMapping(value = "/delBuilder.json", produces = "application/json")
	public void delBuilder(ModelMap model, String id) {
		try {
			builderManager.removeBuilder(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���û���Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
	
	
	/**
	 * ��ѯһ��ʩ����λ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param builderForm
	 *            ʩ����λbuilderForm
	 */
	@RequestMapping(value = "/builderInfo.htm")
	public String builderInfo(ModelMap model,BuilderForm builderForm) {
		try {
			this.builderManager.editBuilder(builderForm);
			model.addAttribute("title", "ʩ����λ��Ϣ");
		} catch (Exception e) {
			log.error("��ѯ����", e);
			
		}
		return "app/work/sgdw/chaKanBuilder";
	}


}