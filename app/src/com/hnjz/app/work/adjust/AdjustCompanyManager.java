package com.hnjz.app.work.adjust;

import java.util.List;

import org.json.JSONArray;



import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysDic;

public interface AdjustCompanyManager extends Manager {
	/**
	 * 查询功能菜单
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryOrgCompany(String name,String isActive) throws Exception;
	/**
	 * 查询功能
	 * 
	 * @return 执法对象类型列表
	 * @throws Exception
	 
	public FyWebResult queryDicTypeEnum()throws Exception;*/
	/**
	 * 查询功能
	 * 
	 * @return 根据执法对象类型和监管部门查询单位的列表
	 * @throws Exception
	 */
	public FyWebResult queryCompanyByCodeAndOrgId(String code, String orgId,String name,String regionId,String qyzt,String kzlx,String isActive,String page,String pageSize)throws Exception;
	/**
	 * 查询功能
	 * 
	 * @return 根据执法对象类型查询单位的列表
	 * @throws Exception
	 */
	public FyWebResult queryCompanyByCode(String code,String name,String OrgId,String page,String pageSize)throws Exception;
	/**
	 * 修改功能
	 * 
	 * @return 修改所属监管部门
	 * @throws Exception
	 */
	public String updateOrg(String orgId,JSONArray array,String code)throws Exception;
	/**
	 * 删除功能
	 * 
	 * @return 删除所属监管部门
	 * @throws Exception
	 */
	public String deleteOrgByCompanyId(String orgId,String companyId,String code)throws Exception;
	/**
	 * 查询功能
	 * 
	 * @return 根据执法对象类型查询无监管部门的单位的列表
	 * @throws Exception
	 */
	public FyWebResult shaiXuanCompanyByCode(String code,String name,String OrgId,String page,String pageSize)throws Exception;
/**
 * 筛选无监管部门的企业
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
	 * 根据orgid查询企业
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
