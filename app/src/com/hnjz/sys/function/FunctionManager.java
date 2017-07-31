/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.function;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysFunc;

/**
 * ���ܲ˵�������
 * 
 * @author wumi
 * @version $Id: FunctionManager.java, v 0.1 Jan 8, 2013 10:15:57 AM wumi Exp $
 */
public interface FunctionManager extends Manager{

	/**
	 * ѡ���ܲ˵�����ҳ��Ĳ�ѯ
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	/**
	 * @param id 
	 */
	public JSONArray querySelectFunction(String id) throws Exception;

	/**
	 * ��ѯ���ܲ˵�
	 * @param isActive 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryFunction(String name, String isActive) throws Exception;

	/**
	 * ����һ�����ܲ˵�
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void saveFunction(FunForm frm) throws AppException;

	/**
	 * ɾ�����ܲ˵���Ϣ
	 * 
	 * @param id
	 *            ���ܲ˵���Ϣ��ID
	 */
	public void removeFunction(String id) throws AppException;

	/**
	 * ��ѯ��ǰ�˵�����Щ����
	 * 
	 * @return ��ǰ�˵�����Щ����
	 * @throws Exception
	 */
	public List<FunOp> queryOptByFunction(String id) throws Exception;

	/**
	 * ��ѯ��ǰ�˵�����Щ����
	 * 
	 * @return ��ǰ�˵�����Щ����
	 * @throws Exception
	 */
	public TSysFunc queryFunByRepid(String reportid) throws Exception;
}