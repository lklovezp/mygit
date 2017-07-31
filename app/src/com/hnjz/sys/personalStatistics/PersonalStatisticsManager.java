package com.hnjz.sys.personalStatistics;

import java.util.List;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

public interface PersonalStatisticsManager extends Manager{
	/**
	 * 登录次数查询
	 * @param id
	 * @param date
	 * @return
	 */
	public String findEachStatisticsManager(String id,String date,String cid);
	/**
	 * 
	 * 办理任务次数
	 * @param id
	 * @param date
	 * @return
	 */
	
	public String findEachStatisticsManagerRW(String id,String date,String cid);
	/***
	 * 监察笔录查询
	 * @param id
	 * @param list
	 * @return
	 */
	public String findEachStatisticsManagerBL(String id,List<StatisticsDocForm> list,String cid);
	
	/**
	 * 创建师部与人员的树
	 * @param lists
	 * @return
	 */
	public List<Combobox> createZterr(String id); 
	
	
	public List<StatisticsDocForm> statisticsUser(String areaid, String tasktypeid, String rwly, String username, String orgids, String jjcd, String starttime, String endtime,String userId);
}
