/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.po.VdbWork;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;

/**
 * 
 * @author wangliang
 * @version $Id: MaturityTaskDetectionManager.java, v 0.1 Aug 21, 2013 3:29:36 PM wangliang Exp $
 */
@Service("maturityTaskDetectionManager")
public class MaturityTaskDetectionManager extends ManagerImpl {

    /**日志*/
    private static final Log log       = LogFactory.getLog(MaturityTaskDetectionManager.class);

    /**
     * 获取到期任务列表
     * 
     * @return
     */
    public List<VdbWork> getMaturityTasks() {
        // 【判断：当前时间在结束时间前的2天以内，则认为是到期任务】
        List<VdbWork> listVdbWork = new ArrayList<VdbWork>();
        try {
            // 构造sql语句
            QueryCondition data = new QueryCondition();
            StringBuilder sql = new StringBuilder();
            sql.append("from VdbWork where endTime < :curtime1");
            Date curdat = new Date();// 字符串转Date。获取当前系统时间
            Date curdat1 = new Date(curdat.getTime() + 2 * 24 * 60 * 60 * 1000);// 字符串转Date 。计算加2天之后的日期      
            data.put("curtime1", curdat1);
            // 根据sql语句进行查询，获得结果集
            listVdbWork = dao.find(sql.toString(), data);
        } catch (Exception e) {
            log.error("查询到期任务时出现异常：", e);
        }
        return listVdbWork;
    }

}
