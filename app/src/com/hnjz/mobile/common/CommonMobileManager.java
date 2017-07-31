package com.hnjz.mobile.common;

import java.util.HashMap;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		公共功能Manager层
 *
 */
public interface CommonMobileManager {

	/**
	 * 
	 * 函数介绍：获得任务类型树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray queryTasktypeTree(String lawobjtype, String markId);

	/**
	 * 
	 * 函数介绍：pid查询附件列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryFileList(String pid, String fileType, String page, String rows);
	
	/**
	 * 
	 * 函数介绍：查询任务办理过程中的附件列表
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	public JSONArray queryWorkBlFileList(String rwid);

	/**
	 * 
	 * 函数介绍：查询附件列表不带分页功能
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray queryFileList(String pid, String fileType);

	/**
	 * 
	 * 函数介绍：查询任务报告附件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray queryWorkBglxFileList(String rwid);

	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public HashMap<String, Object> getUserInfo(String id);

	public String savePas(String pass, String newPass, String confirmPass) throws AppException;

	/**
	 * 
	 * 函数介绍：根据任务类型获得办理附件类型编号
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String queryBlFileType(String rwlx);
}
