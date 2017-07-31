/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.worktype;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.work.po.WorkType;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserPosition;

/**
 * 任务类型管理
 * 
 * @author wumi
 * @version $Id: WorkTypeManager.java, v 0.1 Jan 28, 2013 11:41:42 AM wumi Exp $
 */
@Service("workTypeManager")
public class WorkTypeManager extends ManagerImpl {

    /**日志*/
    private static final Log      log   = LogFactory.getLog(WorkTypeManager.class);

    /**用户对应部门的缓存，key为usrid,value为用户对应的部门*/
    private Map<String, WorkType> types = new ConcurrentHashMap<String, WorkType>();

    /**
     * 获取任务类型的名称
     * 
     * @param id 任务类型Id
     * @return 任务类型的名称
     */
    public String getTypeName(String id) {
        WorkType t = this.getWorkType(id);
        if (null != t) {
            return t.getName();
        }
        return "";
    }

    /**
     * 根据任务类型Id获取任务类型
     * 
     * @param id 用户ID
     * @return 用户
     */
    public WorkType getWorkType(String id) {
        WorkType workType = types.get(id);
        if (null == workType) {
            workType = (WorkType) this.get(WorkType.class, id);
            types.put(id, workType);
        }
        return workType;
    }

    /**
     * 选择功能菜单公共页面的查询
     * 
     * @return 功能菜单列表
     * @throws Exception 
     */
    public List<Map<String, String>> queryWorkType() throws Exception {
        List<Map<String, String>> re = new ArrayList<Map<String, String>>();
        String hsql = "from WorkType where isActive=? ";
        List<WorkType> pos = dao.find(hsql, YnEnum.Y.getCode());
        Map<String, String> o = null;
        for (WorkType ele : pos) {
            o = new HashMap<String, String>();
            o.put("id", ele.getId());
            if (StringUtils.isBlank(ele.getPid())) {
                o.put("pid", ele.getPid());
            }
            o.put("name", ele.getName());
            re.add(o);
        }
        return re;
    }

    /**
     * 选择功能菜单公共页面的查询
     * 
     * @return 功能菜单列表
     * @throws Exception 
     */
    public JSONArray queryAllWorkType(String id) throws Exception {
        JSONArray re = new JSONArray();
        String hsql = "from WorkType where isActive=? ";
        List<WorkType> pos = dao.find(hsql, YnEnum.Y.getCode());
        JSONObject jsonObj = null;
        for (WorkType ele : pos) {
            jsonObj = new JSONObject();
            jsonObj.put("id", ele.getId());
            if (StringUtils.isBlank(ele.getPid())) {
                jsonObj.put("pid", ele.getPid());
            }
            if (StringUtils.equals(id, ele.getId())) {
                jsonObj.put("selected", true);
            }
            jsonObj.put("name", ele.getName());
            re.put(jsonObj);
        }
        return re;
    }

    /**
     * 查询所有任务类型
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> queryAllWorkType() throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String hsql = "from WorkType where isActive=? ";
        List<WorkType> pos = dao.find(hsql, YnEnum.Y.getCode());
        WorkType root = new WorkType();
        root.setId("0");
        root.setName("任务类型");
        list.add(root.toMobileTreeMap());
        for (int i = 0; i < pos.size(); i++) {
            list.add(pos.get(i).toMobileTreeMap());
        }
        return list;
    }

    /**
     * 查询任务类型
     * 
     * @return 任务类型列表
     * @throws Exception 
     */
    public JSONArray queryWorkType(String name) throws Exception {
        JSONArray re = new JSONArray();
        QueryCondition data = new QueryCondition();
        StringBuilder sql = new StringBuilder();
        sql.append("from WorkType where 1=1 ");
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and funcName like :funcName ");
            data.putLike("funcName", name);
        }
        sql.append("order by order");
        List<WorkType> pos = dao.find(sql.toString(), data);
        JSONObject jsonObj = null;
        for (WorkType ele : pos) {
            jsonObj = new JSONObject();
            jsonObj.put("id", ele.getId());
            if (StringUtils.isBlank(name) && null != ele.getPid()) {
                jsonObj.put("pid", ele.getPid());
            }
            JSONObject dataObject = new JSONObject();
            dataObject.put("name", ele.getName());
            dataObject.put("code", TaskTypeCode.getNote(ele.getCode()));
            if (log.isDebugEnabled()) {
                log.debug(ele.getShjb() + ":" + ele.getName());
            }
            dataObject.put("shjb", UserPosition.getNote(ele.getShjb()));
            dataObject.put("zfdxlx", ele.getZfdxlx());
            dataObject.put("url", ele.getUrl());
            dataObject.put("url2", ele.getUrl2());
            dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
            jsonObj.put("dataObject", dataObject);
            re.put(jsonObj);
        }
        return re;
    }

    /**
     * 保存一个任务类型
     * 
     * @param frm {@link WorkTypeForm}
     */
    public void saveWorkType(WorkTypeForm frm) throws AppException {
        WorkType po = null;
        TSysUser user = CtxUtil.getCurUser();
        Date cur = new Date();
        if (StringUtils.isNotBlank(frm.getId())) {
            po = (WorkType) this.get(WorkType.class, frm.getId());
        } else {
            po = new WorkType();
            po.setCreater(user);
            po.setCreateTime(cur);
        }

        if (StringUtils.isNotBlank(frm.getPid())) {
            po.setPid(frm.getPid());
        }
        po.setCode(frm.getCode());
        po.setOrder(frm.getOrder());
        po.setName(frm.getName());
        po.setNote(frm.getNote());
        po.setZfdxlx(frm.getZfdxlx());
        po.setShjb(frm.getShjb());
        po.setUrl(frm.getUrl());
        po.setUrl2(frm.getUrl2());
        po.setSjurl(frm.getSjurl());
        po.setGzliu(frm.getGzliu());
        po.setIsActive(YnEnum.Y.getCode());
        po.setUpdateby(user);
        po.setUpdateTime(cur);
        this.dao.save(po);
        this.types.remove(frm.getId());
        if (log.isDebugEnabled()) {
            log.debug("保存任务类型成功");
        }
    }

    /**
     * 删除任务类型信息
     * 
     * @param id 任务类型信息的ID
     */
    public void removeWorkType(String id) {
        this.dao.remove(WorkType.class, id);
    }

    /**
     * 查询任务类型
     * 
     * @return
     */
    public List<WorkType> queryWorkTypes() {
        String hsql = "from WorkType where isActive=? ";
        return dao.find(hsql, YnEnum.Y.getCode());
    }
    /**
     * 更新任务类型
     * 
     * @param po
     */
    public void updateWorkType(WorkType po){
        this.dao.save(po);
    }
}
