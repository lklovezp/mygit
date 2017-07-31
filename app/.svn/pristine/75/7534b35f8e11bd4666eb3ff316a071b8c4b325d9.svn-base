package com.hnjz.app.data.xxcx;

import org.json.JSONArray;

import com.hnjz.common.FyWebResult;


/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
	归档任务查询
 *
 */
public interface QueryGdTaskManager {
	
	/**
	 * 
	 * 函数介绍：查询归档任务列表
	
	 * 输入参数：
	 * @param lawobjtype:执法对象类型
	 * @param tasktype:任务类型
	 * @param zbOrgId:主办部门
	 * @param regionId:所属行政区
	 * @param zbUsername:主办用户名
	 * @param rwly:任务来源
	 * @param page:当前页默认1
	 * @param pagesize:每页显示条数
	
	 * 返回值：
	 */
	public FyWebResult queryGdrwList(String taskname,String lawobjtype,String tasktype,String areaid, String zbOrgId,String regionId,String lawobjname,String zbUserId,String rwly,String starttime,String endtime,String yqwcStarttime,String yqwcEndtime,String page,String pageSize);

	/**
	 * 
	 * 函数介绍：终端查询归档任务
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryGdrwListForMobile(String lawobjtype, String tasktype, String workname, String lawobjname, String regionId, String zbUsername, String starttime, String endtime, String page,
			String pageSize);

	/**
	 * 
	 * 函数介绍：获得终端任务详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray getTaskInfoForMobile(String id);

	/**
	 * 
	 * 函数介绍：查询任务相关附件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryWorkFileList(String pid, String page, String pageSize);
}
