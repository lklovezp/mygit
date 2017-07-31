package com.hnjz.app.jxkh.quarterstatistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import com.hnjz.app.data.enums.JcxEnum;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按季度统计managerImpl
 *
 */
@Service("quarterStatisticsManager")
public class QuarterStatisticsManagerImpl extends ManagerImpl implements QuarterStatisticsManager, ServletContextAware {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Autowired
	private LawobjManager lawobjManager;

	@Override
	public List<QuarterForm> statisticsQuarterList(String areaid,String orgid, String starttime, String endtime) {
		try {
			StringBuffer sql = this.quarterSqlHelp();
			Map<String, Object> condition = new HashMap<String, Object>();
			//--- 个数视图
			sql.append(" ,lawobjnum_tasktype(tasktypeid_,fsgs,fqgs,zsgs,gfgs) as (   ");
			sql.append("  select  case when o.tasktypeid_ = '13' then o.xftsly_ else o.tasktypeid_ end tasktypeid, ");
			sql.append("  sum(r.fs),sum(r.fq),sum(r.zs),sum(r.gf) from ( ");
			sql.append("  	select distinct t.tasktypeid_,t.xftsly_, l.lawobjid_ from  work_ w "); 
			sql.append("  	left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_  ");
			sql.append("  	left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" 	left join t_biz_taskuser u on w.id_ =  u.taskid_ and u.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" 	left join t_sys_userorg m on u.userid_ = m.userid_ ");
			sql.append(" 	left join t_sys_org g on m.orgid_ = g.id_ ");
			sql.append("  	where  w.XP_CITY is null and w.isactive_ = 'Y' and w.state_ = :state ");
			condition.put("state", WorkState.YGD.getCode());
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("starttime", starttime + " 00:00:00");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("endtime", endtime + " 23:59:59");
			}
			TSysUser user = CtxUtil.getCurUser();
			sql.append("	and w.areaid_ = :areaid ");
			if(StringUtils.isNotBlank(areaid)){
				condition.put("areaid", areaid);
			}else{
				condition.put("areaid", user.getAreaId());
			}
			
			sql.append("  ) o  ");
			sql.append("  left join lawobj_num r on r.lawobjid_ = o.lawobjid_ ");
			sql.append("  group by o.tasktypeid_,o.xftsly_ ");
			sql.append(" ), ");
			//--- 次数和人次视图
			sql.append(" lawobjcs_tasktype(tasktypeid_,wccs,fscs,fqcs,zscs,gfcs,fsrc,fqrc,zsrc,gfrc,xczrs) as (    ");
			sql.append(" select case when t.tasktypeid_ = '13' then t.xftsly_ else t.tasktypeid_ end tasktypeid, ");
			sql.append(" count(distinct w.id_) wccs, ");
			sql.append(" sum( r.fs) fscs, ");
			sql.append(" sum( r.fq) fqcs, ");
			sql.append(" sum( r.zs) zscs, ");
			sql.append(" sum( r.gf) gfcs, ");
			sql.append(" sum( r.fs*c.rs) fsrc, ");
			sql.append(" sum( r.fq*c.rs) fqrc, ");
			sql.append(" sum( r.zs*c.rs) zsrc, ");
			sql.append(" sum( r.gf*c.rs) gfrc, ");
			sql.append(" sum( c.rs) xczrs ");//从t_biz_taskuser查询现场监察总人次，每次任务都有主办协办，对应code('05','06')
			sql.append(" from work_ w  ");
			sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
			sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" left join lawobj_num r on l.lawobjid_ = r.lawobjid_ ");
			sql.append(" left join (select u.taskid_, count(u.userid_) rs  from t_biz_taskuser u where u.TYPE_ = '")
			.append(TaskUserEnum.ZBR.getCode()).append("' or u.type_ = '")
			.append(TaskUserEnum.XBR.getCode()).append("' group by u.taskid_) c ");
			sql.append(" on w.id_ = c.taskid_ ");
			sql.append(" left join t_biz_taskuser u on w.id_ =  u.taskid_ and u.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on u.userid_ = m.userid_ ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_ ");
			sql.append(" where w.XP_CITY is null and w.isactive_ = 'Y' and w.state_= :state ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append("	and w.areaid_ = :areaid ");
			sql.append("  group by t.tasktypeid_,t.xftsly_ ");
			sql.append(" ) ");
			
			// 联合个数和次数
			sql.append(" select * from (select t.NAME_,c.wccs,g.fsgs,g.fqgs,g.zsgs,g.gfgs,c.fscs,c.fqcs,c.zscs,c.gfcs,c.fsrc,c.fqrc,c.zsrc,c.gfrc,c.xczrs,t.code_ ");
			sql.append(" from t_data_tasktype t  ");
			sql.append(" left join lawobjnum_tasktype g on t.code_ = g.tasktypeid_ "); 
			sql.append(" left join lawobjcs_tasktype c on g.tasktypeid_  = c.tasktypeid_ "); 
			sql.append(" where t.code_ in ('10','12') order by t.code_) z  ");
			
			sql.append(" union all ");
			sql.append(" select * from (select t.NAME_,c.wccs,g.fsgs,g.fqgs,g.zsgs,g.gfgs,c.fscs,c.fqcs,c.zscs,c.gfcs,c.fsrc,c.fqrc,c.zsrc,c.gfrc,c.xczrs,t.code_ ");
			sql.append(" from t_sys_dic t  ");
			sql.append(" left join lawobjnum_tasktype g on t.code_ = g.tasktypeid_ "); 
			sql.append(" left join lawobjcs_tasktype c on g.tasktypeid_  = c.tasktypeid_ "); 
			sql.append(" where t.type_ = '").append(DicTypeEnum.XFTSLY.getCode()).append("' order by t.type_) y  ");
			
			List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
			Integer otherWorkNumber = 0;
			String otherWorkStr = "";
			List<Object[]> otherTaskList = this.getOtherWokeNumber("'10','12','13','11','16'",areaid, orgid, starttime, endtime);
			for(int i=0;i<otherTaskList.size();i++){
				Object[] obj = otherTaskList.get(i);
				if(obj[1]!=null && ((BigDecimal) obj[1]).intValue()>0){
					otherWorkStr+=String.valueOf(obj[2])+":"+((BigDecimal) obj[1]).intValue()+"次";
					if(i!=otherTaskList.size()-1){
						otherWorkStr += "、\r\n";
					}
					otherWorkNumber += ((BigDecimal) obj[1]).intValue();
				}
			}
			List<QuarterForm> listResult = new ArrayList<QuarterForm>();
			for (Object[] obj : list) {
				QuarterForm quarterForm = new QuarterForm(String.valueOf(obj[0]), obj[1] == null ? 0 : ((BigDecimal) obj[1]).intValue(), obj[2] == null ? 0 : ((BigDecimal) obj[2]).intValue(),
						obj[3] == null ? 0 : ((BigDecimal) obj[3]).intValue(), obj[4] == null ? 0 : ((BigDecimal) obj[4]).intValue(), obj[5] == null ? 0 : ((BigDecimal) obj[5]).intValue(),
						obj[6] == null ? 0 : ((BigDecimal) obj[6]).intValue(), obj[7] == null ? 0 : ((BigDecimal) obj[7]).intValue(), obj[8] == null ? 0 : ((BigDecimal) obj[8]).intValue(),
						obj[9] == null ? 0 : ((BigDecimal) obj[9]).intValue(), obj[10] == null ? 0 : ((BigDecimal) obj[10]).intValue(), obj[11] == null ? 0 : ((BigDecimal) obj[11]).intValue(),
						obj[12] == null ? 0 : ((BigDecimal) obj[12]).intValue(), obj[13] == null ? 0 : ((BigDecimal) obj[13]).intValue(),
						obj[14] == null ? 0 : ((BigDecimal) obj[14]).intValue(), obj[15] == null?"":String.valueOf(obj[15]));
				quarterForm.setQtjcs(Integer.valueOf(otherWorkNumber));
				quarterForm.setQtjcRemark(otherWorkStr);
				listResult.add(quarterForm);
			}
			return listResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 函数介绍：执法对象对应各个因子排口数量
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private StringBuffer quarterSqlHelp() throws Exception {
		StringBuffer sql = new StringBuffer("with lawobj_num(lawobjid_,fs,fq,zs,gf) as (");
		List<String> list = lawobjManager.queryLawobjColumnByEnmu("01", LawobjOutColunmEnum.gywry_fspfkgs.getCode(), LawobjOutColunmEnum.gywry_fqpfkgs.getCode(),
				LawobjOutColunmEnum.gywry_zsygs.getCode(), LawobjOutColunmEnum.gywry_gfdfcgs.getCode());
		if (list.size() != 4) {
			throw new Exception("工业污染源有字段未配置!");
		}
		this.viewSql(sql, "01", list.get(0), list.get(1), list.get(2), list.get(3));
		//建设项目
		sql.append(" union ");
		sql.append(" select l.id_ lawobjid_,0 fs,0 fq,0 zs,0 gf from t_data_lawobj l ");
		sql.append(" where l.lawobjtype_ = '02' ");
//		sql.append(" and l.isactive_ = 'Y' ");
		//医院
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("03", LawobjOutColunmEnum.yy_fspfkgs.getCode(), LawobjOutColunmEnum.yy_fqpfkgs.getCode(), LawobjOutColunmEnum.yy_zsygs.getCode(),
				LawobjOutColunmEnum.yy_gfdfcgs.getCode());
		if (list.size() != 4) {
			throw new Exception("医院信息有字段未配置!");
		}
		this.viewSql(sql, "03", list.get(0), list.get(1), list.get(2), list.get(3));
		//锅炉
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("04", LawobjOutColunmEnum.gl_fspfkgs.getCode(), LawobjOutColunmEnum.gl_fqpfkgs.getCode(), LawobjOutColunmEnum.gl_zsygs.getCode(),
				LawobjOutColunmEnum.gl_gfdfcgs.getCode());
		if (list.size() != 4) {
			throw new Exception("锅炉信息有字段未配置!");
		}
		this.viewSql(sql, "04", list.get(0), list.get(1), list.get(2), list.get(3));
		//建设项目
		sql.append(" union ");
		sql.append(" select l.id_ lawobjid_,0 fs,0 fq,0 zs,0 gf from t_data_lawobj l ");
		sql.append(" where l.lawobjtype_ = '05' ");
//		sql.append(" and l.isactive_ = 'Y' ");
		//三产
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("06", LawobjOutColunmEnum.sc_fspfkgs.getCode(), LawobjOutColunmEnum.sc_fqpfkgs.getCode(), LawobjOutColunmEnum.sc_zsygs.getCode(),
				LawobjOutColunmEnum.sc_gfdfcgs.getCode());
		if (list.size() != 4) {
			throw new Exception("三产信息有字段未配置!");
		}
		this.viewSql(sql, "06", list.get(0), list.get(1), list.get(2), list.get(3));
		//畜禽养殖
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("07", LawobjOutColunmEnum.xqyz_fspfkgs.getCode(), LawobjOutColunmEnum.xqyz_fqpfkgs.getCode(), LawobjOutColunmEnum.xqyz_zsygs.getCode(),
				LawobjOutColunmEnum.xqyz_gfdfcgs.getCode());
		if (list.size() != 4) {
			throw new Exception("畜禽养殖信息有字段未配置!");
		}
		this.viewSql(sql, "07", list.get(0), list.get(1), list.get(2), list.get(3));
		sql.append(" ) ");
		return sql;
	}

	private void viewSql(StringBuffer sql, String lawobjtype, String fs, String fq, String zs, String gf) {
		sql.append(" select l.id_ lawobjid_, ");
		sql.append(" case when isnumeric(l.").append(fs).append(") = 1 then to_number(l.").append(fs).append(") else 0 end as fs, ");
		sql.append(" case when isnumeric(l.").append(fq).append(") = 1 then to_number(l.").append(fq).append(") else 0 end as fq, ");
		sql.append(" case when isnumeric(l.").append(zs).append(") = 1 then to_number(l.").append(zs).append(") else 0 end as zs, ");
		sql.append(" case when isnumeric(l.").append(gf).append(") = 1 then to_number(l.").append(gf).append(") else 0 end as gf ");
		sql.append(" from t_data_lawobj l ");
		sql.append(" where l.lawobjtype_ = '").append(lawobjtype).append("' ");
//		sql.append(" and l.isactive_ = 'Y' ");
	}

	@Override
	public QuarterZfqkForm queryQuarterZfqk(String areaid,String orgid, String starttime, String endtime) {
		QuarterZfqkForm quarterZfqkForm = new QuarterZfqkForm();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			//污染防治
			StringBuffer sql = this.quarterZfqkSqlHelp();
			sql.append(" select j.tasktypeid_,count(w.id_) worknum,sum(case when w.id_ is not null and isnumeric(j.fzss) = 1 then to_number(j.fzss) else 0 end) as fzss ");
			sql.append(" from lawobj_rwid j  ");
			sql.append(" left join work_ w on j.rwid_ = w.id_ and w.XP_CITY is null and  w.isActive_ = 'Y' and w.state_ = :state ");
			condition.put("state", WorkState.YGD.getCode());
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("starttime", starttime + " 00:00:00");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("endtime", endtime + " 23:59:59");
			}
			TSysUser user = CtxUtil.getCurUser();
			sql.append("	and w.areaid_ = :areaid ");
			if(StringUtils.isNotBlank(areaid)){
				condition.put("areaid", areaid);
			}else{
				condition.put("areaid", user.getAreaId());
			}
			sql.append(" left join t_biz_taskuser tu on j.tasktypeid_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_ ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" where l.lawobjtype_ is not null and l.lawobjtype_ != '02' and j.tasktypeid_ = '10' ");
			sql.append(" group by j.tasktypeid_ ");
			List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setWrfzsszts(obj[2] == null ? "0" : String.valueOf(((BigDecimal) obj[2]).intValue()));
				quarterZfqkForm.setWrfzxcjczcs(obj[1] == null ? "0" : String.valueOf(((BigDecimal) obj[1]).intValue()));
			}

			//建设项目
			sql = new StringBuffer();
			sql.append(" select p.code_,count(w.id_) countWoke,count(k.taskid_) countTemplate from t_data_tasktype p ");
			sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and p.code_ = t.tasktypeid_ ");
			sql.append(" left join work_ w on t.taskid_ = w.id_ and w.XP_CITY is null and  w.isActive_ = 'Y' and w.state_ = :state ");
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" and w.areaid_ = :areaid ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" left join ( ");
			sql.append(" select c.taskid_,c.desc_ from T_BIZ_CHECKLIST c  ");
			sql.append(" left join T_DATA_CHECKLISTITEM i on c.itemid_ = i.id_ ");
			sql.append(" left join T_DATA_CHECKLISTITEMANS s on c.answerid_ = s.id_ ");
			sql.append(" where i.code_ = :jcxCode and s.ISNORMAL_='Y' ");
			sql.append(" ) K on w.id_ = k.taskid_ ");
			sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" where l.lawobjtype_ = '02'  and p.code_ = '10' ");
			sql.append(" group by p.code_ ");
			condition.put("jcxCode", JcxEnum.jsxm_xmsfajhsg.getCode());
			list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setJsxmxcjczcs(obj[1] == null ? "0" : String.valueOf(((BigDecimal) obj[1]).intValue()));
				quarterZfqkForm.setAjhxmsgs(obj[2] == null ? "0" : String.valueOf(((BigDecimal) obj[2]).intValue()));
			}
			
