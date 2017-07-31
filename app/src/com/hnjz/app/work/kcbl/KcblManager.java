package com.hnjz.app.work.kcbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * 勘察笔录RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface KcblManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询勘察笔录form信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public KcblForm getKcblFormData(String applyId);
	
	/**
	 * 
	 * 函数介绍：查询勘察询问笔录问题
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List getKcxwblWtList(String applyId,String rwlx,String wflx,String kcxwbj);
	
	/**
	 * 
	 * 函数介绍：查询追加勘察询问笔录问题
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List getMoreKcxwblWtList(String applyId,String kcxwbj);
	/**
	 * 
	 * 函数介绍：删除勘察笔录及问题项
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void delKcbl(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存询问笔录及问题项
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveKcbl(KcblForm kcblForm, String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel);
	
	/**
	 * 
	 * 函数介绍：生成勘察笔录doc文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveShengchengKcbl(String applyId,String wflx);
	
	/**
	 * 
	 * 函数介绍：获取执法证号
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getLwnumber(String userIds);
	
	/**
	 * 得到勘察笔录问题项
	 * @param applyId
	 * @param wflx
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getBlwt(String applyId, String wflx);
	
	/**
	 * 得到所有已保存的监察笔录问题项及答案
	 * @param applyId
	 * @param kcxwbj
	 * @return
	 */
	public List getAllKcxwblWtList(String applyId,String kcxwbj);
	
	/**
	 * 根据任务获得违法类型 
	 * @param applyId
	 * @return
	 */
	public String getWflx(String applyId);
}
