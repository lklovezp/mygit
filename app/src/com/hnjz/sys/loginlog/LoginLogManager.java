/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.loginlog;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: LogManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface LoginLogManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryLoginLogList(String name, String username, String loginip, String logintype, String page, String pageSize);
	
	/**
	 * 
	 * 函数介绍：查询登录次数
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryLogTimesList(String areaid,String name, String starttime,String endtime, String page, String pageSize);
	
	
	/**
	 * 
	 * 函数介绍：登录次数导出
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void exportLogTimesList(String areaId,String name,String starttime,String endtime,HttpServletResponse res);
	/**
	 * 
	 * 函数介绍：查询登录次数详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryLogTimesListDetails(String id,String starttime,String endtime, String page, String pageSize);
	
	/**
	 * 首页：查询自己总登录次数
	 * @return
	 */
	public int queryZjAllLogTimesList();
	
	/**
	 * 首页：查询自己本月的登录次数
	 * @return
	 */
	public int queryZjTheMonthLogTimesList();
	
	/**
	 * 
	 * 首页：查询登录次数详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryLogTimesDetails(String bythemonth, String page, String pageSize);
	
}
