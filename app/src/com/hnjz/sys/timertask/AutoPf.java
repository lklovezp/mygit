package com.hnjz.sys.timertask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.app.data.po.TDataTimertasklawobj;
import com.hnjz.app.data.po.TDataTimertasktasktype;
import com.hnjz.app.data.xxgl.lawobj.LawobjManagerImpl;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.hjgl.Vhjgl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.app.work.rwgl.RwglManagerImpl;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

public class AutoPf extends ManagerImpl implements Job{
	
	@Autowired
	private WorkPf workPf;
	@Autowired
	private TimerTaskManager timerTaskManager;
	@Autowired
	private CommonManager commonManager;
	@Autowired
    protected WorkManagerImpl workManager;
	@Autowired
    protected RwglManagerImpl rwglManager;
	@Autowired
    protected UserManager userManager;
	@Autowired
    private CommWorkManager commWorkManager;
	@Autowired
    private LawobjManagerImpl lawobjManager;
	
	public static TSysUser jsr;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
			timerTaskManager = (TimerTaskManager) ac.getBean("timerTaskManagerImpl");
			workPf = (WorkPf) ac.getBean("workPf");
			commonManager = (CommonManager) ac.getBean("commonManager");
			workManager = (WorkManagerImpl) ac.getBean("workManager");
			userManager = (UserManager) ac.getBean("userManager");
			rwglManager = (RwglManagerImpl) ac.getBean("rwglManagerImpl");
			commWorkManager = (CommWorkManager) ac.getBean("commWorkManagerImpl");
			lawobjManager = (LawobjManagerImpl) ac.getBean("lawobjManager");
			
			List<Map<String, Object>> pos = timerTaskManager.getTimerTask(arg0.getJobDetail().getJobDataMap().get("id").toString());
			TDataTimertask po = (TDataTimertask) pos.get(0).get("po");
			List<TDataTimertasktasktype> tasktypePos = (List<TDataTimertasktasktype>) pos.get(1).get("tasktypePo");
			List<TDataTimertasklawobj> lawobjPos = (List<TDataTimertasklawobj>) pos.get(2).get("lawobjPo");
			
			WorkDto frm = new WorkDto();
			String jsrId = po.getAccepter().getId();
			
			frm.setWorkNote(po.getContent());
			frm.setSource(po.getSource());
			frm.setSecurity(po.getSecurity());
			frm.setEmergency(po.getEmergency());
			frm.setDjrId(po.getRegister().getId());
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			frm.setCreateTime(sdf.format(d));
			
			// 紧急程度天数
			int emDays = 0;
			List<Combobox> ems = commonManager.queryTSysDicList(DicTypeEnum.JJCD.getCode());
			for (int i = 0; i < ems.size(); i++) {
				if (ems.get(i).getId().equals(po.getEmergency())){
					emDays = Integer.parseInt(ems.get(i).getValue());
					break;
				}
			}
			// 一次
			if (po.getType().equals("1")){
				frm.setStartTime(po.getTaskstarted().toString());
				frm.setEndTime(po.getTaskended().toString());
			}
			// 按月
			else if (po.getType().equals("2")){
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, emDays);
				frm.setStartTime(new Date().toString());
				frm.setEndTime(sdf.format(c.getTime()).toString());
			}
			// 按年
			else if (po.getType().equals("3")){
				Calendar c = Calendar.getInstance();
				c.setTime(po.getTaskstarted());
				c.add(Calendar.DAY_OF_MONTH, emDays);
				frm.setStartTime(po.getTaskstarted().toString());
				frm.setEndTime(sdf.format(c.getTime()).toString());
			}
			frm.setPsyj(po.getOpinion());
			TSysUser pfr = this.userManager.getUser(po.getHander().getId());
			
			for (int i = 0; i < lawobjPos.size(); i++) {
				frm.setWorkName(po.getName() + lawobjPos.get(i).getLawobjname());
				// 派发
				Work work = workPf.savePf(frm, jsrId, pfr, "zfjc");
				// 接受
				String taskId = rwglManager.getTaskIdByWorkId(work.getId());
				jsr = this.userManager.getUser(jsrId);
				workManager.saveJs(work.getId(), taskId, jsr);
				// 执行并填充执法对象类型和执法对象
				if(null == work.getZxStartTime()){
					Work w = workManager.get(work.getId());
					w.setZxStartTime(new Date());
					w.setState(WorkState.BLZ.getCode());//开始办理状态改为“办理中”
					rwglManager.save(w);
				}
				// 保存执法对象类型
				commWorkManager.saveZfdxType(work.getId(), po.getLawobjtype());
				HashMap<String, Object> data = lawobjManager.getLawobjByIds(lawobjPos.get(i).getLawobjid(), jsr, po.getLawobjtype());
				JSONArray a = new JSONArray();
				JSONObject o = new JSONObject();
				
				o.put("lawobjid", data.get("id"));
				o.put("lawobjname", data.get("name"));
				o.put("fddbr", data.get("fddbr"));
				o.put("hbfzrlxdh", data.get("hbfzrlxdh"));
				o.put("address", data.get("address"));
				o.put("regionid", data.get("regionid"));
				o.put("hbfzr", data.get("hbfzr"));
				o.put("fddbrlxdh", data.get("fddbrlxdh"));
				a.put(o);
				commonManager.saveChoseeLawobj(work.getId(), po.getLawobjtype(), a, jsr);
				// 保存任务类型
				commWorkManager.saveTaskTypeMultiple(work.getId(), tasktypePos.get(0).getTasktype().getCode(), jsr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
