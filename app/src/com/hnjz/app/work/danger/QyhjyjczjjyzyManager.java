package com.hnjz.app.work.danger;

import java.util.List;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

public interface QyhjyjczjjyzyManager extends Manager{
	/**
	 * 
	 * �������ܣ��豸�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> queryYjczTypeList();
	public void saveQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm)throws AppException;
	public FyWebResult qyhjyjczjjyzyList(String pid,String isActive,String page, String pageSize) throws AppException;
	public QyhjyjczjjyzyForm editQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm)throws AppException;
	public void removeQyhjyjczjjyzy(String id) throws AppException;
	public QyhjyjczjjyzyForm infoQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm)throws AppException;
	public FyWebResult qyhjyjczjjyzyListByTypeAndTime(String pid, String isActive,String page, String pageSize) throws AppException;


}