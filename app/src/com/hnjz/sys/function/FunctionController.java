/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.sys.function;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.YnEnum;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.po.TSysFunc;

/**
 * 功能管理
 * 
 * @author wumi
 * @version $Id: FunctionController.java, v 0.1 Jan 4, 2012 9:51:08 AM
 *          Administrator Exp $
 */
@Controller
public class FunctionController {
	/** 日志 */
	private static final Log log = LogFactory.getLog(FunctionController.class);

	@Autowired
	private FunctionManager functionManager;

	/**
	 * 到菜单功能的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/function.htm")
	public String function(ModelMap model, String title) {
		this.functionQuery(model, null, YnEnum.Y.getCode());
		model.addAttribute("title", title);
		return "sys/function/function";
	}

	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/functionQuery.json", produces = "application/json")
	public void functionQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = functionManager.queryFunction(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 删除一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            菜单功能的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/functionDel.json", produces = "application/json")
	public void functionDel(ModelMap model, String id) {
		try {
			functionManager.removeFunction(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            菜单功能的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/functionSave.json", produces = "application/json")
	public void functionSave(ModelMap model, FunForm funForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(funForm));
			}
			functionManager.saveFunction(funForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存菜单信息错误：", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}

	/**
	 * 编辑一个菜单信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/functionEdit.htm")
	public String functionEdit(ModelMap model, FunForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			return "sys/function/functionEdit";
		}
		TSysFunc po = (TSysFunc) this.functionManager.get(TSysFunc.class, frm
				.getId());
		frm.setId(po.getId());
		frm.setFuncDesc(po.getDescribe());
		frm.setFuncName(po.getName());
		frm.setOrderby(String.valueOf(po.getOrderby()));
		if (null != po.getFunction()) {
			frm.setParent(po.getFunction().getId());
			frm.setParentName(po.getFunction().getName());
		}
		frm.setLinkaddr(po.getLinkAddr());
		return "sys/function/functionEdit";
	}

	/**
	 * 菜单的公共选择界面
	 * 
	 * @param id
	 *            菜单Id
	 * @return 菜单功能的初始界面
	 */
	@RequestMapping(value = "/functionPubQuery.htm")
	public String functionPubQuery(ModelMap model, String id) {
		try {
			JSONArray re = functionManager.querySelectFunction(id);
			model.addAttribute("menu", re.toString());
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("查询菜单信息错误：", e);
		}
		return "common/functionPubQuery";
	}

	/**
	 * 查询当前菜单有那些操作
	 * 
	 * @param id
	 *            菜单Id
	 * @return 菜单有那些操作
	 */
	@RequestMapping(value = "/functionOpt.json")
	@ResponseBody
	public List<FunOp> functionOpt(ModelMap model, String id) {
		try {
			return functionManager.queryOptByFunction(id);
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
}
