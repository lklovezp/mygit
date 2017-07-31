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
 * ���񷢲���web����ӿ�
 * 
 * @author wumi
 * @version $Id: WorkFacade.java, v 0.1 Apr 1, 2013 3:34:42 PM wumi Exp $
 */
public interface WorkFacade {
    /**
     * �������ɵĽӿڣ������˻���������������Ҳ���������ӿ�
     * 
     * @param dto ������Ϣ
     * @param areaId Ҫ���ɵ�����id
     * @param newresults 
     * @return 
     */
    Result createRwToArea(WorkDto dto, String areaId, String newresults);
    
    /**
     * �����ϱ��Ľӿڣ������ϱ��������ͱ���+��������
     * 
     * @param sbAreaId Ҫ�ϱ�������id
     * @return 
     */
    Result saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap);

}