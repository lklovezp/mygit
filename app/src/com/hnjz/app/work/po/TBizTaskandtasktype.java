package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKANDTASKTYPE 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:13:03 CST 2015
 * 功能描述：任务和任务类型关联
 */ 

@SuppressWarnings("serial")
public class TBizTaskandtasktype extends BaseObject {
	/** 任务id */
	private String taskid;
	/** 任务类型code */
	private String tasktypeid;
	/**检查时间止**/
	private Date jcsj1;
	/**检查时间起**/
	private Date jcsj2;
	/**检查地点**/
	private String jcdd;
	/**监察模板**/
	private String jcmb;
	/**立案登记时间**/
	private Date ladjsj;
	/**立案登记号**/
	private String ladjh;
	/**违法案件名称**/
	private String wfajmc;
	/**调查时间**/
	private Date dcsj;
	/**检查要求**/
	private String jcyq;
	/**检查目的**/
	private String jcmd;
	/**卷宗整理人**/
	private String jzzlr;
	/** 信访投诉来源 */
	private String xftsly;
	/** 监察结论 */
	private String jcjl;
	/** 日常办公备注*/
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

