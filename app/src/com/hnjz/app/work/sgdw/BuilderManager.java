package com.hnjz.app.work.sgdw;


import java.io.IOException;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
/**
 * ʩ����λ����
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
public interface BuilderManager extends Manager{
	/**
	 * ��ѯʩ����λ�б�
	 * 
	 * @param name
	 *            �������������԰���������
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryBuilderList(String name, String isActive, String page, String pageSize)
			throws Exception;
	
    /**
     * ����ʩ����λ��Ϣ
     * @param TDataSgdw tDataSgdw ʩ����λ
     * @throws AppException
     * */	
	public void saveBuilder(BuilderForm builderForm)throws AppException;
	/**
	 * ɾ��ʩ����λ
	 * 
	 * @param id
	 *            ʩ����λID
	 * @throws AppException 
	 */
	public void removeBuilder(String id) throws AppException;
	/**
	 * �޸�ʩ����λ
	 * 
	 * @param builderForm
	 *            ʩ����λbuilderForm
	 * @throws AppException 
	 */
	public void editBuilder(BuilderForm builderForm)throws AppException;

}