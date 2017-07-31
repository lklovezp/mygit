/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * ���������manager
 * 
 * @author wumi
 * @version $Id: IllegalTypeManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface IllegalTypeManager extends Manager {

	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryIllegalType() throws Exception;
	/**
	 * ����Υ������
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void saveIllegalType(IllegalTypeForm frm) throws AppException;
	/**
	 * ��ȡΥ��������Ϣ
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray IllegalTypeInfo(IllegalTypeForm frm);
	/**
	 * ɾ��Υ������
	 * 
	 * @return 
	 * @throws Exception
	 */
	public void removeIllegalType(String id) throws AppException;

}