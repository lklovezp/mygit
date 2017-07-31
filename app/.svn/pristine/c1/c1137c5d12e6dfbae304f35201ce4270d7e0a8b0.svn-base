/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * 区域管理的manager
 * 
 * @author wumi
 * @version $Id: IllegalTypeManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface IllegalTypeManager extends Manager {

	/**
	 * 查询功能菜单
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryIllegalType() throws Exception;
	/**
	 * 保存违法类型
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void saveIllegalType(IllegalTypeForm frm) throws AppException;
	/**
	 * 获取违法类型信息
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray IllegalTypeInfo(IllegalTypeForm frm);
	/**
	 * 删除违法类型
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void removeIllegalType(String id) throws AppException;

}
