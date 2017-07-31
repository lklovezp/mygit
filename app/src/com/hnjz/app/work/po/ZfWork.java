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

import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.ApplyCommonPo;

/**
 * ����
 * 
 * @author zn
 * @version $Id: Work.java, v 0.1 2013-1-22 ����03:18:47 zn Exp $
 */
public class ZfWork extends ApplyCommonPo {
	// Fields
	/**  */
	private static final long serialVersionUID = 1L;
	/** ����ע */
	private String workNote;
	/** ��ʼʱ�䣨�ɷ�ʱ�䣩 */
	private Date startTime;
	/** ����ʱ�� */
	private Date endTime;
	/** ִ���������� */
	private String zfdxType;
	/** ִ���˱�� */
	private String zxrId;
	/** ִ�������� */
	private String zxrName;
	/** ִ�п�ʼʱ�� */
	private Date zxStartTime;
	/** ִ��ʱ�� */
	private Date zxtime;
	/** ����˼��ϣ�����ɷ�����ʱ����Ա��¼�ڴˣ�����ȡ����Ϊ����ˣ� */
	private String shrids;
	/** �˻صļ��ϣ�����������ʱ����Ա��¼�ڴˣ�����ȡ����Ϊ�˻��ˣ� */
	private String thrids;
	/** ��¼�� */
	private TSysUser jlr;
	/** ����ID */
	private String areaid;
	/** ����ID */
	private String xpCity;
	
	/** ���ɸ���ids */
	private String xpfjIds;
	/** ������ʷ��ʾids */
	private String xplspsIds;
	
	/** �ϼ�����ID */
	private String sjid;
	/** �Ƿ����� */
	private Boolean isxp = false;
	/** �ϱ���ID */
	private String sbr;
	/** �ϱ�ʱ�� */
	private Date sbsj;
	/**
	 * ����״̬ <br>
	 * 001 ���ɷ� <br>
	 * 002 ���д����� <br>
	 * 004 �ѽ��� <br>
	 * 008 ִ���� <br>
	 * 012 ���ϱ� <br>
	 * 016 ����� <br>
	 * 017 ���˻� <br>
	 * 020 �ѹ鵵
	 */
	private String state;

	private TSysUser gdUser;

	private Date gdsj;

	// /////////////////////////��������Ҫ���ӵ��ֶ�///////////////////////////
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
	private String jsrIds;//һ�������id�����ڶ��������ʱ������ʱ����
	/** ��ʾ�����ֻ����������ʱ�ø��ֶΣ���Ϊ��������ʱ��δ�������̣��Ժ�ÿһ���Ĳ�����ʾ�������worklog�У� */
	private String psyj;
	/** �Ƿ���� */
	private String isActive;
	
	private String pid;
	
	/** �Ƿ����*/
	private String sfdb;
	/** �������Ƶ����ɸ�ʽ */
	private String rwmcgs;
	/** ������������ */
	private Date rwmcrq;
	/** �����Ӧ���� */
	private TSysOrg orgid;
	/** ������Ϣ */
	private String extinfo;
	/** ����id */
	private String flowid;
	/** ����ʵ��id */
	private String shiliid;
	/** ��ǰ���̽ڵ�id */
	private String trackid;

	/**
	 * �ֻ�����������
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
	 * �ֻ�����ȾԴ����¼
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
	 * ����XML�ĵ�����
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
		// ���Ӹ�����Ϣ
		if (files != null && files.size() > 0) {
			Element e_files = root.addElement("commfiles");
			for (WorkCommFile file : files) {
				file.addXml(e_files);
			}
		}

		if (jcInfo != null) {// �ֳ����
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

	public String getRwmcgs() {
		return rwmcgs;
	}

	public void setRwmcgs(String rwmcgs) {
		this.rwmcgs = rwmcgs;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public Date getRwmcrq() {
		return rwmcrq;
	}

	public void setRwmcrq(Date rwmcrq) {
		this.rwmcrq = rwmcrq;
	}

	public TSysOrg getOrgid() {
		return orgid;
	}

	public void setOrgid(TSysOrg orgid) {
		this.orgid = orgid;
	}

	public String getExtinfo() {
		return extinfo;
	}

	public void setExtinfo(String extinfo) {
		this.extinfo = extinfo;
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