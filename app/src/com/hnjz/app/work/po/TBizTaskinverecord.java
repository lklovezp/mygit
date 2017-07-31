package com.hnjz.app.work.po;

import java.util.Date;
import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKINVERECORD 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:13:03 CST 2015
 * 功能描述：询问笔录
 */ 

@SuppressWarnings("serial")
public class TBizTaskinverecord extends BaseObject {
	/** 任务ID */
	private String taskid;
	/** 询问开始时间 */
	private Date starttime;
	/** 询问结束时间 */
	private Date endtime;
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

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setStarttime(Date starttime){
		this.starttime = starttime;
	}
	public Date getStarttime(){
		return starttime;
	}
	public void setEndtime(Date endtime){
		this.endtime = endtime;
	}
	public Date getEndtime(){
		return endtime;
	}
	public void setXwdd(String xwdd){
		this.xwdd = xwdd;
	}
	public String getXwdd(){
		return xwdd;
	}
	public void setBxwdwmc(String bxwdwmc){
		this.bxwdwmc = bxwdwmc;
	}
	public String getBxwdwmc(){
		return bxwdwmc;
	}
	public void setFddbr(String fddbr){
		this.fddbr = fddbr;
	}
	public String getFddbr(){
		return fddbr;
	}
	public void setFddbrdh(String fddbrdh){
		this.fddbrdh = fddbrdh;
	}
	public String getFddbrdh(){
		return fddbrdh;
	}
	public void setBxwrxm(String bxwrxm){
		this.bxwrxm = bxwrxm;
	}
	public String getBxwrxm(){
		return bxwrxm;
	}
	public void setBxwrxb(String bxwrxb){
		this.bxwrxb = bxwrxb;
	}
	public String getBxwrxb(){
		return bxwrxb;
	}
	public void setBxwrnl(String bxwrnl){
		this.bxwrnl = bxwrnl;
	}
	public String getBxwrnl(){
		return bxwrnl;
	}
	public void setBxwrzw(String bxwrzw){
		this.bxwrzw = bxwrzw;
	}
	public String getBxwrzw(){
		return bxwrzw;
	}
	public void setBxwrdh(String bxwrdh){
		this.bxwrdh = bxwrdh;
	}
	public String getBxwrdh(){
		return bxwrdh;
	}
	public void setBxwzrrxm(String bxwzrrxm){
		this.bxwzrrxm = bxwzrrxm;
	}
	public String getBxwzrrxm(){
		return bxwzrrxm;
	}
	public void setBxwzrrxb(String bxwzrrxb){
		this.bxwzrrxb = bxwzrrxb;
	}
	public String getBxwzrrxb(){
		return bxwzrrxb;
	}
	public void setBxwzrrnl(String bxwzrrnl){
		this.bxwzrrnl = bxwzrrnl;
	}
	public String getBxwzrrnl(){
		return bxwzrrnl;
	}
	public void setBxwzrrdh(String bxwzrrdh){
		this.bxwzrrdh = bxwzrrdh;
	}
	public String getBxwzrrdh(){
		return bxwzrrdh;
	}
	public void setBxwzrrszdw(String bxwzrrszdw){
		this.bxwzrrszdw = bxwzrrszdw;
	}
	public String getBxwzrrszdw(){
		return bxwzrrszdw;
	}
	public void setBxwzrrzz(String bxwzrrzz){
		this.bxwzrrzz = bxwzrrzz;
	}
	public String getBxwzrrzz(){
		return bxwzrrzz;
	}
	public void setBxwzrrybagx(String bxwzrrybagx){
		this.bxwzrrybagx = bxwzrrybagx;
	}
	public String getBxwzrrybagx(){
		return bxwzrrybagx;
	}
	public void setZfrydwmc(String zfrydwmc){
		this.zfrydwmc = zfrydwmc;
	}
	public String getZfrydwmc(){
		return zfrydwmc;
	}
	public void setZfrynames(String zfrynames){
		this.zfrynames = zfrynames;
	}
	public String getZfrynames(){
		return zfrynames;
	}
	public void setZfzhs(String zfzhs){
		this.zfzhs = zfzhs;
	}
	public String getZfzhs(){
		return zfzhs;
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

