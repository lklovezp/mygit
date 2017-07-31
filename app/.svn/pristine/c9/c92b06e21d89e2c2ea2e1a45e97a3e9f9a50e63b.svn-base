/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.jcd;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.IndexManager;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.sys.jcdtemplate.JcdTemplateManager;
import com.hnjz.wf.AbsJbpmController;

/**
 * 执行检查相关的Controller 作者：xb 生成日期：Mar 12, 2015 功能描述：
 * 
 */
@Controller
public class JcdController extends AbsJbpmController {

	@Autowired
	private JcdTemplateManager jcdTemplateManager;
	
	@Autowired
	private JcdManager jcdManager;
	
	@Autowired
    private IndexManager     indexManager;
 
	/**
	 * 跳转到检查单页面并加载左边节点数据
	 * 
	 * @param applyId 任务Id
	 * @param jcmbId 监察模板id
	 * @param taskType 任务类型
	 */
	@RequestMapping(value = "/jcd_page.htm")
	public String jcd_page(String applyId, String jcmbId, String taskType,
			ModelMap model) {
		try {
			Work work = workManager.get(applyId);
			model.addAttribute("work", work);
			String biaoshi  = indexManager.sysVer;
			if("0".equals(biaoshi)){
				model.addAttribute("sysver", biaoshi);
			}else{
				model.addAttribute("sysver", "1");
			}
			model.addAttribute("jcmbId", jcmbId);
			model.addAttribute("applyId", applyId);
			model.addAttribute("taskType", taskType);
			model.addAttribute("menu", jcdTemplateManager.templateTree(jcmbId));
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/jcd_page";
	}
	
	/**
	 * 跳转到再次检查页面
	 * 
	 * @param applyId 任务Id
	 * @param jcmbId 监察模板id
	 * @param taskType 任务类型
	 */
	@RequestMapping(value = "/zcjc_page.htm")
	public String zcjc_page(String applyId, String taskTypeCode,String jcmbId,String fileId, String fileTypeEnumName,String moreCheckFiletype,String jcqk,
			ModelMap model) {
		try {
			String biaoshi  = indexManager.sysVer;
			if("0".equals(biaoshi)){
				model.addAttribute("sysver", biaoshi);
			}else{
				model.addAttribute("sysver", "1");
			}
			model.addAttribute("applyId", applyId);
			model.addAttribute("taskTypeCode", taskTypeCode);
			model.addAttribute("jcmbId", jcmbId);
			model.addAttribute("fileId", fileId);
			model.addAttribute("moreCheckFiletype", moreCheckFiletype);
			model.addAttribute("fileTypeEnumName", fileTypeEnumName);
			model.addAttribute("jcqk", jcqk);
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/zcjc_page";
	}

	/**
	 * 获取检查记录
	 * @param model
	 * @param applyId 任务id
	 * @param jcmbId 监察模板id
	 */
	@RequestMapping(value = "getCheckRecord.json")
	public void getCheckRecord(ModelMap model, String applyId, String superJcmbId, String jcmbId, String taskType) {
		try {
			JSONArray re = jcdManager.getCheckRecord(applyId, superJcmbId, jcmbId, taskType);
			model.addAttribute("ans", re.toString());
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	/**
	 * 获取历史检查记录
	 * @param model
	 * @param applyId 任务id
	 * @param jcmbId 监察模板id
	 */
	@RequestMapping(value = "getHistoryRecord.json")
	public void getHistoryRecord(ModelMap model, String superJcmbId, String workId) {
		try {
			JSONArray re = jcdManager.getHistoryRecord(superJcmbId, workId);
			model.addAttribute("ans", re.toString());
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 获取检查记录
	 * 
	 * @param workId
	 * @param model
	 */
	@RequestMapping(value = "getJcx.json")
	public void jcxPage(ModelMap model, String jcmbId, String applyId, String taskType) {
		try {
			JSONArray re = jcdManager.getJcx(jcmbId,applyId);
			String str = re.toString();
			model.addAttribute("data", str);
			model.addAttribute("jcmbId", jcmbId);
			model.addAttribute("applyId", applyId);
			model.addAttribute("taskType", taskType);
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	/**
	 * 暂存
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param type
	 * @param answer
	 * @param beizhu
	 * @param orderby
	 * @return
	 */
	@RequestMapping(value = "saveTemporary.json", produces = "application/json")
	public void saveTemporary(ModelMap model, 
			String applyId, String jcmbId, String itemId, String itemcontent, String type, String answer, String beizhu, String orderby) {
		try {
			jcdManager.saveTemporary(applyId, jcmbId, itemId,itemcontent, type, answer, beizhu, orderby);
			JsonResultUtil.suncess(model, "保存成功。");
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 弹出修改检查项内容窗口
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param orderby
	 * @return
	 */
	@RequestMapping(value = "jcxEdit.htm", produces = "application/json")
	public String tojcxEdit(ModelMap model, String applyId, String jcmbId, String itemId,String orderby) {
		try {
			model.addAttribute("applyId", applyId);
			model.addAttribute("jcmbid", jcmbId);
			model.addAttribute("itemid", itemId);
			String itemcontent = jcdManager.getJcxcontent(applyId, jcmbId, itemId);
			model.addAttribute("orderby", orderby);
			model.addAttribute("itemcontent", itemcontent);
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/jcxEdit";
	}
	
	/**
	 * 修改检查项内容
	 * @param model
	 * @param applyId
	 * @param jcmbid
	 * @param itemid
	 * @param itemcontent
	 */
	@RequestMapping(value = "saveJcx.json", produces = "application/json")
	public void saveJcx(ModelMap model, String applyId, String jcmbid, String itemid,String itemcontent,String orderby) {
		try {
			jcdManager.editJcx(applyId, jcmbid, itemid,itemcontent,orderby);
			JsonResultUtil.suncess(model, "修改成功");
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 隐藏
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "hideJcx.json", produces = "application/json")
	public void hideJcx(ModelMap model, 
			String applyId, String jcmbId, String itemId,String orderby) {
		try {
			jcdManager.hideJcx(applyId,jcmbId,itemId,orderby);
			JsonResultUtil.suncess(model, "隐藏成功");
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存一个监察模板
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "saveCheckList.json", produces = "application/json")
	public void saveCheckList(ModelMap model, 
			String applyId, String jcmbId, String subdata) {
		try {
		
			jcdManager.saveCheckList(applyId, jcmbId, subdata);
		
			JsonResultUtil.suncess(model, "保存成功。");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "保存失败。");
		}
	}
	
	/**
	 * 生成检查记录文档
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "buildCheckListRecord.json", produces = "application/json")
	public void buildCheckListRecord(ModelMap model, String applyId, String taskType, String jcmbId) {
		try {
			jcdManager.buildCheckListRecord(applyId, taskType, jcmbId);
			JsonResultUtil.suncess(model, "生成成功。");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "生成失败。");
		}
	}
	
	/**
	 * 生成再次检查监察情况
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param jcqk
	 */
	@RequestMapping(value = "buildJcbl.json", produces = "application/json")
	public void buildJcbl(ModelMap model, String applyId, String taskType, String jcmbId, String jcqk,String fileid) {
		try {
			jcdManager.buildJcbl(applyId, taskType, jcmbId, jcqk,fileid);
			JsonResultUtil.suncess(model, "生成成功。");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "生成失败。");
		}
	}
	
	/**
	 * 下载检查记录
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "downloadCheckListRecord", produces = "application/json")
	public void downloadCheckListRecord(ModelMap model, String applyId, String taskType, HttpServletResponse res) {
		try {
			jcdManager.downloadCheckListRecord(applyId, taskType, res);
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查是否生成过检查记录
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "checkJcdExists.json", produces = "application/json")
	public void checkJcdExists(ModelMap model, String applyId, String taskType) {
		try {
			boolean exist = jcdManager.checkJcdExists(applyId, taskType);
			if (exist) {
				JsonResultUtil.suncess(model, "存在。");
			} else {
				JsonResultUtil.fail(model, "不存在。");
			}
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "查询失败。");
		}
	}
	
	/**
	 * 跳转到检查单同一个执法对象的信息记录
	 * 
	 * @param lawobjId 执法对象Id
	 * @param jcmbId 监察模板id
	 * @param taskType 执法对象类型
	 */
	@RequestMapping(value = "/jcd_historyPage.htm")
	public String jcd_historyPage(String applyId, String jcmbId, ModelMap model) {
		try {
			model.addAttribute("applyId", applyId);
			model.addAttribute("jcmbId", jcmbId);
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/jcd_historyPage";
	}
	
	 /**
     * 
     * 函数介绍：查询：执法对象的模板历史列表。
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/getJcmbRecordList.json", produces = "application/json")
    public void getJcmbRecordList(ModelMap model, String applyId, String jcmbId, String page, String pageSize) {
        try {
        	//添加离线判断标识，进行分类查询（存的时候加入0,1的存值）
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = jcdManager.getJcmbRecordList(applyId, jcmbId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
}
