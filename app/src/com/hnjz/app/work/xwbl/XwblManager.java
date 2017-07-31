package com.hnjz.app.work.xwbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hnjz.common.manager.Manager;

/**
 * ѯ�ʱ�¼RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface XwblManager extends Manager{
	
	/**
	 * 
	 * �������ܣ���ѯѯ�ʱ�¼form��Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public XwblForm getXwblFormData(String applyId,String wflx);
	
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
	public ArrayList<HashMap<String, String>> getMoreKcxwblWtList(String applyId,String kcxwbj);
	
	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveXwbl(XwblForm xwblForm, String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel);
	
	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼doc�ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveShengchengXwbl(String applyId,String wflx);
	
	/**
	 * 
	 * �������ܣ������޸�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String saveWT(String applyId,String wtid,String wtcontent);

	public ArrayList<HashMap<String, String>> getBlwt(String applyId, String wflx);

}