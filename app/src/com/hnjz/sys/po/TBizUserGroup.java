package com.hnjz.sys.po;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-11-15
 * 功能描述：用户会商接收人分组
 *
 */
public class TBizUserGroup extends BaseObject {

	private static final long serialVersionUID = 8109035739674575923L;

	private TSysUser user;

	private TSysDic group;

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	public TSysDic getGroup() {
		return group;
	}

	public void setGroup(TSysDic group) {
		this.group = group;
	}

}
