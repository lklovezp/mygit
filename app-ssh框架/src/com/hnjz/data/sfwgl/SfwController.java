/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.data.sfwgl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.data.po.TDataFileInfo;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.po.TSysDic;

/**
 * 收发文管理的Controller
 * @author 时秋寒
 *
 */
@Controller
public class SfwController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(SfwController.class);
	@Autowired
	private SfwManager sfwManager;
	
	@Autowired
	private DicManager dicManager;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/sfwList.htm")
	public String sfwList(ModelMap model, String title) {
		model.addAttribute("title", title);
		if("a0000000000000000000000000000000".equals(CtxUtil.getCurUser().getId()) || "Y".equals(CtxUtil.getCurUser().getIssys())){
			return "data/sfwgl/sfwList";
        }else{
        	return "data/sfwgl/sfwList_user";
        }
	}

	/**
	 * 查询收发文列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 收发文列表
	 * @throws Exception
	 */
	@RequestMapping(value = "/sfwQuery.json", produces = "application/json")
	public void sfwQuery(ModelMap model, String title, String starttime,String endtime, String type,String sourcepid,String sourceid,String number, String isActive, 
			String page, String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = sfwManager.querySfw(title,starttime,endtime,type,sourcepid,sourceid,number,isActive,page,pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到添加收发文信息的页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param frm
	 *            {@link sfwForm}
	 * @return 添加收发文页面
	 */
	@RequestMapping(value = "/sfwAdd.htm")
	public String sfwAdd(ModelMap model, SfwForm frm) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "文件录入");
			frm.setType("0");
			return "data/sfwgl/sfwAdd";
		}
		TDataFileInfo po = (TDataFileInfo) sfwManager.get(TDataFileInfo.class, frm.getId());
		frm.setId(po.getId());
		frm.setTitle(po.getTitle());
		if(StringUtils.isNotBlank(po.getType())){
			frm.setType(po.getType());
		}else{
			frm.setType("0");
		}
		frm.setNumber(po.getNumber());
		frm.setAutograph(po.getAutograph());
		frm.setSfwdate(DateUtil.getDate(po.getSfwdate()));
		frm.setSourceid(po.getSourceid());
		frm.setSourcepid(po.getSourcepid());
		frm.setCode(po.getCode());
		frm.setYear(po.getYear());
		frm.setPosition(po.getPosition());
		frm.setIsActive(po.getIsActive());
		model.addAttribute("title", "修改文件信息");
		return "data/sfwgl/sfwAdd";
	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 *            文件信息的ID
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/delSfw.json", produces = "application/json")
	public void delSfw(String id, ModelMap model) {
		try {
			this.sfwManager.removeSfw(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除文件信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * 保存收发文信息
	 * 
	 * @param frm
	 *            {@link SfwForm}
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/sfwSave.json", produces = "application/json")
	public void sfwSave(SfwForm frm, ModelMap model) throws AppException, ParseException{
		try {
			TDataFileInfo fileInfo= sfwManager.saveSfw(frm);
			model.put("id", fileInfo.getId());
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
     * 跳转到文件信息查看详情界面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/sfwInfo.htm")
    public String gywryInfo(ModelMap model,SfwForm frm) {
    	TDataFileInfo po = (TDataFileInfo) sfwManager.get(TDataFileInfo.class, frm.getId());
		model.addAttribute("title", "查看文件信息");
		frm.setId(po.getId());
		frm.setTitle(po.getTitle());
		if("1".equals(po.getType())){
			frm.setType("收文");
		}else if("0".equals(po.getType())){
			frm.setType("发文");
		}else{
			frm.setType("");
		}
		frm.setNumber(po.getNumber());
		frm.setAutograph(po.getAutograph());
		frm.setSfwdate(DateUtil.getDate(po.getSfwdate()));
		if(StringUtils.isNotBlank(po.getSourceid())){
			TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, po.getSourceid());
			if(dic!=null){
				frm.setSourcename(dic.getName());
			}
		}
		if(StringUtils.isNotBlank(po.getSourcepid())){
			TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, po.getSourcepid());
			if(dic!=null){
				frm.setSourcepname(dic.getName());
			}
		}
		frm.setSourceid(po.getSourceid());
		frm.setSourcepid(po.getSourcepid());
		frm.setPosition(po.getPosition());
		frm.setIsActive(po.getIsActive());
        return "data/sfwgl/sfwInfo";
    }
	/**
	 * 
	 * 函数介绍：收发文类型下拉列表
	 * 
	 */
	@RequestMapping(value = "/sfwlxList.json")
	@ResponseBody
	public List<Combobox> sfwlxList(ModelMap model) {
		return sfwManager.querySfwlxList();
	}
	
	/**
	 * 
	 * 函数介绍：收发文的导入
	 * 
	 */
	@RequestMapping(value = "/importsfFilePage.htm")
	public String importPage(ModelMap model) {
		return "common/import";
	}

}
