package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_LAWDOC 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:03 CST 2015
 * 功能描述：执法文件
 */ 

@SuppressWarnings("serial")
public class TDataLawdoc extends BaseObject {
	/** 执法文书名称 */
	private String name;
	/** 执法文书目录标识 */
	private String dirid;
	/** 关键词 */
	private String keywords;
	/** 附件标识 */
	private String fileid;
	
	
	public TDataLawdoc(){
		
	}
	/**
	 * 
	 * 函数介绍：新建时的构造函数
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataLawdoc(String name,String dirid,String keywords,String fileid){
		this.name = name;
		this.dirid = dirid;
		this.keywords = keywords;
		this.fileid = fileid;
		TSysUser user = CtxUtil.getCurUser();
		this.setOrderby(0);
		this.setIsActive("N");
		this.setCreateby(user);
		this.setCreated(new Date(System.currentTimeMillis()));
		this.setUpdateby(user);
		this.setUpdated(new Date(System.currentTimeMillis()));
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setDirid(String dirid){
		this.dirid = dirid;
	}
	public String getDirid(){
		return dirid;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setFileid(String fileid){
		this.fileid = fileid;
	}
	public String getFileid(){
		return fileid;
	}
}

