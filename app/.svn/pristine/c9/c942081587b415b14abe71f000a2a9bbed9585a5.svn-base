/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.record;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

/**
 * 笔录问题管理的Controller
 * 
 * @author wumi
 * @version $Id: RecordController.java, v 0.1 2013-3-25 下午03:59:37 wumi Exp $
 */
@Controller
public class RecordController {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(RecordController.class);

	@Autowired
	private RecordManager recordManager;

	/**
	 * 到任务类型的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 违法类型的初始界面
	 */
	@RequestMapping(value = "/recordList.htm")
	public String recordList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "sys/record/recordList";
	}

	/**
	 * 查询列表数据
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/queryRecordList.json", produces = "application/json")
	public void queryRecordList(ModelMap model, String wflx, String content, String iscurver, String kcxwbj, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			System.out.println("************************"+wflx);
			FyWebResult re = recordManager.queryRecord(wflx, content, iscurver, kcxwbj, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 编辑一个笔录信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/editRecord.htm")
	public String edtiRecord(ModelMap model, RecordForm frm,String wflx) {
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建笔录问题");
			frm.setTasktype(TaskTypeCode.WFAJ.getCode());
			return "sys/record/newRecord";
		}
		recordManager.recordInfo(frm);
		model.addAttribute("title", "编辑笔录问题");
		model.addAttribute("wflx", wflx);
		return "sys/record/newRecord";
	}
	
	/**
	 * 保存一个笔录信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param VersionForm 笔录问题的表单
	 * @return 笔录的初始界面
	 */
	@RequestMapping(value = "/saveRecord.json", produces = "application/json")
	public void saveRecord(ModelMap model, RecordForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			recordManager.saveRecord(frm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除一个笔录
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            笔录id
	 */
	@RequestMapping(value = "/removeRecord.json", produces = "application/json")
	public void removeRecord(ModelMap model, String ids) {
		try {
			String[] idArry=ids.split(",");
			for(String id:idArry){
				recordManager.removeRecord(id);
			}
			
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
}
