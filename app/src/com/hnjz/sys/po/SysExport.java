package com.hnjz.sys.po;


/**
 * �ֵ�����
 * 
 * @author wumi
 * @version $Id: DicData.java, v 0.1 Jan 6, 2013 12:01:56 PM wumi Exp $
 */
public class SysExport extends BaseObject {

    /***/
    private static final long serialVersionUID = 6614464622873256178L;


    /** id */
    private String            id;
    /**����*/
    private String            tablename;
    /**�ֶ�����*/
    private String            name;
    /**��ע*/
    private String            note;
    /**�ֵ����*/
    private String            diccode;
    /**�ֶ�����*/
    private String            fieldtype;
    /**�ֶγ���*/
    private Integer 		  fieldlength;
    /**�ظ��ж�*/
    private String            parameterCode;//1,����;2,�����ж�;3,�ֵ�CODE;4,�ֵ�NAME
    /**Ĭ��ֵ*/
    private String            defaultCode;//1,��Ч;2,��ǰʱ��;3,��ǰ�û�
    /*�Ƿ����*/
    private String 			 isRequired;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDiccode() {
		return diccode;
	}
	public void setDiccode(String diccode) {
		this.diccode = diccode;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getDefaultCode() {
		return defaultCode;
	}
	public void setDefaultCode(String defaultCode) {
		this.defaultCode = defaultCode;
	}
	public String getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}
	public Integer getFieldlength() {
		return fieldlength;
	}
	public void setFieldlength(Integer fieldlength) {
		this.fieldlength = fieldlength;
	}


 
	
}