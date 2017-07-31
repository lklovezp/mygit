package com.hnjz.app.jxkh.orgstatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	������ͳ��manager�ӿ���
 *
 */
public interface StatisticsOrgManager extends Manager{

	/**
	 * 
	 * �������ܣ�������ͳ��
	 * ���������
	 * @param tasktypeid:��������id
	 * @param rwly:������Դ���
	 * @param jjcd:�����̶ȱ��
	 * @param starttime:�鵵��ʼʱ��
	 * @param endtime:�鵵����ʱ��
	
	 * ����ֵ��
	 */
	public List<StatisticsForm> statisticsOrgList(String areaid,String tasktypeid,String rwly,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * �������ܣ�����������ͳ���б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void exportStatisticalOrgList(String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);

	/**
	 * 
	 * �������ܣ�ͳ������-�����б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryStatisticalOrgInfo(String areaid,String orgid,String type, String tasktypeid, String rwly, String jjcd, String starttime, String endtime, String page, String pageSize);

	/**
	 * 
	 * �������ܣ���ȡ����ִ�й��̷��������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public RwzxgcfxForm getRwzxgcfxFormByRwid(String id);
	
	/**
	 * 
	 * �������ܣ���õ��������ʱ��Ľ׶Σ��������ڽ׶Σ�
	
	 * �������������id
	
	 * ����ֵ��
	 */
	public StageAnalysis getMaxStageAnalysis(String id);

	/**
	 * 
	 * �������ܣ���ѯ��������׶η���
	
	 * �������������id
	
	 * ����ֵ��
	 */
	public List<StageAnalysis> querystageAnalysisList(String id);

	/**
	 * 
	 * �������ܣ���ȡ���ڽ׶�
	
	 * �������������id
	
	 * ����ֵ��
	 */
	public StageAnalysis getYqStageAnalysis(String id);

	/**
	 * 
	 * �������ܣ�����ͳ�ƣ������б�������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void exportStatisticalOrgInfoList(String areaid,String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname, String starttime,
			String endtime, HttpServletResponse res);
	
	/**
	 * 
	 * �������ܣ�������ͳ��
	 * ���������
	 * @param tasktypeid:��������id
	 * @param rwly:������Դ���
	 * @param jjcd:�����̶ȱ��
	 * @param starttime:�鵵��ʼʱ��
	 * @param endtime:�鵵����ʱ��
	
	 * ����ֵ��
	 */
	public List<StatisticsDocForm> statisticsDocByOrgList(String areaid,String tasktypeid,String rwly,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * �������ܣ�����ͳ�ƣ�����¼�����б�������
	 * ���������
	 * ����ֵ��
	 */
	public void exportStatisticalDocInfoList(String areaid,String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname, String starttime,
			String endtime, HttpServletResponse res);
	
}