/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.hjgl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.util.StringUtil;

/**
 * 痕迹管理的查询界面
 * 作者：xb
 * 生成日期：Mar 24, 2015
 * 功能描述：

 *
 */
@Service("hjglManagerImpl")
public class HjglManagerImpl extends ManagerImpl implements HjglManager {

    /**
     * 获取当前任务的痕迹(操作记录)
     * 
     * @param rwid 任务id
     * @return
     */
    public List<Vhjgl> getRwhj(String rwid) {
        String hsql = "from Vhjgl where work = ? order by czsj";
        List<Vhjgl> re = this.dao.find(hsql, rwid);
        return re;
    }
    
    /**
     * 获取当前任务的痕迹(派发转派操作记录)
     * 
     * @param rwid 任务id
     * @return
     */
    public List<Vhjgl> getRwhj_pfzp(String rwid) {
    	String[] operateTypeArr={WorkLogType.PF.getCode(),WorkLogType.ZF.getCode(),WorkLogType.XP.getCode()};
        String hsql = "from Vhjgl where work = ? and operateType in("+StringUtil.convertSqlInArray(operateTypeArr)+")  order by czsj";
        List<Vhjgl> re = this.dao.find(hsql, rwid);
        return re;
    }

}
