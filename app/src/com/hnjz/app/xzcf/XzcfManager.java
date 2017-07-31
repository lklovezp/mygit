package com.hnjz.app.xzcf;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 任务管理RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
包括：任务派发、已办任务、待办任务
 *
 */
public interface XzcfManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询：待办任务列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getXzcfList(String rwmc,String rwly,String pfrId,String rwzt, String tasktype, String lawobjId, String page, String pageSize)throws Exception;

}
