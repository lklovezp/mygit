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
 * ���ȳ�鶨ʱ����ʵ����
 * @author shiqiuhan
 * @created 2016-3-17,����09:11:48
 */
@Service("quarterCheckManager")
public class QuarterCheckManager extends ManagerImpl {

	/** ��־ */
	private static final Log log = LogFactory.getLog(QuarterCheckManager.class);
	
	@Autowired
	private SpotCheckManager spotCheckManager;
	
	/**
	 * �����õļ��ȳ��ʱ�䵽��ִ�м��ȳ��
	 * 
	 * @return
	 */
	public void saveCheck(TDataQuarterChecktimeSet quarterChecktimeSet) {
        try {
        	
        		if(StringUtils.isNotBlank(quarterChecktimeSet.getArea().getId())){
        			
        			 spotCheckManager.startSpotCheck(quarterChecktimeSet.getYear(), quarterChecktimeSet.getQuarter(),quarterChecktimeSet.getArea().getId());//��ѡ��ҵ
                     spotCheckManager.createWork(quarterChecktimeSet.getYear(), quarterChecktimeSet.getQuarter());//��������
                     quarterChecktimeSet.setExecuted("Y");
                     this.dao.save(quarterChecktimeSet);
        		}
        } catch (Exception e) {
            log.error("ִ�м��ȳ��ʱ������", e);
        }
	}

}