package com.hnjz.app.drafter.po;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;

/**
 * ������������������������
 * @author shiqiuhan
 * @created 2015-12-24,����08:58:03
 */
public class TDataDrafterSet extends BaseObject {

	private static final long serialVersionUID = 1517474713052732108L;

	private String audit;	//�����
	
	private TSysOrg org;		//����
	
	private TSysArea area;	//����
	
	private String type;	//��˼��� 1������2����׼��3������

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public TSysOrg getOrg() {
		return org;
	}

	public void setOrg(TSysOrg org) {
		this.org = org;
	}

	public TSysArea getArea() {
		return area;
	}

	public void setArea(TSysArea area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}