package com.hnjz.app.work.zx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * 专项任务办理ZxWorkManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface ZxWorkManager extends Manager{
	
	/**
	 * 
	 * 获取专项办理数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public BlZxxdForm getBlZxxdFormData(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存办理
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZxWorkzxBL(String applyId,BlZxxdForm blZxxdForm);
	
	/**
	 * 
	 * 函数介绍：专项子任务table（办理）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> zxZfdxTableData(String applyId);
	
	/**
	 * 
	 * 函数介绍：专项子任务table（办理）未分派
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> zxZfdxTableData_wfp(String applyId);
	
	/**
	 * 
	 * 函数介绍：分派任务
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx)throws Exception;
	
	public void saveLog(TSysUser czr, Date czsj, WorkLogType opType, WorkState state, Work work,Date startTime);
	
	/**
	 * 
	 * 获取专项子任务办理主窗口数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public BlZxxdZrwMainForm getBlZxxdZrwMainFormData(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存办理
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZxzrw_zxPage(String applyId,BlZxxdZrwMainForm blZxxdZrwMainForm);
	
	/**
	 * 
	 * 函数介绍：办理完毕
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZxzrw_zxPageBlwb(String applyId,String taskId)throws Exception;
	
	/**
	 * 
	 * 函数介绍：验证"办理"【true、通过；false、不通过】
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ResultBean checkZxBlBL(String applyId);
	
	/**
	 * 
	 * 函数介绍：验证子任务"办理"【true、通过；false、不通过】
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ResultBean checkZxZrwBlBL(String applyId);
	
	/**
	 * 
	 * 函数介绍：专项子任务办理完毕附件打个zip包，保存到父任务附件中去。
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZxzrw_zip(String applyId);
	
}
