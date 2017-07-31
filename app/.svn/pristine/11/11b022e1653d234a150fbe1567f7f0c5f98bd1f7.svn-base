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
	 *危化品信息列表查询
	 */
	@RequestMapping(value = "/yjczjjyzyList.json", produces = "application/json")
	public void yjczjjyzyList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = qyhjyjczjjyzyManager.qyhjyjczjjyzyList(pid, isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	@RequestMapping(value="/yjczjjyzyAdd.htm")
	public String yjczjjyzyAdd(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/saveYjczjjyzyContent";
	}
	/**yjczjjyzyAdd.htm
	 * 
	 * 函数介绍：应急设备下拉列表
	 */
	@RequestMapping(value ="/yjczType.json")
	@ResponseBody
	public List<Combobox> wlsState(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=qyhjyjczjjyzyManager.queryYjczTypeList();
		return listResult;
	}
	
	/**
	 * 保存一个救援物资
	 */
	@RequestMapping(value = "/saveQyhjyjczjjyzyForm.json")
	public void saveQyhjyjczjjyzyForm(ModelMap model, QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			qyhjyjczjjyzyManager.saveQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			e.printStackTrace();
		}			
	}
	
	
	/**
	 *危化品信息修改
	 * 
	 */
	@RequestMapping(value = "/editYjczjjyzy.htm")
	public String editYjczjjyzy(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhjyjczjjyzyForm.getId());
			}
			this.qyhjyjczjjyzyManager.editQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			model.addAttribute("title", "编辑主要产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/saveYjczjjyzyContent";
		}
	
	/**
	 * 删除一个救援物资
	 * 
	 */
	@RequestMapping(value = "/delYjczjjyzy.json", produces = "application/json")
	public void delYjczjjyzy(ModelMap model, String id) {
		try {
			qyhjyjczjjyzyManager.removeQyhjyjczjjyzy(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
	/**
	 *应急处置设施信息查询
	 * 
	 */
	@RequestMapping(value = "/yjczjjyzyInfo.htm")
	public String infoYl(ModelMap model,QyhjyjczjjyzyForm qyhjyjczjjyzyForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhjyjczjjyzyForm.getId());
			}
			this.qyhjyjczjjyzyManager.infoQyhjyjczjjyzyForm(qyhjyjczjjyzyForm);
			model.addAttribute("title", "应急处置设施");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoYjczjjyzy";
	}
}
