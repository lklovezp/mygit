package com.hnjz.app.work.xwbl;

import java.util.Date;


/**
 * ѯ�ʱ�¼form
 * ���ߣ�xb
 * �������ڣ�Mar 31, 2015
 * ����������

 *
 */
public class XwblForm {
	/** id **/
	String id;
	/** ����ID */
	private String taskid;
	/** ѯ�ʿ�ʼʱ�� */
	private String starttime;
	/** ѯ�ʽ���ʱ�� */
	private String endtime;
	/** ѯ�ʵص� */
	private String xwdd;
	/** ��ѯ�ʵ�λ���� */
	private String bxwdwmc;
	/** ���������� */
	private String fddbr;
	/** ���������˵绰 */
	private String fddbrdh;
	/** ��ѯ�������� */
	private String bxwrxm;
	/** ��ѯ�����Ա� */
	private String bxwrxb;
	/** ��ѯ�������� */
	private String bxwrnl;
	/** ��ѯ����ְ�� */
	private String bxwrzw;
	/** ��ѯ���˵绰 */
	private String bxwrdh;
	/** ��ѯ����Ȼ������ */
	private String bxwzrrxm;
	/** ��ѯ����Ȼ���Ա� */
	private String bxwzrrxb;
	/** ��ѯ����Ȼ������ */
	private String bxwzrrnl;
	/** ��ѯ����Ȼ�˵绰 */
	private String bxwzrrdh;
	/** ��ѯ����Ȼ�����ڵ�λ */
	private String bxwzrrszdw;
	/** ��ѯ����Ȼ��סַ */
	private String bxwzrrzz;
	/** ��ѯ����Ȼ���뱾����ϵ */
	private String bxwzrrybagx;
	/** ִ����Ա��λ���� */
	private String zfrydwmc;
	/** ִ����Ա���� */
	private String zfrynames;
	/** ִ��֤�� */
	private String zfzhs;
	
	/** ��������������֤�� */
	private String fddbrsfzh;
	/** ��ѯ��������֤�� */
	private String bxwrsfzh;
	/** ��ѯ����Ȼ������֤�� */
	private String bxwzrrsfzh;
	
	/** ���ѯ�ʵĴ� */
	private String lastans;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getXwdd() {
		return xwdd;
	}
	public void setXwdd(String xwdd) {
		this.xwdd = xwdd;
	}
	public String getBxwdwmc() {
		return bxwdwmc;
	}
	public void setBxwdwmc(String bxwdwmc) {
		this.bxwdwmc = bxwdwmc;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getFddbrdh() {
		return fddbrdh;
	}
	public void setFddbrdh(String fddbrdh) {
		this.fddbrdh = fddbrdh;
	}
	public String getBxwrxm() {
		return bxwrxm;
	}
	public void setBxwrxm(String bxwrxm) {
		this.bxwrxm = bxwrxm;
	}
	public String getBxwrxb() {
		return bxwrxb;
	}
	public void setBxwrxb(String bxwrxb) {
		this.bxwrxb = bxwrxb;
	}
	public String getBxwrnl() {
		return bxwrnl;
	}
	public void setBxwrnl(String bxwrnl) {
		this.bxwrnl = bxwrnl;
	}
	public String getBxwrzw() {
		return bxwrzw;
	}
	public void setBxwrzw(String bxwrzw) {
		this.bxwrzw = bxwrzw;
	}
	public String getBxwrdh() {
		return bxwrdh;
	}
	public void setBxwrdh(String bxwrdh) {
		this.bxwrdh = bxwrdh;
	}
	public String getBxwzrrxm() {
		return bxwzrrxm;
	}
	public void setBxwzrrxm(String bxwzrrxm) {
		this.bxwzrrxm = bxwzrrxm;
	}
	public String getBxwzrrxb() {
		return bxwzrrxb;
	}
	public void setBxwzrrxb(String bxwzrrxb) {
		this.bxwzrrxb = bxwzrrxb;
	}
	public String getBxwzrrnl() {
		return bxwzrrnl;
	}
	public void setBxwzrrnl(String bxwzrrnl) {
		this.bxwzrrnl = bxwzrrnl;
	}
	public String getBxwzrrdh() {
		return bxwzrrdh;
	}
	public void setBxwzrrdh(String bxwzrrdh) {
		this.bxwzrrdh = bxwzrrdh;
	}
	public String getBxwzrrszdw() {
		return bxwzrrszdw;
	}
	public void setBxwzrrszdw(String bxwzrrszdw) {
		this.bxwzrrszdw = bxwzrrszdw;
	}
	public String getBxwzrrzz() {
		return bxwzrrzz;
	}
	public void setBxwzrrzz(String bxwzrrzz) {
		this.bxwzrrzz = bxwzrrzz;
	}
	public String getBxwzrrybagx() {
		return bxwzrrybagx;
	}
	public void setBxwzrrybagx(String bxwzrrybagx) {
		this.bxwzrrybagx = bxwzrrybagx;
	}
	public String getZfrydwmc() {
		return zfrydwmc;
	}
	public void setZfrydwmc(String zfrydwmc) {
		this.zfrydwmc = zfrydwmc;
	}
	public String getZfrynames() {
		return zfrynames;
	}
	public void setZfrynames(String zfrynames) {
		this.zfrynames = zfrynames;
	}
	public String getZfzhs() {
		return zfzhs;
	}
	public void setZfzhs(String zfzhs) {
		this.zfzhs = zfzhs;
	}
	public String getFddbrsfzh() {
		return fddbrsfzh;
	}
	public void setFddbrsfzh(String fddbrsfzh) {
		this.fddbrsfzh = fddbrsfzh;
	}
	public String getBxwrsfzh() {
		return bxwrsfzh;
	}
	public void setBxwrsfzh(String bxwrsfzh) {
		this.bxwrsfzh = bxwrsfzh;
	}
	public String getBxwzrrsfzh() {
		return bxwzrrsfzh;
	}
	public void setBxwzrrsfzh(String bxwzrrsfzh) {
		this.bxwzrrsfzh = bxwzrrsfzh;
	}
	public String getLastans() {
		return lastans;
	}
	public void setLastans(String lastans) {
		this.lastans = lastans;
	}
	
}