package com.hnjz.app.work.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.ApplyCommonPo;

/**
 * 任务
 * 
 * @author zn
 * @version $Id: Work.java, v 0.1 2013-1-22 上午03:18:47 zn Exp $
 */
public class XfWork extends ApplyCommonPo {
	// Fields
	/**  */
	private static final long serialVersionUID = 1L;
	/** 任务内容 */
	private String workNote;
	/** 开始时间（派发时间） */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 执法对象类型 */
	private String zfdxType;
	/** 陪同人编号集合 */
	private String ptrIds;
	/** 陪同人名称集合 */
	private String ptrNames;
	/** 执行人编号 */
	private String zxrId;
	/** 执行人名称 */
	private String zxrName;
	/** 执行开始时间 */
	private Date zxStartTime;
	/** 执行时间 */
	private Date zxtime;
	/** 审核人集合（逐层派发任务时将人员记录在此，倒序取出做为审核人） */
	private String shrids;
	/** 退回的集合（逐层审核任务时将人员记录在此，倒序取出做为退回人） */
	private String thrids;
	/** 记录人 */
	private TSysUser jlr;
	/** 区域ID */
	private String areaid;
	/** 区域ID */
	private String xpCity;
	
	/** 下派附件ids */
	private String xpfjIds;
	/** 下派历史批示ids */
	private String xplspsIds;
	
	/** 上级任务ID */
	private String sjid;
	/** 是否下派 */
	private Boolean isxp = false;
	/** 上报人ID */
	private String sbr;
	/** 上报时间 */
	private Date sbsj;
	/**
	 * 任务状态 <br>
	 * 001 已派发 <br>
	 * 002 地市处理中 <br>
	 * 004 已接受 <br>
	 * 008 执行中 <br>
	 * 012 已上报 <br>
	 * 016 审核中 <br>
	 * 017 已退回 <br>
	 * 020 已归档
	 */
	private String state;

	private TSysUser gdUser;

	private Date gdsj;

	// /////////////////////////以下是需要添加的字段///////////////////////////
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
	private String jsrIds;//一组接受人id，用于多个接受人时保存临时数据
	private String jsrNames;
	/** 批示意见（只在任务生成时用该字段，因为任务生成时还未启动流程，以后每一步的操作批示都会存在worklog中） */
	private String psyj;
	/** 是否可用 */
	private String isActive;
	
	private String pid;
	
	/** 信访报出接收人 */
	private String xfbcjsrId;
	private String xfbcjsrName;
	/** 是否代办*/
	private String sfdb;
	/** 办理人是否需要报出 */
	private String blrsfbc;
	/** 任务名称的生成格式 */
	private String rwmcgs;
	/** 任务名称日期 */
	private Date rwmcrq;
	/** 执法对象名称 */
	private String zfdxmc;
	/** 记录时间 */
	private Date jlsj;
	/** 信访内容 */
	private String xfnr;
	/** 来文名称 */
	private String lwmc;
	/** 联系电话 */
	private String lxdh;
	/** 信访人 */
	private String xfr;
	/** 信访时间 */
	private Date xfsj;
	/** 办结时限 */
	private Date bjsx;
	/** 污染类型 */
	private String wrlx;
	/** 信访来源 */
	private String xfly;
	/** 信访编号 */
	private String xfbh;
	/** 污染类型其他*/
	private String wrlxqt;
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
	
	/** 辐射污染类型其他 */
	private String sthjwrqt;
	
	public String getSthjwrqt() {
		return sthjwrqt;
	}

	public void setSthjwrqt(String sthjwrqt) {
		this.sthjwrqt = sthjwrqt;
	}

	/** 企业地址 */
	private String qydz;
	/** 后续办理情况 */
	private String hjxfblqk;
	/** 办结情况 */
	private String bjqk;
	/** 流程id */
	private String flowid;
	/** 创建实例id */
	private String shiliid;
	/** 当前流程节点id */
	private String trackid;

