package com.hnjz.app.data.xxgl.lawobj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.data.enums.DataSourceEnum;
import com.hnjz.app.data.enums.InputTypeEnum;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.PublicColumnEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.Vzfdx;
import com.hnjz.app.jxkh.orgstatistics.ConditionsForm;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-4
 * 功能描述：
		执法对象管理managerImpl
 *
 */
@Service("lawobjManager")
public class LawobjManagerImpl extends ManagerImpl implements LawobjManager,ServletContextAware {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserManager userManager;
	@Autowired
    private IndexManager     indexManager;
	//用户操作常量
	private static final String operatej="<a id='%s' class='b-link' onclick='transfer(this)'>创建人转移</a>";
	private static final String operatec="<a id='%s' class='b-link' onclick='info(this)'>查看</a>";
	private static final String operates="<a id='%s' class='b-link' onclick='del(this)'>删除</a>";
	private static final String operatex="<a id='%s' class='b-link' onclick='edit(this)'>修改</a>";

	
	@Autowired
    private DicManager     dicManager;

	@Override
	public FyWebResult queryGywryList(String year,String quarter,String name, String regionId,String orgId, String qyzt, String kzlx, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
			listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
			condition.put("qyzt", qyzt);
		}
		//状态
		if(StringUtils.isNotBlank(isActive)){
			listSql.append(" and l.ISACTIVE_ = :isactive ");
			condition.put("isactive", isActive);
		}
		String areaid = CtxUtil.getAreaId();
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public String getColumnNameByEnumname(String enumname){
		List<TDataLawobjdic> list = this.dao.find("from TDataLawobjdic d where d.enumname = ?",enumname);
		if(list.size()>0){
			return list.get(0).getColengname();
		}
		return "";
	}

	@Override
	public TDataLawobj saveOrUpdateLawobj(TDataLawobj lawobj,String jsxmid)  throws Exception{
		TDataLawobj lawobjNow = null;
		if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
			lawobjNow = (TDataLawobj) this.get(TDataLawobj.class, lawobj.getId());
		}else{
			lawobjNow = new TDataLawobj();
		}
		
