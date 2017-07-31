/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.jcd;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 检查单的manager
 * 
 * @author wumi
 * @version $Id: JcdManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface JcdManager extends Manager {

	/**
	 * 获取检查项
	 * @param jcmbId
	 * @param applyId
	 * @return
	 * @throws AppException
	 */
	JSONArray getJcx(String jcmbId,String applyId) throws AppException;

	/**
	 * 暂存单个检查项
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param itemcontent
	 * @param type
	 * @param answer
	 * @param beizhu
	 * @param orderby
	 * @throws AppException
	 */

	void saveTemporary(String applyId, String jcmbId, String itemId,String itemcontent,
			String type, String answer, String beizhu, String orderby) throws AppException;
	
	/**
	 * 修改检查项内容
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param itemcontent
	 * @param orderby
	 */
	void editJcx(String applyId, String jcmbId, String itemId,String itemcontent,String orderby);
	
	/**
	 * 获取检查项内容
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @return
	 */
	String getJcxcontent(String applyId, String jcmbId, String itemId);

	/**
	 * 保存用户填写的检查单 
	 * 可用于保存全部检查项和保存单个子模板检查项
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 * @throws AppException
	 */
	void saveCheckList(String applyId, String jcmbId, String subdata) throws AppException;

	/**
	 * 获取检查记录 回显到页面
	 * @param applyId
	 * @param jcmbId
	 * @param taskType 
	 * @return
	 * @throws AppException
	 */
	JSONArray getCheckRecord(String applyId, String superJcmbId, String jcmbId, String taskType) throws AppException;
	
	/**
	 * 获取历史检查模板记录 回显到页面
	 * @param superJcmbId
	 * @param workId
	 * @return
	 */
	public JSONArray getHistoryRecord(String superJcmbId, String workId) throws JSONException;

	/**
	 * 生成检查记录文档
	 * @param applyId 任务id
	 * @param jcmbId 
	 * @param subdata 
	 * @throws AppException
	 */
	HashMap<String, Object> buildCheckListRecord(String applyId, String taskType, String jcmbId) throws Exception;

	/**
	 * 下载检查记录文档
	 * @param applyId 任务id
	 * @param res 
	 * @throws AppException
	 */
	void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException;

	/**
	 * 生成监察报告并下载
	 * @param applyId
	 * @param taskType
	 * @param jcmbId
	 * @param jcqk 
	 * @param fileid
	 * @throws AppException
	 */
	void buildJcbl(String applyId, String taskType, String jcmbId, String jcqk,String fileid) throws Exception;

	/**
	 * 检查是否生成过检查记录
	 * @param applyId
	 * @param taskType
	 */
	boolean checkJcdExists(String applyId, String taskType) throws AppException;
	
	/**
	 * 隐藏检查项
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param orderby
	 * @throws AppException
	 */
	void hideJcx(String applyId,String jcmbId, String itemId, String orderby) throws AppException;

	/**
	 * 
	 * 函数介绍：查询：检查模板的历史记录列表。
	 * 输入参数：
	 * 返回值：FyWebResult
	 */
	public FyWebResult getJcmbRecordList(String applyId, String jcmbId, String page, String pageSize)throws Exception;

}
