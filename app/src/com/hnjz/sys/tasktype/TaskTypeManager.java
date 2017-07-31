/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.tasktype;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;

/**
 * ���������manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface TaskTypeManager extends Manager {

	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryTaskType() throws Exception;
	/**
	 * ������������
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void saveTaskType(TaskTypeForm frm);
	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray taskTypeInfo(TaskTypeForm frm);
	/**
	 * ɾ����������
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void removeTaskType(String id);
	/**
	 * ��ѯ�������������� 
	 * @param id 
	 * 
	 * @return ComboboxTree value : id; text:name
	 * @throws Exception
	 */
	List<ComboboxTree> queryTaskTypeIdTree(String id);
	public List<ComboboxTree> queryTaskTypeCodeTree(String id);
	
	/**
	 * ������������code��ȡ��������
	 * @param code
	 * @return
	 */
	public TDataTasktype getTaskTypeByCode(String code);
	

}