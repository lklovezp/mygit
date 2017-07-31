package com.hnjz.app.work.adjust;

import java.util.List;

import org.json.JSONArray;



import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysDic;

public interface AdjustCompanyManager extends Manager {
	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryOrgCompany(String name,String isActive) throws Exception;
	/**
	 * ��ѯ����
	 * 
	 * @return ִ�����������б�
	 * @throws Exception
	 
	public FyWebResult queryDicTypeEnum()throws Exception;*/
	/**
	 * ��ѯ����
	 * 
	 * @return ����ִ���������ͺͼ�ܲ��Ų�ѯ��λ���б�
	 * @throws Exception
	 */
	public FyWebResult queryCompanyByCodeAndOrgId(String code, String orgId,String name,String regionId,String qyzt,String kzlx,String isActive,String page,String pageSize)throws Exception;
	/**
	 * ��ѯ����
	 * 
	 * @return ����ִ���������Ͳ�ѯ��λ���б�
	 * @throws Exception
	 */
	public FyWebResult queryCompanyByCode(String code,String name,String OrgId,String page,String pageSize)throws Exception;
	/**
	 * �޸Ĺ���
	 * 
	 * @return �޸�������ܲ���
	 * @throws Exception
	 */
	public String updateOrg(String orgId,JSONArray array,String code)throws Exception;
	/**
	 * ɾ������
	 * 
	 * @return ɾ��������ܲ���
	 * @throws Exception
	 */
	public String deleteOrgByCompanyId(String orgId,String companyId,String code)throws Exception;
	/**
	 * ��ѯ����
	 * 
	 * @return ����ִ���������Ͳ�ѯ�޼�ܲ��ŵĵ�λ���б�
	 * @throws Exception
	 */
	public FyWebResult shaiXuanCompanyByCode(String code,String name,String OrgId,String page,String pageSize)throws Exception;
/**
 * ɸѡ�޼�ܲ��ŵ���ҵ
 * */	
	public FyWebResult queryGywryList(String year,String name, String regionId,String orgId, String qyzt, String kzlx, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryJsxmList(String year,String name, String regionId,String orgId, String lawobjId, String industryId, String isActive,String isChoose,  String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryYyxxList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryGlxxList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryJzgdList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String sgdwmc, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryScxxList(String year,String name, String regionId,String orgId,String qyzt, String industryId, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryXqyzList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryFwyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryYsyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryZzyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	public FyWebResult queryYlyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception;
	/**
	 * ����orgid��ѯ��ҵ
	 * */
	public FyWebResult queryGywryListByOrgId(String year,String name, String regionId,String orgId, String qyzt, String kzlx, String isActive, String curPage, String pageSize) throws Exception;
	public FyWebResult queryJsxmListByOrgId(String year, String name, String regionId, String orgId, String lawobjId, String industryId, String isActive,	String isChoose, String curPage, String pageSize) throws Exception;
	public FyWebResult queryYyxxListByOrgId(String year, String name, String regionId,	String orgId, String qyzt, String isActive, String curPage,	String pageSize) throws Exception;
	public FyWebResult queryGlxxListByOrgId(String year, String name, String regionId,	String orgId, String qyzt, String isActive, String curPage,	String pageSize) throws Exception;
	public FyWebResult queryJzgdListByOrgId(String year, String name, String regionId,	String orgId, String qyzt, String isActive, String sgdwmc,	String curPage, String pageSize) throws Exception;
	public FyWebResult queryScxxListByOrgId(String year, String name, String regionId,	String orgId, String qyzt, String industryId, String isActive,	String curPage, String pageSize) throws Exception;
	public FyWebResult queryXqyzListByOrgId(String year, String name, String regionId,	String orgId, String qyzt, String isActive, String curPage,	String pageSize) throws Exception;
	public FyWebResult queryFwyListByOrgId(String year, String name, String orgId,  String qyzt, String isActive, String curPage, String pageSize) throws Exception;
	public FyWebResult queryYsyListByOrgId(String year, String name, String orgId,	String qyzt, String isActive, String curPage, String pageSize) throws Exception;
	FyWebResult queryYlyListByOrgId(String year, String name, String orgId,	String qyzt, String isActive, String curPage, String pageSize)	throws Exception;
	FyWebResult queryZzyListByOrgId(String year, String name, String orgId,	String qyzt, String isActive, String curPage, String pageSize)	throws Exception;
}