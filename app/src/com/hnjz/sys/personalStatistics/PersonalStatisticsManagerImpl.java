package com.hnjz.sys.personalStatistics;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.sys.org.OrgType;
import com.hnjz.sys.po.TSysUser;
@Service(value="PersonalStatisticsManagerImpl")
public class PersonalStatisticsManagerImpl extends ManagerImpl implements
PersonalStatisticsManager {

	@Override
	public String findEachStatisticsManager(String id, String date,String cid) {
		StringBuilder sql = new StringBuilder();
		QueryCondition data = new QueryCondition();
		sql.append("select  count(*)  from T_SYS_LOG t WHERE t.USERID_=:userId");
		data.put("userId", id);
		sql.append(" and t.OPERTIME_ >= to_date(:startDate,'yyyy-MM') and t.OPERTIME_<= to_date(:endDate,'yyyy-MM')");
		data.put("startDate", date + "-01");
		data.put("endDate", date + "-12");
		if("con1".equals(cid)){
			sql.append("group by to_char(t.OPERTIME_,'yyyy-MM') order by to_char(t.OPERTIME_,'yyyy-MM')");
		}else {
			sql.append("group by to_char(t.OPERTIME_,'q') order by to_char(t.OPERTIME_,'q')");
		}
		List<String[]> lTimes = dao.findBySql(sql.toString(),data.getCanshu());
		String str = "";
		if(lTimes != null && lTimes.size()>0){
			for(int i = 0;lTimes.size()>i;i++){
				str += lTimes.get(i)+",";
			}
		}
		return str;
	}

	@Override
	public String findEachStatisticsManagerRW(String id, String date,String cid) {
		StringBuilder sql = new StringBuilder();
		QueryCondition data = new QueryCondition();
		if("con1".equals(cid)){//按月统计
			sql.append("SELECT DISTINCT to_char(a.START_TIME_,'yyyy-MM'),count(*)");
		}else {//按季度统计
			sql.append("SELECT DISTINCT to_char(a.START_TIME_,'q'),count(*)");
		}
		sql.append(" FROM WORK_ a WHERE a.ZXR_ID_ = :userId");
		data.put("userId", id);
		sql.append(" AND STATE_ = '09' AND a.START_TIME_ >= to_date(:startDate,'yyyy-MM') AND a.START_TIME_ <= to_date(:endDate,'yyyy-MM')");
		data.put("startDate", date + "-01");
		data.put("endDate", date + "-12");
		int sumData;
		if("con1".equals(cid)){//按月统计
			sql.append("group by to_char(a.START_TIME_,'yyyy-MM') order by to_char(a.START_TIME_,'yyyy-MM')");
			sumData = 12;
		}else {//按季度统计
			sql.append("group by to_char(a.START_TIME_,'q') order by to_char(a.START_TIME_,'q')");
			sumData = 4;
		}
		List<String[]> lTimes = dao.findBySql(sql.toString(),data.getCanshu());
		List<String> list = new ArrayList<String>();
		String str = "";
		if(lTimes != null && lTimes.size()>0){
			for(int i = 0;sumData>i;i++){
				list.add("0");//添加一组全部为0的数组，为了防止某月火某季度没有值
			}
			for(int i = 0;lTimes.size()>i;i++){
				Object[] obj = lTimes.get(i);
				String sum = obj[1].toString();
				if(sumData == 12){
					String ss = obj[0].toString().substring(obj[0].toString().indexOf("-")+1);
					if(!"0".equals(ss.substring(0,1))){
						list.set(Integer.parseInt(ss.substring(0)), sum);
					}else {
						list.set(Integer.parseInt(ss.substring(1))-1, sum);
					}
				}else{
					String ss = obj[0].toString();
					list.set(Integer.parseInt(ss)-1, sum);
				}
				
			}
			for (String li:list) {
				str += li+",";
			}
		}else{
			for(int i = 0;12>i;i++){
				str+="0,";
			}
		}

		return str;
	}

	@Override
	public String findEachStatisticsManagerBL(String id,
			List<StatisticsDocForm> list,String cid) {
		String str = "";
		for (StatisticsDocForm lists:list) {
				if("con1".equals(cid)){
					str += lists.getYyfs()+",";
					str += lists.getEyfs()+",";
					str += lists.getMyfs()+",";
					str += lists.getSyfs()+",";
					str += lists.getWyfs()+",";
					str += lists.getLyfs()+",";
					str += lists.getQyfs()+",";
					str += lists.getByfs()+",";
					str += lists.getJyfs()+",";
					str += lists.getOyfs()+",";
					str += lists.getAyfs()+",";
					str += lists.getDyfs();
				}else {
					str += lists.getYyfs()+ lists.getEyfs()+ lists.getMyfs()+",";
					str += lists.getSyfs()+ lists.getWyfs()+ lists.getLyfs()+",";
					str += lists.getQyfs()+ lists.getByfs()+ lists.getJyfs()+",";
					str += lists.getOyfs()+ lists.getAyfs()+ lists.getDyfs();
				}
		}
		return str;
	}

	@Override
	public List<Combobox> createZterr(String areaId) {
		List<Combobox> re = new ArrayList<Combobox>();
		String hsql = "from TSysUser where areaId = ?";
		List<TSysUser> as = this.dao.find(hsql, areaId);
	
		for (TSysUser ele : as) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}

	
	
	@Override
	public List<StatisticsDocForm> statisticsUser(String areaid, String tasktypeid, String rwly, String username, String orgids, String jjcd, String starttime, String endtime,String userId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select g.id_ orgid,");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("  s.id_ userid,s.name_ username,");
		sql.append("  nvl(z.yyfs,0),nvl(z.eyfs,0),nvl(z.myfs,0),nvl(z.syfs,0),nvl(z.wyfs,0),nvl(z.lyfs,0),nvl(z.qyfs,0),nvl(z.byfs,0),nvl(z.jyfs,0),nvl(z.oyfs,0),nvl(z.ayfs,0),nvl(z.dyfs,0),nvl(z.ynfs,0) ");
		sql.append("   from t_sys_user s  ");
		sql.append("  left join (      ");
		sql.append("  select u.id_, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-01-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as yyfs, ");
		//是否是闰月
		int year = 0;
		try {
			year = Integer.parseInt(starttime);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		if((year % 4 != 0 ) || (year % 100 == 0 ) && (year % 400 != 0)){
			sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-02-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-02-28 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as eyfs, ");
		}else{
			sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-02-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-02-29 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as eyfs, ");
		}
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-03-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-03-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as myfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-04-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-04-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as syfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-05-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-05-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as wyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-06-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-06-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as lyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-07-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-07-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as qyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-08-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-08-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as byfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-09-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-09-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as jyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-10-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-10-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as oyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-11-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-11-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as ayfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-12-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-12-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as dyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-12-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as ynfs ");
		sql.append("	 from t_data_file w ");
		sql.append("	left join t_sys_user u ");
		sql.append("	on u.id_ = w.createby_ ");
		sql.append("	left join work_ m ");
		sql.append("	on m.id_ = w.pid_ ");
		sql.append("	left join t_sys_userorg o ");
		sql.append("	on u.id_ = o.userid_ ");
		sql.append("	where  w.isactive_ = 'Y' ");
		sql.append("	and u.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and m.archives_time_ between TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss') and TO_DATE(:starttime1,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+"-01-01 00:00:00");
			condition.put("starttime1", starttime+"-12-31 23:59:59");
		}
		sql.append("	and w.type_ in ('")
		.append(FileTypeEnums.RCJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.RCJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.NDHCJCJL.getCode()).append("','")
		.append(FileTypeEnums.NDHCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.HDCJCJL.getCode()).append("','")
		.append(FileTypeEnums.HDCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.XFTSJCJL.getCode()).append("','")
		.append(FileTypeEnums.XFTSMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.PWXKZJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.PWXKZJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZXXDJCBG.getCode()).append("','")
		.append(FileTypeEnums.ZXXDZRWMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZXXDZRWJCJL.getCode()).append("','")
		.append(FileTypeEnums.WFAJDCKCBL.getCode()).append("','")
		.append(FileTypeEnums.WFAJDCXWBL.getCode()).append("','")
		.append(FileTypeEnums.XQZLJCJL.getCode()).append("','")
		.append(FileTypeEnums.XQZLMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.GZJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.GZJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZDJKJCJL.getCode()).append("','")
		.append(FileTypeEnums.WXFWJCJL.getCode()).append("','")
		.append(FileTypeEnums.WXFWMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.WXHXPJCJL.getCode()).append("','")
		.append(FileTypeEnums.FSAQJCJL.getCode()).append("','")
		.append(FileTypeEnums.FSAQMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.WRSGXCDCJCJL.getCode()).append("','")
		.append(FileTypeEnums.WRSGXCDCMOREJCBL.getCode()).append("')");
		sql.append(" group by u.id_ ) z ");
		sql.append("	on s.id_ = z.id_ ");
		sql.append("	 left join t_sys_userorg o on s.id_ = o.userid_  left join t_sys_org g on o.orgid_ = g.id_  left join t_sys_area a on g.areaid_ = a.id_ ");
		sql.append(" where  s.id_ =:userId and s.isactive_ = 'Y' and s.issys_ !='Y' and g.isactive_ = 'Y' and g.areaid_ = :areaid ");
		if (StringUtils.isNotBlank(orgids)) {
			String[] arg = orgids.split(",");
			String orgid = "";
			for (int i = 0; i < arg.length; i++) {
				orgid += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgid += ",";
				}
			}
			sql.append(" and g.id_ in (").append(orgid).append(")");
		}
		condition.put("areaid", areaid);
		condition.put("userId", userId);
		sql.append(" order by g.orderby_,s.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsDocForm> listResult = new ArrayList<StatisticsDocForm>();
		StatisticsDocForm sumForm = new StatisticsDocForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计");
		for (Object[] obj : list) {
			StatisticsDocForm statisticsForm = new StatisticsDocForm(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), ((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(), ((BigDecimal) obj[6]).intValue(), ((BigDecimal) obj[7]).intValue(), ((BigDecimal) obj[8]).intValue(), ((BigDecimal) obj[9]).intValue(), ((BigDecimal) obj[10]).intValue(), ((BigDecimal) obj[11]).intValue(), ((BigDecimal) obj[12]).intValue(), ((BigDecimal) obj[13]).intValue(), ((BigDecimal) obj[14]).intValue(), ((BigDecimal) obj[15]).intValue(), ((BigDecimal) obj[16]).intValue());
			listResult.add(statisticsForm);
			sumForm.setYyfs(sumForm.getYyfs()+statisticsForm.getYyfs());
			sumForm.setEyfs(sumForm.getEyfs()+statisticsForm.getEyfs());
			sumForm.setMyfs(sumForm.getMyfs()+statisticsForm.getMyfs());
			sumForm.setSyfs(sumForm.getSyfs()+statisticsForm.getSyfs());
			sumForm.setWyfs(sumForm.getWyfs()+statisticsForm.getWyfs());
			sumForm.setLyfs(sumForm.getLyfs()+statisticsForm.getLyfs());
			sumForm.setQyfs(sumForm.getQyfs()+statisticsForm.getQyfs());
			sumForm.setByfs(sumForm.getByfs()+statisticsForm.getByfs());
			sumForm.setJyfs(sumForm.getJyfs()+statisticsForm.getJyfs());
			sumForm.setOyfs(sumForm.getOyfs()+statisticsForm.getOyfs());
			sumForm.setAyfs(sumForm.getAyfs()+statisticsForm.getAyfs());
			sumForm.setDyfs(sumForm.getDyfs()+statisticsForm.getDyfs());
			sumForm.setYnfs(sumForm.getYnfs()+statisticsForm.getYnfs());
		}
		listResult.add(sumForm);
		return listResult;
	}
	
	
}
