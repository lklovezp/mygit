package com.hnjz.app.work.manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.app.work.beans.Page;
import com.hnjz.app.work.beans.ProvinceTaskAllCountBean;
import com.hnjz.app.work.beans.ProvinceTaskArrayBean;
import com.hnjz.app.work.beans.ProvinceTaskWorkTypeCountBean;
import com.hnjz.app.work.beans.VTjWorkListBean;
import com.hnjz.app.work.dao.VTjWryWorkDao;
import com.hnjz.app.work.po.VTjWorkList;
import com.hnjz.common.upload.FileUpDownUtil;

/**
 * 任务统计
 * 
 * @author zn
 * @version $Id: WorkStatisticsManager.java, v 0.1 2013-4-9 上午08:45:42 zn Exp $
 */
@Service(value = "provinceTaskManager")
public class ProvinceTaskManager implements ServletContextAware {
    @Autowired
    private VTjWryWorkDao  vtjWryWorkDao;
    
    private ServletContext sc;

    /*
     * 任务统计报表中总数统计
     */
    public List<ProvinceTaskAllCountBean> findWorkListCount(String startTime, String endTime,
                                                            String workTypeIds, String wflxName,
                                                            String typeStr) {
        
        String idStr = "(";
        String[] ids = workTypeIds.split(",");
        for (int i = 0; i < ids.length; i++) {
            idStr += "'" + ids[i] + "',";
        }
        idStr = idStr.substring(0, idStr.length() - 1) + ")";
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select t.areaid_ as areaid,sys_area.area_name ,");
        sqlBuffer.append(" sum(case  when t.work_type_ in " + idStr
                         + " then 1 else 0 end) as allcount,");
        sqlBuffer.append(" sum(case  when t.is_over_ = 1 and t.work_type_ in " + idStr
                         + " then 1 else 0 end) as over_count,");
        sqlBuffer.append(" sum(case when t.is_over_ = 1 and t.work_type_ in "
                    + idStr
                    + " and t.archives_time_ > t.end_time_ then 1 else 0 end) as outdate_over_count,");
        sqlBuffer.append(" sum(case when t.is_over_ = 0 and t.work_type_ in " + idStr
                         + " then 1 else 0 end) as not_over_count,");
        sqlBuffer.append(" sum(case  when t.is_over_ = 0 and t.work_type_ in " + idStr
                    + " and sysdate > t.end_time_ then 1 else 0 end) as outdate_not_over_count ");
        sqlBuffer.append(" from work_ t left join sys_area on sys_area.id = t.areaid_ where  (sys_area.type_ = '"
                    + typeStr + "')");
        if (!startTime.isEmpty()) {
        	startTime += " 00:00:00";
            sqlBuffer.append(" and t.end_time_ >= to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')");
        }
        if (!endTime.isEmpty()) {
        	endTime +=  " 23:59:59";
            sqlBuffer.append(" and t.end_time_ <= to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')");
        }
        sqlBuffer.append(" group by t.areaid_ ,sys_area.area_name");
       // System.out.println("dsjjjj--"+sqlBuffer.toString());
        List<Object> tjList = vtjWryWorkDao.findBySQL(sqlBuffer.toString());

        List<ProvinceTaskAllCountBean> ptacBeanList = new ArrayList<ProvinceTaskAllCountBean>();

        for (int i = 0; i < tjList.size(); i++) {
            ProvinceTaskAllCountBean ptacBean = new ProvinceTaskAllCountBean();
            Object[] object = (Object[]) tjList.get(i);
            ptacBean.setAreaid(object[0].toString());
            ptacBean.setAreaName(object[1].toString());
            ptacBean.setAllCount(Integer.parseInt(object[2].toString()));
            ptacBean.setOverCount(Integer.parseInt(object[3].toString()));
            ptacBean.setOutDateOverCount(Integer.parseInt(object[4].toString()));
            ptacBean.setNotOverCount(Integer.parseInt(object[5].toString()));
            ptacBean.setOutDateNotOverCount(Integer.parseInt(object[6].toString()));
            List<ProvinceTaskWorkTypeCountBean> ptwtcBeanList = this.findWorkListCountByWorktype(
                startTime, endTime, workTypeIds, wflxName, typeStr);
            List<ProvinceTaskWorkTypeCountBean> ptwtcBeanCityList = new ArrayList<ProvinceTaskWorkTypeCountBean>();
            Iterator<ProvinceTaskWorkTypeCountBean> it = ptwtcBeanList.iterator();
            while (it.hasNext()) {
                ProvinceTaskWorkTypeCountBean ptwtcBean = (ProvinceTaskWorkTypeCountBean) it.next();
                if (ptwtcBean.getAreaid().equals(ptacBean.getAreaid())) {
                    ptwtcBeanCityList.add(ptwtcBean);
                }
            }
            ptacBean.setPtwtcBeanList(ptwtcBeanCityList);
            ptacBeanList.add(ptacBean);
        }
        return ptacBeanList;
    }

