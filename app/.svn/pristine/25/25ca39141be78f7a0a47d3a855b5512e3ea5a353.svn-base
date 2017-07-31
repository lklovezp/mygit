package com.hnjz.app.work.danger;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

public interface AirManager extends Manager{
	/*
	 * 保存大气环境保护目标分布
	 * */
	public void saveAirProjectForm(AirProjectForm airProjectForm)throws AppException;
	/*
	 * 保存大气环境基本状况
	 * */
	public void saveAirForm(AirForm airForm)throws AppException;
	/*
	 * 查询大气环境保护目标分布列表
	 * */
	public FyWebResult airProjectList(String pid,String isActive,String page, String pageSize) throws AppException;
	/*
	 * 查询大气环境基本状况列表
	 * */
	public FyWebResult airList(String pid,String isActive,String page, String pageSize) throws AppException;
	/*
	 * 修改大气环境保护目标分布
	 * */
	public AirProjectForm editAirProjectForm(AirProjectForm airProjectForm)throws AppException;
	/*
	 * 修改大气环境基本状况
	 * */
	public AirForm editAirForm(AirForm airForm)throws AppException;
	/*
	 * 查看大气环境保护目标分布
	 * */
	public AirProjectForm infoAirProjectForm(AirProjectForm airProjectForm) throws AppException;
	/*
	 * 删除大气环境保护目标分布
	 * */
	public void removeAirProject(String id) throws AppException;
	/*
	 * 删除大气环境基本状况
	 * */
	public void removeAir(String id) throws AppException;
	/*
	 * 生成危化品word文档
	 * */
	public HashMap<String, String> buildWhpListRecord(String pid) throws Exception;




}
