package com.hnjz.app.work.danger;

import java.util.List;


import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

public interface DangerManager extends Manager{
	/**
	 * 
	 * �������ܣ�����״̬�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> queryWlStateList();
	/**
	 * 
	 * �������ܣ����䷽ʽ�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> ysfs();
	/**
	 * 
	 * �������ܣ��豸״̬�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> sbState();
	/**
	 * 
	 * �������ܣ�������ʽ�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> scType();
    /**
     * ������ҵ��ѧ���������Ҫ��Ʒ
     * T_DATA_QYHXWZQKZYCP
     * @param 
     * */	
	public void saveQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm)throws AppException;
	public void saveQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm)throws AppException;
	public void saveQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm)throws AppException;
	
	public FyWebResult whpContentMainList(String pid,String isActive,String page, String pageSize) throws AppException;
	public FyWebResult whpContentFcpList(String pid,String isActive,String page, String pageSize) throws AppException;
	public FyWebResult whpContentYlList(String pid,String isActive,String page, String pageSize) throws AppException;
	public QyhxwzqkzycpForm editQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm)throws AppException;
	public QyhxwzqkfcpForm editQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm)throws AppException;
	public QyhxwzqkylForm editQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm)throws AppException;
	public QyhxwzqkzycpForm infoQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm)throws AppException;
	public QyhxwzqkfcpForm infoQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm)throws AppException;
	public QyhxwzqkylForm infoQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm)throws AppException;
	public void removeZycp(String id) throws AppException;
	public void removeFcp(String id) throws AppException;
	public void removeYl(String id) throws AppException;

}