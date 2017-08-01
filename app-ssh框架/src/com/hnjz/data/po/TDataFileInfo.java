package com.hnjz.data.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * TDataFileInfo 实体类
 * 作者：时秋寒
 * 功能描述：收发文信息类
 */ 

@SuppressWarnings("serial")
public class TDataFileInfo extends BaseObject {
	/** 文件类型 0 发文 1 收文 */
	private String type; 
	/** 文件标题 */
	private String title;
	/** 文号 */
	private String number;
	/** 签名 */
	private String autograph;
	/** 日期 */
	private Date sfwdate;
	/** 收发文类型父id */
	private String sourcepid;
	/** 收发文类型id */
	private String sourceid;
	/** 存放位置 */
	private String position;
	/** 年份 */
	private String year;
	/** 编号 */
	private String code;
	/** 是否有效 */
	private String isactive;
	/** 创建人 */
	private TSysUser filecreateby;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public Date getSfwdate() {
		return sfwdate;
	}
	public void setSfwdate(Date sfwdate) {
		this.sfwdate = sfwdate;
	}
	public String getSourcepid() {
		return sourcepid;
	}
	public void setSourcepid(String sourcepid) {
		this.sourcepid = sourcepid;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public TSysUser getFilecreateby() {
		return filecreateby;
	}
	public void setFilecreateby(TSysUser filecreateby) {
		this.filecreateby = filecreateby;
	}

}

