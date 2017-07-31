/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * �������Manager
 * @author shiqiuhan
 * @created 2015-12-24,����09:00:13
 */
public interface DrafterManager extends Manager {

	/**
	 * ��ѯ����б�
	 * 
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @return ����б�
	 * @throws Exception
	 */
	public FyWebResult queryDrafter(DrafterForm frm,String page, String pageSize) throws Exception;
	
	/**
	 * ��ѯ�������б�
	 * 
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @return ����б�
	 * @throws Exception
	 */
	public FyWebResult queryAuditDrafter(DrafterForm frm,String page, String pageSize) throws Exception;
	
	/**
	 * ��ѯ���ͳ���б�
	 * 
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @return ����б�
	 * @throws Exception
	 */
	public FyWebResult queryDrafterStatistics(DrafterForm frm,String page, String pageSize) throws Exception;

	/**
	 * ������
	 * 
	 * @param drafterSendForm
	 *            {@link drafterSendForm}
	 */
	@Transactional(readOnly = false)
	public TBizDrafter saveDrafter(DrafterForm frm) throws AppException;

	/**
	 * ɾ�����
	 * 
	 * @param id
	 *            �����Ϣ��ID
	 */
	public void removeDrafter(String id) throws AppException;
	
	/**
	 * ������
	 * 
	 * @param drafterForm
	 *            {@link drafterForm}
	 */
	public TBizDrafter auditDrafter(String id,String result) throws AppException;
	
	/**
	 * 
	 * �������ܣ�����״̬�����б�
	 */
	public List<Combobox> queryStateList();
	
}