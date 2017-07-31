package com.hnjz.app.data.xxcx;

import org.json.JSONArray;

import com.hnjz.common.FyWebResult;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-27
 * ����������
	�鵵�����ѯ
 *
 */
public interface QueryGdTaskManager {
	
	/**
	 * 
	 * �������ܣ���ѯ�鵵�����б�
	
	 * ���������
	 * @param lawobjtype:ִ����������
	 * @param tasktype:��������
	 * @param zbOrgId:���첿��
	 * @param regionId:����������
	 * @param zbUsername:�����û���
	 * @param rwly:������Դ
	 * @param page:��ǰҳĬ��1
	 * @param pagesize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 */
	public FyWebResult queryGdrwList(String taskname,String lawobjtype,String tasktype,String areaid, String zbOrgId,String regionId,String lawobjname,String zbUserId,String rwly,String starttime,String endtime,String yqwcStarttime,String yqwcEndtime,String page,String pageSize);

	/**
	 * 
	 * �������ܣ��ն˲�ѯ�鵵����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryGdrwListForMobile(String lawobjtype, String tasktype, String workname, String lawobjname, String regionId, String zbUsername, String starttime, String endtime, String page,
			String pageSize);

	/**
	 * 
	 * �������ܣ�����ն���������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray getTaskInfoForMobile(String id);

	/**
	 * 
	 * �������ܣ���ѯ������ظ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryWorkFileList(String pid, String page, String pageSize);
}