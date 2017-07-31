package com.hnjz.app.jxkh.tasktrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	任务痕迹管理managerImpl
 *
 */
@Service("taskTraceManager")
public class TaskTraceManagerImpl extends ManagerImpl implements TaskTraceManager {

	@Override
	public FyWebResult queryTaskTraceList(String areaid,String rwmc,String rwly,String starttime,String endtime,String tasktype,String pfr,String czlx,String page,String pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct w.id_,w.work_name_,d.name_ rwly,  ");
		sql.append("  (select  LISTAGG(t.name_, '，') WITHIN GROUP(ORDER BY ROWNUM) from t_data_tasktype t left join t_biz_taskandtasktype p on t.code_ = p.tasktypeid_ where t.ISACTIVE_ = 'Y' and p.ISACTIVE_ = 'Y' and p.taskid_ =w.id_) tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append(" u.username_,g.operate_time_,g.operate_type_,w.state_,g.start_time_ ,w.SOURCE_ from work_ w ");
		sql.append(" left join t_sys_dic d on w.source_ = d.code_ and d.type_ = '").append(DicTypeEnum.RWLY.getCode()).append("' ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" left join t_biz_taskoperlog g on w.id_ = g.work_id_ ");
		sql.append(" where w.isactive_='Y' and g.operate_time_ is not null");
		Map<String, Object> condition = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(rwmc)){
			sql.append(" and w.work_name_ like :rwmc");
			condition.put("rwmc", "%"+rwmc+"%");
		}
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and g.operate_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and g.operate_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(tasktype)){
			String[] arg = tasktype.split(",");
			String tasktypeCode = "";
			for (int i = 0; i < arg.length; i++) {
				tasktypeCode += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					tasktypeCode += ",";
				}
			}
			sql.append(" and p.code_ in (").append(tasktypeCode).append(")");
		}
		if(StringUtils.isNotBlank(pfr)){
			sql.append(" and u.userid_ = :pfr");
			condition.put("pfr", pfr);
		}
		if(StringUtils.isNotBlank(czlx)){
			sql.append(" and g.operate_type_ = :czlx");
			condition.put("czlx", czlx);
		}
		TSysUser user = CtxUtil.getCurUser();
		sql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		//sql.append("	and g.CZR_ID_ = :userid ");
		//condition.put("userid", user.getId());
		sql.append(" order by g.operate_time_ desc,w.work_name_,g.operate_type_ desc ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("workname", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("rwly", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("rwlx", obj[3]==null?"":String.valueOf(obj[3]));
			String state = String.valueOf(obj[8]);
			if(StringUtils.equals(String.valueOf(obj[10]),"11")){//随机抽查任务，任务接受前企业名称不显示
				if(StringUtils.equals(state, WorkState.YPF.getCode()) || StringUtils.equals(state, WorkState.YZP.getCode()) || StringUtils.equals(state, WorkState.YXP.getCode())){
					dataObject.put("lawobjname", "×××××企业");
				}else{
					dataObject.put("lawobjname", obj[4]==null?"":String.valueOf(obj[4]));
				}
			}else{
				dataObject.put("lawobjname", obj[4]==null?"":String.valueOf(obj[4]));
			}
			dataObject.put("pfr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("czsj", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("czlx", obj[7]==null?"":WorkLogType.getNote(String.valueOf(obj[7])));
			dataObject.put("rwzt", obj[8]==null?"":WorkState.getNameByCode(String.valueOf(obj[8])));
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(dataObject);
		}
		return fy;
	}


}
