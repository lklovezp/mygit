package com.hnjz.app.work.xwbl;

import java.util.Date;


/**
 * 询问笔录form
 * 作者：xb
 * 生成日期：Mar 31, 2015
 * 功能描述：

 *
 */
public class XwblForm {
	/** id **/
	String id;
	/** 任务ID */
	private String taskid;
	/** 询问开始时间 */
	private String starttime;
	/** 询问结束时间 */
	private String endtime;
	/** 询问地点 */
	private String xwdd;
	/** 被询问单位名称 */
	private String bxwdwmc;
	/** 法定代表人 */
	private String fddbr;
	/** 法定代表人电话 */
	private String fddbrdh;
	/** 被询问人姓名 */
	private String bxwrxm;
	/** 被询问人性别 */
	private String bxwrxb;
	/** 被询问人年龄 */
	private String bxwrnl;
	/** 被询问人职务 */
	private String bxwrzw;
	/** 被询问人电话 */
	private String bxwrdh;
	/** 被询问自然人姓名 */
	private String bxwzrrxm;
	/** 被询问自然人性别 */
	private String bxwzrrxb;
	/** 被询问自然人年龄 */
	private String bxwzrrnl;
	/** 被询问自然人电话 */
	private String bxwzrrdh;
	/** 被询问自然人所在单位 */
	private String bxwzrrszdw;
	/** 被询问自然人住址 */
	private String bxwzrrzz;
	/** 被询问自然人与本案关系 */
	private String bxwzrrybagx;
	/** 执法人员单位名称 */
	private String zfrydwmc;
	/** 执法人员姓名 */
	private String zfrynames;
	/** 执法证号 */
	private String zfzhs;
	
	/** 法定代表人身份证号 */
	private String fddbrsfzh;
	/** 被询问人身份证号 */
	private String bxwrsfzh;
	/** 被询问自然人身份证号 */
	private String bxwzrrsfzh;
	
	/** 最后询问的答案 */
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
