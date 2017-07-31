/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSet;

import java.util.List;

import com.hnjz.app.drafter.po.TDataDrafterSet;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * ������������Manager
 * @author shiqiuhan
 * @created 2015-12-24,����09:00:13
 */
public interface DrafterSetManager extends Manager {

	/**
	 * ���������������
	 * @param orgId
	 *            ����
	 * @param jb
	 *            ��˼���   
	 * @param shr
	 *            �����           
	 */
	public void saveDrafterSet(String orgId,String jb,String shr) throws AppException;
	
	/**
	 * ���ݲ��ź���˼����ѯ��������������Ϣ
	 * @param orgId
	 *            ����
	 * @param jb
	 *            ��˼���   
	 */
	public List <TDataDrafterSet>  queryShr(String orgId,String jb) throws AppException;
	
	/**
	 * ��������˲�ѯ���ź���˼���
	 * @param auditid
	 *            �����id
	 */
	public List <TDataDrafterSet> querySet(String auditid) throws AppException;
	
}