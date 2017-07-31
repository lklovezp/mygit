package com.hnjz.app.work.adjust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.zfdx.lawobjf.LawobjfManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
@Service("adjustCompanyManagerImpl")
public class AdjustCompanyManagerImpl extends ManagerImpl implements AdjustCompanyManager {
  private static final Log log=LogFactory.getLog(AdjustCompanyManagerImpl.class);
  @Autowired
  private UserManager userManager;
  @Autowired
  private LawobjManager lawobjManager;
  @Autowired
  private IndexManager indexManager;
  @Autowired
  private LawobjfManager tdatalawobjfManager;
  @Override
	public JSONArray queryOrgCompany(String name ,String isActive) throws Exception {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();

		//查询所有部门人员
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where 1=1 ");
		String areaId = CtxUtil.getAreaId();
		if (!CtxUtil.getCurUser().getIssys().equals("Y") && StringUtils.isNotBlank(areaId)) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		// 如果是管理员但不是超级管理员 加区域限制
		if (CtxUtil.getCurUser().getIssys().equals("Y") && !CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :name ");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by orderby ");
		List<TSysOrg> pos = dao.find(sql.toString(), data);
		log.info("测试数据pos====");
		    for(TSysOrg tSysOrg:pos){
		    	if(tSysOrg.getOrg()==null){
		    		obj.put("id", tSysOrg.getId());
		    		obj.put("name", tSysOrg.getName());
		    		obj.put("href", "#");
		    		arr.put(obj);
		    		
		    	}else{
		    		obj=new JSONObject();
				    obj.put("id", tSysOrg.getId());
					obj.put("name", tSysOrg.getName());
					obj.put("pId", tSysOrg.getOrg().getId());
					obj.put("href", "#");
					arr.put(obj);
		    	}
 
				
			}
	
		
		
		return arr;
	}
  /*
   //获取执法对象类型 
	@Override
	public  FyWebResult queryDicTypeEnum() throws Exception {
		String type=DicTypeEnum.LAWTYPE.getCode();
		List<TSysDic> list = this.find(" from TSysDic t where t.type = ? order by t.orderby ", type);
		List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
		Map<String, String> mapEnums=null;
		for(TSysDic tSysDic:list){
			mapEnums=new HashMap<String, String>();
			mapEnums.put("code", tSysDic.getCode());
			mapEnums.put("name", tSysDic.getName());
			listMap.add(mapEnums);
		}
		FyWebResult frFyWebResult =new FyWebResult();
		frFyWebResult.setRows(listMap);
		return frFyWebResult;
	}*/

	@Override
	public FyWebResult queryCompanyByCodeAndOrgId(String code, String orgId,String name,String regionId,String qyzt,String kzlx,String isActive,String page,String pageSize)
			throws Exception {
		FyWebResult fr=new FyWebResult();
		if(StringUtil.isBlank(orgId)){
			//oreId为空不加载数据
			FyResult pos =new FyResult();// this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			fr = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fr.setRows(rows);
		}else{
		     fr=tdatalawobjfManager.queryTdatalawobjf("", "", code, "", orgId, "", page, pageSize);
		}
		
		return fr;
	}

	@Override
	public FyWebResult queryCompanyByCode(String code, String name, String OrgId,String page,String pageSize)
			throws Exception {
		FyWebResult fr=new FyWebResult();
		fr=tdatalawobjfManager.queryTdatalawobjf("", "", code, "", "", "", page, pageSize);
		/*if(code.equals("01")){
			fr=lawobjManager.queryGywryList("","", "","", "", "", "", "Y", page, pageSize);
		}else if(code.equals("02")){
			fr=lawobjManager.queryJsxmList("","", "", "", "", "", "", "Y", "", page, pageSize);
		}else if(code.equals("03")){
			fr=lawobjManager.queryYyxxList("","", "", "", "", "", "Y", page, pageSize);
		}else if(code.equals("04")){
			fr=lawobjManager.queryGlxxList("","", "", "", "", "", "Y", page, pageSize);
		}else if(code.equals("05")){
			fr=lawobjManager.queryJzgdList("","", "", "", "", "", "Y", "", page, pageSize);
		}else if(code.equals("06")){
			fr=lawobjManager.queryScxxList("","", "", "", "", "", "", "Y", page, pageSize);
		}else if(code.equals("07")){
			fr=lawobjManager.queryXqyzList("","", "", "", "", "", "Y", page, pageSize);
		}else if(code.equals("08")){
			fr=lawobjManager.queryFwyList("","", "", "", "", "Y", page, pageSize);
		}else if(code.equals("09")){
			fr=lawobjManager.queryYsyList("","", "", "", "", "Y", page, pageSize);
		}else if(code.equals("10")){
			fr=lawobjManager.queryZzyList("","", "", "", "", "Y", page, pageSize);
		}else if(code.equals("11")){
			fr=lawobjManager.queryYlyList("","", "", "", "", "Y", page, pageSize);
		}*/
		
		
		return fr;
	}

	@Override
	public String updateOrg(String orgId, JSONArray array, String code)
			throws Exception {
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				this.dao.exec("update t_data_lawobjf  set ssjgbm_='"+orgId+"' where id_='"+obj.get("lawObjId")+"'");
			}
		
