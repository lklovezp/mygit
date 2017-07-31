/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.Constants;
import com.hnjz.app.work.worktype.WorkTypeManager;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 首页Manager
 * 
 * @author sunkuo
 * @version $Id: HomeQueryController.java, v 0.1 2013-6-24 上午08:00:14 Administrator Exp $
 */
@Service("homeManager")
public class HomeManager extends ManagerImpl {
    /**日志*/
    private static final Log log = LogFactory.getLog(HomeManager.class);
    @Autowired
    private WorkTypeManager  workTypeManager;
    @Autowired
    private UserManager      userManager;

    /**
     * （首页）统计查询
     * 
     * @param name 查询条件 
     * @return 根据条件搜索出符合条件的信息
     */
    public List<Object[]> getWork(String name) {
        String hsql = null;
        List<Object[]> list = new ArrayList<Object[]>();
        if (StringUtils.isNotBlank(name)) {
            if (name.equals("0")) {
                hsql = "select zfdxName,count(id) from Work  where areaid = ?  group by zfdxName";
            } else if (name.equals("1")) {
                hsql = "select createUser.name,count(id) from Work  where areaid = ?  group by createUser.name";
            } else if (name.equals("2")) {
                hsql = "select state,count(id) from Work  where areaid = ?  group by state";
            } else if (name.equals("3")) {
                hsql = "select workType.name,count(id) from Work where areaid = ? group by workType.name";
            } else if (name.equals("4")) {
                hsql = "select nextOper,count(id) from Work where nextOper is not null and areaid = ?  group by nextOper";
            }
            list = this.dao.find(hsql,CtxUtil.getAreaId());
        }
        if ("2".equals(name)) {
            Object[] obj = new Object[] {};
            for (int i = 0; i < list.size(); i++) {
                obj = list.get(i);
                obj[0] = WorkState.getNote(list.get(i)[0].toString());
            }
        }
        return list;
    }

    /**
     * 统计查询
     * 
     * @param hName,hValue 查询条件 
     * @param page 第几页
     * @param pageSize 每页显示多少条
     * @return 根据条件搜索出符合条件的信息
     */
    public FyWebResult homeInfo(String hName, String hValue, String page, String pageSize) {
        QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuffer sql = new StringBuffer();
        sql.append("from Work where 1=1 ");

        if (StringUtils.isNotBlank(hName) && StringUtils.isNotBlank(hValue)) {
            if (hName.equals("0")) {
                sql.append("and zfdxName = :hValue");
            } else if (hName.equals("1")) {
                sql.append("and createUser.name = :hValue");
            } else if (hName.equals("2")) {
                sql.append("and state = :hValue");
            } else if (hName.equals("3")) {
                sql.append("and workType.name = :hValue");
            } else if (hName.equals("4")) {
                sql.append("and nextOper = :hValue");
            }
            if ("2".equals(hName)) {
                hValue = WorkState.getCode(hValue);
            }
            data.put("hValue", hValue);
        }
        log.debug("SQL: " + sql);
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> vs = pos.getRe();
        Map<String, String> d = null;
        String type = null;
        TSysUser u = null;
        for (Work ele : vs) {
            d = new HashMap<String, String>();
            d.put("id", ele.getId());
//            type = this.workTypeManager.getTypeName(ele.getWorkType().getId());
//            d.put("workType", type);
            d.put("workName", ele.getName());
//            d.put("zfdxName", ele.getZfdxName());
            d.put("created", DateUtil.getDate(ele.getCreateTime()));
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            d.put("zxrName", StringUtils.defaultIfBlank(ele.getZxrName(), ""));
            d.put("code", ele.getCode());
            if (StringUtils.isNotBlank(ele.getTaskId())) {
                d.put("taskId", ele.getTaskId().replace(",", ""));
            } else {
                d.put("taskId", "");
            }
            d.put("zxtime", DateUtil.getDate(ele.getZxtime()));
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("stateCode", ele.getState());//任务状态编号add by xugh
            d.put("start", DateUtil.getDate(ele.getStartTime()));
            d.put("end", DateUtil.getDate(ele.getEndTime()));
            rows.add(d);
        }
        fy.setRows(rows);
        return fy;
    }
}
