/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.jcd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.work.jcd.JcdManager;
import com.hnjz.app.work.po.TBizChecklist;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.YnEnum;
import com.hnjz.common.util.StringUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 执行检查相关的Controller 作者：xb 生成日期：Mar 12, 2015 功能描述：
 * 
 */
@Controller
public class JcdMoController extends AbsJbpmController {
	
	@Autowired
	private JcdMoManager jcdMoManager;
	
	@Autowired
	private JcdManager jcdManager;

	@RequestMapping(value = "/templateTree.mo", produces = "application/json")
	public void templateTree(ModelMap model, String templateId, String applyId, String selectTaskid, String uuid) {
		try {
			HashMap<String, Object> re = jcdMoManager.templateTree(uuid, templateId);
			model.addAttribute("menu", re);
			List<HashMap<String, Object>> l = new ArrayList<HashMap<String,Object>>();
			l.add(re);
			String next = "";
			try {
				this.getFirstJcx(l);	
			} catch (AppException e){
				next = e.getMessage();
			}
			List<HashMap<String, Object>> a = jcdMoManager.getJcx(applyId,selectTaskid,templateId,next);
			model.addAttribute("data", a);
			model.addAttribute("next", next);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	private void getFirstJcx(List<HashMap<String, Object>> re) throws AppException{
		for (int j = 0; j < re.size(); j++) {
			List<HashMap<String, Object>> l = (List<HashMap<String, Object>>) re.get(j).get("children");
			if (l.size() == 0){
				throw new AppException(re.get(j).get("id").toString());
			} else {
				getFirstJcx(l);	
			}
		}
	}
	
	/**
	 * 获取检查记录
	 * 
	 * @param workId
	 * @param model
	 */
	@RequestMapping(value = "getJcx.mo")
	public void jcxPage(ModelMap model, String jcmbId, String templateId, String applyId, String selectTaskid, String taskType) {
		try {
			List<HashMap<String, Object>> re = jcdMoManager.getJcx(applyId,selectTaskid, templateId,jcmbId);
			model.addAttribute("data", re);
			model.addAttribute("templateId", templateId);
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
	@RequestMapping(value = "saveTemporary.mo", produces = "application/json")
	public void saveTemporary(ModelMap model, 
			String applyId, String jcmbId, String itemId, String type, String answer, String beizhu, String orderby) {
		try {
			jcdMoManager.saveTemporary(applyId, jcmbId, itemId, type, answer, beizhu, orderby);
			JsonResultUtil.suncess(model, "保存成功。");
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
	@RequestMapping(value = "saveCheckList.mo", produces = "application/json")
	public void saveCheckList(ModelMap model, 
			String applyId,String selectTaskid, String templateId, String jcmbId, String subdata, String uuid) {
		try {
			jcdMoManager.saveCheckList(applyId, jcmbId, subdata);
			model.addAttribute("msg", "保存成功！");
			HashMap<String, Object> o = jcdMoManager.getNextCheckItem(uuid,applyId,selectTaskid,templateId,jcmbId);
			model.addAttribute("data", o == null ? "" : o.get("data"));
			model.addAttribute("next", o == null ? "" : o.get("next"));
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			model.addAttribute("msg", "保存失败！");
		}
	}
	
	/**
	 * 获取检查记录
	 * @param model
	 * @param applyId 任务id
	 * @param jcmbId 监察模板id
	 */
	@RequestMapping(value = "checkAllDone.mo")
	public void checkAllDone(ModelMap model, String applyId, String selectTaskid, String templateId, String jcmbId, String uuid) {
		try {
			// 取出所有监察模板
			List<TDataChecklistitem> items = jcdMoManager.getAllCheckItems(uuid, jcmbId);
			// 取此任务的所有已填写的检查项
			List<TBizChecklist> pos = jcdMoManager.getAllRecordOfTask(applyId);
			TBizChecklist po = null;
			TDataChecklistitem item = null;
			List<String> itemsid = new ArrayList<String>();
			for (int i = 0; i < pos.size(); i++) {
				itemsid.add(pos.get(i).getItemid());
			}
			boolean breakloop = false;
			for (int i = 0; i < items.size(); i++) {
				if (breakloop){
					break;
				} else {
					item = items.get(i);
					for (int j = 0; j < pos.size(); j++) {
						po = pos.get(j);
						if (itemsid.contains(item.getId())){
							if (po.getItemid().equals(item.getId())){
								// 填空
								if (item.getInputtype().equals("0") && "Y".equals(item.getIsrequired())){
									if (!StringUtil.isNotBlank(po.getDescribe())){
										// 取出这个检查项的模板中的所有检查项和答案
										List<HashMap<String, Object>> re = jcdMoManager.getJcxForCheck(applyId, item.getTemplate().getId());
										model.addAttribute("data", re);
										model.addAttribute("jcmbId", item.getTemplate().getId());
										model.addAttribute("msg", item.getContents() + "答案未填写");
										model.addAttribute("applyId", applyId);
										breakloop = true;
										break;
									}
								}
								// 单选或多选
								else if (item.getInputtype().equals("1") || item.getInputtype().equals("2")){
									if (StringUtil.isBlank(po.getAnswerid()) && "Y".equals(item.getIsrequired())){
										// 取出这个检查项的模板中的所有检查项和答案
										List<HashMap<String, Object>> re = jcdMoManager.getJcxForCheck(applyId, item.getTemplate().getId());
										model.addAttribute("data", re);
										model.addAttribute("jcmbId", item.getTemplate().getId());
										model.addAttribute("msg", item.getContents() + "答案未选择");
										model.addAttribute("applyId", applyId);
										breakloop = true;
										break;
									} else {
										if (StringUtil.isBlank(po.getDescribe()) && item.getIsrequired().equals(YnEnum.Y.getCode())){
											// 取出这个检查项的模板中的所有检查项和答案
											List<HashMap<String, Object>> re = jcdMoManager.getJcxForCheck(applyId, item.getTemplate().getId());
											model.addAttribute("data", re);
											model.addAttribute("jcmbId", item.getTemplate().getId());
											model.addAttribute("msg", item.getContents() + "备注未填写");
											model.addAttribute("applyId", applyId);
											breakloop = true;
											break;
										}
									}
								}
							}
						} else {
							// 取出这个检查项的模板中的所有检查项和答案
							List<HashMap<String, Object>> re = jcdMoManager.getJcx(applyId, selectTaskid, templateId, item.getTemplate().getId());
							model.addAttribute("data", re);
							model.addAttribute("jcmbId", item.getTemplate().getId());
							model.addAttribute("msg", "");
							model.addAttribute("applyId", applyId);
							breakloop = true;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	/**
	 * 生成检查记录文档
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "buildCheckListRecord.mo", produces = "application/json")
	public void buildCheckListRecord(ModelMap model, String applyId, String taskType, String jcmbId) {
		try {
			HashMap<String, Object> data = jcdManager.buildCheckListRecord(applyId, taskType, jcmbId);
			JsonResultUtil.suncess(model, "生成成功。");
			model.addAttribute("data", data);
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
	@RequestMapping(value = "downloadCheckListRecord.mo", produces = "application/json")
	public void downloadCheckListRecord(ModelMap model, String applyId, String taskType, HttpServletResponse res) {
		try {
			jcdMoManager.downloadCheckListRecord(applyId, taskType, res);
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改检查项内容
	 * @param model
	 * @param applyId
	 * @param jcmbid
	 * @param itemid
	 * @param itemcontent
	 */
	@RequestMapping(value = "/saveJcx.mo", produces = "application/json")
	public void saveJcx(ModelMap model, String applyId, String jcmbId, String itemId,String itemcontent,String orderby) {
		try {
			jcdManager.editJcx(applyId, jcmbId, itemId,itemcontent,orderby);
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
	@RequestMapping(value = "/hideJcx.mo", produces = "application/json")
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
     * 
     * 函数介绍：查询：执法对象的模板历史列表。
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/getJcmbRecordList.mo", produces = "application/json")
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
