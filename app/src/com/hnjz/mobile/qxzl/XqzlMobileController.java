package com.hnjz.mobile.qxzl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.wszz.HjwfxwtzForm;
import com.hnjz.app.work.wszz.JdssdhzForm;
import com.hnjz.app.work.wszz.TzgzssdhzForm;
import com.hnjz.app.work.wszz.WszzManager;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
@Controller
public class XqzlMobileController {
	@Autowired
	private WszzManager wszzManager;
	@Autowired
	private CommWorkManager commWorkManager;
	@Autowired
	private AreaManager areaManager;
	/**
	 * 跳转到环境违法行为限期改正通知list页面
	 * taskId 任务Id
	 * taskTypeId 任务类型id
	 * */
	@RequestMapping(value="/gztzAdd.htm")
	public String gztzList(ModelMap model,String taskId,String taskTypeId){
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
	    	model.addAttribute("Androw", "1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/HjwfxwQxgztzList";
	}
	/**
	 * 跳转到（听证）告知书送达回证edit页面
	 * taskId 任务Id
	 * taskTypeId 任务类型id
	 * */
	@RequestMapping(value="/tzgzssdhzMobelAdd.htm")
	public String tzgzssdhzAdd(ModelMap model,String taskId,String taskTypeId){
		try {
			
			
			TzgzssdhzForm tzgzssdhzForm=wszzManager.findTzgzssdhzFormById(taskId, taskTypeId);
			if(tzgzssdhzForm!=null){
				model.addAttribute("tzgzssdhzForm", tzgzssdhzForm);
			}else{
				tzgzssdhzForm=new TzgzssdhzForm();
				TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
				tzgzssdhzForm.setTaskId(taskId);
				tzgzssdhzForm.setTaskTypeId(taskTypeId);
				TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
				tzgzssdhzForm.setTitle(ta.getUnitname());	
				model.addAttribute("tzgzssdhzForm", tzgzssdhzForm);
			}
	    	model.addAttribute("Androw", "1");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/tzgzssdhzEdit";
	}
	/**
	 * 跳转到行政处罚决定书送达回执edit页面
	 * taskId 任务Id
	 * taskTypeId 任务类型id
	 * */
	@RequestMapping(value="/jdssdhzMobelAdd.htm")
	public String jdssdhzAdd(ModelMap model,String taskId,String taskTypeId){
	    try {
	    	
	    	    
	    	    JdssdhzForm jdssdhzForm=wszzManager.findJdssdhzFormById(taskId, taskTypeId);
				if(jdssdhzForm!=null){
				model.addAttribute("jdssdhzForm", jdssdhzForm);
				}else{
					jdssdhzForm=new JdssdhzForm();
					TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
					jdssdhzForm.setTaskId(taskId);
					jdssdhzForm.setTaskTypeId(taskTypeId);
					TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
					jdssdhzForm.setTitle(ta.getUnitname());	
					model.addAttribute("jdssdhzForm", jdssdhzForm);
				}
	    	    
				model.addAttribute("Androw", "1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/work/wszz/jdssdhzEdit";
	}
}
