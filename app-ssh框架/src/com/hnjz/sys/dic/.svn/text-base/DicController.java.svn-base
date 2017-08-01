/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.po.TSysDic;

/**
 * 字典Controller
 * 
 * @author zqf
 * @version $Id: DicController.java, v 0.1 Jan 15, 2017 5:04:04 PM ljf Exp $
 * 
 *          2013-04-11 字典选择（违法类型）[列表，可多选] zn [add] dicSelPage 字典选择页面 dicSelList
 *          字典选择数据
 */
@Controller
public class DicController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(DicController.class);

	@Autowired
	private DicManager dicManager;
	
	@Autowired
	private Dao dao;

	/**
	 * 到菜单功能的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 菜单功能的初始界面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dicMain.htm")
	public String dicOut(ModelMap model, String title) throws Exception {
		JSONArray re = dicManager.queryDic("0");
		model.addAttribute("re", re.toString());
		model.addAttribute("title", title);
		return "sys/dic/dataDictionary_out";
	}
	
	/**
	 * 收文类型界面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dataDictionary_in.htm", method = RequestMethod.GET)
	public String dicMainIn(ModelMap model, String title) throws Exception {
		JSONArray re = dicManager.queryDic("1");
		model.addAttribute("re", re.toString());
		model.addAttribute("title", title);
		return "sys/dic/dataDictionary_in";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/editDic.htm")
	public String editDic(ModelMap model, String id, String type) {
		DicForm dicForm = new DicForm();
		if("1".equals(type)){
			dicForm.setTypename("收文类型");
		}else{
			dicForm.setTypename("发文类型");
		}
		dicForm.setIsActive("Y");
		if (StringUtils.isBlank(id)) {
			dicForm.setType(type);
			model.addAttribute("dicForm", dicForm);
			return "sys/dic/editDic";
		}
		TSysDic po = (TSysDic) dicManager.get(TSysDic.class, id);
		dicForm.setId(po.getId());
		dicForm.setType(po.getType());
		dicForm.setPid(po.getPid());
		if(StringUtils.isNotBlank(po.getPid())){
			TSysDic org = (TSysDic) this.dao.get(TSysDic.class, po.getPid());
			dicForm.setpName(org.getName());
		}
		dicForm.setName(po.getName());
		dicForm.setNote(po.getDescribe());
		dicForm.setOrder(po.getOrderby());
		model.addAttribute("dicForm", dicForm);
		return "sys/dic/editDic";
	}

	/**
	 * 保存列表
	 */
	@RequestMapping(value = "/saveDic.json", produces = "application/json")
	public void saveDic(ModelMap model, DicForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			dicManager.saveDicData(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}

	/**
	 * 删除
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param result
	 *            {@link BindingResult}
	 * @return
	 */
	@RequestMapping(value = "/removeDic.json", produces = "application/json")
	public void removeDic(ModelMap model, String id) {
		try {
			dicManager.removeDic(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
	/**
	 * 数据字典的公共选择界面
	 * 
	 * @param id
	 *            部门Id
	 * @return 字典功能的初始界面
	 */
	@RequestMapping(value = "/dicPubQuery.htm")
	public String dicPubQuery(ModelMap model, String id, String type, String multi) {
		try {
			JSONArray re = dicManager.querySelectDic(id,type);
			model.addAttribute("menu", re.toString());
			model.addAttribute("multi", multi);
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("查询字典节点错误：", e);
		}
		return "common/dicPubQuery";
	}
	
	/**
	 * 根据id获取文档类型名称
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param result
	 *            {@link BindingResult}
	 * @return
	 */
	@RequestMapping(value = "/getWdlxById.json", produces = "application/json")
	public void getWdlxById(ModelMap model, String id) {
		try {
			TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, id);
			if(dic!=null){
				model.put("name", dic.getName());
			}else{
				model.put("name", "");
			}
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
	}

	/**
	 * 查询所有字典根节点（文档总分类）
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/wdflQuery.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> wdflQuery(ModelMap model, String type) {
		try {
			return dicManager.queryWdfl(type);
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	
	/**
	 * 根据文档分类查询所有子类型
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/wdzlxQuery.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> wdzlxQuery(ModelMap model, String pid) {
			return dicManager.queryWdzlx(pid);
	}
}
