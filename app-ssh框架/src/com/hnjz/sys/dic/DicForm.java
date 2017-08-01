/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

/**
 * 字典
 * 
 * @author ljf
 * @version $Id: DicForm.java, v 0.1 Jan 15, 2013 5:05:03 PM wumi Exp $
 */
public class DicForm {

    /** id */
    private String            id;

    /**type 收发文类型*/
    private String            type;
    /**type 收发文类型显示*/
    private String            typename;

	/**代码*/
    private String            pid;
    /**节点名称*/
    private String            pName;
    /**名称*/
    private String            name;
    /**备注*/
    private String            note;
    
    private String isActive; // 是否有效

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/** order 显示顺序 */
    private Integer           order;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	
}
