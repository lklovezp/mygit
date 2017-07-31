/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sal;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.hnjz.common.dao.Dao;
import com.hnjz.facade.WorkFacade;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysServer;

/**
 * �Է���������web������е��õĿͻ���
 * 
 * @author wumi
 * @version $Id: WorkClient.java, v 0.1 Apr 1, 2013 3:36:12 PM wumi Exp $
 */
@org.springframework.stereotype.Service("workClientFactoty")
public class WorkClientFactoty implements InitializingBean {

    /**��־*/
    private static final Log        log = LogFactory.getLog(WorkClientFactoty.class);

    @Autowired
    private Dao                     dao;

    /**key Ϊ�����Ӧ��Id;valueΪ��Ӧ�Ľӿ�ʵ��*/
    private Map<String, WorkFacade> fs  = new ConcurrentHashMap<String, WorkFacade>();

    /**
     * ��ȡ���������ķ������Ŀͻ��˶���
     * 
     * @param areaId ����Id
     * @return ���������ķ������Ŀͻ��˶���
     */
    public WorkFacade getClient(String areaId) {
        WorkFacade f = fs.get(areaId);
        Assert.notNull(f);
        return f;
    }

    /**
     * ����ip�����ͻ���
     * 
     * @param ip��ַ
     * @return  �ͻ��˶��� 
     * @throws MalformedURLException
     */
    private WorkFacade getClient_(String ip) throws MalformedURLException {
        String url = ip.concat("/services/workFacade");
        Service serviceModel = new ObjectServiceFactory().create(WorkFacade.class);
        XFire xfire = XFireFactory.newInstance().getXFire();
        XFireProxyFactory factory = new XFireProxyFactory(xfire);
        WorkFacade client = (WorkFacade) factory.create(serviceModel, url);
        return client;
    }

    /**
     * ������������Ŀͻ��˶���
     * 
     * @throws MalformedURLException
     */
    public void ref()  {
        try {
            String hsql = "from TSysArea where isActive = 'Y' ";
            List<TSysArea> as = this.dao.find(hsql);
            for (TSysArea ele : as) {
                TSysServer s = (TSysServer) this.dao.get(TSysServer.class, ele.getServer().getId());
                WorkFacade f = this.getClient_(s.getUrl());
                if (log.isDebugEnabled()) {
                    log.debug(ele.getName() + ":" + s.getUrl() + "���ɿͻ��˶���");
                }
                fs.put(ele.getId(), f);
            }
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        ref();
    }

}