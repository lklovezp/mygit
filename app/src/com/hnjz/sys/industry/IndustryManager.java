/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.industry;

import java.util.List;

import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: IndustryManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface IndustryManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryIndustry(String name, String type, String isActive, String page,
			String pageSize);

	/**
	 * 删除行业数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void removeIndustry(String id);
	/**
	 * 获取一个行业的数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void industryInfo(IndustryForm frm);

	/**
	 * 保存行业的数据
	 * 
	 * @return 无
	 * @throws Exception
	 */
	public void saveIndustry(IndustryForm frm) throws AppException;
	
	public List<TDataIndustry> getIndustry(String lawobjid);

}
