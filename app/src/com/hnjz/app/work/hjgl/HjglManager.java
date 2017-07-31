package com.hnjz.app.work.hjgl;

import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * 痕迹管理RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface HjglManager extends Manager{
	/**
	 * 
	 * 函数介绍：获取当前任务的痕迹(操作记录)
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Vhjgl> getRwhj(String rwid);
	
	/**
	 * 
	 * 函数介绍：获取当前任务的痕迹(派发转派操作记录)
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Vhjgl> getRwhj_pfzp(String rwid);

}
