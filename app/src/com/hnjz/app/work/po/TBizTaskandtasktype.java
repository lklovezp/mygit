package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKANDTASKTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * ����������������������͹���
 */ 

@SuppressWarnings("serial")
public class TBizTaskandtasktype extends BaseObject {
	/** ����id */
	private String taskid;
	/** ��������code */
	private String tasktypeid;
	/**���ʱ��ֹ**/
	private Date jcsj1;
	/**���ʱ����**/
	private Date jcsj2;
	/**���ص�**/
	private String jcdd;
	/**���ģ��**/
	private String jcmb;
	/**�����Ǽ�ʱ��**/
	private Date ladjsj;
	/**�����ǼǺ�**/
	private String ladjh;
	/**Υ����������**/
	private String wfajmc;
	/**����ʱ��**/
	private Date dcsj;
	/**���Ҫ��**/
	private String jcyq;
	/**���Ŀ��**/
	private String jcmd;
	/**����������**/
	private String jzzlr;
	/** �ŷ�Ͷ����Դ */
	private String xftsly;
	/** ������ */
	private String jcjl;
	/** �ճ��칫��ע*/
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTasktypeid() {
		return tasktypeid;
	}
	public void setTasktypeid(String tasktypeid) {
		this.tasktypeid = tasktypeid;
	}
	public String getJcmb() {
		return jcmb;
	}
	public void setJcmb(String jcmb) {
		this.jcmb = jcmb;
	}
	public String getLadjh() {
		return ladjh;
	}
	public void setLadjh(String ladjh) {
		this.ladjh = ladjh;
	}
	public String getWfajmc() {
		return wfajmc;
	}
	public void setWfajmc(String wfajmc) {
		this.wfajmc = wfajmc;
	}
	public String getJcyq() {
		return jcyq;
	}
	public void setJcyq(String jcyq) {
		this.jcyq = jcyq;
	}
	public String getJcmd() {
		return jcmd;
	}
	public void setJcmd(String jcmd) {
		this.jcmd = jcmd;
	}
	public String getJzzlr() {
		return jzzlr;
	}
	public void setJzzlr(String jzzlr) {
		this.jzzlr = jzzlr;
	}
	public Date getJcsj1() {
		return jcsj1;
	}
	public void setJcsj1(Date jcsj1) {
		this.jcsj1 = jcsj1;
	}
	public Date getJcsj2() {
		return jcsj2;
	}
	public void setJcsj2(Date jcsj2) {
		this.jcsj2 = jcsj2;
	}
	public String getJcdd() {
		return jcdd;
	}
	public void setJcdd(String jcdd) {
		this.jcdd = jcdd;
	}
	public Date getLadjsj() {
		return ladjsj;
	}
	public void setLadjsj(Date ladjsj) {
		this.ladjsj = ladjsj;
	}
	public Date getDcsj() {
		return dcsj;
	}
	public void setDcsj(Date dcsj) {
		this.dcsj = dcsj;
	}
	public String getXftsly() {
		return xftsly;
	}
	public void setXftsly(String xftsly) {
		this.xftsly = xftsly;
	}
	public void setJcjl(String jcjl) {
		this.jcjl = jcjl;
	}
	public String getJcjl() {
		return jcjl;
	}

}
