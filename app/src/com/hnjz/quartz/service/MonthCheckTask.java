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
 * ���³���������ʱ�����
 * likun
 * */
@Service("monthCheckTask")
public class MonthCheckTask {
	@Autowired
	TslawobjManager tslawobjManager;
	@Autowired
	DoubleRandomManager doubleRandomManager;
	
	public void check()throws Exception{
		//���¸���һ����ʷ��
		//��ȡ��ǰ��
		Calendar cal = Calendar.getInstance();
		int year =cal.get(Calendar.YEAR);
		//��ȡ��������������ɷ���������
		List<TBizConfigcheck> list  = doubleRandomManager.queryAllConfigCheck();
		for(TBizConfigcheck tc:list){
		 tslawobjManager.saveHistoryConfigCheck(String.valueOf(year), "", tc);
		 doubleRandomManager.saveCheckAllLawobjf(String.valueOf(year), tc.getAreaId());
		}
	}
	
	

}