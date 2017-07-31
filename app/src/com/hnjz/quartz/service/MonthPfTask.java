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
 * �����ɷ��������ʱ�����
 * likun
 * */

@Service
public class MonthPfTask {
	/**��־*/
    private static final Log             log = LogFactory.getLog(QuarterCheckTask.class);
	@Autowired
	TslawobjManager tslawobjManager;
	@Autowired
	DoubleRandomManager doubleRandomManager;
	
	public void check()throws Exception{
		 String strCurTime = DateUtil.getDateTime(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
	        if (log.isDebugEnabled()) {
	            log.debug("��" + strCurTime + "ʱ���¶���������ɷ�������������");
	        }
	        Date date = new Date();
		List<TBizConfigpfsj> list=tslawobjManager.quarterConfigpfsj();
		
		if(list.size()>0){
    		for (TBizConfigpfsj ele : list) {
                if(date.after(ele.getPfsj())){//��ǰʱ��������õļ����ɷ�ʱ�䣬ִ���ɷ�
                	TBizConfigpf tpf=tslawobjManager.queryTbizConfigpfById(ele.gettBizConfigpf().getId());
                	tslawobjManager.saveCheckedandPf(ele);
                	
                	//��ȡ���ɷ�
                	
                	//ele.setSfypf("Y");
                //	this.dao.save(ele);
                }
            }	
    	}
		
		
		
		
		
	}
	
}