package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�zhaowenming
 * �������ڣ�2015-2-10
 * ����������
��ɫ-���ܲ���
 *
 */
public class TSysRoleFuncOper extends BaseObject {

	private static final long serialVersionUID = -5162708031394910127L;

	private TSysRole role;

	private TSysFuncOper funcoper;

	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

	public TSysFuncOper getFuncoper() {
		return funcoper;
	}

	public void setFuncoper(TSysFuncOper funcoper) {
		this.funcoper = funcoper;
	}

}