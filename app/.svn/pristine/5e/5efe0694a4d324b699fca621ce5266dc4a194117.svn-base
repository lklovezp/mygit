package com.hnjz.app.work.danger;

import java.util.List;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
/*
 * likun
 * 企业周边水环境状况及环境保护目标
 * */

public interface WaterManager extends Manager {
	/**
	 * 
	 * 函数介绍：废水受纳水体功能类别下拉列表
	 * 
	 * 地表水
	 * 
	 * 返回值：
	 */
	public List<Combobox> dbsList();
	/**
	 * 
	 * 函数介绍：废水受纳水体功能类别下拉列表
	 * 
	 * 海水
	 * 
	 * 返回值：
	 */
	public List<Combobox> hsList();
	/**
	 * 
	 * 函数介绍： 清净下水受纳水体功能类别下拉列表
	 * 
	 * 海水
	 * 
	 * 返回值：
	 */
	public List<Combobox> qjhsList();
	/**
	 * 
	 * 函数介绍： 清净下水受纳水体功能类别下拉列表
	 * 
	 * 地表水
	 * 
	 * 返回值：
	 */
	public List<Combobox> qjdbsList();
	public void saveWaterForm(WaterForm waterForm)throws AppException;
	public void saveWaterProjectForm(WaterProjectForm waterProjectForm)throws AppException;
	public FyWebResult waterList(String pid,String isActive,String page, String pageSize) throws AppException;
	public FyWebResult waterProjectList(String pid,String isActive,String page, String pageSize) throws AppException;
	public WaterForm editWaterForm(WaterForm waterForm)throws AppException;
	public WaterProjectForm editWaterProjectForm(WaterProjectForm waterProjectForm)throws AppException;
	public WaterProjectForm infoWaterProjectForm(WaterProjectForm waterProjectForm) throws AppException;
	public WaterForm infoWaterForm(WaterForm waterForm) throws AppException;
	public void removeWater(String id) throws AppException;
	public void removeWaterProject(String id) throws AppException;
	
}
