/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.ApplyCommonPo;

/**
 * �����������ͼ
 * 
 * @author sunkuo
 * @version $Id: VdbWork.java, v 0.1 2013-6-17 ����02:49:37 Administrator Exp $
 */
public class VdbWork extends ApplyCommonPo {
    /**  */
    private static final long serialVersionUID = 704921941897998400L;
    /** ��ǰ�����������û���userName */
    private String  userId;
    /** ����ע */
    private String  workNote;
    /** ��ʼʱ�� */
    private Date    startTime;
    /** ����ʱ�� */
    private Date    endTime;
    /** ��ͬ�˱�ż��� */
    private String  ptrIds;
    /** ��ͬ�����Ƽ��� */
    private String  ptrNames;
    /** ִ���˱�� */
    private String  zxrId;
    /** ִ�������� */
    private String  zxrName;
    /** ִ��ʱ�� */
    private Date    zxtime;
    /** ����˼��ϣ�����ɷ�����ʱ����Ա��¼�ڴˣ�����ȡ����Ϊ����ˣ� */
    private String  shrids;
    /** �˻صļ��ϣ�����������ʱ����Ա��¼�ڴˣ�����ȡ����Ϊ�˻��ˣ� */
    private String  thrids;
    /** ��¼�� */
    private TSysUser    jlr;
    /** ����ID */
    private String  areaid;
    /** ����ID */
    private String  xpCity;
    /** �ϼ�����ID */
    private String  sjid;
    /** �Ƿ����� */
    private Boolean isxp = false;
    /** �ϱ���ID */
    private String  sbr;
    /** �ϱ�ʱ�� */
    private Date    sbsj;
    /** ����״̬ */
    private String  state;

    private TSysUser    gdUser;

    private Date    gdsj;
    
///////////////////////////��������Ҫ���ӵ��ֶ�///////////////////////////
    /** ������Դ */
	private String source;
	/** �����ܼ� */
	private String security;
	/** �����̶� */
	private String emergency;
	
	/** �����̶�orderby */
	private Integer orderby;
	
	/** ִ���������� */
    private String  zfdxType;
    
    /** �ŷõǼǱ�Id */
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