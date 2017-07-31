/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.industry;

import java.util.List;

import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * ��ҵ������manager
 * 
 * @author wumi
 * @version $Id: IndustryManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface IndustryManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param pageSize2 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryIndustry(String name, String type, String isActive, String page,
			String pageSize);

	/**
	 * ɾ����ҵ����
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void removeIndustry(String id);
	/**
	 * ��ȡһ����ҵ������
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void industryInfo(IndustryForm frm);

	/**
	 * ������ҵ������
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void saveIndustry(IndustryForm frm) throws AppException;
	
	public List<TDataIndustry> getIndustry(String lawobjid);

}