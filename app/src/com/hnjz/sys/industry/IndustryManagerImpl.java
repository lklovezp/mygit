/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.industry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;

import com.hnjz.app.data.po.TDataFwy;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.work.enums.ZfdxLx;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("industryManagerImpl")
public class IndustryManagerImpl extends ManagerImpl implements
		IndustryManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(IndustryManagerImpl.class);

	@Override
	public FyWebResult queryIndustry(String name, String lawobjtype, String isActive, String page,
			String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataIndustry where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :name");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(lawobjtype)) {
			sql.append(" and lawobjtype = :type");
			data.put("type", lawobjtype);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataIndustry> industrys = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataIndustry ele : industrys) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getName());
			TDataLawobjtype type=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, ele.getLawobjtype());
			dataObject.put("lawobjtype",type.getName());
			dataObject.put("isActive", ele.getIsActive().equals(YnEnum.Y.getCode()) ? "是" : "否");
			dataObject.put("orderby", String.valueOf(ele.getOrderby()));
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void removeIndustry(String id) {
		TDataIndustry del = (TDataIndustry) this.dao.get(TDataIndustry.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	@Override
	public void industryInfo(IndustryForm frm) {
		
		TDataIndustry po = (TDataIndustry) this.dao.get(TDataIndustry.class, frm.getId());
		
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setCode(po.getCode());
		frm.setIsActive(po.getIsActive());
		frm.setLawobjtype(po.getLawobjtype());
		frm.setTolawobjtype(po.getTolawobjtype());
		frm.setOrderby(po.getOrderby());
	}

	@Override
	public void saveIndustry(IndustryForm frm) throws AppException {
		TDataIndustry po = null;
		// 行业名称不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataIndustry where name = :name and lawobjtype = :lawobjtype and isActive='Y' ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getName());
		data.put("lawobjtype", frm.getLawobjtype());
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (StringUtils.isNotBlank(frm.getId())){
			po = (TDataIndustry)this.get(TDataIndustry.class, frm.getId());
		} else {
			if (count > 0) {
				throw new AppException("行业名称不能重复。");
			} else {
				po = new TDataIndustry();
			}
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		
		po.setName(frm.getName());
		po.setCode(frm.getCode());
		po.setCreated(cur);
		po.setCreateby(curUser);
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		po.setDescribe(frm.getDescribe());
		po.setOrderby(frm.getOrderby());
		po.setLawobjtype(frm.getLawobjtype());
		if (StringUtil.isNotBlank(frm.getTolawobjtype())){
			po.setTolawobjtype(frm.getTolawobjtype());
		}
		this.dao.save(po);
	}
	
	@Override
	public List<TDataIndustry> getIndustry(String lawobjid) {
		// TODO Auto-generated method stub
		String hsql = " from TDataIndustry where tolawobjtype=? ";
		List<TDataIndustry> re = this.dao.find(hsql, lawobjid);
		return re;
	}
}
