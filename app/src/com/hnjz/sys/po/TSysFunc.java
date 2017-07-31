package com.hnjz.sys.po;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：
系统功能
 *
 */
public class TSysFunc extends BaseObject {
	
	private static final long serialVersionUID = -3552739192646459451L;

	private String name;

	private TSysFunc function;

	private String type;

	private String linkAddr;

	private String style;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TSysFunc getFunction() {
		return function;
	}

	public void setFunction(TSysFunc function) {
		this.function = function;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
