package com.hnjz.app.jxkh.userstatistical;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.app.jxkh.orgstatistics.StatisticsForm;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按人员统计manager接口类
 *
 */
public interface StatisticsUserManager extends Manager{

	/**
	 * 
	 * 函数介绍：按人员统计
	 * 输入参数：
	 * @param tasktypeid:任务类型id
	 * @param rwly:任务来源编号
	 * @param jjcd:紧急程度编号
	 * @param starttime:归档开始时间
	 * @param endtime:归档结束时间
	
	 * 返回值：
	 */
	public List<StatisticsForm> statisticsUserList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * 函数介绍：导出统计列表
	 * 输入参数：
	 * 返回值：
	 */
	public void exportStatisticsUserList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);

	/**
	 * 
	 * 函数介绍：查询统计任务列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryStatisticalUserInfo(String userid, String areaid, String orgid, String type, String tasktypeid, String rwly, String jjcd, String starttime, String endtime, String page, String pageSize);

	/**
	 * 
	 * 函数介绍：导出统计任务列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void exportStatisticalUserInfoList(String userid, String username,String areaid, String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname,
			String starttime, String endtime, HttpServletResponse res);
	
	/**
	 * 首页：查询已下达已完成的任务数
	 * @return
	 */
	public StatisticsForm statisticsRwCount();
	
	/**
	 * 首页：查看任务详情
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public FyWebResult queryRwInfo(String type,String tasktype,String page,String pageSize);

	/**
	 * 
	 * 函数介绍：按人员统计监察报告份数
	 * 输入参数：
	 * @param starttime:归档开始时间
	 * 返回值：
	 */
	public List<StatisticsDocForm> statisticsDocByUserList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * 函数介绍：导出按人员统计监察笔录份数列表
	 * 输入参数：
	 * 返回值：
	 */
	public void exportStatisticsDocByUserList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);
	
}
