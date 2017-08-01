package com.hnjz.sys.po;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：
部门
 *
 */
public class TSysOrg extends BaseObject {

	private static final long serialVersionUID = 660180145948451537L;

	private String name;

	private TSysOrg org;

	private String unitname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TSysOrg getOrg() {
		return org;
	}

	public void setOrg(TSysOrg org) {
		this.org = org;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

}
