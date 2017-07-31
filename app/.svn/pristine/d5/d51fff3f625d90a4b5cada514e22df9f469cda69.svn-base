package com.hnjz.app.work.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.beans.Page;
import com.hnjz.app.work.beans.WorkAnalyseBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.process.ProcessManager;

/**
 * 任务的查询操作
 * @author zn
 * @version $Id: WorkQueryManager.java, v 0.1 2013-4-7 上午01:23:36 zn Exp $
 */
@Service(value = "workQueryManager")
public class WorkQueryManager {
    @Autowired
    private WorkDao        workDao;
    @Autowired
    private ProcessManager processManager;
    @Autowired
    private UserManager    userManager;

    /**
     * 分页查询“逾期通报”数据列表
     * @param pageNo
     * @param pageSize
     * @param reportTime
     * @param workTypeId
     * @param workSourceId
     * @param urgencyDegree
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public Page findOverdueReportList(int pageNo, int pageSize, String reportTime,
                                      String workTypeId, String workSourceId, String urgencyDegree)
                                                                                                   throws Exception {
        List<Object> list = new ArrayList<Object>();
        int total = 0;
        Map<String, Object> map = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = workDao.getMySession().connection();
            stmt = conn.prepareCall("{call pg_check.pro_overdue_report(?,?,?,?,?,?,?,?,?)}");
            stmt.registerOutParameter("cur_total", OracleTypes.INTEGER);
            stmt.registerOutParameter("cur_ref", OracleTypes.CURSOR);
            stmt.setInt("pageNo", pageNo);
            stmt.setInt("pageSize", pageSize);
            stmt.setString("reportTime", reportTime);
            stmt.setString("workTypeId", workTypeId);
            stmt.setString("workSourceId", workSourceId);
            stmt.setString("urgencyDegree", urgencyDegree);
            stmt.setString("userArea", CtxUtil.getAreaId());
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject("cur_ref");
            total = stmt.getInt("cur_total");
            while (rs.next()) {
                map = new HashMap<String, Object>();
                map.put("work_id", rs.getString("work_id"));
                map.put("work_name", rs.getString("work_name"));
                map.put("create_user_name", rs.getString("create_user_name"));
                map.put("zx_user_name", rs.getString("zx_user_name"));
                map.put("work_type_name", rs.getString("work_type_name"));
                map.put("start_time", rs.getDate("start_time"));
                map.put("end_time", rs.getDate("end_time"));
                map.put("cz_user_name", rs.getString("cz_user_name"));
                map.put("work_state_code", WorkState.getNote(rs.getString("work_state_code")));
                map.put("l_id", rs.getString("l_id"));
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
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(total);
        page.setList(list);
        return page;
    }

    /**
     * 查询逾期任务
     * @param pageNo
     * @param pageSize
     * @param endTimeA
     * @param endTimeB
     * @param workName
     * @param workTypeId
     * @param workSourceId
     * @param urgencyDegree
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public Page findOverdueList(int pageNo, int pageSize, String endTimeA, String endTimeB,
                                String workName, String workTypeId, String workSourceId,
                                String urgencyDegree) throws Exception {
        List<Object> list = new ArrayList<Object>();
        int total = 0;
        Map<String, Object> map = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = workDao.getMySession().connection();
            stmt = conn.prepareCall("{call pg_check.pro_overdue_list(?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.registerOutParameter("cur_total", OracleTypes.INTEGER);
            stmt.registerOutParameter("cur_ref", OracleTypes.CURSOR);
            stmt.setInt("pageNo", pageNo);
            stmt.setInt("pageSize", pageSize);
            stmt.setString("endTimeA", endTimeA);
            stmt.setString("endTimeB", endTimeB);
            stmt.setString("workName", workName);
            stmt.setString("workTypeId", workTypeId);
            stmt.setString("workSourceId", workSourceId);
            stmt.setString("urgencyDegree", urgencyDegree);
            stmt.setString("userArea", CtxUtil.getAreaId());
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject("cur_ref");
            total = stmt.getInt("cur_total");
            while (rs.next()) {
                map = new HashMap<String, Object>();
                map.put("work_id", rs.getString("work_id"));
                map.put("work_name", rs.getString("work_name"));
                map.put("create_user_name", rs.getString("create_user_name"));
                map.put("zx_user_name", rs.getString("zx_user_name"));
                map.put("work_type_name", rs.getString("work_type_name"));
                map.put("start_time", rs.getDate("start_time"));
                map.put("end_time", rs.getDate("end_time"));
                map.put("cz_user_name", rs.getString("cz_user_name"));
                map.put("work_state_code", WorkState.getNote(rs.getString("work_state_code")));
                map.put("l_id", rs.getString("l_id"));
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
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(total);
        page.setList(list);
        return page;
    }

    /**
     * 查询任务分析内容
     * @param workId
     * @return
     * @throws Exception 
     */
    public WorkAnalyseBean findWorkAnalyse(String workId, String logId) throws Exception {
        WorkAnalyseBean bean = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //任务信息
        Work work = workDao.get(workId);
        bean = new WorkAnalyseBean(work);
        //当前阶段
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select wl.id_,wl.work_state_ from work_log wl");
        sqlBuffer
            .append(" where wl.operate_time_ = (select max(ltype.operate_time_) from work_log ltype where wl.work_id_ = ltype.work_id_)");
        sqlBuffer.append(" and wl.work_id_=?");
        List<Object> curlist = workDao.findBySQL(sqlBuffer.toString(), workId);
        if (curlist.size() > 0) {
            Object[] curArr = (Object[]) curlist.get(0);
            bean.setCurState(curArr[1] == null ? "" : WorkState.getNote(curArr[1].toString()));
        }
        //查询当前任务待办人
        List<String> userNameList = processManager.getAssignee(work.getProcessId());
        List<TSysUser> userList = userManager.getUsersByLoginName(userNameList);
        StringBuffer nameBuffer = new StringBuffer();
        for (int i = 0; i < userList.size(); i++) {
            nameBuffer.append(userList.get(i).getName());
            if (i < userList.size() - 1) {
                nameBuffer.append(",");
            }
        }
        bean.setToOperUser(nameBuffer.toString());
        //最长阶段
        String hql = "from WorkLog l where l.userTime=(select max(wl.userTime) from WorkLog wl where wl.work.id=l.work.id) and l.work.id=?";
        List<WorkLog> objList = workDao.find(hql, workId);
        if (objList.size() > 0) {
            bean.setLongestExecUser(objList.get(0).getCzrName() == null ? "" : objList.get(0)
                .getCzrName());
            bean.setLongestSectionStep(objList.get(0).getOperateType() == null ? "" : WorkLogType
                .getNote(objList.get(0).getOperateType()));
            bean.setLongestStartTime(objList.get(0).getStartTime() == null ? "" : sdf
                .format(objList.get(0).getStartTime()));
            bean.setLongestEndTime(objList.get(0).getCzsj() == null ? "" : sdf.format(objList
                .get(0).getCzsj()));
            bean.setLongestUseTime(objList.get(0).getUserTime() == null ? "" : DateDifferenceUtil
                .getTimeConvert(objList.get(0).getUserTime()));
        }
        //逾期点分析
        WorkLog workLog = (WorkLog) workDao.get(WorkLog.class, logId);
        bean.setOverdueEndTime(workLog.getCzsj() == null ? "" : sdf.format(workLog.getCzsj()));
        bean.setOverdueExecUser(workLog.getCzrName());
        bean.setOverdueSectionStep(workLog.getOperateType() == null ? "" : WorkLogType
            .getNote(workLog.getOperateType()));
        bean.setOverdueStartTime(workLog.getStartTime() == null ? "" : sdf.format(workLog
            .getStartTime()));
        return bean;
    }

    /**
     * 得到阶段分析数据
     * @param workId
     * @return
     */
    public List<WorkLog> findSectionAnalysis(String workId) {
        String hql = "from WorkLog t where t.work.id=? order by t.czsj";
        List<WorkLog> logList = workDao.find(hql, workId);
        return logList;
    }

    /**
     * 分页查询污染源检查记录
     * @param pageNo
     * @param pageSize
     * @param wryId
     * @return
     */
    public Page findByWry(int pageNo, int pageSize, String wryId) {
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.eq("zfdxId", wryId));
        cList.add(Restrictions.eq("areaid", CtxUtil.getAreaId()));
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("createTime", false);
        List<Work> workList = workDao.findByCondition(pageNo, pageSize, cList, orderMap);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < workList.size(); i++) {
            list.add(workList.get(i).toMobileWryInspectionRecordMap());
        }
        Integer total = workDao.findCount(cList);
        Page page = new Page(pageNo, pageSize);
        page.setList(list);
        page.setTotal(total);
        return page;
    }
}
