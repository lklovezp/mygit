/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.timertask;

import java.util.List;
import java.util.Map;

import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;

/**
 * 自动派发管理的manager
 * 
 * @author wumi
 * @version $Id: TimerTaskManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface TimerTaskManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryTimerTask(String name, String accepter, String isActive, String page,
			String pageSize);

	/**
	 * 删除自动派发数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void removeTimerTask(String id);
	/**
	 * 获取一个自动派发的数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void timerTaskInfo(TimerTaskForm frm);

	/**
	 * 保存自动派发的数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void saveTimerTask(TimerTaskForm frm);

	/**
	 * 查询定时任务信息
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTimerTask(String id);

	List<TDataTimertask> getAllTimerTask();

	void execTimerTask(TDataTimertask po);

	public List<ComboboxTree> taskTypeExceptZX();

	public List<Combobox> getLawObjTypeByTaskType(String tasktype);
}
