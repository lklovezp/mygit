/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.loginlog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

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
@Service("logManagerImpl")
public class LoginLogManagerImpl extends ManagerImpl implements LoginLogManager, ServletContextAware {

	/** 日志 */
	private static final Log log = LogFactory.getLog(LoginLogManagerImpl.class);

	public FyWebResult queryLoginLogList(String name, String username,
			String loginip, String logintype, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize == "0" ? "20" : pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("select l from TSysLog l, TSysUser u where l.userid = u.id ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and l.name like :name");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and l.username like :username");
			data.putLike("username", username);
		}
		if (StringUtils.isNotBlank(loginip)) {
			sql.append(" and l.loginip like :loginip");
			data.putLike("loginip", loginip);
		}
		if (StringUtils.isNotBlank(logintype)) {
			sql.append(" and l.logintype = :logintype");
			data.put("logintype", logintype);
		}
		if (StringUtil.isNotBlank(CtxUtil.getOrgId())) {
			sql.append(" and u.areaId = :areaId ");
			data.put("areaId", CtxUtil.getCurUser().getOrgId());
		}

		sql.append(" order by l.opertime desc");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	public FyWebResult queryLogTimesList(String areaid, String name,
			String starttime, String endtime, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		if(null==pageSize){
			pageSize="20";
		}
		data.setPageSize(pageSize == "0" ? "20" : pageSize);
		StringBuffer sql = new StringBuffer();
		sql.append(" select id_,orgname_,name_,count_ from ( SELECT DISTINCT A.ID_, ");
		sql.append(" X.NAME_ ORGNAME_,A.NAME_, ");
		sql.append(" COUNT(DISTINCT TO_CHAR(B.OPERTIME_,'yyyy-MM-dd')) COUNT_ ,X.ORDERBY_ orderby1,A.ORDERBY_ orderby2 ");
		sql.append(" from T_SYS_ORG X ");
		sql.append(" LEFT JOIN T_SYS_USERORG Y ");
		sql.append(" ON X.ID_=Y.ORGID_ ");
		sql.append(" LEFT JOIN T_SYS_USER A ");
		sql.append(" ON Y.USERID_=A.ID_ ");
		sql.append(" LEFT JOIN T_SYS_LOG B ");
		sql.append(" ON A.ID_=B.USERID_ ");
		sql.append(" AND B.RESULT_='Y' ");
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" AND B.OPERTIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss') ");
			data.put("starttime",  starttime+" 00:00:00");
		}
		if (StringUtils.isNotBlank(endtime)) {
			sql.append(" AND B.OPERTIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss') ");
			data.put("endtime",  endtime+" 23:59:59");
		}
		sql.append(" WHERE A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		if (StringUtils.isNotBlank(areaid)) {
			sql.append(" AND X.AREAID_= '").append(areaid).append("' ");
		}
		if(StringUtils.isNotBlank(name)){
			sql.append(" AND A.NAME_ like :name");
			data.putLike("name", name);
		}
		sql.append(" GROUP BY A.ID_,X.NAME_,A.NAME_ ,X.ORDERBY_,A.ORDERBY_ )order by orderby1 asc,orderby2 asc ");
		FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> logs = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : logs) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("orgname", String.valueOf(ele[1]));
			dataObject.put("name", String.valueOf(ele[2]));
			dataObject.put("count", String.valueOf(ele[3]));
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(ele[0])));
			dataObject.put("operate", "<a id='"+String.valueOf(ele[0])+"' class='b-link' onclick='info(this)'>查看</a>");
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}
	
	public int queryZjAllLogTimesList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id_,orgname_,name_,count_ from ( SELECT DISTINCT A.ID_, ");
		sql.append(" X.NAME_ ORGNAME_,A.NAME_, ");
		sql.append(" COUNT(DISTINCT TO_CHAR(B.OPERTIME_,'yyyy-MM-dd')) COUNT_ ,X.ORDERBY_ orderby1,A.ORDERBY_ orderby2 ");
		sql.append(" from T_SYS_ORG X ");
		sql.append(" LEFT JOIN T_SYS_USERORG Y ");
		sql.append(" ON X.ID_=Y.ORGID_ ");
		sql.append(" LEFT JOIN T_SYS_USER A ");
		sql.append(" ON Y.USERID_=A.ID_ ");
		sql.append(" LEFT JOIN T_SYS_LOG B ");
		sql.append(" ON A.ID_=B.USERID_ ");
		sql.append(" AND B.RESULT_='Y' ");
		sql.append(" WHERE A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		sql.append(" AND A.ID_ = '").append(CtxUtil.getUserId()).append("' ");
		sql.append(" GROUP BY A.ID_,X.NAME_,A.NAME_ ,X.ORDERBY_,A.ORDERBY_ ) order by orderby1 asc,orderby2 asc ");
		List<Object[]> logs = this.dao.findBySql(sql.toString());
		if(logs!=null && logs.size()>0) {
			return Integer.parseInt(String.valueOf(logs.get(0)[3]));
		}
		return 0;
	}
	
	public int queryZjTheMonthLogTimesList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id_,orgname_,name_,count_ from ( SELECT DISTINCT A.ID_, ");
		sql.append(" X.NAME_ ORGNAME_,A.NAME_, ");
		sql.append(" COUNT(DISTINCT TO_CHAR(B.OPERTIME_,'yyyy-MM-dd')) COUNT_ ,X.ORDERBY_ orderby1,A.ORDERBY_ orderby2 ");
		sql.append(" from T_SYS_ORG X ");
		sql.append(" LEFT JOIN T_SYS_USERORG Y ");
		sql.append(" ON X.ID_=Y.ORGID_ ");
		sql.append(" LEFT JOIN T_SYS_USER A ");
		sql.append(" ON Y.USERID_=A.ID_ ");
		sql.append(" LEFT JOIN T_SYS_LOG B ");
		sql.append(" ON A.ID_=B.USERID_ ");
		sql.append(" AND B.RESULT_='Y' ");
		sql.append(" WHERE A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		sql.append(" AND A.ID_ = '").append(CtxUtil.getUserId()).append("' ");
		//从本月的第一天开始计算
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		String starttime = DateUtil.getDate(cal.getTime());
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" AND B.OPERTIME_ >= TO_DATE('"+starttime+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ");
		}
		sql.append(" GROUP BY A.ID_,X.NAME_,A.NAME_ ,X.ORDERBY_,A.ORDERBY_ ) order by orderby1 asc,orderby2 asc ");
		List<Object[]> logs = this.dao.findBySql(sql.toString());
		if(logs!=null && logs.size()>0) {
			return Integer.parseInt(String.valueOf(logs.get(0)[3]));
		}
		return 0;
	}


	public List<LoginLogForm> queryLogTimesList(String areaid, String name,
			String starttime, String endtime) {
		Map<String,Object> data = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select id_,orgname_,name_,count_ from ( SELECT DISTINCT A.ID_, ");
		sql.append(" X.NAME_ ORGNAME_,A.NAME_, ");
		sql.append(" COUNT(DISTINCT TO_CHAR(B.OPERTIME_,'yyyy-MM-dd')) COUNT_ ,X.ORDERBY_ orderby1,A.ORDERBY_ orderby2 ");
		sql.append(" from T_SYS_ORG X ");
		sql.append(" LEFT JOIN T_SYS_USERORG Y ");
		sql.append(" ON X.ID_=Y.ORGID_ ");
		sql.append(" LEFT JOIN T_SYS_USER A ");
		sql.append(" ON Y.USERID_=A.ID_ ");
		sql.append(" LEFT JOIN T_SYS_LOG B ");
		sql.append(" ON A.ID_=B.USERID_ ");
		sql.append(" AND B.RESULT_='Y' ");
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" AND B.OPERTIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss') ");
			data.put("starttime",  starttime+" 00:00:00");
		}
		if (StringUtils.isNotBlank(endtime)) {
			sql.append(" AND B.OPERTIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss') ");
			data.put("endtime",  endtime+" 23:59:59");
		}
		sql.append(" WHERE A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		if (StringUtils.isNotBlank(areaid)) {
			sql.append(" AND X.AREAID_= :areaid ");
			data.put("areaid", areaid);
		}
		if(StringUtils.isNotBlank(name)){
			sql.append(" AND A.NAME_ like :name");
			data.put("name", "%"+name+"%");
		}
		sql.append(" GROUP BY A.ID_,X.NAME_,A.NAME_ ,X.ORDERBY_,A.ORDERBY_ )order by orderby1 asc,orderby2 asc ");
		List<Object[]> list =  dao.findBySql(sql.toString(), data);
		List<LoginLogForm> list2 = new ArrayList<LoginLogForm>();
		for(Object[] obj:list){
			LoginLogForm form = new LoginLogForm();
			form.setOrgname(String.valueOf(obj[1]));
			form.setName(String.valueOf(obj[2]));
			form.setCs(String.valueOf(obj[3]));
			list2.add(form);
		}
		return list2;
	}
	public FyWebResult queryLogTimesListDetails(String id,
			String starttime, String endtime, String page, String pageSize) {
			QueryCondition data = new QueryCondition();
			if(null==pageSize){
				pageSize="20";
			}
			data.setPageSize(pageSize == "0" ? "20" : pageSize);
			StringBuffer sql = new StringBuffer();
			sql.append("  select id_,name_,opertime_ from (SELECT DISTINCT A.ID_, ");
			sql.append(" A.NAME_, ");
			sql.append(" TO_CHAR(B.OPERTIME_,'yyyy-MM-dd') OPERTIME_  ");
			sql.append(" from  T_SYS_USER A ");
			sql.append(" RIGHT JOIN T_SYS_LOG B ");
			sql.append(" ON A.ID_=B.USERID_ ");
			sql.append(" WHERE B.RESULT_='Y' ");
			if (StringUtils.isNotBlank(id)) {
				sql.append(" AND B.USERID_=:userid");
				data.put("userid",  id);
			}
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" AND B.OPERTIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss') ");
				data.put("starttime",  starttime+" 00:00:00");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" AND B.OPERTIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss') ");
				data.put("endtime",  endtime+" 23:59:59");
			}
			sql.append(" AND A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		 
			sql.append(" ) order by opertime_ DESC ");
			FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			List<Object[]> logs = pos.getRe();
			Map<String, String> dataObject = null;
			for (Object[] ele : logs) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("opertime", String.valueOf(ele[2]));
				rows.add(dataObject);
			}
			fy.setRows(rows);
			log.debug(fy);
			return fy;
	}
	
	public FyWebResult queryLogTimesDetails(String bythemonth, String page, String pageSize) {
			QueryCondition data = new QueryCondition();
			if(null==pageSize){
				pageSize="20";
			}
			data.setPageSize(pageSize == "0" ? "20" : pageSize);
			StringBuffer sql = new StringBuffer();
			sql.append("  select id_,name_,opertime_ from (SELECT DISTINCT A.ID_, ");
			sql.append(" A.NAME_, ");
			sql.append(" TO_CHAR(B.OPERTIME_,'yyyy-MM-dd') OPERTIME_  ");
			sql.append(" from  T_SYS_USER A ");
			sql.append(" RIGHT JOIN T_SYS_LOG B ");
			sql.append(" ON A.ID_=B.USERID_ ");
			sql.append(" WHERE B.RESULT_='Y' ");
			sql.append(" AND B.USERID_=:userid");
			data.put("userid",  CtxUtil.getUserId());
			
			if ("true".equals(bythemonth)) {
				//从本月的第一天开始计算
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 0);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				
				String starttime = DateUtil.getDate(cal.getTime());
				if (StringUtils.isNotBlank(starttime)) {
					sql.append(" AND B.OPERTIME_ >= TO_DATE('"+starttime+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ");
				}
			}
			sql.append(" AND A.ISACTIVE_='Y' AND A.ISSYS_='N' ");
		 
			sql.append(" ) order by opertime_ DESC ");
			FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			List<Object[]> logs = pos.getRe();
			Map<String, String> dataObject = null;
			for (Object[] ele : logs) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("opertime", String.valueOf(ele[2]));
				rows.add(dataObject);
			}
			fy.setRows(rows);
			log.debug(fy);
			return fy;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
	}

}