		String lawtype = lawobj.getLawobjtype();
		TDataLawobjdic mcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '" + lawtype + "' and enumname = ?", lawtype + PublicColumnEnum.mc.getCode()).get(0);
		String mc = mcDic.getColengname();
		String value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(mc));
		lawobjNow.setName(value);
		/*if(sczt.equals("N")){
			throw new Exception("请选择生产月份。");
		}
		if (value.contains("\\") || value.contains("/")
				|| value.contains(":") || value.contains("*")
				|| value.contains("?") || value.contains("\"")
				|| value.contains("<") || value.contains(">")
				|| value.contains("|")){
			throw new Exception("名称中不能包含【\\/:*?\"<>|】非法字符。");
		}*/
		lawobjNow.setAttribute(lawobj);
		this.save(lawobjNow);
		if(StringUtils.isNotBlank(jsxmid)){
			this.saveJsxmLawobjId(jsxmid, lawobjNow);
		}
		return lawobjNow;
	}
	
	@Override
	public void transferCjr(String lawobjtypeid, String lawobjId,String code,String userId)  throws Exception{
		if(StringUtils.isNotBlank(lawobjId)){
			StringBuffer sql1 = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = ? and d.enumname_ = ? ");
			List<Object> listColumn = this.dao.findBySql(sql1.toString(), lawobjtypeid, code);
			String cjr_column = String.valueOf(listColumn.get(0));
			StringBuffer sql = new StringBuffer("update t_data_lawobj l set l."); 
			sql.append(cjr_column).append(" = '").append(userId).append("' ");
			sql.append(" where l.id_ = '"+lawobjId+"'");
			this.dao.exec(sql.toString());
		}
	}
	
	@Override
	public TDataLawobj getLawobjInfo(TDataLawobj lawobj){
		lawobj = (TDataLawobj) this.get(TDataLawobj.class, lawobj.getId());
		return lawobj;
	}
	
	@Override
	public void removeLawobj(String id){
		TDataLawobj tDataLawobj = (TDataLawobj) this.get(TDataLawobj.class, id);
		tDataLawobj.setIsActive("N");
		tDataLawobj.setUpdateby(CtxUtil.getCurUser());
		tDataLawobj.setUpdated(new Date());
	}
	
	
	@Override
	public void saveUploadFile(MultipartFile file, String fileType) {
		try {
			String osname = UUID.randomUUID().toString().replace("-", "");
//			TDataFile tdataFile = new TDataFile("", osname, file.getOriginalFilename(), file.getSize(), fileType);
			FileUpDownUtil.copyFile(file.getInputStream(), osname, UploadFileType.WORK.getPath(), "");
//			dao.save(tdataFile);
		} catch (IOException e) {
			// TODO renzhengquan Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public FyWebResult queryJsxmList(String year,String quarter,String name, String regionId,String orgId, String lawobjId, String industryId, String isActive,String isChoose,  String curPage, String pageSize) throws Exception {
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
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
				operHtml = OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0]));
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
			dataObject.put("jsdd", obj[19]==null?"":String.valueOf(obj[19]));
			dataObject.put("operate", operHtml);
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public TDataLawobjeia saveOrUpdateHpxx(HpxxForm hpxxForm) {
		try {
			TDataLawobjeia tDataLawobjeia = null;
			if(hpxxForm!=null && StringUtils.isNotBlank(hpxxForm.getId())){
				tDataLawobjeia = (TDataLawobjeia) this.get(TDataLawobjeia.class, hpxxForm.getId());
			}
			tDataLawobjeia = hpxxForm.transToTDataLawobjeia(tDataLawobjeia);
			return (TDataLawobjeia) this.save(tDataLawobjeia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public FyWebResult queryHpxxList(String pid, String curPage, String pageSize) {
		if(StringUtils.isBlank(pid)){
			return new FyWebResult();
		}
		
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuffer hql = new StringBuffer(" from TDataLawobjeia a where a.pid = :pid");
		hql.append(" order by a.docnum1date desc ");
		data.put("pid", pid);
		FyResult pos = dao.find(hql.toString(), data, Integer.parseInt(curPage));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<TDataLawobjeia> list = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataLawobjeia ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getName());
			dataObject.put("hpspwh", ele.getDocnum1());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(ele.getDocnum1date()!=null){
				dataObject.put("spsj", sdf.format(ele.getDocnum1date()));
			}
			
			dataObject.put("operate", String.format(operatec, String.valueOf(ele.getId()))+String.format(operates, String.valueOf(ele.getId()))+String.format(operatex, String.valueOf(ele.getId())));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public HpxxForm getHpxxInfo(HpxxForm hpxxForm) {
		TDataLawobjeia lawobjeia = (TDataLawobjeia) this.get(TDataLawobjeia.class, hpxxForm.getId());
		return lawobjeia.transToHpxxForm();
	}

	@Override
	public void removeHpxx(String id) {
		TDataLawobjeia lawobjeia = (TDataLawobjeia) this.get(TDataLawobjeia.class, id);
		this.remove(lawobjeia);
	}

	@Override
	public FyWebResult queryYyxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
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
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}


	@Override
	public FyWebResult queryGlxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			
			
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}


	@Override
	public FyWebResult queryJzgdList(String year,String quarter, String name, String regionId, String orgId,String qyzt,String isActive, String sgdwmc, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			if(CtxUtil.getCurUser().getIssys().equals("N") && userId.equals(String.valueOf(obj[13]))){
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public FyWebResult queryScxxList(String year,String quarter,String name, String regionId,String orgId,String qyzt, String industryId, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}

			//如果年份和季度都不为空，去除已经添加过的企业
						if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
							listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
							condition.put("year", year);
							condition.put("quarter", quarter);
							condition.put("areaid", areaid);
						}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
		    }else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryFwyList(String year,String quarter,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}

			//如果年份和季度都不为空，去除已经添加过的企业
						if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
							listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
							condition.put("year", year);
							condition.put("quarter", quarter);
							condition.put("areaid", areaid);
						}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryYsyList(String year,String quarter,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			

			//如果年份和季度都不为空，去除已经添加过的企业
						if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
							listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
							condition.put("year", year);
							condition.put("quarter", quarter);
							condition.put("areaid", areaid);
						}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryZzyList(String year,String quarter,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}

			//如果年份和季度都不为空，去除已经添加过的企业
						if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
							listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
							condition.put("year", year);
							condition.put("quarter", quarter);
							condition.put("areaid", areaid);
						}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryYlyList(String year,String quarter,String name, String orgId, String qyzt, String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
				dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public FyWebResult queryXqyzList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception {
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
			if(biaoshi.equals("1")){
				listSql.append(" and  o.id_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgid  CONNECT BY PRIOR ID_=PID_ ) ");
				condition.put("orgid", orgId);
			}else{
				listSql.append(" and  o.id_ in (select id_ from t_sys_org where FIND_IN_SET(id_, getChildLst(:orgid))) ");
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
		if(StringUtils.isBlank(areaid)){
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
			//如果年份不为空，去除已抽查过的对象
			if(StringUtils.isNotBlank(year)&&StringUtils.isBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' and y.year_ = :year and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("areaid", areaid);
			}
			//如果年份和季度都不为空，去除已经添加过的企业
			if(StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(quarter)){
				listSql.append(" and l.id_ not in (select y.LAWOBJID_ from T_DATA_SPECIALLAWOBJ y where y.isActive_ = 'Y' and y.year_ = :year and y.quarter_ = :quarter and y.areaid_ = :areaid) ");
				condition.put("year", year);
				condition.put("quarter", quarter);
				condition.put("areaid", areaid);
			}
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
              dataObject.put("operate", OperateUtil.getEditOperate(String.valueOf(obj[0]))+OperateUtil.getOperate(String.valueOf(obj[0]))+String.format(operatej, String.valueOf(obj[0])));
			}else{
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			}
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public Map<String,StringBuffer> lawobjEditInnerHtml(List<TDataLawobjdic> list,TDataLawobj lawobj) {
		Map<String,StringBuffer> map = new HashMap<String,StringBuffer>();
		StringBuffer html;
		try {
			StringBuffer js = new StringBuffer();
			//设置状态默认值
			js.append("if($('input[name=isActive]').val()==''){$('input[name=isActive]').val('Y');}\r\n");
			html = new StringBuffer("<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>\r\n");
			map.put("js", js);
			map.put("html", html);
			for(int i=0;i<list.size();i++){
				TDataLawobjdic tDataLawobjdic = list.get(i);
				if(InputTypeEnum.hidden.getCode().equals(tDataLawobjdic.getInputtype())){//隐藏域
					String value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(tDataLawobjdic.getColengname()));
					String id = LawobjOutColunmEnum.getColumnByCode(tDataLawobjdic.getEnumname()).name();
					html.append("<input class='i-text' type='hidden'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
					html.append(" id='").append(id).append("'");
					if(StringUtils.isNotBlank(value)){
						html.append(" value='").append(value).append("' ");
					}
					html.append("/>\r\n");
				}else{
					if(tDataLawobjdic.getIstwoitem().equals("Y")){
						html.append("<tr>\r\n");
						html.append("<td width='150' bgcolor='#edfaff' align='right'>\r\n");
						if(tDataLawobjdic.getMandatory().equals("Y")){
							html.append("<label class='requiredLabel'>*</label>");
						}
						html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
						html.append("<td colspan='3'>\r\n");
						this.editInnerHtmlHelp(tDataLawobjdic,map,lawobj);
						html.append("</td>\r\n");
						html.append("</tr>\r\n");
					}else{
						html.append("<tr>\r\n");
						if(i!=list.size()-1 && list.get(i+1).getIstwoitem().equals("N")){//有下一个 下一个还不是单行显示的
							html.append("<td width='150' bgcolor='#edfaff' align='right'>\r\n");
							if(tDataLawobjdic.getMandatory().equals("Y")){
								html.append("<label class='requiredLabel'>*</label>");
							}
							html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
							html.append("<td>\r\n");
							this.editInnerHtmlHelp(tDataLawobjdic,map,lawobj);
							html.append("</td>\r\n");
							
							html.append("<td width='150' bgcolor='#edfaff' align='right'>\r\n");
							if(list.get(i+1).getMandatory().equals("Y")){
								html.append("<label class='requiredLabel'>*</label>");
							}
							html.append(list.get(i+1).getColchiname()).append("：</td>\r\n");
							html.append("<td>\r\n");
							this.editInnerHtmlHelp(list.get(i+1),map,lawobj);
							html.append("</td>\r\n");
							i++;
						}else{
							html.append("<td width='150' bgcolor='#edfaff' align='right'>\r\n");
							if(tDataLawobjdic.getMandatory().equals("Y")){
								html.append("<label class='requiredLabel'>*</label>");
							}
							html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
							html.append("<td colspan='3'>\r\n");
							this.editInnerHtmlHelp(tDataLawobjdic,map,lawobj);
							html.append("</td>\r\n");
						}
						html.append("</tr>\r\n");
					}
				}
			}
			html.append("</table>\r\n");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * 函数介绍：根据输入类型生成HTML
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private void editInnerHtmlHelp(TDataLawobjdic tDataLawobjdic,Map<String,StringBuffer> map,TDataLawobj lawobj){
		try {
			StringBuffer html = map.get("html");
			StringBuffer js = map.get("js");
			String value = "";
			String du = "";
			String fen = "";
			String miao = "";
			String jd = "";
			String wd = "";
			if(tDataLawobjdic.getLawobjtypeid().equals("01") || tDataLawobjdic.getLawobjtypeid().equals("02") || tDataLawobjdic.getLawobjtypeid().equals("03") || tDataLawobjdic.getLawobjtypeid().equals("04") || tDataLawobjdic.getLawobjtypeid().equals("05") || tDataLawobjdic.getLawobjtypeid().equals("06")){
				jd = tDataLawobjdic.getLawobjtypeid()+"08";
				wd = tDataLawobjdic.getLawobjtypeid()+"09";
			}else {
				jd = tDataLawobjdic.getLawobjtypeid()+"07";
				wd = tDataLawobjdic.getLawobjtypeid()+"08";
			}
			String jdColum = this.getColumnNameByEnumname(jd);
			String wdColum = this.getColumnNameByEnumname(wd);
			if(tDataLawobjdic.getColengname().equals(jdColum)){
				value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(jdColum));
				if(StringUtils.isNotBlank(value)){
					String val[] =  value.split(",");
					if(val.length > 1){
						miao = val[2];
						fen = val[1];
						du = val[0];
					}
				}
			}else{
				value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(tDataLawobjdic.getColengname()));
			}
			if(tDataLawobjdic.getColengname().equals(wdColum)){
				value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(wdColum));
				if(StringUtils.isNotBlank(value)){
					String val[] =  value.split(",");
					if(val.length > 1){
						miao = val[2];
						fen = val[1];
						du = val[0];
					}
				}
			}else{
				value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(tDataLawobjdic.getColengname()));
			}
			LawobjOutColunmEnum column = LawobjOutColunmEnum.getColumnByCode(tDataLawobjdic.getEnumname());
			String id = column==null?"":column.name();
			//文本框
			if(InputTypeEnum.wbk.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text easyui-validatebox' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' title='限填150字以内' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' ");
				}
				if(StringUtils.isNotBlank(value)){
					html.append(" value='").append(value).append("' ");
				}
				html.append("/>");
				if(LawobjOutColunmEnum.jsxm_dwmc.getCode().equals(tDataLawobjdic.getEnumname())){//建设项目-单位名称
					html.append(" <a id='chosee'>选择</a> ");
				}
				if(LawobjOutColunmEnum.jzgd_sgdwmc.getCode().equals(tDataLawobjdic.getEnumname())){//建筑工地-施工单位名称
					html.append(" <a id='chosee'>选择</a> ");
				}
			}
			//生产状态控件
			if(InputTypeEnum.scztkj.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input type='radio' class='radioItem' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isBlank(value) || value.indexOf("Y") != -1){
					html.append(" checked ");
				}
				html.append(" value='Y' /> 全年性生产");
				html.append("<input type='radio' class='radioItem' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("Y") == -1){
					html.append(" checked ");
				}
				html.append("value='N' /> 季节性生产");
				if(StringUtils.isNotBlank(value) && value.indexOf("Y") == -1){
					html.append("<span style='display:display;' id='season'>" );
				}else{
					html.append("<span style='display:none;' id='season'>" );
				}
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("01") != -1){
					html.append(" checked ");
				}
				html.append("value='01' /> 1月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("02") != -1){
					html.append(" checked ");
				}
				html.append(" value='02'/> 2月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("03") != -1){
					html.append(" checked ");
				}
				html.append(" value='03' /> 3月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("04") != -1){
					html.append(" checked ");
				}
				html.append(" value='04'/> 4月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("05") != -1){
					html.append(" checked ");
				}
				html.append(" value='05'/> 5月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("06") != -1){
					html.append(" checked ");
				}
				html.append(" value='06'/> 6月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("07") != -1){
					html.append(" checked ");
				}
				html.append(" value='07'/> 7月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("08") != -1){
					html.append(" checked ");
				}
				html.append(" value='08'/> 8月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("09") != -1){
					html.append(" checked ");
				}
				html.append(" value='09'/> 9月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("10") != -1){
					html.append(" checked ");
				}
				html.append(" value='10'/> 10月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("11") != -1){
					html.append(" checked ");
				}
				html.append(" value='11'/> 11月");
				html.append("<input type='checkbox' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value) && value.indexOf("12") != -1){
					html.append(" checked ");
				}
				html.append(" value='12'/> 12月</span>");
				
			}
			//经纬度输入文本框
			if(InputTypeEnum.jwsrk.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='3' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())+"du").append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				if(StringUtils.isNotBlank(du)){
					html.append(" value='").append(du).append("' ");
				}
				html.append("/>度");
				html.append("<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())+"fe").append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				if(StringUtils.isNotBlank(fen)){
					html.append(" value='").append(fen).append("' ");
				}
				html.append("/>分");
				html.append("<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())+"mi").append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				if(StringUtils.isNotBlank(miao)){
					html.append(" value='").append(miao).append("' ");
				}
				html.append("/>秒");
			}
			//只读文本框
			if(InputTypeEnum.zdwbk.getCode().equals(tDataLawobjdic.getInputtype())){
				String userId = CtxUtil.getCurUser().getId();//当前用户id
				//隐藏创建人id
				html.append("<input class='i-text' type='hidden'  name='").append(
						StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value)){
					html.append(" value='").append(value).append("' ");
				}else{
					html.append(" value='").append(userId).append("' ");
				}
				html.append("/>");
				if(StringUtils.isNotBlank(value)){
					TSysUser user = (TSysUser) this.get(TSysUser.class, value);
					html.append(user.getName());
				}else{
					html.append(CtxUtil.getCurUser().getName());
				}
			}
			//文本域
			else if(InputTypeEnum.wby.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<textarea title='限制输入150字符'  style='width:350px;' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' rows='2' class='i-text easyui-validatebox'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("' ");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				html.append(" id='").append(id).append("' ");
				html.append(">");
				if(StringUtils.isNotBlank(value)){
					html.append(value);
				}
				html.append("</textarea>");
				if(LawobjOutColunmEnum.jsxm_dwmc.getCode().equals(tDataLawobjdic.getEnumname())){//建设项目-单位名称
					html.append(" <a id='chosee'>选择</a> ");
				}
				if(LawobjOutColunmEnum.jzgd_sgdwmc.getCode().equals(tDataLawobjdic.getEnumname())){//建筑工地-施工单位名称
					html.append(" <a id='chosee'>选择</a> ");
				}
			}
			//日期
			else if(InputTypeEnum.rq.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='easyui-datebox' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("' ");
				html.append(" id='").append(id).append("'");
				html.append(" data-options='editable:false' ");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(", required:true' ");
				}
				if(StringUtils.isNotBlank(value)){
					html.append(" value='").append(value).append("' ");
				}
				html.append("/>");
			}
			//下拉列表单选
			else if(InputTypeEnum.xllbdx.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text easyui-validatebox' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(StringUtils.isNotBlank(value)){
					html.append(" value='").append(value).append("' ");
				}else{
					if(DataSourceEnum.spjgzd.getCode().equals(tDataLawobjdic.getDatasource()) || DataSourceEnum.jsjdjsczt.getCode().equals(tDataLawobjdic.getDatasource())){
						html.append(" value='1'");
					}
					//设置企业状态的默认值
					if(DataSourceEnum.qyzt.getCode().equals(tDataLawobjdic.getDatasource())){
						html.append(" value='0'");
					}
				}
				html.append("/>");
				js.append("$('input[name=").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("]').combobox({");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					js.append("required:true, ");
				}
				if(DataSourceEnum.hyxl.getCode().equals(tDataLawobjdic.getDatasource())){
					js.append("url:'").append(DataSourceEnum.getDataSourceByCode(tDataLawobjdic.getDatasource()).getUrl()).append("?lawobjType=").append(tDataLawobjdic.getLawobjtypeid()).append("',");
				}else{
					js.append("url:'").append(DataSourceEnum.getDataSourceByCode(tDataLawobjdic.getDatasource()).getUrl()).append("',");
				}
				js.append("valueField:'id',");
				js.append("textField:'name'");
				js.append("});\r\n");
			}
			//下拉列表多选
			else if(InputTypeEnum.xllbddx.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("' ");
				html.append(" id='").append(id).append("'");
				html.append("/>");
				js.append("$('input[name=").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("]').combobox({");
				js.append("multiple:true, ");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					js.append("required:true, ");
				}
				js.append("url:'").append(DataSourceEnum.getDataSourceByCode(tDataLawobjdic.getDatasource()).getUrl()).append("',")
				.append("valueField:'id',")
				.append("textField:'name'")
				.append("});\r\n");
				if(StringUtils.isNotBlank(value)){
					js.append("$('#").append(id).append("').combobox('setValues','").append(value).append("'.split(','));\r\n");
				}else{
					if(DataSourceEnum.jsxz.getCode().equals(tDataLawobjdic.getDatasource())){
						js.append("$('#").append(id).append("').combobox('setValues','").append("1").append("'.split(','));\r\n");
					}
				}
			}
			//下拉树
			else if(InputTypeEnum.xls.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				//所属行政区下拉树
				if(DataSourceEnum.ssxzqs.getCode().equals(tDataLawobjdic.getDatasource())){
					if(StringUtils.isNotBlank(value)){
						html.append(" value='").append(value).append("' ");
					}else{
						String areaId = CtxUtil.getAreaId();
						TSysArea area = (TSysArea) this.get(TSysArea.class, areaId);
						areaId = area.getCode();
						if(areaId.endsWith("00")){
							areaId = areaId.substring(0,4)+"99";
						}
						html.append(" value='").append(areaId).append("' ");
					}
					html.append("/>");
					js.append("$('input[name=").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("]').combotree({");
					if(tDataLawobjdic.getMandatory().equals("Y")){
						js.append("required:true, ");
					}
					js.append("url:'").append(DataSourceEnum.ssxzqs.getUrl()).append("'");
				}else{
					//所属部门下拉树
					if(StringUtils.isNotBlank(value)){
						html.append(" value='").append(value).append("' ");
					}else{
						String orgId = null;
						TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
						if(tSysOrg!=null){
							orgId = tSysOrg.getId();
						}
						html.append(" value='").append(orgId).append("' ");
					}
					html.append("/>");
					js.append("$('input[name=").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("]').combotree({");
					if(tDataLawobjdic.getMandatory().equals("Y")){
						js.append("required:true, ");
					}
					js.append("url:'").append(DataSourceEnum.ssjgbms.getUrl()).append("'");
				}
				js.append("});\r\n");
			}
			//数值
			else if(InputTypeEnum.number.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input class='i-text easyui-numberbox' min='0' type='text'  name='").append(StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())).append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				if(StringUtils.isNotBlank(value)){
					html.append(" value='").append(value).append("' ");
				}
				html.append("/>");
				if(LawobjOutColunmEnum.jsxm_dwmc.getCode().equals(tDataLawobjdic.getEnumname())){//建设项目-单位名称
					html.append(" <a id='chosee' class='b-link'>选择</a> ");
				}
			}
			//弹窗选择人员
			else if(InputTypeEnum.choose.getCode().equals(tDataLawobjdic.getInputtype())){
				html.append("<input type='hidden' id='"+id+"id' name='"+StringUtil.transColumnToAttr(tDataLawobjdic.getColengname())+"'/>");
				html.append("<input class='i-text ' type='text'  name='").append("'");
				html.append(" id='").append(id).append("'");
				if(tDataLawobjdic.getMandatory().equals("Y")){
					html.append(" data-options='required:true' ");
				}
				if(StringUtils.isNotBlank(value)){
					String usernames = "";
					String[] str = value.split(",");
					for(int i=0;i<str.length;i++){
						TSysUser tSysUser = (TSysUser) this.get(TSysUser.class, str[i]);
						usernames += tSysUser==null?"":tSysUser.getName();
						if(i!=str.length-1){
							usernames += ",";
						}
					}
					 js.append("$('#").append(id).append("').val('").append(usernames).append("');");
					 js.append("$('#").append(id+"id").append("').val('").append(value).append("');");
					//js.append("$('#").append(id).append("').linkbox('setValue', {id:'").append(value).append("',name:'").append(usernames).append("'});\r\n");
					
				}
				html.append("/>&nbsp;");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public String lawobjDetailInnerHtml(List<TDataLawobjdic> list,TDataLawobj lawobj) {
		StringBuffer html = new StringBuffer("<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>\r\n");
		for(int i=0;i<list.size();i++){
			TDataLawobjdic tDataLawobjdic = list.get(i);
			if(!InputTypeEnum.hidden.getCode().equals(tDataLawobjdic.getInputtype())){
				if(tDataLawobjdic.getIstwoitem().equals("Y")){
					html.append("<tr>\r\n");
					html.append("<td width='15%' bgcolor='#edfaff' align='right'>\r\n");
					html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
					html.append("<td colspan='3'>\r\n");
					html.append(detailInnerHtmlHelp(tDataLawobjdic, lawobj));
					html.append("</td>\r\n");
					html.append("<tr/>\r\n");
				}else{
					html.append("<tr>\r\n");
					if(i!=list.size()-1 && list.get(i+1).getIstwoitem().equals("N")){//有下一个 下一个还不是单行显示的
						html.append("<td width='15%'bgcolor='#edfaff' align='right'>\r\n");
						html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
						html.append("<td width='35%'>\r\n");
						html.append(detailInnerHtmlHelp(tDataLawobjdic, lawobj));
						html.append("</td>\r\n");
						html.append("<td width='15%'bgcolor='#edfaff' align='right'>\r\n");
						html.append(list.get(i+1).getColchiname()).append("：</td>\r\n");
						html.append("<td width='35%'>\r\n");
						html.append(String.valueOf(detailInnerHtmlHelp(list.get(i+1), lawobj)));
						html.append("</td>\r\n");
						i++;
					}else{
						html.append("<td width='15%' bgcolor='#edfaff' align='right'>\r\n");
						html.append(tDataLawobjdic.getColchiname()).append("：</td>\r\n");
						html.append("<td colspan='3'>\r\n");
						html.append(String.valueOf(detailInnerHtmlHelp(tDataLawobjdic, lawobj)));
						html.append("</td>\r\n");
					}
					html.append("<tr/>\r\n");
				}
			}
		}
		html.append("</table>\r\n");
		return html.toString();
	}
	
	
	@Override
	public String detailInnerHtmlHelp(TDataLawobjdic tDataLawobjdic, TDataLawobj lawobj){
		String columnValue = null;
		try {
			columnValue = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(tDataLawobjdic.getColengname()));
//			String columnValue = String.valueOf(BeanUtil.getLawobjAttrValue(lawobj,tDataLawobjdic.getColengname()));
			//弹窗选择人员
			if(InputTypeEnum.choose.getCode().equals(tDataLawobjdic.getInputtype())){
				if(StringUtil.isNotBlank(columnValue)){
					String usernames = "";
					String[] str = columnValue.split(",");
					//System.out.println("21"+columnValue);
					for(int i=0;i<str.length;i++){
						TSysUser tSysUser = (TSysUser) this.get(TSysUser.class, str[i]);
						usernames += tSysUser==null?"":tSysUser.getName();
						if(i!=str.length-1){
							usernames += ",";
						}
					}
					columnValue = usernames;
				}
			}else if(DataSourceEnum.yxzd.getCode().equals(tDataLawobjdic.getDatasource())){
				columnValue = columnValue.equals("Y")?"有效":"无效";
			}else if(DataSourceEnum.sfzd.getCode().equals(tDataLawobjdic.getDatasource())){
				if(StringUtils.isNotBlank(columnValue)){
					columnValue = columnValue.equals("Y")?"是":"否";
				}
			}else if(DataSourceEnum.kzlxzd.getCode().equals(tDataLawobjdic.getDatasource())){
				List<Object> list = this.dao.find("select name from TSysDic where type = ? and code = ?",DicTypeEnum.KZLX.getCode(),columnValue);
				columnValue = list.size()>0?String.valueOf(list.get(0)):"";
			}else if(DataSourceEnum.ssxzqs.getCode().equals(tDataLawobjdic.getDatasource())){
				TDataRegion tDataRegion = (TDataRegion) this.dao.get(TDataRegion.class, columnValue);
				columnValue = tDataRegion!=null?tDataRegion.getName():"";
			}else if(DataSourceEnum.ssjgbms.getCode().equals(tDataLawobjdic.getDatasource())){
				if(StringUtils.isNotBlank(columnValue)){
					TSysOrg tSysOrg = (TSysOrg) this.dao.get(TSysOrg.class, columnValue);
					columnValue = tSysOrg!=null?tSysOrg.getName():"";
				}
			}else if(DataSourceEnum.qyzt.getCode().equals(tDataLawobjdic.getDatasource())){
				if(columnValue!=null){
					if(columnValue.equals("0")){
						columnValue="运营中";
					}else if(columnValue.equals("1")){
						columnValue="已关闭";
					}else if(columnValue.equals("2")){
						columnValue="已停产";
					}
				}
			}else if(DataSourceEnum.hyxl.getCode().equals(tDataLawobjdic.getDatasource())){
				if(StringUtil.isNotBlank(columnValue)){
					TDataIndustry tDataIndustry = (TDataIndustry) this.dao.get(TDataIndustry.class, columnValue);
					columnValue = tDataIndustry!=null?tDataIndustry.getName():"";
				}else{
					columnValue ="";
				}
				
			}else if(DataSourceEnum.spjgzd.getCode().equals(tDataLawobjdic.getDatasource())){
				List<Object> list = this.dao.find("select name from TSysDic where type = ? and code = ?",DicTypeEnum.SPJG.getCode(),columnValue);
				columnValue = list.size()>0?String.valueOf(list.get(0)):"";
			}else if(DataSourceEnum.jsjdjsczt.getCode().equals(tDataLawobjdic.getDatasource())){
				List<Object> list = this.dao.find("select name from TSysDic where type = ? and code = ?",DicTypeEnum.JSJDJSCZT.getCode(),columnValue);
				columnValue = list.size()>0?String.valueOf(list.get(0)):"";
			}else if(DataSourceEnum.jzgdzt.getCode().equals(tDataLawobjdic.getDatasource())){
				List<Object> list = this.dao.find("select name from TSysDic where type = ? and code = ?",DicTypeEnum.JZGDZT.getCode(),columnValue);
				columnValue = list.size()>0?String.valueOf(list.get(0)):"";
			}else if(DataSourceEnum.jsxz.getCode().equals(tDataLawobjdic.getDatasource())){
				if(StringUtils.isNotBlank(columnValue)){
					String[] arr = columnValue.split(",");
					for(int i=0;i<arr.length;i++){
						if(i==0){
							columnValue = "'"+arr[0]+"'";
						}else{
							columnValue += "'"+arr[i]+"'";
						}
						if(i!=arr.length-1){
							columnValue +=",";
						}
					}
				}
				List<Object> list = this.dao.findBySql("select name_ from T_Sys_Dic where type_ = ? and code_ in ("+columnValue+")",DicTypeEnum.JSXZ.getCode());
				if(list.size()>0){
					columnValue = "";
					for(int i=0;i<list.size();i++){
						columnValue += String.valueOf(list.get(i));
						if(i!=list.size()-1){
							columnValue += ",";
						}
					}
				}
			}
			//创建人
			if(InputTypeEnum.zdwbk.getCode().equals(tDataLawobjdic.getInputtype())){
				if(StringUtils.isNotBlank(columnValue)){
					TSysUser tSysUser = userManager.getUser(columnValue);
					if(tSysUser!=null){
						columnValue = tSysUser.getName();
					}
				}
			}
			//经纬度输入框
			if(InputTypeEnum.jwsrk.getCode().equals(tDataLawobjdic.getInputtype())){
				if(StringUtils.isNotBlank(columnValue)){
					String val[] =  columnValue.split(",");
					if(val.length > 1){
						columnValue = val[0] + "度" + val[1] + "分" + val[2]+"秒";
					}else{
						columnValue = val[0];
					}
				}
			}
			//企业生产状态
			if(InputTypeEnum.scztkj.getCode().equals(tDataLawobjdic.getInputtype())){
				if(StringUtils.isNotBlank(columnValue)){
					if(columnValue.indexOf("Y")!= -1){
						columnValue="全年性生产";
					}else{
						String temp = "";
						if(columnValue.indexOf("01")!= -1){
							temp="（1月）";
						}
						if(columnValue.indexOf("02")!= -1){
							temp=temp+"（2月）";
						}
						if(columnValue.indexOf("03")!= -1){
							temp=temp+"（3月）";
						}
						if(columnValue.indexOf("04")!= -1){
							temp=temp+"（4月）";
						}
						if(columnValue.indexOf("05")!= -1){
							temp=temp+"（5月）";
						}
						if(columnValue.indexOf("06")!= -1){
							temp=temp+"（6月）";
						}
						if(columnValue.indexOf("07")!= -1){
							temp=temp+"（7月）";
						}
						if(columnValue.indexOf("08")!= -1){
							temp=temp+"（8月）";
						}
						if(columnValue.indexOf("09")!= -1){
							temp=temp+"（9月）";
						}
						if(columnValue.indexOf("10")!= -1){
							temp=temp+"（10月）";
						}
						if(columnValue.indexOf("11")!= -1){
							temp=temp+"（11月）";
						}
						if(columnValue.indexOf("12")!= -1){
							temp=temp+"（12月）";
						}
						columnValue = "季节性生产"+temp;
					}
				}
			}
			//畜禽养殖种类
			if(InputTypeEnum.xllbddx.getCode().equals(tDataLawobjdic.getInputtype())){
				if(StringUtils.isNotBlank(columnValue)){
					String[] arr = columnValue.split(",");
					for(int i=0;i<arr.length;i++){
						if(i==0){
							columnValue = "'"+arr[0]+"'";
						}else{
							columnValue += "'"+arr[i]+"'";
						}
						if(i!=arr.length-1){
							columnValue +=",";
						}
					}
					List<Object> list = this.dao.findBySql("select name_ from T_Sys_Dic where type_ = ? and code_ in ("+columnValue+")",DicTypeEnum.XQYZZL.getCode());
					if(list.size()>0){
						columnValue = "";
						for(int i=0;i<list.size();i++){
							columnValue += String.valueOf(list.get(i));
							if(i!=list.size()-1){
								columnValue += ",";
							}
						}
					}
				}
			}
			return StringUtils.isBlank(columnValue)?"":columnValue;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public JsxmForm getJsxmInfo(String id) throws Exception{
		JsxmForm jsxmForm = null;
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
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jsxm_ssxzq.getCode(), 
				LawobjOutColunmEnum.jsxm_dwmc.getCode(), 
				LawobjOutColunmEnum.jsxm_dwdz.getCode(), 
				LawobjOutColunmEnum.jsxm_fddbr.getCode(), 
				LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode(),
				LawobjOutColunmEnum.jsxm_hbfzr.getCode(), 
				LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode());
		String ssxzq_column = null;
		String dwmc_column = null;
		String address_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		if(listColumn.size()!=7){
			throw new Exception("建设项目有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			dwmc_column = String.valueOf(listColumn.get(1));
			address_column = String.valueOf(listColumn.get(2));
			fddbr_column = String.valueOf(listColumn.get(3));
			fddbrlxdh_column = String.valueOf(listColumn.get(4));
			hbfzr_column = String.valueOf(listColumn.get(5));
			hbfzrlxdh_column = String.valueOf(listColumn.get(6));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ ");
		listSql.append(" , l.").append(ssxzq_column);
		listSql.append(" , l.").append(dwmc_column);
		listSql.append(" , l.").append(address_column);
		listSql.append(" , l.").append(fddbr_column);
		listSql.append(" , l.").append(fddbrlxdh_column);
		listSql.append(" , l.").append(hbfzr_column);
		listSql.append(" , l.").append(hbfzrlxdh_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" where l.LAWOBJTYPE_ = '02' and l.id_ = ?");
		List<Object[]> list = this.dao.findBySql(listSql.toString(), id);
		if(list.size()>0){
			Object[] obj = list.get(0);
			jsxmForm = new JsxmForm();
			jsxmForm.setId(id);
			jsxmForm.setRegionid(obj[1]==null?"":String.valueOf(obj[1]));
			jsxmForm.setDwmc(obj[2]==null?"":String.valueOf(obj[2]));
			jsxmForm.setAddress(obj[3]==null?"":String.valueOf(obj[3]));
			jsxmForm.setFddbr(obj[4]==null?"":String.valueOf(obj[4]));
			jsxmForm.setFddbrlxdh(obj[5]==null?"":String.valueOf(obj[5]));
			jsxmForm.setHbfzr(obj[6]==null?"":String.valueOf(obj[6]));
			jsxmForm.setHbfzrlxdh(obj[7]==null?"":String.valueOf(obj[7]));
		}
		return jsxmForm;
	}
	
	
	@Override
	public void saveJsxmLawobjId(String jsxmid,TDataLawobj lawobj){
		try {
			List<String> list = this.queryLawobjColumnByEnmu("02", LawobjOutColunmEnum.jsxm_lawobjid.getCode(), LawobjOutColunmEnum.jsxm_dwmc.getCode());
			if(list.size()>0){
				String lawobjIdColumn = String.valueOf(list.get(0));
				String dwmcColumn = String.valueOf(list.get(1));
				String dwmcValue = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(this.getColumnNameByEnumname(lawobj.getLawobjtype()+PublicColumnEnum.mc.getCode())));
				StringBuffer sql = new StringBuffer("update t_data_lawobj l set l.").append(lawobjIdColumn).append(" = '").append(lawobj.getId()).append("', "); 
				sql.append(" l.").append(dwmcColumn).append(" = '").append(dwmcValue).append("' ");
				sql.append(" where l.id_ = '"+jsxmid+"'");
				this.dao.exec(sql.toString());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String>	queryLawobjColumnByEnmu(String lawobjType,String... args){
		if(args.length>0){
			Object[] obj = new Object[args.length*2];
			StringBuffer sql = new StringBuffer();
			for(int i=0;i<args.length;i++){
				sql.append("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = ? and d.enumname_ = ? ");
				if(i!=args.length-1){
					sql.append(" union all ");
				}
				obj[2*i]  = lawobjType;
				obj[2*i+1]  = args[i];
			}
			List<String> listColumn = this.dao.findBySql(sql.toString(), obj);
			return listColumn;
		}
		return null;
	}
	
	@Override
	public String getLawobjColumnValue(String enumCode,String lawobjid){
		List<Object> list = this.dao.findBySql("select d.colengname_ from t_data_lawobjdic d where d.enumname_ = ? ", enumCode);
		if(list.size()>0){
			String columnName = String.valueOf(list.get(0));
			List<Object> listValue = this.dao.findBySql("select l."+columnName+" from t_data_lawobj l where l.id_ = ? ", lawobjid);
			if(!listValue.isEmpty() && listValue.size()>0){
				if(StringUtils.isNotBlank((String) listValue.get(0))){
					return String.valueOf(listValue.get(0));
				}
				return "";
			}
		}
		return "";
	}

	@Override
	public Boolean checkLawobjname(TDataLawobj lawobj) {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			List<Object> list = this.dao.findBySql("select d.colengname_ from t_data_lawobjdic d where d.enumname_ = ? ", lawobj.getLawobjtype()+PublicColumnEnum.mc.getCode());
			if(list.size()>0){
				String columnName = String.valueOf(list.get(0));
				StringBuffer sql = new StringBuffer("select l."+columnName+" from t_data_lawobj l where l.isactive_ = 'Y' and l.lawobjtype_ = :lawobjtype and l."+columnName+" = :columnName ");
				String value = BeanUtils.getProperty(lawobj, StringUtil.transColumnToAttr(columnName));
				condition.put("columnName", value);
				condition.put("lawobjtype", lawobj.getLawobjtype());
				if(StringUtils.isNotBlank(lawobj.getId())){//编辑
					sql.append(" and l.id_ != :lawobjid");
					condition.put("lawobjid", lawobj.getId());
				}
				list = this.dao.findBySql(sql.toString(), condition);
				if(list.size()>0){
					return true;
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FyWebResult queryLawHistoryList(String id, String page, String pageSize) {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> condition = new HashMap<String,Object>();
		sql.append(" select w.id_,w.work_name_,p.name_ taskname,u.name_ gdusername,g.name_ gddwname,w.Archives_Time_ from work_ w ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_sys_user u on w.ARCHIVES_USER_ = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" where w.isactive_ = 'Y' and (l.lawobjid_ = :lawobjid ");
		//建设项目转污染源等执法对象时带出执法记录
		String tolawobjid_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_lawobjid.getCode());
		sql.append(" or l.lawobjid_ in (select j.id_ from T_DATA_LAWOBJ j where j."+tolawobjid_column+" =:tolawobjid and j.LAWOBJTYPE_= '"+ZfdxLx.JSXM.getCode()+"'))");
		
		sql.append(" and w.state_ = :state");
		condition.put("lawobjid", id);
		condition.put("tolawobjid", id);
		condition.put("state", WorkState.YGD.getCode());
		sql.append(" order by w.Archives_Time_ desc ");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("workname", String.valueOf(obj[1]));
			dataObject.put("taskname", String.valueOf(obj[2]));
			dataObject.put("gdry", String.valueOf(obj[3]));
			dataObject.put("gddw", String.valueOf(obj[4]));
			dataObject.put("gdsj", String.valueOf(obj[5]));
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(dataObject);
		}
		return fy;
	}
	
	
	public HashMap<String, Object> getLawobjByIds(String id, TSysUser u, String type) {
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		sql.append(" union all ");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '"+type+"' and d.enumname_ = ?  ");
		String dwmc = "";
		String ssxzq = "";
		String dz = "";
		String fddbr = "";
		String fddbrlxdh = "";
		String hbfzr = "";
		String hbfzrlxdh = "";
		if (type.equals("01")){
			dwmc = LawobjOutColunmEnum.gywry_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.gywry_ssxzq.getCode();
			dz = LawobjOutColunmEnum.gywry_dz.getCode();
			fddbr = LawobjOutColunmEnum.gywry_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.gywry_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.gywry_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode();
		} else if (type.equals("02")) {
			dwmc = LawobjOutColunmEnum.jsxm_jsxmmc.getCode();
			ssxzq = LawobjOutColunmEnum.jsxm_ssxzq.getCode();
			dz = LawobjOutColunmEnum.jsxm_dwdz.getCode();
			fddbr = LawobjOutColunmEnum.jsxm_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.jsxm_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode();
		} else if (type.equals("03")) {
			dwmc = LawobjOutColunmEnum.yy_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.yy_ssxzq.getCode();
			dz = LawobjOutColunmEnum.yy_dz.getCode();
			fddbr = LawobjOutColunmEnum.yy_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.yy_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.yy_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.yy_hbfzrlxdh.getCode();
		} else if (type.equals("04")) {
			dwmc = LawobjOutColunmEnum.gl_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.gl_ssxzq.getCode();
			dz = LawobjOutColunmEnum.gl_dz.getCode();
			fddbr = LawobjOutColunmEnum.gl_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.gl_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.gl_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.gl_hbfzrlxdh.getCode();
		} else if (type.equals("05")) {
			dwmc = LawobjOutColunmEnum.jzgd_sgxmmc.getCode();
			ssxzq = LawobjOutColunmEnum.jzgd_ssxzq.getCode();
			dz = LawobjOutColunmEnum.jzgd_dz.getCode();
			fddbr = LawobjOutColunmEnum.jzgd_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.jzgd_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.jzgd_hbfzrlxdh.getCode();
		} else if (type.equals("06")) {
			dwmc = LawobjOutColunmEnum.sc_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.sc_ssxzq.getCode();
			dz = LawobjOutColunmEnum.sc_dz.getCode();
			fddbr = LawobjOutColunmEnum.sc_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.sc_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.sc_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.sc_hbfzrlxdh.getCode();
		} else if (type.equals("07")) {
			dwmc = LawobjOutColunmEnum.xqyz_xqyzcmc.getCode();
			ssxzq = LawobjOutColunmEnum.xqyz_ssxzq.getCode();
			dz = LawobjOutColunmEnum.xqyz_dz.getCode();
			fddbr = LawobjOutColunmEnum.xqyz_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.xqyz_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.xqyz_hbfzrlxdh.getCode();
		} else if (type.equals("08")) {
			dwmc = LawobjOutColunmEnum.fwy_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.fwy_ssxzq.getCode();
			dz = LawobjOutColunmEnum.fwy_dz.getCode();
			fddbr = LawobjOutColunmEnum.fwy_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.fwy_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.fwy_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode();
		} else if (type.equals("09")) {
			dwmc = LawobjOutColunmEnum.ysy_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.ysy_ssxzq.getCode();
			dz = LawobjOutColunmEnum.ysy_dz.getCode();
			fddbr = LawobjOutColunmEnum.ysy_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.ysy_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.ysy_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode();
		} else if (type.equals("10")) {
			dwmc = LawobjOutColunmEnum.zzy_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.zzy_ssxzq.getCode();
			dz = LawobjOutColunmEnum.zzy_dz.getCode();
			fddbr = LawobjOutColunmEnum.zzy_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.zzy_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.zzy_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode();
		} else if (type.equals("11")) {
			dwmc = LawobjOutColunmEnum.yly_dwmc.getCode();
			ssxzq = LawobjOutColunmEnum.yly_ssxzq.getCode();
			dz = LawobjOutColunmEnum.yly_dz.getCode();
			fddbr = LawobjOutColunmEnum.yly_fddbr.getCode();
			fddbrlxdh = LawobjOutColunmEnum.yly_fddbrlxdh.getCode();
			hbfzr = LawobjOutColunmEnum.yly_hbfzr.getCode();
			hbfzrlxdh = LawobjOutColunmEnum.yly_hbfzrlxdh.getCode();
		}
		
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				dwmc, 
				ssxzq, 
				dz, 
				fddbr, 
				fddbrlxdh, 
				hbfzr, 
				hbfzrlxdh);
		String dwmc_column = null;
		String ssxzq_column = null;
		String dz_column = null;
		String fddbr_column = null;
		String fddbrlxdh_column = null;
		String hbfzr_column = null;
		String hbfzrlxdh_column = null;
		if(listColumn.size()!=7){
			return null;
		}else{
			dwmc_column = String.valueOf(listColumn.get(0));
			ssxzq_column = String.valueOf(listColumn.get(1));
			dz_column = String.valueOf(listColumn.get(2));
			fddbr_column = String.valueOf(listColumn.get(3));
			fddbrlxdh_column = String.valueOf(listColumn.get(4));
			hbfzr_column = String.valueOf(listColumn.get(5));
			hbfzrlxdh_column = String.valueOf(listColumn.get(6));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_ id,l.").append(dwmc_column).append(" dwmc, r.name_ regionname, l.isactive_");
		listSql.append(", l.").append(dz_column);
		listSql.append(", l.").append(fddbr_column);
		listSql.append(", l.").append(fddbrlxdh_column);
		listSql.append(", l.").append(hbfzr_column);
		listSql.append(", l.").append(hbfzrlxdh_column);
		listSql.append(", l.").append(ssxzq_column);
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '"+type+"' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		
		listSql.append(" and l.ID_ = :id ");
		condition.put("id", id);
		
		listSql.append(" order by r.orderby_,l.updated_ desc, l.").append(dwmc_column);
		FyResult pos = this.dao.find(listSql.toString(), 1, 0, condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		HashMap<String, Object> dataObject = null;
		Object[] obj = listLawobj.get(0);
		dataObject = new HashMap<String, Object>();
		dataObject.put("id", String.valueOf(obj[0]));
		dataObject.put("name", obj[1]==null?"":String.valueOf(obj[1]));
		dataObject.put("regionName", obj[2]==null?"":String.valueOf(obj[2]));
		dataObject.put("isActive", "Y".equals(String.valueOf(obj[3]))?"有效":"无效");
		dataObject.put("address", obj[4]==null?"":String.valueOf(obj[4]));
		dataObject.put("fddbr", obj[5]==null?"":String.valueOf(obj[5]));
		dataObject.put("fddbrlxdh", obj[6]==null?"":String.valueOf(obj[6]));
		dataObject.put("hbfzr", obj[7]==null?"":String.valueOf(obj[7]));
		dataObject.put("hbfzrlxdh", obj[8]==null?"":String.valueOf(obj[8]));
		dataObject.put("regionid", obj[9]==null?"":String.valueOf(obj[9]));
		
		return dataObject;
	}
	/**
	 * 今年没有被抽中的重点污染源列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryNoCheckedList(String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='1' or d.code_='2'or d.code_='3') and d.type_='6'");
		listSql.append(" and l.id_ not in (select c.lawobjid_ from t_biz_checkedlawobj c where year_="+year+" and type_='0')");
		Map<String, Object> condition = new HashMap<String, Object>();
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	/**
	 * 所有重点污染源列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllKeyLawobjList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='1' or d.code_='2'or d.code_='3') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//本区域管辖的企业
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有非重点污染源列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryNoKeyLawobjList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='4') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 根据污染源得到污染源所属监管部门id
	 * @throws Exception 
	 */
	public String getOrgidByLawobj(TDataLawobj lawobj) throws Exception{
		if(lawobj!=null && lawobj.getLawobjtype()!=null){
			if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//工业污染源
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JSXM.getCode())){//建设项目
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YY.getCode())){//医院
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yy_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GL.getCode())){//锅炉
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gl_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JZGD.getCode())){//建筑工地
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jzgd_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.SC.getCode())){//三产
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.sc_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.XQYZ.getCode())){//畜禽养殖
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.xqyz_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.FWY.getCode())){//服务业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.fwy_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YSY.getCode())){//饮食业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.ysy_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.ZZY.getCode())){//三产制造业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.zzy_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YLY.getCode())){//娱乐业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yly_ssjgbm.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	/**
	 * 根据列名获得执法对象该列的值
	 */
	@Override
	public String getValueByColumnName(TDataLawobj lawobj,String columnName){
		StringBuffer listSql = new StringBuffer(" select l.").append(columnName);
		listSql.append(" from t_Data_Lawobj l where id_= ");
		listSql.append("'"+lawobj.getId()+"'");
		List<Object[]> list = this.dao.findBySql(listSql.toString());
		if(list!=null && list.size()>0)
		{
			return String.valueOf(list.get(0));
		}
		else return null;
	}
	/**
	 * 根据污染源得到污染源名称
	 * @throws Exception 
	 */
	public String getNameByLawobj(TDataLawobj lawobj) throws Exception{
		if(lawobj!=null && lawobj.getLawobjtype()!=null){
			if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//工业污染源
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JSXM.getCode())){//建设项目
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_jsxmmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YY.getCode())){//医院
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yy_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GL.getCode())){//锅炉
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gl_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JZGD.getCode())){//建筑工地
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jzgd_sgxmmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.SC.getCode())){//三产
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.sc_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.XQYZ.getCode())){//畜禽养殖
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.xqyz_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.FWY.getCode())){//服务业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.fwy_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YSY.getCode())){//饮食业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.ysy_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.ZZY.getCode())){//三产制造业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.zzy_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YLY.getCode())){//娱乐业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yly_dwmc.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 根据执法对象得到执法对象生产状态
	 * @throws Exception 
	 */
	public String getScztByLawobj(TDataLawobj lawobj) throws Exception{
		if(lawobj!=null && lawobj.getLawobjtype()!=null){
			if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//工业污染源
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JSXM.getCode())){//建设项目
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YY.getCode())){//医院
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yy_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GL.getCode())){//锅炉
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gl_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JZGD.getCode())){//建筑工地
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jzgd_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.SC.getCode())){//三产
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.sc_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.XQYZ.getCode())){//畜禽养殖
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.xqyz_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.FWY.getCode())){//服务业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.fwy_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YSY.getCode())){//饮食业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.ysy_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.ZZY.getCode())){//三产制造业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.zzy_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YLY.getCode())){//娱乐业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yly_qysczt.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 根据污染源得到污染源的控制类型
	 * @throws Exception 
	 */
	public String getKzlxByLawobj(TDataLawobj lawobj) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_kzlx.getCode());
		String kzlx_column = null;
		if(listColumn.size()!=1){
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
		}else{
			kzlx_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select d.name_ kzlx");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append("left join t_sys_dic d on d.type_='6' and l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.id_= ");
		listSql.append("'"+lawobj.getId()+"'");
		List<Object[]> list = this.dao.findBySql(listSql.toString());
		if(list!=null && list.size()>0)
		{
			return String.valueOf(list.get(0));
		}
		else return null;
	}
	
	/**
	 * 所有建设项目列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllJsxmList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ? ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jsxm_ssxzq.getCode());
		String ssxzq_column = null;
		if(listColumn.size()!=1){
			throw new Exception("建设项目有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有医院列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYyList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yy_ssxzq.getCode(),
				LawobjOutColunmEnum.yy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("医院有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有锅炉列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllGlList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gl_ssxzq.getCode(),
				LawobjOutColunmEnum.gl_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("锅炉有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有建筑工地列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllJzgdList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
				LawobjOutColunmEnum.jzgd_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("建筑工地有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有三产列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllScList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.sc_ssxzq.getCode(),
				LawobjOutColunmEnum.sc_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("三产有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有畜禽养殖列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllXqyzList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
				LawobjOutColunmEnum.xqyz_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("畜禽养殖有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有服务业列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllFwyList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.fwy_ssxzq.getCode(),
				LawobjOutColunmEnum.fwy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("服务业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有饮食业列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYsyList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.ysy_ssxzq.getCode(),
				LawobjOutColunmEnum.ysy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("饮食业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * 所有三产制造业列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllZzyList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.zzy_ssxzq.getCode(),
				LawobjOutColunmEnum.zzy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("三产制造业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	
	/**
	 * 所有娱乐业列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYlyList() throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yly_ssxzq.getCode(),
				LawobjOutColunmEnum.yly_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("娱乐业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = CtxUtil.getAreaId();
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 *  查询所有一般企业列表(所有非重点工业污染源和所有其他执法对象类型企业)
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllNormalList() throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		//非重点的工业污染源
		lawobjList.addAll(this.queryNoKeyLawobjList());//非重点工业污染源
		lawobjList.addAll(this.queryAllJsxmList());//建设项目
		lawobjList.addAll(this.queryAllYyList());//医院
		lawobjList.addAll(this.queryAllGlList());//锅炉
		lawobjList.addAll(this.queryAllJzgdList());//建筑工地
		lawobjList.addAll(this.queryAllScList());//三产
		lawobjList.addAll(this.queryAllXqyzList());//畜禽养殖
		lawobjList.addAll(this.queryAllFwyList());//服务业
		lawobjList.addAll(this.queryAllYsyList());//饮食业
		lawobjList.addAll(this.queryAllZzyList());//制造业
		lawobjList.addAll(this.queryAllYlyList());//娱乐业
		return lawobjList;
	}
	
	
	/**
	 * 所有上年度违法、被投诉的工业污染源列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryIllegalLawobjList(String year,String lawobjType) throws Exception{
		String ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_ssxzq.getCode());
		if("02".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_ssxzq.getCode());
		}else if("03".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.yy_ssxzq.getCode());
		}else if("04".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.gl_ssxzq.getCode());
		}else if("05".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.jzgd_ssxzq.getCode());
		}else if("06".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.sc_ssxzq.getCode());
		}else if("07".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.xqyz_ssxzq.getCode());
		}else if("08".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.fwy_ssxzq.getCode());
		}else if("09".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.ysy_ssxzq.getCode());
		}else if("10".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.zzy_ssxzq.getCode());
		}else if("11".equals(lawobjType)){
			ssxzq_column = this.getColumnNameByEnumname(LawobjOutColunmEnum.yly_ssxzq.getCode());
		}
		int year1 = Integer.parseInt(year)-1;
		Date time1 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", String.valueOf(year1).concat("-01-01 00:00:00"));
		Date time2 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", String.valueOf(year).concat("-01-01 00:00:00"));
		StringBuffer listSql = new StringBuffer(" select distinct l.id_ id");
		listSql.append(" from t_Data_Lawobj l , t_Biz_TaskAndLawobj t, T_BIZ_TASKANDTASKTYPE p,T_DATA_TASKTYPE k,WORK_ w,T_DATA_REGION r");
		listSql.append(" where l.isActive_='Y' and l.LAWOBJTYPE_ = '"+lawobjType+"' and l.id_ = t.LAWOBJID_ and t.TASKID_ = p.TASKID_ and p.TASKTYPEID_=k.CODE_ and w.id_=p.taskid_ and l."+ssxzq_column+"= r.id_");
		listSql.append(" and (k.CODE_ = ? or k.CODE_ = ?) ");	//违法+信访投诉
		listSql.append(" and w.ARCHIVES_TIME_ >= ? ");	//归档时间
		listSql.append(" and w.ARCHIVES_TIME_ < ? ");	//归档时间
		
		//所属行政区为本区域的企业
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
			listSql.append(" and r.id_ = ? ");
		}if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = ?) ");
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = ? ");
		}
		List<Object[]> list = this.dao.findBySql(listSql.toString(),TaskTypeCode.WFAJ.getCode(),TaskTypeCode.XFTS.getCode(),time1,time2,area.getCode());
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 *  查询所有特殊企业列表(所有上年度已归档的违法被调查、被投诉的企业)
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryIllegalLawobjList(String year) throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		lawobjList.addAll(this.queryIllegalLawobjList(year,"01"));//工业污染源
		lawobjList.addAll(this.queryIllegalLawobjList(year,"02"));//建设项目
		lawobjList.addAll(this.queryIllegalLawobjList(year,"03"));//医院
		lawobjList.addAll(this.queryIllegalLawobjList(year,"04"));//锅炉
		lawobjList.addAll(this.queryIllegalLawobjList(year,"05"));//建筑工地
		lawobjList.addAll(this.queryIllegalLawobjList(year,"06"));//三产
		lawobjList.addAll(this.queryIllegalLawobjList(year,"07"));//畜禽养殖
		lawobjList.addAll(this.queryIllegalLawobjList(year,"08"));//服务业
		lawobjList.addAll(this.queryIllegalLawobjList(year,"09"));//饮食业
		lawobjList.addAll(this.queryIllegalLawobjList(year,"10"));//制造业
		lawobjList.addAll(this.queryIllegalLawobjList(year,"11"));//娱乐业
		return lawobjList;
	}

	@Override
	public String getRegionIdByLawobj(TDataLawobj lawobj) throws Exception {
		if(lawobj!=null && lawobj.getLawobjtype()!=null){
			if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//工业污染源
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JSXM.getCode())){//建设项目
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YY.getCode())){//医院
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yy_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GL.getCode())){//锅炉
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.gl_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.JZGD.getCode())){//建筑工地
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.jzgd_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.SC.getCode())){//三产
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.sc_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.XQYZ.getCode())){//畜禽养殖
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.xqyz_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.FWY.getCode())){//服务业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.fwy_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YSY.getCode())){//饮食业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.ysy_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.ZZY.getCode())){//三产制造业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.zzy_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.YLY.getCode())){//娱乐业
				String columnName = this.getColumnNameByEnumname(LawobjOutColunmEnum.yly_ssxzq.getCode());//列名
				return this.getValueByColumnName(lawobj,columnName);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public FyWebResult queryLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
        sql.append("select l.* from V_ZFDX_ZHXX l ");
        sql.append(" left join t_data_region r on l.REGIONID_ = r.id_ ");
        sql.append(" where 1=1 ");
       //单位名称
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //执法对象类型
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //所属行政区
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //所属监管部门
        if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
        String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			sql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
        sql.append(" order by l.lawobjType_ asc");
        FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
        FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> row = null;
		for (Object[] obj : listLawobj) {
			row = new HashMap<String, String>();
			row.put("id", String.valueOf(obj[0]));
			row.put("lawobjType", obj[1]==null?"":dicManager.getNameByTypeAndCode("5", String.valueOf(obj[1])));
			row.put("name", obj[2]==null?"":String.valueOf(obj[2]));
			row.put("address", obj[3]==null?"":String.valueOf(obj[3]));
			row.put("regionName", obj[5]==null?"":String.valueOf(obj[5]));
			row.put("orgName", obj[7]==null?"":String.valueOf(obj[7]));
			row.put("fddbr", obj[8]==null?"":String.valueOf(obj[8]));
			row.put("fddbrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
			row.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	@Override
	public Map<String, String> getLawobjByType(String type) throws Exception {
		Map<String, String> dics=new HashMap<String, String>();
		if(StringUtil.isNotBlank(type)){
			if(StringUtils.equals(type, ZfdxLx.GYWRY.getCode())){//工业污染源
					dics.put("mc", LawobjOutColunmEnum.gywry_dwmc.getCode());
					dics.put("dz", LawobjOutColunmEnum.gywry_dz.getCode());
					dics.put("ssxzq", LawobjOutColunmEnum.gywry_ssxzq.getCode());
					dics.put("fddbr", LawobjOutColunmEnum.gywry_fddbr.getCode());
					dics.put("fddbrlxdh", LawobjOutColunmEnum.gywry_fddbrlxdh.getCode());
					dics.put("hbfzr", LawobjOutColunmEnum.gywry_hbfzr.getCode());
					dics.put("hbfzrlxdh", LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.JSXM.getCode())){//建设项目
				dics.put("mc", LawobjOutColunmEnum.jsxm_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.jsxm_dwdz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.jsxm_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.jsxm_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.jsxm_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.jsxm_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.jsxm_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.YY.getCode())){//医院
				dics.put("mc", LawobjOutColunmEnum.yy_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.yy_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.yy_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.yy_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.yy_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.yy_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.yy_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.GL.getCode())){//锅炉
				dics.put("mc", LawobjOutColunmEnum.gl_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.gl_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.gl_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.gl_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.gl_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.gl_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.gl_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.JZGD.getCode())){//建筑工地
				dics.put("mc", LawobjOutColunmEnum.jzgd_sgxmmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.jzgd_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.jzgd_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.jzgd_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.jzgd_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.jzgd_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.jzgd_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.SC.getCode())){//三产
				dics.put("mc", LawobjOutColunmEnum.sc_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.sc_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.sc_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.sc_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.sc_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.sc_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.sc_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.XQYZ.getCode())){//畜禽养殖
				dics.put("mc", LawobjOutColunmEnum.xqyz_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.xqyz_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.xqyz_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.xqyz_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.xqyz_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.xqyz_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.xqyz_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.FWY.getCode())){//服务业
				dics.put("mc", LawobjOutColunmEnum.fwy_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.fwy_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.fwy_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.fwy_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.fwy_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.fwy_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.fwy_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.YSY.getCode())){//饮食业
				dics.put("mc", LawobjOutColunmEnum.ysy_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.ysy_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.ysy_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.ysy_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.ysy_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.ysy_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.ysy_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.ZZY.getCode())){//三产制造业
				dics.put("mc", LawobjOutColunmEnum.zzy_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.zzy_dzh.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.zzy_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.zzy_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.zzy_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.zzy_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.zzy_hbfzrlxdh.getCode());
			}else if(StringUtils.equals(type, ZfdxLx.YLY.getCode())){//娱乐业
				dics.put("mc", LawobjOutColunmEnum.yly_dwmc.getCode());
				dics.put("dz", LawobjOutColunmEnum.yly_dz.getCode());
				dics.put("ssxzq", LawobjOutColunmEnum.yly_ssxzq.getCode());
				dics.put("fddbr", LawobjOutColunmEnum.yly_fddbr.getCode());
				dics.put("fddbrlxdh", LawobjOutColunmEnum.yly_fddbrlxdh.getCode());
				dics.put("hbfzr", LawobjOutColunmEnum.yly_hbfzr.getCode());
				dics.put("hbfzrlxdh", LawobjOutColunmEnum.yly_hbfzrlxdh.getCode());
			}else{
			}
		}else{
		}
		return dics;
	}
	
	public List<Vzfdx> queryLawobjList(String name, String lawobjType, String regionId, String orgId)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
        sql.append("select l.* from V_ZFDX_ZHXX l where 1=1");
        
       //单位名称
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //执法对象类型
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //所属行政区
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //所属监管部门
        if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
        sql.append(" order by l.lawobjType_ asc");
        List<Object[]> list = this.dao.findBySql(sql.toString(),condition);
		List<Vzfdx> zfdxList = new ArrayList();
		for (Object[] obj : list) {
			Vzfdx zfdx = new Vzfdx();
			zfdx.setLawobjType(obj[1]==null?"":dicManager.getNameByTypeAndCode("5", String.valueOf(obj[1])));
			zfdx.setDwmc(obj[2]==null?"":String.valueOf(obj[2]));
			zfdx.setAddress(obj[3]==null?"":String.valueOf(obj[3]));
			zfdx.setRegionName(obj[5]==null?"":String.valueOf(obj[5]));
			zfdx.setOrgName(obj[7]==null?"":String.valueOf(obj[7]));
			zfdx.setFddbr(obj[8]==null?"":String.valueOf(obj[8]));
			zfdx.setFddbrlxdh(obj[9]==null?"":String.valueOf(obj[9]));
			zfdxList.add(zfdx);
			
		}
		return zfdxList;
	}
	
	@Override
	public void exportLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) 
			throws Exception{
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		//单位名称
		if (StringUtils.isNotBlank(name)) {
				conditionsForm.setName("包含“"+name+"”的企业");
		}else{
			conditionsForm.setName("全部");
		}
		//执法对象类型
		if (StringUtils.isNotBlank(lawobjType)) {
			conditionsForm.setLawobjtypename(dicManager.getNameByTypeAndCode("5",lawobjType));
		}else{
			conditionsForm.setLawobjtypename("全部");
		}
		//所属行政区
		if (StringUtils.isNotBlank(regionName)) {
			conditionsForm.setRegionname(regionName);
		}else{
			conditionsForm.setRegionname("全部");
		}
		//所属监管部门
		if (StringUtils.isNotBlank(orgName)) {
			conditionsForm.setOrgname(orgName);
		}else{
			conditionsForm.setOrgname("全部");
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);
		//列表数据
		List list = this.queryLawobjList(name,lawobjType, regionId, orgId);
		map.put("lawobjListForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
				File file = ExcelUtil.setValue(
						new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
						new File(realPath + "excel/xxcx/lawobjList.xls"), 
						new File(realPath + "excel/xxcx/lawobjList.xml"), 
						map, false);
				ExcelUtil.copyStyle(file, "执法对象信息统计表", 4, 0, 4 + list.size() - 1, 6);
				is = new FileInputStream(file);
				String name1="执法对象信息统计表.xls";
				String de = "attachment;filename=".concat(new String(name1.getBytes("GB2312"), "ISO-8859-1"));
				res.setHeader("Content-Disposition", de);
				res.setContentType("application/x-msdownload");
				os = res.getOutputStream();
				byte[] b = new byte[1024];
				int length;
				while ((length = is.read(b)) > 0) {
					os.write(b, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(is!=null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
					if( os != null )
						os.flush();
						os.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
