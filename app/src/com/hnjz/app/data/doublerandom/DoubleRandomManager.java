package com.hnjz.app.data.doublerandom;

import java.util.List;

import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.manager.Manager;

public interface DoubleRandomManager extends Manager{
	/**
	 * ����ʷ�������ѯ year��month�µ����ñ���
	 * */
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year, String month, String areaid)throws Exception;
	/**
	 * ���������ѯ������������ص���ҵ
	 * */
	public List<TDataLawobjf> queryAllKeyLawobjfListByAreaId(String areaid)throws Exception;
	/**
	 * �����·ݣ������ѯ������� month���ܹ����ļ������ص���ҵ
	 * */
	public List<TDataLawobjf> queryKeyLawobjByAreaIdAndMonth(String year,String month,String areaid)throws Exception;
	/**
	 * ���������ѯ������ķǼ������ص���ҵ
	 * */
	public List<TDataLawobjf> queryNoMonthKeyLawobjfListByAreaId(String year,String areaid)throws Exception;
	/**
	 * ��ѡ�ص���ҵ
	 * */
	public String saveCheckKeyLawobjf(String year,String month,String areaid)throws Exception; 
	//������ݺ��·�ȷ���ѳ����������·�
	public String checkMaxMonth(String year,String month,String areaid)throws Exception;
	/**
	 * ��ѡһ����ҵ
	 * */
	public String saveCheckNormalLawobjf(String year,String month,String areaid)throws Exception;
	/**
	 * ��ȡ�ص���ҵ��һ����ҵ��������ҵ���ó�ȡ��ֱ����������ҵ������ֱ���ɷ�
	 * */
	public String saveCheckAllLawobjf(String year,String areaid)throws Exception;
	/**
	 * ���е��ɷ����ñ���
	 * */
	public List<TBizConfigcheck> queryAllConfigCheck()throws Exception;
	/**
	 * ������ҵid��ѯ����ҵ��������
	 * */
	public String queryAreaIdByLawobjfId(String id)throws Exception;
	/**
	 * ���������Υ������Ͷ�ߵ���ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryIllegalLawobjfList(String year) throws Exception;
	
	//��ѯ����һ����ҵ
	public List<String> queryNormalList(String areaId,String year) throws Exception;
	//������ݣ����ȣ�������ҵ���Ͳ�ѯ�����ҵ����
	public int queryCheckedLawobjByType(String year,String quarter,String type,String areaid)throws Exception;
	
}