package com.hnjz.sys.timertask;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.common.util.StringUtil;

/**
 * 查询自动派发任务并逐条创建定时器
 * 
 * @author xuguanghui
 * @version $Id: ExecTimerTask.java, v 0.1 2013-4-3 下午03:19:31 admi Exp $
 */
public class ExecTimerTask {

	/** 日志 */
	private static final Log log = LogFactory.getLog(ExecTimerTask.class);

	@Autowired
	private TimerTaskManager timerTaskManager;

	/**
	 * 作业处理方法 由quartz定时调用
	 */
	public void execute() {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		timerTaskManager = (TimerTaskManager) ac.getBean("timerTaskManagerImpl");
		List<TDataTimertask> pos = timerTaskManager.getAllTimerTask();
		log.debug("需要执行的自动派发任务条数为：" + pos.size());
		Date now = new Date();
		for (TDataTimertask po : pos) {
			try {
				// 执行一次
				if (po.getType().equals("1")){
					// 如果当前时间还未到派发日期  则执行计划
					if (now.getTime() <= po.getTaskstarted().getTime()){
						timerTaskManager.execTimerTask(po);
					}
				} else {
					timerTaskManager.execTimerTask(po);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
