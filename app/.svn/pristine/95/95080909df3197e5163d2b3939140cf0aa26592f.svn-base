package com.hnjz.app.work.zxzz;

import java.util.List;
import java.util.Map;

import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 在线制作RwglManager
 * 作者：zhangqingfeng
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface ZxzzManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：保存询问笔录及问题项
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveXwbl(ZxzzForm zxzzForm, String applyId, String wflx);
	
	/**
	 * 
	 * 函数介绍：生成询问笔录doc文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveShengchengXwbl(String applyId,String wflx);

	public List<TBizAutomoniter> getList(String applyId, String mblx);

	FyWebResult queryZxzzFileList(String pid, String canDel, String fileType, String page, String rows);

	public String getKzlx(String kzlx);
	
	/**
	 * 
	 * 函数介绍：每次生成模板记录次数
	 * 输入参数：applyId:任务id;
	 * 返回值：
	 */
	public void saveBlmbcs(String taskId, String tmplateId, String fileId, String fileCount);

	/**
	 * 
	 * 函数介绍：获取在线制作模板
	 * 输入参数：applyId:任务id;bllx:笔录类型;
	 * 返回值：
	 */
	public List<Map<String, String>> getZxzzFiles(String applyId,String bllx);

	/**
	 * 
	 * 函数介绍：获取在线制作的合并文档
	 * 输入参数：applyId:任务id;bllx:笔录类型;
	 * 返回值：
	 */
	public String saveCopyFile(String applyId, String bllx);
	
}
