package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�������
 * �������ڣ�2015-2-13
 * ����������
���Ź�Ͻ����
 *
 */
public class TSysOrgArea extends BaseObject {

	private static final long serialVersionUID = 6177130978054537474L;

	private TSysOrg org;

	private TSysArea area;

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

}