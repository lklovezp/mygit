package com.hnjz.mobile.data.jxkh;

import com.hnjz.common.FyWebResult;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	����ۼ�����manager�ӿ���
 *
 */
public interface TaskTraceMobileManager {
	
	/**
	 * 
	 * �������ܣ�����ۼ�����
	
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
	public FyWebResult queryTaskTraceList(String areaid,String rwmc,String starttime,String endtime,String tasktype,String czlx,String page,String pageSize);

}