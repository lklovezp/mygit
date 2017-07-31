package com.hnjz.mobile.common;

import java.util.HashMap;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-9
 * ����������
		��������Manager��
 *
 */
public interface CommonMobileManager {

	/**
	 * 
	 * �������ܣ��������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray queryTasktypeTree(String lawobjtype, String markId);

	/**
	 * 
	 * �������ܣ�pid��ѯ�����б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryFileList(String pid, String fileType, String page, String rows);
	
	/**
	 * 
	 * �������ܣ���ѯ������������еĸ����б�
	
	 * �������������id
	
	 * ����ֵ��
	 */
	public JSONArray queryWorkBlFileList(String rwid);

	/**
	 * 
	 * �������ܣ���ѯ�����б�������ҳ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray queryFileList(String pid, String fileType);

	/**
	 * 
	 * �������ܣ���ѯ���񱨸渽��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray queryWorkBglxFileList(String rwid);

	/**
	 * ��ȡ�û���Ϣ
	 * @param id
	 * @return
	 */
	public HashMap<String, Object> getUserInfo(String id);

	public String savePas(String pass, String newPass, String confirmPass) throws AppException;

	/**
	 * 
	 * �������ܣ������������ͻ�ð����������ͱ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String queryBlFileType(String rwlx);
}