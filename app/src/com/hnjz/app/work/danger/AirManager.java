package com.hnjz.app.work.danger;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

public interface AirManager extends Manager{
	/*
	 * ���������������Ŀ��ֲ�
	 * */
	public void saveAirProjectForm(AirProjectForm airProjectForm)throws AppException;
	/*
	 * ���������������״��
	 * */
	public void saveAirForm(AirForm airForm)throws AppException;
	/*
	 * ��ѯ������������Ŀ��ֲ��б�
	 * */
	public FyWebResult airProjectList(String pid,String isActive,String page, String pageSize) throws AppException;
	/*
	 * ��ѯ������������״���б�
	 * */
	public FyWebResult airList(String pid,String isActive,String page, String pageSize) throws AppException;
	/*
	 * �޸Ĵ�����������Ŀ��ֲ�
	 * */
	public AirProjectForm editAirProjectForm(AirProjectForm airProjectForm)throws AppException;
	/*
	 * �޸Ĵ�����������״��
	 * */
	public AirForm editAirForm(AirForm airForm)throws AppException;
	/*
	 * �鿴������������Ŀ��ֲ�
	 * */
	public AirProjectForm infoAirProjectForm(AirProjectForm airProjectForm) throws AppException;
	/*
	 * ɾ��������������Ŀ��ֲ�
	 * */
	public void removeAirProject(String id) throws AppException;
	/*
	 * ɾ��������������״��
	 * */
	public void removeAir(String id) throws AppException;
	/*
	 * ����Σ��Ʒword�ĵ�
	 * */
	public HashMap<String, String> buildWhpListRecord(String pid) throws Exception;




}