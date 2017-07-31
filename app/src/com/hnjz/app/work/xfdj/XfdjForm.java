/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.work.xfdj;

/**
 * 信访登记表单对象
 * @author shiqiuhan
 * @created 2016-3-31,上午08:40:01
 */
public class XfdjForm {
	/** 主键 */
	private String id;
	/** 信访来源 */
	private String xfly;
	/** 信访编号 */
	private String xfbh;
	/** 污染类型 */
	private String wrlx;
	/** 污染类型其他 */
	private String wrlxqt;
	/** 办结时限 */
	private String bjsx;
	/** 信访时间 */
	private String xfsj;
	/** 信访人 */
	private String xfr;
	/** 联系电话 */
	private String lxdh;
	/** 来文名称 */
	private String lwmc;
	/** 信访内容 */
	private String xfnr;
	/** 记录人 */
	private String jlr;
	/** 记录时间 */
	private String jlsj;
	/** 是否有效 */
	private String isActive;
	/** 隶属区域 */
	private String areaid;
	
	/** work表中需要的xfdjId */
	private String xfdjId;
	/** work表中需要的执法对象类型 */
	private String zfdxType;
	/** work表中需要的执法对象Id */
	private String zfdxId;
	/** work表中需要的执法对象名称 */
	private String zfdxmc;
	/** work表中需要的接收人id */
	private String jsrId;
	/** work表中需要的接受人名称 */
	private String jsrName;
	/** work表中需要的批示意见（支队领导意见） */
	private String psyj;
	
	/** 水污染类型其他 */
	private String swrqt;
	/** 大气污染类型其他 */
	private String dqwrqt;
	/** 噪声污染类型其他 */
	private String zswrqt;
	/** 固废污染类型其他 */
	private String gfwrqt;
	/** 辐射污染类型其他 */
	private String fswrqt;
	/** 生态环境污染类型其他 */
	private String sthjwrqt;
	

	/** 企业地址 */
	private String qydz;
	/** 附件pid */
	private String applyId;

	public String getSthjwrqt() {
		return sthjwrqt;
	}

	public void setSthjwrqt(String sthjwrqt) {
		this.sthjwrqt = sthjwrqt;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXfly() {
		return xfly;
	}

	public void setXfly(String xfly) {
		this.xfly = xfly;
	}

	public String getXfbh() {
		return xfbh;
	}

	public void setXfbh(String xfbh) {
		this.xfbh = xfbh;
	}

	public String getWrlx() {
		return wrlx;
	}

	public void setWrlx(String wrlx) {
		this.wrlx = wrlx;
	}

	public String getBjsx() {
		return bjsx;
	}

	public void setBjsx(String bjsx) {
		this.bjsx = bjsx;
	}

	public String getXfsj() {
		return xfsj;
	}

	public void setXfsj(String xfsj) {
		this.xfsj = xfsj;
	}

	public String getXfr() {
		return xfr;
	}

	public void setXfr(String xfr) {
		this.xfr = xfr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLwmc() {
		return lwmc;
	}

	public void setLwmc(String lwmc) {
		this.lwmc = lwmc;
	}

	public String getXfnr() {
		return xfnr;
	}

	public void setXfnr(String xfnr) {
		this.xfnr = xfnr;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getJlsj() {
		return jlsj;
	}

	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getWrlxqt() {
		return wrlxqt;
	}

	public void setWrlxqt(String wrlxqt) {
		this.wrlxqt = wrlxqt;
	}

	public String getSwrqt() {
		return swrqt;
	}

	public void setSwrqt(String swrqt) {
		this.swrqt = swrqt;
	}

	public String getDqwrqt() {
		return dqwrqt;
	}

	public void setDqwrqt(String dqwrqt) {
		this.dqwrqt = dqwrqt;
	}

	public String getZswrqt() {
		return zswrqt;
	}

	public void setZswrqt(String zswrqt) {
		this.zswrqt = zswrqt;
	}

	public String getGfwrqt() {
		return gfwrqt;
	}

	public void setGfwrqt(String gfwrqt) {
		this.gfwrqt = gfwrqt;
	}

	public String getFswrqt() {
		return fswrqt;
	}

	public void setFswrqt(String fswrqt) {
		this.fswrqt = fswrqt;
	}

	public String getQydz() {
		return qydz;
	}

	public void setQydz(String qydz) {
		this.qydz = qydz;
	}

	public String getXfdjId() {
		return xfdjId;
	}

	public void setXfdjId(String xfdjId) {
		this.xfdjId = xfdjId;
	}

	public String getZfdxType() {
		return zfdxType;
	}

	public void setZfdxType(String zfdxType) {
		this.zfdxType = zfdxType;
	}

	public String getZfdxId() {
		return zfdxId;
	}

	public void setZfdxId(String zfdxId) {
		this.zfdxId = zfdxId;
	}

	public String getZfdxmc() {
		return zfdxmc;
	}

	public void setZfdxmc(String zfdxmc) {
		this.zfdxmc = zfdxmc;
	}

	public String getJsrId() {
		return jsrId;
	}

	public void setJsrId(String jsrId) {
		this.jsrId = jsrId;
	}

	public String getJsrName() {
		return jsrName;
	}

	public void setJsrName(String jsrName) {
		this.jsrName = jsrName;
	}

	public String getPsyj() {
		return psyj;
	}

	public void setPsyj(String psyj) {
		this.psyj = psyj;
	}
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
}
