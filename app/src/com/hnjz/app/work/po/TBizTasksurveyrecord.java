package com.hnjz.app.work.po;

import java.util.Date;
import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKSURVEYRECORD 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:13:03 CST 2015
 * 功能描述：勘察笔录
 */ 

@SuppressWarnings("serial")
public class TBizTasksurveyrecord extends BaseObject {
	/** 任务ID */
	private String taskid;
	/** 当事人 */
	private String dsr;
	/** 地址 */
	private String dz;
	/** 勘察开始时间 */
	private Date starttime;
	/** 勘察结束时间 */
	private Date endtime;
	/** 地点 */
	private String dd;
	/** 勘察人 */
	private String kcrid;
	/** 执法证号 */
	private String zfzh;
	/** 记录人 */
	private String jlr;
	/** 记录人执法证号 */
	private String jlrzfzh;
	/** 勘察记录 */
	private String kcjl;
	/** 执法人员单位名称 */
	private String zfrydwmc;

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setDsr(String dsr){
		this.dsr = dsr;
	}
	public String getDsr(){
		return dsr;
	}
	public void setDz(String dz){
		this.dz = dz;
	}
	public String getDz(){
		return dz;
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
	public void setDd(String dd){
		this.dd = dd;
	}
	public String getDd(){
		return dd;
	}
	public void setKcrid(String kcrid){
		this.kcrid = kcrid;
	}
	public String getKcrid(){
		return kcrid;
	}
	public void setZfzh(String zfzh){
		this.zfzh = zfzh;
	}
	public String getZfzh(){
		return zfzh;
	}
	public void setJlr(String jlr){
		this.jlr = jlr;
	}
	public String getJlr(){
		return jlr;
	}
	public void setJlrzfzh(String jlrzfzh){
		this.jlrzfzh = jlrzfzh;
	}
	public String getJlrzfzh(){
		return jlrzfzh;
	}
	public void setKcjl(String kcjl){
		this.kcjl = kcjl;
	}
	public String getKcjl(){
		return kcjl;
	}
	public void setZfrydwmc(String zfrydwmc){
		this.zfrydwmc = zfrydwmc;
	}
	public String getZfrydwmc(){
		return zfrydwmc;
	}
}

