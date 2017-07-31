/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TBizConfigpf;
import com.hnjz.app.data.po.TBizConfigpfsj;
import com.hnjz.app.data.xxgl.tslawobj.TslawobjManager;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.quarterChecktimeSet.QuarterChecktimeSetManager;

/**
 * 季度抽查任务
 * @author shiqiuhan
 * @created 2016-3-18,下午01:33:03
 */
@Service("quarterCheckTask")
public class QuarterCheckTask{

    /**日志*/
    private static final Log             log = LogFactory.getLog(QuarterCheckTask.class);
    @Autowired
    private TslawobjManager tslawobjManager;
    @Autowired
    private QuarterCheckManager                quarterCheckManager;
    
	@Autowired
	private QuarterChecktimeSetManager quarterChecktimeSetManager;

    /**
     * 到期检测方法 由quartz定时调用
     */
    public void check() throws AppException {

        String strCurTime = DateUtil.getDateTime(new Date());// new Date()为获取当前系统时间
        if (log.isDebugEnabled()) {
            log.debug("在" + strCurTime + "时，季度抽查任务操作被触发。");
        }
        Date date = new Date();
        try {
        	List<TDataQuarterChecktimeSet> listTime = quarterChecktimeSetManager.queryAllNoexecutedTime();//所有未执行抽查的时间
        	//Calendar now = Calendar.getInstance();
        //	String year= String.valueOf(now.get(Calendar.YEAR));
        	
        	List<TBizConfigpfsj> list=tslawobjManager.quarterConfigpfsj();
        	 
        	for (TDataQuarterChecktimeSet ele : listTime) {
                if(date.after(ele.getTime())){//当前时间大于设置的季度抽查时间，执行抽查
                	//quarterCheckManager.saveCheck(ele);
                	
                	//tslawobjManager.saveStartQuarterCheck(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//tslawobjManager.saveStartQuarterChecknew(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//重构  还未验证
                	//tslawobjManager.saveStartQuarterChecknew(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//ele.setExecuted("Y");
                	//this.dao.save(ele);
                	
                }
            }
        	//派发还未重构
        	if(list.size()>0){
        		for (TBizConfigpfsj ele : list) {
                    if(date.after(ele.getPfsj())){//当前时间大于设置的季度派发时间，执行派发
                    	TBizConfigpf tpf=tslawobjManager.queryTbizConfigpfById(ele.gettBizConfigpf().getId());
                    	/*if(tpf.getType().equals("1")){
                    		tslawobjManager.saveCheckedandPf(ele);
                    	}else if(tpf.getType().equals("2")){
                    		tslawobjManager.saveSpecialAndPf(ele);
                    	}*/
                    	//抽取和派发
                    	
                    	//ele.setSfypf("Y");
                    //	this.dao.save(ele);
                    }
                }	
        	}
        	
        } catch (Exception e) {
        	log.error("按时执行季度抽查时出错：", e);
        }
    }

}
