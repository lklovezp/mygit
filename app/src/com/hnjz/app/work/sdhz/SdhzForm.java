package com.hnjz.app.work.sdhz;



/**
 * 送达回证的form
 * 作者：zhangqingfeng
 * 生成日期：Mar 22, 2016
 * 功能描述：
 *
 */
public class SdhzForm {
	/** id **/
	String id;
	/** 任务ID */
	private String taskid;
	/** 送达文书名称及文号 */
	private String sdwsmc;
	/** 受送达人名称或姓名 */
	private String jsrmc;
	/** 送达地点 */
	private String jsrdz;
	/** 送达方式 */
	private String sdfs;
	/** 备注 */
	private String remark;
	/** 数据区域过滤标识 */
	private String areaId;
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
