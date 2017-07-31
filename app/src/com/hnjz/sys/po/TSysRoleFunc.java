package com.hnjz.sys.po;

/**
 * 
 * 作者：zhaowenming
 * 生成日期：2015-2-10
 * 功能描述：
角色功能
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
