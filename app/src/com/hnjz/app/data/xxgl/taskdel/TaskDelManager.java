/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.taskdel;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 自动派发管理的manager
 * 
 * @author wumi
 * @version $Id: TaskDelManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface TaskDelManager extends Manager {

	/**
	 * 查询列表数据
	 * @param  
	 * @param  
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryTaskList(String areaid, String name, String hander, String endTimeFrom, String endTimeTo, String page,
			String pageSize,String rwzt,String zbrId);

	/**
	 * 删除自动派发数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void removeTask(String id);

}
