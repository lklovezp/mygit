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
public class WaterController {
	@Autowired
	private WaterManager waterManager;
	private static final Log log=LogFactory.getLog(WaterController.class);
	
	@RequestMapping(value="/waterContentFind.htm")
	public String waterContentFind(ModelMap model,TDataLawobj lawobj){
		String pid=lawobj.getId();
		model.addAttribute("pid", pid);
		return "app/work/danger/waterContent";
	}
	
	@RequestMapping(value="/waterAdd.htm")
	public String waterAdd(ModelMap model,WaterForm waterForm){
		return "app/work/danger/saveWater";
	}
	@RequestMapping(value="/waterProjectAdd.htm")
	public String waterProjectAdd(ModelMap model,WaterProjectForm waterProjectForm){
		return "app/work/danger/saveWaterProject";
	}
	
	/**
	 * 
	 * �������ܣ������б�
	 */
	@RequestMapping(value ="/qsDbsType.json")
	@ResponseBody
	public List<Combobox> qsDbsType(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=waterManager.qjdbsList();
		return listResult;
	}
	@RequestMapping(value ="/qsHsType.json")
	@ResponseBody
	public List<Combobox> qsHsType(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=waterManager.qjhsList();
		return listResult;
	}
	@RequestMapping(value ="/fsDbsType.json")
	@ResponseBody
	public List<Combobox> fsDbsType(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=waterManager.dbsList();
		return listResult;
	}
	@RequestMapping(value ="/fsHsType.json")
	@ResponseBody
	public List<Combobox> fsHsType(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=waterManager.hsList();
		return listResult;
	}
	
	/**
	 * ����ˮ����״̬
	 */
	@RequestMapping(value = "/saveWaterForm.json")
	public void saveWaterForm(ModelMap model, WaterForm waterForm) {
		try {
			waterManager.saveWaterForm(waterForm);
			JsonResultUtil.suncess(model, "���ӳɹ���");
		} catch (AppException e) {
			e.printStackTrace();
		}			
			
	}
	/**
	 * ����ˮ����״̬
	 */
	@RequestMapping(value = "/saveWaterProjectForm.json")
	public void saveWaterProjectForm(ModelMap model, WaterProjectForm waterProjectForm) {
		try {
			waterManager.saveWaterProjectForm(waterProjectForm);
			JsonResultUtil.suncess(model, "���ӳɹ���");
		} catch (AppException e) {
			e.printStackTrace();
		}			
		
	}
	
	/**
	 *water�б���ѯ
	 */
	@RequestMapping(value = "/waterList.json", produces = "application/json")
	public void waterList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = waterManager.waterList(pid, isActive, page,rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	/**
	 *waterProject�б���ѯ
	 */
	@RequestMapping(value = "/waterProjectList.json", produces = "application/json")
	public void waterProjectList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = waterManager.waterProjectList(pid, isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 *water��Ϣ�޸�
	 * 
	 */
	@RequestMapping(value = "/editWater.htm")
	public String editWater(ModelMap model,WaterForm waterForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + waterForm.getId());
			}
			this.waterManager.editWaterForm(waterForm);
			} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/saveWater";
		}
	/**
	 *waterProject��Ϣ�޸�
	 * 
	 */
	@RequestMapping(value = "/editWaterProject.htm")
	public String editWaterProject(ModelMap model,WaterProjectForm waterProjectForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + waterProjectForm.getId());
			}
			this.waterManager.editWaterProjectForm(waterProjectForm);
			} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/saveWaterProject";
	}
	
	/**
	 *water��Ϣ��ѯ
	 * 
	 */
	@RequestMapping(value = "/infoWater.htm")
	public String infoYzcp(ModelMap model,WaterForm waterForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + waterForm.getId());
			}
			this.waterManager.infoWaterForm(waterForm);
			} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/infoWater";
	}
	/**
	 *waterProject��Ϣ��ѯ
	 * 
	 */
	@RequestMapping(value = "/infoWaterProject.htm")
	public String infoWaterProject(ModelMap model,WaterProjectForm waterProjectForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + waterProjectForm.getId());
			}
			this.waterManager.infoWaterProjectForm(waterProjectForm);
			} catch (Exception e) {
			log.error("��ѯ������", e);
		}
		return "app/work/danger/infoWaterProject";
	}
	
	/**
	 * ɾ��һ��water
	 * 
	 */
	@RequestMapping(value = "/delWater.json", produces = "application/json")
	public void delWater(ModelMap model, String id) {
		try {
			waterManager.removeWater(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
	/**
	 * ɾ��һ��waterProject
	 * 
	 */
	@RequestMapping(value = "/delWaterProject.json", produces = "application/json")
	public void delWaterProject(ModelMap model, String id) {
		try {
			waterManager.removeWaterProject(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ϣ����", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}
}