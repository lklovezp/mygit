/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.sys.po;

import java.io.Serializable;
import java.util.Date;

import com.hnjz.sys.po.TSysUser;

/**
 * 
 * ���ߣ�������
 * �������ڣ�2015-2-10
 * ����������
����Object
 *
 */
public class BaseObject implements Serializable {

	/**  */
	private static final long serialVersionUID = -8763130179116786580L;

	private String id;

	/**�汾��*/
	private Integer version;

	/**��ע*/
	private String describe;

	/**����*/
	private Integer orderby;

	/**�Ƿ���Ч*/
	private String isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public TSysUser getUpdateby() {
		return updateby;
	}

	public void setUpdateby(TSysUser updateby) {
		this.updateby = updateby;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public TSysUser getCreateby() {
		return createby;
	}

	public void setCreateby(TSysUser createby) {
		this.createby = createby;
	}

	/**����ʱ��*/
	private Date updated;

	/**�޸�������*/
	private TSysUser updateby;

	/**�޸�ʱ��*/
	private Date created;

	/**����������*/
	private TSysUser createby;

}