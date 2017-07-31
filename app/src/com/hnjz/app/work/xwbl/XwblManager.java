package com.hnjz.app.work.xwbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * 询问笔录RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface XwblManager extends Manager{
	
	/**
	 * 
	 * 函数介绍：查询询问笔录form信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public XwblForm getXwblFormData(String applyId,String wflx);
	
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
	public ArrayList<HashMap<String, String>> getMoreKcxwblWtList(String applyId,String kcxwbj);
	
	/**
	 * 
	 * 函数介绍：保存询问笔录及问题项
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveXwbl(XwblForm xwblForm, String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel);
	
	/**
	 * 
	 * 函数介绍：生成询问笔录doc文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveShengchengXwbl(String applyId,String wflx);
	
	/**
	 * 
	 * 函数介绍：保存修改问题
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String saveWT(String applyId,String wtid,String wtcontent);

	public ArrayList<HashMap<String, String>> getBlwt(String applyId, String wflx);

}
