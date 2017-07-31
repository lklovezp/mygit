/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.domain.Oper;
import com.hnjz.sys.po.RoleFuncOperV;

/**
 * ��Ȩ�����ݴ����ݿ������
 * 
 * @author wumi
 * @version $Id: SecurityData.java, v 0.1 Jan 18, 2013 5:56:00 PM wumi Exp $
 */

public class SecurityData implements InitializingBean {

    /**��־*/
    private static final Log                         log   = LogFactory.getLog(SecurityData.class);

    private Dao                                      dao;
    
    
    /**�û���Ӧ���ŵĻ��棬keyΪController.tostring,valueΪ����Id*/
    private Map<String, String>    controller         = new ConcurrentHashMap<String, String>();

    private Map<String, Collection<ConfigAttribute>> resourceMap;

    private Map<String, List<Oper>>                  opers = new ConcurrentHashMap<String, List<Oper>>();

    public List<Oper> getOper(String roleId, String fid) {
        if (StringUtils.isBlank(fid)) {
            return Collections.emptyList();
        }
        String key = roleId.concat(fid);
        List<Oper> ops = opers.get(key);
        if (null != ops && !ops.isEmpty()) {
            log.debug("�ӻ����л�ȡ�������ݣ�" + ops);
            return ops;
        }
        ops = new ArrayList<Oper>();
        String hsql = "from RoleFuncOperV where roleId in(select role.id from TSysUserRole where user.id = ?) and functionId = ? order by note desc";
        List<RoleFuncOperV> re = this.dao.find(hsql, roleId, fid);
        for (RoleFuncOperV ele : re) {
            ops.add(new Oper(ele.getNote(), ele.getOnclickEvent(), ele.getFashion()));
        }
        opers.put(key, ops);
        if (log.isDebugEnabled()) {
            log.debug("�����ݿ��л�ȡ��������:" + ops);
        }
        return ops;
    }

    public Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return resourceMap;
    }

    public void loadAllResource() {
        log.info("����Ȩ������......");
        String hsql = "from RoleFuncOperV";
        List<RoleFuncOperV> re = this.dao.find(hsql);
        resourceMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
        opers = new ConcurrentHashMap<String, List<Oper>>();
        for (RoleFuncOperV ele : re) {
            Collection<ConfigAttribute> atts = createSec(ele.getRoleId());
            if (resourceMap.containsKey(ele.getLinkAddr())) {
                resourceMap.get(ele.getLinkAddr()).addAll(atts);
            } else {
                resourceMap.put(ele.getLinkAddr(), atts);
            }
            String flink = StringUtils.substringBefore(ele.getFunLink(), "?");
            if (resourceMap.containsKey(flink)) {
                resourceMap.get(flink).addAll(atts);
            } else {
                resourceMap.put(flink, atts);
            }
            if (log.isDebugEnabled()) {
                log.debug(ele.getFunLink() + ":" + resourceMap.get(ele.getFunLink()));
                log.debug(ele.getLinkAddr() + ":" + resourceMap.get(ele.getLinkAddr()));
            }
        }
        log.info("����Ȩ������ ok......");
        opers.clear();
        log.info("����û��������� ok......");
    }

    private Collection<ConfigAttribute> createSec(String role) {
        Collection<ConfigAttribute> atts = new HashSet<ConfigAttribute>();
        atts.add(new SecurityConfig(role.trim()));
        return atts;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
    
    public void add(String key,String value){
        if(!controller.containsKey(key)){
            controller.put(key, value);
        } else {
        	controller.remove(key);
        	controller.put(key, value);
        }
    }
    
    public String getFunctionId(String key){
        return controller.get(key);
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        this.loadAllResource();
        OperateUtil.securityData = this;
    }

}