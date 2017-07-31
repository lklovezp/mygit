package com.hnjz.mobile.data.xxcx;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.InputTypeEnum;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.xxgl.lawobjdic.LawobjDicManager;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-4
 * 功能描述：
		执法对象管理managerImpl
 *
 */
@Service("lawobjMobileManager")
public class LawobjMobileManagerImpl extends ManagerImpl implements LawobjMobileManager {

	@Autowired
	private LawobjManager lawobjManager;
	@Autowired
	private LawobjDicManager lawobjDicManager;
	
	@Autowired
    private CommWorkManager    commWorkManager;
	
	@Override
	public FyWebResult queryGywryList(String name, String regionId, String orgId, String kzlx, String qyzt, String curPage, String pageSize) throws Exception {
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
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
		}
		//控制类型
		if(StringUtils.isNotBlank(kzlx)){
			listSql.append(" and d.code_ = :kzlx ");
			condition.put("kzlx", kzlx);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			//原来后台方法调整
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("kzlxname", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("fddbr", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("fddbrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
			//原来手机端写的方法
			/*dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("kzlxname", String.valueOf(obj[1]));
			dataObject.put("name", String.valueOf(obj[2]));
			dataObject.put("regionname", String.valueOf(obj[3]));
			dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));*/
			rows.add(dataObject);
		}
		return fy;
	}
	
	
	@Override
	public FyWebResult queryJsxmList(String name, String regionId, String orgId, String hylx, String isChoose,String lawobjId,String qyzt, String curPage, String pageSize) throws Exception {
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
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, d.name_ industryName ");
		listSql.append(" , l.").append(fddbr_column);
		listSql.append(" , l.").append(fddbrlxdh_column);
		listSql.append(" , l.").append(address_column);
		listSql.append(" , d.tolawobjtype_ ");
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
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
		}
		//行业
		if(StringUtils.isNotBlank(hylx)){
				listSql.append(" and l.").append(hylx_column).append(" = :hylx ");
				condition.put("hylx", hylx);
		}
		if(StringUtils.isNotBlank(lawobjId)){
			listSql.append(" and l.").append(lawobjid_column).append(" = :lawobjId ");
			condition.put("lawobjId", lawobjId);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		
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
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("hylx", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("fddbr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("address", obj[6]==null?"":String.valueOf(obj[6]));
			rows.add(dataObject);
		}
		return fy;
	}
	
	
	@Override
	public FyWebResult queryYyxxList(String name, String regionId, String orgId,String qyzt, String curPage, String pageSize) throws Exception {
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
				StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname ");
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
				//企业状态
				if(StringUtils.isNotBlank(qyzt)){
					listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
					condition.put("qyzt", qyzt);
				}
				//所属监管部门
				if(StringUtils.isNotBlank(orgId)){
					listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
					condition.put("orgid", orgId);
				}
				//状态
				listSql.append(" and l.ISACTIVE_ = 'Y' ");
				
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("1")){//师级
					listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
					condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
					listSql.append(" and r.id_ = :regionId ");
					condition.put("regionId", area.getCode());
				}
				listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
				FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
				FyWebResult fy = new FyWebResult(pos);
				List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
				fy.setRows(rows);
				List<Object[]> listLawobj = pos.getRe();
				Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("address", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("fddbr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", obj[5]==null?"":String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryJzgdList(String name, String regionId, String orgId,String qyzt,String sgdwmc,String curPage, String pageSize) throws Exception {
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("05",
				LawobjOutColunmEnum.jzgd_sgxmmc.getCode(),
				LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
				LawobjOutColunmEnum.jzgd_dz.getCode(),
				LawobjOutColunmEnum.jzgd_fddbr.getCode(),
				LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.jzgd_qyzt.getCode(),
				LawobjOutColunmEnum.jzgd_ssjgbm.getCode(),
				LawobjOutColunmEnum.jzgd_sgdwmc.getCode());
		if(listColumn.size()!=8){
			throw new Exception("建筑工地信息有字段未进行配置，请重新配置！");
		}
		String dwmc_column = String.valueOf(listColumn.get(0));
		String ssxzq_column = String.valueOf(listColumn.get(1));
		String dz_column = String.valueOf(listColumn.get(2));
		String fddbr_column = String.valueOf(listColumn.get(3));
		String fddbrlxdh_column = String.valueOf(listColumn.get(4));
		String qyzt_column = String.valueOf(listColumn.get(5));
		String ssjgbm_column = String.valueOf(listColumn.get(6));
		String sgdwmc_column = String.valueOf(listColumn.get(7));
		
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05' and l.isactive_ = 'Y' ");
		listSql.append(" and l.").append(qyzt_column).append(" = '0' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//施工单位名称
		if(StringUtils.isNotBlank(sgdwmc)){
			listSql.append(" and l.").append(sgdwmc_column).append(" like :sgdwmc_column ");
			condition.put("sgdwmc_column", "%"+sgdwmc+"%");
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
				listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
				condition.put("qyzt", qyzt);
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
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("address", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("fddbr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", obj[5]==null?"":String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryGlxxList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception {
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("04",
				LawobjOutColunmEnum.gl_dwmc.getCode(),
				LawobjOutColunmEnum.gl_ssxzq.getCode(),
				LawobjOutColunmEnum.gl_dz.getCode(),
				LawobjOutColunmEnum.gl_fddbr.getCode(),
				LawobjOutColunmEnum.gl_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.gl_qyzt.getCode(),
				LawobjOutColunmEnum.gl_ssjgbm.getCode());
		if(listColumn.size()!=7){
			throw new Exception("锅炉信息有字段未进行配置，请重新配置！");
		}
		String dwmc_column = String.valueOf(listColumn.get(0));
		String ssxzq_column = String.valueOf(listColumn.get(1));
		String dz_column = String.valueOf(listColumn.get(2));
		String fddbr_column = String.valueOf(listColumn.get(3));
		String fddbrlxdh_column = String.valueOf(listColumn.get(4));
		String qyzt_column = String.valueOf(listColumn.get(5));
		String ssjgbm_column = String.valueOf(listColumn.get(6));
		
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04' and l.isactive_ = 'Y' ");
		listSql.append(" and l.").append(qyzt_column).append(" = '0' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
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
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("regionname", String.valueOf(obj[2]));
			dataObject.put("address", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("fddbr", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", obj[5]==null?"":String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryScxxList(String name, String industryId, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception {
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("06",
				LawobjOutColunmEnum.sc_dwmc.getCode(),
				LawobjOutColunmEnum.sc_hy.getCode(),
				LawobjOutColunmEnum.sc_ssxzq.getCode(),
				LawobjOutColunmEnum.sc_dz.getCode(),
				LawobjOutColunmEnum.sc_fddbr.getCode(),
				LawobjOutColunmEnum.sc_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.sc_qyzt.getCode(),
				LawobjOutColunmEnum.sc_ssjgbm.getCode());
		if(listColumn.size()!=8){
			throw new Exception("三产信息有字段未进行配置，请重新配置！");
		}
		String dwmc_column = String.valueOf(listColumn.get(0));
		String hylx_column = String.valueOf(listColumn.get(1));
		String ssxzq_column = String.valueOf(listColumn.get(2));
		String dz_column = String.valueOf(listColumn.get(3));
		String fddbr_column = String.valueOf(listColumn.get(4));
		String fddbrlxdh_column = String.valueOf(listColumn.get(5));
		String qyzt_column = String.valueOf(listColumn.get(6));
		String ssjgbm_column = String.valueOf(listColumn.get(7));
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(hylx_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join  t_data_industry d on l.").append(hylx_column).append(" = d.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06' and l.isactive_ = 'Y' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		if(StringUtils.isNotBlank(industryId)){
			listSql.append(" and d.id_ = :industryId ");
			condition.put("industryId", industryId);
		}
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
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
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("regionname", String.valueOf(obj[2]));
			dataObject.put("address", String.valueOf(obj[3]));
			dataObject.put("fddbr", String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryXqyzList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception {
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("07",
				LawobjOutColunmEnum.xqyz_xqyzcmc.getCode(),
				LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
				LawobjOutColunmEnum.xqyz_dz.getCode(),
				LawobjOutColunmEnum.xqyz_fddbr.getCode(),
				LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.xqyz_qyzt.getCode(),
				LawobjOutColunmEnum.xqyz_ssjgbm.getCode());
		if(listColumn.size()!=7){
			throw new Exception("畜禽养殖有字段未进行配置，请重新配置！");
		}
		String dwmc_column = String.valueOf(listColumn.get(0));
		String ssxzq_column = String.valueOf(listColumn.get(1));
		String dz_column = String.valueOf(listColumn.get(2));
		String fddbr_column = String.valueOf(listColumn.get(3));
		String fddbrlxdh_column = String.valueOf(listColumn.get(4));
		String qyzt_column = String.valueOf(listColumn.get(5));
		String ssjgbm_column = String.valueOf(listColumn.get(6));
		
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" left join t_sys_org o on ").append(" l.").append(ssjgbm_column).append(" = o.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07' and l.isactive_ = 'Y' ");
		listSql.append(" and l.").append(qyzt_column).append(" = '0' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			listSql.append(" and l.").append(dwmc_column).append(" like :name ");
			condition.put("name", "%"+name+"%");
		}
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		if(StringUtils.isNotBlank(regionId)){
			listSql.append(" and r.id_ = :regionid ");
			condition.put("regionid", regionId);
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
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("regionname", String.valueOf(obj[2]));
			dataObject.put("address", String.valueOf(obj[3]));
			dataObject.put("fddbr", String.valueOf(obj[4]));
			dataObject.put("fddbrlxdh", String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		return fy;
	}


	@Override
	public JSONArray getLawobjInfo(TDataLawobj lawobj) {
		JSONArray array = new JSONArray();
		List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList(lawobj.getLawobjtype());
		try {
			for(int i=0;i<list.size();i++){
				TDataLawobjdic tDataLawobjdic = list.get(i);
				if(!InputTypeEnum.hidden.getCode().equals(tDataLawobjdic.getInputtype())){
					JSONObject obj = new JSONObject();
					String key = tDataLawobjdic.getColchiname();
					String value = lawobjManager.detailInnerHtmlHelp(tDataLawobjdic, lawobj);
					obj.put("key", key+"：");
					obj.put("value", value);
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@Override
	public Map<String, String> getOneFileListByPid(String pid) {
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = ? order by f.updated_ desc";
		List<Object[]> listObj = this.dao.findBySql(sql, pid);
		if(listObj.size()>0){
			Map<String, String> map = new HashMap<String, String>();
			Object[] obj = listObj.get(0);
			map.put("id", obj[0] == null ? "" : String.valueOf(obj[0]));
			map.put("filetype", obj[1] == null ? "" : String.valueOf(obj[1]));
			map.put("filename", obj[2] == null ? "" : String.valueOf(obj[2]));
			map.put("url", obj[0] == null ? "" : "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				map.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			BigDecimal big = obj[3] == null ? BigDecimal.ZERO : (BigDecimal) obj[3];
			map.put("filesize", FileUtil.sizeFormat(big.longValue()));
			return map;
		}
		return null;
	}
	
	@Override
	public Map<String,String> getOneHpxxFileByPid(String pid){
		List<TDataLawobjdic> list = this.find("from TDataLawobjdic d where d.enumname = ?", LawobjOutColunmEnum.jsxm_lawobjid.getCode());
		if (list.size() > 0) {
			String columnName = list.get(0).getColengname();
			List<Object> listId = this.dao.findBySql("select id_ from t_data_lawobj l where l." + columnName + " = ?", pid);
			if (listId.size() > 0) {
				for (int i = 0; i < listId.size(); i++) {
					if (i == 0) {
						pid = "'" + String.valueOf(listId.get(0)) + "'";
					} else {
						pid += "'" + String.valueOf(listId.get(i)) + "'";
					}
					if (i != listId.size() - 1) {
						pid += ",";
					}
				}
			} else {
				pid = "'" + pid + "'";
			}
		} else {
			pid = "'" + pid + "'";
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select f.id_,a.docnum1_,to_char(a.docnum1date_,'yyyy-MM-dd'),f.name_,f.size_ ");
		sql.append(" from t_data_lawobj o ");
		sql.append(" left join  t_data_lawobjeia a  on a.pid_ = o.id_ ");
		sql.append(" left join  t_data_file f on a.id_ = f.pid_ ");
		sql.append(" where f.id_ is not null and a.pid_ in (").append(pid).append(")");
		sql.append(" and f.type_ = ? ");
		sql.append(" order by o.updated_ desc,a.updated_ desc,f.updated_ desc ");
		List<Object[]> listObj = dao.findBySql(sql.toString(), FileTypeEnums.HPXXHPSPWH.getCode());
		if(listObj.size()>0){
			Map<String,String> map = new HashMap<String,String>();
			Object[] obj = listObj.get(0);
			map.put("fileid", obj[0] == null ? "" : String.valueOf(obj[0]));
			map.put("spwh", obj[1] == null ? "" : String.valueOf(obj[1]));
			map.put("spsj", obj[2] == null ? "" : String.valueOf(obj[2]));
			map.put("filename", obj[3] == null ? "" : String.valueOf(obj[3]));
			BigDecimal big = obj[4] == null ? BigDecimal.ZERO : (BigDecimal) obj[4];
			map.put("filesize", FileUtil.sizeFormat(big.longValue()));
			map.put("url", obj[0] == null ? "" : "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[3] != null && FileUtil.isImage(String.valueOf(obj[3]))){
				map.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			return map;
		}
		return null;
	}
	

	@Override
	public FyWebResult queryHpxxListByPid(String pid, String page, String pageSize) {
		List<TDataLawobjdic> list = this.find("from TDataLawobjdic d where d.enumname = ?", LawobjOutColunmEnum.jsxm_lawobjid.getCode());
		if (list.size() > 0) {
			String columnName = list.get(0).getColengname();
			List<Object> listId = this.dao.findBySql("select id_ from t_data_lawobj l where l." + columnName + " = ?", pid);
			if (listId.size() > 0) {
				for (int i = 0; i < listId.size(); i++) {
					if (i == 0) {
						pid = "'" + String.valueOf(listId.get(0)) + "'";
					} else {
						pid += "'" + String.valueOf(listId.get(i)) + "'";
					}
					if (i != listId.size() - 1) {
						pid += ",";
					}
				}
			} else {
				pid = "'" + pid + "'";
			}
		} else {
			pid = "'" + pid + "'";
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select h.* from ( ");
		sql.append(" select a.id_,a.docnum1_ docnum,a.docnum1date_,f.id_ fileid_,f.name_,f.size_ from t_data_lawobjeia a ");
		sql.append("  left join  t_data_file f on a.id_ = f.pid_  and f.type_ = '").append(FileTypeEnums.HPXXHPSPWH.getCode()).append("' ");
		sql.append("   where a.docnum1_ is not null  ");

		sql.append("   union all ");
		sql.append("   select a.id_,a.docnum2_ docnum,a.docnum2date_,f.id_ fileid_,f.name_,f.size_ from t_data_lawobjeia a ");
		sql.append("    left join  t_data_file f on a.id_ = f.pid_  and f.type_ = '").append(FileTypeEnums.HPXXSSCSPWH.getCode()).append("' ");
		sql.append("      where a.docnum2_ is not null  ");

		sql.append("      union all ");
		sql.append("   select a.id_,a.docnum3_ docnum,a.docnum3date_,f.id_ fileid_,f.name_,f.size_ from t_data_lawobjeia a ");
		sql.append("  left join  t_data_file f on a.id_ = f.pid_  and f.type_ = '").append(FileTypeEnums.HPXXYQSSCSPWH.getCode()).append("' ");
		sql.append("   where a.docnum3_ is not null  ");

		sql.append("    union all ");
		sql.append("    select a.id_,a.docnum4_ docnum,a.docnum4date_,f.id_ fileid_,f.name_,f.size_ from t_data_lawobjeia a   ");
		sql.append("    left join  t_data_file f on a.id_ = f.pid_  and f.type_ = '").append(FileTypeEnums.HPXXSTSYSSPWH.getCode()).append("'  ");
		sql.append("    where a.docnum4_ is not null   ");

		sql.append("      union  all ");
		sql.append("      select a.id_,a.docnum5_ docnum,a.docnum5date_,f.id_ fileid_,f.name_,f.size_ from t_data_lawobjeia a   ");
		sql.append("       left join  t_data_file f on a.id_ = f.pid_  and f.type_ = '").append(FileTypeEnums.HPXXQJSCSPWH.getCode()).append("'  ");
		sql.append("      where a.docnum5_ is not null ) h left join t_data_lawobjeia l on h.id_ = l.id_  ");
		sql.append("   where l.pid_ in (").append(pid).append(")");
		sql.append("     order by l.pid_,l.id_,h.docnum  ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize == null ? 0 : Integer.parseInt(pageSize), new HashMap<String, Object>() );
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> map = null;
		for (Object[] obj : listLawobj) {
			map = new HashMap<String, String>();
			map.put("hpxxid", obj[0] == null ? "" : String.valueOf(obj[0]));
			map.put("spwh", obj[1] == null ? "" : String.valueOf(obj[1]));
			map.put("spsj", obj[2] == null ? "" : String.valueOf(obj[2]));
			map.put("url", obj[3] == null ? "" : "/download.mo?id="+String.valueOf(obj[3]));
			if(obj[4] != null && FileUtil.isImage(String.valueOf(obj[4]))){
				map.put("imageUrl", obj[3] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[3]));
			}
			map.put("filename", obj[4] == null ? "" : String.valueOf(obj[4]));
			BigDecimal big = obj[5] == null ? BigDecimal.ZERO : (BigDecimal) obj[5];
			map.put("filesize", FileUtil.sizeFormat(big.longValue()));
			rows.add(map);
		}
		return fy;
	}
	
	@Override
	public Map<String,String> getOneZfHistory(String lawobjId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select w.id_,w.work_name_,p.name_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd'), u.name_ gdry, o.lawobjtype_ ");
		sql.append(" from work_ w  ");
		sql.append(" left join t_biz_taskandlawobj o on w.id_ = o.taskid_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_sys_user u on w.archives_user_ = u.id_ ");
		sql.append(" where w.state_ = ? and o.lawobjid_ = ?");
		sql.append(" order by w.ARCHIVES_TIME_ desc ");
		List<Object[]> list = dao.findBySql(sql.toString(), WorkState.YGD.getCode(), lawobjId);
		if(list.size()>0){
			Map<String,String> map = new HashMap<String,String>();
			Object[] obj = list.get(0);
			map.put("id", obj[0] == null ? "" : String.valueOf(obj[0]));
			map.put("rwmc", obj[1] == null ? "" : String.valueOf(obj[1]));
			map.put("tasktypename", obj[2] == null ? "" : String.valueOf(obj[2]));
			map.put("gdsj", obj[3] == null ? "" : String.valueOf(obj[3]));
			map.put("gdry", obj[4] == null ? "" : String.valueOf(obj[4]));
			map.put("lawobjtype", obj[5] == null ? "" : String.valueOf(obj[5]));
			return map;
		}
		return null;
	}
	
	@Override
	public FyWebResult queryZfHistoryList(String lawobjId, String curPage, String pageSize) {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select w.id_,w.work_name_,p.name_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd'), u.name_ gdry ");
		sql.append(" from work_ w  ");
		sql.append(" left join t_biz_taskandlawobj o on w.id_ = o.taskid_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_sys_user u on w.archives_user_ = u.id_ ");
		sql.append(" where w.state_ = :workstate and o.lawobjid_ = :lawobjid");
		condition.put("workstate", WorkState.YGD.getCode());
		condition.put("lawobjid", lawobjId);
		sql.append(" order by w.ARCHIVES_TIME_ desc ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(curPage), pageSize == null ? 0 : Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> map = null;
		for (Object[] obj : listLawobj) {
			map = new HashMap<String, String>();
			map.put("id", obj[0] == null ? "" : String.valueOf(obj[0]));
			map.put("rwmc", obj[1] == null ? "" : String.valueOf(obj[1]));
			map.put("tasktypename", obj[2] == null ? "" : String.valueOf(obj[2]));
			map.put("gdsj", obj[3] == null ? "" : String.valueOf(obj[3]));
			map.put("gdry", obj[4] == null ? "" : String.valueOf(obj[4]));
			rows.add(map);
		}
		return fy;
	}


	@Override
	public void saveChoseeLawobj(String rwid, String lawobjtype, String lawobjid) {
		try {
			String[] lawobj = lawobjid.split(",");
			List<TBizTaskandlawobj> list = this.dao.find("from TBizTaskandlawobj t where t.taskid = ?", rwid);
			//删除取消的污染源信息
			for(TBizTaskandlawobj taskandlawobj : list){
				if(!lawobjid.contains(taskandlawobj.getLawobjid())){
					this.remove(taskandlawobj);
					/**
					 * 一下两种情况清：1、单个执法对象；2、多个执法对象（之前是非专项）；
					 */
					if(lawobj.length==1){
						//清任务类型、办理内容
						//2、任务类型
						commWorkManager.saveDelRWLX(rwid);
						//3、办理页面以及子任务
						commWorkManager.saveDelBL(rwid);
					}
					ResultBean rb = commWorkManager.showBlPage(rwid);
					if(lawobj.length>0&&!rb.getResult()){
						//清任务类型、办理内容
						//2、任务类型
						commWorkManager.saveDelRWLX(rwid);
						//3、办理页面以及子任务
						commWorkManager.saveDelBL(rwid);
					}
				}
			}
			TSysUser user = CtxUtil.getCurUser();
			
			for(String str : lawobj){
				TBizTaskandlawobj tBizTaskandlawobj = null;
				for(TBizTaskandlawobj taskandlawobj : list){
					if(str.equals(taskandlawobj.getLawobjid())){
						tBizTaskandlawobj = taskandlawobj;
						break;
					}
				}
				if(tBizTaskandlawobj == null){
					tBizTaskandlawobj = new TBizTaskandlawobj();
					tBizTaskandlawobj.setCreateby(user);
					tBizTaskandlawobj.setCreated(new Date(System.currentTimeMillis()));
				}
				tBizTaskandlawobj.setTaskid(rwid);
				tBizTaskandlawobj.setLawobjtype(lawobjtype);
				tBizTaskandlawobj.setLawobjid(str);
				TDataLawobj tDataLawobj = (TDataLawobj) this.get(TDataLawobj.class, str);
				List<String> columnList = this.getLawobjPublicColumn(lawobjtype);
				tBizTaskandlawobj.setLawobjname(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(0))));
				tBizTaskandlawobj.setAddress(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(1))));
				tBizTaskandlawobj.setManager(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(2))));
				tBizTaskandlawobj.setManagermobile(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(3))));
				tBizTaskandlawobj.setBjcr(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(4))));
				tBizTaskandlawobj.setLxdh(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(5))));
				tBizTaskandlawobj.setRegionid(BeanUtils.getProperty(tDataLawobj, StringUtil.transColumnToAttr(columnList.get(6))));
				tBizTaskandlawobj.setUpdateby(user);
				tBizTaskandlawobj.setUpdated(new Date(System.currentTimeMillis()));
				tBizTaskandlawobj.setIsActive("Y");
				this.save(tBizTaskandlawobj);
			}
		} catch (IllegalAccessException e) {
			// TODO renzhengquan Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO renzhengquan Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO renzhengquan Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 函数介绍：获得执法对象的四个
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private List<String> getLawobjPublicColumn(String lawobjtype){
		List<String> list = null;
		if("01".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.gywry_dwmc.getCode(),
					LawobjOutColunmEnum.gywry_dz.getCode(),
					LawobjOutColunmEnum.gywry_fddbr.getCode(),
					LawobjOutColunmEnum.gywry_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.gywry_hbfzr.getCode(),
					LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.gywry_ssxzq.getCode()
					);
		}else if("02".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.jsxm_jsxmmc.getCode(),
					LawobjOutColunmEnum.jsxm_jsdd.getCode(),
					LawobjOutColunmEnum.jsxm_fddbr.getCode(),
					LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.jsxm_hbfzr.getCode(),
					LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.jsxm_ssxzq.getCode()
					);
		}else if("03".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.yy_dwmc.getCode(),
					LawobjOutColunmEnum.yy_dz.getCode(),
					LawobjOutColunmEnum.yy_fddbr.getCode(),
					LawobjOutColunmEnum.yy_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.yy_hbfzr.getCode(),
					LawobjOutColunmEnum.yy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.yy_ssxzq.getCode()
					);
		}else if("04".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.gl_dwmc.getCode(),
					LawobjOutColunmEnum.gl_dz.getCode(),
					LawobjOutColunmEnum.gl_fddbr.getCode(),
					LawobjOutColunmEnum.gl_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.gl_hbfzr.getCode(),
					LawobjOutColunmEnum.gl_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.gl_ssxzq.getCode()
					);
		}else if("05".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.jzgd_sgxmmc.getCode(),
					LawobjOutColunmEnum.jzgd_gcdd.getCode(),
					LawobjOutColunmEnum.jzgd_fddbr.getCode(),
					LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.jzgd_hbfzr.getCode(),
					LawobjOutColunmEnum.jzgd_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.jzgd_ssxzq.getCode()
					);
		}else if("06".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.sc_dwmc.getCode(),
					LawobjOutColunmEnum.sc_dz.getCode(),
					LawobjOutColunmEnum.sc_fddbr.getCode(),
					LawobjOutColunmEnum.sc_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.sc_hbfzr.getCode(),
					LawobjOutColunmEnum.sc_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.sc_ssxzq.getCode()
					);
		}else if("07".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.xqyz_xqyzcmc.getCode(),
					LawobjOutColunmEnum.xqyz_dz.getCode(),
					LawobjOutColunmEnum.xqyz_fddbr.getCode(),
					LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode(),
					LawobjOutColunmEnum.xqyz_hbfzr.getCode(),
					LawobjOutColunmEnum.xqyz_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.xqyz_ssxzq.getCode()
					);
		}else if("08".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.fwy_dwmc.getCode(),
					//LawobjOutColunmEnum.fwy_ssxzq.getCode(),
					//LawobjOutColunmEnum.fwy_ssjgbm.getCode(), 
					LawobjOutColunmEnum.fwy_dz.getCode(), 
					LawobjOutColunmEnum.fwy_fddbr.getCode(), 
					LawobjOutColunmEnum.fwy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.fwy_hbfzr.getCode(), 
					LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode(), 
					LawobjOutColunmEnum.fwy_ssxzq.getCode()
					//LawobjOutColunmEnum.fwy_qyzt.getCode(),
					//LawobjOutColunmEnum.fwy_cjr.getCode()
					);
		}else if("09".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.ysy_dwmc.getCode(),
					//LawobjOutColunmEnum.ysy_ssxzq.getCode(), 
					//LawobjOutColunmEnum.ysy_ssjgbm.getCode(),
					LawobjOutColunmEnum.ysy_dz.getCode(), 
					LawobjOutColunmEnum.ysy_fddbr.getCode(), 
					LawobjOutColunmEnum.ysy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.ysy_hbfzr.getCode(), 
					LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.ysy_ssxzq.getCode()
					//LawobjOutColunmEnum.ysy_qyzt.getCode(),
					//LawobjOutColunmEnum.ysy_cjr.getCode()
					);
		}else if("10".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.zzy_dwmc.getCode(),
					//LawobjOutColunmEnum.zzy_ssxzq.getCode(), 
					//LawobjOutColunmEnum.zzy_ssjgbm.getCode(),
					LawobjOutColunmEnum.zzy_dzh.getCode(), 
					LawobjOutColunmEnum.zzy_fddbr.getCode(), 
					LawobjOutColunmEnum.zzy_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.zzy_hbfzr.getCode(), 
					LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.zzy_ssxzq.getCode()
					//LawobjOutColunmEnum.zzy_qyzt.getCode(),
					//LawobjOutColunmEnum.zzy_cjr.getCode()
					);
		}else if("11".equals(lawobjtype)){
			list = lawobjManager.queryLawobjColumnByEnmu(lawobjtype, 
					LawobjOutColunmEnum.yly_dwmc.getCode(),
					//LawobjOutColunmEnum.yly_ssxzq.getCode(),
					//LawobjOutColunmEnum.yly_ssjgbm.getCode(), 
					LawobjOutColunmEnum.yly_dz.getCode(), 
					LawobjOutColunmEnum.yly_fddbr.getCode(), 
					LawobjOutColunmEnum.yly_fddbrlxdh.getCode(), 
					LawobjOutColunmEnum.yly_hbfzr.getCode(), 
					LawobjOutColunmEnum.yly_hbfzrlxdh.getCode(),
					LawobjOutColunmEnum.yly_ssxzq.getCode()
					//LawobjOutColunmEnum.yly_qyzt.getCode(),
					//LawobjOutColunmEnum.yly_cjr.getCode()
					);
		}
		return list;
	}
	
	@Override
	public FyWebResult queryFwyList(String name, String regionId, String orgId, String qyzt,String curPage, String pageSize) throws Exception {
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
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("08",
				LawobjOutColunmEnum.fwy_dwmc.getCode(),
				LawobjOutColunmEnum.fwy_ssxzq.getCode(),
				LawobjOutColunmEnum.fwy_ssjgbm.getCode(), 
				LawobjOutColunmEnum.fwy_dz.getCode(), 
				LawobjOutColunmEnum.fwy_fddbr.getCode(), 
				LawobjOutColunmEnum.fwy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.fwy_hbfzr.getCode(), 
				LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode(), 
				LawobjOutColunmEnum.fwy_qyzt.getCode(),
				LawobjOutColunmEnum.fwy_cjr.getCode());
		if(listColumn.size()!=10){
			throw new Exception("服务业信息有字段未进行配置，请重新配置！");
		}
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
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid",orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
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
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryYsyList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception {
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
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("09",
				LawobjOutColunmEnum.ysy_dwmc.getCode(),
				LawobjOutColunmEnum.ysy_ssxzq.getCode(), 
				LawobjOutColunmEnum.ysy_ssjgbm.getCode(),
				LawobjOutColunmEnum.ysy_dz.getCode(), 
				LawobjOutColunmEnum.ysy_fddbr.getCode(), 
				LawobjOutColunmEnum.ysy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.ysy_hbfzr.getCode(), 
				LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.ysy_qyzt.getCode(),
				LawobjOutColunmEnum.ysy_cjr.getCode());
		if(listColumn.size()!=10){
			throw new Exception("饮食业信息有字段未进行配置，请重新配置！");
		}
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
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
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
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryZzyList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception {
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
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("10",
				LawobjOutColunmEnum.zzy_dwmc.getCode(),
				LawobjOutColunmEnum.zzy_ssxzq.getCode(), 
				LawobjOutColunmEnum.zzy_ssjgbm.getCode(),
				LawobjOutColunmEnum.zzy_dzh.getCode(), 
				LawobjOutColunmEnum.zzy_fddbr.getCode(), 
				LawobjOutColunmEnum.zzy_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.zzy_hbfzr.getCode(), 
				LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.zzy_qyzt.getCode(),
				LawobjOutColunmEnum.zzy_cjr.getCode());
		if(listColumn.size()!=10){
			throw new Exception("制造业信息有字段未进行配置，请重新配置！");
		}
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
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
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
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryYlyList(String name, String regionId, String orgId, String qyzt, String curPage, String pageSize) throws Exception {
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
		List<String> listColumn = lawobjManager.queryLawobjColumnByEnmu("11",
				LawobjOutColunmEnum.yly_dwmc.getCode(),
				LawobjOutColunmEnum.yly_ssxzq.getCode(),
				LawobjOutColunmEnum.yly_ssjgbm.getCode(), 
				LawobjOutColunmEnum.yly_dz.getCode(), 
				LawobjOutColunmEnum.yly_fddbr.getCode(), 
				LawobjOutColunmEnum.yly_fddbrlxdh.getCode(), 
				LawobjOutColunmEnum.yly_hbfzr.getCode(), 
				LawobjOutColunmEnum.yly_hbfzrlxdh.getCode(),
				LawobjOutColunmEnum.yly_qyzt.getCode(),
				LawobjOutColunmEnum.yly_cjr.getCode());
		if(listColumn.size()!=10){
			throw new Exception("娱乐业信息有字段未进行配置，请重新配置！");
		}
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
			listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgid", orgId);
		}
		//企业状态
		if(StringUtils.isNotBlank(qyzt)){
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		listSql.append(" and l.ISACTIVE_ = 'Y' ");
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("regionname", obj[2]==null?"":String.valueOf(obj[2]));
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
			rows.add(dataObject);
		}
		return fy;
	}
	
}
