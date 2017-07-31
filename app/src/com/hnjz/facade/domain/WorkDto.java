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
 * �����ɷ��Ķ���
 * 
 * @author wumi
 * @version $Id: WorkDto.java, v 0.1 Mar 27, 2013 11:51:19 AM wumi Exp $
 */
public class WorkDto implements Serializable {

    /**  */
    private static final long serialVersionUID = -9130301561757996163L;

    /** ��� */
    private String            id;
    /**����*/
    private String            workName;
    /**������ID*/
    private String            jsrId;
    /**��ע*/
    private String            workNote;
    
    /**����ʱ��*/
    private String            createTime;
    
    /**��ʼʱ��*/
    private String            startTime;
    /**����ʱ��*/
    private String            endTime;
    /**�������� ds���ɵ����� ry���ɸ�������*/
    private String xptype;
    
    ///////////////////////////��������Ҫ���ӵ��ֶ�///////////////////////////
    /** ������Դ */
	private String source;
	/** �����ܼ� */
	private String security;
	/** �����̶� */
	private String emergency;
	/** �Ǽ���ID */
    private String djrId;
    private String djrName;
    /** ������ID */
	private String jsr;
	private String jsrName;
	/** ������IDs */
	private String jsrIds;
	private String jsrNames;
    /** ��ʾ�����ֻ����������ʱ�ø��ֶΣ���Ϊ��������ʱ��δ�������̣��Ժ�ÿһ���Ĳ�����ʾ�������worklog�У� */
    private String psyj;
    
    /** �ϼ�������Ϣ������ʱ�ã�*/
    private List<Map<String, String>> pFileInfo;
    /** �ϼ���ʷ��ʾ��Ϣ������ʱ�ã�*/
    private List<Map<String, String>> pLspsInfo;
    
    /** ������������ */
	private String rwmcrq;
	
	/** ִ���������� */
	private String zfdxType;
	
	/** ִ���������� */
	private String zfdxmc;
	
	/** ִ���������� */
	private String rwmcgs;
	
	/** �ճ��칫 */
	private String rcbg;
	
	/** �ŷ�Ͷ�� */
	private String xfts;
	
	/** �ŷñ��������� */
	private String xfbcjsrId;
	private String xfbcjsrName;
	
	/** �Ƿ����*/
	private String sfdb;
	
	/** �������Ƿ���Ҫ���� */
	private String blrsfbc;
	
	/** �ŷõǼǱ�id */
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
        
        setSecurity(work.getSecurity());//�����ܼ�
        setEmergency(work.getEmergency());//�����̶�
        if(work.getRwmcrq() !=null){
        	setRwmcrq(sdf.format(work.getRwmcrq()));//�������������ֶ�
        }
        setRwmcgs(work.getRwmcgs());//�����ʽ�ֶ�
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