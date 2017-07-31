/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.taskdel;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * �Զ��ɷ�������manager
 * 
 * @author wumi
 * @version $Id: TaskDelManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface TaskDelManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param  
	 * @param  
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryTaskList(String areaid, String name, String hander, String endTimeFrom, String endTimeTo, String page,
			String pageSize,String rwzt,String zbrId);

	/**
	 * ɾ���Զ��ɷ�����
	 * 
	 * @return ��
	 * @throws Exception
	 */
	public void removeTask(String id);

}