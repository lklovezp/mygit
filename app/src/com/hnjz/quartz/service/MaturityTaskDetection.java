/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.manager.GotoneManager;
import com.hnjz.app.work.po.VdbWork;
import com.hnjz.common.AppException;
import com.hnjz.common.util.DateUtil;

/**
 * 
 * @author wangliang
 * @version $Id: MaturityTaskDetection.java, v 0.1 Aug 20, 2013 4:00:45 PM wangliang Exp $
 */
@Service("maturityTaskDetection")
public class MaturityTaskDetection {

    /**日志*/
    private static final Log             log = LogFactory.getLog(MaturityTaskDetection.class);

    @Autowired
    private MaturityTaskDetectionManager maturityTaskDetectionManager;

    @Autowired
    private GotoneManager                gotoneManager;
    
    @Value("#{settings['WHETHER_THE_JOB_RUN']}")
    private String                whetherToRun;   

    /**
     * 到期检测方法 由quartz定时调用
     */
    public void detect() throws AppException {
        if (!StringUtils.equals("0", whetherToRun)) {
            return;
        }

        String strCurTime = DateUtil.getDate(new Date());// new Date()为获取当前系统时间
        if (log.isDebugEnabled()) {
            log.debug("在" + strCurTime + "时，检测任务操作被触发。");
        }

        try {
            List<VdbWork> listVdbWork = maturityTaskDetectionManager.getMaturityTasks();
            for (VdbWork ele : listVdbWork) {
                this.gotoneManager.saveYuqi(ele.getUserId(), ele);
            }
        } catch (Exception e) {
            log.error("检测任务到期时出错：", e);
        }
    }

}
