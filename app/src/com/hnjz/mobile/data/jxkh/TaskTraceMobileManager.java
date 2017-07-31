package com.hnjz.mobile.data.jxkh;

import com.hnjz.common.FyWebResult;


/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	任务痕迹管理manager接口类
 *
 */
public interface TaskTraceMobileManager {
	
	/**
	 * 
	 * 函数介绍：任务痕迹管理
	
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
	public FyWebResult queryTaskTraceList(String areaid,String rwmc,String starttime,String endtime,String tasktype,String czlx,String page,String pageSize);

}
