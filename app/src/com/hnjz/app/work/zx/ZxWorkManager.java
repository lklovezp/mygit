package com.hnjz.app.work.zx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * ר���������ZxWorkManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface ZxWorkManager extends Manager{
	
	/**
	 * 
	 * ��ȡר���������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public BlZxxdForm getBlZxxdFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZxWorkzxBL(String applyId,BlZxxdForm blZxxdForm);
	
	/**
	 * 
	 * �������ܣ�ר��������table��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> zxZfdxTableData(String applyId);
	
	/**
	 * 
	 * �������ܣ�ר��������table��������δ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> zxZfdxTableData_wfp(String applyId);
	
	/**
	 * 
	 * �������ܣ���������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx)throws Exception;
	
	public void saveLog(TSysUser czr, Date czsj, WorkLogType opType, WorkState state, Work work,Date startTime);
	
	/**
	 * 
	 * ��ȡר���������������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public BlZxxdZrwMainForm getBlZxxdZrwMainFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZxzrw_zxPage(String applyId,BlZxxdZrwMainForm blZxxdZrwMainForm);
	
	/**
	 * 
	 * �������ܣ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZxzrw_zxPageBlwb(String applyId,String taskId)throws Exception;
	
	/**
	 * 
	 * �������ܣ���֤"����"��true��ͨ����false����ͨ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ResultBean checkZxBlBL(String applyId);
	
	/**
	 * 
	 * �������ܣ���֤������"����"��true��ͨ����false����ͨ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ResultBean checkZxZrwBlBL(String applyId);
	
	/**
	 * 
	 * �������ܣ�ר�������������ϸ������zip�������浽�����񸽼���ȥ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZxzrw_zip(String applyId);
	
}