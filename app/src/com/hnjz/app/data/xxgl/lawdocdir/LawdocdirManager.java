package com.hnjz.app.data.xxgl.lawdocdir;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
	ִ�������ֵ�manager�ӿ�
 *
 */
public interface LawdocdirManager extends Manager{
	

	/**
	 * 
	 * �������ܣ����ִ���ļ�Ŀ¼��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String queryLawdicdirTree();
	
	/**
	 * 
	 * �������ܣ�ͨ���������ͻ��ִ���ļ�Ŀ¼��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String queryLawdicdirTreeByTasktype(String tasktypeCode);
	
	/**
	 * 
	 * �������ܣ���������ִ���ļ�Ŀ¼
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveOrUpdateLawdocdir(LawdocdirForm lawdocdirForm);
	
	/**
	 * 
	 * �������ܣ���ȡִ���ļ�Ŀ¼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public LawdocdirForm getLawdocdirInfo(LawdocdirForm lawdocdirForm);

	/**
	 * 
	 * �������ܣ����Ŀ¼ ����������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> queryLawdicdirComboTree();
	
	/**
	 * 
	 * �������ܣ���ø���Ŀ¼�б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public JSONArray getLawdocDirListByPid(String pid);
	
	/**
	 * 
	 * �������ܣ�ɾ������Ŀ¼
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void removeLawdocdir(String id);
}