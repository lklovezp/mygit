package com.hnjz.app.work.jttlbl;

import com.hnjz.common.manager.Manager;

/**
 * ���ȸ�֪��Manager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 22, 2016
 * ����������
 *
 */
public interface JttlblManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public JttlblForm getJttlblForm(String applyId);
	
	/**
	 * 
	 * �������ܣ����濱���¼��������
	 * ���������
	 * ����ֵ��
	 */
	public void saveJttlbl(JttlblForm jttlblForm,String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveShengchengXwbl(String applyId,String wflx);
	
}