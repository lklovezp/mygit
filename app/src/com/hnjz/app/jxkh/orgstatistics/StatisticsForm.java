package com.hnjz.app.jxkh.orgstatistics;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
		绩效考核统计信息对象
 *
 */
public class StatisticsForm {
	/** 部门id */
	private String orgid;
	/** 部门名称 */
	private String orgname;
	/** 用户id */
	private String userid;
	/** 用户名称 */
	private String username;
	/** 下达次数 */
	private Integer xd = 0;
	/** 完成次数 */
	private Integer wc = 0;
	/** 逾期完成 */
	private Integer yqwc = 0;
	/** 正在办理 */
	private Integer zzbl = 0;
	/** 逾期正在办理 */
	private Integer yqzzbl = 0;
	/** 序号*/
	private String seq;
	
	public StatisticsForm(){
		
	}
	
	/**
	 * 
	 * 函数介绍：按部门统计使用的构造函数
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public StatisticsForm(String orgid,String orgname,Integer xd,Integer wc,Integer yqwc,Integer zzbl,Integer yqzzbl){
		this.orgid = orgid;
		this.orgname = orgname;
		this.xd = xd;
		this.wc = wc;
		this.yqwc = yqwc;
		this.zzbl = zzbl;
		this.yqzzbl = yqzzbl;
	}
	/**
	 * 
	 * 函数介绍：按人员统计使用的构造函数
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public StatisticsForm(String orgid,String orgname,String userid,String username,Integer xd,Integer wc,Integer yqwc,Integer zzbl,Integer yqzzbl){
		this.orgid = orgid;
		this.orgname = orgname;
		this.userid = userid;
		this.username = username;
		this.xd = xd;
		this.wc = wc;
		this.yqwc = yqwc;
		this.zzbl = zzbl;
		this.yqzzbl = yqzzbl;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getXd() {
		return xd;
	}

	public void setXd(Integer xd) {
		this.xd = xd;
	}

	public Integer getWc() {
		return wc;
	}

	public void setWc(Integer wc) {
		this.wc = wc;
	}

	public Integer getYqwc() {
		return yqwc;
	}

	public void setYqwc(Integer yqwc) {
		this.yqwc = yqwc;
	}

	public Integer getZzbl() {
		return zzbl;
	}

	public void setZzbl(Integer zzbl) {
		this.zzbl = zzbl;
	}

	public Integer getYqzzbl() {
		return yqzzbl;
	}

	public void setYqzzbl(Integer yqzzbl) {
		this.yqzzbl = yqzzbl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}
