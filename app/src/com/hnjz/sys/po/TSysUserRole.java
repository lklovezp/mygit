package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�������
 * �������ڣ�2015-2-13
 * ����������
�û���ɫ����
 *
 */
public class TSysUserRole extends BaseObject {

	private static final long serialVersionUID = 8109035739674575923L;

	private TSysUser user;

	private TSysRole role;

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

}