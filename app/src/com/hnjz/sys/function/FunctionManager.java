/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.function;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysFunc;

/**
 * 功能菜单的数据
 * 
 * @author wumi
 * @version $Id: FunctionManager.java, v 0.1 Jan 8, 2013 10:15:57 AM wumi Exp $
 */
public interface FunctionManager extends Manager{

	/**
	 * 选择功能菜单公共页面的查询
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	/**
	 * @param id 
	 */
	public JSONArray querySelectFunction(String id) throws Exception;

	/**
	 * 查询功能菜单
	 * @param isActive 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryFunction(String name, String isActive) throws Exception;

	/**
	 * 保存一个功能菜单
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void saveFunction(FunForm frm) throws AppException;

	/**
	 * 删除功能菜单信息
	 * 
	 * @param id
	 *            功能菜单信息的ID
	 */
	public void removeFunction(String id) throws AppException;

	/**
	 * 查询当前菜单有那些操作
	 * 
	 * @return 当前菜单有那些操作
	 * @throws Exception
	 */
	public List<FunOp> queryOptByFunction(String id) throws Exception;

	/**
	 * 查询当前菜单有那些操作
	 * 
	 * @return 当前菜单有那些操作
	 * @throws Exception
	 */
	public TSysFunc queryFunByRepid(String reportid) throws Exception;
}
