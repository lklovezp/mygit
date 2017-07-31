/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.record;

import org.springframework.ui.ModelMap;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * ��ҵ������manager
 * 
 * @author wumi
 * @version $Id: RecordManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface RecordManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryRecord(String wflx, String content, String iscurver, String kcxwbj, String isActive, String page, String pageSize);

	/**
	 * �༭һ����¼��Ϣ
	 * 
	 * @param frm ��¼�������
	 * @return ��
	 */
	public void recordInfo(RecordForm frm);
	
	/**
	 * ����һ����¼��Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param VersionForm ��¼����ı���
	 * @return ��¼�ĳ�ʼ����
	 */
	public void saveRecord(RecordForm frm);

	/**
	 * ɾ��һ����¼��Ϣ
	 * 
	 * @param id ��¼id
	 * @return ��
	 */
	public void removeRecord(String id) throws AppException;

}