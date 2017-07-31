/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.quarterChecktimeSet;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;

/**
 * 季度抽查时间设置的Manager
 * @author shiqiuhan
 * @created 2016-3-17,下午03:03:07
 */
public interface QuarterChecktimeSetManager extends Manager {

	/**
	 * 查询季度抽查时间
	 * 
	 * @param frm
	 *            搜索条件，可以按年份、季度搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 季度抽查时间列表
	 * @throws Exception
	 */
	public FyWebResult queryQuarterChecktimeSet(QuarterChecktimeSetForm frm, String page, String pageSize)
			throws Exception;
	
	/*
	 * 查询所有未执行抽查的时间
	 */
	public List<TDataQuarterChecktimeSet> queryAllNoexecutedTime();

	/**
	 * 保存季度抽查时间设置
	 * 
	 * @param quarterChecktimeSetForm
	 *            {@link quarterChecktimeSetForm}
	 */
	@Transactional(readOnly = false)
	public void saveQuarterChecktimeSet(QuarterChecktimeSetForm frm) throws AppException,ParseException;

	/**
	 * 删除季度抽查时间设置信息
	 * 
	 * @param id
	 *            季度抽查时间设置信息的ID
	 */
	public void removeQuarterChecktimeSet(String id) throws AppException;
	
	/**
	 * 
	 * 函数介绍：季度下拉列表
	 */
	public List<Combobox> queryQuarterList();
	
	/**
	 * 根据年份和季度查找抽查时间
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 *            
	 */
	public String queryTime(String year, String quarter,String areaid);
}
