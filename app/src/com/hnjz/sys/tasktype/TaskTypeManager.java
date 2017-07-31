/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.tasktype;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;

/**
 * 区域管理的manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface TaskTypeManager extends Manager {

	/**
	 * 查询功能菜单
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryTaskType() throws Exception;
	/**
	 * 保存任务类型
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void saveTaskType(TaskTypeForm frm);
	/**
	 * 获取任务类型信息
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray taskTypeInfo(TaskTypeForm frm);
	/**
	 * 删除任务类型
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void removeTaskType(String id);
	/**
	 * 查询任务类型下拉树 
	 * @param id 
	 * 
	 * @return ComboboxTree value : id; text:name
	 * @throws Exception
	 */
	List<ComboboxTree> queryTaskTypeIdTree(String id);
	public List<ComboboxTree> queryTaskTypeCodeTree(String id);
	
	/**
	 * 根据任务类型code获取任务类型
	 * @param code
	 * @return
	 */
	public TDataTasktype getTaskTypeByCode(String code);
	

}
