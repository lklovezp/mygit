package com.hnjz.sys.po;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：
区域
 *
 */
public class TSysArea extends BaseObject {
	
	private static final long serialVersionUID = 3168516511900202893L;

	private String code;
	
	private String name;

	private TSysArea area;

	private TSysServer server;

	private String type;

	private String unitname;

	private String shortunitname;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TSysArea getArea() {
		return area;
	}

	public void setArea(TSysArea area) {
		this.area = area;
	}

	public TSysServer getServer() {
		return server;
	}

	public void setServer(TSysServer server) {
		this.server = server;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getShortunitname() {
		return shortunitname;
	}

	public void setShortunitname(String shortunitname) {
		this.shortunitname = shortunitname;
	}

}
