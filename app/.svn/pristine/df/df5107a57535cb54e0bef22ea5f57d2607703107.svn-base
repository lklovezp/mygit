package com.hnjz.app.data.xxgl.complaint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_COMPLAINT 实体类
 * 作者：张少卫
 * 生成日期：Mon Mar 23 09:26:02 CST 2015
 * 功能描述：投诉信息
 */

public class ComplaintForm {
	private String id;

	/** 执法对象类型  **/
	private String lawobjtypeid;
	/** 单位 */
	private String lawobjid;
	
	/** 单位名称 */
	private String lawobjname;

	/** 单位地址 */
	private String lawobjaddr;

	/** 投诉时间 */
	private String cpdate;

	/** 投诉原因 */
	private String reason;

	/** 投诉人姓名 */
	private String cpusername;

	/** 投诉人联系电话 */
	private String cpmobile;

	/** 处理时间 */
	private String handletime;

	/** 治理措施 */
	private String control;

	/** 备注 */
	private String desc;

	/** 隶属区域 */
	private String areaid;

	/** 是否有效**/
	private String isActive;

	/**
	 * 
	 * 函数介绍：转化为tdatacomplaint对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataComplaint transToTDataComplaint(TDataComplaint tDataComplaint) {
		try {
			TSysUser user = CtxUtil.getCurUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (tDataComplaint == null) {
				tDataComplaint = new TDataComplaint();
				tDataComplaint.setCreateby(user);
				tDataComplaint.setCreated(new Date(System.currentTimeMillis()));
			}
			tDataComplaint.setLawobjtypeid(lawobjtypeid);
			tDataComplaint.setLawobjid(lawobjid);
			tDataComplaint.setLawobjname(lawobjname);
			tDataComplaint.setLawobjaddr(lawobjaddr);
			if (StringUtils.isNotBlank(cpdate)) {
				tDataComplaint.setCpdate(sdf.parse(cpdate));
			}
			tDataComplaint.setReason(reason);
			tDataComplaint.setCpusername(cpusername);
			tDataComplaint.setCpmobile(cpmobile);
			if (StringUtils.isNotBlank(handletime)) {
				tDataComplaint.setHandletime(sdf.parse(handletime));
			}
			tDataComplaint.setControl(control);
			tDataComplaint.setDesc(desc);
			tDataComplaint.setAreaid(user.getAreaId());
			tDataComplaint.setIsActive(isActive);
			tDataComplaint.setUpdateby(user);
			tDataComplaint.setUpdated(new Date(System.currentTimeMillis()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tDataComplaint;
	}

	public void setLawobjname(String lawobjname) {
		this.lawobjname = lawobjname;
	}

	public String getLawobjname() {
		return lawobjname;
	}

	public void setLawobjaddr(String lawobjaddr) {
		this.lawobjaddr = lawobjaddr;
	}

	public String getLawobjaddr() {
		return lawobjaddr;
	}

	public String getCpdate() {
		return cpdate;
	}

	public void setCpdate(String cpdate) {
		this.cpdate = cpdate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setCpusername(String cpusername) {
		this.cpusername = cpusername;
	}

	public String getCpusername() {
		return cpusername;
	}

	public void setCpmobile(String cpmobile) {
		this.cpmobile = cpmobile;
	}

	public String getCpmobile() {
		return cpmobile;
	}

	public String getHandletime() {
		return handletime;
	}

	public void setHandletime(String handletime) {
		this.handletime = handletime;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getControl() {
		return control;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreaid() {
		return areaid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
