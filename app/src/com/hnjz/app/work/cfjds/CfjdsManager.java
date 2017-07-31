package com.hnjz.app.work.cfjds;

import com.hnjz.common.manager.Manager;

/**
 * 行政处罚决定书Manager
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
public interface CfjdsManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询勘察笔录form信息
	 * 输入参数：
	 * 返回值：
	 */
	public CfjdsForm getCfjdsFormData(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存勘察笔录及问题项
	 * 输入参数：
	 * 返回值：
	 */
	public void saveSxgzs(CfjdsForm cfjdsForm,String applyId,String wflx);
	
	/**
	 * 
	 * 函数介绍：生成勘察笔录doc文件
	 * 输入参数：
	 * 返回值：
	 */
	public void saveShengchengSxgzs(String applyId,String wflx);
	
}
