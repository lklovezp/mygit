package com.hnjz.app.work.danger;

import java.util.List;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
/*
 * likun
 * ��ҵ�ܱ�ˮ����״������������Ŀ��
 * */

public interface WaterManager extends Manager {
	/**
	 * 
	 * �������ܣ���ˮ����ˮ�幦����������б�
	 * 
	 * �ر�ˮ
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> dbsList();
	/**
	 * 
	 * �������ܣ���ˮ����ˮ�幦����������б�
	 * 
	 * ��ˮ
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> hsList();
	/**
	 * 
	 * �������ܣ� �徻��ˮ����ˮ�幦����������б�
	 * 
	 * ��ˮ
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> qjhsList();
	/**
	 * 
	 * �������ܣ� �徻��ˮ����ˮ�幦����������б�
	 * 
	 * �ر�ˮ
	 * 
	 * ����ֵ��
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