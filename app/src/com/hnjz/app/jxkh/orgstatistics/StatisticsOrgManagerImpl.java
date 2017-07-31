package com.hnjz.app.jxkh.orgstatistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.org.OrgType;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按部门统计managerImpl
 *
 */
@Service("statisticsOrgManager")
public class StatisticsOrgManagerImpl extends ManagerImpl implements StatisticsOrgManager , ServletContextAware{
	
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 
	 * 函数介绍：公共sql帮助方法
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private String publicSqlHelp(){
		StringBuffer publicSql = new StringBuffer();
		publicSql.append(" with user_(rwid,userid) as( ");
		publicSql.append("	select distinct  w.id_,u.userid_ from work_ w left join T_BIZ_TASKUSER u on w.id_ = u.taskid_ ");
		publicSql.append("	where w.state_ in('")
		.append(WorkState.YXP.getCode()).append("','")
		.append(WorkState.JS.getCode()).append("','")
		.append(WorkState.BLZ.getCode()).append("','")
		.append(WorkState.YBL.getCode()).append("','")
		.append(WorkState.YSH.getCode()).append("','")
		.append(WorkState.YTH.getCode()).append("','")
		.append(WorkState.YGD.getCode()).append("') and (u.type_ = '").append(TaskUserEnum.ZBR.getCode()).append("' ")
		.append(" or u.type_ = '").append(TaskUserEnum.XBR.getCode()).append("' ")
		.append(" or u.type_ = '").append(TaskUserEnum.XPR.getCode()).append("' )");
		//未确定主办人 的任务统计当前待办人
		publicSql.append("	union ");
		publicSql.append("	select distinct w.id_,u.id_ from work_ w left join v_dbwork v on w.id_ = v.id_ left join t_sys_user u on v.username_ = u.username_ ");
		publicSql.append("	where (w.state_ is null or w.state_ in('")
		.append(WorkState.YPF.getCode()).append("','")
		.append(WorkState.YZP.getCode()).append("','")
		.append(WorkState.YXP.getCode()).append("') and u.id_ is not null ) ");
		
		publicSql.append("	), ");
		publicSql.append(" org_(orgid,orgname,rwid,orderby) as( ");
		publicSql.append("	select distinct o.id_,o.name_,r.rwid,o.orderby_ from user_ r  ");
		publicSql.append("	left join t_sys_user u on r.userid = u.id_  ");
		publicSql.append("	left join t_sys_userorg s on r.userid = s.userid_  ");
		publicSql.append("	left join t_sys_org o on s.orgid_ = o.id_ ");
		publicSql.append("	where u.isactive_ = 'Y' and o.isactive_ = 'Y' ");
		publicSql.append("	)  ");
		return publicSql.toString();
	}
	
