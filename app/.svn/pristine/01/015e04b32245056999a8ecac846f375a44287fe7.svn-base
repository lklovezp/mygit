/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * 抽查比例设定Manager
 * @author shiqiuhan
 * @created 2015-12-16,下午02:19:38
 */
public interface CheckProportionManager extends Manager {

	/**
	 * 查询季度抽查比例
	 * 
	 * @param frm
	 *            搜索条件，可以按年份、季度、抽查比例搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 季度抽查比例列表
	 * @throws Exception
	 */
	public FyWebResult queryCheckProportion(CheckProportionForm frm, String page, String pageSize)
			throws Exception;

	/**
	 * 保存季度抽查比例设置
	 * 
	 * @param checkProportionForm
	 *            {@link checkProportionForm}
	 */
	@Transactional(readOnly = false)
	public void saveCheckProportion(CheckProportionForm frm) throws AppException;

	/**
	 * 删除季度抽查比例设置信息
	 * 
	 * @param id
	 *            季度抽查比例设置信息的ID
	 */
	public void removeCheckProportion(String id) throws AppException;
	
	/**
	 * 
	 * 函数介绍：季度下拉列表
	 */
	public List<Combobox> queryQuarterList();
	
	/**
	 * 根据年份和季度查找抽查比例
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 *            
	 */
	public int queryProportion(String year, String quarter);
}
