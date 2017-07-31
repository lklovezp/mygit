package com.hnjz.app.data.xxgl.complaint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_COMPLAINT ʵ����
 * ���ߣ�������
 * �������ڣ�Mon Mar 23 09:26:02 CST 2015
 * ����������Ͷ����Ϣ
 */

public class ComplaintForm {
	private String id;

	/** ִ����������  **/
	private String lawobjtypeid;
	/** ��λ */
	private String lawobjid;
	
	/** ��λ���� */
	private String lawobjname;

	/** ��λ��ַ */
	private String lawobjaddr;

	/** Ͷ��ʱ�� */
	private String cpdate;

	/** Ͷ��ԭ�� */
	private String reason;

	/** Ͷ�������� */
	private String cpusername;

	/** Ͷ������ϵ�绰 */
	private String cpmobile;

	/** ����ʱ�� */
	private String handletime;

	/** ������ʩ */
	private String control;

	/** ��ע */
	private String desc;

	/** �������� */
	private String areaid;

	/** �Ƿ���Ч**/
	private String isActive;

	/**
	 * 
	 * �������ܣ�ת��Ϊtdatacomplaint����
	
	 * ���������
	
	 * ����ֵ��
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