package com.hnjz.app.work.po;

import java.util.Date;
import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKSURVEYRECORD ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * ���������������¼
 */ 

@SuppressWarnings("serial")
public class TBizTasksurveyrecord extends BaseObject {
	/** ����ID */
	private String taskid;
	/** ������ */
	private String dsr;
	/** ��ַ */
	private String dz;
	/** ���쿪ʼʱ�� */
	private Date starttime;
	/** �������ʱ�� */
	private Date endtime;
	/** �ص� */
	private String dd;
	/** ������ */
	private String kcrid;
	/** ִ��֤�� */
	private String zfzh;
	/** ��¼�� */
	private String jlr;
	/** ��¼��ִ��֤�� */
	private String jlrzfzh;
	/** �����¼ */
	private String kcjl;
	/** ִ����Ա��λ���� */
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
