/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

import java.util.List;

import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * 年度抽查对象管理类
 * @author shiqiuhan
 * @created 2016-3-10,下午05:10:56
 */
public interface YearLawobjManager extends Manager {

	/**
	 * 查询年度抽查对象
	 * @param year
	 * @param lawobjname
	 * @param lawobjtype
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryYearLawobj(String year, String lawobjname, String lawobjtype, String type, String page, String pageSize)
			throws Exception ;
	
	/**
	 * 根据类别查询所有年度抽查对象
	 * @param year
	 * @param quarter
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryAllYearLawobj(String year,String quarter,String type,String areaid)
			throws Exception ;
	
	/**
	 * 本年度常规抽选时没有被抽查的污染源
	 * @param year
	 * @param quarter
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryNoCheckedLawobj(String year,String quarter,String type,String areaid)
			throws Exception ;

	/**
	 * 批量保存年度抽查对象
	 * @param ids
	 * @param names
	 * @param year
	 * @throws Exception
	 */
	public void saveYearLawobj(String ids,String names,String year) throws Exception ;

	/**
	 * 删除年度抽查对象
	 * @param id
	 * @throws AppException
	 */
	public void removeYearLawobj(String id) throws AppException;
	
	/**
	 * 该年度是否已完成抽选
	 * @param year
	 * @return
	 * @throws AppException
	 */
	public boolean isChecked(String year) throws AppException;
	
	/**
	 * 保存年度抽查对象列表
	 * @param checkedList
	 * @param year
	 */
	public void saveYearLawobjList(List <TDataLawobj> checkedList, String year ,String cxlx) throws Exception;

	/**
	 * 
	 * 函数介绍：抽选类型下拉列表
	 */
	public List<Combobox> queryCxlxList();

}
