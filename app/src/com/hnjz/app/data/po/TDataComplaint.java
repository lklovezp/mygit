package com.hnjz.app.data.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hnjz.app.data.xxgl.complaint.ComplaintForm;
import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_COMPLAINT 实体类
 * 作者：张少卫
 * 生成日期：Mon Mar 23 09:26:02 CST 2015
 * 功能描述：投诉信息
 */ 

@SuppressWarnings("serial")
public class TDataComplaint extends BaseObject {
	/** 执法对象类型id **/
	private String lawobjtypeid;
	/** 执法对象id */
	private String lawobjid;
	/** 单位名称 */
	private String lawobjname;
	/** 单位地址 */
	private String lawobjaddr;
	/** 投诉时间 */
	private Date cpdate;
	/** 投诉原因 */
	private String reason;
	/** 投诉人姓名 */
	private String cpusername;
	/** 投诉人联系电话 */
	private String cpmobile;
	/** 处理时间 */
	private Date handletime;
	/** 治理措施 */
	private String control;
	/** 备注 */
	private String desc;
	/** 隶属区域 */
	private String areaid;
	
	/**
	 * 
	 * 函数介绍：转化为form对象在页面上展示
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ComplaintForm transToComplaintForm(ComplaintForm complaintForm){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		complaintForm.setId(this.getId());
		complaintForm.setLawobjid(lawobjid);
		complaintForm.setLawobjtypeid(lawobjtypeid);
		complaintForm.setLawobjname(lawobjname);
		complaintForm.setLawobjaddr(lawobjaddr);
		if(cpdate!=null){
			complaintForm.setCpdate(sdf.format(cpdate));
		}
		complaintForm.setReason(reason);
		complaintForm.setCpusername(cpusername);
		complaintForm.setCpmobile(cpmobile);
		if(handletime!=null){
			complaintForm.setHandletime(sdf.format(handletime));
		}
		complaintForm.setControl(control);
		complaintForm.setDesc(desc);
		complaintForm.setIsActive(this.getIsActive());
		return complaintForm;
	}

	public void setLawobjname(String lawobjname){
		this.lawobjname = lawobjname;
	}
	public String getLawobjname(){
		return lawobjname;
	}
	public void setLawobjaddr(String lawobjaddr){
		this.lawobjaddr = lawobjaddr;
	}
	public String getLawobjaddr(){
		return lawobjaddr;
	}
	public void setCpdate(Date cpdate){
		this.cpdate = cpdate;
	}
	public Date getCpdate(){
		return cpdate;
	}
	public void setReason(String reason){
		this.reason = reason;
	}
	public String getReason(){
		return reason;
	}
	public void setCpusername(String cpusername){
		this.cpusername = cpusername;
	}
	public String getCpusername(){
		return cpusername;
	}
	public void setCpmobile(String cpmobile){
		this.cpmobile = cpmobile;
	}
	public String getCpmobile(){
		return cpmobile;
	}
	public void setHandletime(Date handletime){
		this.handletime = handletime;
	}
	public Date getHandletime(){
		return handletime;
	}
	public void setControl(String control){
		this.control = control;
	}
	public String getControl(){
		return control;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	public void setAreaid(String areaid){
		this.areaid = areaid;
	}
	public String getAreaid(){
		return areaid;
	}
	public String getLawobjid() {
		return lawobjid;
	}

	public void setLawobjid(String lawobjid) {
		this.lawobjid = lawobjid;
	}

	public String getLawobjtypeid() {
		return lawobjtypeid;
	}

	public void setLawobjtypeid(String lawobjtypeid) {
		this.lawobjtypeid = lawobjtypeid;
	}
}

