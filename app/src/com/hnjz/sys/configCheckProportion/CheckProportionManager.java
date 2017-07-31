/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * �������趨Manager
 * @author shiqiuhan
 * @created 2015-12-16,����02:19:38
 */
public interface CheckProportionManager extends Manager {

	/**
	 * ��ѯ���ȳ�����
	 * 
	 * @param frm
	 *            �������������԰���ݡ����ȡ�����������
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @return ���ȳ������б�
	 * @throws Exception
	 */
	public FyWebResult queryCheckProportion(CheckProportionForm frm, String page, String pageSize)
			throws Exception;

	/**
	 * ���漾�ȳ���������
	 * 
	 * @param checkProportionForm
	 *            {@link checkProportionForm}
	 */
	@Transactional(readOnly = false)
	public void saveCheckProportion(CheckProportionForm frm) throws AppException;

	/**
	 * ɾ�����ȳ�����������Ϣ
	 * 
	 * @param id
	 *            ���ȳ�����������Ϣ��ID
	 */
	public void removeCheckProportion(String id) throws AppException;
	
	/**
	 * 
	 * �������ܣ����������б�
	 */
	public List<Combobox> queryQuarterList();
	
	/**
	 * ������ݺͼ��Ȳ��ҳ�����
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 *            
	 */
	public int queryProportion(String year, String quarter);
}