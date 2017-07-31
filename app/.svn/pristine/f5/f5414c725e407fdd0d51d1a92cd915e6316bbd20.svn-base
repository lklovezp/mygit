/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.facade.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hnjz.app.work.po.Work;

/**
 * 任务派发的对象
 * 
 * @author wumi
 * @version $Id: WorkDto.java, v 0.1 Mar 27, 2013 11:51:19 AM wumi Exp $
 */
public class WorkDto implements Serializable {

    /**  */
    private static final long serialVersionUID = -9130301561757996163L;

    /** 编号 */
    private String            id;
    /**名称*/
    private String            workName;
    /**接收人ID*/
    private String            jsrId;
    /**备注*/
    private String            workNote;
    
    /**创建时间*/
    private String            createTime;
    
    /**开始时间*/
    private String            startTime;
    /**结束时间*/
    private String            endTime;
    /**下派类型 ds下派到地市 ry下派给接收人*/
    private String xptype;
    
    ///////////////////////////以下是需要添加的字段///////////////////////////
    /** 任务来源 */
	private String source;
	/** 任务密级 */
	private String security;
	/** 紧急程度 */
	private String emergency;
	/** 登记人ID */
    private String djrId;
    private String djrName;
    /** 接受人ID */
	private String jsr;
	private String jsrName;
	/** 接受人IDs */
	private String jsrIds;
	private String jsrNames;
    /** 批示意见（只在任务生成时用该字段，因为任务生成时还未启动流程，以后每一步的操作批示都会存在worklog中） */
    private String psyj;
    
    /** 上级附件信息（下派时用）*/
    private List<Map<String, String>> pFileInfo;
    /** 上级历史批示信息（下派时用）*/
    private List<Map<String, String>> pLspsInfo;
    
    /** 任务名称日期 */
	private String rwmcrq;
	
	/** 执法对象类型 */
	private String zfdxType;
	
	/** 执法对象名称 */
	private String zfdxmc;
	
	/** 执法对象名称 */
	private String rwmcgs;
	
	/** 日常办公 */
	private String rcbg;
	
	/** 信访投诉 */
	private String xfts;
	
	/** 信访报出接收人 */
	private String xfbcjsrId;
	private String xfbcjsrName;
	
	/** 是否代办*/
	private String sfdb;
	
	/** 办理人是否需要报出 */
	private String blrsfbc;
	
	/** 信访登记表id */
	private String xfdjbId;

    public WorkDto() {
    }

    public WorkDto(Work work) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setId(work.getId());
        setWorkName(work.getName());
        /*setWorkType(work.getWorkType().getId());*/
        setWorkNote(work.getWorkNote());
        
        setCreateTime(sdf.format(work.getCreateTime()));
        
        setStartTime(sdf.format(work.getStartTime()));
        setEndTime(sdf.format(work.getEndTime()));
        
        setSecurity(work.getSecurity());//任务密级
        setEmergency(work.getEmergency());//紧急程度
        if(work.getRwmcrq() !=null){
        	setRwmcrq(sdf.format(work.getRwmcrq()));//任务名称日期字段
        }
        setRwmcgs(work.getRwmcgs());//任务格式字段
    }

    public String getRwmcgs() {
		return rwmcgs;
	}

	public void setRwmcgs(String rwmcgs) {
		this.rwmcgs = rwmcgs;
	}

	public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getJsrId() {
        return jsrId;
    }

    public void setJsrId(String jsrId) {
        this.jsrId = jsrId;
    }

    public String getWorkNote() {
        return workNote;
    }

    public void setWorkNote(String workNote) {
        this.workNote = workNote;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getXptype() {
        return xptype;
    }

    public void setXptype(String xptype) {
        this.xptype = xptype;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getDjrId() {
		return djrId;
	}

	public void setDjrId(String djrId) {
		this.djrId = djrId;
	}

	public String getDjrName() {
		return djrName;
	}

	public void setDjrName(String djrName) {
		this.djrName = djrName;
	}

	public String getPsyj() {
		return psyj;
	}

	public void setPsyj(String psyj) {
		this.psyj = psyj;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsrName() {
		return jsrName;
	}

	public void setJsrName(String jsrName) {
		this.jsrName = jsrName;
	}

	public List<Map<String, String>> getPFileInfo() {
		return pFileInfo;
	}

	public void setPFileInfo(List<Map<String, String>> fileInfo) {
		pFileInfo = fileInfo;
	}

	public List<Map<String, String>> getPLspsInfo() {
		return pLspsInfo;
	}

	public void setPLspsInfo(List<Map<String, String>> lspsInfo) {
		pLspsInfo = lspsInfo;
	}

	public String getJsrIds() {
		return jsrIds;
	}

	public void setJsrIds(String jsrIds) {
		this.jsrIds = jsrIds;
	}

	public String getJsrNames() {
		return jsrNames;
	}

	public void setJsrNames(String jsrNames) {
		this.jsrNames = jsrNames;
	}

	public String getRwmcrq() {
		return rwmcrq;
	}

	public void setRwmcrq(String rwmcrq) {
		this.rwmcrq = rwmcrq;
	}

	public String getZfdxType() {
		return zfdxType;
	}

	public void setZfdxType(String zfdxType) {
		this.zfdxType = zfdxType;
	}

	public String getZfdxmc() {
		return zfdxmc;
	}

	public void setZfdxmc(String zfdxmc) {
		this.zfdxmc = zfdxmc;
	}

	public String getRcbg() {
		return rcbg;
	}

	public void setRcbg(String rcbg) {
		this.rcbg = rcbg;
	}

	public String getXfts() {
		return xfts;
	}

	public void setXfts(String xfts) {
		this.xfts = xfts;
	}

	public String getXfbcjsrId() {
		return xfbcjsrId;
	}

	public void setXfbcjsrId(String xfbcjsrId) {
		this.xfbcjsrId = xfbcjsrId;
	}

	public String getXfbcjsrName() {
		return xfbcjsrName;
	}

	public void setXfbcjsrName(String xfbcjsrName) {
		this.xfbcjsrName = xfbcjsrName;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getBlrsfbc() {
		return blrsfbc;
	}

	public void setBlrsfbc(String blrsfbc) {
		this.blrsfbc = blrsfbc;
	}

	public String getXfdjbId() {
		return xfdjbId;
	}

	public void setXfdjbId(String xfdjbId) {
		this.xfdjbId = xfdjbId;
	}

}
