package com.hnjz.app.work.hjgl;

import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * �ۼ�����RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface HjglManager extends Manager{
	/**
	 * 
	 * �������ܣ���ȡ��ǰ����ĺۼ�(������¼)
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Vhjgl> getRwhj(String rwid);
	
	/**
	 * 
	 * �������ܣ���ȡ��ǰ����ĺۼ�(�ɷ�ת�ɲ�����¼)
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Vhjgl> getRwhj_pfzp(String rwid);

}