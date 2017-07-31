package com.hnjz.app.work.danger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
@Controller
public class HjfxffcsController {
	private static final Log log=LogFactory.getLog(HjfxffcsController.class);
	
	@Autowired
	private HjfxffcsManager hjfxffcsManager;
	@RequestMapping(value="/whpHjfxffcsContentFind.htm")
	public String whpContentFind(ModelMap model,TDataLawobj lawobj){
		String pid=lawobj.getId();
		model.addAttribute("pid", pid);
		return "app/work/danger/hjfxffcsContent";
	}
	
	
	@RequestMapping(value="/hjfxffcsAdd.htm")
	public String whpAdd(ModelMap model,HjfxffcsForm hjfxffcsForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/saveHjfxffcsContent";
	}
	
	/**
	 * 保存一个环境防范措施
	 */
	@RequestMapping(value = "/saveHjfxffcsForm.json")
	public void saveHjfxffcsForm(ModelMap model, HjfxffcsForm hjfxffcsForm) {
		try {
			hjfxffcsManager.saveHjfxffcsForm(hjfxffcsForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 *危化品信息列表查询
	 */
	@RequestMapping(value = "/hjfxffcsList.json", produces = "application/json")
	public void hjfxffcsList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = hjfxffcsManager.hjfxffcsList(pid,isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 *危化品信息查询
	 * 
	 */
	@RequestMapping(value = "/hjfxffcsInfo.htm")
	public String infoYzcp(ModelMap model,HjfxffcsForm hjfxffcsForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + hjfxffcsForm.getId());
			}
			this.hjfxffcsManager.infoHjfxffcsForm(hjfxffcsForm);
			model.addAttribute("title", "主要产品信息");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoHjfxffcs";
	}
	
	/**
	 *危化品信息修改
	 * 
	 */
	@RequestMapping(value = "/editHjfxffcs.htm")
	public String editHjfxffcs(ModelMap model,HjfxffcsForm hjfxffcsForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + hjfxffcsForm.getId());
			}
			this.hjfxffcsManager.editHjfxffcsForm(hjfxffcsForm);
			model.addAttribute("title", "编辑主要产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/saveHjfxffcsContent";
		}
	
	
	/**
	 * 删除一个主要产品
	 * 
	 */
	@RequestMapping(value = "/delHjfxffcs.json", produces = "application/json")
	public void delHjfxffcs(ModelMap model, String id) {
		try {
			hjfxffcsManager.removeHjfxffcs(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
}
