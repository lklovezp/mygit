package com.hnjz.app.work.jaspb;

import com.hnjz.common.manager.Manager;

/**
 * �������������᰸��������Manager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
public interface JaspbManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public JaspbForm getSxgzsFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ����濱���¼��������
	 * ���������
	 * ����ֵ��
	 */
	public void saveSxgzs(JaspbForm jaspbForm,String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveShengchengSxgzs(String applyId,String wflx);
	
}