	@Override
	public List<StatisticsForm> statisticsOrgList(String areaid,String tasktypeid, String rwly, String jjcd, String starttime, String endtime) {
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append("	select g.id_, ");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("	nvl(z.xd,0),nvl(z.wc,0),nvl(z.yqwc,0),nvl(z.zzbl,0),nvl(z.yqbl,0) from t_sys_org g ");
		sql.append("	 left join ( ");
		sql.append("	 select o.orgid,  ");
		sql.append("	sum(case when o.id_ is not null then 1 else 0 end ) as xd, ");
		sql.append("	sum(case when o.state_ = '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as wc, ");
		sql.append("	sum(case when o.state_ = '").append(WorkState.YGD.getCode()).append("' and o.ARCHIVES_TIME_ > o.END_TIME_ then 1 else 0 end) as yqwc, ");
		sql.append("	sum(case when o.state_ != '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as zzbl, ");
		sql.append("	sum(case when o.state_ != '").append(WorkState.YGD.getCode()).append("' and o.END_TIME_ < sysdate then 1 else 0 end) as yqbl ");
		sql.append("	 from (  ");
		sql.append("	select o.orgid, w.id_, w.state_, w.archives_time_, w.end_time_ ");
		sql.append("	from org_ o ");
		sql.append("	left join work_ w ");
		sql.append("	on o.rwid = w.id_ ");
		sql.append("	left join t_biz_taskandlawobj l ");
		sql.append("	on w.id_ = l.taskid_ ");
		sql.append("	left join t_biz_taskandtasktype t ");
		sql.append("	on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append("	where w.isactive_ = 'Y' ");
		sql.append("	and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		sql.append("	and w.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
			condition.put("tasktypeid", tasktypeid);
		}
		sql.append("	union all ");
		sql.append("	select o.orgid, w.id_, w.state_, w.archives_time_, w.end_time_ ");
		sql.append("	from org_ o ");
		sql.append("	left join work_ w ");
		sql.append("	on o.rwid = w.id_ ");
		sql.append("	left join t_biz_taskandtasktype t ");
		sql.append("	on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append("	where w.isactive_ = 'Y' ");
		sql.append("	and t.tasktypeid_ ='15' ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		sql.append("	and w.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		sql.append("	) o group by o.orgid) z ");
		sql.append("	on g.id_ = z.orgid ");
		sql.append("	 left join t_sys_area a on g.areaid_ = a.id_  ");
		sql.append(" where g.isactive_ = 'Y' ");
		TSysUser user = CtxUtil.getCurUser();
		sql.append("	and g.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append("	 order by g.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsForm> listResult = new ArrayList<StatisticsForm>();
		StatisticsForm sumForm = new StatisticsForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计值");
		for(Object[] obj : list){
			StatisticsForm statisticsForm = new StatisticsForm(String.valueOf(obj[0]),String.valueOf(obj[1]),
					((BigDecimal)obj[2]).intValue(),((BigDecimal)obj[3]).intValue(),
					((BigDecimal)obj[4]).intValue(),((BigDecimal)obj[5]).intValue(),
					((BigDecimal)obj[6]).intValue());
			listResult.add(statisticsForm);
			sumForm.setXd(sumForm.getXd()+statisticsForm.getXd());
			sumForm.setWc(sumForm.getWc()+statisticsForm.getWc());
			sumForm.setYqwc(sumForm.getYqwc()+statisticsForm.getYqwc());
			sumForm.setZzbl(sumForm.getZzbl()+statisticsForm.getZzbl());
			sumForm.setYqzzbl(sumForm.getYqzzbl()+statisticsForm.getYqzzbl());
		}
		listResult.add(sumForm);
		
		StringBuffer realSql = new StringBuffer(publicSqlHelp());
		realSql.append("select sum(case when w.id_ is not null then 1 else 0 end) as xd, ");
		realSql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as wc, ");
		realSql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' and w.ARCHIVES_TIME_ > w.END_TIME_ then 1 else 0 end) as yqwc, ");
		realSql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as zzbl, ");
		realSql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' and w.END_TIME_ < sysdate then 1 else 0 end) as yqbl ");
		realSql.append(" from ( ");
		realSql.append(" select  w.id_, w.state_, w.archives_time_, w.end_time_ from  ");
		realSql.append(" (select distinct rwid from org_) o ");
		realSql.append(" left join work_ w ");
		realSql.append(" on o.rwid = w.id_ ");
		realSql.append(" left join t_biz_taskandlawobj l ");
		realSql.append(" on w.id_ = l.taskid_ ");
		realSql.append(" left join t_biz_taskandtasktype t ");
		realSql.append(" on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		realSql.append(" where w.isactive_ = 'Y' ");
		realSql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		realSql.append(" and w.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(rwly)){
			realSql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			realSql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(starttime)){
			realSql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			realSql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			realSql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		
		realSql.append(" union all ");
		
		realSql.append(" select w.id_, w.state_, w.archives_time_, w.end_time_ from "); 
		realSql.append(" (select distinct rwid from org_) o ");
		realSql.append(" left join work_ w ");
		realSql.append(" on o.rwid = w.id_ ");
		realSql.append(" left join t_biz_taskandtasktype t ");
		realSql.append(" on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		realSql.append(" where w.isactive_ = 'Y' ");
		realSql.append(" and t.tasktypeid_ ='15' ");
		realSql.append(" and w.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(rwly)){
			realSql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			realSql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(starttime)){
			realSql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			realSql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			realSql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		realSql.append(" ) w ");
		condition.put("areaid", areaid);
		list = this.dao.findBySql(realSql.toString(), condition);
		StatisticsForm realForm = new StatisticsForm();
		realForm.setOrgid("real");
		realForm.setOrgname("实际任务总数");
		if(list.size()>0){
			realForm.setXd(list.get(0)[0]==null?0:((BigDecimal)list.get(0)[0]).intValue());
			realForm.setWc(list.get(0)[1]==null?0:((BigDecimal)list.get(0)[1]).intValue());
			realForm.setYqwc(list.get(0)[2]==null?0:((BigDecimal)list.get(0)[2]).intValue());
			realForm.setZzbl(list.get(0)[3]==null?0:((BigDecimal)list.get(0)[3]).intValue());
			realForm.setYqzzbl(list.get(0)[4]==null?0:((BigDecimal)list.get(0)[4]).intValue());
		}
		listResult.add(realForm);
		return listResult;
	}

	@Override
	public void exportStatisticalOrgList(String areaid, String areaname,String tasktypeid, String tasktypename, String rwly, String rwlyname, String jjcd, String jjcdname, String starttime, String endtime, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		if(StringUtils.isNotBlank(areaname)){
			conditionsForm.setAreaname(areaname);
		}else{
			conditionsForm.setAreaname("所有区域");
		}
		if (StringUtils.isNotBlank(tasktypename)) {
			conditionsForm.setTasktypename(tasktypename);
		} else {
			conditionsForm.setTasktypename("所有任务类型");
		}
		if (StringUtils.isNotBlank(rwlyname)) {
			conditionsForm.setRwlyname(rwlyname);
		} else {
			conditionsForm.setRwlyname("全部");
		}
		if (StringUtils.isNotBlank(jjcdname)) {
			conditionsForm.setJjcdname(jjcdname);
		} else {
			conditionsForm.setJjcdname("全部");
		}
		if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
			conditionsForm.setDatetime(starttime + "至" + endtime);
		} else if (StringUtils.isNotBlank(starttime)) {
			conditionsForm.setDatetime(starttime + "至今");
		} else if (StringUtils.isNotBlank(endtime)) {
			conditionsForm.setDatetime("至" + endtime);
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);

		//列表数据
		List list = this.statisticsOrgList(areaid,tasktypeid, rwly, jjcd, starttime, endtime);
		map.put("statisticsForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_statisticalByOrg.xls"), 
					new File(realPath + "excel/jxkh/jxkh_statisticalByOrg.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "按部门统计", list.size() + 7, list.size() + 7, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "按部门统计", 5, 0, 5 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按部门统计.xls".getBytes("GB2312"), "ISO-8859-1"));
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
	public FyWebResult queryStatisticalOrgInfo(String areaid,String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime,String page,String pageSize){
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append("select * from (");
		//非专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,l.lawobjname_,r.name_ regionname,u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj, w.SOURCE_  from work_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region r on l.regionid_ = r.id_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and w.id_ in (select distinct rwid from org_ g  ");
		if(StringUtils.isNotBlank(orgid) && !"sum".equals(orgid) && !"real".equals(orgid)){
			sql.append(" where g.orgid = :orgid");
			condition.put("orgid", orgid);
		}
		sql.append(" ) ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
			condition.put("tasktypeid", tasktypeid);
		}
		
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 3:
				sql.append(" and w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_ ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 4:
				sql.append(" and w.state_ != :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 5:
				sql.append(" and w.state_ != :state and w.END_TIME_ < sysdate ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		//区域
		if (StringUtils.isNotBlank(areaid)) {
			//sql.append(" and g.AREAID_= '").append(areaid).append("' ");
			sql.append(" and w.areaid_ = :areaid ");
			condition.put("areaid", areaid);
		}
		sql.append("	and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		
		sql.append(" union all");
		//专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname, ");
		sql.append(" u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj, w.SOURCE_ from work_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and w.id_ in (select distinct rwid from org_ g  ");
		if(StringUtils.isNotBlank(orgid) && !"sum".equals(orgid) && !"real".equals(orgid)){
			sql.append(" where g.orgid = :orgid");
		}
		sql.append(" ) ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 3:
				sql.append(" and w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_ ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 4:
				sql.append(" and w.state_ != :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 5:
				sql.append(" and w.state_ != :state and w.END_TIME_ < sysdate ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		sql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append("	and t.tasktypeid_ ='15' ");

		sql.append(") z order by z.end_time_ desc ");
		
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("workname", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("tasktypename", obj[2]==null?"":String.valueOf(obj[2]));
			String state = String.valueOf(obj[8]);
			if(StringUtils.equals(String.valueOf(obj[11]),"11")){//随机抽查任务，任务接受前企业名称不显示
				if(StringUtils.equals(state, WorkState.YPF.getCode()) || StringUtils.equals(state, WorkState.YZP.getCode()) || StringUtils.equals(state, WorkState.YXP.getCode())){
					dataObject.put("lawobjname", "×××××企业");
				}else{
					dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
				}
			}else{
				dataObject.put("lawobjname", obj[3]==null?"":String.valueOf(obj[3]));
			}
			dataObject.put("regionname", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("pfr", obj[5]==null?"":String.valueOf(obj[5]));
			dataObject.put("yqwcsx", obj[6]==null?"":String.valueOf(obj[6]));
			dataObject.put("zbry", obj[7]==null?"":String.valueOf(obj[7]));
			dataObject.put("rwzt", obj[8]==null?"":WorkState.getNameByCode(String.valueOf(obj[8])));
			dataObject.put("dqdclr", obj[9]==null?"":String.valueOf(obj[9]));
			dataObject.put("gdsj", obj[10]==null?"":String.valueOf(obj[10]));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public RwzxgcfxForm getRwzxgcfxFormByRwid(String id) {
		RwzxgcfxForm rwzxgcfxForm = new RwzxgcfxForm();
		StringBuffer sql = new StringBuffer();
		sql.append(" select w.work_name_,w.work_note_,d.name_ rwly,c.name_ rwmj,f.name_ jjcd,u.name_ djr,tu.username_ pfr, ");
		sql.append(" to_char(g.operate_time_,'yyyy-MM-dd') pfsj,to_char(w.end_time_,'yyyy-MM-dd') yqwcsx, ");
		sql.append(" w.state_,w.NEXT_OPER_,w.ZXR_NAME_,o.name_ zbbm,to_char(w.zxstarttime_, 'yyyy-MM-dd hh24:mi') blstarttime, to_char(w.zxtime_, 'yyyy-MM-dd hh24:mi') blendtime ");
		sql.append(" from work_ w  ");
		sql.append(" left join t_sys_dic d on w.source_ = d.code_ and d.type_ = '1' ");
		sql.append(" left join t_sys_dic c on w.SECURITY_ = c.code_ and c.type_ = '2' ");
		sql.append(" left join t_sys_dic f on w.EMERGENCY_ = f.code_ and f.type_ = '3' ");
		sql.append(" left join t_sys_user u on w.DJRID_ = u.id_  ");
		sql.append(" left join t_biz_taskuser tu on w.id_ = tu.taskid_ and tu.type_ = ? ");
		sql.append(" left join t_biz_taskoperlog g on w.id_ = g.work_id_ and g.operate_type_ = ? ");
		sql.append(" left join t_biz_taskoperlog bl on w.id_ = bl.work_id_ and bl.operate_type_ = ? ");
		sql.append(" left join t_sys_userorg uo on w.ZXR_ID_ = uo.userid_ ");
		sql.append(" left join t_sys_org o on uo.orgid_ = o.id_ ");
		sql.append(" where w.id_ = ? ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), TaskUserEnum.PFR.getCode(), WorkLogType.PF.getCode(),WorkLogType.ZX.getCode(), id);
		if(list.size()>0){
			Object[] obj = list.get(0);
			rwzxgcfxForm.setWorkname(obj[0]==null?"":String.valueOf(obj[0]));
			rwzxgcfxForm.setWorknote(obj[1]==null?"":String.valueOf(obj[1]));
			rwzxgcfxForm.setRwly(obj[2]==null?"":String.valueOf(obj[2]));
			rwzxgcfxForm.setRwmj(obj[3]==null?"":String.valueOf(obj[3]));
			rwzxgcfxForm.setJjcd(obj[4]==null?"":String.valueOf(obj[4]));
			rwzxgcfxForm.setDjr(obj[5]==null?"":String.valueOf(obj[5]));
			rwzxgcfxForm.setPfr(obj[6]==null?"":String.valueOf(obj[6]));
			rwzxgcfxForm.setPfsj(obj[7]==null?"":String.valueOf(obj[7]));
			rwzxgcfxForm.setYqwcsx(obj[8]==null?"":String.valueOf(obj[8]));
			rwzxgcfxForm.setRwzt(obj[9]==null?"":WorkState.getNameByCode(String.valueOf(obj[9])));
			rwzxgcfxForm.setDczr(obj[10]==null?"":String.valueOf(obj[10]));
			rwzxgcfxForm.setZbry(obj[11]==null?"":String.valueOf(obj[11]));
			rwzxgcfxForm.setZbbm(obj[12]==null?"":String.valueOf(obj[12]));
			String blsj = "";
			if(obj[13]!=null && StringUtil.isNotBlank(String.valueOf(obj[13]))){
				blsj += String.valueOf(obj[13]);
				blsj += "至";
				if(obj[14]!=null && StringUtil.isNotBlank(String.valueOf(obj[14]))){
					blsj += String.valueOf(obj[14]);
				}
			}
			rwzxgcfxForm.setBlsj(blsj);
			rwzxgcfxForm.setId(id);
		}
		return rwzxgcfxForm;
	}

	@Override
	public StageAnalysis getMaxStageAnalysis(String id) {
		StageAnalysis stageAnalysis = null;
		String sql = "select g.operate_type_,g.czr_name_,to_char(g.start_time_,'yyyy-MM-dd hh24:mi') starttime,to_char(g.operate_time_,'yyyy-MM-dd hh24:mi') from t_biz_taskoperlog g where g.work_id_ = ? order by g.user_time_ desc ";
		List<Object[]> list = this.dao.findBySql(sql,id);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			stageAnalysis = new StageAnalysis(
					obj[0] == null ? "" : WorkLogType.getNote(String.valueOf(obj[0])), 
					obj[1] == null ? "" : String.valueOf(obj[1]), 
					obj[2] == null ? ""	: String.valueOf(obj[2]), 
					obj[3] == null ? "" : String.valueOf(obj[3]));
		}
		return stageAnalysis;
	}
	
	@Override
	public List<StageAnalysis> querystageAnalysisList(String id){
		List<StageAnalysis> result = new ArrayList<StageAnalysis>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select g.operate_type_,g.czr_name_,to_char(g.start_time_,'yyyy-MM-dd hh24:mi') starttime,to_char(g.operate_time_,'yyyy-MM-dd hh24:mi') endtime, ");
		sql.append(" (case when g.user_time_ = o.maxtime then 'Y' else 'N' end ) ismaxtime ");
		sql.append(" from t_biz_taskoperlog g   ");
		sql.append(" left join (select l.work_id_ ,max(l.user_time_) maxtime  from t_biz_taskoperlog l  group by l.work_id_ ) o on g.work_id_ = o.work_id_ ");
		sql.append(" where g.work_id_ = ?  ");
		sql.append(" order by g.operate_time_,g.start_time_  ");
		List<Object[]> list = this.dao.findBySql(sql.toString(),id);
		if (list.size() > 0) {
			for(int i=0;i<list.size();i++){
				Object[] obj = list.get(i);
				StageAnalysis stageAnalysis = new StageAnalysis(
						obj[0] == null ? "" : WorkLogType.getNote(String.valueOf(obj[0])), 
								obj[1] == null ? "" : String.valueOf(obj[1]), 
										obj[2] == null ? ""	: String.valueOf(obj[2]), 
												obj[3] == null ? "" : String.valueOf(obj[3]),
														obj[4] == null ? "" : String.valueOf(obj[4]));
				result.add(stageAnalysis);
			}
		}
		return result;
	}
	
	@Override
	public StageAnalysis getYqStageAnalysis(String id){
		StageAnalysis stageAnalysis = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select g.operate_type_,g.czr_name_, ");
		sql.append(" to_char(g.start_time_,'yyyy-MM-dd hh24:mi') starttime, ");
		sql.append(" to_char(g.operate_time_,'yyyy-MM-dd hh24:mi') endtime ");
		sql.append(" from t_biz_taskoperlog g  ");
		sql.append(" left join work_ w on w.id_ = g.work_id_ ");
		sql.append(" where g.work_id_ = ? ");
		sql.append(" and g.operate_time_ > w.end_time_ ");
		sql.append(" order by g.operate_time_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(),id);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			stageAnalysis = new StageAnalysis(
					obj[0] == null ? "" : WorkLogType.getNote(String.valueOf(obj[0])), 
					obj[1] == null ? "" : String.valueOf(obj[1]), 
					obj[2] == null ? ""	: String.valueOf(obj[2]), 
					obj[3] == null ? "" : String.valueOf(obj[3]));
		}else{
			Work work = (Work) this.get(Work.class, id);
			if((work.getEndTime().compareTo(new Date())<0 && work.getGdsj()==null) || (work.getGdsj()!=null && work.getGdsj().compareTo(work.getEndTime())>0)){//逾期任务
				stageAnalysis = new StageAnalysis();
				stageAnalysis.setStageType(WorkState.getWorkStateByCode(work.getState()).getNextType());
				stageAnalysis.setOprateUser(work.getNextOper());
			}
		}
		return stageAnalysis;
	}
	
	@Override
	public void exportStatisticalOrgInfoList(String areaid,String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		String title = "按部门统计-";
		if("1".equals(type)){
			title += "下达次数";
		}else if("2".equals(type)){
			title += "完成次数";
		}else if("3".equals(type)){
			title += "逾期完成次数";
		}else if("4".equals(type)){
			title += "正在办理次数";
		}else if("5".equals(type)){
			title += "逾期正在办理次数";
		}
		conditionsForm.setTitle(title);
		conditionsForm.setOrgname(orgname);
		if(StringUtils.isNotBlank(areaname)){
			conditionsForm.setAreaname(areaname);
		}else{
			conditionsForm.setAreaname("所有区域");
		}
		if (StringUtils.isNotBlank(tasktypename)) {
			conditionsForm.setTasktypename(tasktypename);
		} else {
			conditionsForm.setTasktypename("所有任务类型");
		}
		if (StringUtils.isNotBlank(rwlyname)) {
			conditionsForm.setRwlyname(rwlyname);
		} else {
			conditionsForm.setRwlyname("全部");
		}
		if (StringUtils.isNotBlank(jjcdname)) {
			conditionsForm.setJjcdname(jjcdname);
		} else {
			conditionsForm.setJjcdname("全部");
		}
		if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
			conditionsForm.setDatetime(starttime + "至" + endtime);
		} else if (StringUtils.isNotBlank(starttime)) {
			conditionsForm.setDatetime(starttime + "至今");
		} else if (StringUtils.isNotBlank(endtime)) {
			conditionsForm.setDatetime("至" + endtime);
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);

		//列表数据
		List list = this.queryStatisticalOrgInfoList(areaid, orgid, type, tasktypeid, rwly, jjcd, starttime, endtime);
		map.put("statisticsTaskInfoForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_orgStatisticalInfo.xls"), 
					new File(realPath + "excel/jxkh/jxkh_orgStatisticalInfo.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "任务完成情况", list.size() + 7, list.size() + 7, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "任务完成情况", 4, 0, 4 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String((title + ".xls").getBytes("GB2312"), "ISO-8859-1"));
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
	 * 函数介绍：导出统计出来的任务列表使用
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private List<StatisticsTaskInfoForm> queryStatisticalOrgInfoList(String areaid,String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		List<StatisticsTaskInfoForm> result = new ArrayList<StatisticsTaskInfoForm>();
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append("select * from (");
		//非专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,l.lawobjname_,r.name_ regionname,u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj from work_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region r on l.regionid_ = r.id_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and w.id_ in (select distinct rwid from org_ g  ");
		if(StringUtils.isNotBlank(orgid) && !"sum".equals(orgid) && !"real".equals(orgid)){
			sql.append(" where g.orgid = :orgid");
			condition.put("orgid", orgid);
		}
		sql.append(" ) ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime+" 23:59:59");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
			condition.put("tasktypeid", tasktypeid);
		}
		
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 3:
				sql.append(" and w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_ ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 4:
				sql.append(" and w.state_ != :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 5:
				sql.append(" and w.state_ != :state and w.END_TIME_ < sysdate ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		sql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append("	and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		
		sql.append(" union all");
		//专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,");
		sql.append("  (select LISTAGG(l.lawobjname_, '、') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append("  (select LISTAGG(r.name_, '、') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname, ");
		sql.append(" u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj from work_ w  ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and w.id_ in (select distinct rwid from org_ g  ");
		if(StringUtils.isNotBlank(orgid) && !"sum".equals(orgid) && !"real".equals(orgid)){
			sql.append(" where g.orgid = :orgid");
		}
		sql.append(" ) ");
		if(StringUtils.isNotBlank(rwly)){
			sql.append(" and w.source_ = :rwly");
		}
		if(StringUtils.isNotBlank(jjcd)){
			sql.append(" and w.emergency_ = :jjcd");
		}
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(endtime)){
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if(StringUtils.isNotBlank(tasktypeid)){
			sql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 3:
				sql.append(" and w.state_ = :state and w.ARCHIVES_TIME_ > w.END_TIME_ ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 4:
				sql.append(" and w.state_ != :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			case 5:
				sql.append(" and w.state_ != :state and w.END_TIME_ < sysdate ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		sql.append("	and w.areaid_ = :areaid ");
		sql.append("	and t.tasktypeid_ ='15' ");

		sql.append(") z order by z.end_time_ desc ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		for (int i=0;i<list.size();i++) {
			Object[] obj = list.get(i);
			StatisticsTaskInfoForm statisticsTaskInfoForm = new StatisticsTaskInfoForm(
					String.valueOf(obj[0]),
					obj[1]==null?"":String.valueOf(obj[1]),
					obj[2]==null?"":String.valueOf(obj[2]),
					obj[3]==null?"":String.valueOf(obj[3]),
					obj[4]==null?"":String.valueOf(obj[4]),
					obj[5]==null?"":String.valueOf(obj[5]),
					obj[6]==null?"":String.valueOf(obj[6]),
					obj[7]==null?"":String.valueOf(obj[7]),
					obj[8]==null?"":WorkState.getNameByCode(String.valueOf(obj[8])),	
					obj[9]==null?"":String.valueOf(obj[9]),
					obj[10]==null?"":String.valueOf(obj[10]),
					String.valueOf(i+1)
					);
			result.add(statisticsTaskInfoForm);
		}
		return result;
	}
	
	@Override
	public List<StatisticsDocForm> statisticsDocByOrgList(String areaid,String tasktypeid, String rwly, String jjcd, String starttime, String endtime) {
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("	select g.id_, ");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("	nvl(z.yyfs,0),nvl(z.eyfs,0),nvl(z.myfs,0),nvl(z.syfs,0),nvl(z.wyfs,0),nvl(z.lyfs,0),nvl(z.qyfs,0),nvl(z.byfs,0),nvl(z.jyfs,0),nvl(z.oyfs,0),nvl(z.ayfs,0),nvl(z.dyfs,0),nvl(z.ynfs,0) from t_sys_org g ");
		sql.append("	 left join ( ");
		sql.append("	select o.orgid_,  ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-01-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as yyfs, ");
		//是否是闰月
		int year = 0;
		try {
			year = Integer.parseInt(starttime);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		if((year % 4 != 0 ) || (year % 100 == 0 ) && (year % 400 != 0)){
			sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-02-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-02-28 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as eyfs, ");
		}else{
			sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-02-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-02-29 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as eyfs, ");
		}
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-03-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-03-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as myfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-04-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-04-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as syfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-05-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-05-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as wyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-06-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-06-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as lyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-07-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-07-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as qyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-08-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-08-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as byfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-09-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-09-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as jyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-10-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-10-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as oyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-11-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-11-30 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as ayfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-12-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-12-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as dyfs, ");
		sql.append("	sum(case when m.archives_time_ between TO_DATE('").append(starttime).append("-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and TO_DATE('").append(starttime).append("-12-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') then 1 else 0 end) as ynfs ");
		sql.append("	 from t_data_file w ");
		sql.append("	left join t_sys_user u ");
		sql.append("	on u.id_ = w.createby_ ");
		sql.append("	left join work_ m ");
		sql.append("	on m.id_ = w.pid_ ");
		sql.append("	left join t_sys_userorg o ");
		sql.append("	on u.id_ = o.userid_ ");
		sql.append("	where w.isactive_ = 'Y' ");
		sql.append("	and u.areaid_ = :areaid ");
		if(StringUtils.isNotBlank(starttime)){
			sql.append(" and m.archives_time_ between TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss') and TO_DATE(:starttime1,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime+"-01-01 00:00:00");
			condition.put("starttime1", starttime+"-12-31 23:59:59");
		}
		sql.append("	and w.type_ in ('")
		.append(FileTypeEnums.RCJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.RCJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.NDHCJCJL.getCode()).append("','")
		.append(FileTypeEnums.NDHCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.HDCJCJL.getCode()).append("','")
		.append(FileTypeEnums.HDCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.XFTSJCJL.getCode()).append("','")
		.append(FileTypeEnums.XFTSMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.PWXKZJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.PWXKZJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZXXDJCBG.getCode()).append("','")
		.append(FileTypeEnums.ZXXDZRWMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZXXDZRWJCJL.getCode()).append("','")
		.append(FileTypeEnums.WFAJDCKCBL.getCode()).append("','")
		.append(FileTypeEnums.WFAJDCXWBL.getCode()).append("','")
		.append(FileTypeEnums.XQZLJCJL.getCode()).append("','")
		.append(FileTypeEnums.XQZLMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.GZJCJCJL.getCode()).append("','")
		.append(FileTypeEnums.GZJCMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.ZDJKJCJL.getCode()).append("','")
		.append(FileTypeEnums.WXFWJCJL.getCode()).append("','")
		.append(FileTypeEnums.WXFWMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.WXHXPJCJL.getCode()).append("','")
		.append(FileTypeEnums.FSAQJCJL.getCode()).append("','")
		.append(FileTypeEnums.FSAQMOREJCBL.getCode()).append("','")
		.append(FileTypeEnums.WRSGXCDCJCJL.getCode()).append("','")
		.append(FileTypeEnums.WRSGXCDCMOREJCBL.getCode()).append("')");
		sql.append(" group by o.orgid_ ) z ");
		sql.append("	on g.id_ = z.orgid_ ");
		sql.append("	 left join t_sys_area a on g.areaid_ = a.id_  ");
		sql.append(" where g.isactive_ = 'Y' ");
		sql.append("	and g.areaid_ = :areaid ");
		sql.append("	 order by g.orderby_ ");
		condition.put("areaid", areaid);
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsDocForm> listResult = new ArrayList<StatisticsDocForm>();
		StatisticsDocForm sumForm = new StatisticsDocForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计值");
		for(Object[] obj : list){
			StatisticsDocForm statisticsForm = new StatisticsDocForm(String.valueOf(obj[0]),String.valueOf(obj[1]),
					((BigDecimal)obj[2]).intValue(),((BigDecimal)obj[3]).intValue(),
					((BigDecimal)obj[4]).intValue(),((BigDecimal)obj[5]).intValue(),
					((BigDecimal)obj[6]).intValue(),((BigDecimal)obj[7]).intValue(),((BigDecimal)obj[8]).intValue(),((BigDecimal)obj[9]).intValue(),((BigDecimal)obj[10]).intValue(),((BigDecimal)obj[11]).intValue(), ((BigDecimal)obj[12]).intValue(), ((BigDecimal)obj[13]).intValue(), ((BigDecimal)obj[14]).intValue());
			listResult.add(statisticsForm);
			sumForm.setYyfs(sumForm.getYyfs()+statisticsForm.getYyfs());
			sumForm.setEyfs(sumForm.getEyfs()+statisticsForm.getEyfs());
			sumForm.setMyfs(sumForm.getMyfs()+statisticsForm.getMyfs());
			sumForm.setSyfs(sumForm.getSyfs()+statisticsForm.getSyfs());
			sumForm.setWyfs(sumForm.getWyfs()+statisticsForm.getWyfs());
			sumForm.setLyfs(sumForm.getLyfs()+statisticsForm.getLyfs());
			sumForm.setQyfs(sumForm.getQyfs()+statisticsForm.getQyfs());
			sumForm.setByfs(sumForm.getByfs()+statisticsForm.getByfs());
			sumForm.setJyfs(sumForm.getJyfs()+statisticsForm.getJyfs());
			sumForm.setOyfs(sumForm.getOyfs()+statisticsForm.getOyfs());
			sumForm.setAyfs(sumForm.getAyfs()+statisticsForm.getAyfs());
			sumForm.setDyfs(sumForm.getDyfs()+statisticsForm.getDyfs());
			sumForm.setYnfs(sumForm.getYnfs()+statisticsForm.getYnfs());
		}
		listResult.add(sumForm);
		return listResult;
	}
	
	@Override
	public void exportStatisticalDocInfoList(String areaid,String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		if(StringUtils.isNotBlank(areaname)){
			conditionsForm.setAreaname(areaname);
		}else{
			conditionsForm.setAreaname("所有区域");
		}
		if (StringUtils.isNotBlank(starttime)) {
			conditionsForm.setDatetime(starttime);
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);

		//列表数据
		List list = this.statisticsDocByOrgList(areaid,tasktypeid, rwly, jjcd, starttime, endtime);
		map.put("statisticsDocForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByOrg.xls"), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByOrg.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "按部门统计监察笔录份数", list.size() + 15, list.size() + 15, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "按部门统计监察笔录份数", 5, 0, 5 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按部门统计监察笔录份数.xls".getBytes("GB2312"), "ISO-8859-1"));
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
}
