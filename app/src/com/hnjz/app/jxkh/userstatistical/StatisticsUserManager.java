package com.hnjz.app.jxkh.userstatistical;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.app.jxkh.orgstatistics.StatisticsForm;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	����Աͳ��manager�ӿ���
 *
 */
public interface StatisticsUserManager extends Manager{

	/**
	 * 
	 * �������ܣ�����Աͳ��
	 * ���������
	 * @param tasktypeid:��������id
	 * @param rwly:������Դ���
	 * @param jjcd:�����̶ȱ��
	 * @param starttime:�鵵��ʼʱ��
	 * @param endtime:�鵵����ʱ��
	
	 * ����ֵ��
	 */
	public List<StatisticsForm> statisticsUserList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * �������ܣ�����ͳ���б�
	 * ���������
	 * ����ֵ��
	 */
	public void exportStatisticsUserList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);

	/**
	 * 
	 * �������ܣ���ѯͳ�������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryStatisticalUserInfo(String userid, String areaid, String orgid, String type, String tasktypeid, String rwly, String jjcd, String starttime, String endtime, String page, String pageSize);

	/**
	 * 
	 * �������ܣ�����ͳ�������б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void exportStatisticalUserInfoList(String userid, String username,String areaid, String areaname,String orgid, String orgname, String type, String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname,
			String starttime, String endtime, HttpServletResponse res);
	
	/**
	 * ��ҳ����ѯ���´�����ɵ�������
	 * @return
	 */
	public StatisticsForm statisticsRwCount();
	
	/**
	 * ��ҳ���鿴��������
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public FyWebResult queryRwInfo(String type,String tasktype,String page,String pageSize);

	/**
	 * 
	 * �������ܣ�����Աͳ�Ƽ�챨�����
	 * ���������
	 * @param starttime:�鵵��ʼʱ��
	 * ����ֵ��
	 */
	public List<StatisticsDocForm> statisticsDocByUserList(String areaid,String tasktypeid,String rwly,String username,String orgids,String jjcd,String starttime,String endtime);

	/**
	 * 
	 * �������ܣ���������Աͳ�Ƽ���¼�����б�
	 * ���������
	 * ����ֵ��
	 */
	public void exportStatisticsDocByUserList(String title,String areaid,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgids,String orgnames,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res);
	
}