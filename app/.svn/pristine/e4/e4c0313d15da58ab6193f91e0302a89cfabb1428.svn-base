package com.hnjz.app.work.manager;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.work.beans.Page;
import com.hnjz.app.work.beans.VTjWorkListBean;
import com.hnjz.app.work.beans.WorkDcStatisticsBean;
import com.hnjz.app.work.beans.WorkDcStatisticsCityBean;
import com.hnjz.app.work.beans.WorkDeptStatisticsArrayBean;
import com.hnjz.app.work.beans.WorkDeptStatisticsBean;
import com.hnjz.app.work.beans.WorkDeptTypeStatisticsBean;
import com.hnjz.app.work.beans.WorkTypeStatisticsBean;
import com.hnjz.app.work.dao.VTjWorkListDao;
import com.hnjz.app.work.dao.VTjWryWorkDao;
import com.hnjz.app.work.po.VTjWorkList;
import com.hnjz.app.work.po.VTjWryWork;
import com.hnjz.app.work.po.VTjWorkList.TjType;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;

/**
 * 任务统计
 * @author zn
 * @version $Id: WorkStatisticsManager.java, v 0.1 2013-4-9 上午08:45:42 zn Exp $
 */
@Service(value = "workstatisticsManager")
public class WorkStatisticsManager implements ServletContextAware {
    /** 污染源任务视图 */
    @Autowired
    private VTjWryWorkDao  vtjWryWorkDao;
    /** 总任务视图 */
    @Autowired
    private VTjWorkListDao vtjWorkListDao;

    private ServletContext sc;

