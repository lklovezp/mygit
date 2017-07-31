package com.hnjz.app.jxkh.areastatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-08-31
 * 功能描述：按区域统计manager接口类
 *
 */
public interface StatisticsAreaManager extends Manager{

	/**
	 * 
	 * 函数介绍：按区域统计
	 * 输入参数：
	 * @param starttime:归档开始时间
	 * @param endtime:归档结束时间
	
	 * 返回值：
	 */
	public List<StatisticsDocForm> statisticsDocByAreaList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * 函数介绍：导出按区域统计监察笔录份数列表
	 * 输入参数：
	 * 返回值：
	 */
	public void exportStatisticsDocByAreaList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);
	
}
