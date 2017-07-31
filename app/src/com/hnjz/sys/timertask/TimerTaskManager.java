/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.timertask;

import java.util.List;
import java.util.Map;

import com.hnjz.app.data.po.TDataTimertask;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;

/**
 * �Զ��ɷ�������manager
 * 
 * @author wumi
 * @version $Id: TimerTaskManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface TimerTaskManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param pageSize2 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryTimerTask(String name, String accepter, String isActive, String page,
			String pageSize);

	/**
	 * ɾ���Զ��ɷ�����
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void removeTimerTask(String id);
	/**
	 * ��ȡһ���Զ��ɷ�������
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void timerTaskInfo(TimerTaskForm frm);

	/**
	 * �����Զ��ɷ�������
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void saveTimerTask(TimerTaskForm frm);

	/**
	 * ��ѯ��ʱ������Ϣ
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTimerTask(String id);

	List<TDataTimertask> getAllTimerTask();

	void execTimerTask(TDataTimertask po);

	public List<ComboboxTree> taskTypeExceptZX();

	public List<Combobox> getLawObjTypeByTaskType(String tasktype);
}