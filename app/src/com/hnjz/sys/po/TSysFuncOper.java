package com.hnjz.sys.po;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：
系统功能对应的操作
 *
 */
public class TSysFuncOper extends BaseObject {

	private static final long serialVersionUID = -7914998541139766765L;

	private TSysFunc function;

	private String onclickEvent;

	private String linkAddr;

	private String opername;

	public TSysFunc getFunction() {
		return function;
	}

	public void setFunction(TSysFunc function) {
		this.function = function;
	}

	public String getOnclickEvent() {
		return onclickEvent;
	}

	public void setOnclickEvent(String onclickEvent) {
		this.onclickEvent = onclickEvent;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

}
