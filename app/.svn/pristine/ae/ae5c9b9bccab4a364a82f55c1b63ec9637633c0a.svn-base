package com.hnjz.app.work.danger;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

@Controller
public class AirController {
     private static final Log log=LogFactory.getLog(AirController.class);
     @Autowired
     private AirManager airManager;
     @Autowired
     private CommonManager commonManager;
     @RequestMapping(value="/airContentFind.htm")
 	public String airContentFind(ModelMap model,TDataLawobj lawobj){
 		String pid=lawobj.getId();
 		model.addAttribute("pid", pid);
 		return "app/work/danger/airContent";
 	}
     
     @RequestMapping(value="/airProjectAdd.htm")
 	public String airProjectAdd(ModelMap model,AirProjectForm airProjectForm){
 		
 		//model.addAttribute("pid",waterProjectForm pid);
 		return "app/work/danger/saveAirProject";
 	}
     @RequestMapping(value="/airAdd.htm")
     public String airAdd(ModelMap model,AirForm airForm){
    	 
    	 //model.addAttribute("pid",waterProjectForm pid);
    	 return "app/work/danger/saveAir";
     }
     
     /**
 	 * 保存大气状态
 	 */
 	@RequestMapping(value = "/saveAirProjectForm.json")
 	public void saveWaterProjectForm(ModelMap model, AirProjectForm airProjectForm) {
 		try {
 			airManager.saveAirProjectForm(airProjectForm);
 			JsonResultUtil.suncess(model, "添加成功！");
 		} catch (AppException e) {
 			e.printStackTrace();
 		}			
 		
 	}
     /**
      * 保存大气状态
      */
     @RequestMapping(value = "/saveAirForm.json")
     public void saveAirForm(ModelMap model, AirForm airForm) {
    	 try {
    		 
    		 airManager.saveAirForm(airForm);
    		 JsonResultUtil.suncess(model, "添加成功！");
    	 } catch (AppException e) {
    		 e.printStackTrace();
    	 }			
    	 
     }
 	/**
	 *air列表查询
	 */
	@RequestMapping(value = "/airProjectList.json", produces = "application/json")
	public void airProjectList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = airManager.airProjectList(pid, isActive, page,rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 *air列表查询
	 */
	@RequestMapping(value = "/airList.json", produces = "application/json")
	public void airList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = airManager.airList(pid, isActive, page,rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 *AirProject信息修改
	 * 
	 */
	@RequestMapping(value = "/editAirProject.htm")
	public String editAirProjectForm(ModelMap model,AirProjectForm airProjectForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + airProjectForm.getId());
			}
			this.airManager.editAirProjectForm(airProjectForm);
			model.addAttribute("title", "编辑主要产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/saveAirProject";
	}
	/**
	 *Air信息修改
	 * 
	 */
	@RequestMapping(value = "/editAir.htm")
	public String editAirForm(ModelMap model,AirForm airForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + airForm.getId());
			}
			this.airManager.editAirForm(airForm);
			model.addAttribute("title", "编辑主要产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/saveAir";
	}
	
	/**
	 *airProject信息查询
	 * 
	 */
	@RequestMapping(value = "/infoAirProject.htm")
	public String infoAirProject(ModelMap model,AirProjectForm airProjectForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + airProjectForm.getId());
			}
			this.airManager.infoAirProjectForm(airProjectForm);
			model.addAttribute("title", "信息");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoAirProject";
	}
	
	/**
	 * 删除一个airProject
	 * 
	 */
	@RequestMapping(value = "/delAirProject.json", produces = "application/json")
	public void delAirProject(ModelMap model, String id) {
		try {
			airManager.removeAirProject(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	/**
	 * 删除一个air
	 * 
	 */
	@RequestMapping(value = "/delAir.json", produces = "application/json")
	public void delAir(ModelMap model, String id) {
		try {
			airManager.removeAir(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	/**
	 * 生成危化品文档
	 * @param 
	 */
	@RequestMapping(value = "buildWhpListRecord.json", produces = "application/json")
	public void buildWhpListRecord(ModelMap model,String pid) {
		try {
			
			Map<String, String> map=airManager.buildWhpListRecord(pid);
			log.info("ceshiID:===="+map.get("id"));
			//commonManager.downloadFile(map.get("id"), res);
			JsonResultUtil.suncess(model,map.get("id"));
		} catch (Exception e) {
			log.error("保存信息错误：", e);
		//	JsonResultUtil.fail(model, "生成失败。");
		}
	}
}
