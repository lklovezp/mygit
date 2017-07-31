package com.hnjz.mobile.data.jxkh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	任务痕迹管理managerImpl(终端使用)
 *
 */
@Service("taskTraceMobileManager")
public class TaskTraceMobileManagerImpl extends ManagerImpl implements TaskTraceMobileManager {

	@Override
	public FyWebResult queryTaskTraceList(String areaid,String rwmc,String starttime,String endtime,String tasktype,String czlx,String page,String pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct w.id_,w.work_name_,d.name_ rwly,tu.username_ pfr,  ");
		sql.append("  (select  LISTAGG(t.name_, '，') WITHIN GROUP(ORDER BY ROWNUM) from t_data_tasktype t left join t_biz_taskandtasktype p on t.code_ = p.tasktypeid_ where t.ISACTIVE_ = 'Y' and p.ISACTIVE_ = 'Y' and p.taskid_ =w.id_) tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append(" '',g.operate_time_,g.operate_type_,w.state_,g.start_time_ from work_ w ");
		sql.append(" left join t_sys_dic d on w.source_ = d.code_ and d.type_ = '").append(DicTypeEnum.RWLY.getCode()).append("' ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '02' ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_biz_taskoperlog g on w.id_ = g.work_id_ ");
		sql.append(" where w.isactive_='Y' and g.operate_time_ is not null");
		Map<String, Object> condition = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(rwmc)){
			sql.append(" and w.work_name_ like :rwmc");
			condition.put("rwmc", "%"+rwmc+"%");
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
		if(StringUtils.isNotBlank(czlx)){
			sql.append(" and g.operate_type_ = :czlx");
			condition.put("czlx", czlx);
		}
		TSysUser user = CtxUtil.getCurUser();
		if(StringUtils.isNotBlank(areaid)){
			sql.append("	and w.areaid_ = :areaid ");
			condition.put("areaid", areaid);
		}else{
			sql.append("	and w.areaid_ = :areaid ");
			condition.put("areaid", user.getAreaId());
		}
		sql.append("	and g.CZR_ID_ = :userid ");
		condition.put("userid", user.getId());
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
			dataObject.put("workname", String.valueOf(obj[1]));
			dataObject.put("rwly", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("pfr", String.valueOf(obj[3]));
			dataObject.put("rwlx", obj[4]==null?"":String.valueOf(obj[4]));
//			dataObject.put("lawobjname", String.valueOf(obj[4]));
			dataObject.put("czsj", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("czlx", obj[8]==null?"":WorkLogType.getNote(String.valueOf(obj[8])));
			dataObject.put("rwzt", obj[9]==null?"":WorkState.getNameByCode(String.valueOf(obj[9])));
//			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(dataObject);
		}
		return fy;
	}


}
