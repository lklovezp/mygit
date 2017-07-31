package com.hnjz.app.data.xxgl.lawobjdic;

import java.util.List;

import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.common.domain.Combobox;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
	ִ�������ֵ�manager�ӿ�
 *
 */
public interface LawobjDicManager {
	
	/**
	 * 
	 * �������ܣ����ִ��������������ֶ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> queryLawobjColumnList();
	
	/**
	 * 
	 * �������ܣ��������ִ�������ֵ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveOrUpdateLawobjDic(String lawobjtypeid,String[] id,String[] orderby,String[] colengname,String[] enumname,String[] colchiname,String[] inputtype,String[] datasource,String[] mandatory,String[] istwoitem);

	/**
	 * 
	 * �������ܣ���ȡִ�������ֶ��������б�
	
	 * ���������ִ����������
	
	 * ����ֵ��
	 */
	public List<TDataLawobjdic> queryLawobjDicList(String lawobjtypeid);

}