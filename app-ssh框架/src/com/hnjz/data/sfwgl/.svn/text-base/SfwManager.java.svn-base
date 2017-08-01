/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.data.sfwgl;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.data.po.TDataFileInfo;

/**
 * 收发文管理的Manager
 * @author 时秋寒
 *
 */
public interface SfwManager extends Manager {
	/**
	 * 查询收发文列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 收发文列表
	 * @throws Exception
	 */
	public FyWebResult querySfw(String title, String starttime,String endtime, String type,String sourcepid,String sourceid,String number, String isActive, String page, String pageSize)
			throws Exception;

	/**
	 * 保存一个收发文信息
	 * 
	 * @param sfwForm
	 *            {@link SfwForm}
	 */
	@Transactional(readOnly = false)
	public TDataFileInfo saveSfw(SfwForm frm) throws AppException, ParseException;

	/**
	 * 删除收发文信息
	 * 
	 * @param id
	 *            收发文信息的ID
	 */
	public void removeSfw(String id) throws AppException;
	/**
	 * 
	 * 函数介绍：收发文类型下拉列表
	 */
	public List<Combobox> querySfwlxList();
	
}
