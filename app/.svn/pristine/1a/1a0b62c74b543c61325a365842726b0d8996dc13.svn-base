/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.po.VdbWork;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.util.DateUtil;
import com.hnjz.quartz.po.Sms;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 发送通知和短信的Manager
 * 
 * @author wumi
 * @version $Id: GotoneManager.java, v 0.1 2013-9-12 上午10:00:05 wumi Exp $
 */
@Service("gotoneManager")
public class GotoneManager extends ManagerImpl {

    /**日志*/
    private static final Log log = LogFactory.getLog(GotoneManager.class);

    @Autowired
    private UserManager      userManager;
    
    @Value("#{settings['GOTONE']}")
    private String           gotone;    

    /**
     * 保存逾期的通知。目前是短信和推送都通知
     * 
     * @param userName 用户登录名
     * @param ele 代办任务对象
     */
    public void saveYuqi(String userName, VdbWork ele) {
        TSysUser userObj = this.userManager.getUserByLoginName(userName);
        if (null == userObj) {
            if (log.isDebugEnabled()) {
                log.debug("用户为空：" + userName);
            }
            return;
        }
        //通知内容
        StringBuilder s = new StringBuilder();
        s.append(ele.getNextOper());
        s.append("您好，您负责的任务“").append(ele.getName());
        s.append("”将于").append(DateUtil.getDate(ele.getEndTime()));
        s.append("到期，请尽快处理。谢谢。");
        this.saveAll(userObj, s.toString());
    }
    
    /**
     * 任务派发、转派、上报移交、审核等工作中
     * @param userId 用户ID
     * @param note 操作内容
     */
    public void saveWorkStep(String userId, String note) {
    	TSysUser user = (TSysUser) this.dao.get(TSysUser.class, userId);
    	if(null == user) {
    		if (log.isDebugEnabled()) {
                log.debug("用户为空：" + userId);
            }
    		return;
    	}
    	this.saveAll(user, note);
    }
    
    /**
     * 保存用户通知内容，根据系统配置，通知或者短信
     * 
     * @param userObj 用户信息
     * @param note 要发送的内容
     */
    private void saveAll(TSysUser userObj, String note){
        if(StringUtils.equals(gotone, "SMS")){
            this.saveSms(userObj, note);
        }
        if(StringUtils.equals(gotone, "NOTIFY")){
        }
    }

    /**
     * 保存短信
     * 
     * @param userName 用户登录名
     * @param note 短信内容
     */
    private void saveSms(TSysUser userObj, String note) {
        if (StringUtils.isBlank(userObj.getWorkmobile())
            && StringUtils.isBlank(userObj.getPersonmobile())) {
            if (log.isDebugEnabled()) {
                log.debug("手机号为空："+userObj.getUsername());
            }
            return;
        }
        String hsql = "from Sms where jsrPhone = ? and smsContent = ?";
        List<Sms> re = this.dao.find(hsql, userObj.getId(), note);
        if (re.isEmpty()) {
            Date curDate = new Date();
            // 构造待发送短信
            Sms sms = new Sms();
            // 为接收人姓名、接收人电话赋值，并编辑待发送短信内容
            sms.setJsrName(userObj.getName());
            sms.setSmsContent(note);
            if (StringUtils.isNotBlank(userObj.getWorkmobile())) {
                sms.setJsrPhone(userObj.getWorkmobile());
            } else if (StringUtils.isNotBlank(userObj.getPersonmobile())) {
                sms.setJsrPhone(userObj.getPersonmobile());
            }
            sms.setUploadTime(curDate);
            this.dao.save(sms);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("短信:" + note + "已经插入过");
            }
        }
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
