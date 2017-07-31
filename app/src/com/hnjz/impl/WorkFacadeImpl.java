/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.facade.WorkFacade;
import com.hnjz.facade.domain.Result;
import com.hnjz.facade.domain.WorkDto;

/**
 * 
 * 任务相关web 服务的实现
 * @author wumi
 * @version $Id: WorkFacadeImpl.java, v 0.1 Apr 2, 2013 11:50:00 AM wumi Exp $
 */
@Service("workFacade")
public class WorkFacadeImpl implements WorkFacade {

    /**日志*/
    private static final Log log = LogFactory.getLog(WorkFacadeImpl.class);

    @Autowired
    private WorkPf           workPf;
    
    @Autowired
    private CommWorkManager    commWorkManager;

    /**
     * @see com.hnjz.facade.WorkFacade#createRwToArea(com.hnjz.facade.domain.WorkDto, java.lang.String)
     */
    public Result createRwToArea(WorkDto dto, String areaId, String newresults) {
        if (log.isDebugEnabled()) {
            log.debug("WorkDto:" + dto);
            log.debug("areaId:" + areaId);
        }
        Result result = new Result();
        try {
            workPf.savePfJsxp(dto, areaId, newresults);
            result.setOk(true);
        } catch (Exception e) {
            log.error("", e);
            result.setOk(false);
        }

        return result;
    }
    
    /**
     * @see com.hnjz.facade.WorkFacade#createRwToArea(com.hnjz.facade.domain.WorkDto, java.lang.String)
     */
    public Result saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) {
        if (log.isDebugEnabled()) {
            log.debug("sjid:" + sjid);
            log.debug("sbAreaId:" + sbAreaId);
        }
        Result result = new Result();
        try {
        	commWorkManager.saveSbRwlxAndFile(sjid,sbAreaId,rwlxIds,sbFileInfo,bLspsInfo,jcjlMap,rcbgDescMap);
            result.setOk(true);
        } catch (Exception e) {
            log.error("", e);
            result.setOk(false);
        }

        return result;
    }

}
