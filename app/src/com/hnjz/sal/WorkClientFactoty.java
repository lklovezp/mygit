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
 * 对发布的任务web服务进行调用的客户端
 * 
 * @author wumi
 * @version $Id: WorkClient.java, v 0.1 Apr 1, 2013 3:36:12 PM wumi Exp $
 */
@org.springframework.stereotype.Service("workClientFactoty")
public class WorkClientFactoty implements InitializingBean {

    /**日志*/
    private static final Log        log = LogFactory.getLog(WorkClientFactoty.class);

    @Autowired
    private Dao                     dao;

    /**key 为区域对应的Id;value为对应的接口实现*/
    private Map<String, WorkFacade> fs  = new ConcurrentHashMap<String, WorkFacade>();

    /**
     * 获取区域所属的服务器的客户端对象
     * 
     * @param areaId 区域Id
     * @return 区域所属的服务器的客户端对象
     */
    public WorkFacade getClient(String areaId) {
        WorkFacade f = fs.get(areaId);
        Assert.notNull(f);
        return f;
    }

    /**
     * 根据ip创建客户端
     * 
     * @param ip地址
     * @return  客户端对象 
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
     * 生成所有区域的客户端对象
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
                    log.debug(ele.getName() + ":" + s.getUrl() + "生成客户端对象");
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
