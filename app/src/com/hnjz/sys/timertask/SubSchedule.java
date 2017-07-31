package com.hnjz.sys.timertask;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.common.util.QuartzUtil;

public class SubSchedule implements Job{

	@Autowired
	private TimerTaskManager timerTaskManager;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			String id = arg0.getJobDetail().getJobDataMap().get("id").toString();
			String schedule = arg0.getJobDetail().getJobDataMap().get("schedule").toString();
			
			ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
			timerTaskManager = (TimerTaskManager) ac.getBean("timerTaskManagerImpl");
			List<Map<String, Object>> pos = timerTaskManager.getTimerTask(arg0.getJobDetail().getJobDataMap().get("id").toString());
			TDataTimertask po = (TDataTimertask) pos.get(0).get("po");
			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			// 下个月
			c.add(Calendar.MONTH, 1);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			// 本月总天数
			int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			// 执行次数
			int times = po.getTimes();
			// 递增触发  如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。 
			int increasing = day / times;

//			schedule = "0 0 0 1/" + increasing + " " + month + " " + year;
			schedule = "0 0 0 1/" + increasing + " * ?";
			Scheduler sf = StdSchedulerFactory.getDefaultScheduler();
			
			// 删除上个月可能已经启动过的定时器
			try {
				QuartzUtil.removeJob(id + "-sub-t", "timerTaskJob", "timerTaskGroup", 1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			// 设置触发器
			CronTrigger ct = newTrigger().withIdentity(id + "-sub-t", "timerTaskGroup").startNow().withSchedule(cronSchedule(schedule)).build();
			// 任务详情
			JobDetail jd = JobBuilder.newJob(AutoPf.class).withIdentity(id + "-sub-j", "timerTaskGroup").build();
			JobDataMap jdm = jd.getJobDataMap();
			jdm.put("id", id);
			sf.scheduleJob(jd, ct);
			sf.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
