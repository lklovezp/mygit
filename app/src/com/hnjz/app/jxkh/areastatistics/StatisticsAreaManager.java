package com.hnjz.app.jxkh.areastatistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.manager.Manager;

/**
 * 
 * ���ߣ�zhangqingfeng
 * �������ڣ�2016-08-31
 * ����������������ͳ��manager�ӿ���
 *
 */
public interface StatisticsAreaManager extends Manager{

	/**
	 * 
	 * �������ܣ�������ͳ��
	 * ���������
	 * @param starttime:�鵵��ʼʱ��
	 * @param endtime:�鵵����ʱ��
	
	 * ����ֵ��
	 */
	public List<StatisticsDocForm> statisticsDocByAreaList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * �������ܣ�����������ͳ�Ƽ���¼�����б�
	 * ���������
	 * ����ֵ��
	 */
	public void exportStatisticsDocByAreaList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);
	
}