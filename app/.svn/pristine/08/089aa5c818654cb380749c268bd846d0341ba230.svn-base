package com.hnjz.common.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.hnjz.sys.timertask.AutoPf;
import com.hnjz.sys.timertask.SubSchedule;

public class QuartzUtil {
	/**
	 * 执行一次
	 * @param id
	 * @param schedule
	 * @throws Exception
	 */
	public static void once(String id, Date date) throws Exception {
		Scheduler sf = StdSchedulerFactory.getDefaultScheduler();
		// 设置触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(id + "-t", "TriggerGroup").startAt(date).build();
		// 任务详情
		JobDetail jd = JobBuilder.newJob(AutoPf.class).withIdentity(id + "-j", "JobGroup").build();
		JobDataMap jdm = jd.getJobDataMap();
		jdm.put("id", id);
		sf.scheduleJob(jd, trigger);
		sf.start();
	}
	
	/**
	 * 每月执行
	 * @param id
	 * @param schedule
	 * @throws Exception
	 */
	public static void month(String id, String schedule) throws Exception {
		Scheduler sf = StdSchedulerFactory.getDefaultScheduler();
		// 设置触发器
		CronTrigger ct = newTrigger().withIdentity(id + "-t", "TriggerGroup")
				.startNow().withSchedule(cronSchedule(schedule)).build();
		// 任务详情
		JobDetail jd = JobBuilder.newJob(SubSchedule.class).withIdentity(id + "-j", "JobGroup").build();
		JobDataMap jdm = jd.getJobDataMap();
		jdm.put("id", id);
		jdm.put("schedule", schedule);
		sf.scheduleJob(jd, ct);
		sf.start();
	}
	
	/**
	 * 每年执行
	 * @param id
	 * @param schedule
	 * @throws Exception
	 */
	public static void year(String id, String schedule) throws Exception {
		Scheduler sf = StdSchedulerFactory.getDefaultScheduler();
		// 设置触发器
		CronTrigger ct = newTrigger().withIdentity(id + "-t", "TriggerGroup")
				.startNow().withSchedule(cronSchedule(schedule)).build();
		
		// 任务详情
		JobDetail jd = JobBuilder.newJob(AutoPf.class).withIdentity(id + "-j", "JobGroup").build();
		JobDataMap jdm = jd.getJobDataMap();
		jdm.put("id", id);
		sf.scheduleJob(jd, ct);
		sf.start();
	}
	
	/**
	 * 修改任务
	 * @param name
	 * @param group
	 * @param time
	 */
	public static void modifyJob(String name, String group, String time){
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(name + "-t", group);
			//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			//表达式调度构建器
			//按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronSchedule(time)).build();
			//按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除任务
	 * @param id
	 * @param group
	 */
	public static void removeJob(String id, String jGroup, String tGroup, int type){
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			String tName = id;
			String jName = id;
			if (type == 0){
				tName += "-t";
				jName += "-j";
			} else {
				tName += "-sub-t";
				jName += "-sub-j";
			}
			JobKey jobKey = JobKey.jobKey(jName + "-j", "JobGroup");
			TriggerKey tk = TriggerKey.triggerKey(tName + "-t", "TriggerGroup");
			scheduler.pauseTrigger(tk);
			scheduler.unscheduleJob(tk);
			scheduler.deleteJob(jobKey);
			
			JobKey subjobKey = JobKey.jobKey(jName + "-sub-j", "timerTaskJob");
			TriggerKey subtk = TriggerKey.triggerKey(tName + "-sub-t", "timerTaskGroup");
			if (scheduler.checkExists(subtk)){
				scheduler.pauseTrigger(subtk);
				scheduler.unscheduleJob(subtk);
				scheduler.deleteJob(subjobKey);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

