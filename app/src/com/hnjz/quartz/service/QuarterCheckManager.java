/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.spotCheck.SpotCheckManager;

/**
 * 季度抽查定时任务实现类
 * @author shiqiuhan
 * @created 2016-3-17,下午09:11:48
 */
@Service("quarterCheckManager")
public class QuarterCheckManager extends ManagerImpl {

	/** 日志 */
	private static final Log log = LogFactory.getLog(QuarterCheckManager.class);
	
	@Autowired
	private SpotCheckManager spotCheckManager;
	
	/**
	 * 按设置的季度抽查时间到期执行季度抽查
	 * 
	 * @return
	 */
	public void saveCheck(TDataQuarterChecktimeSet quarterChecktimeSet) {
        try {
        	
        		if(StringUtils.isNotBlank(quarterChecktimeSet.getArea().getId())){
        			
        			 spotCheckManager.startSpotCheck(quarterChecktimeSet.getYear(), quarterChecktimeSet.getQuarter(),quarterChecktimeSet.getArea().getId());//抽选企业
                     spotCheckManager.createWork(quarterChecktimeSet.getYear(), quarterChecktimeSet.getQuarter());//生成任务
                     quarterChecktimeSet.setExecuted("Y");
                     this.dao.save(quarterChecktimeSet);
        		}
        } catch (Exception e) {
            log.error("执行季度抽查时出错：", e);
        }
	}

}
