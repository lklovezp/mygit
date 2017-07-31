/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.spotCheck;

import java.util.List;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TBizCheckedLawobj;

/**
 * 随机抽选Manager
 * @author shiqiuhan
 * @created 2015-12-17,下午03:33:18
 */
public interface SpotCheckManager extends Manager {

	/**
	 * 开始抽选(季度)
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public String startSpotCheck(String year, String quarter,String areaid);
	/**
	 * 删除抽选结果(年度)
	 * 
	 * @param year
	 *            年份
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public void deleteResult(String year);
	/**
	 * 开始抽选(年度)
	 * 
	 * @param year
	 *            年份
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public String startYearCheck(String year);
	/**
	 * 查询被抽中污染源列表
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public FyWebResult queryCheckedLawobj(String year, String quarter,String areaid,String page, String pageSize) throws Exception;
	/**
	 * 生成任务
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public String createWork(String year, String quarter);
	
	/**
	 * 根据年份季度查询被抽中污染源列表
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> queryCheckedList(String year, String quarter);
	/**
	 * 保存季度抽选结果
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> saveCheckedList(String year, String quarter,String month ,String type, List<TDataLawobjf> checkedList,String areaId)throws Exception;

	public List<TBizCheckedLawobj> saveCheckedListnew(String year, String quarter, String type, List<TDataLawobjf> checkedList,String areaId) throws Exception;

}
