package com.hnjz.quartz.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.doublerandom.DoubleRandomManager;
import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizConfigpf;
import com.hnjz.app.data.po.TBizConfigpfsj;
import com.hnjz.app.data.xxgl.tslawobj.TslawobjManager;
import com.hnjz.common.util.DateUtil;

/**
 * 按月派发随机任务定时器入口
 * likun
 * */

@Service
public class MonthPfTask {
	/**日志*/
    private static final Log             log = LogFactory.getLog(QuarterCheckTask.class);
	@Autowired
	TslawobjManager tslawobjManager;
	@Autowired
	DoubleRandomManager doubleRandomManager;
	
	public void check()throws Exception{
		 String strCurTime = DateUtil.getDateTime(new Date());// new Date()为获取当前系统时间
	        if (log.isDebugEnabled()) {
	            log.debug("在" + strCurTime + "时，月度随机任务派发操作被触发。");
	        }
	        Date date = new Date();
		List<TBizConfigpfsj> list=tslawobjManager.quarterConfigpfsj();
		
		if(list.size()>0){
    		for (TBizConfigpfsj ele : list) {
                if(date.after(ele.getPfsj())){//当前时间大于设置的季度派发时间，执行派发
                	TBizConfigpf tpf=tslawobjManager.queryTbizConfigpfById(ele.gettBizConfigpf().getId());
                	tslawobjManager.saveCheckedandPf(ele);
                	
                	//抽取和派发
                	
                	//ele.setSfypf("Y");
                //	this.dao.save(ele);
                }
            }	
    	}
		
		
		
		
		
	}
	
}
