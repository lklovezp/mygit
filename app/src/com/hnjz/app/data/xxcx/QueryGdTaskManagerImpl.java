package com.hnjz.app.data.xxcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.FileUtil;
/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
	归档任务查询
 *
 */
@Service("queryGdTaskManager")
public class QueryGdTaskManagerImpl extends ManagerImpl implements QueryGdTaskManager{

	@Override
	public FyWebResult queryGdrwList(String taskname,String lawobjtype, String tasktype, String areaid, String zbOrgId, String regionId, String lawobjname, String zbUsername, String rwly, String starttime,String endtime,String yqwcStarttime,String yqwcEndtime, String page, String pageSize) {
		StringBuffer sql = new StringBuffer("select * from (");
		//非专项任务
		sql.append("select w.work_name_ workname,k.name_ tasktypename,d.name_ rwly,l.lawobjname_,g.name_ orgname,w.zxr_name_ username,w.archives_time_ gdrq,w.id_,e.name_ from WORK_ w ");
		sql.append(" left join t_biz_taskandtasktype t on w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype k on t.tasktypeid_ = k.code_ ");
		sql.append(" left join t_sys_dic d on w.SOURCE_ = d.code_ and d.type_ = '1' ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region e on l.regionid_ = e.id_ ");
		sql.append(" left join t_sys_user r on w.zxr_id_ = r.id_ ");
		sql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where t.ISACTIVE_ = 'Y' and  w.isactive_='Y' and w.state_ = '").append(WorkState.YGD.getCode()).append("' ");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(taskname)){
			sql.append(" and w.work_name_ like :taskname");
			condition.put("taskname", "%"+taskname+"%");
		}
		if(StringUtils.isNotBlank(lawobjtype)){
			sql.append(" and l.lawobjtype_ = :lawobjtype");
			condition.put("lawobjtype", lawobjtype);
		}
		if(StringUtils.isNotBlank(tasktype)){
			//按季度统计使用（信访投诉来源）
			if("1".equals(tasktype) || "2".equals(tasktype)){
				sql.append(" and t.xftsly_ = :xftsly");
				condition.put("xftsly", tasktype);
			}else{
				String[] str = tasktype.split(",");
				sql.append(" and k.code_ in ( ");
				for(int i=0;i<str.length;i++){
					sql.append("'"+str[i]+"'");
					if(i!=str.length-1){
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		if(StringUtils.isNotBlank(zbOrgId)){
			String[] str = zbOrgId.split(",");
			sql.append(" and g.id_ in ( ");
			for(int i=0;i<str.length;i++){
				sql.append("'"+str[i]+"'");
				if(i!=str.length-1){
					sql.append(",");
				}
			}
			sql.append(") ");
		}
		if(StringUtils.isNotBlank(regionId)){
			sql.append(" and l.REGIONID_ = :regionId");
			condition.put("regionId", regionId);
		}
		if(StringUtils.isNotBlank(lawobjname)){
			sql.append(" and l.lawobjname_ like :lawobjname");
			condition.put("lawobjname", "%"+lawobjname+"%");
		}
		if(StringUtils.isNotBlank(zbUsername)){
			sql.append(" and w.zxr_name_ like :zbUsername");
			condition.put("zbUsername", "%"+zbUsername+"%");
		}
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and d.code_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(yqwcStarttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:yqwcStarttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("yqwcStarttime", yqwcStarttime+" 00:00:00");
			sql.append(" and l.lawobjid_ is not null and k.id_ is not null ");
		}
		if(StringUtils.isNotBlank(yqwcEndtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:yqwcEndtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("yqwcEndtime", yqwcEndtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.archives_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.archives_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		sql.append(" and w.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(areaid)){
			condition.put("areaid", areaid);
		}else{
			condition.put("areaid", CtxUtil.getAreaId());
		}
		
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		//专项任务
		sql.append(" union all ");
		sql.append(" select distinct w.work_name_ workname,k.name_ tasktypename,d.name_ rwly,");
		sql.append("  (select LISTAGG(y.lawobjname_, ',') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj y where y.taskid_ =w.id_) lawobjname, ");
		sql.append(" g.name_ orgname,w.zxr_name_ username,w.archives_time_ gdrq,w.id_, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname ");
		sql.append(" from WORK_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype k on t.tasktypeid_ = k.code_ ");
		sql.append(" left join t_sys_dic d on w.SOURCE_ = d.code_ and d.type_ = '1' ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region e on l.regionid_ = e.id_ ");
		sql.append(" left join t_sys_user r on w.zxr_id_ = r.id_ ");
		sql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where w.isactive_='Y' and w.state_ = '").append(WorkState.YGD.getCode()).append("' ");
		if(StringUtils.isNotBlank(taskname)){
			sql.append(" and w.work_name_ like :taskname");
		}
		if(StringUtils.isNotBlank(lawobjtype)){
			sql.append(" and l.lawobjtype_ = :lawobjtype");
		}
		if(StringUtils.isNotBlank(tasktype)){
			//按季度统计使用（信访投诉来源）
			if("1".equals(tasktype) || "2".equals(tasktype)){
				sql.append(" and t.xftsly_ = :xftsly");
				condition.put("xftsly", tasktype);
			}else{
				String[] str = tasktype.split(",");
				sql.append(" and k.code_ in ( ");
				for(int i=0;i<str.length;i++){
					sql.append("'"+str[i]+"'");
					if(i!=str.length-1){
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		if(StringUtils.isNotBlank(zbOrgId)){
			String[] str = zbOrgId.split(",");
			sql.append(" and g.id_ in ( ");
			for(int i=0;i<str.length;i++){
				sql.append("'"+str[i]+"'");
				if(i!=str.length-1){
					sql.append(",");
				}
			}
			sql.append(") ");
		}
		if(StringUtils.isNotBlank(regionId)){
			sql.append(" and l.REGIONID_ = :regionId");
		}
		if(StringUtils.isNotBlank(lawobjname)){
			sql.append(" and l.lawobjname_ like :lawobjname");
		}
		if(StringUtils.isNotBlank(zbUsername)){
			sql.append(" and w.zxr_name_ like :zbUsername");
		}
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and d.code_ = :rwly");
		}
		if(StringUtils.isNotBlank(yqwcStarttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:yqwcStarttime,'yyyy-MM-dd hh24:mi:ss')");
			sql.append(" and l.lawobjid_ is not null and k.id_ is not null ");
		}
		if(StringUtils.isNotBlank(yqwcEndtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:yqwcEndtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.archives_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.archives_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		sql.append(" and w.areaid_ = :areaid ");
		sql.append(" and t.tasktypeid_ ='15' ");
		
		
		sql.append(") z ");
		sql.append(" order by z.gdrq desc ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("workname", String.valueOf(obj[0]));
			dataObject.put("tasktypename", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("rwly", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("orgname", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("username", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("gdrq", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("id", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("regionname", obj[8]==null?"":String.valueOf(obj[8]));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public FyWebResult queryGdrwListForMobile(String lawobjtype,String tasktype,String workname,String lawobjname,String regionId,String zbUsername,String starttime,String endtime,String page,String pageSize) {
		StringBuffer sql = new StringBuffer("select * from (");
		//非专项任务
		sql.append("select w.work_name_ workname,k.name_ tasktypename,d.name_ rwly,l.lawobjname_,g.name_ orgname,w.zxr_name_ username,w.archives_time_ gdrq,w.id_,e.name_ from WORK_ w ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype k on t.tasktypeid_ = k.code_ ");
		sql.append(" left join t_sys_dic d on w.SOURCE_ = d.code_ and d.type_ = '1' ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region e on l.regionid_ = e.id_ ");
		sql.append(" left join t_sys_user r on w.zxr_id_ = r.id_ ");
		sql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where w.isactive_='Y' and w.state_ = '").append(WorkState.YGD.getCode()).append("' ");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(workname)){
			sql.append(" and w.work_name_ like :workname");
			condition.put("workname", "%"+workname+"%");
		}
		if(StringUtils.isNotBlank(lawobjtype)){
			sql.append(" and l.lawobjtype_ = :lawobjtype");
			condition.put("lawobjtype", lawobjtype);
		}
		if(StringUtils.isNotBlank(tasktype)){
			//按季度统计使用（信访投诉来源）
			if("1".equals(tasktype) || "2".equals(tasktype)){
				sql.append(" and t.xftsly_ = :xftsly");
				condition.put("xftsly", tasktype);
			}else{
				String[] str = tasktype.split(",");
				sql.append(" and k.code_ in ( ");
				for(int i=0;i<str.length;i++){
					sql.append("'"+str[i]+"'");
					if(i!=str.length-1){
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		if(StringUtils.isNotBlank(regionId)){
			sql.append(" and l.REGIONID_ = :regionId");
			condition.put("regionId", regionId);
		}
		if(StringUtils.isNotBlank(lawobjname)){
			sql.append(" and l.lawobjname_ like :lawobjname");
			condition.put("lawobjname", "%"+lawobjname+"%");
		}
		if(StringUtils.isNotBlank(zbUsername)){
			sql.append(" and w.zxr_name_ like :zbUsername");
			condition.put("zbUsername", "%"+zbUsername+"%");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.archives_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.archives_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		sql.append(" and w.areaid_ = :areaid ");
		condition.put("areaid", CtxUtil.getAreaId());
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		//专项任务
		sql.append(" union all ");
		sql.append(" select distinct w.work_name_ workname,k.name_ tasktypename,d.name_ rwly,");
		sql.append("  (select LISTAGG(y.lawobjname_, ',') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj y where y.taskid_ =w.id_) lawobjname, ");
		sql.append(" g.name_ orgname,w.zxr_name_ username,w.archives_time_ gdrq,w.id_, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname ");
		sql.append(" from WORK_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype k on t.tasktypeid_ = k.code_ ");
		sql.append(" left join t_sys_dic d on w.SOURCE_ = d.code_ and d.type_ = '1' ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region e on l.regionid_ = e.id_ ");
		sql.append(" left join t_sys_user r on w.zxr_id_ = r.id_ ");
		sql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where w.isactive_='Y' and w.state_ = '").append(WorkState.YGD.getCode()).append("' ");
		if(StringUtils.isNotBlank(workname)){
			sql.append(" and w.work_name_ like :workname");
		}
		if(StringUtils.isNotBlank(lawobjtype)){
			sql.append(" and l.lawobjtype_ = :lawobjtype");
		}
		if(StringUtils.isNotBlank(tasktype)){
			//按季度统计使用（信访投诉来源）
			if("1".equals(tasktype) || "2".equals(tasktype)){
				sql.append(" and t.xftsly_ = :xftsly");
				condition.put("xftsly", tasktype);
			}else{
				String[] str = tasktype.split(",");
				sql.append(" and k.code_ in ( ");
				for(int i=0;i<str.length;i++){
					sql.append("'"+str[i]+"'");
					if(i!=str.length-1){
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		if(StringUtils.isNotBlank(regionId)){
			sql.append(" and l.REGIONID_ = :regionId");
		}
		if(StringUtils.isNotBlank(lawobjname)){
			sql.append(" and l.lawobjname_ like :lawobjname");
		}
		if(StringUtils.isNotBlank(zbUsername)){
			sql.append(" and w.zxr_name_ like :zbUsername");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.archives_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.archives_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		sql.append(" and w.areaid_ = :areaid ");
		sql.append(" and t.tasktypeid_ ='15' ");
		
		
		sql.append(") z ");
		sql.append(" order by z.gdrq desc ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("workname", String.valueOf(obj[0]));
			dataObject.put("tasktypename", obj[1]==null?"":String.valueOf(obj[1]));
//			dataObject.put("rwly", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
//			dataObject.put("orgname", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("username", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("gdrq", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("id", obj[7]==null?"":String.valueOf(obj[7]));
//			dataObject.put("regionname", obj[8]==null?"":String.valueOf(obj[8]));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public JSONArray getTaskInfoForMobile(String id) {
		JSONArray array = new JSONArray();
		JSONObject jsonObj = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select w.work_name_,w.work_note_,d.name_ rwly,c.name_ rwmj,f.name_ jjcd,u.name_ djr,tu.username_ pfr, ");
			sql.append(" to_char(g.operate_time_,'yyyy-MM-dd hh24:mi:ss') pfsj,to_char(w.end_time_,'yyyy-MM-dd') yqwcsx, ");
			sql.append(" w.state_,w.NEXT_OPER_,w.ZXR_NAME_,o.name_ zbbm,to_char(w.ZXSTARTTIME_,'yyyy-MM-dd') zxsj ");
			sql.append(" from work_ w  ");
			sql.append(" left join t_sys_dic d on w.source_ = d.code_ and d.type_ = '1' ");
			sql.append(" left join t_sys_dic c on w.SECURITY_ = c.code_ and c.type_ = '2' ");
			sql.append(" left join t_sys_dic f on w.EMERGENCY_ = f.code_ and f.type_ = '3' ");
			sql.append(" left join t_sys_user u on w.DJRID_ = u.id_  ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = ? ");
			sql.append(" left join t_biz_taskoperlog g on w.id_ = g.work_id_ and g.operate_type_ = ? ");
			sql.append(" left join t_sys_userorg uo on w.ZXR_ID_ = uo.userid_ ");
			sql.append(" left join t_sys_org o on uo.orgid_ = o.id_ ");
			sql.append(" where w.id_ = ? ");
			List<Object[]> list = this.dao.findBySql(sql.toString(), TaskUserEnum.PFR.getCode(), WorkLogType.PF.getCode(), id);
			if(list.size()>0){
				Object[] obj = list.get(0);
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "任务名称：");
				jsonObj.put("value", obj[0]==null?"":String.valueOf(obj[0]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "主要内容：");
				jsonObj.put("value", obj[1]==null?"":String.valueOf(obj[1]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "任务来源：");
				jsonObj.put("value", obj[2]==null?"":String.valueOf(obj[2]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "紧急程度：");
				jsonObj.put("value", obj[4]==null?"":String.valueOf(obj[4]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "任务密级：");
				jsonObj.put("value", obj[3]==null?"":String.valueOf(obj[3]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "登记人：");
				jsonObj.put("value", obj[5]==null?"":String.valueOf(obj[5]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "派发人：");
				jsonObj.put("value", obj[6]==null?"":String.valueOf(obj[6]));
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "要求完成时限：");
				jsonObj.put("value", obj[8]==null?"":String.valueOf(obj[8]));
				
				String tasktypeName = "";
				List<Object> tasktypeList = this.dao.findBySql("select p.name_ from t_biz_taskandtasktype t left join t_data_tasktype p on t.tasktypeid_ = p.code_ where t.ISACTIVE_ = 'Y' and p.ISACTIVE_ = 'Y' and t.taskid_ = ?",id);
				for(int i=0;i<tasktypeList.size();i++){
					tasktypeName += String.valueOf(tasktypeList.get(i));
					if(i!= tasktypeList.size()-1){
						tasktypeName += "，";
					}
				}
				String lawobjname = "";
				List<Object> lawobjnameList = this.dao.findBySql("select o.lawobjname_ from t_biz_taskandlawobj o where o.taskid_ = ?",id);
				for(int i=0;i<lawobjnameList.size();i++){
					lawobjname += String.valueOf(lawobjnameList.get(i));
					if(i!= lawobjnameList.size()-1){
						lawobjname += "， ";
					}
				}
				if(!"日常办公".equals(tasktypeName)){
					jsonObj = new JSONObject();
					array.put(jsonObj);
					jsonObj.put("key", "执法对象：");
					String state = obj[9]==null?"":String.valueOf(obj[9]);//任务状态
					String source = obj[2]==null?"":String.valueOf(obj[2]);//任务来源
					if("随机抽查".equals(source) && (WorkState.YPF.getCode().equals(state) || WorkState.YZP.getCode().equals(state) || WorkState.YXP.getCode().equals(state))){//随机抽查
						jsonObj.put("value", "×××××企业");
					}else{
						jsonObj.put("value", lawobjname);
					}
				}
				
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "任务类型：");
				jsonObj.put("value", tasktypeName);
				
				/*String option = "";
				String optionSql = "select t.exec_user_,to_char(t.created_,'yyyy-MM-dd hh:mi') ,t.opinion_ from T_BIZ_WFNODEINFO t ";
				optionSql += " where t.apply_id_=? ";
				optionSql += " and t.result_ = '退回执行' ";
				List<Object[]> optionList = this.dao.findBySql(optionSql,id);
				for(int i=0;i<optionList.size();i++){
					Object[] optionObj = optionList.get(i);
					option += optionObj[0]==null?"":String.valueOf(optionObj[0])+"(";
					option += optionObj[1]==null?"":String.valueOf(optionObj[1])+"):";
					option += optionObj[2]==null?"":String.valueOf(optionObj[3]);
					if(i!=0&& i!= tasktypeList.size()-1){
						option += "/t/r";
					}
				}
				jsonObj = new JSONObject();
				array.put(jsonObj);
				jsonObj.put("key", "退回意见：");
				jsonObj.put("value", option);*/
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@Override
	public FyWebResult queryWorkFileList(String pid, String page, String pageSize) {
		Map<String,Object> condition = new HashMap<String,Object>();
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = :pid";
		condition.put("pid", pid);
		sql+=" order by d.code_, f.CREATED_ desc";
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			dataObject.put("url", obj[0]==null?"":"/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			rowsList.add(dataObject);
		}
		return fy;
	}

}
