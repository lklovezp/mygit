package com.hnjz.app.jxkh.tasktrace;

import com.hnjz.common.FyWebResult;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	����ۼ�����manager�ӿ���
 *
 */
public interface TaskTraceManager {
	
	/**
	 * 
	 * �������ܣ���ѯ���������¼�б�
	
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
	public FyWebResult queryTaskTraceList(String areaid,String rwmc,String rwly,String starttime,String endtime,String tasktype,String pfr,String czlx,String page,String pageSize);

}