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
 * ��鵥��manager
 * 
 * @author wumi
 * @version $Id: JcdManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface JcdManager extends Manager {

	/**
	 * ��ȡ�����
	 * @param jcmbId
	 * @param applyId
	 * @return
	 * @throws AppException
	 */
	JSONArray getJcx(String jcmbId,String applyId) throws AppException;

	/**
	 * �ݴ浥�������
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
	 * �޸ļ��������
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param itemcontent
	 * @param orderby
	 */
	void editJcx(String applyId, String jcmbId, String itemId,String itemcontent,String orderby);
	
	/**
	 * ��ȡ���������
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @return
	 */
	String getJcxcontent(String applyId, String jcmbId, String itemId);

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
	 * ��ȡ����¼ ���Ե�ҳ��
	 * @param applyId
	 * @param jcmbId
	 * @param taskType 
	 * @return
	 * @throws AppException
	 */
	JSONArray getCheckRecord(String applyId, String superJcmbId, String jcmbId, String taskType) throws AppException;
	
	/**
	 * ��ȡ��ʷ���ģ���¼ ���Ե�ҳ��
	 * @param superJcmbId
	 * @param workId
	 * @return
	 */
	public JSONArray getHistoryRecord(String superJcmbId, String workId) throws JSONException;

	/**
	 * ���ɼ���¼�ĵ�
	 * @param applyId ����id
	 * @param jcmbId 
	 * @param subdata 
	 * @throws AppException
	 */
	HashMap<String, Object> buildCheckListRecord(String applyId, String taskType, String jcmbId) throws Exception;

	/**
	 * ���ؼ���¼�ĵ�
	 * @param applyId ����id
	 * @param res 
	 * @throws AppException
	 */
	void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException;

	/**
	 * ���ɼ�챨�沢����
	 * @param applyId
	 * @param taskType
	 * @param jcmbId
	 * @param jcqk 
	 * @param fileid
	 * @throws AppException
	 */
	void buildJcbl(String applyId, String taskType, String jcmbId, String jcqk,String fileid) throws Exception;

	/**
	 * ����Ƿ����ɹ�����¼
	 * @param applyId
	 * @param taskType
	 */
	boolean checkJcdExists(String applyId, String taskType) throws AppException;
	
	/**
	 * ���ؼ����
	 * @param applyId
	 * @param jcmbId
	 * @param itemId
	 * @param orderby
	 * @throws AppException
	 */
	void hideJcx(String applyId,String jcmbId, String itemId, String orderby) throws AppException;

	/**
	 * 
	 * �������ܣ���ѯ�����ģ�����ʷ��¼�б���
	 * ���������
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getJcmbRecordList(String applyId, String jcmbId, String page, String pageSize)throws Exception;

}