	/**
	 * 手机端任务详情
	 * 
	 * @return
	 */
	public Map<String, Object> toMobileInfoMap() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workName", getName());
		map.put("workNote", getWorkNote() == null ? "" : getWorkNote());
		map.put("startTime", sdf.format(getStartTime()));
		map.put("endTime", sdf.format(getEndTime()));
		map.put("nextOper", StringUtils.isNotBlank(this.getNextOper()) ? this
				.getNextOper() : "");
		return map;
	}

	/**
	 * 手机端污染源检查记录
	 * 
	 * @return
	 */
	public Map<String, Object> toMobileWryInspectionRecordMap() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workId", getId());
		map.put("workName", getName());
		map.put("startTime", sdf.format(getStartTime()));
		return map;
	}

	/**
	 * 生成XML文档对象
	 * 
	 * @return
	 */
	public Document toXml(WorkJcinfo jcInfo, List<WorkCommFile> files) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(getClass().getSimpleName());
		root.addElement("id").addText(getId());
		root.addElement("name").addText(getName());
		root.addElement("note").addText(
				getWorkNote() == null ? "" : getWorkNote());
		root.addElement("starttime").addText(sdf.format(getStartTime()));
		root.addElement("endtime").addText(sdf.format(getEndTime()));
		root.addElement("ptrids").addText(
				getPtrIds() == null ? "" : getPtrIds());
		root.addElement("ptrnames").addText(
				getPtrNames() == null ? "" : getPtrNames());
		root.addElement("zxrid").addText(
				StringUtils.defaultIfBlank(getZxrId(), ""));
		root.addElement("zxrname").addText(
				StringUtils.defaultIfBlank(getZxrName(), ""));
		if(null!=getZxStartTime()){
			root.addElement("zxStartTime").addText(sdf.format(getZxStartTime()));
		}else{
			root.addElement("zxStartTime").addText("");
		}
		
		root.addElement("zxtime").addText(sdf.format(getZxtime()));
		root.addElement("jlrid").addText(
				getJlr() == null ? "" : getJlr().getId());
		root.addElement("areaid").addText(
				getAreaid() == null ? "" : getAreaid());
		root.addElement("sjid").addText(getSjid() == null ? "" : getSjid());
		root.addElement("isxp").addText(
				getIsxp() == null ? "false" : getIsxp().toString());
		// root.addElement("sbr").addText(getSbr() == null ? "" : getSbr());
		// root.addElement("sbsj").addText(sdf.format(getSbsj()));
		// 添加附件信息
		if (files != null && files.size() > 0) {
			Element e_files = root.addElement("commfiles");
			for (WorkCommFile file : files) {
				file.addXml(e_files);
			}
		}

		if (jcInfo != null) {// 现场监察
			Element e_jcinfo = root.addElement("jcinfo");
			jcInfo.addXml(e_jcinfo);
		}
		return doc;
	}

	public String getWorkNote() {
		return this.workNote;
	}

	public void setWorkNote(String workNote) {
		this.workNote = workNote;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getZfdxType() {
		return this.zfdxType;
	}

	public void setZfdxType(String zfdxType) {
		this.zfdxType = zfdxType;
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

	public String getZxrId() {
		return zxrId;
	}

	public void setZxrId(String zxrId) {
		this.zxrId = zxrId;
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

	public String getXpCity() {
		return xpCity;
	}

	public void setXpCity(String xpCity) {
		this.xpCity = xpCity;
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

	public Date getZxStartTime() {
		return zxStartTime;
	}

	public void setZxStartTime(Date zxStartTime) {
		this.zxStartTime = zxStartTime;
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
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public String getXpfjIds() {
		return xpfjIds;
	}

	public void setXpfjIds(String xpfjIds) {
		this.xpfjIds = xpfjIds;
	}

	public String getXplspsIds() {
		return xplspsIds;
	}

	public void setXplspsIds(String xplspsIds) {
		this.xplspsIds = xplspsIds;
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

	public String getRwmcgs() {
		return rwmcgs;
	}

	public void setRwmcgs(String rwmcgs) {
		this.rwmcgs = rwmcgs;
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

	public Date getRwmcrq() {
		return rwmcrq;
	}

	public void setRwmcrq(Date rwmcrq) {
		this.rwmcrq = rwmcrq;
	}

	public String getZfdxmc() {
		return zfdxmc;
	}

	public void setZfdxmc(String zfdxmc) {
		this.zfdxmc = zfdxmc;
	}

	public Date getJlsj() {
		return jlsj;
	}

	public void setJlsj(Date jlsj) {
		this.jlsj = jlsj;
	}

	public String getXfnr() {
		return xfnr;
	}

	public void setXfnr(String xfnr) {
		this.xfnr = xfnr;
	}

	public String getLwmc() {
		return lwmc;
	}

	public void setLwmc(String lwmc) {
		this.lwmc = lwmc;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getXfr() {
		return xfr;
	}

	public void setXfr(String xfr) {
		this.xfr = xfr;
	}

	public Date getXfsj() {
		return xfsj;
	}

	public void setXfsj(Date xfsj) {
		this.xfsj = xfsj;
	}

	public Date getBjsx() {
		return bjsx;
	}

	public void setBjsx(Date bjsx) {
		this.bjsx = bjsx;
	}

	public String getWrlx() {
		return wrlx;
	}

	public void setWrlx(String wrlx) {
		this.wrlx = wrlx;
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

	public String getHjxfblqk() {
		return hjxfblqk;
	}

	public void setHjxfblqk(String hjxfblqk) {
		this.hjxfblqk = hjxfblqk;
	}

	public String getBjqk() {
		return bjqk;
	}

	public void setBjqk(String bjqk) {
		this.bjqk = bjqk;
	}

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getShiliid() {
		return shiliid;
	}

	public void setShiliid(String shiliid) {
		this.shiliid = shiliid;
	}

	public String getTrackid() {
		return trackid;
	}

	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}

}