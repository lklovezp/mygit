package com.hnjz.sys.eachStatistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.IfFunc;
import org.eclipse.jdt.internal.compiler.codegen.ObjectCache;
import org.springframework.stereotype.Service;

import com.hnjz.Sys;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
@Service(value="EachStatisticsManagerImpl")
public class EachStatisticsManagerImpl extends ManagerImpl implements
EachStatisticsManager {

	@Override
	public String findEachStatisticsManager(String id, String date,String cid) {
		StringBuilder sql = new StringBuilder();
		QueryCondition data = new QueryCondition();
		sql.append("select  count(*)  from T_SYS_LOG t  LEFT JOIN T_SYS_USER c ON c.ID_ = t.USERID_ WHERE c.AREAID_=:areaId");
		data.put("areaId", id);
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
		sql.append(" FROM WORK_ a LEFT JOIN T_SYS_USER b on a.ZXR_ID_ = b.ID_ WHERE b.AREAID_ = :areaId");
		data.put("areaId", id);
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
			if(id.equals(lists.getOrgid())){
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
		}
		return str;
	}

}
