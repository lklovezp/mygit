package com.hnjz.app.drafter.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;

/**
 * ���������������
 * @author shiqiuhan
 * @created 2015-12-24,����08:58:03
 */
public class TBizDrafter extends BaseObject {

private static final long serialVersionUID = 1517474713052732108L;
	
	private String name; //�������
	
	private String drafterId; //�����id
	
	private String drafterName; //���������
	
	private String drafterOrgid; //����˲���id
	
	private String drafterOrgname; //����˲�������
	
	private Integer state; //���״̬
	
	private Date submitDate; //����ύ����
	
	private TSysUser audit;	//�����
	
	private Date auditDate; //�������
	
	private String describe; //��ע
	
	private TSysArea area;	//����
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDrafterId() {
		return drafterId;
	}

	public void setDrafterId(String drafterId) {
		this.drafterId = drafterId;
	}

	public String getDrafterName() {
		return drafterName;
	}

	public void setDrafterName(String drafterName) {
		this.drafterName = drafterName;
	}

	public String getDrafterOrgid() {
		return drafterOrgid;
	}

	public void setDrafterOrgid(String drafterOrgid) {
		this.drafterOrgid = drafterOrgid;
	}

	public String getDrafterOrgname() {
		return drafterOrgname;
	}

	public void setDrafterOrgname(String drafterOrgname) {
		this.drafterOrgname = drafterOrgname;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public TSysUser getAudit() {
		return audit;
	}

	public void setAudit(TSysUser audit) {
		this.audit = audit;
	}

	public TSysArea getArea() {
		return area;
	}

	public void setArea(TSysArea area) {
		this.area = area;
	}

}