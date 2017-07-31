package com.hnjz.app.jxkh.areastatistics;

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
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.excel.ExcelUtil;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-08-31
 * 功能描述：按区域统计managerImpl
 *
 */
@Service("statisticsAreaManager")
public class StatisticsAreaManagerImpl extends ManagerImpl implements StatisticsAreaManager, ServletContextAware {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	public List<StatisticsDocForm> statisticsDocByAreaList(String areaid, String tasktypeid, String rwly, String username, String orgids, String jjcd, String starttime, String endtime) {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select g.id_ orgid,g.name_ orgname,");
		sql.append("  nvl(z.yyfs,0),nvl(z.eyfs,0),nvl(z.myfs,0),nvl(z.syfs,0),nvl(z.wyfs,0),nvl(z.lyfs,0),nvl(z.qyfs,0),nvl(z.byfs,0),nvl(z.jyfs,0),nvl(z.oyfs,0),nvl(z.ayfs,0),nvl(z.dyfs,0),nvl(z.ynfs,0) ");
		sql.append("   from t_sys_area g");
		sql.append("  left join (      ");
		sql.append("  select u.areaid_, ");
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
		sql.append("	left join t_sys_area a ");
		sql.append("	on a.id_ = u.areaid_ ");
		sql.append("	where w.isactive_ = 'Y' ");
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
		sql.append(" group by u.areaid_ ) z ");
		sql.append("	on g.id_ = z.areaid_ ");
		sql.append(" where g.pid_ is null or g.pid_ = 'a0000000000000000000000000000000' and g.isactive_ = 'Y' order by g.orderby_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
		List<StatisticsDocForm> listResult = new ArrayList<StatisticsDocForm>();
		StatisticsDocForm sumForm = new StatisticsDocForm();
		sumForm.setOrgid("sum");
		sumForm.setOrgname("合计");
		for (Object[] obj : list) {
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
	public void exportStatisticsDocByAreaList(String title, String areaid,String areaname,String tasktypeid, String tasktypename, String rwly, String rwlyname, String username, String orgids, String orgnames, String jjcd,
			String jjcdname, String starttime, String endtime, HttpServletResponse res) {
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		if (StringUtils.isNotBlank(starttime)) {
			conditionsForm.setDatetime(starttime);
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);

		//列表数据
		List list = this.statisticsDocByAreaList(areaid,tasktypeid, rwly, username, orgids, jjcd, starttime, endtime);
		map.put("statisticsDocForm", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = ExcelUtil.setValue(new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByArea.xls"), 
					new File(realPath + "excel/jxkh/jxkh_statisticalDocByArea.xml"), map, false);
			ExcelUtil.addMergedRegion(file, "按区域统计监察笔录份数", list.size() + 15, list.size() + 15, 0, 1);//合并“合计”单元格
			ExcelUtil.copyStyle(file, "按区域统计监察笔录份数", 5, 0, 5 + list.size() - 1, 0 + 31);
			is = new FileInputStream(file);
			String de = "attachment;filename=".concat(new String("按区域统计监察笔录份数.xls".getBytes("GB2312"), "ISO-8859-1"));
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
