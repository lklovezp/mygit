package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�zhaowenming
 * �������ڣ�2015-2-10
 * ����������
��ɫ����
 *
 */
public class TSysRoleFunc extends BaseObject {

	private static final long serialVersionUID = 967560898440783606L;

	private TSysRole role;

	private TSysFunc function = new TSysFunc();

	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

	public TSysFunc getFunction() {
		return function;
	}

	public void setFunction(TSysFunc function) {
		this.function = function;
	}

}