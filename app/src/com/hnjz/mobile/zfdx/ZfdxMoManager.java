package com.hnjz.mobile.zfdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface ZfdxMoManager {
	/**
	 * ִ�����󸸱�����
	 */
	public List<Map<String,String>> queryLawobjf(String update);
	
	/**
	 * ִ������ҵ��ȾԴ
	 */
	public List<Map<String,String>> queryGywry(String update);
	/**
	 * ִ����������Ŀ
	 */
	public List<Map<String,String>> queryJsxm(String update);
	/**
	 * ִ������ҽԺ
	 */
	public List<Map<String,String>> queryYy(String update);
	/**
	 * ִ�������¯
	 */
	public List<Map<String,String>> queryGl(String update);
	/**
	 * ִ������������
	 */
	public List<Map<String,String>> queryJzgd(String update);
	/**
	 * ִ������������ֳ
	 */
	public List<Map<String,String>> queryXqyz(String update);
	/**
	 * ִ����������
	 */
	public List<Map<String,String>> querySc(String update);
	/**
	 * ִ������ҵ��ȾԴ
	 */
	public List<Map<String,String>> queryFwy(String update);
	
	/**
	 * ִ������ҵ��ȾԴ
	 */
	public List<Map<String,String>> queryYsy(String update);
	/**
	 * ִ������ҵ��ȾԴ
	 */
	public List<Map<String,String>> queryZzy(String update);
	/**
	 * ִ������ҵ��ȾԴ
	 */
	public List<Map<String,String>> queryYly(String update);
	
    /**
     * �ֵ��
     */
	public List<Map<String,String>> queryDic(String update);
	
	/**
	 * ִ����������
	 */
	public List<Map<String,String>> queryLawObjtype(String update);
	
	/**
	 * ���ģ���
	 */
	public List<Map<String,String>> queryJcmb(String update);   
	
	/**
	 * ��ѯ������Ա��
	 * @param update
	 * @return
	 */
	public List querytaskUser(String update,String table)throws HibernateException, SQLException;
	
	/**
	 * ��ȡ�ֻ�������ͬ�������ݿ���
	 */
   public void getMoTbdatas(String table,String datas)throws Exception;
   
   /**
    * ���ݽ�������Ϣͬ��ĳ���˵Ĵ��������б�
    */
   public List 	querytaskByUserid(String update,String userid)throws HibernateException, SQLException;
}