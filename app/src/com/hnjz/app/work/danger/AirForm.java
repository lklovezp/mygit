package com.hnjz.app.work.danger;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class AirForm {
	private String id;
	private String pid;
	private String type;
	private String areaId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
