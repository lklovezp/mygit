/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.function.FunForm;

/**
 * 角色管理类
 * 
 * @author wumi
 * @version $Id: RoleManager.java, v 0.1 Dec 28, 2011 9:22:27 AM Administrator
 *          Exp $
 */
public interface RoleManager extends Manager {

	/**
	 * 查询出所有有效的角色列表
	 * 
	 * @return 所有有效的角色列表
	 * @throws Exception
	 */
	public List<Combobox> queryRole() throws Exception;

	/**
	 * 查询角色
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @param pageSize2 
	 * @return 角色列表
	 * @throws Exception
	 */
	public FyWebResult queryRole(String name, String isActive, String page, String pageSize)
			throws Exception;

	/**
	 * 保存一个功能菜单
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	@Transactional(readOnly = false)
	public void saveRole(RoleForm frm) throws AppException;

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 *            角色信息的ID
	 */
	public void removeRole(String id) throws AppException;
}
