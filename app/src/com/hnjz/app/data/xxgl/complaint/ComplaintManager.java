package com.hnjz.app.data.xxgl.complaint;

import java.util.Map;

import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.common.FyWebResult;



/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
	ִ�������ֵ�manager�ӿ�
 *
 */
public interface ComplaintManager {
	
	/**
	 * 
	 * �������ܣ����Ͷ����Ϣ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ComplaintForm getComplaintInfo(ComplaintForm complaintForm);
	
	/**
	 * 
	 * �������ܣ���������Ͷ����Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public TDataComplaint saveOrUpdateComplaint(ComplaintForm complaintForm);

	/**
	 * 
	 * �������ܣ���ѯͶ����Ϣ�б�����
	
	 * ���������
	 * @param lawobjname  ��λ����
	 * @param lawobjaddress ��λ��ַ
	 * @param starttime Ͷ��ʱ�� ��ʼʱ��
	 * @param endtime Ͷ��ʱ�� ����ʱ��
	 * @param isActive �Ƿ���Ч
	 * @param lawobjid ������Ϣid
	 * @param page ��ǰҳ
	 * @param pageSize ÿҳ��ʾ����
	
	 * ����ֵ��
	 */
	public FyWebResult queryComplaintList(String lawobjtypeid,String lawobjname,String lawobjaddress,String starttime,String endtime,String isActive,String lawobjid,String page,String pageSize);

	/**
	 * 
	 * �������ܣ�Ͷ����Ϣ��Ϊ��Ч
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void removeComplaint(String id);

	/**
	 * 
	 * �������ܣ���ȡһ��Ͷ����Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, String> queryOneComplaint(String lawobjid);
}