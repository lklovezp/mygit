package com.hnjz.app.jxkh.overduetask;

import com.hnjz.common.FyWebResult;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	��������manager�ӿ���
 *
 */
public interface OverdueTaskManager {
	
	/**
	 * 
	 * �������ܣ���ѯ���������б�
	
	 * ���������
	 * @param starttime:Ҫ�����ʱ�޿�ʼʱ��
	 * @param endtime:Ҫ�����ʱ�޽���ʱ��
	 * @param rwly:������Դ
	 * @param jjcd:�����̶�
	 * @param isComplete:�Ƿ����
	 * @param rwmc:��������
	 * @param tasktype:��������
	
	 * ����ֵ��
	 */
	public FyWebResult queryOverdueTaskList(String areaid,String starttime,String endtime,String rwly,String jjcd,String isComplete,String rwmc,String tasktype,String page,String pageSize);
	/**
	 * 
	 * �������ܣ���ѯ���������б�
	
	 * ���������
	 * @param starttime:Ҫ�����ʱ�޿�ʼʱ��
	 * @param endtime:Ҫ�����ʱ�޽���ʱ��
	 * @param rwly:������Դ
	 * @param jjcd:�����̶�
	 * @param isComplete:�Ƿ����
	 * @param rwmc:��������
	 * @param tasktype:��������
	
	 * ����ֵ��
	 */
	public FyWebResult queryYqrwByUser(String userid, String areaid,String starttime, String endtime, String rwly, String jjcd, String isComplete, String rwmc, String tasktype,String page,String pageSize);
}