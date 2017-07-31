package com.hnjz.app.tjbb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.jxkh.orgstatistics.ConditionsForm;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
	统计排污单位数量
 *
 */
@Service("statisticalManager")
public class StatisticalManagerImpl extends ManagerImpl implements StatisticalManager, ServletContextAware{

	@Autowired
	private LawobjManager lawobjManager;
	
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	public FyWebResult tjpwdwsl(String regionId, String lawobjtype, String page, String pageSize) {
		try {
			//StringBuffer sql = this.getViewSql();
			StringBuffer sql = new StringBuffer();
			sql.append( " with lawobj_wrw(regionid_,lawobjtype,lawobjid_,fs,fq,zs,gf,fzss) as ( " );
			sql.append( "  select  f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRFZSSSL_) = 1 then to_number(g.WRFZSSSL_) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_GYWRY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_JSXM g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_YY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_GL g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_JZGD g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_SC g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_XQYZ g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_FWY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_YSY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_ZZY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_YLY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append(" union ");
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_LAWOBJTYPE g on g.ID_=f.LAWOBJTYPEID_ where f.ISACTIVE_='Y' AND g.ISACTIVE_='Y' AND G.ID_ NOT IN(1,2,3,4,5,6,7,8,9,10,11) " );
			sql.append( " ) " );
			
			
			
			
			
			
			
			
			Map<String, Object> condition = new HashMap<String, Object>();
			sql.append(" select r.id_,r.name_ regionname,d1.id_ code_,d1.name_ lawobjtype,count(w.lawobjid_) wrysum,sum(fs),sum(fq),sum(zs),sum(gf),sum(fzss) ");
			sql.append(" from t_data_region r right join lawobj_wrw w on r.id_ = w.regionid_ ");
			sql.append(" left join T_DATA_LAWOBJTYPE d1 on w.lawobjtype = d1.id_ ");
			
			sql.append(" where 1=1 ");
			if(StringUtils.isNotBlank(regionId)){
				sql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			if(StringUtils.isNotBlank(lawobjtype)){
				sql.append(" and d1.id_ = :lawobjtype ");
				condition.put("lawobjtype", lawobjtype);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				sql.append(" and r.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}
			sql.append(" group by r.id_,r.name_,r.orderby_,d1.id_,d1.name_ ");
			sql.append(" order by r.orderby_,d1.id_ ");
			FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
			FyWebResult fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> listLawobj = pos.getRe();
			Map<String, String> dataObject = null;
			for (Object[] obj : listLawobj) {
				dataObject = new HashMap<String, String>();
				dataObject.put("regionid", String.valueOf(obj[0]));
				dataObject.put("regionname", String.valueOf(obj[1]));
				dataObject.put("lawobjtypecode", String.valueOf(obj[2]));
				dataObject.put("lawobjtypename", obj[3]==null?"":String.valueOf(obj[3]));
				if(String.valueOf(obj[0]).endsWith("00")){
					dataObject.put("pwdwsl", "");
					dataObject.put("wrwlxsl", "");
					dataObject.put("pfksl", "");
					dataObject.put("fzsssl", "");
					dataObject.put("zxsssl", "");
				}else{
					dataObject.put("pwdwsl", obj[4]==null?"":String.valueOf(obj[4]));
					BigDecimal fsNumber =  obj[5]==null?BigDecimal.ZERO:(BigDecimal)obj[5];
					BigDecimal fqNumber = obj[6]==null?BigDecimal.ZERO:(BigDecimal)obj[6];
					BigDecimal zsNumber = obj[7]==null?BigDecimal.ZERO:(BigDecimal)obj[7];
					BigDecimal gfNumber = obj[8]==null?BigDecimal.ZERO:(BigDecimal)obj[8];
					BigDecimal fzssNumber = obj[9]==null?BigDecimal.ZERO:(BigDecimal)obj[9];
					Integer wrwlxNumber = 0;
					wrwlxNumber += fsNumber.longValue()>0?1:0;
					wrwlxNumber += fqNumber.longValue()>0?1:0;
					wrwlxNumber += zsNumber.longValue()>0?1:0;
					wrwlxNumber += gfNumber.longValue()>0?1:0;
					dataObject.put("wrwlxsl", wrwlxNumber.toString());
					Long pfksl = fsNumber.longValue()+fqNumber.longValue()+zsNumber.longValue()+gfNumber.longValue();
					dataObject.put("pfksl", pfksl.toString());
					dataObject.put("fzsssl", fzssNumber.toString());
					dataObject.put("zxsssl", fzssNumber.toString());
				}
				rows.add(dataObject);
			}
			return fy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 函数介绍：视图sql
	
	 * 输入参数：
	
	 * 返回值：
	 * @throws Exception 
	 */
	private StringBuffer getViewSql() throws Exception{
		StringBuffer sql = new StringBuffer("with lawobj_wrw(regionid_,lawobjid_,lawobjtype,fs,fq,zs,gf,fzss) as(");
		List<String> list = lawobjManager.queryLawobjColumnByEnmu("01", LawobjOutColunmEnum.gywry_ssxzq.getCode(), LawobjOutColunmEnum.gywry_fspfkgs.getCode(), LawobjOutColunmEnum.gywry_fqpfkgs.getCode(),
				LawobjOutColunmEnum.gywry_zsygs.getCode(), LawobjOutColunmEnum.gywry_gfdfcgs.getCode(),LawobjOutColunmEnum.gywry_wrfzsssl.getCode());
		if (list.size() != 6) {
			throw new Exception("工业污染源有字段未配置!");
		}
		this.viewSqlHelp(sql, "01", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//建设项目
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("02", LawobjOutColunmEnum.jsxm_ssxzq.getCode());
		if (list.size() != 1) {
			throw new Exception("建设项目信息有字段未配置!");
		}
		sql.append(" select l.").append(list.get(0)).append(" regionid_,l.id_ lawobjid_,l.lawobjtype_,0 fs,0 fq,0 zs,0 gf,0 fzss  ");
		sql.append(" from t_data_lawobj l  where l.lawobjtype_ = '02' and l.isactive_ = 'Y' ");
		//医院
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("03", LawobjOutColunmEnum.yy_ssxzq.getCode(), LawobjOutColunmEnum.yy_fspfkgs.getCode(), LawobjOutColunmEnum.yy_fqpfkgs.getCode(), LawobjOutColunmEnum.yy_zsygs.getCode(),
				LawobjOutColunmEnum.yy_gfdfcgs.getCode(),LawobjOutColunmEnum.yy_wryfzsssl.getCode());
		if (list.size() != 6) {
			throw new Exception("医院信息有字段未配置!");
		}
		this.viewSqlHelp(sql, "03", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//锅炉
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("04", LawobjOutColunmEnum.gl_ssxzq.getCode(), LawobjOutColunmEnum.gl_fspfkgs.getCode(), LawobjOutColunmEnum.gl_fqpfkgs.getCode(), LawobjOutColunmEnum.gl_zsygs.getCode(),
				LawobjOutColunmEnum.gl_gfdfcgs.getCode(),LawobjOutColunmEnum.gl_wryfzsssl.getCode());
		if (list.size() != 6) {
			throw new Exception("锅炉信息有字段未配置!");
		}
		this.viewSqlHelp(sql, "04", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//建筑工地
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("05", LawobjOutColunmEnum.jzgd_ssxzq.getCode());
		if (list.size() != 1) {
			throw new Exception("建筑工地信息有字段未配置!");
		}
		sql.append(" select l.").append(list.get(0)).append(" regionid_,l.id_ lawobjid_,l.lawobjtype_,0 fs,0 fq,0 zs,0 gf,0 fzss  ");
		sql.append(" from t_data_lawobj l  where l.lawobjtype_ = '05' and l.isactive_ = 'Y' ");
		//三产
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("06", LawobjOutColunmEnum.sc_ssxzq.getCode(), LawobjOutColunmEnum.sc_fspfkgs.getCode(), LawobjOutColunmEnum.sc_fqpfkgs.getCode(), LawobjOutColunmEnum.sc_zsygs.getCode(),
				LawobjOutColunmEnum.sc_gfdfcgs.getCode(),LawobjOutColunmEnum.sc_wryfzsssl.getCode());
		if (list.size() != 6) {
			throw new Exception("三产信息有字段未配置!");
		}
		this.viewSqlHelp(sql, "06", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//畜禽养殖
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("07", LawobjOutColunmEnum.xqyz_ssxzq.getCode(), LawobjOutColunmEnum.xqyz_fspfkgs.getCode(), LawobjOutColunmEnum.xqyz_fqpfkgs.getCode(), LawobjOutColunmEnum.xqyz_zsygs.getCode(),
				LawobjOutColunmEnum.xqyz_gfdfcgs.getCode(),LawobjOutColunmEnum.xqyz_wryfzsssl.getCode());
		if (list.size() != 6) {
			throw new Exception("畜禽养殖信息有字段未配置!");
		}
		this.viewSqlHelp(sql, "07", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//服务业
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("08", LawobjOutColunmEnum.fwy_ssxzq.getCode(), LawobjOutColunmEnum.fwy_lyt.getCode(), LawobjOutColunmEnum.fwy_trq.getCode(), LawobjOutColunmEnum.fwy_drsq.getCode(),
				LawobjOutColunmEnum.fwy_kt.getCode(),LawobjOutColunmEnum.fwy_djgngl.getCode());
		if (list.size() != 6) {
			throw new Exception("服务业有字段未配置!");
		}
		this.viewSqlHelp(sql, "08", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//饮食业
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("09", LawobjOutColunmEnum.ysy_ssxzq.getCode(), LawobjOutColunmEnum.ysy_bx.getCode(), LawobjOutColunmEnum.ysy_trq.getCode(), LawobjOutColunmEnum.ysy_zbj.getCode(),
				LawobjOutColunmEnum.ysy_pqfj.getCode(),LawobjOutColunmEnum.ysy_fl.getCode());
		if (list.size() != 6) {
			throw new Exception("饮食业有字段未配置!");
		}
		this.viewSqlHelp(sql, "09", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//三产制造业
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("10", LawobjOutColunmEnum.zzy_ssxzq.getCode(), LawobjOutColunmEnum.zzy_qgj.getCode(), LawobjOutColunmEnum.zzy_dj.getCode(), LawobjOutColunmEnum.zzy_dkj.getCode(),
				LawobjOutColunmEnum.zzy_zzj.getCode(),LawobjOutColunmEnum.zzy_db.getCode());
		if (list.size() != 6) {
			throw new Exception("三产制造业有字段未配置!");
		}
		this.viewSqlHelp(sql, "10", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		//娱乐业
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("11", LawobjOutColunmEnum.yly_ssxzq.getCode(), LawobjOutColunmEnum.yly_pqs.getCode(), LawobjOutColunmEnum.yly_pfk.getCode(), LawobjOutColunmEnum.yly_zw.getCode(),
				LawobjOutColunmEnum.yly_kts.getCode(),LawobjOutColunmEnum.yly_dn.getCode());
		if (list.size() != 6) {
			throw new Exception("娱乐业有字段未配置!");
		}
		this.viewSqlHelp(sql, "11", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
		sql.append(" ) ");
		return sql;
	}

	/**
	 * 
	 * 函数介绍：视图帮助方法
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private void viewSqlHelp(StringBuffer sql,String lawobjtype,String regionid,String fs,String fq,String zs,String gf,String fzss){
		sql.append(" select l.").append(regionid).append(" regionid_,l.id_ lawobjid_,l.lawobjtype_, ");
		sql.append(" case when isnumeric(l.").append(fs).append(") = 1 then to_number(l.").append(fs).append(") else 0 end as fs, ");
		sql.append(" case when isnumeric(l.").append(fq).append(") = 1 then to_number(l.").append(fq).append(") else 0 end as fq, ");
		sql.append(" case when isnumeric(l.").append(zs).append(") = 1 then to_number(l.").append(zs).append(") else 0 end as zs, ");
		sql.append(" case when isnumeric(l.").append(gf).append(") = 1 then to_number(l.").append(gf).append(") else 0 end as gf, ");
		sql.append(" case when isnumeric(l.").append(fzss).append(") = 1 then to_number(l.").append(fzss).append(") else 0 end as fzss ");  
		sql.append(" from t_data_lawobj l  where l.lawobjtype_ = '").append(lawobjtype).append("' and l.isactive_ = 'Y' ");
	}

	@Override
	public void exportStatisticalList(String regionId, String lawobjtype, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> listConditions = new ArrayList<Object>();
		ConditionsForm conditionsForm = new ConditionsForm();
		if(StringUtil.isNotBlank(regionId)){
			TDataRegion tDataRegion = (TDataRegion)this.get(TDataRegion.class, regionId);
			conditionsForm.setRegionname(tDataRegion.getName());
		}
		if(StringUtil.isNotBlank(lawobjtype)){
			List<TDataLawobjtype> listDic = this.dao.find("from TDataLawobjtype d where d.isactive='Y' and d.id="+lawobjtype+"");
			if(listDic.size()>0){
				for(TDataLawobjtype type:listDic){
					conditionsForm.setLawobjtypename(type.getName());
				}
				
			}
		}
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);
		List list = this.queryAllStatisticalList(regionId, lawobjtype);
		map.put("tjbbForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/tjbb_StatisticalList.xls"), 
					new File(realPath + "excel/jxkh/tjbb_StatisticalList.xml"), map, false);
			ExcelUtil.copyStyle(file, "统计排污单位数", 3, 0, 3 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("统计排污单位数.xls".getBytes("GB2312"), "ISO-8859-1"));
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
	
	/**
	 * 
	 * 函数介绍：查询统计列表（不分页），并封装为form，为导出excel使用
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private List<TjbbForm> queryAllStatisticalList(String regionId, String lawobjtype){
		List<TjbbForm> list = new ArrayList<TjbbForm>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append( " with lawobj_wrw(regionid_,lawobjtype,lawobjid_,fs,fq,zs,gf,fzss) as ( " );
			sql.append( "  select  f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRFZSSSL_) = 1 then to_number(g.WRFZSSSL_) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_GYWRY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_JSXM g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_YY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_GL g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_JZGD g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_SC g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric(g.WRYFZSSSL_) = 1 then to_number(g.WRYFZSSSL_) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_XQYZ g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_FWY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss from  t_data_lawobjf f RIGHT  JOIN T_DATA_YSY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_ZZY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append( " union " );
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_YLY g on g.FID_=f.ID_ where f.ISACTIVE_='Y' " );
			sql.append(" union ");
			sql.append( " select f.SSXZQ_,f.LAWOBJTYPEID_,f.ID_   , case when isnumeric(f.FSPFKS_) = 1 then to_number(f.FSPFKS_) else 0 end as fs   , case when isnumeric(f.FQPFKS_ ) = 1 then to_number(f.FQPFKS_ ) else 0 end as fq   ,   case when isnumeric(f.ZSYGS_) = 1 then to_number(f.ZSYGS_) else 0 end as zs    ,  case when isnumeric(f.GFDFCGS_ ) = 1 then to_number(f.GFDFCGS_ ) else 0 end as gf    ,    case when isnumeric('a') = 1 then to_number(1) else 0 end as fzss  from  t_data_lawobjf f RIGHT  JOIN T_DATA_LAWOBJTYPE g on g.ID_=f.LAWOBJTYPEID_ where f.ISACTIVE_='Y' AND g.ISACTIVE_='Y' AND G.ID_ NOT IN(1,2,3,4,5,6,7,8,9,10,11) " );
			sql.append( " ) " );
			
			
			Map<String, Object> condition = new HashMap<String, Object>();
			sql.append(" select r.id_,r.name_ regionname,d1.id_ code_,d1.name_ lawobjtype,count(w.lawobjid_) wrysum,sum(fs),sum(fq),sum(zs),sum(gf),sum(fzss) ");
			sql.append(" from t_data_region r right join lawobj_wrw w on r.id_ = w.regionid_ ");
			sql.append(" left join T_DATA_LAWOBJTYPE d1 on w.lawobjtype = d1.id_ ");
			
			sql.append(" where 1=1 ");
			if(StringUtils.isNotBlank(regionId)){
				sql.append(" and r.id_ = :regionid ");
				condition.put("regionid", regionId);
			}
			if(StringUtils.isNotBlank(lawobjtype)){
				sql.append(" and d1.id_ = :lawobjtype ");
				condition.put("lawobjtype", lawobjtype);
			}
			String areaid = CtxUtil.getAreaId();
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				sql.append(" and r.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}
			sql.append(" group by r.id_,r.name_,r.orderby_,d1.id_,d1.name_ ");
			sql.append(" order by r.orderby_,d1.id_ ");
			List<Object[]> pos = this.dao.findBySql(sql.toString(), condition);
			TjbbForm tjbbForm = null;
			for (int i=0;i<pos.size();i++) {
				Object[] obj = pos.get(i);
				tjbbForm = new TjbbForm();
				tjbbForm.setSeq(String.valueOf(i+1));
				tjbbForm.setRegionid(String.valueOf(obj[0]));
				tjbbForm.setRegionname(String.valueOf(obj[1]));
				tjbbForm.setLawobjtypecode(String.valueOf(obj[2]));
				tjbbForm.setLawobjtypename(obj[3]==null?"":String.valueOf(obj[3]));
				if(!String.valueOf(obj[0]).endsWith("00")){
					tjbbForm.setPwdwsl(String.valueOf(obj[4]));
					BigDecimal fsNumber =  obj[5]==null?BigDecimal.ZERO:(BigDecimal)obj[5];
					BigDecimal fqNumber = obj[6]==null?BigDecimal.ZERO:(BigDecimal)obj[6];
					BigDecimal zsNumber = obj[7]==null?BigDecimal.ZERO:(BigDecimal)obj[7];
					BigDecimal gfNumber = obj[8]==null?BigDecimal.ZERO:(BigDecimal)obj[8];
					BigDecimal fzssNumber = obj[9]==null?BigDecimal.ZERO:(BigDecimal)obj[9];
					Integer wrwlxNumber = 0;
					wrwlxNumber += fsNumber.longValue()>0?1:0;
					wrwlxNumber += fqNumber.longValue()>0?1:0;
					wrwlxNumber += zsNumber.longValue()>0?1:0;
					wrwlxNumber += gfNumber.longValue()>0?1:0;
					tjbbForm.setWrwlxsl(wrwlxNumber.toString());
					Long pfksl = fsNumber.longValue()+fqNumber.longValue()+zsNumber.longValue()+gfNumber.longValue();
					tjbbForm.setPfksl(pfksl.toString());
					tjbbForm.setFzsssl(fzssNumber.toString());
				}
				list.add(tjbbForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
