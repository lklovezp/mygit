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

    /**type 字典类型*/
    private String            dicType;
    
    /**type 字典类型id*/
    private String            typeId;
    /**代码*/
    private String            code;
    /**名称*/
    private String            name;

    /** order 显示顺序 */
    private Integer           order;

    /** 备注 */
    private String            note;
    /**状态 */
    private String 				state;
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/** （是否必填）附件类型使用**/
	private String mandatory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }
    

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
  
}
