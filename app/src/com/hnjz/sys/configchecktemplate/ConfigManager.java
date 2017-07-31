/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configchecktemplate;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @config $Id: ConfigManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface ConfigManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryConfig(String code, String name, String isexecchecklist, String page, String pageSize);

	/**
	 * 编辑一个版本信息
	 * 
	 * @param frm 版本表单
	 * @return 无
	 */
	public void configInfo(ConfigForm frm);
	
	/**
	 * 保存一个版本信息
	 * @param file 
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm 版本的表单
	 * @return 版本的初始界面
	 */
	public void saveConfig(ConfigForm frm, MultipartFile file);

	/**
	 * 删除一个版本信息
	 * 
	 * @param id 版本id
	 * @return 无
	 */
	public void removeConfig(String id);

	/**
	 * 
	 * 函数介绍：
	根据任务类型+执法对象类型 查询监察模板信息
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, String> queryJcmbInfo(String tasktypeid, String lawobjtype);

	/**
	 * 下载上传的检查摸板
	 * @param id
	 * @param res
	 */
	public void downloadCheckTemplate(String id, HttpServletResponse res);

}
