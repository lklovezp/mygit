/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.quarterChecktimeSet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.configCheckProportion.QuarterEnum;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;

/**
 * 季度抽查时间设置的Manager实现
 * @author shiqiuhan
 * @created 2016-3-17,下午03:03:41
 */
@Service("quarterChecktimeSetImpl")
public class QuarterChecktimeSetManagerImpl extends ManagerImpl implements QuarterChecktimeSetManager {
	
	@Autowired
	private AreaManager areaManager;

	/**
	 * 查询季度抽查时间
	 * 
	 * @param frm
	 *            搜索条件，可以按年份、季度、抽查时间搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 季度抽查时间列表
	 * @throws Exception
	 */
	public FyWebResult queryQuarterChecktimeSet(QuarterChecktimeSetForm frm, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataQuarterChecktimeSet where 1=1 ");
		//年份
		if (StringUtils.isNotBlank(frm.getYear())) {
			sql.append(" and year = :year ");
			data.put("year", frm.getYear());
		}
		//季度
		if (StringUtils.isNotBlank(frm.getQuarter())) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", frm.getQuarter());
		}
		//是否有效
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", frm.getIsActive());
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		//是否有效
		if (StringUtils.isNotBlank(frm.getExecuted())) {
			sql.append(" and executed = :executed ");
			data.put("executed", frm.getExecuted());
		} else {
			sql.append(" and executed = :executed ");
			data.put("executed", YnEnum.N.getCode());
		}
		//所属区域
		//String areaid = CtxUtil.getAreaId();
		if(StringUtils.isNotBlank(frm.getArea())){
			sql.append(" and area.id = :areaid ");
			data.put("areaid", frm.getArea());
		}
		sql.append(" order by year,quarter,orderby ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataQuarterChecktimeSet> quarterChecktimeSets = pos.getRe();
		Map<String, String> row = null;
		for (TDataQuarterChecktimeSet ele : quarterChecktimeSets) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("year", ele.getYear()+"年");
			row.put("quarter", QuarterEnum.getNameByCode(ele.getQuarter()));
			row.put("time", DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",ele.getTime()));
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
			row.put("executed", YnEnum.getNote(ele.getExecuted()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存季度抽查时间设置
	 * 
	 * @param quarterChecktimeSetForm
	 *            {@link quarterChecktimeSetForm}
	 * @throws ParseException 
	 */
	@Transactional(readOnly = false)
	public void saveQuarterChecktimeSet(QuarterChecktimeSetForm frm) throws AppException, ParseException {
		TDataQuarterChecktimeSet po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataQuarterChecktimeSet) this.get(TDataQuarterChecktimeSet.class, frm.getId());
		} else {
			po = new TDataQuarterChecktimeSet();
			po.setCreateby(user);
			po.setCreated(cur);
			po.setExecuted("N");
		}
		//所属区域
		String areaid = frm.getArea();
		if(StringUtils.isNotBlank(areaid)){
			po.setArea((TSysArea)areaManager.get(TSysArea.class, areaid));
		}
		po.setYear(frm.getYear());
		po.setQuarter(frm.getQuarter());
		po.setTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getTime()));
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		po.setOrderby(0);
		this.dao.save(po);
	}

	/**
	 * 删除季度抽查时间设置信息
	 * 
	 * @param id
	 *            季度抽查时间设置信息的ID
	 */
	public void removeQuarterChecktimeSet(String id) throws AppException {
		TDataQuarterChecktimeSet delObj = (TDataQuarterChecktimeSet) this.dao.get(TDataQuarterChecktimeSet.class, id);
		this.dao.remove(delObj);
	}
	
	/**
	 * 
	 * 函数介绍：季度下拉列表
	 */
	public List<Combobox> queryQuarterList(){
		return QuarterEnum.getQuarterEnumList();
	}
	
	/**
	 * 根据年份和季度查找抽查时间
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 *            
	 */
	public String queryTime(String year, String quarter,String areaid){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataQuarterChecktimeSet where isActive='Y' ");
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//季度
		if (StringUtils.isNotBlank(quarter)) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", quarter);
		}
		//所属区域
		//String areaid = areaid;
		if(StringUtils.isNotBlank(areaid)){
			sql.append(" and area.id = :areaid ");
			data.put("areaid", areaid);
		}
		List<TDataQuarterChecktimeSet> timeList = dao.find(sql.toString(), data);
		if(timeList!=null && timeList.size()>0){
			return String.valueOf(timeList.get(0).getTime());
		}else{
			return null;
		}
	}

	/*
	 * 查询所有未执行抽查的时间
	 */
	public List<TDataQuarterChecktimeSet> queryAllNoexecutedTime() {
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataQuarterChecktimeSet where isActive='Y' and executed='N'");
		List<TDataQuarterChecktimeSet> timeList = dao.find(sql.toString());
		return timeList;
	}
}