    /**
     * 部门任务统计
     * @param startTime
     * @param endTime
     * @param workType
     * @param workTypeName
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("deprecation")
    public List<Object> findDeptList(String startTime, String endTime, String workType,
                                     String workTypeName) throws SQLException {

        List<Object> list = new ArrayList<Object>();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String[] workTypeArr = workType.split(",");
        String[] workTypeNameArr = workTypeName.split(",");
        String[] rworkTypeArr = null;//结果中的任务类型
        String[] xdArr = null;
        String[] wcArr = null;
        String[] yqwcArr = null;
        String[] zxArr = null;
        String[] yqwwcArr = null;
        try {
            conn = vtjWryWorkDao.getMySession().connection();
            stmt = conn.prepareCall("{call pg_statistics.pro_dept(?,?,?,?,?)}");
            stmt.registerOutParameter("cur_ref", OracleTypes.CURSOR);
            stmt.setString("userArea", CtxUtil.getAreaId());
            stmt.setString("endTimeA", startTime);
            stmt.setString("endTimeB", endTime);
            stmt.setString("workTypeId", StringUtil.convertSqlIn(workType));
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject("cur_ref");
            WorkDeptStatisticsBean bean = null;
            WorkDeptTypeStatisticsBean typeBean = null;
            while (rs.next()) {
                bean = new WorkDeptStatisticsBean();
                bean.setOrgId(rs.getString("org_id"));
                bean.setOrgName(rs.getString("org_name"));
                bean.setfOrgName(rs.getString("f_org_name"));
                bean.setZxd(rs.getString("zxd"));
                bean.setZwc(rs.getString("zwc"));
                bean.setZyqwc(rs.getString("zyqwc"));
                bean.setZzx(rs.getString("zzx"));
                bean.setZyqwwc(rs.getString("zyqwwc"));
                bean.setWorkTypeIds(workTypeArr);
                bean.setWorkTypeNames(workTypeNameArr);
                rworkTypeArr = rs.getString("work_type_s").split(",");
                xdArr = rs.getString("xd_s").split(",");
                wcArr = rs.getString("wc_s").split(",");
                yqwcArr = rs.getString("yqwc_s").split(",");
                zxArr = rs.getString("zx_s").split(",");
                yqwwcArr = rs.getString("yqwwc_s").split(",");

                for (int i = 0; i < workTypeArr.length; i++) {
                    typeBean = null;
                    for (int j = 0; j < rworkTypeArr.length; j++) {
                        if (workTypeArr[i].equals(rworkTypeArr[j])) {//结果中存在所选类型
                            typeBean = new WorkDeptTypeStatisticsBean(workTypeArr[i],
                                workTypeNameArr[i], xdArr[j], wcArr[j], yqwcArr[j], zxArr[j],
                                yqwwcArr[j]);
                            break;
                        }
                    }
                    if (typeBean == null) {
                        typeBean = new WorkDeptTypeStatisticsBean(workTypeArr[i],
                            workTypeNameArr[i], "0", "0", "0", "0", "0");
                    }
                    bean.getTypeBeans().add(typeBean);
                }
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /**
     * 查询总任务列表
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
                                  String workType, String dept, String tjType)
                                                                              throws ParseException {
        Page page = findWorkTotalListBase(pageNo, pageSize, startTime, endTime, workType, dept,
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
     * 查询总任务列表基础（为列表显示及导出提供数据）
     * page中的list为对象集合
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param workType
     * @return
     * @throws ParseException
     */
    public Page findWorkTotalListBase(int pageNo, int pageSize, String startTime, String endTime,
                                      String workType, String dept, String tjType)
                                                                                  throws ParseException {
        endTime = DateUtil.getNextDate(endTime, 1);
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.isNotNull("orgId"));
        cList.add(Restrictions.eq("areaId", CtxUtil.getAreaId()));
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            cList.add(Restrictions.between("endTime", Date.valueOf(startTime),
                Date.valueOf(endTime)));
        } else {
            if (StringUtils.isNotBlank(startTime)) {
                cList.add(Restrictions.ge("endTime", Date.valueOf(startTime)));
            } else if (StringUtils.isNotBlank(endTime)) {
                cList.add(Restrictions.le("endTime", Date.valueOf(endTime)));
            }
        }
        if (StringUtils.isNotBlank(workType)) {
            cList.add(Restrictions.in("workTypeId", workType.split(",")));
        }
        if (StringUtils.isNotBlank(dept)) {
            cList.add(Restrictions.in("orgId", dept.split(",")));
        }
        if (StringUtils.isNotBlank(tjType)) {
            TjType t = TjType.getByCode(tjType);
            switch (t) {
                case XD:
                    cList.add(Restrictions.eq("xd", 1));
                    break;
                case WC:
                    cList.add(Restrictions.eq("wc", 1));
                    break;
                case YQWC:
                    cList.add(Restrictions.eq("yqwc", 1));
                    break;
                case ZX:
                    cList.add(Restrictions.eq("zx", 1));
                    break;
                case YQWWC:
                    cList.add(Restrictions.eq("yqwwc", 1));
                    break;
                default:
                    break;
            }
        }
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("endTime", false);
        //orderMap.put("workTypeId", false);
        List<VTjWorkList> list = vtjWorkListDao.findByCondition(pageNo, pageSize, cList, orderMap);
        int total = vtjWorkListDao.findCount(cList);

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(total);
        page.setList(list);
        return page;
    }

    /**
     * 部门统计生成EXCEL
     * @param startTime
     * @param endTime
     * @param workType
     * @param workTypeName
     * @return
     * @throws Exception
     */
    public String createDeptListExcel(String startTime, String endTime, String workType,
                                      String workTypeName) throws Exception {
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
        //集合
        List<Object> dataList = new ArrayList<Object>();
        List<Object> list = findDeptList(startTime, endTime, workType, workTypeName);
        for (int i = 0; i < list.size(); i++) {
            dataList.add(((WorkDeptStatisticsBean) list.get(i)).toArrayBean());
        }
        //添加合计
        dataList.add(new WorkDeptStatisticsArrayBean(dataList));
        map.put("list", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String dirPath = FileUpDownUtil.path.concat(File.separator).concat("excel")
            .concat(File.separator).concat("work").concat(File.separator);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = ExcelUtil.setValue(
            new File(dirPath.concat(sdf.format(c.getTime())).concat(".xls")),
            new File(sc.getRealPath(File.separator) + "excel/work/work_total.xls"),
            new File(sc.getRealPath(File.separator) + "excel/work/work_total.xml"), map);
        return file.getPath();
    }

    /**
     * 全部任务统计列表生成EXCEL
     * @param startTime
     * @param endTime
     * @param workType
     * @param workTypeName
     * @param dept
     * @param deptName
     * @param tjType
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String createTotalListExcel(String startTime, String endTime, String workType,
                                       String workTypeName, String dept, String deptName,
                                       String tjType) throws Exception {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> paraList = new ArrayList<Object>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("title", TjType.getByCode(tjType).getText().concat("列表"));
        paraMap.put("riqi", startTime.concat(" 到 ").concat(endTime));
        paraMap.put("deptName", deptName);
        paraMap.put("workTypeName", workTypeName);
        paraList.add(paraMap);
        map.put("param", paraList);
        //集合
        Page page = findWorkTotalListBase(-1, -1, startTime, endTime, workType, dept, tjType);
        List<VTjWorkList> tList = (List<VTjWorkList>) page.getList();
        List<Object> vtjWorkListBeanList = new ArrayList<Object>();
        for (int i = 0; i < tList.size(); i++) {
            vtjWorkListBeanList.add(new VTjWorkListBean(i + 1, tList.get(i)));
        }
        map.put("list", vtjWorkListBeanList);
       // System.out.println(vtjWorkListBeanList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String dirPath = FileUpDownUtil.path.concat(File.separator).concat("excel")
            .concat(File.separator).concat("work").concat(File.separator);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = ExcelUtil.setValue(
            new File(dirPath.concat(sdf.format(c.getTime())).concat(".xls")),
            new File(sc.getRealPath(File.separator) + "excel/work/work_total_list.xls"), new File(
                sc.getRealPath(File.separator) + "excel/work/work_total_list.xml"), map);
        return file.getPath();
    }

    /**污染源任务统计**/

    /**
     * 一般任务统计
     * @param pageNo
     * @param pageSize
     * @param workTypeId
     * @return
     */
    @SuppressWarnings("deprecation")
    public Page findCommList(Integer pageNo, Integer pageSize, String workTypeId, String wryName,
                             String city, String industry, String startTime, String endTime)
                                                                                            throws Exception {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<Object> list = null;
        int total = 0;
        try {
            conn = vtjWryWorkDao.getMySession().connection();
            stmt = conn.prepareCall("{call pg_statistics.pro_wry_comm(?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.registerOutParameter("cur_total", OracleTypes.INTEGER);
            stmt.registerOutParameter("cur_ref", OracleTypes.CURSOR);
            stmt.setInt("pageNo", pageNo);
            stmt.setInt("pageSize", pageSize);
            stmt.setString("userArea", CtxUtil.getAreaId());
            stmt.setString("startTime", startTime);
            stmt.setString("endTime", endTime);
            stmt.setString("workTypeId", workTypeId);
            stmt.setString("wryName", wryName);
            stmt.setString("city", city);
            stmt.setString("industry", industry);
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject("cur_ref");
            total = stmt.getInt("cur_total");
            list = new ArrayList<Object>();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                map.put("workids", rs.getString("workids"));
                map.put("workcount", rs.getString("workcount"));
                map.put("wrymc", rs.getString("wrymc"));
                map.put("hymc", rs.getString("hymc"));
                map.put("ssds", rs.getString("ssds"));
                map.put("hblxr", rs.getString("hblxr"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        Page page = new Page();
        page.setList(list);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(total);
        return page;
    }

    /**
     * 调查任务按部门查询任务统计
     * @param startTime
     * @param endTime
     * @param wflxId
     * @param wflxName
     * @return
     */
    public List<Object> findDcListByDept(String startTime, String endTime, String wflxId,
                                         String wflxName) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select m.orgid,");
        sqlBuffer.append("m.forgname,");
        sqlBuffer.append("m.orgname,");
        sqlBuffer.append("sum(m.workcount),");
        sqlBuffer.append("wm_concat(m.workcount),");
        sqlBuffer.append("wm_concat(m.wflxid),");
        sqlBuffer.append("wm_concat(m.wflxname)");
        sqlBuffer.append(" from (select t.orgid,");
        sqlBuffer.append("t.orgname,");
        sqlBuffer.append("t.forgname,");
        sqlBuffer.append("count(t.workid) workcount,");
        sqlBuffer.append("t.wflxid,");
        sqlBuffer.append("t.wflxname");
        sqlBuffer.append(" from v_tj_wry_work t");
        sqlBuffer.append(" where t.worktype = '40288aad3ceb645b013cebf276160001'");
        sqlBuffer.append(" and t.area_id='").append(CtxUtil.getAreaId()).append("'");
        //创建时间
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {//两个时间都不为空
            sqlBuffer.append(" and t.createtime between to_date('").append(startTime)
                .append(" 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date('").append(endTime)
                .append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        } else {
            if (StringUtils.isNotBlank(startTime)) {//开始时间不为空
                sqlBuffer.append(" and t.createtime >= to_date('").append(startTime)
                    .append(" 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
            } else if (StringUtils.isNotBlank(endTime)) {//结束时间不为空
                sqlBuffer.append("and t.createtime <= to_date('").append(endTime)
                    .append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
            }
        }
        //违法类型
        if (StringUtils.isNotBlank(wflxId)) {
            String[] wflxIdArr = wflxId.split(",");
            StringBuffer wflxIdBuffer = new StringBuffer();
            for (int i = 0; i < wflxIdArr.length; i++) {
                wflxIdBuffer.append("'").append(wflxIdArr[i]).append("'");
                if (i < wflxIdArr.length - 1) {
                    wflxIdBuffer.append(",");
                }
            }
            sqlBuffer.append(" and t.wflxid in (").append(wflxIdBuffer.toString()).append(")");
        }
        sqlBuffer.append(" group by t.orgid, t.orgname, t.forgname, t.wflxid, t.wflxname");
        sqlBuffer.append(" order by t.orgname) m");
        sqlBuffer.append(" group by m.orgid, m.forgname, m.orgname");
        List<Object> tjList = vtjWryWorkDao.findBySQL(sqlBuffer.toString());
        List<Object> list = new ArrayList<Object>();
        Object[] objs = null;
        WorkDcStatisticsBean bean = null;
        String[] wflxArr = wflxId.split(",");
        String[] wflxNameArr = wflxName.split(",");
        WorkTypeStatisticsBean typeBean = null;
        for (int i = 0; i < tjList.size(); i++) {
            objs = (Object[]) tjList.get(i);
            bean = new WorkDcStatisticsBean();
            bean.setOrgId(objs[0] == null ? "" : objs[0].toString());
            bean.setfOrgName(objs[1] == null ? "" : objs[1].toString());
            bean.setOrgName(objs[2] == null ? "" : objs[2].toString());
            bean.setTotalCount(objs[3] == null ? "" : objs[3].toString());
            bean.setWorkCount(objs[4] == null ? new String[0] : objs[4].toString().split(","));
            bean.setWorkTypeId(objs[5] == null ? new String[0] : objs[5].toString().split(","));
            bean.setWorkTypeName(objs[6] == null ? new String[0] : objs[6].toString().split(","));

            for (int j = 0; j < wflxArr.length; j++) {
                typeBean = null;
                for (int k = 0; k < bean.getWorkTypeId().length; k++) {
                    if (bean.getWorkTypeId()[k].equals(wflxArr[j])) {
                        typeBean = new WorkTypeStatisticsBean();
                        typeBean.setId(wflxArr[j]);
                        typeBean.setName(wflxNameArr[j]);
                        typeBean.setCount(bean.getWorkCount()[k]);
                        break;
                    }
                }
                if (typeBean == null) {//未查询出此违法类型数据
                    typeBean = new WorkTypeStatisticsBean();
                    typeBean.setId(wflxArr[j]);
                    typeBean.setName(wflxNameArr[j]);
                    typeBean.setCount("0");
                }
                bean.getTypeBeans().add(typeBean);
            }
            list.add(bean);
        }
        return list;
    }

    /**
     * 调查任务按地市查询任务统计
     * @param startTime
     * @param endTime
     * @param wflxId
     * @param wflxName
     * @return
     */
    public List<Object> findDcListByCity(String startTime, String endTime, String wflxId,
                                         String wflxName) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select m.ssds,");
        sqlBuffer.append("sum(m.workcount),");
        sqlBuffer.append("wm_concat(m.workcount),");
        sqlBuffer.append("wm_concat(m.wflxid),");
        sqlBuffer.append("wm_concat(m.wflxname)");
        sqlBuffer.append(" from (select t.ssds, count(t.workid) workcount, t.wflxid, t.wflxname");
        sqlBuffer.append(" from v_tj_wry_work t");
        sqlBuffer.append(" where t.worktype = '40288aad3ceb645b013cebf276160001'");
        sqlBuffer.append(" and t.area_id='").append(CtxUtil.getAreaId()).append("'");
        //创建时间
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {//两个时间都不为空
            sqlBuffer.append(" and t.createtime between to_date('").append(startTime)
                .append(" 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date('").append(endTime)
                .append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        } else {
            if (StringUtils.isNotBlank(startTime)) {//开始时间不为空
                sqlBuffer.append(" and t.createtime >= to_date('").append(startTime)
                    .append(" 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
            } else if (StringUtils.isNotBlank(endTime)) {//结束时间不为空
                sqlBuffer.append("and t.createtime <= to_date('").append(endTime)
                    .append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
            }
        }
        //违法类型
        if (StringUtils.isNotBlank(wflxId)) {
            String[] wflxIdArr = wflxId.split(",");
            StringBuffer wflxIdBuffer = new StringBuffer();
            for (int i = 0; i < wflxIdArr.length; i++) {
                wflxIdBuffer.append("'").append(wflxIdArr[i]).append("'");
                if (i < wflxIdArr.length - 1) {
                    wflxIdBuffer.append(",");
                }
            }
            sqlBuffer.append(" and t.wflxid in (").append(wflxIdBuffer.toString()).append(")");
        }
        sqlBuffer.append(" group by t.ssds, t.wflxid, t.wflxname");
        sqlBuffer.append(" order by t.ssds) m");
        sqlBuffer.append(" group by m.ssds");
        List<Object> tjList = vtjWryWorkDao.findBySQL(sqlBuffer.toString());
        List<Object> list = new ArrayList<Object>();
        Object[] objs = null;
        WorkDcStatisticsCityBean bean = null;
        String[] wflxArr = wflxId.split(",");
        String[] wflxNameArr = wflxName.split(",");
        WorkTypeStatisticsBean typeBean = null;
        for (int i = 0; i < tjList.size(); i++) {
            objs = (Object[]) tjList.get(i);
            bean = new WorkDcStatisticsCityBean();
            bean.setCityId("");
            bean.setCityName(objs[0] == null ? "" : objs[0].toString());
            bean.setTotalCount(objs[1] == null ? "" : objs[1].toString());
            bean.setWorkCount(objs[2] == null ? new String[0] : objs[2].toString().split(","));
            bean.setWorkTypeId(objs[3] == null ? new String[0] : objs[3].toString().split(","));
            bean.setWorkTypeName(objs[4] == null ? new String[0] : objs[4].toString().split(","));

            for (int j = 0; j < wflxArr.length; j++) {
                typeBean = null;
                for (int k = 0; k < bean.getWorkTypeId().length; k++) {
                    if (bean.getWorkTypeId()[k].equals(wflxArr[j])) {
                        typeBean = new WorkTypeStatisticsBean();
                        typeBean.setId(wflxArr[j]);
                        typeBean.setName(wflxNameArr[j]);
                        typeBean.setCount(bean.getWorkCount()[k]);
                        break;
                    }
                }
                if (typeBean == null) {//未查询出此违法类型数据
                    typeBean = new WorkTypeStatisticsBean();
                    typeBean.setId(wflxArr[j]);
                    typeBean.setName(wflxNameArr[j]);
                    typeBean.setCount("0");
                }
                bean.getTypeBeans().add(typeBean);
            }
            list.add(bean);
        }
        return list;
    }

    /**
     * 得到调查任务的编号集合
     * @param startTime
     * @param endTime
     * @param wflxId
     * @param deptIds
     * @param citys
     * @return
     */
    public String getDcWorkIds(String startTime, String endTime, String wflxId, String deptIds,
                               String citys) {
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.eq("worktype", "40288aad3ceb645b013cebf276160001"));
        cList.add(Restrictions.eq("areaId", CtxUtil.getAreaId()));
        cList.add(Restrictions.in("wflxid", wflxId.split(",")));//违法类型
        if (StringUtils.isNotBlank(deptIds)) {
            cList.add(Restrictions.in("orgid", deptIds.split(",")));
        }
        if (StringUtils.isNotBlank(citys)) {
            cList.add(Restrictions.in("ssds", citys.split(",")));
        }
        endTime = DateUtil.getNextDate(endTime, 1);
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            cList.add(Restrictions.between("createtime", Date.valueOf(startTime),
                Date.valueOf(endTime)));
        } else {
            if (StringUtils.isNotBlank(startTime)) {
                cList.add(Restrictions.ge("createtime", Date.valueOf(startTime)));
            } else if (StringUtils.isNotBlank(endTime)) {
                cList.add(Restrictions.le("createtime", Date.valueOf(endTime)));
            }
        }
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("archivestime", false);//归档时间倒序
        List<VTjWryWork> vList = vtjWryWorkDao.findByCondition(-1, -1, cList, orderMap);
        StringBuffer ids = new StringBuffer();
        for (int i = 0; i < vList.size(); i++) {
            ids.append(vList.get(i).getWorkid()).append(",");
        }
        return ids.toString();
    }

    /**
     * 根据任务编号集合查询任务
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> findWorkByIds(String ids) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (StringUtils.isNotBlank(ids)) {
            List<Criterion> cList = new ArrayList<Criterion>();
            cList.add(Restrictions.in("id", ids.split(",")));
            Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
            orderMap.put("archivestime", false);//归档时间倒序
            List<VTjWryWork> vList = vtjWryWorkDao.findByCondition(-1, -1, cList, orderMap);
            for (int i = 0; i < vList.size(); i++) {
                list.add(vList.get(i).toCommMap());
            }
        }
        return list;
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        this.sc = arg0;
    }
}