    /*
     * 按任务类型 获取任务统计报表
     */
    public List<ProvinceTaskWorkTypeCountBean> findWorkListCountByWorktype(String startTime,
                                                                           String endTime,
                                                                           String workTypeIds,
                                                                           String wflxName,
                                                                           String typeStr) {
        //endTime = DateUtil.getNextDate(endTime, 1);
        String[] workTypeId = workTypeIds.split(",");
        List<ProvinceTaskWorkTypeCountBean> ptacBeanList = new ArrayList<ProvinceTaskWorkTypeCountBean>();
        for (int i = 0; i < workTypeId.length; i++) {
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer
                .append("select t.areaid_ as areaid,sys_area.area_name as areaname,'"
                        + workTypeId[i]
                        + "',"
                        + " sum(case  when t.work_type_ = '"
                        + workTypeId[i]
                        + "' then 1 else 0 end) as allcount,"
                        + " sum(case  when t.work_type_ = '"
                        + workTypeId[i]
                        + "' and t.is_over_ = 1 then 1 else 0 end) as over_count,"
                        + " sum(case when t.work_type_ = '"
                        + workTypeId[i]
                        + "' and t.is_over_ = 1 and t.archives_time_ > t.end_time_ then 1 else 0 end) as outdate_over_count,"
                        + " sum(case when t.work_type_ = '"
                        + workTypeId[i]
                        + "' and t.is_over_ = 0 then 1 else 0 end) as not_over_count,"
                        + " sum(case  when t.work_type_ = '"
                        + workTypeId[i]
                        + "' and t.is_over_ = 0 and sysdate > t.end_time_ then 1 else 0 end) as outdate_not_over_count "
                        + " from work_ t left join sys_area on sys_area.id = t.areaid_ where (sys_area.type_ = '"
                        + typeStr + "')");
            if (!startTime.isEmpty()) {
                sqlBuffer.append(" and t.end_time_ >= to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')");
            }
            if (!endTime.isEmpty()) {
                sqlBuffer.append(" and t.end_time_ <= to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')");
            }
            
            sqlBuffer.append(" group by t.areaid_ ,sys_area.area_name");
            //System.out.println(sqlBuffer.toString());
            List<Object> tjList = vtjWryWorkDao.findBySQL(sqlBuffer.toString());

            for (int j = 0; j < tjList.size(); j++) {
                ProvinceTaskWorkTypeCountBean ptacBean = new ProvinceTaskWorkTypeCountBean();
                Object[] object = (Object[]) tjList.get(j);

                ptacBean.setAreaid(object[0].toString());
                ptacBean.setAreaName(object[1].toString());
                ptacBean.setWorkTypeId(object[2].toString());
                ptacBean.setWorkTypeAllCount(Integer.parseInt(object[3].toString()));
                ptacBean.setWorkTypeOverCount(Integer.parseInt(object[4].toString()));
                ptacBean.setWorkTypeOutDateOverCount(Integer.parseInt(object[5].toString()));
                ptacBean.setWorkTypeNotOverCount(Integer.parseInt(object[6].toString()));
                ptacBean.setWorkTypeOutDateNotOverCount(Integer.parseInt(object[7].toString()));
                ptacBeanList.add(ptacBean);
            }
        }

        return ptacBeanList;
    }

