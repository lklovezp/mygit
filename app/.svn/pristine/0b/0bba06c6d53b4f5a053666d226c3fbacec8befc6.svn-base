/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 监察模板管理的Controller
 * 
 * @author wumi
 * @version $Id: JcdTemplateController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class JcdTemplateController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdTemplateController.class);

	@Autowired
	private JcdTemplateManager jcdTemplateManager;

	/**
	 * 到监察模板的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 监察模板的初始界面
	 */
	@RequestMapping(value = "/jcdTemplateList.htm")
	public String jcdTemplateList(ModelMap model, String title) {
		
		this.jcdTemplateQuery(model, null);
		model.addAttribute("title", title);
		return "sys/jcdtemplate/jcdTemplateList";
	}
	
	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryJcdTemplate.json", produces = "application/json")
	public void jcdTemplateQuery(ModelMap model, String name) {
		try {
			JSONArray re = jcdTemplateManager.queryJcdTemplate(name);
			model.addAttribute("menu", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 点击监察模板树跳转到页面
	 * @param model {@link ModelMap}
	 * @return 页面路径
	 */
	@RequestMapping(value = "/treeClickJumpPage.htm")
	public String treeClickJumpPage(ModelMap model, TemplateForm frm) {
		return jcdTemplateManager.treeClickJumpPage(frm);
	}
	
	/**
	 * 跳转到新建检查项页面
	 * @param model {@link ModelMap}
	 * @return 新建检查项的初始界面
	 */
	@RequestMapping(value = "/editCheckListItem.htm")
	public String newCheckListItem(ModelMap model, CheckItemForm frm) {
		jcdTemplateManager.queryCheckListList(frm);
		return "sys/jcdtemplate/newCheckListItem";
	}
	
	/**
	 * 跳转到版本页面
	 * @param model {@link ModelMap}
	 * @return 新建检查项的初始界面
	 */
	@RequestMapping(value = "/editTemplateVersion.htm")
	public String editTemplateVersion(ModelMap model, TemplateForm frm) {
		
		return jcdTemplateManager.editTemplateVersion(frm);
	}
	
	/**
	 * 保存监察模板
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存监察模板
	 */
	@RequestMapping(value = "/saveTemplate.json", produces = "application/json")
	public void saveTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveTemplate(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 保存子模板
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存监察模板
	 */
	@RequestMapping(value = "/saveSubTemplate.json", produces = "application/json")
	public void saveSubTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveSubTemplate(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 保存模板版本
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存监察模板
	 */
	@RequestMapping(value = "/saveTemplateVersion.json", produces = "application/json")
	public void saveTemplateVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveTemplateVersion(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 保存复制的模板版本
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveCopyVersion.json", produces = "application/json")
	public void saveCopyVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveCopyVersion(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 跳转到新建检查项页面
	 * @param model {@link ModelMap}
	 * @return 新建检查项的初始界面
	 */
	@RequestMapping(value = "/copyTemplatePage.htm")
	public String copyTemplatePage(ModelMap model, String oldIndustry, String oldTaskType) {
		TemplateForm t = new TemplateForm();
		t.setIndustry(oldIndustry);
		t.setTasktype(oldTaskType);
		TDataChecklisttemplate po = jcdTemplateManager.queryTemplateWithNoId(t);
		model.addAttribute("title", "复制模板");
		model.addAttribute("oldIndustry", oldIndustry);
		model.addAttribute("oldTaskType", oldTaskType);
		model.addAttribute("name", po.getName());
		return "sys/jcdtemplate/copyTemplate";
	}
	
	/**
	 * 保存复制的模板
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveCopyTemplate.json", produces = "application/json")
	public void saveCopyTemplate(ModelMap model, String name, String oldIndustry, String oldTaskType, String industry, String taskType) {
		try {
			jcdTemplateManager.saveCopyTemplate(name, oldIndustry, oldTaskType, industry, taskType);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 保存检查项
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 保存检查项
	 */
	@RequestMapping(value = "/saveCheckListItem.json", produces = "application/json")
	public void saveCheckListItem(ModelMap model, CheckItemForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveCheckListItem(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 查询检查项列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryCheckListItem.json", produces = "application/json")
	public void queryCheckListItem(ModelMap model, String templateid,
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = jcdTemplateManager.queryCheckListItem(templateid, page,
					pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 查询检查项列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryCheckItemAns.json", produces = "application/json")
	public void queryCheckItemAns(ModelMap model, String itemId) {
		try {
			FyWebResult re = jcdTemplateManager.queryCheckItemAns(itemId);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 跳转到添加子模板页面
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/addSubTemplate.htm")
	public String addSubTemplate(ModelMap model, TemplateForm frm) {
		jcdTemplateManager.addSubTemplate(frm);
		return "sys/jcdtemplate/newSubTemplate";
	}
	
	/**
	 * 设置为默认模板
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/saveDefaultVersion.json")
	public void saveDefaultVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.saveDefaultVersion(frm);
			JsonResultUtil.suncess(model, "设置成功。");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "设置失败。");
		}
	}
	
	/**
	 * 删除检查项
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeCheckListItme.json")
	public void removeCheckListItme(ModelMap model, CheckItemForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeCheckListItme(frm);
			JsonResultUtil.suncess(model, "删除成功。");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, "删除失败。");
		}
	}
	
	/**
	 * 删除模板
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeTemplate.json")
	public void removeTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeTemplate(frm);
			JsonResultUtil.suncess(model, "删除成功。");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除版本
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeTempVersion.json")
	public void removeVersion(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeVersion(frm);
			JsonResultUtil.suncess(model, "删除成功。");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除子模板
	 * @param model {@link ModelMap}
	 * @return 
	 */
	@RequestMapping(value = "/removeSubTemplate.json")
	public void removeSubTemplate(ModelMap model, TemplateForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			jcdTemplateManager.removeSubTemplate(frm);
			JsonResultUtil.suncess(model, "删除成功。");
		} catch (Exception e) {
			log.error("删除信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/templateTree.json", produces = "application/json")
	public void templateTree(ModelMap model, String templateId) {
		try {
			JSONArray re = jcdTemplateManager.templateTree(templateId);
			model.addAttribute("menu", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
}
