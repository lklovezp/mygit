/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysOrg;

/**
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: OrgManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
public interface OrgManager extends Manager {

	/**
	 * 根据用户ID获取用户的部门
	 */
	public TSysOrg getOrgByUserid(String userId);

	/**
	 * 获取某个部门下的所有部门,及本部门
	 * 
	 * @param orgId
	 *            部门id
	 * @return 某个部门下的所有部门
	 */
	public List<TSysOrg> getChOrgs(String orgId);

	/**
	 * 获取某个部门下的所有部门
	 * 
	 * @param orgId
	 *            部门ID
	 * @param orgs
	 *            递归时传入的结果
	 * @return
	 */
	void getChs(String orgId, List<TSysOrg> orgs);
	/**
	 * 获取某个部门下的所有部门
	 * 
	 * @param orgId
	 *            父级部门ID
	 * @return
	 */
	List<TSysOrg> getChs(String orgId);

	/**
	 * 获取部门
	 * 
	 * @param list
	 * @param orgid
	 * @param curOrgId
	 */
	void digui(List<TSysOrg> list, String orgid, String curOrgId);

	/**
	 * 根据部门Id获取部门
	 * 
	 * @param id
	 *            部门ID
	 * @return 用户
	 */
	public TSysOrg getOrg(String id);

	/**
	 * 查询出所有的部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray querySelectOrg(String id) throws Exception;
	
	/**
	 * admin查询出所有的部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray queryAdminOrg(String id, String areaid) throws Exception;

	/**
	 * 查询功能菜单
	 * @param isActive 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryOrg(String name, String isActive) throws Exception;

	/**
	 * 保存一个功能菜单
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void saveOrg(OrgForm frm) throws AppException;

	/**
	 * 删除部门信息
	 * 
	 * @param id
	 *            功能菜单信息的ID
	 */
	public void removeOrg(String id) throws AppException;

	/**
	 * 查询一个部门信息
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void orgInfo(OrgForm frm);
}
