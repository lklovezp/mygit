/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.operlog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;

/**
 * 笔录问题管理的manager
 * 
 * @author wumi
 * @version $Id: LogManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("operLogManagerImpl")
public class OperLogManagerImpl extends ManagerImpl implements
	OperLogManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(OperLogManagerImpl.class);

	@Override
	public FyWebResult queryOperLogList(String czsjFrom, String czsjTo, String czrName, String operateType, String note, String page, String pageSize) throws Exception{
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("select l from WorkLog l, TSysUser u where l.czrId = u.id ");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String now = sdf.format(new Date());
		String pred = DateUtil.getPreDate(sdf.parse(now));
		if (StringUtils.isNotBlank(czsjFrom)) {
			sql.append(" and l.czsj >= TO_DATE(:czsjFrom,'yyyy-MM-dd hh24:mi:ss')");
			data.put("czsjFrom", czsjFrom + " 00:00:00");
		} else {
			sql.append(" and l.czsj >= TO_DATE(:czsjFrom,'yyyy-MM-dd hh24:mi:ss')");
			data.put("czsjFrom", pred + " 00:00:00");
		}
		if (StringUtils.isNotBlank(czsjTo)) {
			sql.append(" and l.czsj <= TO_DATE(:czsjTo,'yyyy-MM-dd hh24:mi:ss')");
			data.put("czsjTo", czsjTo + " 23:59:59");
		} else {
			sql.append(" and l.czsj <= TO_DATE(:czsjTo,'yyyy-MM-dd hh24:mi:ss')");
			data.put("czsjTo", now + " 23:59:59");
		}
		if (StringUtils.isNotBlank(czrName)) {
			sql.append(" and l.czrId like :czrId");
			data.putLike("czrId", czrName);
		}
		if (StringUtils.isNotBlank(operateType)) {
			sql.append(" and l.operateType = :operateType");
			data.put("operateType", operateType);
		}
		if (StringUtils.isNotBlank(note)) {
			sql.append(" and l.note like :note");
			data.putLike("note", note);
		}
		if (StringUtil.isNotBlank(CtxUtil.getAreaId())){
			sql.append(" and u.areaId = :areaId ");
			data.put("areaId", CtxUtil.getCurUser().getAreaId());	
		}
		
		sql.append(" order by l.czsj desc");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<WorkLog> logs = pos.getRe();
		Map<String, String> dataObject = null;
		for (WorkLog ele : logs) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dataObject.put("czsj", sdf2.format(ele.getCzsj()));
			dataObject.put("operateType", WorkLogType.getNote(ele.getOperateType()));
			dataObject.put("czr", ele.getCzrName());
			dataObject.put("note", ele.getNote());
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}
}