			sql = new StringBuffer();
			sql.append(" select p.code_,count(w.id_) countWoke,sum(case when isnumeric(k.desc_)=1 then to_number(k.desc_) else 0 end) countTemplate from t_data_tasktype p ");
			sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and p.code_ = t.tasktypeid_ ");
			sql.append(" left join work_ w on t.taskid_ = w.id_ and w.isActive_ = 'Y' and w.state_ = :state ");
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" and w.areaid_ = :areaid ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" left join ( ");
			sql.append(" select c.taskid_,c.desc_ from T_BIZ_CHECKLIST c  ");
			sql.append(" left join T_DATA_CHECKLISTITEM i on c.itemid_ = i.id_ ");
			sql.append(" left join T_DATA_CHECKLISTITEMANS s on c.answerid_ = s.id_ ");
			sql.append(" where i.code_ = :jcxCode ");
			sql.append(" ) K on w.id_ = k.taskid_ ");
			sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" where l.lawobjtype_ = '02'  and p.code_ = '10' ");
			sql.append(" group by p.code_ ");
			condition.put("jcxCode", JcxEnum.jsxm_xmsjtcs.getCode());
			list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setXmsjtcs(obj[2] == null ? "0" : String.valueOf(((BigDecimal) obj[2]).intValue()));
			}
			//建设项目防治设施投产数
			sql = new StringBuffer();
			sql.append(" select p.code_,sum(case when isnumeric(c.desc_)=1 then to_number(c.desc_) else 0 end) from t_data_tasktype p ");
			sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and p.code_ = t.tasktypeid_ ");
			sql.append(" left join work_ w on t.taskid_ = w.id_ and w.isActive_ = 'Y' and w.state_ = :state ");
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" and w.areaid_ = :areaid ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" left join T_BIZ_CHECKLIST c on w.id_ = c.taskid_ ");
			sql.append(" left join T_DATA_CHECKLISTITEM i on c.itemid_ = i.id_ ");
			sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
			sql.append(" where l.lawobjtype_ = '02' and i.code_ = :jcxCode and p.code_ = '10' ");
			sql.append(" group by p.code_ ");
			condition.put("jcxCode", JcxEnum.jsxm_fzsstcs.getCode());
			list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setFzsstcs(obj[1] == null ? "0" : String.valueOf(((BigDecimal) obj[1]).intValue()));
				Float stszxl = (StringUtil.isBlank(quarterZfqkForm.getXmsjtcs())||"0".equals(quarterZfqkForm.getXmsjtcs()))?0:(Float.parseFloat(quarterZfqkForm.getFzsstcs()) / Float.parseFloat(quarterZfqkForm.getXmsjtcs())*100);
				quarterZfqkForm.setStszxl(stszxl.intValue()+"%");
			}

			//限期治理项目现场监察情况
			sql = new StringBuffer();
			sql.append(" select t.code_,count(w.id_) from t_data_tasktype t ");
			sql.append(" left join t_biz_taskandtasktype p on p.ISACTIVE_ = 'Y' and t.code_ = p.tasktypeid_ ");
			sql.append(" left join work_ w on p.taskid_ = w.id_ and w.isActive_ = 'Y' and w.state_ = :state ");
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" and w.areaid_ = :areaid ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" where t.code_ = :jcxCode ");
			condition.put("jcxCode", 17);
			sql.append(" group by t.code_  ");
			list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setXqzlxcjczcs(obj[1] == null ? "0" : String.valueOf(((BigDecimal) obj[1]).intValue()));
			}

			//许可证现场检查情况
			sql = new StringBuffer();
			sql.append(" select p.code_,count(w.id_) countWoke,count(distinct k.taskid_) countTemplate from t_data_tasktype p ");
			sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and p.code_ = t.tasktypeid_ ");
			sql.append(" left join work_ w on t.taskid_ = w.id_ and w.isActive_ = 'Y' and w.state_ = :state ");
			if (StringUtils.isNotBlank(starttime)) {
				sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			}
			if (StringUtils.isNotBlank(endtime)) {
				sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" and w.areaid_ = :areaid ");
			sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
			sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
			sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
			if (StringUtils.isNotBlank(orgid)) {
				String[] arg = orgid.split(",");
				String orgidStr = "";
				for (int i = 0; i < arg.length; i++) {
					orgidStr += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						orgidStr += ",";
					}
				}
				sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
			}
			sql.append(" left join ( ");
			sql.append(" select c.taskid_ from T_BIZ_CHECKLIST c  ");
			sql.append(" left join T_DATA_CHECKLISTITEM i on c.itemid_ = i.id_ ");
			sql.append(" left join T_DATA_CHECKLISTITEMANS s on c.answerid_ = s.id_ ");
			sql.append(" where i.code_ = :jcxCode and s.isnormal_ = 'Y'");
			sql.append(" ) K on w.id_ = k.taskid_ ");
			sql.append(" where p.code_ = '14' ");
			sql.append(" group by p.code_ ");
			condition.put("jcxCode", JcxEnum.xkz_fzsstcs.getCode());
			list = this.dao.findBySql(sql.toString(), condition);
			if (list.size() > 0) {
				Object[] obj = list.get(0);
				quarterZfqkForm.setXkzxcjczcs(obj[1] == null ? "0" : String.valueOf(((BigDecimal) obj[1]).intValue()));
				quarterZfqkForm.setCbpwjs(obj[2] == null ? "0" : String.valueOf(((BigDecimal) obj[2]).intValue()));
			}
			// 其他检查次数
			Integer otherWorkNumber = 0;
			List<Object[]> otherTaskList = this.getOtherWokeNumber("'10','11','14','16','17'", areaid,orgid, starttime, endtime);
			for(Object[] obj : otherTaskList){
				if(obj[1]!=null && ((BigDecimal) obj[1]).intValue()>0){
					otherWorkNumber += ((BigDecimal) obj[1]).intValue();
				}
			}
			quarterZfqkForm.setQtjccs(String.valueOf(otherWorkNumber));
			Integer xcjczcs = (StringUtil.isNotBlank(quarterZfqkForm.getWrfzxcjczcs())?Integer.parseInt(quarterZfqkForm.getWrfzxcjczcs()):0) 
					+ (StringUtil.isNotBlank(quarterZfqkForm.getJsxmxcjczcs())?Integer.parseInt(quarterZfqkForm.getJsxmxcjczcs()):0) 
					+ (StringUtil.isNotBlank(quarterZfqkForm.getXqzlxcjczcs())?Integer.parseInt(quarterZfqkForm.getXqzlxcjczcs()):0)
					+ (StringUtil.isNotBlank(quarterZfqkForm.getXkzxcjczcs())?Integer.parseInt(quarterZfqkForm.getXkzxcjczcs()):0)
					+ (StringUtil.isNotBlank(quarterZfqkForm.getQtjccs())?Integer.parseInt(quarterZfqkForm.getQtjccs()):0);
			quarterZfqkForm.setXcjczcs(String.valueOf(xcjczcs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quarterZfqkForm;
	}

	/**
	 * 
	 * 函数介绍：执法情况视图
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private StringBuffer quarterZfqkSqlHelp() throws Exception {
		StringBuffer sql = new StringBuffer("with lawobj_rwid(tasktypeid_,rwid_,lawobjid_,fzss) as (");
		List<String> list = lawobjManager.queryLawobjColumnByEnmu("01", LawobjOutColunmEnum.gywry_wrfzsssl.getCode());
		if (list.size() != 1) {
			throw new Exception("工业污染源有字段未配置!");
		}
		this.ZfqkViewSql(sql, "01", list.get(0));
		//建设项目
		sql.append(" union ");
		this.ZfqkViewSql(sql, "02", null);
		//医院
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("03", LawobjOutColunmEnum.yy_wryfzsssl.getCode());
		if (list.size() != 1) {
			throw new Exception("医院信息有字段未配置!");
		}
		this.ZfqkViewSql(sql, "03", list.get(0));
		//锅炉
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("04", LawobjOutColunmEnum.gl_wryfzsssl.getCode());
		if (list.size() != 1) {
			throw new Exception("锅炉信息有字段未配置!");
		}
		this.ZfqkViewSql(sql, "04", list.get(0));
		//建筑工地
		sql.append(" union ");
		this.ZfqkViewSql(sql, "05", null);
		//三产
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("06", LawobjOutColunmEnum.sc_wryfzsssl.getCode());
		if (list.size() != 1) {
			throw new Exception("三产信息有字段未配置!");
		}
		this.ZfqkViewSql(sql, "06", list.get(0));
		//畜禽养殖
		sql.append(" union ");
		list = lawobjManager.queryLawobjColumnByEnmu("07", LawobjOutColunmEnum.xqyz_wryfzsssl.getCode());
		if (list.size() != 1) {
			throw new Exception("畜禽养殖信息有字段未配置!");
		}
		this.ZfqkViewSql(sql, "07", list.get(0));
		sql.append(" ) ");
		return sql;
	}

	private void ZfqkViewSql(StringBuffer sql, String lawobjtype, String fzss) {
		sql.append(" select p.code_ tasktypeid_,t.taskid_ rwid_,l.id_ lawobjid_, ");
		if(StringUtil.isBlank(fzss)){
			sql.append(" 0 fs ");
		}else{
			sql.append(" case when isnumeric(l.").append(fzss).append(") = 1 then to_number(l.").append(fzss).append(") else 0 end as fs ");
		}
		sql.append(" from t_data_tasktype p ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj o on o.taskid_ = t.taskid_ ");
		sql.append(" left join t_data_lawobj l on o.lawobjid_ = l.id_   ");
		sql.append(" where o.lawobjtype_ = '").append(lawobjtype).append("' ");
//		sql.append(" and l.isactive_ = 'Y' ");
	}

	/**
	 * 
	 * 函数介绍：获取其他任务数
	
	 * 输入参数：taskCode：不包含的任务类型code，逗号隔开 sql:not in(taskCode);orgid-主办部门；要求完成时限
	
	 * 返回值：
	 */
	private List<Object[]> getOtherWokeNumber(String tasktypeCode, String areaid, String orgid, String starttime, String endtime) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> condition = new HashMap<String, Object>();
		sql.append(" select t.code_,case when t.code_ = '15' then count(distinct w.id_) else count(w.id_) end,t.name_ from t_data_tasktype t ");
		sql.append(" left join t_biz_taskandtasktype p on p.ISACTIVE_ = 'Y' and t.code_ = p.tasktypeid_ ");
		sql.append(" left join work_ w on p.taskid_ = w.id_ and w.isActive_ = 'Y'  ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ");
		sql.append(" left join t_sys_userorg m on tu.userid_ = m.userid_  ");
		sql.append(" left join t_sys_org g on m.orgid_ = g.id_  ");
		sql.append(" where w.XP_CITY is null and t.code_ not in (").append(tasktypeCode).append(")");
		if (StringUtils.isNotBlank(orgid)) {
			String[] arg = orgid.split(",");
			String orgidStr = "";
			for (int i = 0; i < arg.length; i++) {
				orgidStr += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgidStr += ",";
				}
			}
			sql.append(" and g.id_ is not null and g.id_ in (").append(orgidStr).append(")");
		}
		sql.append(" and w.state_ = :state ");
		condition.put("state", WorkState.YGD.getCode());
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime + " 23:59:59");
		}
		if (StringUtils.isNotBlank(endtime)) {
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime + " 00:00:00");
		}
		sql.append(" and w.areaid_ = :areaid ");
		TSysUser user = CtxUtil.getCurUser();
		if(StringUtils.isNotBlank(areaid)){
			condition.put("areaid", areaid);
		}else{
			condition.put("areaid", user.getAreaId());
		}
		sql.append(" group by t.code_,t.name_ order by t.code_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		return list;
	}

	@Override
	public void exportQuarterStatistics(String year, String quarter,String areaid, String orgid, String orgname, String starttime, String endtime, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
//		污染源监察情况
		List list = this.statisticsQuarterList(areaid, orgid, starttime, endtime);
		map.put("quarterForm", list);
		
//		环境监察现场监督执法情况
		QuarterZfqkForm quarterZfqkForm = this.queryQuarterZfqk(areaid, orgid, starttime, endtime);
		if(list.size()>0){
			quarterZfqkForm.setWryjcqtjccs(String.valueOf(((QuarterForm)list.get(0)).getQtjcs()));
			quarterZfqkForm.setWryjcqtjcRemark(String.valueOf(((QuarterForm)list.get(0)).getQtjcRemark()));
		}
		quarterZfqkForm.setOrgname(orgname);
		quarterZfqkForm.setYear(year);
		quarterZfqkForm.setQuarter(quarter);
		List<Object> quarterZfqkList = new ArrayList<Object>();
		quarterZfqkList.add(quarterZfqkForm);
		map.put("quarterZfqkForm", quarterZfqkList);
		
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(
					new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_quarterStatistical.xls"), 
					new File(realPath + "excel/jxkh/jxkh_quarterStatistical.xml"), 
					map, false);
			//					ExcelUtil.copyStyle(file, "任务完成情况", 4, 0, 4 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按季度环境监察工作统计表.xls".getBytes("GB2312"), "ISO-8859-1"));
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
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (os != null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
