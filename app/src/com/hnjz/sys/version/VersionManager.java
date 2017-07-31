/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: VersionManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface VersionManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryVersion(String code, String name, String isActive, String page, String pageSize);

	/**
	 * 编辑一个版本信息
	 * 
	 * @param frm 版本表单
	 * @return 无
	 */
	public void versionInfo(VersionForm frm);
	
	/**
	 * 保存一个版本信息
	 * @param file 
	 * @param request 
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm 版本的表单
	 * @return 版本的初始界面
	 */
	public void saveVersion(VersionForm frm, MultipartFile file, HttpServletRequest request);

	/**
	 * 删除一个版本信息
	 * 
	 * @param id 版本id
	 * @return 无
	 */
	public void removeVersion(String id);

	/**
	 * 下载安装包
	 * 
	 * @param id 版本id
	 * @return 无
	 */
	public void downloadVersion(String id, HttpServletResponse res);
	
	/**
	 * 取得软件版本
	 * 
	 * @param 
	 * @return 无
	 */
	public String getVersion();

	/**
	 * 取得软件版本
	 * 
	 * @param 
	 * @return 无
	 */
	public HashMap<String, Object> getVersionMo();
	
	/**
	 * 下载帮助文档
	 * 
	 * @return 无
	 */
	public void downApk(HttpServletResponse res) throws AppException;

	/**
	 * 下载手机端
	 * 
	 * @return 无
	 * @throws AppException 
	 */
	public void downMoblieApkApk(HttpServletResponse res) throws AppException;

	/**
	 * 获取手机端版本
	 * 
	 * @return 无
	 * @throws AppException 
	 */
	public HashMap<String, Object> getMoblieVersionMo();

}
