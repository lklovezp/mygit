/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;


import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysArea;

/**
 * ���������manager
 * 
 * @author wumi
 * @version $Id: AreaManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface AreaManager extends Manager{

	/**
	 * �����û�id��ȡ�û��������ŵ�����
	 * 
	 * @param userId
	 *            �û�Id
	 * @return �û��������ŵ�����
	 */
	public TSysArea getAreaByUser(String userId);

	/**
	 * ��ѯ�����еĲ���
	 * 
	 * @return �����б�
	 * @throws Exception
	 */
	public JSONArray querySelectArea(String id) throws Exception;

	/**
	 * ��ѯ���ܲ˵�
	 * @param isActive 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryArea(String name, String isActive) throws Exception;

	/**
	 * ��������
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public void saveArea(AreaForm frm) throws AppException;

	/**
	 * ��ȡ������������
	 * 
	 * @param id
	 *            �����ID
	 */
	public void areaInfo(AreaForm frm);
	
	/**
	 * ɾ������
	 * 
	 * @param id
	 *            �����ID
	 */
	public void removeArea(String id) throws AppException;
	
	
	/**
	 * �����û�����id��ȡ�ϼ������������ַ
	 * 
	 * @param userId
	 *            �û�Id
	 * @return �û��������ŵ�����
	 */
	public String getPAreaServerByAreaId(String areaId) throws AppException;
	
	/**
	 * �����û�����id��ȡ�ϼ�����id
	 * 
	 * @param userId
	 *            �û�Id
	 * @return �û��������ŵ�����
	 */
	public String getPAreaIdByAreaId(String areaId) throws AppException;

	/**
	 * ���ݲ��Ż������
	 * @param o
	 * @return
	 */
	public TSysArea getAreaByOrgid(String oid);

	/**
	 * ��ȡĳ�������µ���������
	 * @param areaId
	 * @return
	 */
	public List<TSysArea> getChAreas(String areaId);
}