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
 * ��ѯ�Զ��ɷ���������������ʱ��
 * 
 * @author xuguanghui
 * @version $Id: ExecTimerTask.java, v 0.1 2013-4-3 ����03:19:31 admi Exp $
 */
public class ExecTimerTask {

	/** ��־ */
	private static final Log log = LogFactory.getLog(ExecTimerTask.class);

	@Autowired
	private TimerTaskManager timerTaskManager;

	/**
	 * ��ҵ�������� ��quartz��ʱ����
	 */
	public void execute() {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		timerTaskManager = (TimerTaskManager) ac.getBean("timerTaskManagerImpl");
		List<TDataTimertask> pos = timerTaskManager.getAllTimerTask();
		log.debug("��Ҫִ�е��Զ��ɷ���������Ϊ��" + pos.size());
		Date now = new Date();
		for (TDataTimertask po : pos) {
			try {
				// ִ��һ��
				if (po.getType().equals("1")){
					// �����ǰʱ�仹δ���ɷ�����  ��ִ�мƻ�
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