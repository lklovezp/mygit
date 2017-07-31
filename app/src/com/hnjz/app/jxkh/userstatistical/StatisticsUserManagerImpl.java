package com.hnjz.app.jxkh.userstatistical;

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
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.jxkh.orgstatistics.ConditionsForm;
import com.hnjz.app.jxkh.orgstatistics.StageAnalysis;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.app.jxkh.orgstatistics.StatisticsForm;
import com.hnjz.app.jxkh.orgstatistics.StatisticsTaskInfoForm;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.org.OrgType;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按人员统计managerImpl
 *
 */
@Service("statisticsUserManager")
public class StatisticsUserManagerImpl extends ManagerImpl implements StatisticsUserManager, ServletContextAware {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
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
		.append(" or u.type_ = '").append(TaskUserEnum.XPR.getCode()).append("' ) ");
		//未确定主办人 的任务统计当前待办人
		publicSql.append("	union ");
		publicSql.append("	select distinct w.id_,u.id_ from work_ w left join v_dbwork v on w.id_ = v.id_ left join t_sys_user u on v.username_ = u.username_ ");
		publicSql.append("	where (w.state_ is null or w.state_ in('")
		.append(WorkState.YPF.getCode()).append("','")
		.append(WorkState.YZP.getCode()).append("','")
		.append(WorkState.YXP.getCode()).append("') ) ");
		publicSql.append("	) ");
		return publicSql.toString();
	}

	@Override
	public List<StatisticsForm> statisticsUserList(String areaid, String tasktypeid, String rwly, String username, String orgids, String jjcd, String starttime, String endtime) {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append(" select g.id_ orgid,");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("  s.id_ userid,s.name_ username,");
		sql.append("  nvl(z.xd,0),nvl(z.wc,0),nvl(z.yqwc,0),nvl(z.zzbl,0),nvl(z.yqbl,0) ");
		sql.append("   from t_sys_user s  ");
		sql.append("  left join (      ");
		sql.append("  select w.userid, ");
		sql.append(" count(w.id_) as xd, ");
		sql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as wc, ");
		sql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' and w.ARCHIVES_TIME_ > w.END_TIME_ then 1 else 0 end) as yqwc, ");
		sql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as zzbl, ");
		sql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' and w.END_TIME_ < sysdate then 1 else 0 end) as yqbl ");
		sql.append(" from ( ");
		
		//非专项任务
		sql.append(" select u.userid,w.id_,w.state_,w.end_time_,w.archives_time_ from user_ u ");
		sql.append(" left join work_ w on u.rwid = w.id_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		if (StringUtils.isNotBlank(rwly)) {
			sql.append(" and w.source_ = :rwly");
			condition.put("rwly", rwly);
		}
		if (StringUtils.isNotBlank(jjcd)) {
			sql.append(" and w.emergency_ = :jjcd");
			condition.put("jjcd", jjcd);
		}
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("starttime", starttime + " 00:00:00");
		}
		if (StringUtils.isNotBlank(endtime)) {
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			condition.put("endtime", endtime + " 23:59:59");
		}
		if (StringUtils.isNotBlank(tasktypeid)) {
			sql.append(" and t.tasktypeid_ = :tasktypeid");
			condition.put("tasktypeid", tasktypeid);
		}
		sql.append("	and w.areaid_ = :areaid ");
		//专项任务
		sql.append(" union all ");
		sql.append(" select u.userid,w.id_,w.state_,w.end_time_,w.archives_time_ from user_ u ");
		sql.append(" left join work_ w on u.rwid = w.id_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and t.tasktypeid_ ='15' ");
		if (StringUtils.isNotBlank(rwly)) {
			sql.append(" and w.source_ = :rwly");
		}
		if (StringUtils.isNotBlank(jjcd)) {
			sql.append(" and w.emergency_ = :jjcd");
		}
		if (StringUtils.isNotBlank(starttime)) {
			sql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(endtime)) {
			sql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(tasktypeid)) {
			sql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		sql.append("	and w.areaid_ = :areaid ");
		sql.append(" ) w group by w.userid ");
		sql.append(" ) z on s.id_ = z.userid ");
		sql.append(" left join t_sys_userorg o on s.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" left join t_sys_area a on g.areaid_ = a.id_  ");
		sql.append(" where s.isactive_ = 'Y' and s.issys_ !='Y' and g.isactive_ = 'Y' ");
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and s.name_ like :username");
			condition.put("username", "%" + username + "%");
		}
		if (StringUtils.isNotBlank(orgids)) {
			String[] arg = orgids.split(",");
			String orgid = "";
			for (int i = 0; i < arg.length; i++) {
				orgid += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgid += ",";
				}
			}
			sql.append(" and g.id_ in (").append(orgid).append(")");
		}
		sql.append("	and g.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		sql.append(" order by g.orderby_,s.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsForm> listResult = new ArrayList<StatisticsForm>();
		StatisticsForm sumForm = new StatisticsForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计");
		sumForm.setUserid("sum");
		sumForm.setUsername("任务之和");
		for (Object[] obj : list) {
			StatisticsForm statisticsForm = new StatisticsForm(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), ((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(), ((BigDecimal) obj[6]).intValue(), ((BigDecimal) obj[7]).intValue(), ((BigDecimal) obj[8]).intValue());
			listResult.add(statisticsForm);
			sumForm.setXd(sumForm.getXd() + statisticsForm.getXd());
			sumForm.setWc(sumForm.getWc() + statisticsForm.getWc());
			sumForm.setYqwc(sumForm.getYqwc() + statisticsForm.getYqwc());
			sumForm.setZzbl(sumForm.getZzbl() + statisticsForm.getZzbl());
			sumForm.setYqzzbl(sumForm.getYqzzbl() + statisticsForm.getYqzzbl());
		}
		listResult.add(sumForm);

		StringBuffer realSql = new StringBuffer(publicSqlHelp());
		realSql.append("select sum(1) as xd, ");
		realSql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as wc, ");
		realSql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' and w.ARCHIVES_TIME_ > w.END_TIME_ then 1 else 0 end) as yqwc, ");
		realSql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as zzbl, ");
		realSql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' and w.END_TIME_ < sysdate then 1 else 0 end) as yqbl ");
		realSql.append(" from ( ");
		//非专项的
		realSql.append("  select  w.id_, w.state_, w.archives_time_, w.end_time_ from  work_ w ");
		realSql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		realSql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		realSql.append(" where w.isactive_ = 'Y' ");
		realSql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		if (StringUtils.isNotBlank(rwly)) {
			realSql.append(" and w.source_ = :rwly");
		}
		if (StringUtils.isNotBlank(jjcd)) {
			realSql.append(" and w.emergency_ = :jjcd");
		}
		if (StringUtils.isNotBlank(starttime)) {
			realSql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(endtime)) {
			realSql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(tasktypeid)) {
			realSql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		realSql.append("	and w.areaid_ = :areaid ");
		condition.put("areaid", areaid);
		realSql.append(" and w.id_ in (select distinct rwid from user_ u left join t_sys_user r on u.userid = r.id_  ");
		realSql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		realSql.append(" left join t_sys_org g on o.orgid_ = g.id_ where r.isactive_ = 'Y' and r.issys_ !='Y' and g.isactive_ = 'Y' ");
		if (StringUtils.isNotBlank(username)) {
			realSql.append(" and r.name_ like :username");
		}
		if (StringUtils.isNotBlank(orgids)) {
			String[] arg = orgids.split(",");
			String orgid = "";
			for (int i = 0; i < arg.length; i++) {
				orgid += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgid += ",";
				}
			}
			realSql.append(" and g.id_ in (").append(orgid).append(")");
		}
		realSql.append(" ) ");
		
		realSql.append(" union all ");
		//专项的
		realSql.append("  select  w.id_, w.state_, w.archives_time_, w.end_time_ from  work_ w ");
		realSql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		realSql.append(" where w.isactive_ = 'Y' ");
		realSql.append(" and t.tasktypeid_ ='15' ");
		if (StringUtils.isNotBlank(rwly)) {
			realSql.append(" and w.source_ = :rwly");
		}
		if (StringUtils.isNotBlank(jjcd)) {
			realSql.append(" and w.emergency_ = :jjcd");
		}
		if (StringUtils.isNotBlank(starttime)) {
			realSql.append(" and w.end_time_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(endtime)) {
			realSql.append(" and w.end_time_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
		}
		if (StringUtils.isNotBlank(tasktypeid)) {
			realSql.append(" and t.tasktypeid_ = :tasktypeid");
		}
		realSql.append("	and w.areaid_ = :areaid ");
		realSql.append(" and w.id_ in (select distinct rwid from user_ u left join t_sys_user r on u.userid = r.id_  ");
		realSql.append(" left join t_sys_userorg o on r.id_ = o.userid_ ");
		realSql.append(" left join t_sys_org g on o.orgid_ = g.id_ where r.isactive_ = 'Y' and r.issys_ !='Y' and g.isactive_ = 'Y' ");
		if (StringUtils.isNotBlank(username)) {
			realSql.append(" and r.name_ like :username");
		}
		if (StringUtils.isNotBlank(orgids)) {
			String[] arg = orgids.split(",");
			String orgid = "";
			for (int i = 0; i < arg.length; i++) {
				orgid += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgid += ",";
				}
			}
			realSql.append(" and g.id_ in (").append(orgid).append(")");
		}
		realSql.append(" ) ");
		
		realSql.append(" ) w ");
		list = this.dao.findBySql(realSql.toString(), condition);
		StatisticsForm realForm = new StatisticsForm();
		realForm.setOrgid("real");
		realForm.setOrgname("实际任务总数");
		realForm.setUserid("real");
		realForm.setUsername("实际任务总数");
		if (list.size() > 0) {
			realForm.setXd(list.get(0)[0] == null ? 0 : ((BigDecimal) list.get(0)[0]).intValue());
			realForm.setWc(list.get(0)[1] == null ? 0 : ((BigDecimal) list.get(0)[1]).intValue());
			realForm.setYqwc(list.get(0)[2] == null ? 0 : ((BigDecimal) list.get(0)[2]).intValue());
			realForm.setZzbl(list.get(0)[3] == null ? 0 : ((BigDecimal) list.get(0)[3]).intValue());
			realForm.setYqzzbl(list.get(0)[4] == null ? 0 : ((BigDecimal) list.get(0)[4]).intValue());
		}
		listResult.add(realForm);
		return listResult;
	}
	
	//统计
	@Override
	public StatisticsForm statisticsRwCount() {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append(" select g.id_ orgid,");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("  s.id_ userid,s.name_ username,");
		sql.append("  nvl(z.xd,0),nvl(z.wc,0),nvl(z.yqwc,0),nvl(z.zzbl,0),nvl(z.yqbl,0) ");
		sql.append("   from t_sys_user s  ");
		sql.append("  left join (      ");
		sql.append("  select w.userid, ");
		sql.append(" count(w.id_) as xd, ");
		sql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as wc, ");
		sql.append(" sum(case when w.state_ = '").append(WorkState.YGD.getCode()).append("' and w.ARCHIVES_TIME_ > w.END_TIME_ then 1 else 0 end) as yqwc, ");
		sql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' then 1 else 0 end) as zzbl, ");
		sql.append(" sum(case when w.state_ != '").append(WorkState.YGD.getCode()).append("' and w.END_TIME_ < sysdate then 1 else 0 end) as yqbl ");
		sql.append(" from ( ");
		
		//非专项任务
		sql.append(" select u.userid,w.id_,w.state_,w.end_time_,w.archives_time_ from user_ u ");
		sql.append(" left join work_ w on u.rwid = w.id_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15') ");
		sql.append("	and w.areaid_ = :areaid ");
		//专项任务
		sql.append(" union all ");
		sql.append(" select u.userid,w.id_,w.state_,w.end_time_,w.archives_time_ from user_ u ");
		sql.append(" left join work_ w on u.rwid = w.id_ ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" where w.isactive_ = 'Y' ");
		sql.append(" and t.tasktypeid_ ='15' ");
		sql.append("	and w.areaid_ = :areaid ");
		sql.append(" ) w group by w.userid ");
		sql.append(" ) z on s.id_ = z.userid ");
		sql.append(" left join t_sys_userorg o on s.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" left join t_sys_area a on g.areaid_ = a.id_  ");
		sql.append(" where s.isactive_ = 'Y' and s.issys_ !='Y' and g.isactive_ = 'Y' ");
		sql.append("	and g.areaid_ = :areaid ");
		sql.append("	and s.id_ = :userid ");
		condition.put("areaid", CtxUtil.getAreaId());
		condition.put("userid", CtxUtil.getUserId());
		sql.append(" order by g.orderby_,s.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		if(list!=null && list.size()>0) {
			Object obj[] = list.get(0);
			StatisticsForm statisticsForm = new StatisticsForm(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), ((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(), ((BigDecimal) obj[6]).intValue(), ((BigDecimal) obj[7]).intValue(), ((BigDecimal) obj[8]).intValue());
			return statisticsForm;
		}
		return null;
	}

	@Override
	public void exportStatisticsUserList(String title, String areaid,String areaname,String tasktypeid, String tasktypename, String rwly, String rwlyname, String username, String orgids, String orgnames, String jjcd,
			String jjcdname, String starttime, String endtime, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		//区域
		if (StringUtils.isNotBlank(areaname)) {
			conditionsForm.setAreaname(areaname);
		} else {
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
		if (StringUtils.isNotBlank(orgnames)) {
			conditionsForm.setOrgname(orgnames);
		} else {
			conditionsForm.setOrgname("全部");
		}
		if (StringUtils.isNotBlank(username)) {
			conditionsForm.setUsername(username);
		} else {
			conditionsForm.setUsername("全部");
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
		List list = this.statisticsUserList(areaid,tasktypeid, rwly, username, orgids, jjcd, starttime, endtime);
		map.put("statisticsForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), new File(realPath
					+ "excel/jxkh/jxkh_statisticalByUser.xls"), new File(realPath + "excel/jxkh/jxkh_statisticalByUser.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "按人员统计", list.size() + 7, list.size() + 7, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "按人员统计", 5, 0, 5 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按人员统计.xls".getBytes("GB2312"), "ISO-8859-1"));
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
				if (os != null)
					os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public FyWebResult queryStatisticalUserInfo(String userid,String areaid, String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime,String page,String pageSize){
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append(" select * from (");
		//非专项的
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,l.lawobjname_,r.name_ regionname,u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj ,w.SOURCE_ from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			sql.append(" and u.id_ = :userid");
			condition.put("userid", userid);
		}
		if (StringUtils.isNotBlank(orgid)) {
			String[] arg = orgid.split(",");
			String ids = "";
			for (int i = 0; i < arg.length; i++) {
				ids += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					ids += ",";
				}
			}
			sql.append(" and g.id_ in (").append(ids).append(")");
		}
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region r on l.regionid_ = r.id_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15')  ");
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
		
		sql.append(" union all ");
		//专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname, ");
		sql.append(" u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj,w.SOURCE_ from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			sql.append(" and u.id_ = :userid");
		}
		if (StringUtils.isNotBlank(orgid)) {
			String[] arg = orgid.split(",");
			String ids = "";
			for (int i = 0; i < arg.length; i++) {
				ids += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					ids += ",";
				}
			}
			sql.append(" and g.id_ in (").append(ids).append(")");
		}
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and t.tasktypeid_ ='15'  ");
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
		
		sql.append(" ) w ");
		sql.append(" order by w.end_time_ desc ");
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
	public FyWebResult queryRwInfo(String type,String tasktype,String page,String pageSize){
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append(" select * from (");
		//非专项的
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,l.lawobjname_,r.name_ regionname,u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj ,w.SOURCE_ from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		sql.append(" and u.id_ = :userid");
		condition.put("userid", CtxUtil.getUserId());
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region r on l.regionid_ = r.id_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15')  ");
		if(StringUtil.isNotBlank(tasktype)){
			sql.append(" and t.tasktypeid_ = :tasktype ");
			condition.put("tasktype", tasktype);
		}
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		sql.append(" union all ");
		//专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname, ");
		sql.append(" u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj,w.SOURCE_ from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		sql.append(" and u.id_ = :userid");
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and t.tasktypeid_ ='15'  ");
		switch (Integer.parseInt(type)) {
			case 2:
				sql.append(" and w.state_ = :state ");
				condition.put("state", WorkState.YGD.getCode());
				break;
			default:
				break;
		}
		
		sql.append(" ) w ");
		sql.append(" order by w.end_time_ desc ");
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
	public void exportStatisticalUserInfoList(String userid,String username,String areaid, String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		String title = "按人员统计-";
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
		conditionsForm.setUsername(username);
		if (StringUtils.isNotBlank(areaname)) {
			conditionsForm.setAreaname(areaname);
		} else {
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
		List list = this.queryStatisticalUserInfoList(userid, areaid, orgid, type, tasktypeid, rwly, jjcd, starttime, endtime);
		map.put("statisticsTaskInfoForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_userStatisticalInfo.xls"), 
					new File(realPath + "excel/jxkh/jxkh_userStatisticalInfo.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "任务完成情况", list.size() + 7, list.size() + 7, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "任务完成情况", 4, 0, 4 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String((title+".xls").getBytes("GB2312"), "ISO-8859-1"));
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
	private List<StatisticsTaskInfoForm> queryStatisticalUserInfoList(String userid,String areaid, String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		List<StatisticsTaskInfoForm> result = new ArrayList<StatisticsTaskInfoForm>();
		Map<String, Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer(publicSqlHelp());
		sql.append(" select * from (");
		//非专项的
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename,l.lawobjname_,r.name_ regionname,u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			sql.append(" and u.id_ = :userid");
			condition.put("userid", userid);
		}
		if (StringUtils.isNotBlank(orgid)) {
			String[] arg = orgid.split(",");
			String ids = "";
			for (int i = 0; i < arg.length; i++) {
				ids += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					ids += ",";
				}
			}
			sql.append(" and g.id_ in (").append(ids).append(")");
		}
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskandlawobj l on w.id_ = l.taskid_ ");
		sql.append(" left join t_data_region r on l.regionid_ = r.id_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and (t.tasktypeid_ is null or t.tasktypeid_ !='15')  ");
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
		
		sql.append(" union all ");
		//专项的任务
		sql.append(" select w.id_,w.work_name_,p.name_ tasktypename, ");
		sql.append("  (select LISTAGG(l.lawobjname_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_biz_taskandlawobj l where l.taskid_ =w.id_) lawobjname, ");
		sql.append("  (select LISTAGG(r.name_, '，') WITHIN GROUP(ORDER BY ROWNUM)  from t_data_region r where r.id_ in (select l.regionid_ from t_biz_taskandlawobj l where l.taskid_ = w.id_)) regionname, ");
		sql.append(" u.username_ pfr,w.end_time_,w.ZXR_NAME_,w.state_,w.next_oper_,to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd') gdsj from work_ w  ");
		sql.append(" left join (");
		sql.append(" select distinct rwid from user_ r ");
		sql.append(" left join t_sys_user u on r.userid = u.id_ ");
		sql.append(" left join t_sys_userorg o on u.id_ = o.userid_ ");
		sql.append(" left join t_sys_org g on o.orgid_ = g.id_ ");
		sql.append(" where u.isactive_ = 'Y' and u.issys_ !='Y' and g.isactive_ = 'Y' ");
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			sql.append(" and u.id_ = :userid");
		}
		if (StringUtils.isNotBlank(orgid)) {
			String[] arg = orgid.split(",");
			String ids = "";
			for (int i = 0; i < arg.length; i++) {
				ids += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					ids += ",";
				}
			}
			sql.append(" and g.id_ in (").append(ids).append(")");
		}
		sql.append(" ) s on w.id_ = s.rwid ");
		sql.append(" left join t_biz_taskandtasktype t on t.ISACTIVE_ = 'Y' and w.id_ = t.taskid_ ");
		sql.append(" left join t_data_tasktype p on t.tasktypeid_ = p.code_ ");
		sql.append(" left join t_biz_taskuser u on w.id_ = u.taskid_ and u.type_ = '").append(TaskUserEnum.PFR.getCode()).append("' ");
		sql.append(" where s.rwid is not null and w.isactive_ = 'Y' ");
		sql.append(" and t.tasktypeid_ ='15'  ");
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
		if(StringUtils.isNotBlank(areaid)){
			condition.put("areaid", areaid);
		}
		sql.append(" ) w ");
		sql.append(" order by w.end_time_ desc ");
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
	public List<StatisticsDocForm> statisticsDocByUserList(String areaid, String tasktypeid, String rwly, String username, String orgids, String jjcd, String starttime, String endtime) {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select g.id_ orgid,");
		sql.append("	case when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 0 then '总队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 1 then '支队领导' ");
		sql.append("	when g.type_ = '").append(OrgType.ZD.getCode()).append("' and a.type_ = 2 then '大队领导' ");
		sql.append("	else g.name_ end orgname,  ");
		sql.append("  s.id_ userid,s.name_ username,");
		sql.append("  nvl(z.yyfs,0),nvl(z.eyfs,0),nvl(z.myfs,0),nvl(z.syfs,0),nvl(z.wyfs,0),nvl(z.lyfs,0),nvl(z.qyfs,0),nvl(z.byfs,0),nvl(z.jyfs,0),nvl(z.oyfs,0),nvl(z.ayfs,0),nvl(z.dyfs,0),nvl(z.ynfs,0) ");
		sql.append("   from t_sys_user s  ");
		sql.append("  left join (      ");
		sql.append("  select u.id_, ");
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
		sql.append(" group by u.id_ ) z ");
		sql.append("	on s.id_ = z.id_ ");
		sql.append("	 left join t_sys_userorg o on s.id_ = o.userid_  left join t_sys_org g on o.orgid_ = g.id_  left join t_sys_area a on g.areaid_ = a.id_ ");
		sql.append(" where  s.isactive_ = 'Y' and s.issys_ !='Y' and g.isactive_ = 'Y' and g.areaid_ = :areaid ");
		if (StringUtils.isNotBlank(orgids)) {
			String[] arg = orgids.split(",");
			String orgid = "";
			for (int i = 0; i < arg.length; i++) {
				orgid += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					orgid += ",";
				}
			}
			sql.append(" and g.id_ in (").append(orgid).append(")");
		}
		condition.put("areaid", areaid);
		sql.append(" order by g.orderby_,s.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsDocForm> listResult = new ArrayList<StatisticsDocForm>();
		StatisticsDocForm sumForm = new StatisticsDocForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计");
		for (Object[] obj : list) {
			StatisticsDocForm statisticsForm = new StatisticsDocForm(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), ((BigDecimal) obj[4]).intValue(),
					((BigDecimal) obj[5]).intValue(), ((BigDecimal) obj[6]).intValue(), ((BigDecimal) obj[7]).intValue(), ((BigDecimal) obj[8]).intValue(), ((BigDecimal) obj[9]).intValue(), ((BigDecimal) obj[10]).intValue(), ((BigDecimal) obj[11]).intValue(), ((BigDecimal) obj[12]).intValue(), ((BigDecimal) obj[13]).intValue(), ((BigDecimal) obj[14]).intValue(), ((BigDecimal) obj[15]).intValue(), ((BigDecimal) obj[16]).intValue());
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
	public void exportStatisticsDocByUserList(String title, String areaid,String areaname,String tasktypeid, String tasktypename, String rwly, String rwlyname, String username, String orgids, String orgnames, String jjcd,
			String jjcdname, String starttime, String endtime, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		//区域
		if (StringUtils.isNotBlank(areaname)) {
			conditionsForm.setAreaname(areaname);
		} else {
			conditionsForm.setAreaname("所有区域");
		}
		if (StringUtils.isNotBlank(orgnames)) {
			conditionsForm.setOrgname(orgnames);
		} else {
			conditionsForm.setOrgname("全部");
		}
		if (StringUtils.isNotBlank(starttime)) {
			conditionsForm.setDatetime(starttime);
		} 
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);

		//列表数据
		List list = this.statisticsDocByUserList(areaid,tasktypeid, rwly, username, orgids, jjcd, starttime, endtime);
		map.put("statisticsDocForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByUser.xls"), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByUesr.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "按人员统计监察笔录份数", list.size() + 15, list.size() + 15, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "按人员统计监察笔录份数", 5, 0, 5 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按人员统计监察笔录份数.xls".getBytes("GB2312"), "ISO-8859-1"));
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
				if (os != null)
					os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
