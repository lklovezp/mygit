package com.hnjz.app.work.wszz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysOrg;

public interface WszzManager extends Manager{
	/**
	 * �����û�id����û���������
	 * */
	public TSysOrg getOrgbyUser(String userId)throws Exception;
	/**
	 * �����û�id����û���������
	 * */
	public HjwfxwtzForm findHjwfxwtzFormById(String taskId,String taskTypeId)throws Exception;
	/**
	 * �����û�id����û���������
	 * */
	public void saveHjwfxwtzFormById(HjwfxwtzForm hjwfxwtzForm)throws Exception;
	/**
	 * ���ɻ���Υ����Ϊ���ڸ���֪ͨ��
	 * */
	public HashMap<String, String> buildTzd(HjwfxwtzForm hjwfxwtzForm)throws Exception;
	/**
	 * �������������������ʹ��ִ��
	 * */
	public HashMap<String, String> buildJdssdhz(JdssdhzForm jdssdhzForm)throws Exception;
	/**
	 * ���ӻ��޸����������������ʹ��ִ��
	 * */
	public void saveJdssdhzFormById(JdssdhzForm jdssdhzForm)throws Exception;
	/**
	 * ��ѯ���������������ʹ��ִ
	 * */
	public JdssdhzForm findJdssdhzFormById(String taskId,String taskTypeId)throws Exception;
	/**
	 * ���ɣ���֤����֪���ʹ��֤��
	 * */
	public HashMap<String, String> buildTzgzssdhz(TzgzssdhzForm tzgzssdhzForm)throws Exception;
	/**
	 * ���ӻ��޸ģ���֤����֪���ʹ��֤��
	 * */
	public void saveTzgzssdhzFormById(TzgzssdhzForm tzgzssdhzForm)throws Exception;
	/**
	 * ��ѯ����֤����֪���ʹ��֤
	 * */
	public TzgzssdhzForm findTzgzssdhzFormById(String taskId,String taskTypeId)throws Exception;
	
}