/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.spotCheck;

import java.util.List;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TBizCheckedLawobj;

/**
 * �����ѡManager
 * @author shiqiuhan
 * @created 2015-12-17,����03:33:18
 */
public interface SpotCheckManager extends Manager {

	/**
	 * ��ʼ��ѡ(����)
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public String startSpotCheck(String year, String quarter,String areaid);
	/**
	 * ɾ����ѡ���(���)
	 * 
	 * @param year
	 *            ���
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public void deleteResult(String year);
	/**
	 * ��ʼ��ѡ(���)
	 * 
	 * @param year
	 *            ���
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public String startYearCheck(String year);
	/**
	 * ��ѯ��������ȾԴ�б�
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public FyWebResult queryCheckedLawobj(String year, String quarter,String areaid,String page, String pageSize) throws Exception;
	/**
	 * ��������
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public String createWork(String year, String quarter);
	
	/**
	 * ������ݼ��Ȳ�ѯ��������ȾԴ�б�
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> queryCheckedList(String year, String quarter);
	/**
	 * ���漾�ȳ�ѡ���
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> saveCheckedList(String year, String quarter,String month ,String type, List<TDataLawobjf> checkedList,String areaId)throws Exception;

	public List<TBizCheckedLawobj> saveCheckedListnew(String year, String quarter, String type, List<TDataLawobjf> checkedList,String areaId) throws Exception;

}