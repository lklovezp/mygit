package com.hnjz.app.jxkh.quarterstatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	������ͳ��manager�ӿ���
 *
 */
public interface QuarterStatisticsManager {

	/**
	 * 
	 * �������ܣ�������ͳ��
	
	 * ���������
	 * @param orgid:����id
	 * @param starttime:Ҫ�����ʱ��
	 * @param endtime:Ҫ�����ʱ��
	
	 * ����ֵ��
	 */
	public List<QuarterForm> statisticsQuarterList(String areaid, String orgid, String starttime, String endtime);

	/**
	 * 
	 * �������ܣ�ͳ���ֳ�ִ�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public QuarterZfqkForm queryQuarterZfqk(String areaid,String orgid, String starttime, String endtime);

	/**
	 * 
	 * �������ܣ�����ͳ�ƽ��
	
	 * ���������year-�꣬quarter-���ȣ�orgid-����id��orgname-�������ƣ�starttime-Ҫ�����ʱ�޿�ʼʱ�䣬endtime-����ʱ��
	
	 * ����ֵ��
	 */
	public void exportQuarterStatistics(String year, String quarter, String areaid,String orgid, String orgname, String starttime, String endtime, HttpServletResponse res);
}