package com.hnjz.app.work.danger;

import java.util.List;


import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

public interface DangerManager extends Manager{
	/**
	 * 
	 * 函数介绍：物理状态下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> queryWlStateList();
	/**
	 * 
	 * 函数介绍：运输方式下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> ysfs();
	/**
	 * 
	 * 函数介绍：设备状态下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> sbState();
	/**
	 * 
	 * 函数介绍：生产方式下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> scType();
    /**
     * 保存企业化学物质情况主要产品
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