    /**
     * 查询总任务列表
     * 
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param workType
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public Page findWorkTotalList(int pageNo, int pageSize, String startTime, String endTime,
                                  String workType, String areaid, String tjType)
                                                                                throws ParseException {
        Page page = findWorkTotalListBase(pageNo, pageSize, startTime, endTime, workType, areaid,
            tjType);
        List<VTjWorkList> tlist = (List<VTjWorkList>) page.getList();
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < tlist.size(); i++) {
            list.add(tlist.get(i).toMap());
        }
        page.setList(list);
        return page;
    }

    /**
     * 查询总任务列表基础（为列表显示及导出提供数据） page中的list为对象集合
     * 
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param workType
     * @param tjType
     * @return
     * @throws ParseException
     */
    public Page findWorkTotalListBase(int pageNo, int pageSize, String startTime, String endTime,
                                      String workType, String areaid, String tjType)
                                                                                    throws ParseException {
        
        String idStr = "(";
        String[] ids = workType.split(",");
        for (int i = 0; i < ids.length; i++) {
            idStr += "'" + ids[i] + "',";
        }
        idStr = idStr.substring(0, idStr.length() - 1) + ")";

        List<VTjWorkList> list = new ArrayList<VTjWorkList>();
        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (pageNo != -1 && pageSize != -1) {
            stringBuffer.append("SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (");
        }

        stringBuffer
            .append("select t.id_,s.area_name,t.zfdx_name_ ,t.work_name_,wt.name_,t.start_time_ ,t.end_time_ ,t.zxr_name_ ,t.state_ from work_ t  inner join sys_area s on t.areaid_ = s.id ");
        String whereStr = "";
        if (!areaid.equals("") && areaid != null) {
            whereStr += " and t.areaid_='" + areaid + "'";
        }

        if (!startTime.isEmpty()) {
            startTime += " 00:00:00" ;
            whereStr += " and t.end_time_>= to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";

        }
        if (!endTime.isEmpty()) {
        	endTime += " 23:59:59" ;
            whereStr += " and t.end_time_<= to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')";
        }
        whereStr += " and (s.type_='0' or s.type_ = '1')";
        if (tjType.equals("workTypeAllCount") || tjType.equals("allCount")) {
            whereStr += " and t.work_type_ in " + idStr
                        + " left join work_type wt on t.work_type_ = wt.id_ ";
        } else if (tjType.equals("workTypeOverCount") || tjType.equals("overCount")) {
            whereStr += " and t.is_over_ = 1 and t.work_type_ in " + idStr
                        + "  left join work_type wt on t.work_type_ = wt.id_ ";
        } else if (tjType.equals("workTypeOutDateOverCount") || tjType.equals("outDateOverCount")) {
            whereStr += " and t.is_over_ = 1 and t.archives_time_ > t.end_time_ and t.work_type_ in "
                        + idStr + " left join work_type wt on t.work_type_ = wt.id_ ";
        } else if (tjType.equals("workTypeNotOverCount") || tjType.equals("notOverCount")) {
            whereStr += " and t.is_over_ = 0 and t.work_type_ in " + idStr
                        + " left join work_type wt on t.work_type_ = wt.id_ ";
        } else if (tjType.equals("workTypeOutDateNotOverCount")
                   || tjType.equals("outDateNotOverCount")) {
            whereStr += " and t.is_over_ = 0 and sysdate > t.end_time_ and t.work_type_ in "
                        + idStr + " left join work_type wt on t.work_type_ = wt.id_ ";
        }
        whereStr+=" order by t.end_time_ desc";
        stringBuffer.append(whereStr);
        if (pageNo != -1 && pageSize != -1) {
            stringBuffer.append(") A ) WHERE RN BETWEEN " + ((pageNo - 1) * pageSize + 1) + " AND "
                                + pageNo * pageSize + "");
        }
        //System.out.println(stringBuffer.toString());
        List<Object> workList = vtjWryWorkDao.findBySQL(stringBuffer.toString());
        for (int i = 0; i < workList.size(); i++) {
            Object[] object = (Object[]) workList.get(i);
            VTjWorkList vtjWorkList = new VTjWorkList();
            vtjWorkList.setWorkId(object[0] == null ? "" : object[0].toString());
            vtjWorkList.setWrySsds(object[1] == null ? "" : object[1].toString());
            vtjWorkList.setWryName(object[2] == null ? "" : object[2].toString());

            vtjWorkList.setWorkName(object[3] == null ? "" : object[3].toString());
            vtjWorkList.setWorkTypeName(object[4] == null ? "" : object[4].toString());
            vtjWorkList.setStartTime(sdf.parse(object[5].toString()));
            vtjWorkList.setEndTime(sdf.parse(object[6].toString()));
            vtjWorkList.setZxr(object[7] == null ? "" : object[7].toString());
            vtjWorkList.setState(object[8] == null ? "" : object[8].toString());
            list.add(vtjWorkList);
        }
        String fromStr = "select count(*) from work_ t  inner join sys_area s on t.areaid_ = s.id ";
        String countSQL = fromStr + whereStr;

        String objectStr = vtjWryWorkDao.findBySQL(countSQL).get(0).toString();
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setList(list);
        page.setTotal(Integer.parseInt(objectStr));
        return page;
    }

