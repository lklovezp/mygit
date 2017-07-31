package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
@Controller
public class QyhjyjczjjyzyController {
	private static final Log log=LogFactory.getLog(HjfxffcsController.class);

	@Autowired
	private HjfxffcsManager hjfxffcsManager;
	@Autowired
	private QyhjyjczjjyzyManager qyhjyjczjjyzyManager;
	@RequestMapping(value="/whpYjczjjyzyContentFind.htm")
	public String whpContentFind(ModelMap model,TDataLawobj lawobj){
		String pid=lawobj.getId();
		model.addAttribute("pid", pid);
		return "app/work/danger/yjczjjyzyContent";
	}
	
	/**
	 *Σ��Ʒ��Ϣ�б���ѯ
	 */
	@RequestMapping(value = "/yjczjjyzyList.json", produces = "application/json")
	public void yjczjjyzyList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = qyhjyjczjjyzyManager.qyhjyjczjjyzyList(pid, isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	@RequestMapping(value="/yjczjjyzyAdd.htm")
	public String yjczjjyzyAdd(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/saveYjczjjyzyContent";
	}
	/**yjczjjyzyAdd.htm
	 * 
	 * �������ܣ�Ӧ���豸�����б�
	 */
	@RequestMapping(value ="/yjczType.json")
	@ResponseBody
	public List<Combobox> wlsState(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=qyhjyjczjjyzyManager.queryYjczTypeList();
		return listResult;
	}
	
	/**
	 * ����һ����Ԯ����
	 */
	@RequestMapping(value = "/saveQyhjyjczjjyzyForm.json")
	public void saveQyhjyjczjjyzyForm(ModelMap model, QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			qyhjyjczjjyzyManager.saveQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			JsonResultUtil.suncess(model, "���ӳɹ���");
		} catch (AppException e) {
			e.printStackTrace();
		}			
	}
	
	
	/**
	 *Σ��Ʒ��Ϣ�޸�
	 * 
	 */
	@RequestMapping(value = "/editYjczjjyzy.htm")
	public String editYjczjjyzy(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhjyjczjjyzyForm.getId());
			}
			this.qyhjyjczjjyzyManager.editQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			model.addAttribute("title", "�༭��Ҫ��Ʒ");
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/saveYjczjjyzyContent";
		}
	
	/**
	 * ɾ��һ����Ԯ����
	 * 
	 */
	@RequestMapping(value = "/delYjczjjyzy.json", produces = "application/json")
	public void delYjczjjyzy(ModelMap model, String id) {
		try {
			qyhjyjczjjyzyManager.removeQyhjyjczjjyzy(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
	
	/**
	 *Ӧ��������ʩ��Ϣ��ѯ
	 * 
	 */
	@RequestMapping(value = "/yjczjjyzyInfo.htm")
	public String infoYl(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhjyjczjjyzyForm.getId());
			}
			this.qyhjyjczjjyzyManager.infoQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			model.addAttribute("title", "Ӧ��������ʩ");
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/infoYjczjjyzy";
	}
}