package com.hnjz.app.work.cfjds;

import com.hnjz.common.manager.Manager;

/**
 * ��������������Manager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
public interface CfjdsManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public CfjdsForm getCfjdsFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ����濱���¼��������
	 * ���������
	 * ����ֵ��
	 */
	public void saveSxgzs(CfjdsForm cfjdsForm,String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveShengchengSxgzs(String applyId,String wflx);
	
}