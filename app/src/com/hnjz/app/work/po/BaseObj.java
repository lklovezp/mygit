/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.app.work.po;

import java.io.Serializable;
import java.util.Date;

import com.hnjz.sys.po.TSysUser;

/**
 * ������po����
 * 
 * @author wumi
 * @version $Id: BaserPo.java, v 0.1 Jun 20, 2012 5:18:54 PM wumi Exp $
 */
public class BaseObj implements Serializable {

    /**  */
    private static final long serialVersionUID = -8763130179116786580L;

    /**�汾��*/
    private Integer           version;
    /**�Ƿ���Ч*/
    private String            isActive;
    /**����������*/
    private TSysUser              creater;
    /**�޸�������*/
    private TSysUser              updateby;
    /**����ʱ��*/
    private Date              updateTime;
    /**�޸�ʱ��*/
    private Date              createTime;

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public TSysUser getCreater() {
        return creater;
    }

    public void setCreater(TSysUser creater) {
        this.creater = creater;
    }

    public TSysUser getUpdateby() {
        return updateby;
    }

    public void setUpdateby(TSysUser updateby) {
        this.updateby = updateby;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}