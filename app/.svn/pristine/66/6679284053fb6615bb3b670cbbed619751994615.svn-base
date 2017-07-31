/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.jcd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.work.po.TBizChecklist;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * 检查单的manager
 * 
 * @author wumi
 * @version $Id: JcdManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface JcdMoManager extends Manager {

	/**
	 * 获取检查项
	 * @param jcmbId 监察模板id
	 * @return
	 * @throws AppException
	 */
	List<HashMap<String, Object>> getJcx(String applyId,String selectTaskid, String templateId,String jcmbId) throws AppException;
	
	/**
	 * 暂存单个检查项
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param type
	 * @param answer
	 * @param beizhu
	 * @param orderby
	 * @throws AppException
	 */
	void saveTemporary(String applyId, String jcmbId, String itemId,
			String type, String answer, String beizhu, String orderby) throws AppException;

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
	 * 下载检查记录文档
	 * @param applyId 任务id
	 * @param res 
	 * @throws AppException
	 */
	void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException;

	/**
	 * 构建检查单树
	 * @param templateId
	 */
	HashMap<String, Object> templateTree(String uuid, String templateId) throws AppException;
	
	
	public HashMap<String, Object> getNextCheckItem(String uuid, String applyId, String selectTaskid, String templateId, String jcmbId) throws AppException;

	/**
	 * 找出一个任务的所有已填写的检查项
	 * @param applyId
	 * @return
	 */
	List<TBizChecklist> getAllRecordOfTask(String applyId);

	/**
	 * 获取一个监察模板下的所有检查项
	 * @param jcmbId
	 * @return
	 */
	List<TDataChecklistitem> getAllCheckItems(String uuid, String jcmbId);

	List<HashMap<String, Object>> getJcxForCheck(String applyId, String jcmbId);
}
