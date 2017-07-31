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
 * ��鵥��manager
 * 
 * @author wumi
 * @version $Id: JcdManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface JcdMoManager extends Manager {

	/**
	 * ��ȡ�����
	 * @param jcmbId ���ģ��id
	 * @return
	 * @throws AppException
	 */
	List<HashMap<String, Object>> getJcx(String applyId,String selectTaskid, String templateId,String jcmbId) throws AppException;
	
	/**
	 * �ݴ浥�������
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
	 * �����û���д�ļ�鵥 
	 * �����ڱ���ȫ�������ͱ��浥����ģ������
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 * @throws AppException
	 */
	void saveCheckList(String applyId, String jcmbId, String subdata) throws AppException;

	/**
	 * ���ؼ���¼�ĵ�
	 * @param applyId ����id
	 * @param res 
	 * @throws AppException
	 */
	void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException;

	/**
	 * ������鵥��
	 * @param templateId
	 */
	HashMap<String, Object> templateTree(String uuid, String templateId) throws AppException;
	
	
	public HashMap<String, Object> getNextCheckItem(String uuid, String applyId, String selectTaskid, String templateId, String jcmbId) throws AppException;

	/**
	 * �ҳ�һ���������������д�ļ����
	 * @param applyId
	 * @return
	 */
	List<TBizChecklist> getAllRecordOfTask(String applyId);

	/**
	 * ��ȡһ�����ģ���µ����м����
	 * @param jcmbId
	 * @return
	 */
	List<TDataChecklistitem> getAllCheckItems(String uuid, String jcmbId);

	List<HashMap<String, Object>> getJcxForCheck(String applyId, String jcmbId);
}