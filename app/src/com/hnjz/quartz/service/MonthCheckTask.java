package com.hnjz.quartz.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hnjz.app.data.doublerandom.DoubleRandomManager;
import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.xxgl.tslawobj.TslawobjManager;
import com.hnjz.common.security.CtxUtil;

/**
 * 按月抽查随机任务定时器入口
 * likun
 * */
@Service("monthCheckTask")
public class MonthCheckTask {
	@Autowired
	TslawobjManager tslawobjManager;
	@Autowired
	DoubleRandomManager doubleRandomManager;
	
	public void check()throws Exception{
		//将月更新一下历史表
		//获取当前年
		Calendar cal = Calendar.getInstance();
		int year =cal.get(Calendar.YEAR);
		//获取当年所有区域的派发比例设置
		List<TBizConfigcheck> list  = doubleRandomManager.queryAllConfigCheck();
		for(TBizConfigcheck tc:list){
		 tslawobjManager.saveHistoryConfigCheck(String.valueOf(year), "", tc);
		 doubleRandomManager.saveCheckAllLawobjf(String.valueOf(year), tc.getAreaId());
		}
	}
	
	

}
