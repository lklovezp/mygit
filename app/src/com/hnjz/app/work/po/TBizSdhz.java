package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
* T_BIZ_SDHZ ʵ����
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 23 2016
 * �����������ʹ��֤
 */ 

@SuppressWarnings("serial")
public class TBizSdhz extends BaseObject {
	/** ����ID */
	private String taskid;
	/** �ʹ��������Ƽ��ĺ� */
	private String sdwsmc;
	/** ���ʹ������ƻ����� */
	private String jsrmc;
	/** �ʹ�ص� */
	private String jsrdz;
	/** �ʹ﷽ʽ */
	private String sdfs;
	/** ��ע */
	private String remark;
	/** ����������˱�ʶ */
	private String areaId;
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getSdwsmc() {
		return sdwsmc;
	}
	public void setSdwsmc(String sdwsmc) {
		this.sdwsmc = sdwsmc;
	}
	public String getJsrmc() {
		return jsrmc;
	}
	public void setJsrmc(String jsrmc) {
		this.jsrmc = jsrmc;
	}
	public String getJsrdz() {
		return jsrdz;
	}
	public void setJsrdz(String jsrdz) {
		this.jsrdz = jsrdz;
	}
	public String getSdfs() {
		return sdfs;
	}
	public void setSdfs(String sdfs) {
		this.sdfs = sdfs;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}
