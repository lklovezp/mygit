package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�������
 * �������ڣ�2015-2-13
 * ����������
�û�+���� ����
 *
 */
public class TSysUserOrg extends BaseObject {

	private static final long serialVersionUID = -134035310811473753L;

	private TSysUser user;

	private TSysOrg org;

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	public TSysOrg getOrg() {
		return org;
	}

	public void setOrg(TSysOrg org) {
		this.org = org;
	}

}