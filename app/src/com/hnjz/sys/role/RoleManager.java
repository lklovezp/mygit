/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.function.FunForm;

/**
 * ��ɫ������
 * 
 * @author wumi
 * @version $Id: RoleManager.java, v 0.1 Dec 28, 2011 9:22:27 AM Administrator
 *          Exp $
 */
public interface RoleManager extends Manager {

	/**
	 * ��ѯ��������Ч�Ľ�ɫ�б�
	 * 
	 * @return ������Ч�Ľ�ɫ�б�
	 * @throws Exception
	 */
	public List<Combobox> queryRole() throws Exception;

	/**
	 * ��ѯ��ɫ
	 * 
	 * @param name
	 *            �������������԰����ƣ���ע����
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @param pageSize2 
	 * @return ��ɫ�б�
	 * @throws Exception
	 */
	public FyWebResult queryRole(String name, String isActive, String page, String pageSize)
			throws Exception;

	/**
	 * ����һ�����ܲ˵�
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	@Transactional(readOnly = false)
	public void saveRole(RoleForm frm) throws AppException;

	/**
	 * ɾ����ɫ��Ϣ
	 * 
	 * @param id
	 *            ��ɫ��Ϣ��ID
	 */
	public void removeRole(String id) throws AppException;
}