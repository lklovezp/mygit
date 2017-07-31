/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.record;

import org.springframework.ui.ModelMap;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: RecordManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface RecordManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryRecord(String wflx, String content, String iscurver, String kcxwbj, String isActive, String page, String pageSize);

	/**
	 * 编辑一个笔录信息
	 * 
	 * @param frm 笔录问题表单
	 * @return 无
	 */
	public void recordInfo(RecordForm frm);
	
	/**
	 * 保存一个笔录信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param VersionForm 笔录问题的表单
	 * @return 笔录的初始界面
	 */
	public void saveRecord(RecordForm frm);

	/**
	 * 删除一个笔录信息
	 * 
	 * @param id 笔录id
	 * @return 无
	 */
	public void removeRecord(String id) throws AppException;

}
