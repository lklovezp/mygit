package com.hnjz.app.data.xxgl.dataCrud;

/**
 * 
 * ���ߣ�zhangqingfeng
 * �������ڣ�2016-3-07
 * �������������ݿ�����ͬ����manager�ӿ�
 *
 */
public interface DataCrudManager {
	
	/**
	 * 
	 * �������ܣ���ҳ��ѯִ���ļ��б�
	 * ���������
	 * @param page:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 */
	public void saveDataNameList(String type, String webUrl);

	/**
	 * �������ܣ���ȡmysql����ʾʱ��ֵ
	 */
	public DataCrudForm getUpdateTimeValue(String webUrl);
	
	/**
	 * 
	 * �������ܣ�ͬ�����ȡ���µ�ʱ��ֵ
	 * ���������
	 * @param page:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 */
	public String queryUpdateddata(String type);
}