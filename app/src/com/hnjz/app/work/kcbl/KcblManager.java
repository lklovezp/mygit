package com.hnjz.app.work.kcbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * �����¼RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface KcblManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯ�����¼form��Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public KcblForm getKcblFormData(String applyId);
	
	/**
	 * 
	 * �������ܣ���ѯ����ѯ�ʱ�¼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List getKcxwblWtList(String applyId,String rwlx,String wflx,String kcxwbj);
	
	/**
	 * 
	 * �������ܣ���ѯ׷�ӿ���ѯ�ʱ�¼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List getMoreKcxwblWtList(String applyId,String kcxwbj);
	/**
	 * 
	 * �������ܣ�ɾ�������¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void delKcbl(String applyId);
	
	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveKcbl(KcblForm kcblForm, String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel);
	
	/**
	 * 
	 * �������ܣ����ɿ����¼doc�ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveShengchengKcbl(String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ���ȡִ��֤��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getLwnumber(String userIds);
	
	/**
	 * �õ������¼������
	 * @param applyId
	 * @param wflx
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getBlwt(String applyId, String wflx);
	
	/**
	 * �õ������ѱ���ļ���¼�������
	 * @param applyId
	 * @param kcxwbj
	 * @return
	 */
	public List getAllKcxwblWtList(String applyId,String kcxwbj);
	
	/**
	 * ����������Υ������ 
	 * @param applyId
	 * @return
	 */
	public String getWflx(String applyId);
}