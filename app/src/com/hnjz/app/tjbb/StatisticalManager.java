package com.hnjz.app.tjbb;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-27
 * ����������
	ͳ�����۵�λ����
 *
 */
public interface StatisticalManager {
	
	/**
	 * 
	 * �������ܣ�ͳ�����۵�λ�����б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult tjpwdwsl(String regionId, String lawobjtype, String page, String pageSize);

	/**
	 * 
	 * �������ܣ�����ͳ���б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void exportStatisticalList(String regionId, String lawobjtype, HttpServletResponse res);

}