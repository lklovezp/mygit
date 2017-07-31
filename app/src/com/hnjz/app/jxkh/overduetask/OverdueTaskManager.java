package com.hnjz.app.jxkh.overduetask;

import com.hnjz.common.FyWebResult;


/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	逾期任务manager接口类
 *
 */
public interface OverdueTaskManager {
	
	/**
	 * 
	 * 函数介绍：查询逾期任务列表
	
	 * 输入参数：
	 * @param starttime:要求完成时限开始时间
	 * @param endtime:要求完成时限结束时间
	 * @param rwly:任务来源
	 * @param jjcd:紧急程度
	 * @param isComplete:是否完成
	 * @param rwmc:任务名称
	 * @param tasktype:任务类型
	
	 * 返回值：
	 */
	public FyWebResult queryOverdueTaskList(String areaid,String starttime,String endtime,String rwly,String jjcd,String isComplete,String rwmc,String tasktype,String page,String pageSize);
	/**
	 * 
	 * 函数介绍：查询逾期任务列表
	
	 * 输入参数：
	 * @param starttime:要求完成时限开始时间
	 * @param endtime:要求完成时限结束时间
	 * @param rwly:任务来源
	 * @param jjcd:紧急程度
	 * @param isComplete:是否完成
	 * @param rwmc:任务名称
	 * @param tasktype:任务类型
	
	 * 返回值：
	 */
	public FyWebResult queryYqrwByUser(String userid, String areaid,String starttime, String endtime, String rwly, String jjcd, String isComplete, String rwmc, String tasktype,String page,String pageSize);
}
