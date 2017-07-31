package com.hnjz.app.work.wszz;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;

@Controller
public class WszzController{
	@Autowired
	private WszzManager wszzManager;
	@Autowired
	private CommWorkManager commWorkManager;
	@Autowired
	private AreaManager areaManager;
	/**
	 * ��ת������Υ����Ϊ���ڸ���֪ͨlistҳ��
	 * taskId ����Id
	 * taskTypeId ��������id
	 * */
	@RequestMapping(value="/gztzList.htm")
	public String getgztzList(ModelMap model,String taskId,String taskTypeId){
	    try {
	    	
	    	HjwfxwtzForm hjwfxwtzForm=wszzManager.findHjwfxwtzFormById(taskId, taskTypeId);
	    	if(hjwfxwtzForm!=null){
	    	model.addAttribute("hjwfxwtzForm", hjwfxwtzForm);	
	    	}else{
	    		hjwfxwtzForm=new HjwfxwtzForm();
	    		BlMainForm bm=commWorkManager.getBlMainFormData(taskId);
		    	hjwfxwtzForm.setCorpName(bm.getBlZfdxForm().getLawobjname());
				TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
				hjwfxwtzForm.setTaskId(taskId);
				hjwfxwtzForm.setTaskTypeId(taskTypeId);
				hjwfxwtzForm.setTitle(o.getUnitname());	
				model.addAttribute("hjwfxwtzForm", hjwfxwtzForm);
	    	}
	    	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/HjwfxwQxgztzList";
	}
	/**
	 * �޸Ļ���Υ����Ϊ���ڸ���֪ͨ��
	 * */
	@RequestMapping(value="/editHjwfxwtzForm.json")
	public void saveHjwfxwtzForm(ModelMap model,HjwfxwtzForm hjwfxwtzForm,String Androw){
		try {
			wszzManager.saveHjwfxwtzFormById(hjwfxwtzForm);
			hjwfxwtzForm=wszzManager.findHjwfxwtzFormById(hjwfxwtzForm.getTaskId(), hjwfxwtzForm.getTaskTypeId());
			wszzManager.buildTzd(hjwfxwtzForm);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.suncess(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * ��ת�����������������ʹ��ִeditҳ��
	 * taskId ����Id
	 * taskTypeId ��������id
	 * */
	@RequestMapping(value="/jdssdhzAdd.htm")
	public String savejdssdhzAdd(ModelMap model,String taskId,String taskTypeId){
	    try {
	    	
	    		Calendar a=Calendar.getInstance();
	    	    JdssdhzForm jdssdhzForm=wszzManager.findJdssdhzFormById(taskId, taskTypeId);
	    	    if(jdssdhzForm!=null){
				model.addAttribute("jdssdhzForm", jdssdhzForm);
				}else{
					jdssdhzForm=new JdssdhzForm();
					TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
					jdssdhzForm.setTaskId(taskId);
					jdssdhzForm.setTaskTypeId(taskTypeId);
					jdssdhzForm.setTimeName(String.valueOf(a.get(Calendar.YEAR)));
					TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
					jdssdhzForm.setTitle(ta.getUnitname());	
					model.addAttribute("jdssdhzForm", jdssdhzForm);
				}
	    	    
	    	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/jdssdhzEdit";
	}
	/**
	 * ��ת������֤����֪���ʹ��֤editҳ��
	 * taskId ����Id
	 * taskTypeId ��������id
	 * */
	@RequestMapping(value="/tzgzssdhzAdd.htm")
	public String savetzgzssdhzAdd(ModelMap model,String taskId,String taskTypeId){
		try {
			
			Calendar a=Calendar.getInstance();
			TzgzssdhzForm tzgzssdhzForm=wszzManager.findTzgzssdhzFormById(taskId, taskTypeId);
			if(tzgzssdhzForm!=null){
				model.addAttribute("tzgzssdhzForm", tzgzssdhzForm);
			}else{
				tzgzssdhzForm=new TzgzssdhzForm();
				TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
				tzgzssdhzForm.setTaskId(taskId);
				tzgzssdhzForm.setTaskTypeId(taskTypeId);
				tzgzssdhzForm.setTimeName(String.valueOf(a.get(Calendar.YEAR)));
				TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
				tzgzssdhzForm.setTitle(ta.getUnitname());	
				model.addAttribute("tzgzssdhzForm", tzgzssdhzForm);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/tzgzssdhzEdit";
	}
	/**
	 * ����֤����֪��
	 * */
	@RequestMapping(value="/editTzgzssdhzForm.json")
	public void saveTzgzssdhzForm(ModelMap model,TzgzssdhzForm tzgzssdhzForm,String Androw){
		try {
			wszzManager.saveTzgzssdhzFormById(tzgzssdhzForm);
			TzgzssdhzForm jf=wszzManager.findTzgzssdhzFormById(tzgzssdhzForm.getTaskId(), tzgzssdhzForm.getTaskTypeId());
			wszzManager.buildTzgzssdhz(jf);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "����ɹ���");
			//model.addAttribute("msg", "����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.suncess(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * �޸����������������ʹ��ִ
	 * */
	@RequestMapping(value="/editJdssdhzForm.json")
	public void saveJdssdhzForm(ModelMap model,JdssdhzForm jdssdhzForm,String Androw){
		try {
			wszzManager.saveJdssdhzFormById(jdssdhzForm);
			JdssdhzForm jf=wszzManager.findJdssdhzFormById(jdssdhzForm.getTaskId(), jdssdhzForm.getTaskTypeId());
			wszzManager.buildJdssdhz(jf);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.suncess(model, "����ɹ���");
			e.printStackTrace();
		}
	}
	
}