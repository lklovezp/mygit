package com.hnjz.app.jxkh.overduetask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.jxkh.orgstatistics.StageAnalysis;
import com.hnjz.app.jxkh.orgstatistics.StatisticsOrgManager;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	逾期任务managerImpl
 *
 */
@Service("overdueTaskManager")
public class OverdueTaskManagerImpl extends ManagerImpl implements OverdueTaskManager {

	@Autowired
	private StatisticsOrgManager statisticsOrgManager;
	
	@Override
	public FyWebResult queryOverdueTaskList(String areaid,String starttime, String endtime, String rwly, String jjcd, String isComplete, String rwmc, String tasktype,String page,String pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct w.id_,w.work_name_,");
		sql.append("  (select  LISTAGG(t.name_, '，') WITHIN GROUP(ORDER BY ROWNUM) from t_data_tasktype t left join t_biz_taskandtasktype p on t.code_ = p.tasktypeid_ where t.ISACTIVE_ = 'Y' and p.ISACTIVE_ = 'Y' and p.taskid_ =w.id_) tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append(" u.name_ username,w.end_time_,w.state_,w.next_oper_ ,w.SOURCE_ from work_ w ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_sys_user u on w.createby_ = u.id_ ");
		sql.append(" where w.isactive_ = 'Y' and ((w.state_ != :state and w.END_TIME_ < sysdate) or (w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_)) ");
		Map<String, Object> condition = new HashMap<String,Object>();
		condition.put("state", WorkState.YGD.getCode());
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(isComplete) ){
			if("Y".equals(isComplete)){
				sql.append(" and w.state_ = '").append(WorkState.YGD.getCode()).append("'");
			}else if("N".equals(isComplete)){
				sql.append(" and w.state_ != '").append(WorkState.YGD.getCode()).append("'");
			}
		}
		if(StringUtils.isNotBlank(rwmc)){
			sql.append(" and w.work_name_ like :rwmc");
			condition.put("rwmc", "%"+rwmc+"%");
		}
		if(StringUtils.isNotBlank(tasktype)){
			String[] arg = tasktype.split(",");
			String tasktypeid = "";
			for(int i=0;i<arg.length;i++){
				tasktypeid = "'"+arg[i]+"'";
				if(i!=arg.length-1){
					tasktypeid +=",";
				}
			}
			sql.append(" and p.code_ in (").append(tasktypeid).append(")");
		}
		sql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append(" order by w.end_time_ desc,w.work_name_ ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("workname", String.valueOf(obj[1]));
			dataObject.put("taskname", obj[2]==null?"":String.valueOf(obj[2]));
			String state = String.valueOf(obj[6]);
			if(StringUtils.equals(String.valueOf(obj[8]),"11")){//随机抽查任务，任务接受前企业名称不显示
				if(StringUtils.equals(state, WorkState.YPF.getCode()) || StringUtils.equals(state, WorkState.YZP.getCode()) || StringUtils.equals(state, WorkState.YXP.getCode())){
					dataObject.put("lawobjname", "×××××企业");
				}else{
					dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
				}
			}else{
				dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
			}
			dataObject.put("pfr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("yqwcsx", obj[5]==null?"":String.valueOf(obj[5]));
			if(obj[6]!=null && WorkState.YGD.getCode().equals(String.valueOf(obj[6]))){
				dataObject.put("isComplete", "是");
			}else{
				dataObject.put("isComplete", "否");
			}
			StageAnalysis stageAnalysis = statisticsOrgManager.getYqStageAnalysis(String.valueOf(obj[0]));
			if(null != stageAnalysis){
				dataObject.put("yqdjdlx", stageAnalysis.getStageType());
				dataObject.put("yqdczr", stageAnalysis.getOprateUser());
			}else{
				dataObject.put("yqdjdlx", "");
				dataObject.put("yqdczr", "");
			}
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryYqrwByUser(String userid,String areaid,String starttime, String endtime, String rwly, String jjcd, String isComplete, String rwmc, String tasktype,String page,String pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct w.id_,w.work_name_,");
		sql.append("  (select  LISTAGG(t.name_, '，') WITHIN GROUP(ORDER BY ROWNUM) from t_data_tasktype t left join t_biz_taskandtasktype p on t.code_ = p.tasktypeid_ where t.ISACTIVE_ = 'Y' and p.ISACTIVE_ = 'Y' and p.taskid_ =w.id_) tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append(" u.name_ username,w.end_time_,w.state_,w.next_oper_ ,w.SOURCE_ from work_ w ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_sys_user u on w.createby_ = u.id_ ");
		sql.append(" left join t_biz_taskuser c on c.taskid_ = w.id_ ");
		sql.append(" where w.isactive_ = 'Y' and ((w.state_ != :state and w.END_TIME_ < sysdate) or (w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_)) ");
		Map<String, Object> condition = new HashMap<String,Object>();
		condition.put("state", WorkState.YGD.getCode());
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			sql.append(" and c.userid_ = :userid");
			condition.put("userid", userid);
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(isComplete) ){
			if("Y".equals(isComplete)){
				sql.append(" and w.state_ = '").append(WorkState.YGD.getCode()).append("'");
			}else if("N".equals(isComplete)){
				sql.append(" and w.state_ != '").append(WorkState.YGD.getCode()).append("'");
			}
		}
		if(StringUtils.isNotBlank(rwmc)){
			sql.append(" and w.work_name_ like :rwmc");
			condition.put("rwmc", "%"+rwmc+"%");
		}
		if(StringUtils.isNotBlank(tasktype)){
			String[] arg = tasktype.split(",");
			String tasktypeid = "";
			for(int i=0;i<arg.length;i++){
				tasktypeid = "'"+arg[i]+"'";
				if(i!=arg.length-1){
					tasktypeid +=",";
				}
			}
			sql.append(" and p.code_ in (").append(tasktypeid).append(")");
		}
		sql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append(" order by w.end_time_ desc,w.work_name_ ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("workname", String.valueOf(obj[1]));
			dataObject.put("taskname", obj[2]==null?"":String.valueOf(obj[2]));
			String state = String.valueOf(obj[6]);
			if(StringUtils.equals(String.valueOf(obj[8]),"11")){//随机抽查任务，任务接受前企业名称不显示
				if(StringUtils.equals(state, WorkState.YPF.getCode()) || StringUtils.equals(state, WorkState.YZP.getCode()) || StringUtils.equals(state, WorkState.YXP.getCode())){
					dataObject.put("lawobjname", "×××××企业");
				}else{
					dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
				}
			}else{
				dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
			}
			dataObject.put("pfr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("yqwcsx", obj[5]==null?"":String.valueOf(obj[5]));
			if(obj[6]!=null && WorkState.YGD.getCode().equals(String.valueOf(obj[6]))){
				dataObject.put("isComplete", "是");
			}else{
				dataObject.put("isComplete", "否");
			}
			StageAnalysis stageAnalysis = statisticsOrgManager.getYqStageAnalysis(String.valueOf(obj[0]));
			if(null != stageAnalysis){
				dataObject.put("yqdjdlx", stageAnalysis.getStageType());
				dataObject.put("yqdczr", stageAnalysis.getOprateUser());
			}else{
				dataObject.put("yqdjdlx", "");
				dataObject.put("yqdczr", "");
			}
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(dataObject);
		}
		return fy;
	}

}
