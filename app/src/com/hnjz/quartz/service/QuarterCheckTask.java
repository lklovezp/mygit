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
 * ���ȳ������
 * @author shiqiuhan
 * @created 2016-3-18,����01:33:03
 */
@Service("quarterCheckTask")
public class QuarterCheckTask{

    /**��־*/
    private static final Log             log = LogFactory.getLog(QuarterCheckTask.class);
    @Autowired
    private TslawobjManager tslawobjManager;
    @Autowired
    private QuarterCheckManager                quarterCheckManager;
    
	@Autowired
	private QuarterChecktimeSetManager quarterChecktimeSetManager;

    /**
     * ���ڼ�ⷽ�� ��quartz��ʱ����
     */
    public void check() throws AppException {

        String strCurTime = DateUtil.getDateTime(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
        if (log.isDebugEnabled()) {
            log.debug("��" + strCurTime + "ʱ�����ȳ�����������������");
        }
        Date date = new Date();
        try {
        	List<TDataQuarterChecktimeSet> listTime = quarterChecktimeSetManager.queryAllNoexecutedTime();//����δִ�г���ʱ��
        	//Calendar now = Calendar.getInstance();
        //	String year= String.valueOf(now.get(Calendar.YEAR));
        	
        	List<TBizConfigpfsj> list=tslawobjManager.quarterConfigpfsj();
        	 
        	for (TDataQuarterChecktimeSet ele : listTime) {
                if(date.after(ele.getTime())){//��ǰʱ��������õļ��ȳ��ʱ�䣬ִ�г��
                	//quarterCheckManager.saveCheck(ele);
                	
                	//tslawobjManager.saveStartQuarterCheck(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//tslawobjManager.saveStartQuarterChecknew(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//�ع�  ��δ��֤
                	//tslawobjManager.saveStartQuarterChecknew(ele.getYear(), ele.getQuarter(), ele.getArea().getId(),ele);
                	//ele.setExecuted("Y");
                	//this.dao.save(ele);
                	
                }
            }
        	//�ɷ���δ�ع�
        	if(list.size()>0){
        		for (TBizConfigpfsj ele : list) {
                    if(date.after(ele.getPfsj())){//��ǰʱ��������õļ����ɷ�ʱ�䣬ִ���ɷ�
                    	TBizConfigpf tpf=tslawobjManager.queryTbizConfigpfById(ele.gettBizConfigpf().getId());
                    	/*if(tpf.getType().equals("1")){
                    		tslawobjManager.saveCheckedandPf(ele);
                    	}else if(tpf.getType().equals("2")){
                    		tslawobjManager.saveSpecialAndPf(ele);
                    	}*/
                    	//��ȡ���ɷ�
                    	
                    	//ele.setSfypf("Y");
                    //	this.dao.save(ele);
                    }
                }	
        	}
        	
        } catch (Exception e) {
        	log.error("��ʱִ�м��ȳ��ʱ������", e);
        }
    }

}