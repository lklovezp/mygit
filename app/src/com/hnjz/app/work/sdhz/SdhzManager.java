package com.hnjz.app.work.sdhz;

import com.hnjz.common.manager.Manager;

/**
 * �ʹ��֤��Manager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
public interface SdhzManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public SdhzForm getSxgzsFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ����濱���¼��������
	 * ���������
	 * ����ֵ��
	 */
	public void saveSxgzs(SdhzForm sxgzsForm,String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveShengchengSxgzs(String applyId,String wflx);
	
}