/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.loginlog;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * ��ҵ������manager
 * 
 * @author wumi
 * @version $Id: LogManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface LoginLogManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryLoginLogList(String name, String username, String loginip, String logintype, String page, String pageSize);
	
	/**
	 * 
	 * �������ܣ���ѯ��¼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryLogTimesList(String areaid,String name, String starttime,String endtime, String page, String pageSize);
	
	
	/**
	 * 
	 * �������ܣ���¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void exportLogTimesList(String areaId,String name,String starttime,String endtime,HttpServletResponse res);
	/**
	 * 
	 * �������ܣ���ѯ��¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryLogTimesListDetails(String id,String starttime,String endtime, String page, String pageSize);
	
	/**
	 * ��ҳ����ѯ�Լ��ܵ�¼����
	 * @return
	 */
	public int queryZjAllLogTimesList();
	
	/**
	 * ��ҳ����ѯ�Լ����µĵ�¼����
	 * @return
	 */
	public int queryZjTheMonthLogTimesList();
	
	/**
	 * 
	 * ��ҳ����ѯ��¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryLogTimesDetails(String bythemonth, String page, String pageSize);
	
}