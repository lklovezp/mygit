package com.hnjz.webservice;

import javax.activation.DataHandler;


/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-4-2
 * ����������
	���ŵ����ӿ�
 *
 */
public interface BtdjService {

	/**
	 * 
	 * �������ܣ������ֵ��Ƿ�ͬ��
	
	 * �������������id����������
	
	 * ����ֵ��
	 */
	public String dicDataIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���ȡ�ֵ�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getDicDataList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�����������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String areaInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ��������Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getAreaInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ�����������������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String xzqInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ����������������ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getXzqInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ��û���Ϣ�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String userInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ϣ�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getUserInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ϣ�Ƿ���ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getOrgInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ��������Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String orgInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ����������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String rwlxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ������������Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getRwlxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���ҵ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String hylxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ����ҵ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getHylxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�Υ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String wflxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ��Υ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getWflxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ�������������͹�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zfdxRwlxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ�������������͹�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZfdxRwlxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���������Υ�����͹�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String rwlxWflxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���������Υ�����͹�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getRwlxWflxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ�������ֵ��Ƿ���ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zfdxDicInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���ȡִ�������ֵ�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZfdxDicInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ�������Ƿ���ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zfdxInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���ȡִ������ͬ����Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZfdxInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ϣ�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String hpInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͬ��������Ϣ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getHpInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ϣ�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String fjxxInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getFjxxInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ͨ���ļ�id�������ļ�
	
	 * ���������fileid���ļ�id
	
	 * ����ֵ��
	 */
	public DataHandler downFile(String fileid);

	/**
	 * 
	 * �������ܣ�ִ���ļ�Ŀ¼�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zfFileDirIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ���ļ�Ŀ¼ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZfFileDirList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ���ļ��Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zfFileIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�ִ���ļ�ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZfFileList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼�������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String dataRecordIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼������ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getDataRecordList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�����¼ģ���Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String jcjlmbIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�����¼ģ��ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getJcjlmbList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String jcxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ������ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getJcxList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ��������Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String jcxdaIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ�������ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getJcxDaList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ���ȡ��Ҫ���µİ�װ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getUpdateFile();
	
	/**
	 * 
	 * �������ܣ����ɲ���Ȩ�ñ�׼�Ƿ�ͬ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String zyclInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ����ɲ���Ȩ�ñ�׼ͬ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getZyclInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ���нӿڵ�ͬ��״̬
	
	 * ������������С�ID��AREAID��UPDATED���б���json�ַ�����
	
	 * ����ֵ��ID��XXZT�б���ɵ�json�ַ���
	 */
	public String getJcxxTBZT(String strRequestJson);

	/**
	 * 
	 * �������ܣ�������°汾��
	
	 * ���������
	
	 * ����ֵ��
	 */
    public String getMaxVersionCode();
}