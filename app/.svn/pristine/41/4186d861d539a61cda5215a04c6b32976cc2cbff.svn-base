/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.function.FunForm;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserPosition;

/**
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: OrgManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
public interface OrgManager extends Manager {

	/**
	 * 根据用户ID获取用户的职位，{@link UserPosition}
	 * 
	 * @param userId
	 *            用户id
	 * @return {@link UserPosition}
	 */
	public UserPosition getPosition(String userId);
	
	/**
	 * 根据用户ID获取用户的部门
	 */
	public TSysOrg getOrgByUserid(String userId);

	/**
	 * 根据部门和用户获取用户的职位
	 * 
	 * @param org
	 * @param userId
	 * @return
	 */
	UserPosition getPosition(TSysOrg org, String userId);

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
	 * 跨级派发，转派时，获取出需要补全的用户
	 * 
	 * @param pfrId
	 *            派发人id(当前用户),为null表示从总队长开始
	 * @param jsrId
	 *            接收人id(任务接受人id)
	 * @return
	 */
	public List<TSysUser> getUsers(String pfrId, String jsrId, boolean isXp);

	/**
	 * 获取部门
	 * 
	 * @param list
	 * @param orgid
	 * @param curOrgId
	 */
	void digui(List<TSysOrg> list, String orgid, String curOrgId);

	/**
	 * 获取用户的主管
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户的主管
	 */
	public TSysUser getLeaderByUser(String userId);

	/**
	 * 根据部门获取部门的主管
	 * 
	 * @param orgId
	 *            部门id
	 * @return 部门的主管
	 */
	public TSysUser getLeaderByOrg(String orgId);

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
	public JSONArray querySelectOrg(String id, String areaid) throws Exception;
	
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
	 * 系统管理员可以更新用户所属的区域为所在部门的区域
	 * 
	 * @throws AppException
	 */
	public void saveUserArea() throws AppException;

	/**
	 * 查询一个部门信息
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void orgInfo(OrgForm frm);
	/**
	 * 根据部门ID查询当前部门的所有人员
	 * 
	 * @param orgId
 	 */
	public List<TSysUser> queryUsersByOrgId(String orgId) throws Exception;
	/**
	 * 根据区域获取区域的支队长
	 * */
	public TSysUser getLeaderByAreaId(String areaid)throws Exception;
}
