package com.hnjz.app.tjbb;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;


/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
	统计排污单位数量
 *
 */
public interface StatisticalManager {
	
	/**
	 * 
	 * 函数介绍：统计排污单位数量列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult tjpwdwsl(String regionId, String lawobjtype, String page, String pageSize);

	/**
	 * 
	 * 函数介绍：导出统计列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void exportStatisticalList(String regionId, String lawobjtype, HttpServletResponse res);

}
