/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.facade;

import java.util.List;
import java.util.Map;

import com.hnjz.facade.domain.Result;
import com.hnjz.facade.domain.WorkDto;

/**
 * 任务发布的web服务接口
 * 
 * @author wumi
 * @version $Id: WorkFacade.java, v 0.1 Apr 1, 2013 3:34:42 PM wumi Exp $
 */
public interface WorkFacade {
    /**
     * 任务下派的接口，任务退回是重新生成任务，也会调用这个接口
     * 
     * @param dto 任务信息
     * @param areaId 要下派的区域id
     * @param newresults 
     * @return 
     */
    Result createRwToArea(WorkDto dto, String areaId, String newresults);
    
    /**
     * 任务上报的接口，处理上报任务类型保存+附件保存
     * 
     * @param sbAreaId 要上报的区域id
     * @return 
     */
    Result saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap);

}
