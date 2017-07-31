package com.hnjz.app.work.jttlbl;

import com.hnjz.common.manager.Manager;

/**
 * 事先告知书Manager
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
public interface JttlblManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询勘察笔录form信息
	 * 输入参数：
	 * 返回值：
	 */
	public JttlblForm getJttlblForm(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存勘察笔录及问题项
	 * 输入参数：
	 * 返回值：
	 */
	public void saveJttlbl(JttlblForm jttlblForm,String applyId,String wflx);
	
	/**
	 * 
	 * 函数介绍：生成勘察笔录doc文件
	 * 输入参数：
	 * 返回值：
	 */
	public void saveShengchengXwbl(String applyId,String wflx);
	
}
