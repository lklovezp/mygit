package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysArea;

/**
 * �ŷõǼǱ�ʵ����
 * @author shiqiuhan
 * @created 2016-3-29,����09:33:33
 */

@SuppressWarnings("serial")
public class TBizXfdj extends BaseObject {
	/** ID */
	private String id;
	/** �ŷ���Դ */
	private String xfly;
	/** �ŷñ�� */
	private String xfbh;
	/** ��Ⱦ���� */
	private String wrlx;
	/** ��Ⱦ��������*/
	private String wrlxqt;
	/** ���ʱ�� */
	private Date bjsx;
	/** �ŷ�ʱ�� */
	private Date xfsj;
	/** �ŷ��� */
	private String xfr;
	/** ��ϵ�绰 */
	private String lxdh;
	/** �������� */
	private String lwmc;
	/** �ŷ����� */
	private String xfnr;
	/** ��¼�� */
	private String jlr;
	/** ��¼ʱ�� */
	private Date jlsj;
	/** �Ƿ���Ч */
	private String isActive;
	/** �������� */
	private TSysArea area;
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
	/** ��̬������Ⱦ�������� */
	private String sthjwrqt;
	
	public String getSthjwrqt() {
		return sthjwrqt;
	}
	public void setSthjwrqt(String sthjwrqt) {
		this.sthjwrqt = sthjwrqt;
	}
	/** ��ҵ��ַ */
	private String qydz;
	
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
	public Date getBjsx() {
		return bjsx;
	}
	public void setBjsx(Date bjsx) {
		this.bjsx = bjsx;
	}
	public Date getXfsj() {
		return xfsj;
	}
	public void setXfsj(Date xfsj) {
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
	public Date getJlsj() {
		return jlsj;
	}
	public void setJlsj(Date jlsj) {
		this.jlsj = jlsj;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public TSysArea getArea() {
		return area;
	}
	public void setArea(TSysArea area) {
		this.area = area;
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
	
}
