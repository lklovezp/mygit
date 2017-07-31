package com.hnjz.mobile.data.xxcx;

import java.util.Map;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-3
 * ����������
	ִ������Manager�ӿ�
 *
 */
public interface LawobjMobileManager extends Manager{

	/**
	 * 
	 * �������ܣ���ҵ��ȾԴ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param kzlx:��������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryGywryList(String name, String regionId, String orgId, String kzlx, String qyzt, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ�������Ŀ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param hylx:��ҵ����
	 * @param isChoose:�Ƿ���ѡ��ִ������Y/N��
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryJsxmList(String name, String regionId, String orgId, String hylx, String isChoose, String lawobjId,String qyzt, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�ҽԺ��Ϣ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYyxxList(String name, String regionId, String orgId, String qyzt, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ����������б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryJzgdList(String name, String regionId, String orgId,String qyzt,String sgdwmc,String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ���¯�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryGlxxList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ�������Ϣ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryScxxList(String name,String industryId, String regionId, String orgId, String qyzt,String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ�������ֳ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryXqyzList(String name, String regionId, String orgId, String qyzt,String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ����ִ����������
	 * ���������lawobjId:ִ������id
	 * ����ֵ��
	 */
	public JSONArray getLawobjInfo(TDataLawobj lawobj);

	/**
	 * 
	 * �������ܣ���õ�������ִ�����󸽼�
	 * ���������
	 * ����ֵ��
	 */
	public Map<String, String> getOneFileListByPid(String pid);
	
	/**
	 * 
	 * �������ܣ����һ�����µĻ�������
	 * ���������
	 * ����ֵ��
	 */
	public Map<String,String> getOneHpxxFileByPid(String pid);

	/**
	 * 
	 * �������ܣ���õ���ִ����ʷ��¼����
	 * ���������ִ������id
	 * ����ֵ��
	 */
	public Map<String, String> getOneZfHistory(String lawobjId);

	/**
	 * 
	 * �������ܣ���ѯִ����ʷ��¼
	 * ���������
	 * ����ֵ��
	 */
	public FyWebResult queryZfHistoryList(String lawobjId, String curPage, String pageSize);

	/**
	 * 
	 * �������ܣ���ѯ���໷����Ϣ
	 * ���������
	 * ����ֵ��
	 */
	public FyWebResult queryHpxxListByPid(String pid, String page, String pageSize);
	
	/**
	 * 
	 * �������ܣ�����ѡ���ִ�������ն�ר�ã�
	 * �������������id��ִ���������͡�ִ������id���ö��Ÿ�����
	 * ����ֵ��
	 */
	public void saveChoseeLawobj(String rwid,String lawobjtype,String lawobjid);

	/**
	 * 
	 * �������ܣ�����ҵ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryFwyList(String name, String regionId, String orgId,String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ���ʳҵ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYsyList(String name, String regionId,String orgId, String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�����ҵ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryZzyList(String name, String regionId,String orgId, String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * �������ܣ�����ҵ�б�
	 * ���������
	 * @param name:��ҵ���ƣ�֧��ģ����ѯ
	 * @param regionCode:��������������
	 * @param curPage:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	 * ����ֵ��
	 * @throws Exception 
	 */
	public FyWebResult queryYlyList(String name, String regionId, String orgId, String qyzt,String page, String pageSize) throws Exception;

}