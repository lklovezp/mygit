/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

/**
 * �ֵ�
 * 
 * @author ljf
 * @version $Id: DicForm.java, v 0.1 Jan 15, 2013 5:05:03 PM wumi Exp $
 */
public class DicForm {

    /** id */
    private String            id;

    /**type �ֵ�����*/
    private String            dicType;
    
    /**type �ֵ�����id*/
    private String            typeId;
    /**����*/
    private String            code;
    /**����*/
    private String            name;

    /** order ��ʾ˳�� */
    private Integer           order;

    /** ��ע */
    private String            note;
    /**״̬ */
    private String 				state;
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/** ���Ƿ�����������ʹ��**/
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