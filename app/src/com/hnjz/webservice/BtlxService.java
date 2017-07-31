package com.hnjz.webservice;

import javax.activation.DataHandler;


/**
 * 
 * ���ߣ�zhangqingfeng
 * �������ڣ�2016-4-1
 * �����������������߽ӿ�
 *
 */
public interface BtlxService {

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
	 * �������ܣ���ȡ�û���Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public String getUserInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ� ͬ��������Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public String getOrgInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ�������Ϣ�Ƿ�ͬ��
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
    
    /**
	 * 
	 * �������ܣ���ȡ����������
	 * ���������
	 * ����ֵ��
	 */
	public String getServDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ��ɫ����
	 * ���������
	 * ����ֵ��
	 */
	public String getRoleDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ɫ��Ϣ�Ƿ�ͬ��
	 * ���������
	 * ����ֵ��
	 */
	public String roleInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ�ϵͳ�����Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String funcInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ��������
	 * ���������
	 * ����ֵ��
	 */
	public String getFuncInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ����ܲ����Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String funcOperInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ��ɫ���ܵ�����
	 * ���������
	 * ����ֵ��
	 */
	public String getRoleFuncInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * �������ܣ��汾�������Ƿ�ͬ��
	 * ���������
	 * ����ֵ��
	 */
	public String versionInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ�汾�ŵ�����
	 * ���������
	 * ����ֵ��
	 */
	public String getVersionInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ�汾�ŵ�����
	 * ���������
	 * ����ֵ��
	 */
	public String getJcmbszInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ�ʩ����λ�����Ƿ�ͬ��
	 * ���������
	 * ����ֵ��
	 */
	public String sgdwInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡʩ����λ������
	 * ���������
	 * ����ֵ��
	 */
	public String getSgdwInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ���������˵�����
	 * ���������
	 * ����ֵ��
	 */
	public String getWghzrrInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ��������ִ���ļ�Ŀ¼������
	 * ���������
	 * ����ֵ��
	 */
	public String getDirTasktypeInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ�ִ�����������Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String ZfdxDataIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡִ�����������
	 * ���������
	 * ����ֵ��
	 */
	public String getZfdxDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ��ҵΣ����Ϣ������
	 * ���������
	 * ����ֵ��
	 */
	public String getQywhInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ������������Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String serverIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ����������Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String HpInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ����ɲ��������Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String zyclInfoListIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ��ŷõǼǱ������Ƿ�ͬ��
	 * �������������id����������
	 * ����ֵ��
	 */
	public String xfdjbInfoListIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * �������ܣ���ȡ�ŷõǼǱ���Ϣ������
	 * ���������
	 * ����ֵ��
	 */
	public String getXfdjbInfoList(String strAreaId, String strUpdated);
}