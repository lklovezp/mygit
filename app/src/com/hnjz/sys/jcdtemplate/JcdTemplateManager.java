/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 任务类型管理的manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface JcdTemplateManager extends Manager {

	/**
	 * 查询功能菜单
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryJcdTemplate(String name) throws Exception;
	/**
	 * 保存监察模板
	 * 
	 * @return 无
	 * @throws AppException
	 */
	public void saveTemplate(TemplateForm frm) throws AppException;
	/**
	 * 保存监察模板、版本
	 * 
	 * @return 无
	 * @throws AppException
	 */
	public void saveTemplateVersion(TemplateForm frm) throws AppException;
	/**
	 * 点击左侧监察模板树跳转页面接口
	 * 
	 * @return 页面路径
	 * @throws 
	 */
	public String treeClickJumpPage(TemplateForm frm);
	/**
	 * 点击左侧监察模板树跳转页面接口
	 * 
	 * @return 页面路径
	 * @throws 
	 */
	public void queryCheckListList(CheckItemForm frm);
	/**
	 * 保存检查项
	 * 
	 * @return 无
	 * @throws AppException
	 */
	public void saveCheckListItem(CheckItemForm frm) throws AppException;
	/**
	 * 查询检查项列表
	 * @param templateId 监察模板id
	 * @param page 当前页码
	 * @param pageSize 每页记录条数
	 * @throws 
	 */
	public FyWebResult queryCheckListItem(String templateId, String page,
			String pageSize);
	/**
	 * 查询答案列表
	 * @param itemId 检查项id
	 * @throws 
	 */
	public FyWebResult queryCheckItemAns(String itemId);
	/**
	 * 添加子模板跳转页面接口
	 * 
	 * @return
	 * @throws 
	 */
	public void addSubTemplate(TemplateForm frm);
	/**
	 * 查询版本信息
	 * 
	 * @return 页面路径
	 * @throws 
	 */
	public String editTemplateVersion(TemplateForm frm);
	/**
	 * 设置默认监察模板
	 * 
	 * @return 
	 * @throws 
	 */
	public void saveDefaultVersion(TemplateForm frm);
	
	/**
	 * 保存复制的版本
	 * 
	 * @throws 
	 */
	public void saveCopyVersion(TemplateForm frm) throws AppException;
	
	/**
	 * 保存子模板
	 * 
	 * @throws 
	 */
	public void saveSubTemplate(TemplateForm frm) throws AppException;
	
	/**
	 * 删除检查项
	 * 
	 * @throws 
	 */
	public void removeCheckListItme(CheckItemForm frm) throws AppException;
	
	/**
	 * 删除版本
	 * 
	 * @throws 
	 */
	public void removeVersion(TemplateForm frm) throws AppException;
	
	/**
	 * 删除子模板
	 * 
	 * @throws 
	 */
	public void removeSubTemplate(TemplateForm frm) throws AppException;
	
	/**
	 * @throws AppException 
	 * 删除模板
	 * 
	 * @throws 
	 */
	public void removeTemplate(TemplateForm frm) throws AppException;
	
	/**=====================================供外部使用的方法======================================*/
	
	/**
	 * 构建监察模板树
	 * 
	 * @param 模板id
	 * @return 树菜单json数据
	 */
	public JSONArray templateTree(String templateId) throws AppException;
	
	
	public void saveCopyTemplate(String name, String oldIndustry, String oldTaskType, String newIndustry, String newTaskType) throws AppException;
	
	public TDataChecklisttemplate queryTemplateWithNoId(TemplateForm frm);
	
	
	/**
	 * 获取某个模板下的所有子模板
	 * @param templateId
	 * @return
	 */
	public List<TDataChecklisttemplate> getChTemplates(String templateId);
	
	/**
	 * 根据检查模板id获取模板
	 * @param id
	 * @return
	 */
	public TDataChecklisttemplate getChecklisttemplate(String id);
	
}
