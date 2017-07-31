/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;


import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysArea;

/**
 * 区域管理的manager
 * 
 * @author wumi
 * @version $Id: AreaManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface AreaManager extends Manager{

	/**
	 * 根据用户id获取用户所属部门的区域
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户所属部门的区域
	 */
	public TSysArea getAreaByUser(String userId);

	/**
	 * 查询出所有的部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray querySelectArea(String id) throws Exception;

	/**
	 * 查询功能菜单
	 * @param isActive 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryArea(String name, String isActive) throws Exception;

	/**
	 * 保存区域
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public void saveArea(AreaForm frm) throws AppException;

	/**
	 * 获取单个区域区域
	 * 
	 * @param id
	 *            区域的ID
	 */
	public void areaInfo(AreaForm frm);
	
	/**
	 * 删除区域
	 * 
	 * @param id
	 *            区域的ID
	 */
	public void removeArea(String id) throws AppException;
	
	
	/**
	 * 根据用户区域id获取上级区域服务器地址
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户所属部门的区域
	 */
	public String getPAreaServerByAreaId(String areaId) throws AppException;
	
	/**
	 * 根据用户区域id获取上级区域id
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户所属部门的区域
	 */
	public String getPAreaIdByAreaId(String areaId) throws AppException;

	/**
	 * 根据部门获得区域
	 * @param o
	 * @return
	 */
	public TSysArea getAreaByOrgid(String oid);

	/**
	 * 获取某个区域下的所有区域
	 * @param areaId
	 * @return
	 */
	public List<TSysArea> getChAreas(String areaId);
}
