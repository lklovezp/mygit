/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

import java.util.List;

import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * ��ȳ����������
 * @author shiqiuhan
 * @created 2016-3-10,����05:10:56
 */
public interface YearLawobjManager extends Manager {

	/**
	 * ��ѯ��ȳ�����
	 * @param year
	 * @param lawobjname
	 * @param lawobjtype
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryYearLawobj(String year, String lawobjname, String lawobjtype, String type, String page, String pageSize)
			throws Exception ;
	
	/**
	 * ��������ѯ������ȳ�����
	 * @param year
	 * @param quarter
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryAllYearLawobj(String year,String quarter,String type,String areaid)
			throws Exception ;
	
	/**
	 * ����ȳ����ѡʱû�б�������ȾԴ
	 * @param year
	 * @param quarter
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryNoCheckedLawobj(String year,String quarter,String type,String areaid)
			throws Exception ;

	/**
	 * ����������ȳ�����
	 * @param ids
	 * @param names
	 * @param year
	 * @throws Exception
	 */
	public void saveYearLawobj(String ids,String names,String year) throws Exception ;

	/**
	 * ɾ����ȳ�����
	 * @param id
	 * @throws AppException
	 */
	public void removeYearLawobj(String id) throws AppException;
	
	/**
	 * ������Ƿ�����ɳ�ѡ
	 * @param year
	 * @return
	 * @throws AppException
	 */
	public boolean isChecked(String year) throws AppException;
	
	/**
	 * ������ȳ������б�
	 * @param checkedList
	 * @param year
	 */
	public void saveYearLawobjList(List <TDataLawobj> checkedList, String year ,String cxlx) throws Exception;

	/**
	 * 
	 * �������ܣ���ѡ���������б�
	 */
	public List<Combobox> queryCxlxList();

}