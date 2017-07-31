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
 * ����
 * 
 * @author zn
 * @version $Id: Work.java, v 0.1 2013-1-22 ����03:18:47 zn Exp $
 */
public class XfWork extends ApplyCommonPo {
	// Fields
	/**  */
	private static final long serialVersionUID = 1L;
	/** �������� */
	private String workNote;
	/** ��ʼʱ�䣨�ɷ�ʱ�䣩 */
	private Date startTime;
	/** ����ʱ�� */
	private Date endTime;
	/** ִ���������� */
	private String zfdxType;
	/** ��ͬ�˱�ż��� */
	private String ptrIds;
	/** ��ͬ�����Ƽ��� */
	private String ptrNames;
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
	private String jsrIds;//һ�������id�����ڶ��������ʱ������ʱ����
	private String jsrNames;
	/** ��ʾ�����ֻ����������ʱ�ø��ֶΣ���Ϊ��������ʱ��δ�������̣��Ժ�ÿһ���Ĳ�����ʾ�������worklog�У� */
	private String psyj;
	/** �Ƿ���� */
	private String isActive;
	
	private String pid;
	
	/** �ŷñ��������� */
	private String xfbcjsrId;
	private String xfbcjsrName;
	/** �Ƿ����*/
	private String sfdb;
	/** �������Ƿ���Ҫ���� */
	private String blrsfbc;
	/** �������Ƶ����ɸ�ʽ */
	private String rwmcgs;
	/** ������������ */
	private Date rwmcrq;
	/** ִ���������� */
	private String zfdxmc;
	/** ��¼ʱ�� */
	private Date jlsj;
	/** �ŷ����� */
	private String xfnr;
	/** �������� */
	private String lwmc;
	/** ��ϵ�绰 */
	private String lxdh;
	/** �ŷ��� */
	private String xfr;
	/** �ŷ�ʱ�� */
	private Date xfsj;
	/** ���ʱ�� */
	private Date bjsx;
	/** ��Ⱦ���� */
	private String wrlx;
	/** �ŷ���Դ */
	private String xfly;
	/** �ŷñ�� */
	private String xfbh;
	/** ��Ⱦ��������*/
	private String wrlxqt;
	/** ˮ��Ⱦ�������� */
	private String swrqt;
	/** ������Ⱦ�������� */
	private String dqwrqt;
	/** ������Ⱦ�������� */
	private String zswrqt;
	/** �̷���Ⱦ�������� */
	private String gfwrqt;
	/** ������Ⱦ�������� */
	private String fswrqt;
	
	/** ������Ⱦ�������� */
	private String sthjwrqt;
	
	public String getSthjwrqt() {
		return sthjwrqt;
	}

	public void setSthjwrqt(String sthjwrqt) {
		this.sthjwrqt = sthjwrqt;
	}

	/** ��ҵ��ַ */
	private String qydz;
	/** ����������� */
	private String hjxfblqk;
	/** ������ */
	private String bjqk;
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