package com.hnjz.app.work.jaspb;

import com.hnjz.common.manager.Manager;

/**
 * 行政处罚案件结案审批表的Manager
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
public interface JaspbManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询勘察笔录form信息
	 * 输入参数：
	 * 返回值：
	 */
	public JaspbForm getSxgzsFormData(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存勘察笔录及问题项
	 * 输入参数：
	 * 返回值：
	 */
	public void saveSxgzs(JaspbForm jaspbForm,String applyId,String wflx);
	
	/**
	 * 
	 * 函数介绍：生成勘察笔录doc文件
	 * 输入参数：
	 * 返回值：
	 */
	public void saveShengchengSxgzs(String applyId,String wflx);
	
}