    /**
     * 全省任务统计生成EXCEL
     * 
     * @param startTime
     * @param endTime
     * @param workType
     * @param workTypeName
     * @return
     * @throws Exception
     */
    public String createQsrwtjListExcel(String startTime, String endTime, String workType,
                                        String workTypeName, String queryType) throws Exception {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> paraList = new ArrayList<Object>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("riqi", startTime.concat(" 到 ").concat(endTime));
        String[] workTypeNameArr = workTypeName.split(",");
        for (int i = 0; i < workTypeNameArr.length; i++) {
            if (i + 1 == 1) {
                paraMap.put("title1", workTypeNameArr[i]);
            } else if (i + 1 == 2) {
                paraMap.put("title2", workTypeNameArr[i]);
            } else if (i + 1 == 3) {
                paraMap.put("title3", workTypeNameArr[i]);
            } else if (i + 1 == 4) {
                paraMap.put("title4", workTypeNameArr[i]);
            } else if (i + 1 == 5) {
                paraMap.put("title5", workTypeNameArr[i]);
            }
        }
        paraList.add(paraMap);
        map.put("param", paraList);
        // 集合
        List<Object> dataList = new ArrayList<Object>();
        List<ProvinceTaskAllCountBean> list = findWorkListCount(startTime, endTime, workType,
            workTypeName, queryType);
        for (int i = 0; i < list.size(); i++) {
            dataList.add(((ProvinceTaskAllCountBean) list.get(i)).toArrayBean());
        }
        // 添加合计
        dataList.add(new ProvinceTaskArrayBean(dataList));
        map.put("list", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String dirPath = FileUpDownUtil.path.concat(File.separator).concat("excel").concat(
            File.separator).concat("work").concat(File.separator);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        
        File file = ExcelUtil.setValue(new File(dirPath.concat(sdf.format(c.getTime())).concat(
            ".xls")), new File(sc.getRealPath(File.separator) + "excel/work/qsrwtj_total.xls"),
            new File(sc.getRealPath(File.separator) + "excel/work/qsrwtj_total.xml"), map);
        ExcelUtil.copyStyle(file, "全省任务统计", 6, 0, 6+dataList.size()-1, 33);
        return file.getPath();
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        this.sc = arg0;
    }

    /**
     * 全省任务统计列表生成EXCEL
     * 
     * @param startTime
     * @param endTime
     * @param workType
     * @param workTypeName
     * @param areaid
     * @param areaName
     * @param tjType
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String createTotalListExcel(String startTime, String endTime, String workType,
                                       String workTypeName, String areaid, String areaName,
                                       String tjType) throws Exception {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> paraList = new ArrayList<Object>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        String tjTypeStr = "";
        if (tjType.equals("allCount")) {
            tjTypeStr = "下达任务";
        } else if (tjType.equals("overCount")) {
            tjTypeStr = "完成任务";
        } else if (tjType.equals("outDateOverCount")) {
            tjTypeStr = "逾期完成任务";
        } else if (tjType.equals("notOverCount")) {
            tjTypeStr = "正在执行任务";
        } else if (tjType.equals("outDateNotOverCount")) {
            tjTypeStr = "逾期未完成任务";
        }
        if (tjType.equals("workTypeAllCount")) {
            tjTypeStr = areaName + "下达任务";
        } else if (tjType.equals("workTypeOverCount")) {
            tjTypeStr = areaName + "完成任务";
        } else if (tjType.equals("workTypeOutDateOverCount")) {
            tjTypeStr = areaName + "逾期完成任务";
        } else if (tjType.equals("workTypeNotOverCount")) {
            tjTypeStr = areaName + "正在执行任务";
        } else if (tjType.equals("workTypeOutDateNotOverCount")) {
            tjTypeStr = areaName + "逾期未完成任务";
        }

        paraMap.put("title", tjTypeStr.concat("列表"));
        paraMap.put("riqi", startTime.concat(" 到 ").concat(endTime));
        paraMap.put("tjfs", tjTypeStr);
        paraMap.put("worktype", workTypeName);
        paraMap.put("hjjclx", "");
        paraMap.put("jjcd", "");
        paraList.add(paraMap);
        map.put("param", paraList);
        // 集合
        Page page = findWorkTotalListBase(-1, -1, startTime, endTime, workType, areaid, tjType);
        List<VTjWorkList> tList = (List<VTjWorkList>) page.getList();
        List<Object> vtjWorkListBeanList = new ArrayList<Object>();
        for (int i = 0; i < tList.size(); i++) {
            vtjWorkListBeanList.add(new VTjWorkListBean(i + 1, tList.get(i)));
        }
        map.put("list", vtjWorkListBeanList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String dirPath = FileUpDownUtil.path.concat(File.separator).concat("excel").concat(
            File.separator).concat("work").concat(File.separator);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = ExcelUtil.setValue(new File(dirPath.concat(sdf.format(c.getTime())).concat(
            ".xls")),
            new File(sc.getRealPath(File.separator) + "excel/work/qsrwtj_total_list.xls"),
            new File(sc.getRealPath(File.separator) + "excel/work/qsrwtj_total_list.xml"), map);
        return file.getPath();
    }

}
