/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.taskdel;

import java.text.ParseException;
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

import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicTypeEnum;

/**
 * 任务删除的manager
 * 
 * @author wumi
 * @version $Id: TaskDelManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("taskDelManagerImpl")
public class TaskDelManagerImpl extends ManagerImpl implements
		TaskDelManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(TaskDelManagerImpl.class);

	@Override
	public FyWebResult queryTaskList(String areaid, String name, String hander, String endTimeFrom, String endTimeTo, String page,
			String pageSize,String rwzt,String zbrId) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("" +
			"select a.ID_ as id, a.WORK_NAME_ as rwmc, d.NAME_ as rwly, c.NAME_ as jjcd, a.END_TIME_, nvl(b.NAME_, '') as username, a.ZXR_NAME_ as zxrName, " +
				"case a.STATE_ " +
				" when '" + WorkState.YPF.getCode() + "' then '" + WorkState.YPF.getText() +
				"' when '" + WorkState.YZP.getCode() + "' then '" + WorkState.YZP.getText() +
				"' when '" + WorkState.YXP.getCode() + "' then '" + WorkState.YXP.getText() +
				"' when '" + WorkState.JS.getCode() + "' then '" + WorkState.JS.getText() +
				"' when '" + WorkState.BLZ.getCode() + "' then '" + WorkState.BLZ.getText() +
				"' when '" + WorkState.YBL.getCode() + "' then '" + WorkState.YBL.getText() +
				"' when '" + WorkState.YSH.getCode() + "' then '" + WorkState.YSH.getText() +
				"' when '" + WorkState.YTH.getCode() + "' then '" + WorkState.YTH.getText() +
				"' when '" + WorkState.YGD.getCode() + "' then '" + WorkState.YGD.getText() +
				"' else '未知' end as state " +
			"from WORK_ a left join T_SYS_USER b on a.CREATEBY_ = b.ID_ " +
			"left join T_SYS_DIC c on c.TYPE_ = '"+DicTypeEnum.JJCD.getCode()+"' and c.CODE_ = a.EMERGENCY_ " +
			"left join T_SYS_DIC d on d.TYPE_ = '"+DicTypeEnum.RWLY.getCode()+"' and d.CODE_ = a.SOURCE_ " +
			"where a.ISACTIVE_ = 'Y' and a.STATE_ is not null ");
		//if (StringUtils.isNotBlank(CtxUtil.getCurUser().getAreaId())){
		//	sql.append(" and a.AREAID_ = '" + CtxUtil.getCurUser().getAreaId() + "' ");
		//}
		//2016-06-22修改：查询条件加区域筛选，上级可删除下级任务
		if (StringUtils.isNotBlank(areaid)){
				sql.append(" and a.AREAID_ = '" + areaid + "' ");
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and a.WORK_NAME_ like :name");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(hander)) {
			sql.append(" and b.ID_ = :hander");
			data.put("hander", hander);
		}
		if (StringUtils.isNotBlank(endTimeFrom)) {
			sql.append(" and a.END_TIME_ >= TO_DATE(:endTime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("endTime", endTimeFrom + " 23:59:59");
		}
		
		if (StringUtils.isNotBlank(endTimeTo)) {
			sql.append(" and a.END_TIME_ <= TO_DATE(:endTimeTo,'yyyy-MM-dd hh24:mi:ss')");
			data.put("endTimeTo", endTimeTo + " 23:59:59");
		}
		//主办人
        if (StringUtil.isNotBlank(zbrId)) {
            sql.append(" and a.ZXR_ID_ = :zbr");
            data.put("zbr", zbrId);
        }
      //任务状态
        if (StringUtils.isNotBlank(rwzt)) {
            sql.append(" and a.state_ = :state ");
            data.put("state", rwzt);
        }
		sql.append(" order by a.EMERGENCY_ desc, a.END_TIME_ desc ");
		FyResult pos = dao.find(sql.toString(), page == null ? 1 : Integer.parseInt(page), pageSize == null ? 20 : Integer.parseInt(pageSize), data.getCanshu());
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> taskDels = pos.getRe();
		Map<String, String> dataObject = null;
		Date curDate=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟 
		for (Object[] ele : taskDels) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("name", String.valueOf(ele[1]));
			dataObject.put("source", String.valueOf(ele[2]));
			dataObject.put("emergency", String.valueOf(ele[3]));
			dataObject.put("endTime", ele[4] == null ? "" : String.valueOf(ele[4]).substring(0, 10));
			dataObject.put("hander", ele[5] == null ? "" : String.valueOf(ele[5]) );
			dataObject.put("zxrName", ele[6] == null ? "" : String.valueOf(ele[6]) );
			dataObject.put("state", String.valueOf(ele[7]));
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(ele[0])));
			
			//是否逾期
			Date deadline = null;
			try {
				deadline = sdf.parse(String.valueOf(ele[4]).substring(0, 10));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataObject.put("isYQ", String.valueOf(DateUtil.compareDate(curDate, deadline)));

			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void removeTask(String id) {
		Work po = (Work)this.dao.get(Work.class, id);
		
		po.setIsActive(YnEnum.N.getCode());
		this.dao.save(po);
		// 删除子任务
		List<Work> subs = this.dao.find("from Work where pid = ?", id);
		
		Work sub = new Work();
		TBizTaskandlawobj law = new TBizTaskandlawobj();
		List<TBizTaskandlawobj> laws = null;
		
		if (subs.size() > 0){
			for (int i = 0; i < subs.size(); i++) {
				sub = subs.get(i);
				sub.setIsActive(YnEnum.N.getCode());
				this.dao.save(sub);
				
//				// 去除任务分工
//				laws = this.dao.find("from TBizTaskandlawobj where newtaskid = ?", sub.getId());
//				if (laws.size() > 0){
//					law = laws.get(0);
//					law.setNewtaskid(null);
//					this.dao.save(law);
//				}
			}
		} else {
			// 去除任务分工
			laws = this.dao.find("from TBizTaskandlawobj where newtaskid = ?", id);
			if (laws.size() > 0){
				law = laws.get(0);
				law.setNewtaskid(null);
				this.dao.save(law);
			}
		}
	}
}
