package com.hnjz.sys.mobile;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * 
 * �ֻ����ܹ���
 * 
 * @author Administrator
 * @version $Id: MobileManager.java, v 0.1 Apr 22, 2013 2:46:05 PM Administrator
 *          Exp $
 */
public interface MobileManager extends Manager{

	/**
	 * �ֻ�Ȩ�޹�������ȡ�����ֻ����ܣ�������ɫ���еĹ��ܹ���
	 * 
	 * @param roleId
	 *            ��ɫId
	 * @return
	 */
	public JSONArray queryQx(String roleId);

	/**
	 * ��ȡ�û��ֻ��˾��еĲ˵�
	 * 
	 * @return
	 */
	public List<Map<String, String>> queryMo();

	/**
	 * �����ֻ���Ȩ��,����ǰ��ɾ����ɫ���е�Ȩ��
	 * 
	 * @param mobileId
	 *            �ֻ�����Id
	 * @param roleId
	 *            ��ɫId
	 */
	public void saveRight(String[] mobileId, String roleId);

	/**
	 * ��ѯ�ֻ�����
	 * 
	 * @param name
	 * @param isActive 
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryMobile(String name, String isActive) throws Exception;

	/**
	 * 
	 * �����ֻ�����
	 * 
	 * @param form
	 * @throws AppException
	 */
	public void saveMobile(MobileForm form) throws AppException;

	/**
	 * @return �б�
	 * @throws Exception
	 */
	public JSONArray querySelectMobile(String id) throws Exception;

	/**
	 * ɾ���ֻ�������Ϣ
	 * 
	 * @param id
	 */
	public void removeMobile(String id) throws AppException;
	
	/**
	 * 
	 * �������ܣ���ѯ��ǰ�û����ն˲˵�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray queryMobileMenu();

}