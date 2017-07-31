/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.worktype;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.work.po.WorkType;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;
import com.hnjz.wf.enums.ProcessEnum;
/**
 * 
 * @author wumi
 * @version $Id: WorkTypeController.java, v 0.1 Jan 28, 2013 11:42:34 AM wumi Exp $
 */
@Controller
public class WorkTypeController {
    /**日志*/
    private static final Log log = LogFactory.getLog(WorkTypeController.class);

    @Autowired
    private WorkTypeManager  workTypeManager;

    /**
     * 进入到任务类型管理的初始界面
     * 
     * @param model  {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/workType.htm")
    public String workType(ModelMap model) {
        this.queryWorkType(model, null);
        return "app/rwsz/worktype/workType";
    }

    /**
     * 查询任务类型
     * 
     * @param model {@link ModelMap}
     * @param name 查询条件
     */
    @RequestMapping(value = "/queryWorkType.json", produces = "application/json")
    public void queryWorkType(ModelMap model, String name) {
        try {
            JSONArray re = workTypeManager.queryWorkType(name);
            model.addAttribute("re", re.toString());
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }

    /**
     * 删除一个任务类型
     * 
     * @param model  {@link ModelMap}
     * @param id 任务类型ID
     */
    @RequestMapping(value = "/delWorkType.json", produces = "application/json")
    public void delWorkType(ModelMap model, String id) {
        try {
            workTypeManager.removeWorkType(id);
            JsonResultUtil.suncess(model, "删除成功！");
        } catch (Exception e) {
            log.error("删除菜单信息错误：", e);
            JsonResultUtil.fail(model, "删除失败！");
        }
    }

    /**
     * 保存任务类型
     * 
     * @param model {@link ModelMap}
     * @param frm  任务类型的form表单 {@link WorkTypeForm}
     */
    @RequestMapping(value = "/saveWorkType.json", produces = "application/json")
    public void saveWorkType(ModelMap model, WorkTypeForm frm) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("表单信息:" + LogUtil.m(frm));
            }
            workTypeManager.saveWorkType(frm);
            JsonResultUtil.suncess(model, "保存成功！");
        } catch (AppException e) {
            log.error("保存菜单信息错误：", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }

    /**
     * 编辑一个任务类型
     * 
     * @param id 菜单Id
     * @return 任务类型的编辑界面
     */
    @RequestMapping(value = "/editWorkType.htm")
    public String editWorkType(ModelMap model, WorkTypeForm frm) {
        if (log.isDebugEnabled()) {
            log.debug("id:" + frm.getId());
        }
        if (StringUtils.isBlank(frm.getId())) {
            return "app/rwsz/worktype/editWorkType";
        }
        WorkType po = (WorkType) this.workTypeManager.get(WorkType.class, frm.getId());
        frm.setId(po.getId());
        frm.setName(po.getName());
        frm.setIsjjrw(po.getIsjjrw());
        frm.setNote(po.getNote());
        frm.setOrder(po.getOrder());
        frm.setZfdxlx(po.getZfdxlx());
        frm.setShjb(po.getShjb());
        frm.setUrl(po.getUrl());
        frm.setUrl2(po.getUrl2());
        frm.setCode(po.getCode());
        frm.setSjurl(po.getSjurl());
        frm.setGzliu(po.getGzliu());
        return "app/rwsz/worktype/editWorkType";
    }

    /**
     * 任务类型的公共选择界面
     * 
     * @param id 任务类型Id
     * @param oper  操作方式 s-单选，m-多选
     * @return 任务类型的公共选择界面
     */
    @RequestMapping(value = "/workTypePub.json", produces = "application/json")
    @ResponseBody
    public List<Map<String, String>> workTypePub() {
        try {
            return workTypeManager.queryWorkType();
        } catch (Exception e) {
            log.error("查询菜单信息错误：", e);
        }
        return null;
    }

    /**
     * 任务类型的公共选择界面
     * 
     * @param id 任务类型Id
     * @param oper  操作方式 s-单选，m-多选
     * @return 任务类型的公共选择界面
     */
    @RequestMapping(value = "/workTypePubQuery.htm")
    public String workTypePubQuery(ModelMap model, String id, String oper) {
        try {
            JSONArray re = workTypeManager.queryAllWorkType(id);
            model.addAttribute("oper", oper);
            model.addAttribute("menu", re.toString());
        } catch (Exception e) {
            log.error("查询菜单信息错误：", e);
        }
        return "work/statistics/treePubQuery";
    }

    /**
     * 任务类型下拉列表的公共查询
     */
    @RequestMapping(value = "/queryAllWkCode.json", produces = "application/json")
    @ResponseBody
    public List<Combobox> queryAllRole() {
        try {
            return TaskTypeCode.getTypes();
        } catch (Exception e) {
            log.error("查询错误：", e);
        }
        return null;
    }
    /**
     *  工作流
     */
    @RequestMapping(value = "/queryGzliu.json", produces = "application/json")
    @ResponseBody
    public List<Combobox> queryGzliu() {
        try {
            return ProcessEnum.getTypes();
        } catch (Exception e) {
            log.error("查询错误：", e);
        }
        return null;
    }
}
