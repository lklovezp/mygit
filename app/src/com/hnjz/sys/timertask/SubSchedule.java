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
			// �¸���
			c.add(Calendar.MONTH, 1);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			// ����������
			int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			// ִ�д���
			int times = po.getTimes();
			// ��������  ��������������"5/15" ��ʾ��5�뿪ʼ��ÿ��15�봥��(5,20,35,50)�� 
			int increasing = day / times;

//			schedule = "0 0 0 1/" + increasing + " " + month + " " + year;
			schedule = "0 0 0 1/" + increasing + " * ?";
			Scheduler sf = StdSchedulerFactory.getDefaultScheduler();
			
			// ɾ���ϸ��¿����Ѿ��������Ķ�ʱ��
			try {
				QuartzUtil.removeJob(id + "-sub-t", "timerTaskJob", "timerTaskGroup", 1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			// ���ô�����
			CronTrigger ct = newTrigger().withIdentity(id + "-sub-t", "timerTaskGroup").startNow().withSchedule(cronSchedule(schedule)).build();
			// ��������
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