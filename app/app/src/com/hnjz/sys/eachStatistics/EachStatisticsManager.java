package com.hnjz.sys.eachStatistics;

import java.util.List;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.manager.Manager;

public interface EachStatisticsManager extends Manager{
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
}
