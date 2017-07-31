package com.hnjz.app.jxkh.orgstatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按部门统计manager接口类
 *
 */
public interface StatisticsOrgManager extends Manager{

	/**
	 * 
	 * 函数介绍：按部门统计
	 * 输入参数：
	 * @param tasktypeid:任务类型id
	 * @param rwly:任务来源编号
	 * @param jjcd:紧急程度编号
	 * @param starttime:归档开始时间
	 * @param endtime:归档结束时间
	
	 * 返回值：
	 */
	public List<StatisticsForm> statisticsOrgList(String areaid,String tasktypeid,String rwly,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * 函数介绍：导出按部门统计列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void exportStatisticalOrgList(String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);

	/**
	 * 
	 * 函数介绍：统计详情-任务列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryStatisticalOrgInfo(String areaid,String orgid,String type, String tasktypeid, String rwly, String jjcd, String starttime, String endtime, String page, String pageSize);

	/**
	 * 
	 * 函数介绍：获取任务执行过程分析界面基本数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public RwzxgcfxForm getRwzxgcfxFormByRwid(String id);
	
	/**
	 * 
	 * 函数介绍：获得单个任务耗时最长的阶段（任务逾期阶段）
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	public StageAnalysis getMaxStageAnalysis(String id);

	/**
	 * 
	 * 函数介绍：查询单个任务阶段分析
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	public List<StageAnalysis> querystageAnalysisList(String id);

	/**
	 * 
	 * 函数介绍：获取逾期阶段
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	public StageAnalysis getYqStageAnalysis(String id);

	/**
	 * 
	 * 函数介绍：导出统计（任务列表）数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void exportStatisticalOrgInfoList(String areaid,String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname, String starttime,
			String endtime, HttpServletResponse res);
	
	/**
	 * 
	 * 函数介绍：按部门统计
	 * 输入参数：
	 * @param tasktypeid:任务类型id
	 * @param rwly:任务来源编号
	 * @param jjcd:紧急程度编号
	 * @param starttime:归档开始时间
	 * @param endtime:归档结束时间
	
	 * 返回值：
	 */
	public List<StatisticsDocForm> statisticsDocByOrgList(String areaid,String tasktypeid,String rwly,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * 函数介绍：导出统计（监察笔录份数列表）数据
	 * 输入参数：
	 * 返回值：
	 */
	public void exportStatisticalDocInfoList(String areaid,String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname, String starttime,
			String endtime, HttpServletResponse res);
	
}
