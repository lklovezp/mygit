package com.hnjz.app.work.danger;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class WaterForm {
	private String id;
	private String pid;
	private String qxlxdm;
	private String stmc;
	private String stdm;
	private String dbs;
	private String hs;
	private String qjstmc;
	private String qjstdm;
	private String qjdbs;
	private String qjhs;
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
	public String getQxlxdm() {
		return qxlxdm;
	}
	public void setQxlxdm(String qxlxdm) {
		this.qxlxdm = qxlxdm;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	public String getStdm() {
		return stdm;
	}
	public void setStdm(String stdm) {
		this.stdm = stdm;
	}
	public String getDbs() {
		return dbs;
	}
	public void setDbs(String dbs) {
		this.dbs = dbs;
	}
	public String getHs() {
		return hs;
	}
	public void setHs(String hs) {
		this.hs = hs;
	}
	public String getQjstmc() {
		return qjstmc;
	}
	public void setQjstmc(String qjstmc) {
		this.qjstmc = qjstmc;
	}
	public String getQjstdm() {
		return qjstdm;
	}
	public void setQjstdm(String qjstdm) {
		this.qjstdm = qjstdm;
	}
	public String getQjdbs() {
		return qjdbs;
	}
	public void setQjdbs(String qjdbs) {
		this.qjdbs = qjdbs;
	}
	public String getQjhs() {
		return qjhs;
	}
	public void setQjhs(String qjhs) {
		this.qjhs = qjhs;
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
