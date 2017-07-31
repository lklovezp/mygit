/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.ApplyCommonPo;

/**
 * 代办任务的视图
 * 
 * @author sunkuo
 * @version $Id: VdbWork.java, v 0.1 2013-6-17 上午02:49:37 Administrator Exp $
 */
public class VdbWork extends ApplyCommonPo {
    /**  */
    private static final long serialVersionUID = 704921941897998400L;
    /** 当前任务所属的用户，userName */
    private String  userId;
    /** 任务备注 */
    private String  workNote;
    /** 开始时间 */
    private Date    startTime;
    /** 结束时间 */
    private Date    endTime;
    /** 陪同人编号集合 */
    private String  ptrIds;
    /** 陪同人名称集合 */
    private String  ptrNames;
    /** 执行人编号 */
    private String  zxrId;
    /** 执行人名称 */
    private String  zxrName;
    /** 执行时间 */
    private Date    zxtime;
    /** 审核人集合（逐层派发任务时将人员记录在此，倒序取出做为审核人） */
    private String  shrids;
    /** 退回的集合（逐层审核任务时将人员记录在此，倒序取出做为退回人） */
    private String  thrids;
    /** 记录人 */
    private TSysUser    jlr;
    /** 区域ID */
    private String  areaid;
    /** 区域ID */
    private String  xpCity;
    /** 上级任务ID */
    private String  sjid;
    /** 是否下派 */
    private Boolean isxp = false;
    /** 上报人ID */
    private String  sbr;
    /** 上报时间 */
    private Date    sbsj;
    /** 任务状态 */
    private String  state;

    private TSysUser    gdUser;

    private Date    gdsj;
    
///////////////////////////以下是需要添加的字段///////////////////////////
    /** 任务来源 */
	private String source;
	/** 任务密级 */
	private String security;
	/** 紧急程度 */
	private String emergency;
	
	/** 紧急程度orderby */
	private Integer orderby;
	
	/** 执法对象类型 */
    private String  zfdxType;
    
    /** 信访登记表Id */
    private String  xfdjbId;

    public String getWorkNote() {
        return workNote;
    }

    public void setWorkNote(String workNote) {
        this.workNote = workNote;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPtrIds() {
        return ptrIds;
    }

    public void setPtrIds(String ptrIds) {
        this.ptrIds = ptrIds;
    }

    public String getPtrNames() {
        return ptrNames;
    }

    public void setPtrNames(String ptrNames) {
        this.ptrNames = ptrNames;
    }

    public String getZxrId() {
        return zxrId;
    }

    public void setZxrId(String zxrId) {
        this.zxrId = zxrId;
    }

    public String getZxrName() {
        return zxrName;
    }

    public void setZxrName(String zxrName) {
        this.zxrName = zxrName;
    }

    public Date getZxtime() {
        return zxtime;
    }

    public void setZxtime(Date zxtime) {
        this.zxtime = zxtime;
    }

    public String getShrids() {
        return shrids;
    }

    public void setShrids(String shrids) {
        this.shrids = shrids;
    }

    public String getThrids() {
        return thrids;
    }

    public void setThrids(String thrids) {
        this.thrids = thrids;
    }

    public TSysUser getJlr() {
        return jlr;
    }

    public void setJlr(TSysUser jlr) {
        this.jlr = jlr;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getXpCity() {
        return xpCity;
    }

    public void setXpCity(String xpCity) {
        this.xpCity = xpCity;
    }

    public String getSjid() {
        return sjid;
    }

    public void setSjid(String sjid) {
        this.sjid = sjid;
    }

    public Boolean getIsxp() {
        return isxp;
    }

    public void setIsxp(Boolean isxp) {
        this.isxp = isxp;
    }

    public String getSbr() {
        return sbr;
    }

    public void setSbr(String sbr) {
        this.sbr = sbr;
    }

    public Date getSbsj() {
        return sbsj;
    }

    public void setSbsj(Date sbsj) {
        this.sbsj = sbsj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TSysUser getGdUser() {
        return gdUser;
    }

    public void setGdUser(TSysUser gdUser) {
        this.gdUser = gdUser;
    }

    public Date getGdsj() {
        return gdsj;
    }

    public void setGdsj(Date gdsj) {
        this.gdsj = gdsj;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public String getZfdxType() {
		return zfdxType;
	}

	public void setZfdxType(String zfdxType) {
		this.zfdxType = zfdxType;
	}

	public String getXfdjbId() {
		return xfdjbId;
	}

	public void setXfdjbId(String xfdjbId) {
		this.xfdjbId = xfdjbId;
	}
	
}
