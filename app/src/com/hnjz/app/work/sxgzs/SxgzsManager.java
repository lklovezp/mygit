package com.hnjz.app.work.sxgzs;

import com.hnjz.common.manager.Manager;

/**
 * ���ȸ�֪��Manager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
public interface SxgzsManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public SxgzsForm getSxgzsFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ����濱���¼��������
	 * ���������
	 * ����ֵ��
	 */
	public void saveSxgzs(SxgzsForm sxgzsForm,String applyId);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveShengchengSxgzs(String applyId);
	
}