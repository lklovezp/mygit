package com.hnjz.app.xzcf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.Constants;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.NullControllHashMap;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

@Service("xczfManager")
public class XzcfManagerImpl extends ManagerImpl implements XzcfManager  {
	
	@Autowired
    protected Dao dao;
	
	@Autowired
    private UserManager userManager;
	
	/**日志*/
    private static final Log log = LogFactory.getLog(XzcfManagerImpl.class);
    
    @Autowired
	private CommonManager commonManager;
    
	@Override
	public FyWebResult getXzcfList(String rwmc, String rwly,String pfrId,String rwzt, String tasktype, String lawobjId, String page,String pageSize) throws Exception {
        QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        if(lawobjId != null && lawobjId != ""){
        	//任务类型
            if (StringUtil.isNotBlank(tasktype)) {
            	sql.append("select distinct w from Work w, TBizTaskandtasktype tt, TBizTaskandlawobj d where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid and w.id = d.taskid and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            	countSql.append("select distinct w.* from Work_ w, T_Biz_Taskandtasktype tt, TBizTaskandlawobj d  where w.isActive_ = 'Y' and tt.isActive_ = 'Y' and w.id_ = tt.taskid_ and w.id = d.taskid and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            } else {
            	sql.append("select distinct w from Work w, TBizTaskandtasktype tt, TBizTaskandlawobj d where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid and w.id = d.taskid and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            	//sql.append("select w from Work w where w.isActive = 'Y' and w.zxrId = :userId");
            }
            sql.append(" and d.lawobjid=:lawobjid ");
            countSql.append(" and d.lawobjid =:lawobjid ");
            data.put("lawobjid", lawobjId);
        }else{
        	//任务类型
            if (StringUtil.isNotBlank(tasktype)) {
            	sql.append("select distinct w from Work w, TBizTaskandtasktype tt where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            	countSql.append("select distinct w.* from Work_ w, T_Biz_Taskandtasktype tt where w.isActive_ = 'Y' and tt.isActive_ = 'Y' and w.id_ = tt.taskid_ and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            } else {
            	sql.append("select distinct w from Work w, TBizTaskandtasktype tt where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid and tt.tasktypeid in(" + FileTypeEnums.WFAJDC.getCode() + ")");
            	//sql.append("select w from Work w where w.isActive = 'Y' and w.zxrId = :userId");
            }
        }
        sql.append(" and w.areaid=:areaid ");
        countSql.append(" and w.AREAID_ = :areaid ");
        data.put("areaid", CtxUtil.getAreaId());
        
        //主办人只看到自己的任务
        sql.append(" and w.zxrId=:userId ");
        countSql.append(" and w.zxrId=:userId ");
        data.put("userId", CtxUtil.getUserId());
        
        //任务名称
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (w.name like :dbworkName or w.workNote like :dbworkName ) ");
            countSql.append(" and (w.WORK_NAME_ like :dbworkName or w.WORK_NOTE_ like :dbworkName ) ");
            data.putLike("dbworkName", rwmc);
        }
        //任务状态
        if (StringUtils.isNotBlank(rwzt)) {
            sql.append(" and w.state = :state ");
            countSql.append(" and w.state_ = :state ");
            data.put("state", rwzt);
        }
        //任务来源
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and w.source = :rwly");
            countSql.append(" and w.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //派发人
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and w.createUser.id = :pfr");
            countSql.append(" and w.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        ///////操作类型:派发、转派、办理、审核/////
        sql.append(" and w.id in (select work.id from WorkLog l where 1=1 ");
        countSql.append(" and w.id_ in (select WORK_ID_ from T_BIZ_TASKOPERLOG l where 1=1 ");
        //操作类型,为字符串数组
        sql.append(" and l.operateType in (:operateType) ");
        countSql.append(" and l.OPERATE_TYPE_ in (:operateType) ");
        String[] operateType = {WorkLogType.PF.getCode(),WorkLogType.ZF.getCode(),WorkLogType.XP.getCode(),WorkLogType.ZX.getCode(),WorkLogType.SH.getCode(),WorkLogType.TH.getCode(),WorkLogType.GD.getCode()};
        data.put("operateType", operateType);
        sql.append(") ");
        countSql.append(") ");
        sql.append(" order by w.startTime desc");
        countSql.append(" order by w.START_TIME_ desc");
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> vs = pos.getRe();
        NullControllHashMap d = null;
        TSysUser u = null;
        for (Work ele : vs) {
            d = new NullControllHashMap();
            d.put("id", ele.getId());
            //任务来源名称
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //紧急程度名称
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
            d.put("rwly", str1);//任务来源
            d.put("jjcd", str2);//紧急程度
            d.put("pfsj", ele.getStartTime());//派发时间
            d.put("yqwcsx", ele.getEndTime());//要求完成时限
            d.put("dbworkName", ele.getName());
            d.put("workNote", ele.getWorkNote());
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            if(StringUtils.isNotBlank(OperateUtil.getOperate(ele.getId()))){
            	d.put("operate", OperateUtil.getOperate(ele.getId()));
            }else{
            	d.put("operate", "<a onclick='edit(this)' id='"+ele.getId()+"' class='b-link'>行政处罚</a>");
            }
            rows.add(d);
        }
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
    }

}
