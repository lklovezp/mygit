package com.hnjz.app.work.danger;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class WaterProjectForm {
	private String id;
	private String pid;
	private String bhmbmc;
	private String lx;
	private Integer sl;
	private String jd;
	private String wd;
	private Integer jl;
	private String sxhjgn;
	private String areaId;
	private String order;
	/**版本号*/
    private Integer           version;
    /**是否有效*/
    private String            isActive;
    /**创建人姓名*/
    private TSysUser              creater;
    /**修改人姓名*/
    private TSysUser              updateby;
    /**创建时间*/
    private Date              updateTime;
    /**修改时间*/
    private Date              createTime;
    
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBhmbmc() {
		return bhmbmc;
	}
	public void setBhmbmc(String bhmbmc) {
		this.bhmbmc = bhmbmc;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getWd() {
		return wd;
	}
	public void setWd(String wd) {
		this.wd = wd;
	}
	public Integer getJl() {
		return jl;
	}
	public void setJl(Integer jl) {
		this.jl = jl;
	}
	public String getSxhjgn() {
		return sxhjgn;
	}
	public void setSxhjgn(String sxhjgn) {
		this.sxhjgn = sxhjgn;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public TSysUser getCreater() {
		return creater;
	}
	public void setCreater(TSysUser creater) {
		this.creater = creater;
	}
	public TSysUser getUpdateby() {
		return updateby;
	}
	public void setUpdateby(TSysUser updateby) {
		this.updateby = updateby;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
