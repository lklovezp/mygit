/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.facade.WorkFacade;
import com.hnjz.facade.domain.Result;
import com.hnjz.facade.domain.WorkDto;

/**
 * ����Ŀͻ��˶���
 * 
 * @author wumi
 * @version $Id: WorkClient.java, v 0.1 Apr 1, 2013 4:12:01 PM wumi Exp $
 */
@Service("workClient")
public class WorkClient {

    @Autowired
    private WorkClientFactoty workClientFactoty;

    /**
     * �������ɵĽӿڣ������˻���������������Ҳ���������ӿ�
     * 
     * @param dto ������Ϣ
     * @param areaId Ҫ���ɵ�����id
     * @return 
     */
    public Result createRwToArea(WorkDto dto, String areaId, String newresults) {
        WorkFacade f = workClientFactoty.getClient(areaId);
        return f.createRwToArea(dto, areaId, newresults);
    }
    
    /**
     * �����ϱ��Ľӿڣ������ϱ��������ͱ���+��������
     * 
     * @param dto ������Ϣ
     * @param sbAreaId Ҫ�ϱ�������id
     * @return 
     */
    public Result saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) {
        WorkFacade f = workClientFactoty.getClient(sbAreaId);
        Result r=f.saveSbRwlxAndFile(sjid,sbAreaId,rwlxIds,sbFileInfo,bLspsInfo,jcjlMap,rcbgDescMap);
        return r;
    }

}