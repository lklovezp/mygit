package com.hnjz.sys.po;


/**
 * 字典数据
 * 
 * @author wumi
 * @version $Id: DicData.java, v 0.1 Jan 6, 2013 12:01:56 PM wumi Exp $
 */
public class SysExport extends BaseObject {

    /***/
    private static final long serialVersionUID = 6614464622873256178L;


    /** id */
    private String            id;
    /**表名*/
    private String            tablename;
    /**字段名称*/
    private String            name;
    /**备注*/
    private String            note;
    /**字典代码*/
    private String            diccode;
    /**字段类型*/
    private String            fieldtype;
    /**字段长度*/
    private Integer 		  fieldlength;
    /**重复判断*/
    private String            parameterCode;//1,主键;2,重重判断;3,字典CODE;4,字典NAME
    /**默认值*/
    private String            defaultCode;//1,有效;2,当前时间;3,当前用户
    /*是否必填*/
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
