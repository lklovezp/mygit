package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysArea;

/**
 * �ŷð�ᵥʵ����
 * @author shiqiuhan
 * @created 2016-4-6,����06:27:36
 */

@SuppressWarnings("serial")
public class TBizXfbjd extends BaseObject {
	/** ID */
	private String id;
	/** ����id */
	private String rwid;
	/** ����ʱ�� */
	private Date slsj;
	/** ������ */
	private String bjqk;
	/** �����ŷð������ */
	private String hjxfblqk;
	/** �ط���ʽ */
	private String hfxs;
	/** �ط���ʽ(����) */
	private String hfxsxm;
	/** �ط���ʽ(��Ӧ����),��绰���룬�������� */
	private String hfxsdyrn;
	/** �ط��� */
	private String hfr;
	/** �ط����� */
	private Date hfrq;
	/** ������ */
	private String fhr;
	/** �������� */
	private Date fhrq;
	/** ������ */
	private String jsr;
	/** ����ʱ�� */
	private Date jssj;
	/** �������*/
	private String bcqk;
	/** ������*/
	private String bcr;
	/** ��������*/
	private Date bcrq;
	/** �������� */
	private TSysArea area;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRwid() {
		return rwid;
	}
	public void setRwid(String rwid) {
		this.rwid = rwid;
	}
	public Date getSlsj() {
		return slsj;
	}
	public void setSlsj(Date slsj) {
		this.slsj = slsj;
	}
	public String getBjqk() {
		return bjqk;
	}
	public void setBjqk(String bjqk) {
		this.bjqk = bjqk;
	}
	public String getHjxfblqk() {
		return hjxfblqk;
	}
	public void setHjxfblqk(String hjxfblqk) {
		this.hjxfblqk = hjxfblqk;
	}
	public String getHfxs() {
		return hfxs;
	}
	public void setHfxs(String hfxs) {
		this.hfxs = hfxs;
	}
	public String getHfxsxm() {
		return hfxsxm;
	}
	public void setHfxsxm(String hfxsxm) {
		this.hfxsxm = hfxsxm;
	}
	public String getHfxsdyrn() {
		return hfxsdyrn;
	}
	public void setHfxsdyrn(String hfxsdyrn) {
		this.hfxsdyrn = hfxsdyrn;
	}
	public String getHfr() {
		return hfr;
	}
	public void setHfr(String hfr) {
		this.hfr = hfr;
	}
	public Date getHfrq() {
		return hfrq;
	}
	public void setHfrq(Date hfrq) {
		this.hfrq = hfrq;
	}
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	public Date getFhrq() {
		return fhrq;
	}
	public void setFhrq(Date fhrq) {
		this.fhrq = fhrq;
	}
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public Date getJssj() {
		return jssj;
	}
	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	public String getBcqk() {
		return bcqk;
	}
	public void setBcqk(String bcqk) {
		this.bcqk = bcqk;
	}
	public String getBcr() {
		return bcr;
	}
	public void setBcr(String bcr) {
		this.bcr = bcr;
	}
	public Date getBcrq() {
		return bcrq;
	}
	public void setBcrq(Date bcrq) {
		this.bcrq = bcrq;
	}
	public TSysArea getArea() {
		return area;
	}
	public void setArea(TSysArea area) {
		this.area = area;
	}
}
