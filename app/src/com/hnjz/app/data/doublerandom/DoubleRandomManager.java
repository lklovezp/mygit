package com.hnjz.app.data.doublerandom;

import java.util.List;

import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.manager.Manager;

public interface DoubleRandomManager extends Manager{
	/**
	 * 从历史表里面查询 year年month月的设置比例
	 * */
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year, String month, String areaid)throws Exception;
	/**
	 * 根据区域查询本区域的所有重点企业
	 * */
	public List<TDataLawobjf> queryAllKeyLawobjfListByAreaId(String areaid)throws Exception;
	/**
	 * 根据月份，区域查询本区域的 month月能够抽查的季节性重点企业
	 * */
	public List<TDataLawobjf> queryKeyLawobjByAreaIdAndMonth(String year,String month,String areaid)throws Exception;
	/**
	 * 根据区域查询本区域的非季节性重点企业
	 * */
	public List<TDataLawobjf> queryNoMonthKeyLawobjfListByAreaId(String year,String areaid)throws Exception;
	/**
	 * 抽选重点企业
	 * */
	public String saveCheckKeyLawobjf(String year,String month,String areaid)throws Exception; 
	//根据年份和月份确定已抽查过的最大的月份
	public String checkMaxMonth(String year,String month,String areaid)throws Exception;
	/**
	 * 抽选一般企业
	 * */
	public String saveCheckNormalLawobjf(String year,String month,String areaid)throws Exception;
	/**
	 * 抽取重点企业和一般企业，特殊企业不用抽取，直接在特殊企业表里面直接派发
	 * */
	public String saveCheckAllLawobjf(String year,String areaid)throws Exception;
	/**
	 * 所有的派发设置比例
	 * */
	public List<TBizConfigcheck> queryAllConfigCheck()throws Exception;
	/**
	 * 根据企业id查询出企业所属区域
	 * */
	public String queryAreaIdByLawobjfId(String id)throws Exception;
	/**
	 * 所有上年度违法、被投诉的企业列表
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryIllegalLawobjfList(String year) throws Exception;
	
	//查询所有一般企业
	public List<String> queryNormalList(String areaId,String year) throws Exception;
	//根据年份，季度，区域，企业类型查询抽查企业数量
	public int queryCheckedLawobjByType(String year,String quarter,String type,String areaid)throws Exception;
	
}