		return "success";
	}

	@Override
	public String deleteOrgByCompanyId(String orgId, String companyId,
			String code) throws Exception {
		 orgId="";
		
		this.dao.exec("update t_data_lawobjf  set ssjgbm_='"+orgId+"' where id_='"+companyId+"'");
		
		return "success";
	}

	@Override
	public FyWebResult shaiXuanCompanyByCode(String code, String name,
			String orgId, String page, String pageSize) throws Exception {
		FyWebResult fr=new FyWebResult();
	     fr=tdatalawobjfManager.queryssjgbmnull(page, pageSize);
		return fr;
	}
	
	@Override
	public FyWebResult queryGywryList(String year,String name, String regionId,String orgId, String qyzt, String kzlx, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.gywry_dwmc.getCode(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_ssjgbm.getCode(), 
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_dz.getCode(),
				LawobjOutColunmEnum.gywry_fddbr.getCode(),
				LawobjOutColunmEnum.gywry_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.gywry_hbfzr.getCode(), 
				LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode(),
				LawobjOutColunmEnum.gywry_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String kzlx_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=11){
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			kzlx_column = String.valueOf(listColumn.get(3));
			dz_column = String.valueOf(listColumn.get(4));
			fddbr_column = String.valueOf(listColumn.get(5));
			fddbrlxdh_column = String.valueOf(listColumn.get(6));
			hbfzr_column = String.valueOf(listColumn.get(7));
			hbfzrlxdh_column = String.valueOf(listColumn.get(8));
			qyzt_column = String.valueOf(listColumn.get(9));
			cjr_column = String.valueOf(listColumn.get(10));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,d.name_ kzlx,l.isactive_");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(", l.").append(qyzt_column);
		listSql.append(", l.").append(cjr_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on d.type_='6' and l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称模糊查询
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//控制类型
		if(StringUtils.isNotBlank(kzlx)){
			listSql.append(" and d.code_ = :kzlx ");
			condition.put("kzlx", kzlx);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			if(biaoshi.equals("1")){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("kzlx", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
			dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("fddbrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
			dataObject.put("regionid", obj[11]==null?"":String.valueOf(obj[11]));
			if("0".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[13]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryJsxmList(String year,String name, String regionId,String orgId, String lawobjId, String industryId, String isActive,String isChoose,  String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.jsxm_jsxmmc.getCode(), 
				LawobjOutColunmEnum.jsxm_ssxzq.getCode(),
				LawobjOutColunmEnum.jsxm_ssjgbm.getCode(),
				LawobjOutColunmEnum.jsxm_hylx.getCode(), 
				LawobjOutColunmEnum.jsxm_lawobjid.getCode(), 
				LawobjOutColunmEnum.jsxm_jsjdjsczt.getCode(),
				LawobjOutColunmEnum.jsxm_fddbr.getCode(),
				LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.jsxm_dwdz.getCode(),
				LawobjOutColunmEnum.jsxm_dwmc.getCode(),
				LawobjOutColunmEnum.jsxm_hbfzr.getCode(),
				LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.jsxm_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String hylx_column = null;
		String lawobjid_column = null;
		String jsjdjsczt_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String address_column = null;
		String lawobjDwmc_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String cjr_column = null;
		if(listColumn.size()!=13){
			throw new Exception("建设项目有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			hylx_column = String.valueOf(listColumn.get(3));
			lawobjid_column = String.valueOf(listColumn.get(4));
			jsjdjsczt_column = String.valueOf(listColumn.get(5));
			fddbr_column = String.valueOf(listColumn.get(6));
			fddbrlxdh_column = String.valueOf(listColumn.get(7));
			address_column = String.valueOf(listColumn.get(8));
			lawobjDwmc_column = String.valueOf(listColumn.get(9));
			hbfzr_column = String.valueOf(listColumn.get(10));
			hbfzrlxdh_column = String.valueOf(listColumn.get(11));
			cjr_column = String.valueOf(listColumn.get(12));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, d.name_ industryName,l.isactive_ ");
		listSql.append(" , l.").append(jsjdjsczt_column);
		listSql.append(" , l.").append(lawobjid_column);
		listSql.append(" , l.").append(ssxzq_column);
		listSql.append(" , l.").append(ssjgbm_column);
		listSql.append(" , l.").append(fddbr_column);
		listSql.append(" , l.").append(fddbrlxdh_column);
		listSql.append(" , l.").append(address_column);
		listSql.append(" , l.").append(lawobjDwmc_column);
		listSql.append(" , c.name_ jsjdjsczt ");
		listSql.append(" , l.").append(hbfzr_column);
		listSql.append(" , l.").append(hbfzrlxdh_column);
		listSql.append(" , l.").append(cjr_column);
		listSql.append(" , d.tolawobjtype_ ");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join  t_data_industry d on l.").append(hylx_column).append(" = d.id_ ");
		listSql.append(" left join  t_sys_dic c on c.type_ = '").append(DicTypeEnum.JSJDJSCZT.getCode()).append("' and l.").append(jsjdjsczt_column).append(" = c.code_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//建设项目名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区域
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//行业
		if(StringUtils.isNotBlank(industryId)){
			listSql.append(" and l.").append(hylx_column).append(" = :industryId ");
			condition.put("industryId", industryId);
		}
		if(StringUtils.isNotBlank(lawobjId)){
			listSql.append(" and l.").append(lawobjid_column).append(" = :lawobjId ");
			condition.put("lawobjId", lawobjId);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		if(StringUtils.isNotBlank(isChoose) && "Y".equals(isChoose)){
			listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("industryName", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
			String operHtml = OperateUtil.getOperate(String.valueOf(obj[0]));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[17]))){
				operHtml = OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				operHtml = OperateUtil.getOperate(String.valueOf(obj[0]));
			}
			if(operHtml.contains("修改") || operHtml.contains("编辑")){
				//(obj[7]==null || StringUtils.isBlank(String.valueOf(obj[7]))
				if((obj[13]==null || StringUtils.isBlank(String.valueOf(obj[13])))&& obj[18]!=null && String.valueOf(obj[6]).equals("6")){//已验收、所属执法对象为空
					if(String.valueOf(obj[18]).equals("01")){
						operHtml += "<a class='b-link' onclick='transGywry(this)' id='"+String.valueOf(obj[0])+"' >转污染源</a>";
					}else if(String.valueOf(obj[18]).equals("03")){
						operHtml += "<a class='b-link' onclick='transYy(this)' id='"+String.valueOf(obj[0])+"' >转医院</a>";
					}else if(String.valueOf(obj[18]).equals("06")){
						operHtml += "<a class='b-link' onclick='transSc(this)' id='"+String.valueOf(obj[0])+"' >转三产</a>";
					}else if(String.valueOf(obj[18]).equals("07")){
						operHtml += "<a class='b-link' onclick='transXqyz(this)' id='"+String.valueOf(obj[0])+"' >转畜禽养殖</a>";
					}else if(String.valueOf(obj[18]).equals("08")){
						operHtml += "<a class='b-link' onclick='transFwy(this)' id='"+String.valueOf(obj[0])+"' >转服务业</a>";
					}else if(String.valueOf(obj[18]).equals("09")){
						operHtml += "<a class='b-link' onclick='transYsy(this)' id='"+String.valueOf(obj[0])+"' >转饮食业</a>";
					}else if(String.valueOf(obj[18]).equals("10")){
						operHtml += "<a class='b-link' onclick='transZzy(this)' id='"+String.valueOf(obj[0])+"' >转制造业</a>";
					}else if(String.valueOf(obj[18]).equals("11")){
						operHtml += "<a class='b-link' onclick='transYly(this)' id='"+String.valueOf(obj[0])+"' >转娱乐业</a>";
					}
				}
			}
			dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("fddbr", obj[10]==null?"":String.valueOf(obj[10]));
			dataObject.put("fddbrlxdh", obj[11]==null?"":String.valueOf(obj[11]));
			dataObject.put("address", obj[12]==null?"":String.valueOf(obj[12]));
			dataObject.put("dwmc", obj[13]==null?"":String.valueOf(obj[13]));
			dataObject.put("jsjdjsczt", obj[14]==null?"":String.valueOf(obj[14]));
			dataObject.put("hbfzr", obj[15]==null?"":String.valueOf(obj[15]));
			dataObject.put("hbfzrlxdh", obj[16]==null?"":String.valueOf(obj[16]));
			dataObject.put("operate", operHtml);
			rows.add(dataObject);
		}
		return fy;
	}
    
	@Override
	public FyWebResult queryYyxxList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.yy_dwmc.getCode(),
					LawobjOutColunmEnum.yy_ssxzq.getCode(),
					LawobjOutColunmEnum.yy_ssjgbm.getCode(),
					LawobjOutColunmEnum.yy_dz.getCode(),
					LawobjOutColunmEnum.yy_fddbr.getCode(),
					LawobjOutColunmEnum.yy_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.yy_hbfzr.getCode(),
					LawobjOutColunmEnum.yy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.yy_qyzt.getCode(),
					LawobjOutColunmEnum.yy_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String address_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("医院信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			address_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, l.isactive_ ");
		listSql.append(", l.").append(address_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(", l.").append(qyzt_column);
		listSql.append(", l.").append(cjr_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//医院名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
			dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzr", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("regionid", obj[10]==null?"":String.valueOf(obj[10]));
			if("0".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[12]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryGlxxList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.gl_dwmc.getCode(), 
				LawobjOutColunmEnum.gl_ssxzq.getCode(),
				LawobjOutColunmEnum.gl_ssjgbm.getCode(),
				LawobjOutColunmEnum.gl_dz.getCode(),
				LawobjOutColunmEnum.gl_fddbr.getCode(),
				LawobjOutColunmEnum.gl_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.gl_hbfzr.getCode(),
				LawobjOutColunmEnum.gl_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.gl_qyzt.getCode(),
				LawobjOutColunmEnum.gl_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String address_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("锅炉信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			address_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, l.isactive_");
		listSql.append(", l.").append(address_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(qyzt_column);
		listSql.append(", l.").append(cjr_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
			dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("regionid", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
			if("0".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[11]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[12]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryJzgdList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String sgdwmc, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.jzgd_sgxmmc.getCode(), 
				LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
				LawobjOutColunmEnum.jzgd_ssjgbm.getCode(),
				LawobjOutColunmEnum.jzgd_dz.getCode(),
				LawobjOutColunmEnum.jzgd_fddbr.getCode(),
				LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.jzgd_hbfzr.getCode(),
				LawobjOutColunmEnum.jzgd_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.jzgd_sgdwmc.getCode(),
				LawobjOutColunmEnum.jzgd_qyzt.getCode(),
				LawobjOutColunmEnum.jzgd_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String address_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String sgdwmc_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=11){
			throw new Exception("建筑工地信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			address_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			sgdwmc_column = String.valueOf(listColumn.get(8));
			qyzt_column = String.valueOf(listColumn.get(9));
			cjr_column = String.valueOf(listColumn.get(10));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,l.isactive_ ");
		listSql.append(", l.").append(address_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(sgdwmc_column);
		listSql.append(", l.").append(qyzt_column);
		listSql.append(", l.").append(cjr_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		//施工单位名称
		if(StringUtils.isNotBlank(sgdwmc)){
			listSql.append(" and l.").append(sgdwmc_column).append(" like :sgdwmc_column ");
			condition.put("sgdwmc_column", "%"+sgdwmc+"%");
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[3]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
			dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("regionid", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
			dataObject.put("sgdwmc", obj[11]==null?"":String.valueOf(obj[11]));
			if("0".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[13]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryScxxList(String year,String name, String regionId,String orgId,String qyzt, String industryId, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.sc_dwmc.getCode(), 
				LawobjOutColunmEnum.sc_ssxzq.getCode(),
				LawobjOutColunmEnum.sc_ssjgbm.getCode(), 
				LawobjOutColunmEnum.sc_hy.getCode(), 
				LawobjOutColunmEnum.sc_dz.getCode(), 
				LawobjOutColunmEnum.sc_fddbr.getCode(),
				LawobjOutColunmEnum.sc_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.sc_hbfzr.getCode(), 
				LawobjOutColunmEnum.sc_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.sc_qyzt.getCode(),
				LawobjOutColunmEnum.sc_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String hylx_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=11){
			throw new Exception("三产信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			hylx_column = String.valueOf(listColumn.get(3));
			dz_column = String.valueOf(listColumn.get(4));
			fddbr_column = String.valueOf(listColumn.get(5));
			fddbrlxdh_column = String.valueOf(listColumn.get(6));
			hbfzr_column = String.valueOf(listColumn.get(7));
			hbfzrlxdh_column = String.valueOf(listColumn.get(8));
			qyzt_column = String.valueOf(listColumn.get(9));
			cjr_column = String.valueOf(listColumn.get(10));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", r.name_ regionname,o.name_ orgName,d.name_ industryName,l.isactive_ ")
		.append(", l.").append(dz_column)
		.append(", l.").append(fddbr_column)
		.append(", l.").append(fddbrlxdh_column)
		.append(", l.").append(hbfzr_column)
		.append(", l.").append(hbfzrlxdh_column)
		.append(", l.").append(ssxzq_column)
		.append(", l.").append(qyzt_column)
		.append(", l.").append(cjr_column)
		.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join  t_data_industry d on l.").append(hylx_column).append(" = d.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//行业类型
		if(StringUtils.isNotBlank(industryId)){
			listSql.append(" and d.id_ = :industryId ");
			condition.put("industryId", industryId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("industryName", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
			dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("fddbrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
			dataObject.put("regionid", obj[11]==null?"":String.valueOf(obj[11]));
			if("0".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[13]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryXqyzList(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.xqyz_xqyzcmc.getCode(), 
				LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
				LawobjOutColunmEnum.xqyz_ssjgbm.getCode(), 
				LawobjOutColunmEnum.xqyz_dz.getCode(), 
				LawobjOutColunmEnum.xqyz_fddbr.getCode(), 
				LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.xqyz_hbfzr.getCode(), 
				LawobjOutColunmEnum.xqyz_hbfzrlxdh.getCode(), 
				LawobjOutColunmEnum.xqyz_dwmc.getCode(),
				LawobjOutColunmEnum.xqyz_qyzt.getCode(),
				LawobjOutColunmEnum.xqyz_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String address_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String dwmc_column2 = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=11){
			throw new Exception("畜禽养殖信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			address_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			dwmc_column2 = String.valueOf(listColumn.get(8));
			qyzt_column = String.valueOf(listColumn.get(9));
			cjr_column = String.valueOf(listColumn.get(10));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,l.isactive_");
		listSql.append(", l.").append(address_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(", l.").append(dwmc_column2);
		listSql.append(", l.").append(qyzt_column);
		listSql.append(", l.").append(cjr_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属行政区
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
			dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzr", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("hbfzrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("regionid", obj[10]==null?"":String.valueOf(obj[10]));
			dataObject.put("dwmc", obj[11]==null?"":String.valueOf(obj[11]));
			if("0".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[12]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[13]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryFwyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.fwy_dwmc.getCode(), 
				LawobjOutColunmEnum.fwy_ssxzq.getCode(),
				LawobjOutColunmEnum.fwy_ssjgbm.getCode(), 
				LawobjOutColunmEnum.fwy_dz.getCode(), 
				LawobjOutColunmEnum.fwy_fddbr.getCode(), 
				LawobjOutColunmEnum.fwy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.fwy_hbfzr.getCode(), 
				LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode(), 
				LawobjOutColunmEnum.fwy_qyzt.getCode(),
				LawobjOutColunmEnum.fwy_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("服务业信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			dz_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
		.append(", l.").append(dz_column)
		.append(", l.").append(fddbr_column)
		.append(", l.").append(fddbrlxdh_column)
		.append(", l.").append(hbfzr_column)
		.append(", l.").append(hbfzrlxdh_column)
		.append(", l.").append(ssxzq_column)
		.append(", l.").append(qyzt_column)
		.append(", l.").append(cjr_column)
		.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称模糊查询
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
			dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
			if("0".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[11]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryYsyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.ysy_dwmc.getCode(), 
				LawobjOutColunmEnum.ysy_ssxzq.getCode(), 
				LawobjOutColunmEnum.ysy_ssjgbm.getCode(),
				LawobjOutColunmEnum.ysy_dz.getCode(), 
				LawobjOutColunmEnum.ysy_fddbr.getCode(), 
				LawobjOutColunmEnum.ysy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.ysy_hbfzr.getCode(), 
				LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.ysy_qyzt.getCode(),
				LawobjOutColunmEnum.ysy_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("饮食业信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			dz_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
		.append(", l.").append(dz_column)
		.append(", l.").append(fddbr_column)
		.append(", l.").append(fddbrlxdh_column)
		.append(", l.").append(hbfzr_column)
		.append(", l.").append(hbfzrlxdh_column)
		.append(", l.").append(ssxzq_column)
		.append(", l.").append(qyzt_column)
		.append(", l.").append(cjr_column)
		.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称模糊查询
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
			dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
			if("0".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[11]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryZzyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.zzy_dwmc.getCode(), 
				LawobjOutColunmEnum.zzy_ssxzq.getCode(), 
				LawobjOutColunmEnum.zzy_ssjgbm.getCode(),
				LawobjOutColunmEnum.zzy_dzh.getCode(), 
				LawobjOutColunmEnum.zzy_fddbr.getCode(), 
				LawobjOutColunmEnum.zzy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.zzy_hbfzr.getCode(), 
				LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.zzy_qyzt.getCode(),
				LawobjOutColunmEnum.zzy_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("制造业信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			dz_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
		.append(", l.").append(dz_column)
		.append(", l.").append(fddbr_column)
		.append(", l.").append(fddbrlxdh_column)
		.append(", l.").append(hbfzr_column)
		.append(", l.").append(hbfzrlxdh_column)
		.append(", l.").append(ssxzq_column)
		.append(", l.").append(qyzt_column)
		.append(", l.").append(cjr_column)
		.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称模糊查询
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
			dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
			if("0".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[11]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	@Override
	public FyWebResult queryYlyList(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize,String columnName) throws Exception {
		//添加离线版判断标识，方便sql语句的调整
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.yly_dwmc.getCode(), 
				LawobjOutColunmEnum.yly_ssxzq.getCode(),
				LawobjOutColunmEnum.yly_ssjgbm.getCode(), 
				LawobjOutColunmEnum.yly_dz.getCode(), 
				LawobjOutColunmEnum.yly_fddbr.getCode(), 
				LawobjOutColunmEnum.yly_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.yly_hbfzr.getCode(), 
				LawobjOutColunmEnum.yly_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.yly_qyzt.getCode(),
				LawobjOutColunmEnum.yly_cjr.getCode());
		String dwmc_column = null;
		String ssxzq_column = null;
		String ssjgbm_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		String qyzt_column = null;
		String cjr_column = null;
		if(listColumn.size()!=10){
			throw new Exception("娱乐业信息有字段未进行配置，请重新配置！");
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			ssjgbm_column = String.valueOf(listColumn.get(2));
			dz_column = String.valueOf(listColumn.get(3));
			fddbr_column = String.valueOf(listColumn.get(4));
			fddbrlxdh_column = String.valueOf(listColumn.get(5));
			hbfzr_column = String.valueOf(listColumn.get(6));
			hbfzrlxdh_column = String.valueOf(listColumn.get(7));
			qyzt_column = String.valueOf(listColumn.get(8));
			cjr_column = String.valueOf(listColumn.get(9));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
		.append(", l.").append(dz_column)
		.append(", l.").append(fddbr_column)
		.append(", l.").append(fddbrlxdh_column)
		.append(", l.").append(hbfzr_column)
		.append(", l.").append(hbfzrlxdh_column)
		.append(", l.").append(ssxzq_column)
		.append(", l.").append(qyzt_column)
		.append(", l.").append(cjr_column)
		.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11' and").append(" l.").append(columnName).append(" is null ");
		Map<String, Object> condition = new HashMap<String, Object>();
		//单位名称模糊查询
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//如果年份不为空，去除已抽查过的对象
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
			condition.put("year", year);
			condition.put("areaid", areaid);
		}
		listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
			dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
			if("0".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","运营中");
			}else if("1".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已关闭");
			}else if("2".equals(String.valueOf(obj[10]))){
				dataObject.put("qyzt","已停产");
			}
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[11]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
		
		
		@Override
		public FyWebResult queryGywryListByOrgId(String year,String name, String regionId,String orgId, String qyzt, String kzlx, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.gywry_dwmc.getCode(), 
					LawobjOutColunmEnum.gywry_ssxzq.getCode(),
					LawobjOutColunmEnum.gywry_ssjgbm.getCode(), 
					LawobjOutColunmEnum.gywry_kzlx.getCode(),
					LawobjOutColunmEnum.gywry_dz.getCode(),
					LawobjOutColunmEnum.gywry_fddbr.getCode(),
					LawobjOutColunmEnum.gywry_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.gywry_hbfzr.getCode(), 
					LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.gywry_qyzt.getCode(),
					LawobjOutColunmEnum.gywry_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String kzlx_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=11){
				throw new Exception("工业污染源有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				kzlx_column = String.valueOf(listColumn.get(3));
				dz_column = String.valueOf(listColumn.get(4));
				fddbr_column = String.valueOf(listColumn.get(5));
				fddbrlxdh_column = String.valueOf(listColumn.get(6));
				hbfzr_column = String.valueOf(listColumn.get(7));
				hbfzrlxdh_column = String.valueOf(listColumn.get(8));
				qyzt_column = String.valueOf(listColumn.get(9));
				cjr_column = String.valueOf(listColumn.get(10));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,d.name_ kzlx,l.isactive_");
			listSql.append(", l.").append(dz_column);
			listSql.append(", l.").append(fddbr_column);
			listSql.append(", l.").append(fddbrlxdh_column);
			listSql.append(", l.").append(hbfzr_column);
			listSql.append(", l.").append(hbfzrlxdh_column);
			listSql.append(", l.").append(ssxzq_column);
			listSql.append(", l.").append(qyzt_column);
			listSql.append(", l.").append(cjr_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append("left join t_sys_dic d on d.type_='6' and l.").append(kzlx_column).append(" = d.code_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '01' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称模糊查询
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//控制类型
			if(StringUtils.isNotBlank(kzlx)){
				listSql.append(" and d.code_ = :kzlx ");
				condition.put("kzlx", kzlx);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				if(biaoshi.equals("1")){
					listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
					condition.put("qyzt", qyzt);
				}
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("kzlx", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
				dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("fddbrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
				dataObject.put("regionid", obj[11]==null?"":String.valueOf(obj[11]));
				if("0".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");

				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryJsxmListByOrgId(String year,String name, String regionId,String orgId, String lawobjId, String industryId, String isActive,String isChoose,  String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.jsxm_jsxmmc.getCode(), 
					LawobjOutColunmEnum.jsxm_ssxzq.getCode(),
					LawobjOutColunmEnum.jsxm_ssjgbm.getCode(),
					LawobjOutColunmEnum.jsxm_hylx.getCode(), 
					LawobjOutColunmEnum.jsxm_lawobjid.getCode(), 
					LawobjOutColunmEnum.jsxm_jsjdjsczt.getCode(),
					LawobjOutColunmEnum.jsxm_fddbr.getCode(),
					LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.jsxm_dwdz.getCode(),
					LawobjOutColunmEnum.jsxm_dwmc.getCode(),
					LawobjOutColunmEnum.jsxm_hbfzr.getCode(),
					LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.jsxm_cjr.getCode(),
					LawobjOutColunmEnum.jsxm_jsdd.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String hylx_column = null;
			String lawobjid_column = null;
			String jsjdjsczt_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String address_column = null;
			String lawobjDwmc_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String cjr_column = null;
			String jsdd_column = null;
			if(listColumn.size()!=14){
				throw new Exception("建设项目有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				hylx_column = String.valueOf(listColumn.get(3));
				lawobjid_column = String.valueOf(listColumn.get(4));
				jsjdjsczt_column = String.valueOf(listColumn.get(5));
				fddbr_column = String.valueOf(listColumn.get(6));
				fddbrlxdh_column = String.valueOf(listColumn.get(7));
				address_column = String.valueOf(listColumn.get(8));
				lawobjDwmc_column = String.valueOf(listColumn.get(9));
				hbfzr_column = String.valueOf(listColumn.get(10));
				hbfzrlxdh_column = String.valueOf(listColumn.get(11));
				cjr_column = String.valueOf(listColumn.get(12));
				jsdd_column = String.valueOf(listColumn.get(13));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, d.name_ industryName,l.isactive_ ");
			listSql.append(" , l.").append(jsjdjsczt_column);
			listSql.append(" , l.").append(lawobjid_column);
			listSql.append(" , l.").append(ssxzq_column);
			listSql.append(" , l.").append(ssjgbm_column);
			listSql.append(" , l.").append(fddbr_column);
			listSql.append(" , l.").append(fddbrlxdh_column);
			listSql.append(" , l.").append(address_column);
			listSql.append(" , l.").append(lawobjDwmc_column);
			listSql.append(" , c.name_ jsjdjsczt ");
			listSql.append(" , l.").append(hbfzr_column);
			listSql.append(" , l.").append(hbfzrlxdh_column);
			listSql.append(" , l.").append(cjr_column);
			listSql.append(" , d.tolawobjtype_ ");
			listSql.append(" , l.").append(jsdd_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join  t_data_industry d on l.").append(hylx_column).append(" = d.id_ ");
			listSql.append(" left join  t_sys_dic c on c.type_ = '").append(DicTypeEnum.JSJDJSCZT.getCode()).append("' and l.").append(jsjdjsczt_column).append(" = c.code_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '02' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//建设项目名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区域
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
				if(biaoshi.equals("1")){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
				}
			}
			//行业
			if(StringUtils.isNotBlank(industryId)){
				listSql.append(" and l.").append(hylx_column).append(" = :industryId ");
				condition.put("industryId", industryId);
			}
			if(StringUtils.isNotBlank(lawobjId)){
				listSql.append(" and l.").append(lawobjid_column).append(" = :lawobjId ");
				condition.put("lawobjId", lawobjId);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			if(StringUtils.isNotBlank(isChoose) && "Y".equals(isChoose)){
				listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("industryName", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
				dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("fddbr", obj[10]==null?"":String.valueOf(obj[10]));
				dataObject.put("fddbrlxdh", obj[11]==null?"":String.valueOf(obj[11]));
				dataObject.put("address", obj[12]==null?"":String.valueOf(obj[12]));
				dataObject.put("dwmc", obj[13]==null?"":String.valueOf(obj[13]));
				dataObject.put("jsjdjsczt", obj[14]==null?"":String.valueOf(obj[14]));
				dataObject.put("hbfzr", obj[15]==null?"":String.valueOf(obj[15]));
				dataObject.put("hbfzrlxdh", obj[16]==null?"":String.valueOf(obj[16]));
				dataObject.put("jsdd", obj[19]==null?"":String.valueOf(obj[19]));
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryYyxxListByOrgId(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.yy_dwmc.getCode(),
						LawobjOutColunmEnum.yy_ssxzq.getCode(),
						LawobjOutColunmEnum.yy_ssjgbm.getCode(),
						LawobjOutColunmEnum.yy_dz.getCode(),
						LawobjOutColunmEnum.yy_fddbr.getCode(),
						LawobjOutColunmEnum.yy_fddbrlxdh.getCode(),
						LawobjOutColunmEnum.yy_hbfzr.getCode(),
						LawobjOutColunmEnum.yy_hbfzrlxdh.getCode(),
						LawobjOutColunmEnum.yy_qyzt.getCode(),
						LawobjOutColunmEnum.yy_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String address_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("医院信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				address_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, l.isactive_ ");
			listSql.append(", l.").append(address_column);
			listSql.append(", l.").append(fddbr_column);
			listSql.append(", l.").append(fddbrlxdh_column);
			listSql.append(", l.").append(hbfzr_column);
			listSql.append(", l.").append(hbfzrlxdh_column);
			listSql.append(", l.").append(ssxzq_column);
			listSql.append(", l.").append(qyzt_column);
			listSql.append(", l.").append(cjr_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '03' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//医院名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
				if(biaoshi.equals("1")){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
				}
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
				dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzr", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("regionid", obj[10]==null?"":String.valueOf(obj[10]));
				if("0".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryGlxxListByOrgId(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.gl_dwmc.getCode(), 
					LawobjOutColunmEnum.gl_ssxzq.getCode(),
					LawobjOutColunmEnum.gl_ssjgbm.getCode(),
					LawobjOutColunmEnum.gl_dz.getCode(),
					LawobjOutColunmEnum.gl_fddbr.getCode(),
					LawobjOutColunmEnum.gl_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.gl_hbfzr.getCode(),
					LawobjOutColunmEnum.gl_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.gl_qyzt.getCode(),
					LawobjOutColunmEnum.gl_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String address_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("锅炉信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				address_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, o.name_ orgName, l.isactive_");
			listSql.append(", l.").append(address_column);
			listSql.append(", l.").append(fddbr_column);
			listSql.append(", l.").append(fddbrlxdh_column);
			listSql.append(", l.").append(ssxzq_column);
			listSql.append(", l.").append(hbfzr_column);
			listSql.append(", l.").append(hbfzrlxdh_column);
			listSql.append(", l.").append(qyzt_column);
			listSql.append(", l.").append(cjr_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '04' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
				dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("regionid", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
				if("0".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[11]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryJzgdListByOrgId(String year,String name, String regionId, String orgId,String qyzt,String isActive, String sgdwmc, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.jzgd_sgxmmc.getCode(), 
					LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
					LawobjOutColunmEnum.jzgd_ssjgbm.getCode(),
					LawobjOutColunmEnum.jzgd_dz.getCode(),
					LawobjOutColunmEnum.jzgd_fddbr.getCode(),
					LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.jzgd_hbfzr.getCode(),
					LawobjOutColunmEnum.jzgd_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.jzgd_sgdwmc.getCode(),
					LawobjOutColunmEnum.jzgd_qyzt.getCode(),
					LawobjOutColunmEnum.jzgd_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String address_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String sgdwmc_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=11){
				throw new Exception("建筑工地信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				address_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				sgdwmc_column = String.valueOf(listColumn.get(8));
				qyzt_column = String.valueOf(listColumn.get(9));
				cjr_column = String.valueOf(listColumn.get(10));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,l.isactive_ ");
			listSql.append(", l.").append(address_column);
			listSql.append(", l.").append(fddbr_column);
			listSql.append(", l.").append(fddbrlxdh_column);
			listSql.append(", l.").append(ssxzq_column);
			listSql.append(", l.").append(hbfzr_column);
			listSql.append(", l.").append(hbfzrlxdh_column);
			listSql.append(", l.").append(sgdwmc_column);
			listSql.append(", l.").append(qyzt_column);
			listSql.append(", l.").append(cjr_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '05' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			//施工单位名称
			if(StringUtils.isNotBlank(sgdwmc)){
				listSql.append(" and l.").append(sgdwmc_column).append(" like :sgdwmc_column ");
				condition.put("sgdwmc_column", "%"+sgdwmc+"%");
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
				dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("regionid", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
				dataObject.put("sgdwmc", obj[11]==null?"":String.valueOf(obj[11]));
				if("0".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryScxxListByOrgId(String year,String name, String regionId,String orgId,String qyzt, String industryId, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.sc_dwmc.getCode(), 
					LawobjOutColunmEnum.sc_ssxzq.getCode(),
					LawobjOutColunmEnum.sc_ssjgbm.getCode(), 
					LawobjOutColunmEnum.sc_hy.getCode(), 
					LawobjOutColunmEnum.sc_dz.getCode(), 
					LawobjOutColunmEnum.sc_fddbr.getCode(),
					LawobjOutColunmEnum.sc_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.sc_hbfzr.getCode(), 
					LawobjOutColunmEnum.sc_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.sc_qyzt.getCode(),
					LawobjOutColunmEnum.sc_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String hylx_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=11){
				throw new Exception("三产信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				hylx_column = String.valueOf(listColumn.get(3));
				dz_column = String.valueOf(listColumn.get(4));
				fddbr_column = String.valueOf(listColumn.get(5));
				fddbrlxdh_column = String.valueOf(listColumn.get(6));
				hbfzr_column = String.valueOf(listColumn.get(7));
				hbfzrlxdh_column = String.valueOf(listColumn.get(8));
				qyzt_column = String.valueOf(listColumn.get(9));
				cjr_column = String.valueOf(listColumn.get(10));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", r.name_ regionname,o.name_ orgName,d.name_ industryName,l.isactive_ ")
			.append(", l.").append(dz_column)
			.append(", l.").append(fddbr_column)
			.append(", l.").append(fddbrlxdh_column)
			.append(", l.").append(hbfzr_column)
			.append(", l.").append(hbfzrlxdh_column)
			.append(", l.").append(ssxzq_column)
			.append(", l.").append(qyzt_column)
			.append(", l.").append(cjr_column)
			.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append("left join  t_data_industry d on l.").append(hylx_column).append(" = d.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '06' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//行业类型
			if(StringUtils.isNotBlank(industryId)){
				listSql.append(" and d.id_ = :industryId ");
				condition.put("industryId", industryId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("industryName", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[5]))?"有效":"无效");
				dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("fddbrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzr", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("hbfzrlxdh", obj[10]==null?"":String.valueOf(obj[10]));
				dataObject.put("regionid", obj[11]==null?"":String.valueOf(obj[11]));
				if("0".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryXqyzListByOrgId(String year,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.xqyz_xqyzcmc.getCode(), 
					LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
					LawobjOutColunmEnum.xqyz_ssjgbm.getCode(), 
					LawobjOutColunmEnum.xqyz_dz.getCode(), 
					LawobjOutColunmEnum.xqyz_fddbr.getCode(), 
					LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.xqyz_hbfzr.getCode(), 
					LawobjOutColunmEnum.xqyz_hbfzrlxdh.getCode(), 
					LawobjOutColunmEnum.xqyz_dwmc.getCode(),
					LawobjOutColunmEnum.xqyz_qyzt.getCode(),
					LawobjOutColunmEnum.xqyz_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String address_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String dwmc_column2 = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=11){
				throw new Exception("畜禽养殖信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				address_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				dwmc_column2 = String.valueOf(listColumn.get(8));
				qyzt_column = String.valueOf(listColumn.get(9));
				cjr_column = String.valueOf(listColumn.get(10));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname,o.name_ orgName,l.isactive_");
			listSql.append(", l.").append(address_column);
			listSql.append(", l.").append(fddbr_column);
			listSql.append(", l.").append(fddbrlxdh_column);
			listSql.append(", l.").append(hbfzr_column);
			listSql.append(", l.").append(hbfzrlxdh_column);
			listSql.append(", l.").append(ssxzq_column);
			listSql.append(", l.").append(dwmc_column2);
			listSql.append(", l.").append(qyzt_column);
			listSql.append(", l.").append(cjr_column);
			listSql.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '07' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属行政区
			if(StringUtils.isNotBlank(regionId)){
				listSql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("orgName", obj[3]==null?"":String.valueOf(obj[3]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
				dataObject.put("address", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbr", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("fddbrlxdh", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzr", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("hbfzrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
				dataObject.put("regionid", obj[10]==null?"":String.valueOf(obj[10]));
				dataObject.put("dwmc", obj[11]==null?"":String.valueOf(obj[11]));
				if("0".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[12]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryFwyListByOrgId(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.fwy_dwmc.getCode(), 
					LawobjOutColunmEnum.fwy_ssxzq.getCode(),
					LawobjOutColunmEnum.fwy_ssjgbm.getCode(), 
					LawobjOutColunmEnum.fwy_dz.getCode(), 
					LawobjOutColunmEnum.fwy_fddbr.getCode(), 
					LawobjOutColunmEnum.fwy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.fwy_hbfzr.getCode(), 
					LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode(), 
					LawobjOutColunmEnum.fwy_qyzt.getCode(),
					LawobjOutColunmEnum.fwy_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("服务业信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				dz_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
			.append(", l.").append(dz_column)
			.append(", l.").append(fddbr_column)
			.append(", l.").append(fddbrlxdh_column)
			.append(", l.").append(hbfzr_column)
			.append(", l.").append(hbfzrlxdh_column)
			.append(", l.").append(ssxzq_column)
			.append(", l.").append(qyzt_column)
			.append(", l.").append(cjr_column)
			.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '08' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称模糊查询
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
				dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
				if("0".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		
		@Override
		public FyWebResult queryYsyListByOrgId(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.ysy_dwmc.getCode(), 
					LawobjOutColunmEnum.ysy_ssxzq.getCode(), 
					LawobjOutColunmEnum.ysy_ssjgbm.getCode(),
					LawobjOutColunmEnum.ysy_dz.getCode(), 
					LawobjOutColunmEnum.ysy_fddbr.getCode(), 
					LawobjOutColunmEnum.ysy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.ysy_hbfzr.getCode(), 
					LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.ysy_qyzt.getCode(),
					LawobjOutColunmEnum.ysy_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("饮食业信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				dz_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
			.append(", l.").append(dz_column)
			.append(", l.").append(fddbr_column)
			.append(", l.").append(fddbrlxdh_column)
			.append(", l.").append(hbfzr_column)
			.append(", l.").append(hbfzrlxdh_column)
			.append(", l.").append(ssxzq_column)
			.append(", l.").append(qyzt_column)
			.append(", l.").append(cjr_column)
			.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '09' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称模糊查询
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
				dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
				if("0".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		@Override
		public FyWebResult queryZzyListByOrgId(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.zzy_dwmc.getCode(), 
					LawobjOutColunmEnum.zzy_ssxzq.getCode(), 
					LawobjOutColunmEnum.zzy_ssjgbm.getCode(),
					LawobjOutColunmEnum.zzy_dzh.getCode(), 
					LawobjOutColunmEnum.zzy_fddbr.getCode(), 
					LawobjOutColunmEnum.zzy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.zzy_hbfzr.getCode(), 
					LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.zzy_qyzt.getCode(),
					LawobjOutColunmEnum.zzy_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("制造业信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				dz_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
			.append(", l.").append(dz_column)
			.append(", l.").append(fddbr_column)
			.append(", l.").append(fddbrlxdh_column)
			.append(", l.").append(hbfzr_column)
			.append(", l.").append(hbfzrlxdh_column)
			.append(", l.").append(ssxzq_column)
			.append(", l.").append(qyzt_column)
			.append(", l.").append(cjr_column)
			.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '10' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称模糊查询
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
				dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
				if("0".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
		
		@Override
		public FyWebResult queryYlyListByOrgId(String year,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
			//添加离线版判断标识，方便sql语句的调整
			String biaoshi = indexManager.sysVer;
			StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ? ");
			sql.append(" union all");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			sql.append(" union all ");
			sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
			List<Object> listColumn = this.dao.findBySql(sql.toString(), LawobjOutColunmEnum.yly_dwmc.getCode(), 
					LawobjOutColunmEnum.yly_ssxzq.getCode(),
					LawobjOutColunmEnum.yly_ssjgbm.getCode(), 
					LawobjOutColunmEnum.yly_dz.getCode(), 
					LawobjOutColunmEnum.yly_fddbr.getCode(), 
					LawobjOutColunmEnum.yly_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.yly_hbfzr.getCode(), 
					LawobjOutColunmEnum.yly_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.yly_qyzt.getCode(),
					LawobjOutColunmEnum.yly_cjr.getCode());
			String dwmc_column = null;
			String ssxzq_column = null;
			String ssjgbm_column = null;
			String dz_column = null;
			String fddbr_column = null;
			String fddbrlxdh_column = null;
			String hbfzr_column = null;
			String hbfzrlxdh_column = null;
			String qyzt_column = null;
			String cjr_column = null;
			if(listColumn.size()!=10){
				throw new Exception("娱乐业信息有字段未进行配置，请重新配置！");
			}else{
				dwmc_column = String.valueOf(listColumn.get(0));
				ssxzq_column = String.valueOf(listColumn.get(1));
				ssjgbm_column = String.valueOf(listColumn.get(2));
				dz_column = String.valueOf(listColumn.get(3));
				fddbr_column = String.valueOf(listColumn.get(4));
				fddbrlxdh_column = String.valueOf(listColumn.get(5));
				hbfzr_column = String.valueOf(listColumn.get(6));
				hbfzrlxdh_column = String.valueOf(listColumn.get(7));
				qyzt_column = String.valueOf(listColumn.get(8));
				cjr_column = String.valueOf(listColumn.get(9));
			}
			StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(", o.name_ orgName,l.isactive_ ")
			.append(", l.").append(dz_column)
			.append(", l.").append(fddbr_column)
			.append(", l.").append(fddbrlxdh_column)
			.append(", l.").append(hbfzr_column)
			.append(", l.").append(hbfzrlxdh_column)
			.append(", l.").append(ssxzq_column)
			.append(", l.").append(qyzt_column)
			.append(", l.").append(cjr_column)
			.append(" from t_Data_Lawobj l ");
			listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
			listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
			listSql.append(" where l.LAWOBJTYPE_ = '11' ");
			Map<String, Object> condition = new HashMap<String, Object>();
			//单位名称模糊查询
			if(StringUtils.isNotBlank(name)){
				listSql.append(" and l.").append(dwmc_column).append(" like :name ");
				condition.put("name", "%"+name+"%");
			}
			//所属监管部门
			if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ = :orgid ");
					condition.put("orgid", orgId);
			}
			//企业状态
			if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
			}
			//状态
			if(StringUtils.isNotBlank(isActive)){
				listSql.append(" and l.ISACTIVE_ = :isactive ");
				condition.put("isactive", isActive);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			listSql.append(" order by r.orderby_,o.id_,l.updated_ desc, l.").append(dwmc_column);
			FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			String userId = CtxUtil.getCurUser().getId();//当前用户id
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				dataObject.put("orgName", obj[2]==null?"":String.valueOf(obj[2]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
				dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
				dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
				dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
				dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
				dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
				dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
				if("0".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					dataObject.put("qyzt","已停产");
				}
				//删除功能
				dataObject.put("operate"," <a id='"+String.valueOf(obj[0])+"' class='b-link' onclick='del(this)'>删除</a> ");
				rows.add(dataObject);
			}
			return fy;
		}
}
