package com.hnjz.app.xzcf;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * �������RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
�����������ɷ����Ѱ����񡢴�������
 *
 */
public interface XzcfManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����������б���
	 * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getXzcfList(String rwmc,String rwly,String pfrId,String rwzt, String tasktype, String lawobjId, String page, String pageSize)throws Exception;

}