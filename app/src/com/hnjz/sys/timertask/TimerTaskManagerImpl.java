/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.timertask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.QuartzUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataLawobjtypetasktype;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.app.data.po.TDataTimertasklawobj;
import com.hnjz.app.data.po.TDataTimertasktasktype;

/**
 * 自动派发管理的manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("timerTaskManagerImpl")
public class TimerTaskManagerImpl extends ManagerImpl implements
		TimerTaskManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(TimerTaskManagerImpl.class);

	@Override
	public FyWebResult queryTimerTask(String name, String accepter, String isActive, String page,
			String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		
		String curAreaId = CtxUtil.getCurUser().getAreaId();
		
		sql.append("from TDataTimertasktasktype a where 1=1 ");
		
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and a.timertask.name like :name");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(curAreaId)) {
			sql.append(" and timertask.hander.areaId = :curAreaId");
			data.put("curAreaId", curAreaId);
		}
		if (StringUtils.isNotBlank(accepter)) {
			sql.append(" and a.timertask.accepter.id = :accepter");
			data.put("accepter", accepter);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and a.timertask.isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and a.timertask.isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by a.timertask.updated desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataTimertasktasktype> timerTasks = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataTimertasktasktype ele : timerTasks) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getTimertask().getId());
			dataObject.put("name", ele.getTimertask().getName());
			dataObject.put("accepter", ele.getTimertask().getAccepter().getName());
			dataObject.put("hander", ele.getTimertask().getHander().getName());
			dataObject.put("tasktype", ele.getTasktype().getName());
			if (StringUtil.isNotBlank(String.valueOf(ele.getTimertask().getTimes()))){
				dataObject.put("times", String.valueOf(ele.getTimertask().getTimes()));
			} else {
				dataObject.put("times", "");
			}
			if (ele.getTimertask().getType().equals("1")){
				dataObject.put("type", "定时（1次）");
			} else if (ele.getTimertask().getType().equals("2")){
				dataObject.put("type", "按月");
			} else if (ele.getTimertask().getType().equals("3")){
				dataObject.put("type", "按年");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dataObject.put("updated", sdf.format(ele.getTimertask().getUpdated()));
			dataObject.put("operate", OperateUtil.getOperate(ele.getTimertask().getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void removeTimerTask(String id) {
//		this.dao.removeFindObjs("from TDataTimertasktasktype where timertask.id = ?", id);
//		this.dao.removeFindObjs("from TDataTimertasklawobj where pid = ?", id);
		TDataTimertask del = (TDataTimertask) this.dao.get(TDataTimertask.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
		// 删除定时任务
		try {
			QuartzUtil.removeJob(id, "JobGroup", "TriggerGroup", 0);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void timerTaskInfo(TimerTaskForm frm) {
		TDataTimertask po = (TDataTimertask) this.dao.get(TDataTimertask.class, frm.getId());
		List<TDataTimertasktasktype> tasktypePo = this.dao.find("from TDataTimertasktasktype where timertask.id = ?", po.getId());
		List<TDataTimertasklawobj> lawobjPo = this.dao.find("from TDataTimertasklawobj where pid = ?", po.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		frm.setId(po.getId());
		frm.setAccepter(po.getAccepter().getId());
		frm.setAccepterName(po.getAccepter().getName());
		frm.setName(po.getName());
		frm.setContent(po.getContent());
		frm.setEmergency(po.getEmergency());
		frm.setHander(po.getHander().getId());
		frm.setHanderName(po.getHander().getName());
		frm.setLawobjtype(po.getLawobjtype());
		frm.setOpinion(po.getOpinion());
		frm.setRegister(po.getRegister().getId());
		frm.setRegisterName(po.getRegister().getName());
		frm.setSecurity(po.getSecurity());
		frm.setSource(po.getSource());
		frm.setTaskended(String.valueOf(po.getTaskended()).equals("null") ? "" : sdf.format(po.getTaskended()));
		frm.setTaskstarted(String.valueOf(po.getTaskstarted()).equals("null") ? "" : sdf.format(po.getTaskstarted()));
		frm.setTasktype(tasktypePo.get(0).getTasktype().getCode());
		frm.setTimes(po.getTimes());
		frm.setType(po.getType());
		
		String lawobjId = "";
		String lawobjName = "";
		for (int i = 0; i < lawobjPo.size(); i++) {
			if (i > 0) {
				lawobjId += ",";
				lawobjName += ",";
			}
			lawobjId += lawobjPo.get(i).getLawobjid();
			lawobjName += lawobjPo.get(i).getLawobjname();
		}
		frm.setLawobj(lawobjId);
		frm.setLawobjName(lawobjName);
	}

	@Override
	public void saveTimerTask(TimerTaskForm frm) {
		try{
			TDataTimertask po = null;
			TDataTimertasktasktype tasktypePo = null;
			TDataTimertasklawobj lawobjPo = null;
			// 自动派发名称不能重复
			StringBuilder hsq = new StringBuilder();
			hsq.append("select count(id) from TDataTimertask where isActive = 'Y' and name = :name ");
			QueryCondition data = new QueryCondition();
			data.put("name", frm.getName());
			long count = (Long) this.dao.find(hsq.toString(), data).get(0);
			if (StringUtils.isNotBlank(frm.getId())){
				// 新建任务时肯定没有相同的定时任务， 只有修改时需要先删除定时任务，然后重新制定任务
				try {
					QuartzUtil.removeJob(frm.getId(), "JobGroup", "TriggerGroup", 0);
				} catch (Exception e){
					System.out.println(e.getMessage());
				}
				
				po = (TDataTimertask)this.get(TDataTimertask.class, frm.getId());
				tasktypePo = (TDataTimertasktasktype)this.find("from TDataTimertasktasktype where timertask.id = ?", frm.getId()).get(0);
				lawobjPo = (TDataTimertasklawobj)this.find("from TDataTimertasklawobj where pid = ?", frm.getId()).get(0);
			} else {
				if (count > 0) {
					throw new AppException("自动派发名称不能重复。");
				} else {
					po = new TDataTimertask();
					tasktypePo = new TDataTimertasktasktype();
					lawobjPo = new TDataTimertasklawobj();
				}
			}
			
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			
			TSysUser accepter = (TSysUser)this.get(TSysUser.class, frm.getAccepter());
			TSysUser hander = (TSysUser)this.get(TSysUser.class, frm.getHander());
			TSysUser register = (TSysUser)this.get(TSysUser.class, frm.getRegister());
			
			po.setAccepter(accepter);
			po.setName(frm.getName());
			po.setContent(frm.getContent());
			po.setEmergency(frm.getEmergency());
			po.setHander(hander);
			po.setLawobjtype(frm.getLawobjtype());
			po.setOpinion(frm.getOpinion());
			po.setRegister(register);
			po.setSecurity(frm.getSecurity());
			po.setSource(frm.getSource());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			po.setType(frm.getType());
			if(frm.getType().equals("2")){
				po.setTimes(frm.getTimes());
				po.setTaskended(null);
				po.setTaskstarted(null);
			} else {
				po.setTimes(null);
				po.setTaskended(sdf.parse(frm.getTaskended()));
				po.setTaskstarted(sdf.parse(frm.getTaskstarted()));
			}
			
			po.setCreated(cur);
			po.setCreateby(curUser);
			po.setIsActive(YnEnum.Y.getCode());
			po.setUpdateby(curUser);
			po.setUpdated(cur);
			po.setOrderby(0);
			
			po = (TDataTimertask) this.dao.save(po);
			TDataTasktype tasktype = (TDataTasktype)this.dao.find("from TDataTasktype where isActive = 'Y' and code = ?", frm.getTasktype()).get(0);
			tasktypePo.setTimertask(po);
			tasktypePo.setTasktype(tasktype);
			// 先删除执法对象
			this.dao.removeFindObjs("from TDataTimertasklawobj where pid = ?", po.getId());
			// 循环保存执法对象
			for (int i = 0; i < frm.getLawobj().split(",").length; i++) {
				lawobjPo = new TDataTimertasklawobj();
				lawobjPo.setPid(po.getId());
				lawobjPo.setLawobjid(frm.getLawobj().split(",")[i]);
				lawobjPo.setLawobjname(frm.getLawobjName().split(",")[i]);
				this.dao.save(lawobjPo);
			}
			this.dao.save(tasktypePo);
			// 执行定时任务
			Date now = new Date();
			// 执行一次
			if (po.getType().equals("1")){
				// 如果当前时间还未到派发日期  则执行计划
				if (now.getTime() <= po.getTaskstarted().getTime()){
					this.execTimerTask(po);
				}
			} else {
				this.execTimerTask(po);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 执行任务
	 */
	@Override
	public void execTimerTask(TDataTimertask po){
		try {
			// 一次
			if (po.getType().equals("1")) {
				QuartzUtil.once(po.getId(), po.getTaskstarted());
			}
			// 按月
			else if (po.getType().equals("2")) {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				// 下一分钟执行
				c.add(Calendar.MINUTE, 1);
				int day = c.get(Calendar.DAY_OF_MONTH);
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
				// 明天开始执行 计算下个月的任务的计划
				if (day < maxDay){
					day += 1;
				}
				// 每月的x号零时零点零分
				String schedule = second + " " + minute + " " + hour + " " + day + " * ?";
				// 然后每个月一号执行
				QuartzUtil.month(po.getId(), schedule);
			}
			// 按年
			else if (po.getType().equals("3")) {
				Calendar c = Calendar.getInstance();
				c.setTime(po.getTaskstarted());
				int month = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				// 每年的X月的X号零时零点零分
				String schedule = "0 0 0 " + day + " " + month + " ?";
				QuartzUtil.year(po.getId(), schedule);
			}
			
			log.debug("=========================定时任务已执行=======================");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Map<String, Object>> getTimerTask(String id) {
		List<Map<String, Object>> pos = new ArrayList<Map<String,Object>>();
		TDataTimertask po = (TDataTimertask) this.dao.get(TDataTimertask.class, id);
		List<TDataTimertasktasktype> tasktypePo = this.dao.find("from TDataTimertasktasktype where timertask.id = ?", po.getId());
		List<TDataTimertasklawobj> lawobjPo = this.dao.find("from TDataTimertasklawobj where pid = ?", po.getId());
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("po", po);
		pos.add(m);
		m.put("tasktypePo", tasktypePo);
		pos.add(m);
		m.put("lawobjPo", lawobjPo);
		pos.add(m);
		return pos;
	}
	
	@Override
	public List<TDataTimertask> getAllTimerTask (){
		List<TDataTimertask> pos = this.dao.find("from TDataTimertask where isActive = 'Y'");
		
		return pos;
	}

	@Override
	public List<ComboboxTree> taskTypeExceptZX() {
		List<TDataTasktype> list = this.find(" from TDataTasktype where isActive = 'Y' and code != '" + TaskTypeCode.ZXXD.getCode() + "' order by orderby");
		return this.taskTypeTreeHelp(list, null);
	}
	
	private List<ComboboxTree> taskTypeTreeHelp(List<TDataTasktype> list,String pid){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(TDataTasktype ele : list){
			if((null == pid && null==ele.getPid()) || (null != pid && null != ele.getPid() && pid.equals(ele.getPid())) ){
				ComboboxTree comboboxTree = new ComboboxTree(ele.getCode(),ele.getName());
				comboboxTree.setChildren(this.taskTypeTreeHelp(list, ele.getId()));
				listTree.add(comboboxTree);
			}
		}
		return listTree;
	}

	@Override
	public List<Combobox> getLawObjTypeByTaskType(String tasktype) {
		List<Object[]> pos = this.dao.find("select a.lawobjtype, b.name from TDataLawobjtypetasktype a, TSysDic b where a.tasktypeid = ? and b.type = '5' and a.lawobjtype = b.code", tasktype);
		List<Combobox> listTree = new ArrayList<Combobox>();
		for (Object[] o : pos) {
			listTree.add(new Combobox(String.valueOf(o[0]), String.valueOf(o[1]), String.valueOf(o[0])));
		}
		return listTree;
	}
}
