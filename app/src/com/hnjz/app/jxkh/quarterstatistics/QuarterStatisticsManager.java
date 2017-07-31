package com.hnjz.app.jxkh.quarterstatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按季度统计manager接口类
 *
 */
public interface QuarterStatisticsManager {

	/**
	 * 
	 * 函数介绍：按季度统计
	
	 * 输入参数：
	 * @param orgid:部门id
	 * @param starttime:要求完成时间
	 * @param endtime:要求完成时间
	
	 * 返回值：
	 */
	public List<QuarterForm> statisticsQuarterList(String areaid, String orgid, String starttime, String endtime);

	/**
	 * 
	 * 函数介绍：统计现场执法情况
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public QuarterZfqkForm queryQuarterZfqk(String areaid,String orgid, String starttime, String endtime);

	/**
	 * 
	 * 函数介绍：导出统计结果
	
	 * 输入参数：year-年，quarter-季度，orgid-部门id，orgname-部门名称，starttime-要求完成时限开始时间，endtime-结束时间
	
	 * 返回值：
	 */
	public void exportQuarterStatistics(String year, String quarter, String areaid,String orgid, String orgname, String starttime, String endtime, HttpServletResponse res);
}
